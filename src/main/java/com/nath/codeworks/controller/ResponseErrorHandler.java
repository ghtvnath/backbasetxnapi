package com.nath.codeworks.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nath.codeworks.exception.BackBaseRestException;
import com.nath.codeworks.model.backbase.BbError;

/**
 * @author ghtvnath
 * 
 * Handle Exceptions that are thrown by Spring MVC Controller classes. 
 */
@ControllerAdvice
public class ResponseErrorHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResponseErrorHandler.class);
		
	@ExceptionHandler(BackBaseRestException.class)
    protected ResponseEntity<BbError> handleBackBaseRestEx(BackBaseRestException ex){
		LOGGER.warn("Handling BackBaseRestException", ex);
		BbError bbError = new BbError(ex.getErrorCode().toString(), ex.getMessage());

		ResponseEntity<BbError> responseEntity = null;
		switch (ex.getErrorCode()){
			case NO_DATA:
				responseEntity = new ResponseEntity<>(bbError, HttpStatus.NO_CONTENT);
				break;
			default:
				responseEntity = new ResponseEntity<>(bbError, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
    }

}
