package com.nath.codeworks.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.nath.codeworks.exception.BackBaseRestException;
import com.nath.codeworks.exception.ErrorCode;
import com.nath.codeworks.model.backbase.BbTransaction;

/**
 * @author ghtvnath
 *
 * This Util class is responsible in formatting OpenBank API response to the format 
 * that is served to Backbase API clients.
 */
public class OpenBankUtil {
	
	private static final String TRANSACTIONS = "transactions";
	private static final String ID = "id";
	private static final String NAME = "name";
	private static final String NUMBER = "number";
	private static final String HOLDER = "holder";
	private static final String METADATA = "metadata";
	private static final String IMAGE_URL = "image_URL";
	private static final String AMOUNT = "amount";
	private static final String CURRENCY = "currency";
	private static final String TYPE = "type";
	private static final String DESC = "description";
	private static final String THIS_ACCOUNT = "this_account";
	private static final String OTHER_ACCOUNT = "other_account";
	private static final String DETAILS = "details";
	private static final String VALUE = "value";
	
	
	public static List<BbTransaction> getBbTransactionFromOpenBankJson (String jsonStr) throws BackBaseRestException {
		try {
			JSONObject jsonObject = new JSONObject(jsonStr);
	        JSONArray jsonArray = jsonObject.getJSONArray(TRANSACTIONS);
	        List<BbTransaction> bbTransactionsList = new ArrayList<>();
	        for (int x=0; x<jsonArray.length(); x++) {
	        	bbTransactionsList.add(mapJsonToBbTransaction(jsonArray.getJSONObject(x)));
	        }
	        return bbTransactionsList;
		} catch (Exception e) {
			throw new BackBaseRestException(ErrorCode.CONNECTIVITY, "Error parsing data from supporting services");
		}
		
	}
	
	private static BbTransaction mapJsonToBbTransaction (JSONObject jsonObject) {
    	BbTransaction bbTransaction = new BbTransaction();
    	bbTransaction.setId(jsonObject.optString(ID));
    	bbTransaction.setAccountId(jsonObject.getJSONObject(THIS_ACCOUNT).optString(ID));
    	bbTransaction.setCounterPartyAccount(jsonObject.getJSONObject(OTHER_ACCOUNT)
    			.optString(NUMBER));
    	bbTransaction.setCounterPartyName(jsonObject.getJSONObject(OTHER_ACCOUNT)
    			.getJSONObject(HOLDER).optString(NAME));
    	bbTransaction.setCounterPartyLogoPath(jsonObject.getJSONObject(OTHER_ACCOUNT)
    			.getJSONObject(METADATA).optString(IMAGE_URL));
    	bbTransaction.setInstructedAmount(jsonObject.getJSONObject(DETAILS)
    			.getJSONObject(VALUE).optDouble(AMOUNT));
    	bbTransaction.setInstructedCurrency(jsonObject.getJSONObject(DETAILS)
    			.getJSONObject(VALUE).optString(CURRENCY));
    	bbTransaction.setTransactionAmount(jsonObject.getJSONObject(DETAILS)
    			.getJSONObject(VALUE).optDouble(AMOUNT));
    	bbTransaction.setTransactionCurrency(jsonObject.getJSONObject(DETAILS)
    			.getJSONObject(VALUE).optString(CURRENCY));
    	bbTransaction.setTransactionType(jsonObject.getJSONObject(DETAILS).optString(TYPE));
    	bbTransaction.setDescription(jsonObject.getJSONObject(DETAILS).optString(DESC));
    	return bbTransaction;
    }
	

}
