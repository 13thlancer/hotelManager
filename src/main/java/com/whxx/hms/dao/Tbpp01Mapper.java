package com.whxx.hms.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.whxx.hms.model.Tbpp01a;

@Repository
public interface Tbpp01Mapper {

	List listFirstConfig(Map<String, String> paramMap);

	List<Map<String, Object>> listFirstSystem(Map<String, String> paramMap);



}
