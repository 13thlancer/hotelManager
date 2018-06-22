package com.whxx.hms.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.whxx.hms.model.Building;
import com.whxx.hms.model.RoomTypeHistory;

@Repository
public interface RoomTypeHistoryMapper {

	void insertRoomTypeHistory(RoomTypeHistory roomTypeHistory);

	List<Map<String, Object>> listRoomTypeHistory(Map<String, String> paramMap);



}
