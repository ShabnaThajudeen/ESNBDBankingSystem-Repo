package com.esnbd.esnbdonline.services;

import java.util.List;

import com.esnbd.esnbdonline.dto.AccountHolderDetails;
import com.esnbd.esnbdonline.dto.CreditCard;
import com.esnbd.esnbdonline.dto.User;

public interface NewCreditCardService {

	String issueNewCreditCard(String name, String dob, String addr, String mailId, String creditCardType);

	String processCreditCardDetails(List<AccountHolderDetails> holders, String name, String dob, String addr,
			String mailId, String creditCardType);

	AccountHolderDetails setAccountHolderDetails(String name, String dob, String addr, String mailId,
			String creditCardType);

	User processUserDetails(String name, String dob, String addr);

	CreditCard setCreditCardDetails(String creditCardType, String name);

	String generateCreditCardNo(String creditCardType);

	String generateCVV();	
}
