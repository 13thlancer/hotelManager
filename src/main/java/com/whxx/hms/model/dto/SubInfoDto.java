package com.whxx.hms.model.dto;

import java.math.BigDecimal;

/**
 * 预订页面查询列表Dto
 * @ClassName: SubInfoDto 
 * @Description: TODO
 * @author: zengxin
 * @date: 2018年6月11日 下午2:10:57
 */
public class SubInfoDto {
	/**
	 * 主键
	 */
	private String subInfoId;

	/**
	 * 集团id
	 */
	private Integer hotelGroupId;

	/**
	 * 酒店id
	 */
	private Integer hotelId;

	/**
	 * 子订单号规则 YYMMDD00001开始累加，每天前缀都会变化
	 */
	private String subId;

	/**
	 * 订单状态： R=预订 I=在住 C=取消 O=结帐退房 N=noshow(当日将到过滤此项目)
	 */
	private String mainStatus;
	
	/**
	 * 入住类型:  F=全天房  H=钟点房(钟点房只能直接入住)  L=长包房',
	 */
	private String   inType;

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
	 * 房价
	 */
	private String dayPrice;

	/**
	 * 是否排房, Y是，N否
	 */
	private String ifArrange;

	/**
	 * 房间数量
	 */
	private Integer roomNum;

	/**
	 * 计划入住时间
	 */
	private String planStart;

	/**
	 * 计划退房时间
	 */
	private String planEnd;

	/**
	 * 入住天数
	 */
	private Integer days;

	/**
	 * 余额
	 */
	private BigDecimal charge;

	/**
	 * 信用
	 */
	private BigDecimal credit;

	/**
	 * 备注
	 */
	private String remark;

	
	public String getInType() {
		return inType;
	}

	public void setInType(String inType) {
		this.inType = inType;
	}

	public String getSubInfoId() {
		return subInfoId;
	}

	public void setSubInfoId(String subInfoId) {
		this.subInfoId = subInfoId;
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

	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

	public String getMainStatus() {
		return mainStatus;
	}

	public void setMainStatus(String mainStatus) {
		this.mainStatus = mainStatus;
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

	public String getDayPrice() {
		return dayPrice;
	}

	public void setDayPrice(String dayPrice) {
		this.dayPrice = dayPrice;
	}

	public String getIfArrange() {
		return ifArrange;
	}

	public void setIfArrange(String ifArrange) {
		this.ifArrange = ifArrange;
	}

	public Integer getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(Integer roomNum) {
		this.roomNum = roomNum;
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

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public BigDecimal getCharge() {
		return charge;
	}

	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}

	public BigDecimal getCredit() {
		return credit;
	}

	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}