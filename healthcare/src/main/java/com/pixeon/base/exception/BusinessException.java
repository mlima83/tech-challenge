package com.pixeon.base.exception;

import java.util.ArrayList;
import java.util.List;

import com.pixeon.base.dto.ErrorItemDtoV1;

public class BusinessException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private List<ErrorItemDtoV1> errors;

	public BusinessException() {
		super();
	}
	
	public BusinessException(String message) {
		super(message);
		addError("global", message);
	}
	
	public BusinessException(String key, String message) {
		super(message);
		addError(key, message);
	}
	
	public BusinessException(List<ErrorItemDtoV1> errors) {
		super();
		this.errors = errors;
	}
	
	public BusinessException(List<ErrorItemDtoV1> errors, Throwable throwable) {
		super(throwable);
		this.errors = errors;
	}
	
	public BusinessException(String key, String message, Throwable throwable) {
		super(message, throwable);
		addError(key, message);
	}
	
	public BusinessException(String message, Throwable throwable) {
		super(message, throwable);
		addError("global", message);
	}
	
	public void addError(String key, String message) {
		if (this.errors == null) {
			this.errors = new ArrayList<ErrorItemDtoV1>();
		}
		this.errors.add(new ErrorItemDtoV1(key, message));
	}
	
	public List<ErrorItemDtoV1> getErrors() {
		return this.errors;
	}

}
