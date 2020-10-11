package com.pixeon.healthcare.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.pixeon.base.model.DefaultEntity;

@Entity
public class CustomProperty extends DefaultEntity {
	
	@NotNull
	@ManyToOne
	private Transaction transaction;

	@NotEmpty
	private String key;
	
	@NotEmpty
	private String value;
	
	public CustomProperty() {}
	
	public CustomProperty(Transaction transaction, String key, String value) {
		this.transaction = transaction;
		this.key = key;
		this.value = value;
	}


}
