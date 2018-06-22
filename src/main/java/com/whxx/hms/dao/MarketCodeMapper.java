package com.whxx.hms.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.whxx.hms.model.ConsumeCode;
import com.whxx.hms.model.MarketCode;
import com.whxx.hms.model.PayCode;

@Repository
public interface MarketCodeMapper {

	List<Map<String, Object>> listMarketCode(Map<String, String> paramMap);

	MarketCode selectMarketCodeByName(Map<String, String> paramMap);

	String isMarketCodeConfigCode(String marketCodeCode);

	void insertMarketCode(MarketCode marketCode);

	void updateMarketCode(MarketCode marketCode);

	void deleteMarketCodeById(String marketCodeId);

	String isMarketCodeSeq(String seq);

}