package com.whxx.hms.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.whxx.hms.model.dto.DropdownDto;

@Repository
public interface SalesManMapper {
	
	/**
	 * 查询销售员的下拉框
	 * @Title: insert 
	 * @Description: TODO
	 * @param tbaccessLog
	 * @return: void
	 */
	@SuppressWarnings("rawtypes")
	List<DropdownDto> salesManDropDownList(Map param);
	
}
