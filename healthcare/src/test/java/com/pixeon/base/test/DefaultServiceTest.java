package com.pixeon.base.test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.AfterClass;
import org.junit.BeforeClass; 

public abstract class DefaultServiceTest {
	
    private static ValidatorFactory validatorFactory;
    
    protected static Validator validator;
	
    @BeforeClass
    public static void initDefault() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();

    }
    
    @AfterClass
    public static void closeDefault() {
        validatorFactory.close();
    }

}
