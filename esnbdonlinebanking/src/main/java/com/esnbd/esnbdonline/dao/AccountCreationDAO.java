package com.esnbd.esnbdonline.dao;

import java.util.List;

import com.esnbd.esnbdonline.dto.AccountHolderDetails;

public interface AccountCreationDAO {
	List<AccountHolderDetails> checkExistingAccountHolder(String name, String dob);
	String createAccountHolder(AccountHolderDetails holder);	
	String updateAccountHolder(AccountHolderDetails holder);	
}
