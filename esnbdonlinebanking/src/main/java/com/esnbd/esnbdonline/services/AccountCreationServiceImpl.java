package com.esnbd.esnbdonline.services;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.esnbd.esnbdonline.appconstants.AppConstants;
import com.esnbd.esnbdonline.dao.AccountCreationDAO;
import com.esnbd.esnbdonline.dto.Account;
import com.esnbd.esnbdonline.dto.AccountHolderDetails;
import com.esnbd.esnbdonline.dto.AccountTransactions;
import com.esnbd.esnbdonline.dto.Address;
import com.esnbd.esnbdonline.dto.CurrentAccount;
import com.esnbd.esnbdonline.dto.SBAccount;
import com.esnbd.esnbdonline.dto.User;

@Transactional
public class AccountCreationServiceImpl implements AccountCreationService{
	private AccountCreationDAO accCreationDAO;		

	public void setAccCreationDAO(AccountCreationDAO accCreationDAO) {
		this.accCreationDAO = accCreationDAO;
	}

	@Override	
	public String saveAccountHolderDetails(String name, String dob, String addr, String mailId, String accType) {
		String message = "";
		/**
		 * An account Holder can have a SBAccount and a CurrentAccount
		 * Check if the user details already exist, 
		 * if user details not exist then proceed with the account creation
		 * if exist, see if the type of account trying to create exist
		 * if the type of account doesn't exist proceed with account creation
		 */
		
		//------ Checking if an existing account holder --------------------------------------
		List<AccountHolderDetails> holders =  new ArrayList<AccountHolderDetails>();
		try {
			holders = accCreationDAO.checkExistingAccountHolder(name, dob);
			
			//------ Processing account holder based on the query result --------------------------------------
			message = this.processAccountHolderDetails(holders, name, dob, addr, mailId, accType);	
		} catch (DataAccessException e) {	
			message = AppConstants.ACCOUNT_CREATION_FAILED;
			e.printStackTrace();
		}		
		return message;
	}	
	
	@Override
	public AccountHolderDetails setAccountHolderDetails(String name, String dob, String addr, String mailId, String accType) {
		//------ Account Holder Reference --------------------------------------
		AccountHolderDetails accountHolder = new AccountHolderDetails();
				
		//------ Setting the address details for accountHolder -----------------
		Address address = this.setAddressDetails(addr);
		accountHolder.setAddress(address);
				
		//------ Setting the account list details for accountHolder ------------
		List<Account> accounts = this.setAccountListDetails(accType, accountHolder);
		accountHolder.setAccount(accounts);
				
		//------ Setting name, dob, mailId, credit card details for accountHolder ------------
		accountHolder.setName(name);
		accountHolder.setDob(dob);
		accountHolder.setMailId(mailId);
		accountHolder.setCreditCard(null);
		
		//------ Setting User details - userName and password for accountHolder ------------
		User user = this.processUserDetails(name, dob, addr);
		accountHolder.setUser(user);
		
		return accountHolder;
	}
	
	@Override
	public Address setAddressDetails(String address) {
		return new Address(address);
	}
	
	@Override
	public List<Account> setAccountListDetails(String accType, AccountHolderDetails holder){
		List<Account> accounts = new ArrayList<Account>();
		
		Account acc = this.setBankAccount(accType, holder);
		
		accounts.add(acc);
		
		return accounts;
	}
	
	@Override
	public Account setBankAccount(String accType, AccountHolderDetails holder) {
		Account acc;
		
		if(accType.equals(AppConstants.ACCOUNT_TYPE_SB)) {			
			acc = new SBAccount();				
			
			//------ Setting the SBAccount details -----------------
			acc.setAccountType(AppConstants.ACCOUNT_TYPE_SB);
			acc.setHolderDetails(holder);
			acc.setTransactions(this.getAccountTransactionDetails(acc));			
		}
		else {
			acc = new CurrentAccount();	
			
			//------ Setting the CurrentAccount details -----------------
			acc.setAccountType(AppConstants.ACCOUNT_TYPE_CURRENT);
			acc.setHolderDetails(holder);
			acc.setTransactions(this.getAccountTransactionDetails(acc));				
		}
		
		return acc;
	}
	
