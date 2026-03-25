package com.esnbd.esnbdonline.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.esnbd.esnbdonline.dto.Account;
import com.esnbd.esnbdonline.dto.AccountTransactions;

public class BankStatementDisplayDAOImpl implements BankStatementDisplayDAO{	
	private HibernateTemplate session;

	public void setSession(HibernateTemplate session) {
		this.session = session;
	}	
	
	@SuppressWarnings("deprecation")
	@Override
	@Transactional(readOnly = true)
	public List<AccountTransactions> getAccountStatement(String accNo, String fromDate, String toDate) {		
		String hql = "from com.esnbd.esnbdonline.dto.AccountTransactions where account=:acc and date between :fromdate and :todate";
		
		Account acc = session.get(Account.class, Long.parseLong(accNo));		
		
		String[] paramNames = {"acc", "fromdate", "todate"};
		Object[] params = {acc, fromDate, toDate};		
		
		@SuppressWarnings("unchecked")
		List<AccountTransactions> transactions = (List<AccountTransactions>)session.findByNamedParam(hql, paramNames, params);		
		System.out.println(transactions);
		return transactions;		
	}
}
