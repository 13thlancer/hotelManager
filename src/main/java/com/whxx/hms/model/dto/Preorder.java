package com.whxx.hms.model.dto;

import java.math.BigDecimal;

/**
 * 预订信息
 * @ClassName: Preorder 
 * @Description: TODO
 * @author: zengxin
 * @date: 2018年5月31日 上午9:33:47
 */
public class Preorder {
	
	/**
     *  主键Id
     */
    private String mainInfoId ;
    
    /**
     *  预订单Id
     */
    private String priMainId ;

	
	/**
     *   集团id
     */
    private Integer hotelGroupId;

    /**
     *   酒店id
     */
    private Integer hotelId;
    
	/**
	 * 预订类型
	 */
	private String rsvClass;
	
	/**
	 * 预订类型
	 */
	private String rsvClassName;
	
	/**
	 * 组团单位下拉框
	 */
	private String groupNo;
	
	/**
	 * 组团单位下拉框
	 */
	private String groupNoName;
	
	/**
	 * 首日房价
	 */
	private BigDecimal dayPrice;
	
	/**
	 * 优惠理由
	 */
	private String benefitReason;
	
	/**
	 * 优惠理由
	 */
	private String benefitReasonName;

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
	 * 房型代码 room_type.code
	 */
	private String roomType;
	
	/**
	 * 房型代码 room_type.code
	 */
	private String roomTypeName;
	/**
	 * 房间数量
	 */
	private Integer roomNum;
	
   /**
     *   房价码类别
     */
   private String ratecodeCategory;
   
   /**
    *   房价码类别
    */
  private String ratecodeCategoryName;

	/**
	 * 房价码code_ratecode.code
	 */
	private String ratecode;
	
	/**
	 * 房价码code_ratecode.code
	 */
	private String ratecodeName;

	/**
	 * 包价：无早，单早，双早等信息，可以收工修改
	 */
	private String packages;
	
	/**
	 * 包价：无早，单早，双早等信息，可以收工修改
	 */
	private String packagesName;
	
	
	public String getRsvClassName() {
		return rsvClassName;
	}

	public void setRsvClassName(String rsvClassName) {
		this.rsvClassName = rsvClassName;
	}

	public String getGroupNoName() {
		return groupNoName;
	}

	public void setGroupNoName(String groupNoName) {
		this.groupNoName = groupNoName;
	}

	public String getBenefitReasonName() {
		return benefitReasonName;
	}

	public void setBenefitReasonName(String benefitReasonName) {
		this.benefitReasonName = benefitReasonName;
	}

	public String getRoomTypeName() {
		return roomTypeName;
	}

	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}

	public String getRatecodeCategoryName() {
		return ratecodeCategoryName;
	}

	public void setRatecodeCategoryName(String ratecodeCategoryName) {
		this.ratecodeCategoryName = ratecodeCategoryName;
	}

	public String getRatecodeName() {
		return ratecodeName;
	}

	public void setRatecodeName(String ratecodeName) {
		this.ratecodeName = ratecodeName;
	}

	public String getPackagesName() {
		return packagesName;
	}

	public void setPackagesName(String packagesName) {
		this.packagesName = packagesName;
	}

	public String getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

	public String getBenefitReason() {
		return benefitReason;
	}

	public void setBenefitReason(String benefitReason) {
		this.benefitReason = benefitReason;
	}

	public String getPriMainId() {
		return priMainId;
	}

	public void setPriMainId(String priMainId) {
		this.priMainId = priMainId;
	}

	public String getMainInfoId() {
		return mainInfoId;
	}

	public void setMainInfoId(String mainInfoId) {
		this.mainInfoId = mainInfoId;
	}

	public BigDecimal getDayPrice() {
		return dayPrice;
	}

	public void setDayPrice(BigDecimal dayPrice) {
		this.dayPrice = dayPrice;
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

	public String getRatecodeCategory() {
		return ratecodeCategory;
	}

	public void setRatecodeCategory(String ratecodeCategory) {
		this.ratecodeCategory = ratecodeCategory;
	}

	public String getRsvClass() {
		return rsvClass;
	}

	public void setRsvClass(String rsvClass) {
		this.rsvClass = rsvClass;
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

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public Integer getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(Integer roomNum) {
		this.roomNum = roomNum;
	}

	public String getRatecode() {
		return ratecode;
	}

	public void setRatecode(String ratecode) {
		this.ratecode = ratecode;
	}

	public String getPackages() {
		return packages;
	}

	public void setPackages(String packages) {
		this.packages = packages;
	}
	
}
