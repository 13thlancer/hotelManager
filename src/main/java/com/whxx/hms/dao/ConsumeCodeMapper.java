package com.whxx.hms.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.whxx.hms.model.ConsumeCode;

@Repository
public interface ConsumeCodeMapper {

	List<Map<String, Object>> listConsumeCode(Map<String, String> paramMap);

	void updateConsumeCode(ConsumeCode consumeCode);

	ConsumeCode selectConsumeCodeByName(Map<String, String> paramMap);

}