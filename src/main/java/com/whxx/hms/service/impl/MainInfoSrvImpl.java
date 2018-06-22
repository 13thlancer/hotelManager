package com.whxx.hms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.whxx.core.utils.ToolUtil;
import com.whxx.core.utils.UUIDUtil;
import com.whxx.hms.dao.MainInfoMapper;
import com.whxx.hms.model.MainInfo;
import com.whxx.hms.service.MainInfoSrv;

@Service
@Transactional
public class MainInfoSrvImpl  implements MainInfoSrv {
	
	
	@Autowired
	private MainInfoMapper mainInfoMapper;
	
	
	@Override
	public void insertMainInfo(MainInfo mainInfo) {
		mainInfo.setMainInfoId(UUIDUtil.getUUID());
		mainInfo.setLineNo(ToolUtil.getLineNo());
		mainInfoMapper.insertMainInfo(mainInfo);

	}

	@Override
	public void updateMainInfoById(MainInfo mainInfo) {
		// TODO Auto-generated method stub
		mainInfoMapper.updateMainInfoById(mainInfo);
	}

}
