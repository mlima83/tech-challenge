package com.pixeon.healthcare.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pixeon.healthcare.model.Exam;
import com.pixeon.healthcare.repository.basic.ExamRepositoryBasic;
import com.pixeon.healthcare.repository.custom.ExamRepositoryCustom;

public interface ExamRepository extends ExamRepositoryCustom, ExamRepositoryBasic {

	public Page<Exam> findByInstitution_Cnpj(String cnpj, Pageable pageable);
	
	public Exam findByIdAndInstitution_Id(Long examId, Long institutionId);
}
