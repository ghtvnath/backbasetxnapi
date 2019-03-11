package com.nath.codeworks.service;

import com.nath.codeworks.exception.BackBaseRestException;
import com.nath.codeworks.model.backbase.BbTransaction;
import com.nath.codeworks.model.backbase.BbTransactionsAmount;

import java.util.List;

public interface BbTransactionService {

	List<BbTransaction> getListOfTransactions() throws BackBaseRestException;

	List<BbTransaction> getListOfTransactionsByType(final String type) throws BackBaseRestException;

	BbTransactionsAmount getTransactionsAmountTotalByType(final String type) throws BackBaseRestException;

}
