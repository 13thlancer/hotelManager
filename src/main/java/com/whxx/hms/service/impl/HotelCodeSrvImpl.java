package com.whxx.hms.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.whxx.core.controller.BaseController;
import com.whxx.core.utils.CommUtil;
import com.whxx.core.utils.ToolUtil;
import com.whxx.core.utils.UUIDUtil;
import com.whxx.hms.controller.systemcode.PubCodeController;
import com.whxx.hms.dao.BuildingMapper;
import com.whxx.hms.dao.FloorMapper;
import com.whxx.hms.dao.RoomMapper;
import com.whxx.hms.dao.RoomTypeMapper;
import com.whxx.hms.dao.Tbpp01Mapper;
import com.whxx.hms.dao.Tbpp01aMapper;
import com.whxx.hms.model.Building;
import com.whxx.hms.model.Floor;
import com.whxx.hms.model.Room;
import com.whxx.hms.model.RoomType;
import com.whxx.hms.model.Tbpp01a;
import com.whxx.hms.service.HotelCodeSrv;
import com.whxx.hms.service.PubCodeSrv;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpStatus;

@Service
@Transactional
public class HotelCodeSrvImpl implements HotelCodeSrv{
	private final static Logger logger = LoggerFactory.getLogger(HotelCodeSrvImpl.class);
	
	@Autowired
	private BuildingMapper buildingMapper;
	
	@Autowired
	private FloorMapper FloorMapper;
	
	@Autowired
	private RoomTypeMapper RoomTypeMapper;
	
	@Autowired
	private RoomMapper roomMapper;
	

	@Override
	public List<Map<String, Object>> listBuilding(Map<String, String> paramMap) {
		return this.buildingMapper.listBuilding(paramMap);
	}



	@Override
	public Building selectBuildingByName(Map<String, String> paramMap) {
		
		return this.buildingMapper.selectBuildingByName(paramMap);
	}



	@Override
	public void updateBuilding(Building building) {
		this.buildingMapper.updateBuilding(building);
	}


	@Override
	public String isBuildingConfigCode(String buildingCode) {
		return this.buildingMapper.isBuildingConfigCode(buildingCode);
	}





	@Override
	public void insertBuilding(Building building) {
		this.buildingMapper.insertBuilding(building);
	}



	@Override
	public void deleteBuildingById(String buildingId) {
		this.buildingMapper.deleteBuildingById(buildingId);
		
	}



	@Override
	public List<Map<String, Object>> listFloor(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return this.FloorMapper.listFloor(paramMap);
	}



	@Override
	public Floor selectFloorByName(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return this.FloorMapper.selectFloorByName(paramMap);
	}



	@Override
	public void updateFloor(Floor floor) {
		// TODO Auto-generated method stub
		this.FloorMapper.updateFloor(floor);
	}



	@Override
	public String isFloorConfigCode(String floorCode,String buildingCode) {
		// TODO Auto-generated method stub
		return this.FloorMapper.isFloorConfigCode(floorCode,buildingCode);
	}



	@Override
	public void insertFloor(Floor floor) {
		// TODO Auto-generated method stub
		this.FloorMapper.insertFloor(floor);
	}



	@Override
	public void deleteFloorById(String floorId) {
		// TODO Auto-generated method stub
		this.FloorMapper.deleteFloorById(floorId);
	}



	@Override
	public List<Map<String, Object>> listRoomType(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return this.RoomTypeMapper.listRoomType(paramMap);
	}



	@Override
	public RoomType selectRoomTypeByName(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return this.RoomTypeMapper.selectRoomTypeByName(paramMap);
	}



	@Override
	public void updateRoomType(RoomType roomType) {
		// TODO Auto-generated method stub
		this.RoomTypeMapper.updateRoomType(roomType);
	}



	@Override
	public void insertRoomType(RoomType roomType) {
		// TODO Auto-generated method stub
		this.RoomTypeMapper.insertRoomType(roomType);
	}



	@Override
	public void deleteRoomTypeById(String roomTypeId) {
		// TODO Auto-generated method stub
		this.RoomTypeMapper.deleteRoomTypeById(roomTypeId);
	}



	@Override
	public String isRoomTypeConfigCode(String floorCode) {
		// TODO Auto-generated method stub
		return this.RoomTypeMapper.isRoomTypeConfigCode(floorCode);
	}



	@Override
	public List<Map<String, Object>> listRooms(Map<String, String> paramMap) {
		return this.roomMapper.listRooms(paramMap);
	}



	@Override
	public Room selectRoomByRoomCode(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return this.roomMapper.selectRoomByRoomCode(paramMap);
	}



	@Override
	public String isRoomConfigCode(String roomCode) {
		// TODO Auto-generated method stub
		return this.roomMapper.isRoomConfigCode(roomCode);
	}



	@Override
	public void insertRoom(Room room) {
		// TODO Auto-generated method stub
		this.roomMapper.insertRoom(room);
	}



	@Override
	public void insertRooms(Map<String, String> paramMap,long startNum,long endNum ) {
		String mobilePrefix = paramMap.get("mobilePrefix");//获取分机号前缀
		String lockPrefix = paramMap.get("lockPrefix");//获取门锁号前缀
		for(long i=startNum;i<=endNum;i++) {
			//新增,seq默认为1
			Room room=new Room();
			String roomTypeId = UUIDUtil.getUUID();
			BeanUtil.copyProperties(paramMap, room);
			room.setRoomId(roomTypeId);
			room.setSeq(1);
			room.setStopped("N");//是否弃用，默认不弃用
			room.setLineNo(ToolUtil.getLineNo());
			room.setRoomCode(i+"");
			room.setTel(mobilePrefix+i);
			room.setTelShort(mobilePrefix+i);
			room.setLockRoomNo(lockPrefix+i);
			this.roomMapper.insertRoom(BaseController.setCreateAndUpdate(room));
		}
	}

	@Override
	public void updateRoom(Room room) {
		// TODO Auto-generated method stub
		this.roomMapper.updateRoom(room);
	}

	@Override
	public void deleteRoomById(String roomId) {
		// TODO Auto-generated method stub
		this.roomMapper.deleteRoomById(roomId);
	}


}
