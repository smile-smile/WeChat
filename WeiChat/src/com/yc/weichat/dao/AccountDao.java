package com.yc.weichat.dao;

import java.util.Map;

public interface AccountDao {
	public Map<String, Object> selectAccount(String userId, String password);
}