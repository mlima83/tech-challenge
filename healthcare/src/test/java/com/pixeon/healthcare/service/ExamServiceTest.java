package com.pixeon.healthcare.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
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
import com.pixeon.healthcare.model.DebitOn;
import com.pixeon.healthcare.model.Exam;
import com.pixeon.healthcare.model.Gender;
import com.pixeon.healthcare.model.Institution;
import com.pixeon.healthcare.model.Transaction;
import com.pixeon.healthcare.model.TransactionType;
import com.pixeon.healthcare.model.Transactionable;
import com.pixeon.healthcare.repository.BudgetRepository;
import com.pixeon.healthcare.repository.ExamRepository;
import com.pixeon.healthcare.repository.TransactionRepository; 

@Import(ValidationAutoConfiguration.class)
public class ExamServiceTest extends DefaultServiceTest {
	
	@Mock
	private BudgetRepository budgetRepository;
	
	@Mock
	private TransactionRepository transactionRepository;
	
	@Mock
	private ExamRepository examRepository;
	
	@Mock
	private BudgetService budgetService;
	
	@InjectMocks
	private ExamService examService;
	
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
    private void validateOnSave(Exam exam) {
		Set<ConstraintViolation<Exam>> violations = validator.validate(exam);
		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(violations);
		}
    }
    
	@Test(expected = BusinessException.class)
	public void saveNullTest() throws BusinessException {
		examService.save(null);
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void saveEmptyTest() throws BusinessException {
		Exam emptyEntity = new Exam();
		emptyEntity.setInstitution(new Institution());
		validateOnSave(emptyEntity);
	}
	
	@Test
	public void saveFullTest() throws BusinessException {
		Exam exam = getNewFullExam();
		validateOnSave(exam);
		when(examRepository.save(exam)).thenReturn(exam);
		Exam savedExam = examService.save(exam);
		assertNotNull(savedExam);
	}
	
	@Test
	public void debitOnCreateTest() throws BusinessException {
		Exam exam = getNewFullExam();
		exam.setId(null);
		validateOnSave(exam);
		when(examRepository.save(exam)).thenReturn(exam);
		Exam savedExam = examService.save(exam);
		assertNotNull(savedExam);
		verify(budgetService).toDebit(exam, 1);
	}
	
	@Test
	public void getAnExamTest() throws BusinessException {
		Exam exam = getNewFullExam();
		validateOnSave(exam);
		when(examRepository.findByIdAndInstitution_Id(exam.getId(), exam.getInstitution().getId())).thenReturn(exam);
		Exam savedExam = examService.findByExamIdAndIstitutionId(exam.getId(), exam.getInstitution().getId());
		assertNotNull(savedExam);
	}
	
	@Test
	public void getAnExamAndDebitTest() throws BusinessException {
		Exam exam = getNewFullExam();
		validateOnSave(exam);
		when(examRepository.findByIdAndInstitution_Id(exam.getId(), exam.getInstitution().getId())).thenReturn(exam);
		when(budgetService.existsDebitWithProperty(exam, DebitOn.PROPERTY_NAME, DebitOn.GET.toString())).thenReturn(false);
		Exam savedExam = examService.findByExamIdAndIstitutionId(exam.getId(), exam.getInstitution().getId());
		assertNotNull(savedExam);
		verify(budgetService).toDebit(exam, 1);
	}
	
	@Test
	public void getAnExamAndNotDebitTest() throws BusinessException {
		Exam exam = getNewFullExam();
		validateOnSave(exam);
		when(examRepository.findByIdAndInstitution_Id(exam.getId(), exam.getInstitution().getId())).thenReturn(exam);
		when(budgetService.existsDebitWithProperty(exam, DebitOn.PROPERTY_NAME, DebitOn.GET.toString())).thenReturn(true);
		Exam savedExam = examService.findByExamIdAndIstitutionId(exam.getId(), exam.getInstitution().getId());
		assertNotNull(savedExam);
		verify(budgetService, never()).toDebit(exam, 1);
	}
	
	@Test(expected = BusinessException.class)
	public void examNotFoundOnGetTest() throws BusinessException {
		Exam exam = getNewFullExam();
		validateOnSave(exam);
		when(examRepository.findByIdAndInstitution_Id(exam.getId(), exam.getInstitution().getId())).thenReturn(null);
		examService.findByExamIdAndIstitutionId(exam.getId(), exam.getInstitution().getId());
	}
	
	@Test(expected = BusinessException.class)
	public void examNotFoundOnDeleteTest() throws BusinessException {
		Exam exam = getNewFullExam();
		when(examRepository.findByIdAndInstitution_Id(exam.getId(), exam.getInstitution().getId())).thenReturn(null);
		examService.deleteByExamIdAndIstitutionId(exam.getId(), exam.getInstitution().getId());
	}
	
	@Test
	public void deleteTest() throws BusinessException {
		Exam exam = getNewFullExam();
		when(examRepository.findByIdAndInstitution_Id(exam.getId(), exam.getInstitution().getId())).thenReturn(exam);
		examService.deleteByExamIdAndIstitutionId(exam.getId(), exam.getInstitution().getId());
		verify(examRepository).delete(exam);
	}
	
	
}
