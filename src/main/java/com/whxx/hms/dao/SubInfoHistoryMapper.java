package com.whxx.hms.dao;

import org.springframework.stereotype.Repository;

import com.whxx.hms.model.SubInfoHistory;

@Repository
public interface SubInfoHistoryMapper {
	
	/**
	 * 新增
	 * @Title: insertSubInfoHistory 
	 * @Description: TODO
	 * @param consume
	 * @return: void
	 */
	void insertSubInfoHistory(SubInfoHistory subInfo);
	
}
