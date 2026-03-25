package com.esnbd.esnbdonline.services;

import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import com.esnbd.esnbdonline.appconstants.AppConstants;
import com.esnbd.esnbdonline.dao.CreditCardTransactionDAO;
import com.esnbd.esnbdonline.dto.CreditCard;
import com.esnbd.esnbdonline.dto.CreditCardTransactions;

@Transactional
public class CreditCardTransactionServiceImpl implements CreditCardTransactionService{	
	private CreditCardTransactionDAO ccTransDAO;	
	
	public void setCcTransDAO(CreditCardTransactionDAO ccTransDAO) {
		this.ccTransDAO = ccTransDAO;
	}
	
	@Override
	public String processTransaction(String name, String creditCardNo, String cvv, String amount) {
		String message = "";		
		
		try {
			List<CreditCard> cards = ccTransDAO.getCreditCardDetails(name, creditCardNo, cvv);			
			message = this.validateCreditCardDetails(cards, name, creditCardNo, cvv, amount );
		} catch (Exception e) {
			message = AppConstants.CC_TRANSACTION_INVALID_DETAILS + " " + AppConstants.CC_TRANSACTION_FAILED;
			e.printStackTrace();
		}		
		return message;
	}

	public String validateCreditCardDetails(List<CreditCard> cards, String name, String creditCardNo, String cvv, String amount) {
		String message = "";
		if(cards.size() == 0) {
			message = AppConstants.CC_TRANSACTION_INVALID_DETAILS + " " + AppConstants.CC_TRANSACTION_FAILED;
		}
		else {
			for(CreditCard card: cards) {
				Float transAmount = Float.parseFloat(amount);
				boolean flag = card.refreshCreditCardLimit();
				if(flag) {
					Set<CreditCardTransactions> transcns = this.creditLimitUpdateTransaction(card);
					card.setTransactions(transcns);
					try {
						message = ccTransDAO.processTranaction(card);						
					} catch (Exception e) {
						message = AppConstants.CC_TRANSACTION_FAILED;
						e.printStackTrace();
					}
				}	
				if(DateService.notExpired(card.getValidThru())) {
					if(transAmount <= card.getBalanceLimit()) {
						card.processTransaction(transAmount);	
						
						Set<CreditCardTransactions> transactions = this.updateTransactionDetails(card, transAmount);
						card.setTransactions(transactions);				
						try {
							message = ccTransDAO.processTranaction(card);
						} catch (Exception e) {
							message = AppConstants.CC_TRANSACTION_FAILED;
							e.printStackTrace();
						}
					}
					else {
						message = AppConstants.CC_TRANSACTION_INSUFFICIENT_BALANCE + " " + AppConstants.CC_TRANSACTION_FAILED;
					}
				}
				else {
					message = AppConstants.CC_TRANSACTION_CC_EXPIRED + " " + AppConstants.CC_TRANSACTION_FAILED;
				}
							
			}	
		}			
		return message;
	}
	
	@Override
	public Set<CreditCardTransactions> updateTransactionDetails(CreditCard card, Float amount){
		Set<CreditCardTransactions> transactions =  card.getTransactions();
		
		CreditCardTransactions transaction = new CreditCardTransactions();
		transaction.setDescription("Online Payment");
		transaction.setBalanceLimit(card.getBalanceLimit());
		transaction.setCard(card);
		transaction.setDate(DateService.getCurrentDate());
		transaction.setDebit(amount);	
		
		transactions.add(transaction);			
		return transactions;
	}	
	
	@Override
	public Set<CreditCardTransactions> creditLimitUpdateTransaction(CreditCard card){
		Set<CreditCardTransactions> transactions =  card.getTransactions();
		
		CreditCardTransactions transaction = new CreditCardTransactions();
		transaction.setDescription("Monthly limit added");
		transaction.setBalanceLimit(card.getBalanceLimit());
		transaction.setCard(card);
		transaction.setDate(DateService.getCurrentDate());
		transaction.setDebit(null);	
		
		transactions.add(transaction);			
		return transactions;
	}	
}
