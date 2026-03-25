package com.esnbd.esnbdonline.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import com.esnbd.esnbdonline.appconstants.AppConstants;
import com.esnbd.esnbdonline.dao.NewCreditCardDAO;
import com.esnbd.esnbdonline.dto.AccountHolderDetails;
import com.esnbd.esnbdonline.dto.Address;
import com.esnbd.esnbdonline.dto.CreditCard;
import com.esnbd.esnbdonline.dto.CreditCardType;
import com.esnbd.esnbdonline.dto.User;


public class NewCreditCardServiceImpl implements NewCreditCardService{	
	private NewCreditCardDAO ncCardDAO;	
	
	public void setNcCardDAO(NewCreditCardDAO ncCardDAO) {
		this.ncCardDAO = ncCardDAO;
	}

	@Override
	public String issueNewCreditCard(String name, String dob, String addr, String mailId, String creditCardType) {
		/**
		 * Check if credit card exist for the user with name and dob,
		 * if doesn't exist, create login credentials and issue credit card
		 * if credit card exist, then don't issue
		 * 
		 */
		String message ="";
		
		List<AccountHolderDetails> holders = new ArrayList<AccountHolderDetails>();
		try {
			holders = ncCardDAO.checkCreditCardExisting(name, dob);	
			message = this.processCreditCardDetails(holders, name, dob, addr, mailId, creditCardType);
		} catch (Exception e) {
			message = AppConstants.CCARD_CREATION_FAILED;
			e.printStackTrace();
		}		
		return message;
	}
	
	@Override
	public String processCreditCardDetails(List<AccountHolderDetails> holders, String name, String dob, String addr, String mailId, String creditCardType) {
		String message = "";
		
		//------ creating user account ------
		if(holders.size() == 0) {
			AccountHolderDetails holder = this.setAccountHolderDetails(name, dob, addr, mailId, creditCardType);
			message = ncCardDAO.issueNewCreditCard(holder);
		}
		else {
			CreditCard c;
			for(AccountHolderDetails holder: holders) {
				c = holder.getCreditCard();
				if(c != null) {
					message = AppConstants.CCARD_EXIST;					
				}
				else {
					CreditCard card = this.setCreditCardDetails(creditCardType, name);
					holder.setCreditCard(card);
					message = ncCardDAO.activateCreditCard(holder);
				}
			}
		}
		return message;
	}
	
	@Override
	public AccountHolderDetails setAccountHolderDetails(String name, String dob, String addr, String mailId, String creditCardType) {
		//------ Account Holder Reference --------------------------------------
		AccountHolderDetails accountHolder = new AccountHolderDetails();
		
		//------ Setting name, dob, mailId details for accountHolder ------------
		accountHolder.setName(name);
		accountHolder.setDob(dob);
		accountHolder.setMailId(mailId);
						
		//------ Setting the address details for accountHolder -----------------
		Address address = new Address(addr);
		accountHolder.setAddress(address);
						
		//------ Setting the account list details for accountHolder ------------		
		accountHolder.setAccount(null);		
				
		//------ Setting User details - userName and password for accountHolder ------------
		User user = this.processUserDetails(name, dob, addr);
		accountHolder.setUser(user);
		
		//------ Setting Credit Card details ------------
		accountHolder.setCreditCard(this.setCreditCardDetails(creditCardType, name));
				
		return accountHolder;		
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
	public CreditCard setCreditCardDetails(String creditCardType, String name) {
		CreditCard c =  new CreditCard();
		c.setCreditCardType(CreditCardType.valueOf(creditCardType));
		c.setCreditCardNo(this.generateCreditCardNo(creditCardType));
		c.setCvv(this.generateCVV());
		c.setValidFrom(DateService.getValidFromDateCreCard());
		c.setValidThru(DateService.getValidTillDateCreCard());
		c.setName(name);
		c.setTransactions(null);
		return c;
	}
	
	@Override
	public String generateCreditCardNo(String creditCardType) {
		String creditCardNo = "";
		if(creditCardType.equalsIgnoreCase("VISA")) {
			creditCardNo =  CreditCardType.VISA.generateNumber();
		}
		else if (creditCardType.equalsIgnoreCase("MASTER_CARD")) {
			creditCardNo =  CreditCardType.MASTER_CARD.generateNumber();
		}
        else if (creditCardType.equalsIgnoreCase("AMERICAN_EXPRESS")) {
        	creditCardNo =  CreditCardType.AMERICAN_EXPRESS.generateNumber();
		}
        else {
        	creditCardNo =  CreditCardType.DISCOVER.generateNumber();
        }
		return creditCardNo;
	}
	
	@Override
	public String generateCVV() {
		String cvv = "";
		Random rnd = new Random();
    	int counter =0;
    	while(counter <= 2){
    	int generate  = rnd.nextInt(9); 
    	cvv = cvv + Integer.toString(generate);
    	counter++;
    	}
		 
		return cvv;
	}	
}
