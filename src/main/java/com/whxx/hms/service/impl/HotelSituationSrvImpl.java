package com.whxx.hms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whxx.hms.dao.MainInfoMapper;
import com.whxx.hms.dao.RoomStatusMapper;
import com.whxx.hms.dao.RoomTypeHistoryMapper;
import com.whxx.hms.dao.SubInfoMapper;
import com.whxx.hms.model.MainInfo;
import com.whxx.hms.model.RoomStatus;
import com.whxx.hms.model.RoomTypeHistory;
import com.whxx.hms.model.SubInfo;
import com.whxx.hms.service.HotelSituationSrv;

@Service
public class HotelSituationSrvImpl implements HotelSituationSrv {
	
	@Autowired
	private RoomStatusMapper roomStatusMapper;
	
	@Autowired
	private SubInfoMapper subInfoMapper;
	
	@Autowired
	private RoomTypeHistoryMapper roomTypeHistoryMapper;


	@Override
	public List<RoomStatus> getListRoomStatus(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return this.roomStatusMapper.getListRoomStatus(paramMap);
	}


	@Override
	public SubInfo getSubInfo(String subInfoId) {
		// TODO Auto-generated method stub
		return this.subInfoMapper.getSubInfo(subInfoId);
	}


	@Override
	public void insertRoomTypeHistory(RoomTypeHistory roomTypeHistory) {
		// TODO Auto-generated method stub
		this.roomTypeHistoryMapper.insertRoomTypeHistory(roomTypeHistory);
	}


	@Override
	public List<Map<String, Object>> listRoomTypeHistory(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return this.roomTypeHistoryMapper.listRoomTypeHistory(paramMap);
	}

}
