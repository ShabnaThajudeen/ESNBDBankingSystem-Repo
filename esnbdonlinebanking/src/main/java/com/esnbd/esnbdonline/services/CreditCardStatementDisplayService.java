package com.esnbd.esnbdonline.services;

import java.util.List;

import com.esnbd.esnbdonline.dto.CreditCard;
import com.esnbd.esnbdonline.dto.CreditCardTransactions;

public interface CreditCardStatementDisplayService {

	List<CreditCardTransactions> getStatement(String ccNo, String fromDate, String toDate);

	CreditCard getCreditCardId(List<CreditCard> cards);

	List<CreditCardTransactions> getCardTransactions(CreditCard c, String fromDate, String toDate);	
}
