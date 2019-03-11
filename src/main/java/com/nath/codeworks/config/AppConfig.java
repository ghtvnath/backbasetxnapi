package com.nath.codeworks.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author ghtvnath
 * This configuration class will hold all the properties and configurations.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.nath.codeworks")
@PropertySource(value= {"classpath:application.properties"})
public class AppConfig {

	@Value("${openbank.transactions.api.url}") 
	private String openBankTransactionApiUrl;

	public String getOpenBankTransactionApiUrl() {
		return "https://apisandbox.openbankproject.com/obp/v1.2.1/banks/rbs/accounts/savings-kids-john/public/transactions";
	}


}
