package com.nath.codeworks.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.nath.codeworks.exception.ErrorCode;
import com.nath.codeworks.model.backbase.BbError;

/**
 * @author ghtvnath
 * 
 * This authentication entry point is used to modify custome authentication error 
 * response to follow the application format. A json response of BbError.
 */
@Component
public class ApiAuthEntryPoint extends BasicAuthenticationEntryPoint {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ApiAuthEntryPoint.class);

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authenticationException) throws IOException, ServletException {
		LOGGER.error("Authentication failed. Responding with authentication error");
		response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName());
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		BbError bbError = new BbError(ErrorCode.AUTHENTICATION.toString(), "Username and password of "
				+ "Basic Authentication failed.");
		response.setContentType("application/json");       
	    response.setCharacterEncoding("UTF-8");
	    JSONObject json = new JSONObject(bbError);

	    LOGGER.error(json.toString());
	    
		PrintWriter writer = response.getWriter();
		writer.println(json.toString());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		setRealmName("backbase-txn-api");
		super.afterPropertiesSet();
	}

}
