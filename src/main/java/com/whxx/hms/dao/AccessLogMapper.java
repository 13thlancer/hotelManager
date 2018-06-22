package com.whxx.hms.dao;

import org.springframework.stereotype.Repository;

import com.whxx.hms.model.TbAccessLog;

@Repository
public interface AccessLogMapper {
	
	/**
	 * 新增访问其他后台系统接口调用日志
	 * @Title: insert 
	 * @Description: TODO
	 * @param tbaccessLog
	 * @return: void
	 */
	void insert(TbAccessLog tbaccessLog);
	
}
