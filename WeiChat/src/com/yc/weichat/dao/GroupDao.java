package com.yc.weichat.dao;

import java.util.List;
import java.util.Map;

public interface GroupDao {
	public int updateRelation(String groupId,String myselfId);
	public int updateGroup(String groupId,String groupName,String adminName);
	public List<Map<String, Object>> selectGroupfriendsId(String groupId);
	public List<Map<String, Object>> selectGroup(String userId);
	public Map<String, Object> selectGroupInfo(String groupId);
}
