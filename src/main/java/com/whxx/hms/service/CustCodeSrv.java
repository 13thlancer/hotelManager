package com.whxx.hms.service;

import java.util.List;
import java.util.Map;

import com.whxx.hms.model.ConsumeCode;
import com.whxx.hms.model.MarketCode;
import com.whxx.hms.model.PayCode;
import com.whxx.hms.model.PriceCode;
import com.whxx.hms.model.RsvMan;
import com.whxx.hms.model.SrcCode;

public interface CustCodeSrv {

	List<Map<String, Object>> listConsumeCode(Map<String, String> paramMap);

	void updateConsumeCode(ConsumeCode consumeCode);

	ConsumeCode selectConsumeCodeByName(Map<String, String> paramMap);

	List<Map<String, Object>> listPayCode(Map<String, String> paramMap);

	PayCode selectPayCodeByName(Map<String, String> paramMap);

	String isPayCodeConfigCode(String payCodeCode);

	void insertPayCode(PayCode setCreateAndUpdate);

	void updatePayCode(PayCode payCode);

	void deletePayCodeById(String payCodeId);

	String isPayCodeSeq(String seq);

	List<Map<String, Object>> listMarketCode(Map<String, String> paramMap);

	MarketCode selectMarketCodeByName(Map<String, String> paramMap);

	String isMarketCodeSeq(String seq);

	void insertMarketCode(MarketCode setCreateAndUpdate);

	void updateMarketCode(MarketCode marketCode);

	void deleteMarketCodeById(String marketCodeId);

	List<Map<String, Object>> listSrcCode(Map<String, String> paramMap);

	SrcCode selectSrcCodeByName(String name);

	String isSrcsCodeSeq(String seq);

	String isSrcCodeConfigCode(String srcCodeCode);

	void insertSrcCode(SrcCode setCreateAndUpdate);

	void updateSrcCode(SrcCode srcCode);

	void deletSrcCodeById(String srcCodeId);

	SrcCode selectSrcCodeByName(Map<String, String> paramMap);

	List<Map<String, Object>> listPriceCode(Map<String, String> paramMap);

	PriceCode selectPriceCodeByName(Map<String, String> paramMap);

	void insertPriceCode(PriceCode setCreateAndUpdate);

	void updatePriceCode(PriceCode priceCode);

	void deletePriceCodeById(String priceCodeId);

	String isPriceCodeConfigCode(String priceCodeCode);

	List<Map<String, Object>> listRsvMan(Map<String, String> paramMap);

	RsvMan selectRsvManByName(Map<String, String> paramMap);

	String isDistinctNo(String memberNo);

	void insertRsvMan(RsvMan setCreateAndUpdate);

	void updateRsvMan(RsvMan rsvMan);

	void deleteRsvMan(String rsvManId);

}
