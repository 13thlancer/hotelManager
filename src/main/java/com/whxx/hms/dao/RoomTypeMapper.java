package com.whxx.hms.dao;

import java.util.List;
import java.util.Map;

import com.whxx.hms.model.RoomType;
import com.whxx.hms.model.dto.RoomTypeDto;

public interface RoomTypeMapper {

	List<Map<String, Object>> listRoomType(Map<String, String> paramMap);

	RoomType selectRoomTypeById(String roomTypeId);

	void updateRoomType(RoomType roomType);

	void insertRoomType(RoomType roomType);

	void deleteRoomTypeById(String roomTypeId);
	
	RoomType selectRoomTypeByName(Map<String, String> paramMap);

	String isRoomTypeConfigCode(String floorCode);
	
	/**
	 * 房型码下拉框
	 * @Title: listRoomTypeApp 
	 * @Description: TODO
	 * @param param
	 * @return
	 * @return: List<RoomType>
	 */
	@SuppressWarnings("rawtypes")
	List<RoomTypeDto> roomTypeDropList(Map param);

}
