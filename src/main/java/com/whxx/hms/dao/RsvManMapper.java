package com.whxx.hms.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.whxx.hms.model.RsvMan;
import com.whxx.hms.model.dto.DropdownDto;
import com.whxx.hms.model.dto.HotelGroupId;

@Repository
public interface RsvManMapper {

	List<Map<String, Object>> listRsvMan(Map<String, String> paramMap);

	RsvMan selectRsvManByName(Map<String, String> paramMap);

	void updateBuilding(RsvMan rsvMan);

	String isDistinctNo(String memberNo);

	void insertRsvMan(RsvMan rsvMan);

	void deleteRsvManById(String rsvManId);
	
	List<DropdownDto> selectGroupDropList(HotelGroupId hotel);

}
