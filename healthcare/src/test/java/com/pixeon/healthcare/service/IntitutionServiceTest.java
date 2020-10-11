package com.pixeon.healthcare.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
import com.pixeon.base.model.StateChange;
import com.pixeon.base.test.DefaultServiceTest;
import com.pixeon.healthcare.mapper.InstitutionMapper;
import com.pixeon.healthcare.model.Institution;
import com.pixeon.healthcare.repository.InstitutionRepository; 

@Import(ValidationAutoConfiguration.class)
public class IntitutionServiceTest extends DefaultServiceTest {
	
	@Mock
	private InstitutionRepository institutionRepository;
	
	@Mock
	private InstitutionMapper institutionMapper;
	
	@Mock
	private BudgetService budgetService;
	
	@InjectMocks
	private IntitutionService institutionService;
	
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
    private Institution getNewFullInstitution() {
    	Institution fullInstitution = new Institution();
        fullInstitution.setCnpj("11111111111111");
        fullInstitution.setName("Name of institution");
        fullInstitution.setNameCi("Name of institution");
        return fullInstitution;
    }
    
    /**
     * this method call the javax.validation validation that springdata
     * @param institution
     * @throws BusinessException
     */
    private void validateOnSave(Institution institution) {
		Set<ConstraintViolation<Institution>> violations = validator.validate(institution);
		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(violations);
		}
    }
    
	@Test(expected = BusinessException.class)
	public void saveNullTest() throws BusinessException {
		institutionService.save(null);
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void saveEmptyTest() throws BusinessException {
		Institution emptyInstitution = new Institution();
		validateOnSave(emptyInstitution);
		assertEquals(institutionService.save(emptyInstitution), emptyInstitution);
	}
	
	@Test
	public void saveFullTest() throws BusinessException {
		Institution fullInstitution = getNewFullInstitution();
		validateOnSave(fullInstitution);
		when(institutionRepository.save(fullInstitution)).thenReturn(fullInstitution);
		Institution savedInstitution = institutionService.save(fullInstitution);
		assertNotNull(savedInstitution);
		assertNotNull(savedInstitution.getDtCreated());
		assertNotNull(savedInstitution.getDtUpdated());
		assertEquals(fullInstitution.getStateChange(), StateChange.INSERT);
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void emptyCnpjTest() throws BusinessException {
		Institution fullInstitution = getNewFullInstitution();
		fullInstitution.setCnpj("");
		validateOnSave(fullInstitution);
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void sizeCnpjTest() throws BusinessException {
		Institution fullInstitution = getNewFullInstitution();
		fullInstitution.setCnpj("111");
		validateOnSave(fullInstitution);
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void contentCnpjTest() throws BusinessException {
		Institution fullInstitution = getNewFullInstitution();
		fullInstitution.setCnpj("1111111111111a");
		validateOnSave(fullInstitution);
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void emptyNameTest() throws BusinessException {
		Institution fullInstitution = getNewFullInstitution();
		fullInstitution.setName("");
		validateOnSave(fullInstitution);
	}
	

}
