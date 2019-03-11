package com.nath.codeworks.controller;

import java.util.List;

import com.nath.codeworks.exception.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nath.codeworks.exception.BackBaseRestException;
import com.nath.codeworks.model.backbase.BbTransaction;
import com.nath.codeworks.model.backbase.BbTransactionsAmount;
import com.nath.codeworks.service.BbTransactionService;

/**
 * @author ghtvnath
 * Controller class for getting Transactions. 
 */
@Controller
@RequestMapping("/transactions")
public class BbTransactionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BbTransactionController.class);

    @Autowired
    BbTransactionService bbTransactionService;

    /**
     * <p>Get list of all transactions</p>
     * 
     * @return List of BbTransaction
     * @throws BackBaseRestException
     */
    @ResponseBody
    @GetMapping
    public List<BbTransaction> getBbTransactions() throws BackBaseRestException {
        LOGGER.debug("Getting all transactions detials");
        List<BbTransaction> bbTransactions = bbTransactionService.getListOfTransactions();
        if (CollectionUtils.isEmpty(bbTransactions)) {
            throw new BackBaseRestException(ErrorCode.NO_DATA, "Requested data not found");
        }
        return bbTransactions;
    }

    /**
     * <p>Get list of all transactions filtered by type</p>
     * 
     * @param type (String)
     * @return list of BbTransaction filtered by type
     * @throws BackBaseRestException
     */
    @ResponseBody
    @GetMapping("/type/{type}")
    public List<BbTransaction> getBbTransactionsByType(@PathVariable String type) throws BackBaseRestException {
        LOGGER.debug("Getting transactions detials for type {}", type);
        List<BbTransaction> bbTransactions =  bbTransactionService.getListOfTransactionsByType(type);
        if (CollectionUtils.isEmpty(bbTransactions)) {
            throw new BackBaseRestException(ErrorCode.NO_DATA, "Requested data not found");
        }
        return bbTransactions;
    }

    /**
     * <p>Get total amount for all transactions for given type</p>
     * 
     * @param type (String)
     * @return BbTransactionAmount which contains the type and the total of for the transaction type
     * @throws BackBaseRestException
     */
    @ResponseBody
    @GetMapping("/type/{type}/total")
    public BbTransactionsAmount getBbTransactionsTotalByType(@PathVariable String type) throws BackBaseRestException {
        LOGGER.debug("Getting transactions amount total for type {}", type);
        return bbTransactionService.getTransactionsAmountTotalByType(type);
    }

}
