package com.whxx.hms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.whxx.core.controller.BaseController;
import com.whxx.core.utils.ToolUtil;
import com.whxx.core.utils.UUIDUtil;
import com.whxx.hms.dao.HotelMapper;
import com.whxx.hms.dao.ModifyLogsMapper;
import com.whxx.hms.model.ModifyLogs;
import com.whxx.hms.model.TbHotel;
import com.whxx.hms.service.ModifyLogsSrv;

@Service
@Transactional
public class ModifyLogsSrvImpl extends BaseController implements ModifyLogsSrv {

	@Autowired
	private ModifyLogsMapper modifyLogsMapper;
	
	@Autowired
	private HotelMapper hotelMapper;
	
    @Value("${hotel.code}")
    private String hotelCode;
    
	@Override
	public void insertModifyLogs(ModifyLogs modifyLogs) {
		// TODO Auto-generated method stub
		//查询酒店集团Id和酒店Id
		TbHotel hotel = this.hotelMapper.selectByHotelCode(hotelCode);
		modifyLogs.setModifyLogsId(UUIDUtil.getUUID());
		modifyLogs.setLineNo(ToolUtil.getLineNo());
		modifyLogs.setHotelGroupId(hotel.getHotelGroupId());
		modifyLogs.setHotelId(hotel.getHotelId());
		this.modifyLogsMapper.insertModifyLogs(setCreateEmpAndTime(modifyLogs));
	}

}
