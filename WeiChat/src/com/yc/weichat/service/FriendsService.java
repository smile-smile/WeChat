package com.yc.weichat.service;

import java.util.List;
import java.util.Map;

import com.yc.weichat.entity.Account;

public interface FriendsService {
	List<Account> listFriendsInfo(String myselfId);
	public boolean addFriend(String userId,String friendId,String friendName);
}
