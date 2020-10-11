package com.pixeon.healthcare.repository;

import com.pixeon.healthcare.model.Budget;
import com.pixeon.healthcare.repository.basic.BudgetRepositoryBasic;
import com.pixeon.healthcare.repository.custom.BudgetRepositoryCustom;

public interface BudgetRepository extends BudgetRepositoryCustom, BudgetRepositoryBasic {
	
	public Budget findByOrignKey(String orignKey);

}
