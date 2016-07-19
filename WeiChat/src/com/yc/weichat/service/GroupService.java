package com.yc.weichat.service;

import java.util.List;

import com.yc.weichat.entity.Account;
import com.yc.weichat.entity.Group;

public interface GroupService {
	public boolean addGroup(String groupId,String myselfId);
	public boolean newGroup(String groupId,String groupName,String adminName);
	public List<String> listGroupFriendsInfo(String groupId);
	public List<Group> listGroupInfo(String userId);
	public Group getGroupInfo(String groupId);
}
