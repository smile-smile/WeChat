package com.yc.weichat.service;

import java.util.List;
import java.util.Map;

public interface FriendsService {
	List<Map<String, Object>> listFriendsInfo(String myselfId);
}
