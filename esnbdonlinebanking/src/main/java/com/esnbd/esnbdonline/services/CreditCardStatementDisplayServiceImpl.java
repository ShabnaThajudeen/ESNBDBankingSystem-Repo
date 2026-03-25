package com.esnbd.esnbdonline.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.esnbd.esnbdonline.dao.CreditCardStatementDisplayDAO;
import com.esnbd.esnbdonline.dto.CreditCard;
import com.esnbd.esnbdonline.dto.CreditCardTransactions;

@Transactional
public class CreditCardStatementDisplayServiceImpl implements CreditCardStatementDisplayService{
	private CreditCardStatementDisplayDAO ccsDisplayDAO;
	
	public void setCcsDisplayDAO(CreditCardStatementDisplayDAO ccsDisplayDAO) {
		this.ccsDisplayDAO = ccsDisplayDAO;
	}

	@Override
	public List<CreditCardTransactions> getStatement(String ccNo, String fromDate, String toDate){
		List<CreditCardTransactions> trans = new ArrayList<CreditCardTransactions>();
		List<CreditCard> cards = new ArrayList<CreditCard>();
		try {			
			cards = ccsDisplayDAO.getCreditCards(ccNo);				
			CreditCard c = this.getCreditCardId(cards);			
			trans = this.getCardTransactions(c, fromDate, toDate);				
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return trans;
	}
	
	@Override
	public CreditCard getCreditCardId(List<CreditCard> cards) {	
		CreditCard c = null;
		if(cards.size() != 0) {
			for(CreditCard card : cards) {
				c = card;				
			}
		}	
		return c;
	}
	
	@Override
	public List<CreditCardTransactions> getCardTransactions(CreditCard c, String fromDate, String toDate) {
		String fromDt = DateService.dateStringFormatter(fromDate);
		String toDt = DateService.dateStringFormatter(toDate);
		
		List<CreditCardTransactions> trans = new ArrayList<CreditCardTransactions>();
		try {
			trans = ccsDisplayDAO.getCreditStatement(c, fromDt, toDt);
		} catch (Exception e) {			
			e.printStackTrace();
		}	
		Collections.sort(trans, (CreditCardTransactions t1, CreditCardTransactions t2) -> { return t2.getTransId().compareTo(t1.getTransId());
		});
		return trans;	
	}
}
