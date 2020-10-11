package com.pixeon.healthcare.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Lazy;

import com.pixeon.base.model.DefaultEntity;

@Entity
public class Transaction extends DefaultEntity {
	
	@ManyToOne
	private Budget budget;

	@NotEmpty
	private String orignKey;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;
	
	@Min(1)
	private int quantity;
	
	@Lazy
	@OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
	private List<CustomProperty> properties;
	
	public Transaction() {}
	
	public Transaction(Budget budget, TransactionType transactionType, Transactionable transactionable, int quantity) {
		this.budget = budget;
		this.transactionType = transactionType;
		this.orignKey = transactionable.getKey();
		this.quantity = quantity;
		if (transactionable.getProperties() != null && !transactionable.getProperties().isEmpty()) {
			this.setProperties(new ArrayList<CustomProperty>());
			transactionable.getProperties().forEach((key, value) -> {
				getProperties().add(new CustomProperty(this, key, value));
			});
		}
	}

	public Budget getBudget() {
		return budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}

	public String getOrignKey() {
		return orignKey;
	}

	public void setOrignKey(String orignKey) {
		this.orignKey = orignKey;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public List<CustomProperty> getProperties() {
		return properties;
	}

	public void setProperties(List<CustomProperty> properties) {
		this.properties = properties;
	}


}
