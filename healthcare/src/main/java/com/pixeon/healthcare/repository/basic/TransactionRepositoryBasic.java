package com.pixeon.healthcare.repository.basic;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.pixeon.healthcare.model.Transaction;

public interface TransactionRepositoryBasic extends PagingAndSortingRepository<Transaction,Long> {

}
