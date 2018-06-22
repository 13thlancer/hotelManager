package com.whxx.hms.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.whxx.hms.model.dto.DropdownDto;
import com.whxx.hms.model.dto.HotelGroupId;

@Repository
public interface CommissionCodeMapper {

	/**
	 * 佣金码下拉框
	 * @Title: commissionCodeDropList 
	 * @Description: TODO
	 * @param hotel
	 * @return
	 * @return: List<DropdownDto>
	 */
	List<DropdownDto> commissionCodeDropList(HotelGroupId hotel);

}