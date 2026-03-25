package com.esnbd.esnbdonline.dao;

import java.util.List;

import com.esnbd.esnbdonline.dto.CreditCard;
import com.esnbd.esnbdonline.dto.CreditCardTransactions;

public interface CreditCardStatementDisplayDAO {	

	List<CreditCard> getCreditCards(String ccNo);

	List<CreditCardTransactions> getCreditStatement(CreditCard c, String fromDate, String toDate);	
}
