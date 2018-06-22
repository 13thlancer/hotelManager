package com.whxx.hms.model.dto;

/**
 * 判断是否存在酒店Id
 * @ClassName: HotelGroupId 
 * @Description: TODO
 * @author: zengxin
 * @date: 2018年6月7日 上午10:04:45
 */
public class HotelGroupId {
	/**
     *  集团ID
     */
    private Integer hotelGroupId ;
    
    /**
     *  酒店Id
     */
    private Integer hotelId ;

	public Integer getHotelGroupId() {
		return hotelGroupId;
	}

	public void setHotelGroupId(Integer hotelGroupId) {
		this.hotelGroupId = hotelGroupId;
	}

	public Integer getHotelId() {
		return hotelId;
	}

	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}
    
}
