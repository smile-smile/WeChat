package com.yc.weichat.dao;

import java.util.Map;

import com.yc.weichat.entity.Account;


public interface AccountDao {
	public Map<String, Object> selectAccounts(String userId, String password);
	public Map<String, Object> selectAccount(String userId);
	public int updateAccount(Account acc);
}