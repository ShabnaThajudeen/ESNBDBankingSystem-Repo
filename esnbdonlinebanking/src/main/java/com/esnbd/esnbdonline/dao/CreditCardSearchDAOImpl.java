package com.esnbd.esnbdonline.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.esnbd.esnbdonline.dto.AccountHolderDetails;
import com.esnbd.esnbdonline.dto.User;

public class CreditCardSearchDAOImpl implements CreditCardSearchDAO{	
	private HibernateTemplate session;

	public void setSession(HibernateTemplate session) {
		this.session = session;
	}	
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	@Transactional(readOnly = true)
	public List<AccountHolderDetails> getCreditCardDetails(String userName){		
		List<AccountHolderDetails> holders = new ArrayList<AccountHolderDetails>();
		
		String hql = "from com.esnbd.esnbdonline.dto.AccountHolderDetails where user=:uName";		
		
		User user = new User();
		user.setUserName(userName);
		try {			
			holders = (List<AccountHolderDetails>)session.findByNamedParam(hql, "uName", user);				
		} catch (DataAccessException e) {			
			e.printStackTrace();
		}		
		return holders;
	}
}