	@Override
	public Set<AccountTransactions> getAccountTransactionDetails(Account acc) {
		Set<AccountTransactions> transactions = new LinkedHashSet<AccountTransactions>();
		
		AccountTransactions transaction = new AccountTransactions();
		
		//------ Setting the current date for transaction ------------
		transaction.setDate(DateService.getCurrentDate());
		
		//------ Setting the description for transaction ------------
		transaction.setDescription(AppConstants.TRANSACTION_DESC_INITIAL_DEPOSIT);
		
		//------ Setting the cheque no for transaction ------------
		transaction.setChequeNo(null);
		
		//------ Setting the withdrawal amount for transaction ------------
	    transaction.setWithdraw(null);
	    
	    //------ Setting the deposit amount and account balance for transaction ------------
	    if(acc.getAccountType().equalsIgnoreCase(AppConstants.ACCOUNT_TYPE_CURRENT)) {
	    	transaction.setDeposit(AppConstants.CURRENT_ACCOUNT_MIN_BALANCE);
	    	transaction.setAccBalance(AppConstants.CURRENT_ACCOUNT_MIN_BALANCE);
	    }
	    else {
	    	transaction.setDeposit(AppConstants.SAVINGS_ACCOUNT_MIN_BALANCE);
	    	transaction.setAccBalance(AppConstants.SAVINGS_ACCOUNT_MIN_BALANCE);
	    }
	    
	    //------ Setting the account reference for transaction ------------
	    transaction.setAccount(acc);
	    
	    transactions.add(transaction);		
		
		return transactions;
	}
	
	@Override
	public User processUserDetails(String name, String dob, String addr) {
		User user = new User();		
		
		String userName = "";
		String pass = "";
		
		//------ generating userName for User ------------
		StringTokenizer tokenUserName = new StringTokenizer(name);		
		while(tokenUserName.hasMoreTokens()) {
			userName = userName + tokenUserName.nextToken();
		}
		
		StringTokenizer tokenDOB = new StringTokenizer(dob);
		String dateOfBirth = "";
		while(tokenDOB.hasMoreTokens()) {
			dateOfBirth = dateOfBirth + tokenDOB.nextToken("-");
		}
		
		userName = userName.concat(dateOfBirth.substring(0, 4));
		
		String str = addr.replaceAll("\\s", "");
		
		//------ generating password for User ------------
		pass = "U"+ str.substring(0, 4) + "@" + dob.substring(1, 4);
		
		user.setUserName(userName);
		user.setPassword(pass);
		
		return user;
	}
	
	@Override
	public String processAccountHolderDetails(List<AccountHolderDetails> holders, String name, String dob, String addr, String mailId, String accType) {
		String message ="";
		
		AccountHolderDetails accHolder = new AccountHolderDetails();
		
		if(holders.size() == 0) {	
			accHolder = this.setAccountHolderDetails(name, dob, addr, mailId, accType);
			
			message = accCreationDAO.createAccountHolder(accHolder);		
		}
		else {
			for(AccountHolderDetails holder: holders) {	
				if(holder.getAccount() == null) {
					List<Account> accounts = new ArrayList<Account>();
					
					Account acc = this.setBankAccount(accType, holder);
					accounts.add(acc);
					
					holder.setAccount(accounts);
					
					message = accCreationDAO.updateAccountHolder(holder);
				}
				else {
					List<String> typeOfAcc = new ArrayList<String>();
					
					List<Account> accounts = holder.getAccount();					
					for(int i = 0; i < accounts.size(); i++) {
						typeOfAcc.add(accounts.get(i).getAccountType());						
					}	
					if(typeOfAcc.contains(AppConstants.ACCOUNT_TYPE_SB) && typeOfAcc.contains(AppConstants.ACCOUNT_TYPE_CURRENT)) {
						message = accType + " " + AppConstants.ACCOUNT_EXIST;
					}
					else if(typeOfAcc.contains(accType)){
						message = accType + " " + AppConstants.ACCOUNT_EXIST;
					}
					else {
						Account acc = this.setBankAccount(accType, holder);
						accounts.add(acc);
						holder.setAccount(accounts);
						
						message = accCreationDAO.updateAccountHolder(holder);									
					}//else	
				}
											
			}//for				
		}//else		
		return message;
	}
}
