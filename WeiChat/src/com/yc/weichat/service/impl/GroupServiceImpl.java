package com.yc.weichat.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yc.weichat.dao.GroupDao;
import com.yc.weichat.dao.impl.GroupDaoImpl;
import com.yc.weichat.entity.Group;
import com.yc.weichat.service.GroupService;

public class GroupServiceImpl implements GroupService {
	GroupDao groupnDao;
	public GroupServiceImpl() {
		groupnDao=new GroupDaoImpl();
	}
	@Override
	public boolean addGroup(String groupId,String myselfId){
		return groupnDao.updateRelation(groupId, myselfId)>0;
	}
	@Override
	public boolean newGroup(String groupId, String groupName, String adminName) {
		return groupnDao.updateGroup(groupId, groupName, adminName)>0;
	}
	
	@Override
	public List<String> listGroupFriendsInfo(String groupId) {
		List<Map<String, Object>> results = groupnDao.selectGroupfriendsId(groupId);
		List<String> list = new ArrayList<String>();
		
		for(Map<String, Object> map : results) {
			list.add((String)map.get("userid"));
		}
		return list;
	}
	@Override
	public List<Group> listGroupInfo(String userId) {
		List<Map<String, Object>> result = groupnDao.selectGroup(userId);
		List<Group> list = new ArrayList<Group>();
		for(Map<String, Object> map : result) {
			Group g = new Group();
			g.setId((String)map.get("groupid"));
			g.setName((String)map.get("name"));
			g.setAdmin((String)map.get("adminname"));
			list.add(g);
		}
		return list;
	}
	@Override
	public Group getGroupInfo(String groupId) {
		Map<String, Object> result = groupnDao.selectGroupInfo(groupId);
		Group g = new Group();
		g.setId((String)result.get("groupid"));
		g.setName((String)result.get("name"));
		g.setAdmin((String)result.get("adminname"));
		return g;
	}




}
