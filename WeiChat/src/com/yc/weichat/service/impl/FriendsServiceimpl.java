package com.yc.weichat.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yc.weichat.dao.FriendsDao;
import com.yc.weichat.dao.impl.FriendsDaoimpl;
import com.yc.weichat.entity.Account;
import com.yc.weichat.service.FriendsService;

public class FriendsServiceimpl implements FriendsService {
	
    private FriendsDao friendsDao;
    
    public FriendsServiceimpl(){
    	friendsDao=new FriendsDaoimpl();
    }
    
	public List<Account> listFriendsInfo(String myselfId) {
		List<Map<String, Object>> results = friendsDao.selectFriends(myselfId);
		List<Account> list = new ArrayList<Account>();
		for(Map<String, Object> map : results) {
			Account acc = new Account();
			acc.setUserId((String)map.get("userid"));
			acc.setPassword((String)map.get("password"));
			acc.setPhone((String)map.get("phone"));
			acc.setEmail((String)map.get("email"));
			acc.setName((String)map.get("name"));
			acc.setAddress((String)map.get("address"));
			acc.setSex((String)map.get("sex"));
			acc.setPic((InputStream)map.get("pic"));
			list.add(acc);
		}
		return list;
	}

	@Override
	public boolean addFriend(String userId, String friendId, String friendName) {
		return friendsDao.updateFriend(userId, friendId, friendName)>0;
	}
	
}
