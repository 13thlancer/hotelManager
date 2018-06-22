package com.whxx.hms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whxx.hms.dao.BuildingMapper;
import com.whxx.hms.dao.FloorMapper;
import com.whxx.hms.dao.RoomTypeMapper;
import com.whxx.hms.dao.Tbpp01Mapper;
import com.whxx.hms.dao.Tbpp01aMapper;
import com.whxx.hms.model.Building;
import com.whxx.hms.model.Floor;
import com.whxx.hms.model.RoomType;
import com.whxx.hms.model.Tbpp01a;
import com.whxx.hms.service.PubCodeSrv;

@Service
public class PubCodeSrvImpl implements PubCodeSrv{
	
	@Autowired
	private Tbpp01Mapper tbpp01Mapper;
	
	@Autowired
	private Tbpp01aMapper tbpp01aMapper;
		
	@Override
	public List listFirstConfig(Map<String, String> paramMap) {
		List list=this.tbpp01Mapper.listFirstConfig(paramMap);
		return list;
	}



	@Override
	public List listSecond(Map<String, String> paramMap) {
		List list=this.tbpp01aMapper.listSecond(paramMap);
		return list;
	}



	@Override
	public void insertSecondCode(Tbpp01a tbpp01a) {
		this.tbpp01aMapper.insertSecondCode(tbpp01a);
	}



	@Override
	public Tbpp01a selectSecondCodeByName(Map<String, String> paramMap) {
		Tbpp01a Tbpp01a=this.tbpp01aMapper.selectSecondCodeByName(paramMap);
		return Tbpp01a;
	}



	@Override
	public void updateSecondCode(Tbpp01a tbpp01a) {
		this.tbpp01aMapper.updateSecondCode(tbpp01a);
	}



	@Override
	public void deleteSecondCodeById(String tbpp01aId) {
		this.tbpp01aMapper.deleteSecondCodeById(tbpp01aId);
		
	}



	@Override
	public List<Map<String, Object>> listFirstSystem(Map<String, String> paramMap) {
		List<Map<String, Object>> list=this.tbpp01Mapper.listFirstSystem(paramMap);
		return list;
	}



	@Override
	public String isFirstSeq(String tbpp01Id, String seq) {
		return this.tbpp01aMapper.isFirstSeq(tbpp01Id,seq);
	}



	@Override
	public String isFirstConfigCode(String tbpp01Id, String configCode) {
		return this.tbpp01aMapper.isFirstConfigCode(tbpp01Id,configCode);
	}


}
