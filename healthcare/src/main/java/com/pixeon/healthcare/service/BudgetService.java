package com.pixeon.healthcare.service;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.pixeon.base.exception.BusinessException;
import com.pixeon.base.model.DefaultEntity;
import com.pixeon.healthcare.model.Budget;
import com.pixeon.healthcare.model.Budgetable;
import com.pixeon.healthcare.model.Transaction;
import com.pixeon.healthcare.model.TransactionType;
import com.pixeon.healthcare.model.Transactionable;
import com.pixeon.healthcare.repository.BudgetRepository;
import com.pixeon.healthcare.repository.TransactionRepository;

@Service
@Validated
public class BudgetService {

	@Autowired
	private BudgetRepository budgetRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Value("${default.amount.coins}")
	private int defaultAmountCoins;
	
	@Transactional
	public Budget create(@Valid Budgetable budgetable) {
		return save(new Budget(budgetable, defaultAmountCoins));
	}
	
	@Transactional
	public Budget toDebit(@Valid Transactionable transactionable, int quantity) throws BusinessException {
		if (quantity < 1) {
			throw new BusinessException("Quantity must be greater than zero");
		}
		Budget budget = budgetRepository.findByOrignKey(transactionable.getBudgetKey());
		if (budget == null) {
			throw new BusinessException("Budget not found");
		}
		if (!budget.hasBalance()) {
			throw new BusinessException("Your budget has no balance to make the transaction");
		}
		save(new Transaction(budget, TransactionType.DEBIT, transactionable, quantity));
		budget.setBalance(budget.getBalance() - quantity);
		return budgetRepository.save(budget);
	}
	
	public boolean existsDebitWithProperty(Transactionable transactionable, String propertyKey, String propertyValue) {
		return transactionRepository.countByOrignKeyAndTransactionTypeAndProperties_keyAndProperties_Value(transactionable.getKey(), TransactionType.DEBIT, propertyKey, propertyValue) > 0;
	}
	
	private Budget save(Budget budget) {
		return budgetRepository.save((Budget) prepareEntity(budget));
	}
	
	private Transaction save(Transaction transaction) {
		return transactionRepository.save((Transaction) prepareEntity(transaction));
	}
	
	private DefaultEntity prepareEntity(DefaultEntity entity) {
		Date now = new Date();
		if (entity.getId() == null || entity.getDtCreated() == null) {
			entity.setDtCreated(now);
		}
		entity.setDtUpdated(now);
		return entity;
	}

}
