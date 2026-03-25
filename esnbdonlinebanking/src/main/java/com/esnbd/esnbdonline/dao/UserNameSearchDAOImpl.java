package com.esnbd.esnbdonline.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.esnbd.esnbdonline.dto.AccountHolderDetails;
import com.esnbd.esnbdonline.dto.User;

public class UserNameSearchDAOImpl implements UserNameSearchDAO{	
	private HibernateTemplate session;

	public void setSession(HibernateTemplate session) {
		this.session = session;
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	@Transactional(readOnly = true)
	public String getName(String userName){
		String name = "";
		
		User user = new User();
		user.setUserName(userName);
		
		String hql = "from com.esnbd.esnbdonline.dto.AccountHolderDetails where user=:uName";
		List<AccountHolderDetails> holders;
		try {
			holders = (List<AccountHolderDetails>)session.findByNamedParam(hql, "uName", user);
			for(AccountHolderDetails holder: holders ) {				
				name = holder.getName();	
				System.out.println();
				System.out.println("Logged in user name = " + name);
			}		
		} catch (DataAccessException e) {			
			e.printStackTrace();
		}		
		return name;
	}
}
