package com.pixeon.base.dto;

import java.util.List;

import com.pixeon.base.exception.BusinessException;

public class ResponseErrorDtoV1 {
	
	public ResponseErrorDtoV1() {}
	
	public ResponseErrorDtoV1(BusinessException businessException) {
		this.errors = businessException.getErrors();
	}
	
	private List<ErrorItemDtoV1> errors;

	public List<ErrorItemDtoV1> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorItemDtoV1> errors) {
		this.errors = errors;
	}

}
