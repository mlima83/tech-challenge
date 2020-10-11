package com.pixeon.healthcare.repository.basic;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.pixeon.healthcare.model.Exam;

public interface ExamRepositoryBasic extends PagingAndSortingRepository<Exam,Long> {

}
