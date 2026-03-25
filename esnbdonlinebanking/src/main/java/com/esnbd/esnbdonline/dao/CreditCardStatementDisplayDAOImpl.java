package com.esnbd.esnbdonline.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.esnbd.esnbdonline.dto.CreditCard;
import com.esnbd.esnbdonline.dto.CreditCardTransactions;

public class CreditCardStatementDisplayDAOImpl implements CreditCardStatementDisplayDAO{	
	private HibernateTemplate session;

	public void setSession(HibernateTemplate session) {
		this.session = session;
	}	
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	@Transactional(readOnly = true)
	public List<CreditCard> getCreditCards(String ccNo) {
		List<CreditCard> cards = new ArrayList<CreditCard>();
		
		String hql = "from com.esnbd.esnbdonline.dto.CreditCard where creditCardNo = :no";
		try {
			cards = (List<CreditCard>)session.findByNamedParam(hql, "no", ccNo);					
		} catch (DataAccessException e) {			
			e.printStackTrace();
		}
		return cards;
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	@Transactional(readOnly = true)
	public List<CreditCardTransactions> getCreditStatement(CreditCard c, String fromDate, String toDate) {		
		String hql = "from com.esnbd.esnbdonline.dto.CreditCardTransactions where card=:card and date between :fromD and :toD";			
		
		String[] paramNames = {"card", "fromD", "toD"};
		Object[] params = {c, fromDate, toDate};		
		
		
		List<CreditCardTransactions> transactions = new ArrayList<CreditCardTransactions>();
		try {
			transactions = (List<CreditCardTransactions>)session.findByNamedParam(hql, paramNames, params);
			System.out.println(transactions.size());
			for(CreditCardTransactions tran: transactions) {
				System.out.println(tran.getTransId());
			}
		} catch (DataAccessException e) {			
			e.printStackTrace();
		}		
		return transactions;		
	}
}
