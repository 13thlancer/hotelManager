package com.whxx.hms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whxx.hms.dao.ConsumeCodeMapper;
import com.whxx.hms.dao.MarketCodeMapper;
import com.whxx.hms.dao.PayCodeMapper;
import com.whxx.hms.dao.PriceCodeMapper;
import com.whxx.hms.dao.RsvManMapper;
import com.whxx.hms.dao.SrcCodeMapper;
import com.whxx.hms.model.ConsumeCode;
import com.whxx.hms.model.MarketCode;
import com.whxx.hms.model.PayCode;
import com.whxx.hms.model.PriceCode;
import com.whxx.hms.model.RsvMan;
import com.whxx.hms.model.SrcCode;
import com.whxx.hms.service.CustCodeSrv;

@Service
public class CustCodeSrvImpl implements CustCodeSrv{
	
	@Autowired
	private ConsumeCodeMapper consumeCodeMapper;
	
	@Autowired
	private PayCodeMapper payCodeMapper;
	
	@Autowired
	private MarketCodeMapper marketCodeMapper;
	
	@Autowired
	private SrcCodeMapper srcCodeMapper;
	
	@Autowired
	private PriceCodeMapper priceCodeMapper;
	
	@Autowired
	private RsvManMapper rsvManMapper;
	
	@Override
	public List<Map<String, Object>> listConsumeCode(Map<String, String> paramMap) {
		
		return this.consumeCodeMapper.listConsumeCode(paramMap);
	}

	@Override
	public void updateConsumeCode(ConsumeCode consumeCode) {
		this.consumeCodeMapper.updateConsumeCode(consumeCode);
		
	}

	@Override
	public ConsumeCode selectConsumeCodeByName(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return this.consumeCodeMapper.selectConsumeCodeByName(paramMap);
	}

	@Override
	public List<Map<String, Object>> listPayCode(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return this.payCodeMapper.listPayCode(paramMap);
	}

	@Override
	public PayCode selectPayCodeByName(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return this.payCodeMapper.selectPayCodeByName( paramMap);
	}

	@Override
	public String isPayCodeConfigCode(String payCodeCode) {
		// TODO Auto-generated method stub
		return this.payCodeMapper.isPayCodeConfigCode(payCodeCode);
	}

	@Override
	public void insertPayCode(PayCode payCode) {
		this.payCodeMapper.insertPayCode(payCode);
		
	}

	@Override
	public void updatePayCode(PayCode payCode) {
		this.payCodeMapper.updatePayCode(payCode);
		
	}

	@Override
	public void deletePayCodeById(String payCodeId) {
		this.payCodeMapper.deletePayCodeById(payCodeId);
		
	}

	@Override
	public String isPayCodeSeq(String seq) {
		// TODO Auto-generated method stub
		return this.payCodeMapper.isPayCodeSeq(seq);
	}

	@Override
	public List<Map<String, Object>> listMarketCode(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return this.marketCodeMapper.listMarketCode(paramMap);
	}

	@Override
	public MarketCode selectMarketCodeByName(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return this.marketCodeMapper.selectMarketCodeByName( paramMap);
	}

	@Override
	public String isMarketCodeSeq(String seq) {
		// TODO Auto-generated method stub
		return this.marketCodeMapper.isMarketCodeSeq(seq);
	}

	@Override
	public void insertMarketCode(MarketCode marketCode) {
		// TODO Auto-generated method stub
		this.marketCodeMapper.insertMarketCode(marketCode);
	}

	@Override
	public void updateMarketCode(MarketCode marketCode) {
		// TODO Auto-generated method stub
		this.marketCodeMapper.updateMarketCode(marketCode);
	}

	@Override
	public void deleteMarketCodeById(String marketCodeId) {
		// TODO Auto-generated method stub
		this.marketCodeMapper.deleteMarketCodeById(marketCodeId);
	}

	@Override
	public List<Map<String, Object>> listSrcCode(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return this.srcCodeMapper.listSrcCode(paramMap);
	}

	@Override
	public SrcCode selectSrcCodeByName(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return this.srcCodeMapper.selectSrcCodeByName( paramMap);
	}

	@Override
	public String isSrcsCodeSeq(String seq) {
		// TODO Auto-generated method stub
		return this.srcCodeMapper.isSrcCodeSeq(seq);
	}

	@Override
	public String isSrcCodeConfigCode(String srcCodeCode) {
		// TODO Auto-generated method stub
		return this.srcCodeMapper.isSrcCodeConfigCode(srcCodeCode);
	}

	@Override
	public void insertSrcCode(SrcCode srcCode) {
		// TODO Auto-generated method stub
		this.srcCodeMapper.insertSrcCode(srcCode);
	}

	@Override
	public void updateSrcCode(SrcCode srcCode) {
		// TODO Auto-generated method stub
		this.srcCodeMapper.updateSrcCode(srcCode);
	}

	@Override
	public void deletSrcCodeById(String srcCodeId) {
		// TODO Auto-generated method stub
		this.srcCodeMapper.deleteSrcCodeById(srcCodeId);
	}

	@Override
	public SrcCode selectSrcCodeByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> listPriceCode(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return this.priceCodeMapper.listPriceCode(paramMap);
	}

	@Override
	public PriceCode selectPriceCodeByName(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return this.priceCodeMapper.selectPriceCodeByName(paramMap);
	}

	@Override
	public void insertPriceCode(PriceCode priceCode) {
		// TODO Auto-generated method stub
		this.priceCodeMapper.insertPriceCode(priceCode);
	}

	@Override
	public void updatePriceCode(PriceCode priceCode) {
		// TODO Auto-generated method stub
		this.priceCodeMapper.updatePriceCode(priceCode);
	}

	@Override
	public void deletePriceCodeById(String priceCodeId) {
		// TODO Auto-generated method stub
		this.priceCodeMapper.deletePriceCodeById(priceCodeId);
	}

	@Override
	public String isPriceCodeConfigCode(String priceCodeCode) {
		// TODO Auto-generated method stub
		return this.priceCodeMapper.isPriceCodeConfigCode(priceCodeCode);
	}

	@Override
	public List<Map<String, Object>> listRsvMan(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return this.rsvManMapper.listRsvMan(paramMap);
	}

	@Override
	public RsvMan selectRsvManByName(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return this.rsvManMapper.selectRsvManByName(paramMap);
	}

	@Override
	public String isDistinctNo(String memberNo) {
		// TODO Auto-generated method stub
		return this.rsvManMapper.isDistinctNo(memberNo);
	}

	@Override
	public void insertRsvMan(RsvMan rsvMan) {
		// TODO Auto-generated method stub
		this.rsvManMapper.insertRsvMan(rsvMan);
	}

	@Override
	public void updateRsvMan(RsvMan rsvMan) {
		// TODO Auto-generated method stub
		this.rsvManMapper.updateBuilding(rsvMan);
	}

	@Override
	public void deleteRsvMan(String rsvManId) {
		// TODO Auto-generated method stub
		this.rsvManMapper.deleteRsvManById(rsvManId);
	}

}
