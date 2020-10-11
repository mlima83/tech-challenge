package com.pixeon.healthcare.repository;

import com.pixeon.healthcare.model.TransactionType;
import com.pixeon.healthcare.repository.basic.TransactionRepositoryBasic;
import com.pixeon.healthcare.repository.custom.TransactionRepositoryCustom;

public interface TransactionRepository extends TransactionRepositoryCustom, TransactionRepositoryBasic {

	public Long countByOrignKeyAndTransactionType(String orignKey, TransactionType transactionType);
	
	public Long countByOrignKeyAndTransactionTypeAndProperties_keyAndProperties_Value(String orignKey,
			TransactionType transactionType, String propertyKey, String propertyValue);
	
}
