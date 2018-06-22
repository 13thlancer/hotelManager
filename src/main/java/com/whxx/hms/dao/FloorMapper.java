package com.whxx.hms.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.whxx.hms.model.Floor;
import com.whxx.hms.model.dto.DropdownDto;

public interface FloorMapper {

	/**
	 * 查询楼层下拉框
	 * @Title: floorDropList 
	 * @Description: TODO
	 * @param param
	 * @return
	 * @return: List<DropdownDto>
	 */
	List<DropdownDto> floorDropList(Map param);
	
	List<Map<String, Object>> listFloor(Map<String, String> paramMap);

	Floor selectFloorByName(Map<String, String> paramMap);

	void updateFloor(Floor floor);

	String isFloorConfigCode(String floorCode);

	void insertFloor(Floor floor);

	void deleteFloorById(String floorId);

	String isFloorConfigCode(@Param("floorCode")String floorCode, @Param("buildingCode") String buildingCode);

}
