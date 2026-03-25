package com.esnbd.esnbdonline.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.esnbd.esnbdonline.dto.AccountHolderDetails;
import com.esnbd.esnbdonline.dto.User;

public class UserAccountSearchDAOImpl implements UserAccountSearchDAO{	
	private HibernateTemplate session;

	public void setSession(HibernateTemplate session) {
		this.session = session;
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	@Transactional(readOnly = true)
	public List<AccountHolderDetails> getAccountHolderDetails(String userName){			
		String hql = "from com.esnbd.esnbdonline.dto.AccountHolderDetails where user=:uName";
		List<AccountHolderDetails> holders = new ArrayList<AccountHolderDetails>();
		User user = new User();
		user.setUserName(userName);
		try {			
			holders = (List<AccountHolderDetails>)session.findByNamedParam(hql, "uName", user);	
			/*
			 * System.out.println();
			 * System.out.println("Getting account holder details for user: " + holders);
			 * System.out.println();
			 */
		} catch (DataAccessException e) {			
			e.printStackTrace();
		}		
		return holders;
	}
}
