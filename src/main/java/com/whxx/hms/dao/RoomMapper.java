package com.whxx.hms.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.whxx.hms.model.Room;
import com.whxx.hms.model.dto.RoomDto;

@Repository
public interface RoomMapper {

	List<Map<String, Object>> listRooms(Map<String, String> paramMap);

	Room selectRoomByRoomCode(Map<String, String> paramMap);

	String isRoomConfigCode(String roomCode);

	void insertRoom(Room room);

	void updateRoom(Room room);

	void deleteRoomById(String roomId);

	List<String> getRoomList(Map<String, String> paramMap);
	
	/**
	 * 排房根据房型查询可用房间号
	 * @Title: roomDropDownList 
	 * @Description: TODO
	 * @param param
	 * @return
	 * @return: List<DropdownDto>
	 */
	@SuppressWarnings("rawtypes")
	List<RoomDto> roomDropDownList(Map param);



}
