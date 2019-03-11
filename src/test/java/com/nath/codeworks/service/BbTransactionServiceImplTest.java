package com.nath.codeworks.service;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.nath.codeworks.config.AppConfig;
import com.nath.codeworks.exception.BackBaseRestException;
import com.nath.codeworks.model.backbase.BbTransaction;
import com.nath.codeworks.model.backbase.BbTransactionsAmount;

@RunWith(MockitoJUnitRunner.class)
public class BbTransactionServiceImplTest {

	@Mock
	RestClient restClient;

	@Mock
	AppConfig appConfig;

	@InjectMocks
	BbTransactionServiceImpl bbTransactionServiceImpl;
	
	private static final String TEST_OPEN_SANDBOX_URL = "test/opensandbox/url";
	private static final String TXN_TYPE_SANDBOX_TAN = "SANDBOX_TAN";
	private static final String TXN_TYPE_SANDBOX_PAYMENT = "sandbox-payment";

	@Before
	public void setUp() throws Exception {

		when(appConfig.getOpenBankTransactionApiUrl()).thenReturn(TEST_OPEN_SANDBOX_URL);

		when(restClient.getForObject(eq(TEST_OPEN_SANDBOX_URL), eq(String.class))).thenReturn(getOpenSandboxSampleResponse());

	}

	@Test
	public void testGetListOfTransactions() throws BackBaseRestException {
		List<BbTransaction> bbTransactions = bbTransactionServiceImpl.getListOfTransactions();
		Assert.assertNotNull(bbTransactions);
		Assert.assertEquals(50, bbTransactions.size());
	}
	
	@Test
	public void testGetListOfTransactionsByType1() throws BackBaseRestException {
		List<BbTransaction> bbTransactions = bbTransactionServiceImpl.getListOfTransactionsByType(TXN_TYPE_SANDBOX_TAN);
		Assert.assertNotNull(bbTransactions);
		Assert.assertEquals(2, bbTransactions.size());
	}
	
	@Test
	public void testGetListOfTransactionsByType2() throws BackBaseRestException {
		List<BbTransaction> bbTransactions = bbTransactionServiceImpl.getListOfTransactionsByType(TXN_TYPE_SANDBOX_PAYMENT);
		Assert.assertNotNull(bbTransactions);
		Assert.assertEquals(19, bbTransactions.size());
	}
	
	@Test
	public void testGetTransactionsAmountTotalByType1() throws BackBaseRestException {
		BbTransactionsAmount txnAmount = bbTransactionServiceImpl.getTransactionsAmountTotalByType(TXN_TYPE_SANDBOX_TAN);
		Assert.assertEquals(Double.valueOf(10.00), txnAmount.getTransactionsAmountTotal());
	}
	
	@Test
	public void testGetTransactionsAmountTotalByType2() throws BackBaseRestException {
		BbTransactionsAmount txnAmount = bbTransactionServiceImpl.getTransactionsAmountTotalByType(TXN_TYPE_SANDBOX_PAYMENT);
		Assert.assertEquals(Double.valueOf(73.76), txnAmount.getTransactionsAmountTotal());
	}

	private String getOpenSandboxSampleResponse() throws URISyntaxException, IOException {
		Path path = Paths.get(getClass().getClassLoader().getResource("opensandbox-sample-api-response.txt").toURI());

		Stream<String> lines = Files.lines(path);
		String data = lines.collect(Collectors.joining("\n"));
		lines.close();
		
		if (StringUtils.isBlank(data)) {
			Assert.fail("Sample response is empty");
		}
		return data.trim();
	}

}
