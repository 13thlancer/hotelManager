package com.whxx.hms.service;

import com.whxx.hms.model.MainInfo;

public interface MainInfoSrv {

	/**
	 * 新增预订单信息
	 * @Title: insertMainInfo 
	 * @Description: TODO
	 * @param mainInfo
	 * @return: void
	 */
	void insertMainInfo(MainInfo mainInfo);
	
	
	/**
	 * 修改预订单信息
	 * @Title: insertMainInfo 
	 * @Description: TODO
	 * @param mainInfo
	 * @return: void
	 */
	void updateMainInfoById(MainInfo mainInfo);
	
}
