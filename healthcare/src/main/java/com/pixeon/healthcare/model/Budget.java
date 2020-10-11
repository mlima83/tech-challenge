package com.pixeon.healthcare.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.pixeon.base.model.DefaultEntity;

@Entity
public class Budget extends DefaultEntity {

	@NotEmpty
	@Column(unique = true)
	private String orignKey;
	
	@Min(0)
	private int balance;
	
	public Budget() {}
	
	public Budget(Budgetable budgetable, int defaultBalance) {
		this.orignKey = budgetable.getKey();
		this.balance = defaultBalance;
	}

	public String getOrignKey() {
		return orignKey;
	}

	public void setOrignKey(String orignKey) {
		this.orignKey = orignKey;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public boolean hasBalance() {
		return this.balance > 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orignKey == null) ? 0 : orignKey.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Budget other = (Budget) obj;
		if (orignKey == null) {
			if (other.orignKey != null)
				return false;
		} else if (!orignKey.equals(other.orignKey))
			return false;
		return true;
	}


}
