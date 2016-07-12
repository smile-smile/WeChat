package com.yc.weichat.service;

import com.yc.weichat.entity.Account;

public interface AccountService {
	
	public Account login(String userId, String password);
	public boolean register(Account acc);
	public Account findAccount(String userId);
}
