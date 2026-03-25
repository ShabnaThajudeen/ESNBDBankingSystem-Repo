package com.esnbd.esnbdonline.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import com.esnbd.esnbdonline.appconstants.AppConstants;
import com.esnbd.esnbdonline.dao.TransactionDAO;
import com.esnbd.esnbdonline.dto.Account;
import com.esnbd.esnbdonline.dto.AccountTransactions;
import com.esnbd.esnbdonline.dto.CurrentAccount;
import com.esnbd.esnbdonline.dto.SBAccount;

public class TransactionServiceImpl implements TransactionService{	
	private TransactionDAO transDAO;

	public void setTransDAO(TransactionDAO transDAO) {
		this.transDAO = transDAO;
	}
	
	@Override
	public String debitProcessing(String debitFromAcc, String debitAmount, String creditToAcc) {
		CurrentAccount debitFromCurrAccount;
		CurrentAccount creditToCurrAccount;
		SBAccount debitFromSBAccount;
		SBAccount creditToSBAccount;
		Account debitAcc;
		Account creditAcc; 
		String message = "";
		Set<AccountTransactions> transactions1 = new LinkedHashSet<AccountTransactions>();
		Set<AccountTransactions> transactions2 = new LinkedHashSet<AccountTransactions>();
		
		Long debitAccNo = Long.parseLong(debitFromAcc);
		Long creditAccNo = Long.parseLong(creditToAcc);
		Float amount = Float.parseFloat(debitAmount);	
		
		Account debitFromAccount = transDAO.getAccount(debitAccNo);
		Account creditToAccount = transDAO.getAccount(creditAccNo);
		
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM yyyy");
		String dateStr = dateFormat.format(date);
		
		if(debitFromAccount != null && creditToAccount != null) {
			if(debitFromAccount instanceof CurrentAccount) {
				debitFromCurrAccount = (CurrentAccount)debitFromAccount;
				if(debitFromCurrAccount.getAccountBalance() >=  amount) {
					debitFromCurrAccount.withdraw(amount);
					
					AccountTransactions trans = new AccountTransactions(dateStr, AppConstants.TRANSACTION_DESC_ONELINE_DEBIT, null, amount, null, debitFromCurrAccount.getAccountBalance(), debitFromCurrAccount);
					transactions1.add(trans);					
					debitFromCurrAccount.setTransactions(transactions1);
					
					debitAcc = debitFromCurrAccount;
				}
				else {
					message = AppConstants.TRANSACTION_INSUFFICIENT_BALANCE + debitFromCurrAccount.getAccountNo();
					return message;
				}
				
			}
			else {
				debitFromSBAccount = (SBAccount)debitFromAccount;
				if(debitFromSBAccount.getAccountBalance() >= amount) {
					debitFromSBAccount.withdraw(amount);
					
					AccountTransactions trans = new AccountTransactions(dateStr, AppConstants.TRANSACTION_DESC_ONELINE_DEBIT, null, amount, null, debitFromSBAccount.getAccountBalance(), debitFromSBAccount);
					transactions1.add(trans);					
					debitFromSBAccount.setTransactions(transactions1);
					
					debitAcc = debitFromSBAccount;
				}
				else {
					message = AppConstants.TRANSACTION_INSUFFICIENT_BALANCE + debitFromSBAccount.getAccountNo();
					return message;
				}
				
			}
			
			if(creditToAccount instanceof CurrentAccount) {
				creditToCurrAccount = (CurrentAccount)creditToAccount;
				creditToCurrAccount.deposit(amount);
				
				AccountTransactions trans = new AccountTransactions(dateStr, AppConstants.TRANSACTION_DESC_ONELINE_CREDIT, null, null, amount, creditToCurrAccount.getAccountBalance(), creditToCurrAccount);
				transactions2.add(trans);					
				creditToCurrAccount.setTransactions(transactions2);
				
				creditAcc = creditToCurrAccount;
			}
			else {
				creditToSBAccount = (SBAccount)creditToAccount;
				creditToSBAccount.deposit(amount);
				
				AccountTransactions trans = new AccountTransactions(dateStr, AppConstants.TRANSACTION_DESC_ONELINE_CREDIT, null, null, amount, creditToSBAccount.getAccountBalance(), creditToSBAccount);
				transactions2.add(trans);					
				creditToSBAccount.setTransactions(transactions2);
				
				creditAcc = creditToSBAccount;
			}			
			message = transDAO.transactionProcessing(debitAcc, creditAcc);
		}
		else {
			if(debitFromAccount == null) {
				message = debitFromAcc + " " + AppConstants.TRANSACTION_ACCOUNT_NOT_EXIST;
			}
			else {
				message = creditToAcc + " " + AppConstants.TRANSACTION_ACCOUNT_NOT_EXIST;
			}			
		}		
		return message;
	}
}
