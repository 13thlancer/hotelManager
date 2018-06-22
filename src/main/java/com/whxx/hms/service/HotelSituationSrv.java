package com.whxx.hms.service;

import java.util.List;
import java.util.Map;

import com.whxx.hms.model.MainInfo;
import com.whxx.hms.model.RoomStatus;
import com.whxx.hms.model.RoomTypeHistory;
import com.whxx.hms.model.SubInfo;

public interface HotelSituationSrv {

	List<RoomStatus> getListRoomStatus(Map<String, String> paramMap);

	SubInfo getSubInfo( String subInfoId);

	void insertRoomTypeHistory(RoomTypeHistory setCreateEmpAndTime);

	List<Map<String, Object>> listRoomTypeHistory(Map<String, String> paramMap);

}
