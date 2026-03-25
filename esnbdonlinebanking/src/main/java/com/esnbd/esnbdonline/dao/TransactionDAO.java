package com.esnbd.esnbdonline.dao;

import com.esnbd.esnbdonline.dto.Account;

public interface TransactionDAO {
	Account getAccount(Long accountNo);
	String transactionProcessing(Account debitAcc, Account creditAccount);
}
