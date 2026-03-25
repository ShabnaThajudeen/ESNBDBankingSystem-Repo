package com.esnbd.esnbdonline.services;

import java.util.List;

import com.esnbd.esnbdonline.dto.AccountHolderDetails;

public interface CreditCardSearchService {

	String getCreditCardNo(String userName);

	String getCreditCard(List<AccountHolderDetails> holders);	
}
