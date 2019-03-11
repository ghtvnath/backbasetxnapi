package com.nath.codeworks.controller;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.nath.codeworks.exception.BackBaseRestException;
import com.nath.codeworks.model.backbase.BbTransaction;
import com.nath.codeworks.model.backbase.BbTransactionsAmount;
import com.nath.codeworks.service.BbTransactionService;

@RunWith(MockitoJUnitRunner.class)
public class BbTransactionControllerTest {

	@Mock
	BbTransactionService bbTransactionService;

	@InjectMocks
	BbTransactionController bbTransactionController;

	@Test
	public void testGetBbTransactions() throws BackBaseRestException {
		when(bbTransactionService.getListOfTransactions()).thenReturn(getBbTransactions());
		List<BbTransaction> bbTransactions = bbTransactionController.getBbTransactions();
		Assert.assertNotNull(bbTransactions);
		Assert.assertEquals(2, bbTransactions.size());
	}

	@Test(expected = BackBaseRestException.class)
	public void testGetBbTransactionsNoData() throws BackBaseRestException {
		bbTransactionController.getBbTransactions();
	}

	@Test
	public void testGetBbTransactionsByType() throws BackBaseRestException {
		when(bbTransactionService.getListOfTransactionsByType(anyString())).thenReturn(getBbTransactions());
		List<BbTransaction> bbTransactions = bbTransactionController.getBbTransactionsByType("TXN");
		Assert.assertNotNull(bbTransactions);
		Assert.assertEquals(2, bbTransactions.size());
	}

	@Test(expected = BackBaseRestException.class)
	public void testGetBbTransactionsByTypeNoData() throws BackBaseRestException {
		bbTransactionController.getBbTransactionsByType("TXN");
	}
	
	@Test
	public void testGetBbTransactionsTotalByType() throws BackBaseRestException {
		when(bbTransactionService.getTransactionsAmountTotalByType(anyString())).thenReturn(new BbTransactionsAmount("TXN", 120.00));
		BbTransactionsAmount amount = bbTransactionController.getBbTransactionsTotalByType("TXN");
		Assert.assertNotNull(amount);
		Assert.assertEquals(Double.valueOf(120.00), Double.valueOf(amount.getTransactionsAmountTotal()));
	}

	private List<BbTransaction> getBbTransactions() {
		BbTransaction bbTransaction1 = new BbTransaction();
		BbTransaction bbTransaction2 = new BbTransaction();
		return Arrays.asList(bbTransaction1, bbTransaction2);
	}

}
