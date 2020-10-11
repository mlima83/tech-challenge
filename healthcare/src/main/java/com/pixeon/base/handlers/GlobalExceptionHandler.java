package com.pixeon.base.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pixeon.base.dto.ErrorItemDtoV1;
import com.pixeon.base.dto.ResponseErrorDtoV1;
import com.pixeon.base.exception.BusinessException;

@ControllerAdvice
public class GlobalExceptionHandler {

    final static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @SuppressWarnings("rawtypes")
	@ExceptionHandler(BusinessException.class) 
    public ResponseEntity handleBusinessException(HttpServletRequest request, BusinessException ex){
        return ResponseEntity.badRequest().body(new ResponseErrorDtoV1(ex)); 
    }
    
    @SuppressWarnings("rawtypes")
	@ExceptionHandler(javax.validation.ConstraintViolationException.class) 
    public ResponseEntity handleConstraintViolationException(HttpServletRequest request, javax.validation.ConstraintViolationException ex){
        return ResponseEntity.badRequest().body(new ResponseErrorDtoV1(new BusinessException(ex.getMessage(), ex))); 
    }
    
    @SuppressWarnings("rawtypes")
	@ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class) 
    public ResponseEntity handleConstraintViolationException(HttpServletRequest request, org.springframework.dao.DataIntegrityViolationException ex){
        return ResponseEntity.badRequest().body(new ResponseErrorDtoV1(new BusinessException(ex.getMessage(), ex))); 
    }
    
    @SuppressWarnings("rawtypes")
	@ExceptionHandler(org.hibernate.StaleObjectStateException.class) 
    public ResponseEntity handleConstraintViolationException(HttpServletRequest request, org.hibernate.StaleObjectStateException ex){
        return ResponseEntity.badRequest().body(new ResponseErrorDtoV1(new BusinessException(ex.getMessage(), ex))); 
    }
    
    @SuppressWarnings("rawtypes")
	@ExceptionHandler(IllegalArgumentException.class) 
    public ResponseEntity handleIllegalArgumentException(HttpServletRequest request, IllegalArgumentException ex){
        return ResponseEntity.badRequest().body(new ResponseErrorDtoV1(new BusinessException(ex.getMessage(), ex))); 
    }
    
    @SuppressWarnings("rawtypes")
	@ExceptionHandler(MethodArgumentNotValidException.class) 
    public ResponseEntity handleIllegalArgumentException(HttpServletRequest request, MethodArgumentNotValidException ex){
    	List<ErrorItemDtoV1> errors = new ArrayList<ErrorItemDtoV1>();
    	for (ObjectError error : ex.getBindingResult().getAllErrors())  {
    		String msg = error.getDefaultMessage();
    		String field = "";
    		for (Object def :error.getArguments()) {
    			DefaultMessageSourceResolvable d = (DefaultMessageSourceResolvable) def;
    			field = d.getDefaultMessage();
    			break;
    		}
    		errors.add(new ErrorItemDtoV1(field, msg));
    	}
        return ResponseEntity.badRequest().body(new ResponseErrorDtoV1(new BusinessException(errors))); 
    }



}
