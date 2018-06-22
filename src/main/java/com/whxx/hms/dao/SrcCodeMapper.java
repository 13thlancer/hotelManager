package com.whxx.hms.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.whxx.hms.model.ConsumeCode;
import com.whxx.hms.model.PayCode;
import com.whxx.hms.model.SrcCode;

@Repository
public interface SrcCodeMapper {

	List<Map<String, Object>> listSrcCode(Map<String, String> paramMap);

	SrcCode selectSrcCodeByName(Map<String, String> paramMap);

	String isSrcCodeConfigCode(String srcCodeCode);

	void insertSrcCode(SrcCode srcCode);

	void updateSrcCode(SrcCode srcCode);

	void deleteSrcCodeById(String srcCodeId);

	String isSrcCodeSeq(String seq);

}