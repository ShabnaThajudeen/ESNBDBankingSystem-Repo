package com.esnbd.esnbdonline.dao;

import java.util.List;

import com.esnbd.esnbdonline.dto.AccountTransactions;

public interface BankStatementDisplayDAO {
	List<AccountTransactions> getAccountStatement(String accNo, String fromDate, String toDate);	
}
