package com.nath.codeworks.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nath.codeworks.exception.BackBaseRestException;
import com.nath.codeworks.exception.ErrorCode;

/**
 * @author ghtvnath
 * 
 * Single responsibility of using RestTemplate to call any API and receive response. 
 * Implemented in generic format so that client can serve any service expecting 
 * any data format through any Url. 
 */
@Service
public class RestClient {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RestClient.class);
	
	private static final RestTemplate restTemplate = new RestTemplate();
	
	
	public <T> T getForObject(String url, Class<T> responseType, Object... urlVariables) throws BackBaseRestException {
		try {
			return restTemplate.getForObject(url, responseType);
		} catch (Exception e) {
			LOGGER.error("Error occurred in connecting to {}", url, e);
			throw new BackBaseRestException(ErrorCode.CONNECTIVITY, "Error in connecting to supporting services");
		}
	}

}
