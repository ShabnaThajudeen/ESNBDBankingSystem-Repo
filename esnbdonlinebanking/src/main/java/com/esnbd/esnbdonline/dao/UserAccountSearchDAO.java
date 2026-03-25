package com.esnbd.esnbdonline.dao;

import java.util.List;

import com.esnbd.esnbdonline.dto.AccountHolderDetails;

public interface UserAccountSearchDAO {
	List<AccountHolderDetails> getAccountHolderDetails(String userName);
}
