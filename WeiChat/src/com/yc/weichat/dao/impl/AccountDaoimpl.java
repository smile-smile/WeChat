package com.yc.weichat.dao.impl;

import java.util.Map;

import com.yc.weichat.dao.AccountDao;
import com.yc.weichat.util.DBHelper;

public class AccountDaoimpl implements AccountDao{
	public Map<String, Object> selectAccount(String userId,String password) {
		String sql = "select * from account where userId=? and password=?";
		return DBHelper.doQueryOne(sql, userId, password);
	}
}
