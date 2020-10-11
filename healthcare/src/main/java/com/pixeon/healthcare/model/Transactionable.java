package com.pixeon.healthcare.model;

import java.util.Map;

import javax.validation.constraints.NotEmpty;

public interface Transactionable {
	
	@NotEmpty
	public String getKey();
	
	@NotEmpty
	public String getBudgetKey();
	
	public Map<String, String> getProperties();


}
