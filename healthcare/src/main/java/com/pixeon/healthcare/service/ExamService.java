package com.pixeon.healthcare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.pixeon.base.exception.BusinessException;
import com.pixeon.base.service.DefaultService;
import com.pixeon.healthcare.model.DebitOn;
import com.pixeon.healthcare.model.Exam;
import com.pixeon.healthcare.repository.ExamRepository;

@Service
@Validated
public class ExamService extends DefaultService<Exam, Long>{

	@Autowired
	private ExamRepository examRepository;
	
	@Autowired
	private BudgetService budgetService;
	
	@Value("${default.amount.coins}")
	private int defaultAmountCoins;
    
    @Override
    public void afterSave(Exam exam) throws BusinessException {
    	if (exam.isNew()) {
    		budgetService.toDebit(exam, 1);
    	}
    }
    
    public Exam findByExamIdAndIstitutionId(Long examId, Long institutionId) throws BusinessException {
    	Exam exam = examRepository.findByIdAndInstitution_Id(examId, institutionId);
    	if (exam == null) {
    		throw new BusinessException("Exam not found");
    	}
    	if (!budgetService.existsDebitWithProperty(exam, DebitOn.PROPERTY_NAME, DebitOn.GET.toString())) {
    		budgetService.toDebit(exam, 1);
    	}
    	return exam;
    }
    
    public void deleteByExamIdAndIstitutionId(Long examId, Long institutionId) throws BusinessException {
    	Exam exam = this.examRepository.findByIdAndInstitution_Id(examId, institutionId);
    	if (exam == null) {
    		throw new BusinessException("Exam not found");
    	}
    	examRepository.delete(exam);
    }

	@Override
	public PagingAndSortingRepository<Exam, Long> getRepository() {
		return examRepository;
	}

}
