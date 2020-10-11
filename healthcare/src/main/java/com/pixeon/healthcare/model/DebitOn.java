package com.pixeon.healthcare.model;

/**
 * A custom property of Exam for control to budget
 * @author marco
 *
 */
public enum DebitOn {
	
	CREATE, GET;
	
	public static final String PROPERTY_NAME = "DEBIT_ON";
	
	@Override
	public String toString() {
		return super.name();
	}
}
