package com.whxx.hms.service;

import com.whxx.hms.model.ModifyLogs;

public interface ModifyLogsSrv {

	/**
	 * 新增表数据修改的操作记录
	 * @Title: insertModifyLogs 
	 * @Description: TODO
	 * @param modifyLogs
	 * @return: void
	 */
	void insertModifyLogs(ModifyLogs modifyLogs);
}
