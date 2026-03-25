package com.esnbd.esnbdonline.dao;

import java.util.List;

import com.esnbd.esnbdonline.dto.AccountHolderDetails;

public interface CreditCardSearchDAO {

	List<AccountHolderDetails> getCreditCardDetails(String userName);
	
}
