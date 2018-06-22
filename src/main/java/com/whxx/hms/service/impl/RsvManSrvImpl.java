package com.whxx.hms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.whxx.hms.dao.RsvManMapper;
import com.whxx.hms.model.dto.DropdownDto;
import com.whxx.hms.model.dto.HotelGroupId;
import com.whxx.hms.service.RsvManSrv;

@Service
@Transactional
public class RsvManSrvImpl implements RsvManSrv {

	@Autowired
	private RsvManMapper rsvManMapper;
	
	@Override
	public List<DropdownDto> selectGroupDropList(HotelGroupId hotel) {
		// TODO Auto-generated method stub
		return rsvManMapper.selectGroupDropList(hotel);
	}

}
