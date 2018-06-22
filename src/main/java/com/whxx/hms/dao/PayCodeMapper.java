package com.whxx.hms.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.whxx.hms.model.Building;
import com.whxx.hms.model.PayCode;
import com.whxx.hms.model.dto.DropdownDto;
import com.whxx.hms.model.dto.HotelGroupId;

@Repository
public interface PayCodeMapper {

	List<Map<String, Object>> listPayCode(Map<String, String> paramMap);

	PayCode selectPayCodeByName(Map<String, String> paramMap);

	String isPayCodeConfigCode(String payCodeCode);

	void insertPayCode(PayCode payCode);

	void updatePayCode(PayCode payCode);

	void deletePayCodeById(String payCodeId);

	String isPayCodeSeq(String seq);
	
	/**
	 * 付款方式（所有付款方式不区分类别）
	 * @Title: payCodeDropList 
	 * @Description: TODO
	 * @param hotel
	 * @return
	 * @return: List<DropdownDto>
	 */
	List<DropdownDto> payCodeDropList(HotelGroupId hotel);
	
	
	/**
	 *  付款方式（只包含国内卡和国外卡）
	 * @Title: payCodeDropList 
	 * @Description: TODO
	 * @param hotel
	 * @return
	 * @return: List<DropdownDto>
	 */
	List<DropdownDto> paylicenceCodeDropList(HotelGroupId hotel);



}
