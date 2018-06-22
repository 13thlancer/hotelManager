package com.whxx.hms.service;

import java.util.List;
import java.util.Map;

import com.whxx.hms.model.Building;
import com.whxx.hms.model.Floor;
import com.whxx.hms.model.RoomType;
import com.whxx.hms.model.Tbpp01a;

public interface PubCodeSrv {

	List listFirstConfig(Map<String, String> paramMap);

	List listSecond(Map<String, String> paramMap);

	void insertSecondCode(Tbpp01a setCreateAndUpdate);

	Tbpp01a selectSecondCodeByName(Map<String, String> paramMap);

	void updateSecondCode(Tbpp01a tbpp01a);

	void deleteSecondCodeById(String tbpp01aId);

	List<Map<String, Object>> listFirstSystem(Map<String, String> paramMap);

	String isFirstSeq(String tbpp01Id, String seq);

	String isFirstConfigCode(String tbpp01Id, String configCode);

}
