package com.whxx.hms.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.whxx.hms.model.dto.RateCodeDto;

@Repository
@SuppressWarnings("rawtypes")
public interface PriceCodeResMapper {

	/**
	 * 查询房价码
	 * @Title: ratecodeDropList 
	 * @Description: TODO
	 * @param param
	 * @return
	 * @return: List<RateCodeDto>
	 */
	List<RateCodeDto> ratecodeDropList( Map param);
}
