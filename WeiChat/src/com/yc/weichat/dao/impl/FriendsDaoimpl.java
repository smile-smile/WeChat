package com.yc.weichat.dao.impl;
import java.util.List;
import java.util.Map;

import com.yc.weichat.util.DBHelper;
import com.yc.weichat.dao.FriendsDao;

public class FriendsDaoimpl implements FriendsDao {

	public List<Map<String, Object>> selectFriends(String myselfId){
		String sql="select * from Account where userId in(select friendId from Friends where myselfId=?)";
		return DBHelper.doQuery(sql,myselfId);
	}

	@Override
	public int updateFriend(String myselfId, String friendId, String friendName) {
		String sql = "insert into Friends values(?,?,?,null,null,null)";
		return DBHelper.doUpdate(sql,myselfId,friendId,friendName);
	}

}
