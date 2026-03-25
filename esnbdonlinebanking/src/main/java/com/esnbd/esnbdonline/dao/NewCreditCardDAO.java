package com.esnbd.esnbdonline.dao;

import java.util.List;

import com.esnbd.esnbdonline.dto.AccountHolderDetails;

public interface NewCreditCardDAO {	
	List<AccountHolderDetails> checkCreditCardExisting(String name, String dob);

	String issueNewCreditCard(AccountHolderDetails holder);
	
	String activateCreditCard(AccountHolderDetails holder);	
}
