package com.whxx.hms.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.whxx.hms.model.TbHotel;



@Repository
public interface HotelMapper {

	TbHotel selectByHotelCode(String hotelCode);

	List selectHotelDropdown(Map<String, Object> param);

}
