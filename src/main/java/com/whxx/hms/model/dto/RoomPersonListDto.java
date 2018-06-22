package com.whxx.hms.model.dto;

import java.util.List;

import com.whxx.hms.model.RoomPerson;

/**
 * 住客信息
 * @ClassName: RoomPersonListDto 
 * @Description: TODO
 * @author: zengxin
 * @date: 2018年6月21日 下午5:46:38
 */
public class RoomPersonListDto {

	/**
	 * 住客信息
	 */
	private List<RoomPerson> roomPersonList;

	public List<RoomPerson> getRoomPersonList() {
		return roomPersonList;
	}

	public void setRoomPersonList(List<RoomPerson> roomPersonList) {
		this.roomPersonList = roomPersonList;
	}
	
}
