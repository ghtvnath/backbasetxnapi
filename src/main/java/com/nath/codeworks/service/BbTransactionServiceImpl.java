package com.nath.codeworks.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nath.codeworks.config.AppConfig;
import com.nath.codeworks.exception.BackBaseRestException;
import com.nath.codeworks.exception.ErrorCode;
import com.nath.codeworks.model.backbase.BbTransaction;
import com.nath.codeworks.model.backbase.BbTransactionsAmount;
import com.nath.codeworks.util.OpenBankUtil;

/**
 * @author ghtvnath
 * 
 * The service class for getting Transaction Details. This will call RestClient service 
 * to get data from Openbank APIs, and format the data to the format which the Backbase Rest API
 * clients are served.
 */
@Service
public class BbTransactionServiceImpl implements BbTransactionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BbTransactionServiceImpl.class);

	private AppConfig appConfig;
	
	private RestClient restClient;
	
	@Autowired
	public BbTransactionServiceImpl(AppConfig appConfig, RestClient restClient) {
		this.appConfig = appConfig;
		this.restClient = restClient;
	}

	@Override
	public List<BbTransaction> getListOfTransactions() throws BackBaseRestException {
		LOGGER.debug("Getting all transactions details");
		if (StringUtils.isBlank(appConfig.getOpenBankTransactionApiUrl())) {
			throw new BackBaseRestException(ErrorCode.SYSTEM, "Error in configurations.");
		}
		String jsonResponse = "";
		
		jsonResponse = restClient.getForObject(appConfig.getOpenBankTransactionApiUrl(), String.class);
		return OpenBankUtil.getBbTransactionFromOpenBankJson(jsonResponse);

	}

	@Override
	public List<BbTransaction> getListOfTransactionsByType(final String type) throws BackBaseRestException {
		LOGGER.debug("Getting all transactions details for type {}", type);
		List<BbTransaction> listOfAllTransactions = getListOfTransactions();
		List<BbTransaction> resultList = listOfAllTransactions.stream()
				.filter(txn -> txn.getTransactionType().equalsIgnoreCase(type)).collect(Collectors.toList());

		return resultList;
	}

	@Override
	public BbTransactionsAmount getTransactionsAmountTotalByType(String type) throws BackBaseRestException {
		LOGGER.debug("Getting transactions amount total for transactions of type {}", type);
		List<BbTransaction> listOfAllTransactions = getListOfTransactions();
		Double total = listOfAllTransactions.stream().filter(txn -> txn.getTransactionType().equalsIgnoreCase(type))
				.mapToDouble(txn -> txn.getTransactionAmount()).sum();
		BbTransactionsAmount txnAmount = new BbTransactionsAmount(type, total);
		return txnAmount;
	}

}
