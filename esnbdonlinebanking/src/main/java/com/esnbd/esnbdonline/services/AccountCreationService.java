package com.esnbd.esnbdonline.services;

import java.util.List;
import java.util.Set;

import com.esnbd.esnbdonline.dto.Account;
import com.esnbd.esnbdonline.dto.AccountHolderDetails;
import com.esnbd.esnbdonline.dto.AccountTransactions;
import com.esnbd.esnbdonline.dto.Address;
import com.esnbd.esnbdonline.dto.User;

public interface AccountCreationService {
	String saveAccountHolderDetails(String name, String dob, String addr, String mailId, String accType);

	String processAccountHolderDetails(List<AccountHolderDetails> holders, String name, String dob, String addr,
			String mailId, String accType);

	AccountHolderDetails setAccountHolderDetails(String name, String dob, String addr, String mailId, String accType);

	Address setAddressDetails(String address);

	List<Account> setAccountListDetails(String accType, AccountHolderDetails holder);

	Account setBankAccount(String accType, AccountHolderDetails holder);

	Set<AccountTransactions> getAccountTransactionDetails(Account acc);

	User processUserDetails(String name, String dob, String addr);
}
