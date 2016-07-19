package com.yc.weichat.dao.impl;

import java.util.List;
import java.util.Map;

import com.yc.weichat.dao.GroupDao;
import com.yc.weichat.util.DBHelper;

public class GroupDaoImpl implements GroupDao {

	@Override
	//加入群
	public int updateRelation(String groupId, String myselfId) {
		String sql = "insert into Relation values(?,?,null,null,null)";
		return DBHelper.doUpdate(sql,groupId,myselfId); 
	}

	@Override
	//创建群
	public int updateGroup(String groupId, String groupName, String adminName) {
		String sql = "insert into Groups values(?,?,?,null,null)";
		return DBHelper.doUpdate(sql,groupId,groupName,adminName); 
	}

	@Override
	//查找群里的人
	public List<Map<String, Object>> selectGroupfriendsId(String groupId) {
		String sql="select userId from Relation where groupId=?";
		return DBHelper.doQuery(sql,groupId);
	}

	@Override
	public List<Map<String, Object>> selectGroup(String userId) {
		String sql = "select * from Groups where groupId in (select groupId from Relation where userId=?)";
		return DBHelper.doQuery(sql,userId);
	}

	public Map<String, Object> selectGroupInfo(String groupId) {
		String sql = "select * from Groups where groupId =?";
		return DBHelper.doQueryOne(sql,groupId);
	}
}
