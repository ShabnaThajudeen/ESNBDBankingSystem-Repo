package com.esnbd.esnbdonline.services;

import com.esnbd.esnbdonline.dao.UserNameSearchDAO;

public class UserNameSearchServiceImpl implements UserNameSearchService{
	private UserNameSearchDAO userNameSearchDAO;
	
	public void setUserNameSearchDAO(UserNameSearchDAO userNameSearchDAO) {
		this.userNameSearchDAO = userNameSearchDAO;
	}

	@Override
	public String getName(String userName){
		String name = "";
		
		name = userNameSearchDAO.getName(userName);
		
		return name;		
	}
}
