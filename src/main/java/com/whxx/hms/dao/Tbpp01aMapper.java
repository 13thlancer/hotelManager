package com.whxx.hms.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.whxx.hms.model.Tbpp01a;
import com.whxx.hms.model.dto.DropdownDto;
import com.whxx.hms.model.dto.HotelGroupId;

@Repository
public interface Tbpp01aMapper {
	
	/**
	 * 根据configcode查询通用代码档
	 * @Title: selectConfigCode 
	 * @Description: TODO
	 * @param hotel
	 * @return
	 * @return: List<DropdownDto>
	 */
	List<DropdownDto> selectConfigCode(Map param);

	List listSecond(Map<String, String> paramMap);

	void insertSecondCode(Tbpp01a tbpp01a);

	Tbpp01a selectSecondCodeByName(Map<String, String> paramMap);

	void updateSecondCode(Tbpp01a tbpp01a);

	void deleteSecondCodeById(String tbpp01aId);
	
	String isFirstSeq(@Param("tbpp01Id") String tbpp01Id,@Param("seq") String seq);

	String isFirstConfigCode(@Param("tbpp01Id") String tbpp01Id,@Param("configCode") String configCode);

}
