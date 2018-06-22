package com.whxx.hms.service;

import java.util.List;

import com.whxx.hms.model.SubInfo;
import com.whxx.hms.model.dto.SubInfoDto;

public interface SubInfoSrv {

	/**
	 * 新增预订单信息
	 * @Title: insertSubInfo 
	 * @Description: TODO
	 * @param subInfo
	 * @return: void
	 */
	void insertSubInfo(SubInfo subInfo);
	
	
	/**
	 * 修改预订单信息
	 * @Title: insertMainInfo 
	 * @Description: TODO
	 * @param mainInfo
	 * @return: void
	 */
	void updateSubInfoById(SubInfo subInfo);
	
	/**
	 * 批量处理列表
	 * @Title: batchOrderList 
	 * @Description: TODO
	 * @param priMainId
	 * @return
	 * @return: List<SubInfoDto>
	 */
	List<SubInfoDto> batchOrderList(String priMainId);
}
