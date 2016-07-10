package com.yc.weichat.service.impl;

import java.util.List;
import java.util.Map;

import com.yc.weichat.dao.FriendsDao;
import com.yc.weichat.dao.impl.FriendsDaoimpl;
import com.yc.weichat.service.FriendsService;

public class FriendsServiceimpl implements FriendsService {
	
    private FriendsDao friendsDao;
    
    public FriendsServiceimpl(){
    	friendsDao=new FriendsDaoimpl();
    }
	public List<Map<String, Object>> listFriendsInfo(String myselfId) {
		return friendsDao.selectFriends(myselfId);
	}
	
}
