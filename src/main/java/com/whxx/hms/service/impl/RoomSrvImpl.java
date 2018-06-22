package com.whxx.hms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whxx.hms.dao.RoomMapper;
import com.whxx.hms.service.RoomSrv;

@Service
public class RoomSrvImpl implements RoomSrv {

	@Autowired
	private RoomMapper roomMapper;

	@Override
	public List<String> getRoomList(Map<String, String> paramMap) {
		
		return this.roomMapper.getRoomList(paramMap);
	}

}
