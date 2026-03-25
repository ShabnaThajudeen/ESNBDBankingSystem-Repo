package com.esnbd.esnbdonline.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.esnbd.esnbdonline.appconstants.AppConstants;
import com.esnbd.esnbdonline.dto.Account;

public class TransactionDAOImpl implements TransactionDAO{
	private HibernateTemplate session;

	public void setSession(HibernateTemplate session) {
		this.session = session;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Account getAccount(Long accountNo) {
		Account account = null;
		try {
			account = session.get(Account.class, accountNo);
		} catch (DataAccessException e) {			
			e.printStackTrace();
		}
		return account;		
	}
	
	@Override
	@Transactional(readOnly = false)
	public String transactionProcessing(Account debitAcc, Account creditAcc) {
		String message = "";
		try {
			session.update(debitAcc);
			session.update(creditAcc);
			message = AppConstants.TRANSACTION_SUCCESSFULL;
		} catch (DataAccessException e) {	
			message = AppConstants.TRANSACTION_FAILED;
			e.printStackTrace();
		}
		return message;
	}
}
