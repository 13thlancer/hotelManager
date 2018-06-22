package com.whxx.hms.service;

import java.util.List;

import com.whxx.hms.model.dto.DropdownDto;
import com.whxx.hms.model.dto.HotelGroupId;

public interface RsvManSrv {

	/**
	 * 查询协议单位下拉框
	 * @Title: selectGroupDropList 
	 * @Description: TODO
	 * @param hotel
	 * @return
	 * @return: List<DropdownDto>
	 */
	List<DropdownDto> selectGroupDropList(HotelGroupId hotel);
}
