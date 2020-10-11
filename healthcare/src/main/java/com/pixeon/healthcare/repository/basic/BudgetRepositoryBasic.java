package com.pixeon.healthcare.repository.basic;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.pixeon.healthcare.model.Budget;

public interface BudgetRepositoryBasic extends PagingAndSortingRepository<Budget,Long> {

}
