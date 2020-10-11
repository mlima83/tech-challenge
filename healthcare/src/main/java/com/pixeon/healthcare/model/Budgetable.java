package com.pixeon.healthcare.model;

import javax.validation.constraints.NotEmpty;

public interface Budgetable {
	
	@NotEmpty
	public String getKey();

}
