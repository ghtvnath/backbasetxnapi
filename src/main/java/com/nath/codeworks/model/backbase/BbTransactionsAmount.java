package com.nath.codeworks.model.backbase;

/**
 * @author ghtvnath
 * 
 * Format in which Transaction Amount for a transaction type will be responded with
 */
public class BbTransactionsAmount {
	
	private String transactionType;
	private Double transactionsAmountTotal;
	
	public BbTransactionsAmount(String transactionType, Double transactionsAmountTotal) {
		this.transactionType = transactionType;
		this.transactionsAmountTotal = transactionsAmountTotal;
	}
	
	public String getTransactionType() {
		return transactionType;
	}
	public Double getTransactionsAmountTotal() {
		return transactionsAmountTotal;
	}

}
