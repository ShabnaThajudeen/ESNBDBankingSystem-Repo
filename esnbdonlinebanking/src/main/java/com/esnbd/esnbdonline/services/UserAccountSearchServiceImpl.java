package com.esnbd.esnbdonline.services;

import java.util.ArrayList;
import java.util.List;

import com.esnbd.esnbdonline.dao.UserAccountSearchDAO;
import com.esnbd.esnbdonline.dto.Account;
import com.esnbd.esnbdonline.dto.AccountHolderDetails;

public class UserAccountSearchServiceImpl implements UserAccountSearchService{
	private UserAccountSearchDAO userAccSearchDAO;

	public void setUserAccSearchDAO(UserAccountSearchDAO userAccSearchDAO) {
		this.userAccSearchDAO = userAccSearchDAO;
	}
	
	@Override
	public List<Account> getUserAccounts(String userName){
		List<Account> accounts = new ArrayList<Account>();	
		List<AccountHolderDetails> holders =  new ArrayList<AccountHolderDetails>();		
		
		holders = userAccSearchDAO.getAccountHolderDetails(userName);		
		for(AccountHolderDetails holder: holders) {
			/*
			 * System.out.println(); System.out.println("User details fetched: " + holder);
			 */
			accounts = holder.getAccount();	
			/*
			 * System.out.println(holder.getName() + "'s accounts: " + accounts);
			 * System.out.println();
			 */
		}		
		return accounts;		
	}
}
