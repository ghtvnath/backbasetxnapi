package com.nath.codeworks.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.nath.codeworks.exception.BackBaseRestException;
import com.nath.codeworks.exception.ErrorCode;
import com.nath.codeworks.model.backbase.BbError;

@RunWith(MockitoJUnitRunner.class)
public class ResponseErrorHandlerTest {

	ResponseErrorHandler responseErrorHandler;

	@Before
	public void setUp() throws Exception {
		responseErrorHandler = new ResponseErrorHandler();
	}

	@Test
	public void handleSystemErrorTest() {
		ResponseEntity<BbError> responseEntity = responseErrorHandler
				.handleBackBaseRestEx(new BackBaseRestException(ErrorCode.SYSTEM, "System Error"));
		Assert.assertEquals(500, responseEntity.getStatusCode().value());
	}
	
	@Test
	public void handleNoDataTest() {
		ResponseEntity<BbError> responseEntity = responseErrorHandler
				.handleBackBaseRestEx(new BackBaseRestException(ErrorCode.NO_DATA, "No data"));
		Assert.assertEquals(204, responseEntity.getStatusCode().value());
	}

}
