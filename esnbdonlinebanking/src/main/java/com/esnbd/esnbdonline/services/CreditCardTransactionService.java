package com.esnbd.esnbdonline.services;

import java.util.Set;

import com.esnbd.esnbdonline.dto.CreditCard;
import com.esnbd.esnbdonline.dto.CreditCardTransactions;

public interface CreditCardTransactionService {
	String processTransaction(String name, String creditCardNo, String cvv, String amount);

	Set<CreditCardTransactions> updateTransactionDetails(CreditCard card, Float amount);

	Set<CreditCardTransactions> creditLimitUpdateTransaction(CreditCard card);	
}
