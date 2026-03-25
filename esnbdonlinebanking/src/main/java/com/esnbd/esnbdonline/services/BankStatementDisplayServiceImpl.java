package com.esnbd.esnbdonline.services;

import java.util.Collections;
import java.util.List;

import com.esnbd.esnbdonline.dao.BankStatementDisplayDAO;
import com.esnbd.esnbdonline.dto.AccountTransactions;

public class BankStatementDisplayServiceImpl implements BankStatementDisplayService{
	private BankStatementDisplayDAO bsDisplayDAO;	

	public void setBsDisplayDAO(BankStatementDisplayDAO bsDisplayDAO) {
		this.bsDisplayDAO = bsDisplayDAO;
	}

	@Override
	public List<AccountTransactions> getStatement(String accNo, String fromDate, String toDate){		
		String fromDt = DateService.dateStringFormatter(fromDate);
		String toDt = DateService.dateStringFormatter(toDate);
		
		List<AccountTransactions> trans = bsDisplayDAO.getAccountStatement(accNo, fromDt, toDt);	
		Collections.sort(trans, (AccountTransactions t1, AccountTransactions t2) -> {
			return t2.getTransId().compareTo(t1.getTransId());     
		}); 		
		return trans;		
	}
}
