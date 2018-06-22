package com.whxx.hms.service;

import java.util.List;
import java.util.Map;

import com.whxx.hms.model.TbHotel;

public interface HotelSrv {

	TbHotel selectByHotelCode(String hotelCode);

	List selectHotelDropdown(Map<String, Object> param);

}
