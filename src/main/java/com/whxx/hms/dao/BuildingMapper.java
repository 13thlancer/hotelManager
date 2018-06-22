package com.whxx.hms.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.whxx.hms.model.Building;
import com.whxx.hms.model.dto.DropdownDto;

@Repository
public interface BuildingMapper {

	List<Map<String, Object>> listBuilding(Map<String, String> paramMap);

	Building selectBuildingByName(Map<String, String> paramMap);

	void updateBuilding(Building building);

	String isBuildingConfigCode(String buildingCode);

	void insertBuilding(Building building);

	void deleteBuildingById(String buildingId);
	
	/**
	 * 查询楼栋
	 * @Title: buildingDropList 
	 * @Description: TODO
	 * @param param
	 * @return
	 * @return: List<DropdownDto>
	 */
	List<DropdownDto> buildingDropList(Map param);

}
