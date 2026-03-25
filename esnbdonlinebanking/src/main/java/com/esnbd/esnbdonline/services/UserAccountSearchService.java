package com.esnbd.esnbdonline.services;

import java.util.List;

import com.esnbd.esnbdonline.dto.Account;

public interface UserAccountSearchService {
	List<Account> getUserAccounts(String userName);
}
