package com.whxx.hms.dao;

import org.springframework.stereotype.Repository;

import com.whxx.hms.model.MainInfoHistory;

@Repository
public interface MainInfoHistoryMapper {
	
	
	/**
	 * 新增
	 * @Title: insertConsume 
	 * @Description: TODO
	 * @param consume
	 * @return: void
	 */
	void insertMainInfoHistory(MainInfoHistory mainInfo);
	
	
}
