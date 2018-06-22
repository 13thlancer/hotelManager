package com.whxx.hms.service;

import java.util.List;
import java.util.Map;

import com.whxx.hms.model.Building;
import com.whxx.hms.model.Floor;
import com.whxx.hms.model.Room;
import com.whxx.hms.model.RoomType;
import com.whxx.hms.model.Tbpp01a;

public interface HotelCodeSrv {

	List<Map<String, Object>> listBuilding(Map<String, String> paramMap);

	Building selectBuildingByName(Map<String, String> paramMap);

	void updateBuilding(Building building);

	String isBuildingConfigCode(String buildingCode);

	void insertBuilding(Building setCreateAndUpdate);

	void deleteBuildingById(String buildingId);

	List<Map<String, Object>> listFloor(Map<String, String> paramMap);

	Floor selectFloorByName(Map<String, String> paramMap);

	void updateFloor(Floor floor);

	void insertFloor(Floor setCreateAndUpdate);

	void deleteFloorById(String floorId);

	List<Map<String, Object>> listRoomType(Map<String, String> paramMap);

	RoomType selectRoomTypeByName(Map<String, String> paramMap);

	void updateRoomType(RoomType roomType);

	void insertRoomType(RoomType setCreateAndUpdate);

	void deleteRoomTypeById(String roomTypeId);

	String isRoomTypeConfigCode(String floorCode);

	List<Map<String, Object>> listRooms(Map<String, String> paramMap);

	Room selectRoomByRoomCode(Map<String, String> paramMap);

	String isRoomConfigCode(String roomCode);

	void insertRoom(Room setCreateAndUpdate);

	void insertRooms(Map<String, String> paramMap, long startNum, long endNum);

	void updateRoom(Room room);

	void deleteRoomById(String roomId);

	String isFloorConfigCode(String floorCode, String buildingCode);

	

}
