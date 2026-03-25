package com.esnbd.esnbdonline.services;

import java.util.List;

import com.esnbd.esnbdonline.dto.AccountTransactions;

public interface BankStatementDisplayService {
	List<AccountTransactions> getStatement(String accNo, String fromDate, String toDate);
}
