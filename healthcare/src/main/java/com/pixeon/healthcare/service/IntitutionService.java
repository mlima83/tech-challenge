package com.pixeon.healthcare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.pixeon.base.exception.BusinessException;
import com.pixeon.base.service.DefaultService;
import com.pixeon.healthcare.model.Institution;
import com.pixeon.healthcare.repository.InstitutionRepository;

@Service
@Validated
public class IntitutionService extends DefaultService<Institution, Long>{

	@Autowired
	private InstitutionRepository institutionRepository;
	
	@Autowired
	private BudgetService budgetService;
    
    @Override
    public void afterSave(Institution institution) throws BusinessException {
    	if (institution.isNew()) {
    		budgetService.create(institution);
    	}
    	
    }
    
	@Override
	public PagingAndSortingRepository<Institution, Long> getRepository() {
		return institutionRepository;
	}

}
