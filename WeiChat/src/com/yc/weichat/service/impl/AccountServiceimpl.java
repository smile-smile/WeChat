package com.yc.weichat.service.impl;

import java.io.InputStream;
import java.util.Map;

import com.yc.weichat.util.Encrypt;
import com.yc.weichat.dao.AccountDao;
import com.yc.weichat.dao.impl.AccountDaoimpl;
import com.yc.weichat.entity.Account;
import com.yc.weichat.service.AccountService;

public class AccountServiceimpl implements AccountService{
	
	private AccountDao loginDao;
	
	public AccountServiceimpl(){
		loginDao=new AccountDaoimpl();
	}
	@Override
	public Account login(String userId, String password) {
		String encryptPassword = Encrypt.md5AndSha(password);
		Map<String, Object> result = loginDao.selectAccount(userId, encryptPassword);
		if(result == null) {
			return null;
		}
		Account account = new Account();
		account.setUserId((String)result.get("userid"));
		account.setPassword((String)result.get("password"));
		account.setPhone((String)result.get("phone"));
		account.setEmail((String)result.get("email"));
		account.setName((String)result.get("name"));
		account.setSex((String)result.get("sex"));
		account.setPic((InputStream)result.get("pic"));
		return account;
		
	}

}
