package com.yc.weichat.dao.impl;

import java.util.Map;

import com.yc.weichat.dao.AccountDao;
import com.yc.weichat.entity.Account;
import com.yc.weichat.util.DBHelper;


public class AccountDaoimpl implements AccountDao{
	public Map<String, Object> selectAccounts(String userId,String password) {
		String sql = "select * from account where userId=? and password=?";
		return DBHelper.doQueryOne(sql, userId, password);
	}

	@Override
	public int updateAccount(Account acc) {
		String sql = "insert into Account values(?,?,?,?,?,?,?,?,null,null,null)";
		return DBHelper.doUpdate(sql,acc.getUserId(),acc.getPassword(),acc.getPhone(),acc.getEmail(),
				acc.getName(),acc.getAddress(),acc.getSex(),acc.getPic()); 
	}

	@Override
	public Map<String, Object> selectAccount(String userId) {
		String sql = "select * from account where userId=?";
		return DBHelper.doQueryOne(sql, userId);
	}

}
