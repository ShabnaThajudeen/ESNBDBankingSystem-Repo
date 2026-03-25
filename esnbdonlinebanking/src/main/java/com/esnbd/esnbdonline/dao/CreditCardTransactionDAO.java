package com.esnbd.esnbdonline.dao;

import java.util.List;

import com.esnbd.esnbdonline.dto.CreditCard;

public interface CreditCardTransactionDAO {
	List<CreditCard> getCreditCardDetails(String name, String creditCardNo, String cvv);

	String processTranaction(CreditCard card);	
}
