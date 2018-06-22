package com.whxx.hms.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.whxx.hms.model.ModifyLogs;

@Repository
public interface ModifyLogsMapper {
	
	/**
	 * 新增表数据修改的操作记录
	 * @Title: insertModifyLogs 
	 * @Description: TODO
	 * @param modifyLogs
	 * @return: void
	 */
	void insertModifyLogs(ModifyLogs modifyLogs);
	
	/**
	 * 查询异动日志
	 * @Title: selectByPriMainId 
	 * @Description: TODO
	 * @param modifyLogs
	 * @return
	 * @return: List<ModifyLogs>
	 */
	List<ModifyLogs> selectByPriMainId(ModifyLogs modifyLogs);
}
