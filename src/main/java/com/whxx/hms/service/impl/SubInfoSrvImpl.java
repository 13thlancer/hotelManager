package com.whxx.hms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.whxx.core.utils.ToolUtil;
import com.whxx.core.utils.UUIDUtil;
import com.whxx.hms.dao.SubInfoMapper;
import com.whxx.hms.model.SubInfo;
import com.whxx.hms.model.dto.SubInfoDto;
import com.whxx.hms.service.SubInfoSrv;

@Service
@Transactional
public class SubInfoSrvImpl implements SubInfoSrv{

	
	@Autowired
	private SubInfoMapper subInfoMapper;
	
	@Override
	public void insertSubInfo(SubInfo subInfo) {
		subInfo.setSubInfoId(UUIDUtil.getUUID());
		subInfo.setLineNo(ToolUtil.getLineNo());
		subInfoMapper.insertSubInfo(subInfo);
	}

	@Override
	public void updateSubInfoById(SubInfo subInfo) {
		// TODO Auto-generated method stub
		subInfoMapper.updateSubInfoById(subInfo);
	}

	@Override
	public List<SubInfoDto> batchOrderList(String priMainId) {
		// TODO Auto-generated method stub
		return this.subInfoMapper.batchOrderList(priMainId);
	}

}
