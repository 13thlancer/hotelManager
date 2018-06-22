package com.whxx.hms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whxx.hms.dao.HotelMapper;
import com.whxx.hms.model.TbHotel;
import com.whxx.hms.service.HotelSrv;

@Service
public class HotelSrvImpl implements HotelSrv{

	@Autowired
	private HotelMapper hotelMapper;

	@Override
	public TbHotel selectByHotelCode(String hotelCode) {
		// TODO Auto-generated method stub
		return this.hotelMapper.selectByHotelCode(hotelCode);
	}

	@Override
	public List selectHotelDropdown(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return hotelMapper.selectHotelDropdown(param);
	}
}
