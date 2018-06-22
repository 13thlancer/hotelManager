package com.whxx.hms.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.whxx.hms.model.Building;
import com.whxx.hms.model.PriceCode;

@Repository
public interface PriceCodeMapper {

	List<Map<String, Object>> listPriceCode(Map<String, String> paramMap);

	PriceCode selectPriceCodeByName(Map<String, String> paramMap);

	void updatePriceCode(PriceCode priceCode);

	String isPriceCodeConfigCode(String priceCodeCode);

	void insertPriceCode(PriceCode priceCode);

	void deletePriceCodeById(String priceCodeId);

}
