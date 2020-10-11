package com.pixeon.healthcare.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.context.annotation.Import;

import com.pixeon.base.exception.BusinessException;
import com.pixeon.base.test.DefaultServiceTest;
import com.pixeon.healthcare.model.Budget;
import com.pixeon.healthcare.model.Budgetable;
import com.pixeon.healthcare.model.DebitOn;
import com.pixeon.healthcare.model.Exam;
import com.pixeon.healthcare.model.Gender;
import com.pixeon.healthcare.model.Institution;
import com.pixeon.healthcare.model.Transaction;
import com.pixeon.healthcare.model.TransactionType;
import com.pixeon.healthcare.model.Transactionable;
import com.pixeon.healthcare.repository.BudgetRepository;
import com.pixeon.healthcare.repository.TransactionRepository; 

@Import(ValidationAutoConfiguration.class)
public class BudgetServiceTest extends DefaultServiceTest {
	
	@Mock
	private BudgetRepository budgetRepository;
	
	@Mock
	private TransactionRepository transactionRepository;
	
	@InjectMocks
	private BudgetService budgetService;
	
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
    private Institution getNewFullInstitution() {
    	Institution fullInstitution = new Institution();
    	fullInstitution.setId(83L);
        fullInstitution.setCnpj("11111111111111");
        fullInstitution.setName("Institution name");
        fullInstitution.setNameCi("institution name");
        return fullInstitution;
    }
    
    private Exam getNewFullExam() {
    	Exam exam = new Exam();
    	exam.setId(80L);
    	exam.setInstitution(getNewFullInstitution());
    	exam.setPatientAge(30);
    	exam.setPatientGender(Gender.FEMALE);
    	exam.setPatientName("Patient Name");
    	exam.setPatientNameCi("patient name");
    	exam.setPhysicianCRM("2156456454");
    	exam.setPhysicianName("Physician Name");
    	exam.setPhysicianNameCi("physician name");
    	exam.setProcedureName("Procedure name");
    	exam.setProcedureNameCi("procedure name");
        return exam;
    }
    
    private Budget getNewFullBudget(Institution institution, int quantity) {
        return new Budget(institution, quantity);
    }
    
    private Transaction getNewFullTransaction(Budget budget, Transactionable transactionable) {
        return new Transaction(budget, TransactionType.DEBIT, transactionable, 1);
    }
    
    /**
     * this method call the javax.validation validation that springdata
     * @param institution
     * @throws BusinessException
     */
    private void validateOnSave(Budgetable budget) {
		Set<ConstraintViolation<Budgetable>> violations = validator.validate(budget);
		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(violations);
		}
    }
	
	@Test(expected = ConstraintViolationException.class)
	public void saveEmptyTest() throws BusinessException {
		Institution emptyEntity = new Institution();
		validateOnSave(emptyEntity);
	}
	
	@Test
	public void saveFullTest() throws BusinessException {
		Institution fullInstitution = getNewFullInstitution();
		Budget budget = getNewFullBudget(fullInstitution, 20);
		validateOnSave(fullInstitution);
		when(budgetRepository.save(budget)).thenReturn(budget);
		Budget savedBudget = budgetService.create(fullInstitution);
		assertNotNull(savedBudget);
		assertNotNull(savedBudget.getOrignKey());
		assertEquals(savedBudget.getBalance(), 20);
		assertEquals(savedBudget.getOrignKey(), String.valueOf(fullInstitution.getId()));
	}
	
	@Test
	public void toDebitTest() throws BusinessException {
		Institution fullInstitution = getNewFullInstitution();
		final int quantity = 20;
		Budget budget = getNewFullBudget(fullInstitution, quantity);
		Exam exam = getNewFullExam();
		Transaction transaction = getNewFullTransaction(budget, exam);
		when(budgetRepository.findByOrignKey(budget.getOrignKey())).thenReturn(budget);
		when(transactionRepository.save(transaction)).thenReturn(transaction);
		when(budgetRepository.save(budget)).thenReturn(budget);
		
		Budget savedBudget = budgetService.toDebit(exam, 1);
		assertNotNull(savedBudget);
		assertEquals(savedBudget.getBalance(), quantity -1);
	}
	
	@Test(expected = BusinessException.class)
	public void quantityToDebitTest() throws BusinessException {
		Exam exam = getNewFullExam();
		budgetService.toDebit(exam, 0);
	}
	
	@Test(expected = BusinessException.class)
	public void budgetNotFoundToDebitTest() throws BusinessException {
		Exam exam = getNewFullExam();
		budgetService.toDebit(exam, 1);
	}
	
	@Test(expected = BusinessException.class)
	public void hasBaleanceToDebitTest() throws BusinessException {
		Institution fullInstitution = getNewFullInstitution();
		final int quantity = 0;
		Budget budget = getNewFullBudget(fullInstitution, quantity);
		Exam exam = getNewFullExam();
		when(budgetRepository.findByOrignKey(budget.getOrignKey())).thenReturn(budget);
		budgetService.toDebit(exam, 1);
	}
	
	@Test
	public void existsDebitWithPropertyTest() throws BusinessException {
		Exam exam = getNewFullExam();
		when(transactionRepository.countByOrignKeyAndTransactionTypeAndProperties_keyAndProperties_Value(exam.getKey(), TransactionType.DEBIT, DebitOn.PROPERTY_NAME, DebitOn.CREATE.toString())).thenReturn(1L);
		assertTrue(budgetService.existsDebitWithProperty(exam, DebitOn.PROPERTY_NAME, DebitOn.CREATE.toString()));
	}

}
