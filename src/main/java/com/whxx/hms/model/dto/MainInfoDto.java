package com.whxx.hms.model.dto;

import java.math.BigDecimal;

/**
 * 预订页面查询列表Dto
 * @ClassName: SubInfoDto 
 * @Description: TODO
 * @author: zengxin
 * @date: 2018年6月11日 下午2:10:57
 */
public class MainInfoDto {
	/**
	 * 主键
	 */
	private String subInfoId;
	
	/**
	 * 预订单号
	 */
	private String priMainId;

	/**
	 * 集团id
	 */
	private Integer hotelGroupId;

	/**
	 * 酒店id
	 */
	private Integer hotelId;

	/**
	 * 入住类型
	 */
	private String inType;

	/**
	 * 入住人名称
	 */
	private String inPerson;

	/**
	 * 房型代码 room_type.code
	 */
	private String roomType;

	/**
	 * 房号 room_no.code
	 */
	private String roomNo;
	
	/**
	 * 房间数
	 */
	private Integer roomNum;

	/**
	 * 房价
	 */
	private String dayPrice;
	
	/**
	 * 手机
	 */
	private String mobile;


	/**
	 * 计划入住时间
	 */
	private String planStart;

	/**
	 * 计划退房时间
	 */
	private String planEnd;
	
	/**
	 * 余额
	 */
	private BigDecimal balance;
	
	/**
	 * 保留时间
	 */
	private String keepTime;
	

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getSubInfoId() {
		return subInfoId;
	}

	public void setSubInfoId(String subInfoId) {
		this.subInfoId = subInfoId;
	}

	public String getPriMainId() {
		return priMainId;
	}

	public void setPriMainId(String priMainId) {
		this.priMainId = priMainId;
	}

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

	public String getInType() {
		return inType;
	}

	public void setInType(String inType) {
		this.inType = inType;
	}

	public String getInPerson() {
		return inPerson;
	}

	public void setInPerson(String inPerson) {
		this.inPerson = inPerson;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public Integer getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(Integer roomNum) {
		this.roomNum = roomNum;
	}

	public String getDayPrice() {
		return dayPrice;
	}

	public void setDayPrice(String dayPrice) {
		this.dayPrice = dayPrice;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPlanStart() {
		return planStart;
	}

	public void setPlanStart(String planStart) {
		this.planStart = planStart;
	}

	public String getPlanEnd() {
		return planEnd;
	}

	public void setPlanEnd(String planEnd) {
		this.planEnd = planEnd;
	}

	public String getKeepTime() {
		return keepTime;
	}

	public void setKeepTime(String keepTime) {
		this.keepTime = keepTime;
	}


}