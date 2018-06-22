package com.whxx.hms.model.dto;

import java.math.BigDecimal;

/**
 * 新增预授权
 * @ClassName: PreLicence 
 * @Description: TODO
 * @author: zengxin
 * @date: 2018年5月31日 上午9:33:36
 */
public class PreLicenceDto {
	
	/**
	 * 消费记录表Id
	 */
	private String consumeId;
	
	/**
	 * 转移到新的订单的明细ID
	 */
	private String newSubId;

	/**
	 * 预订单号
	 */
	private String priMainId;
	
	/**
     *   集团id
     */
    private Integer hotelGroupId;

    /**
     *   酒店id
     */
    private Integer hotelId;
    
	/**
	 * 预授权合计
	 */
	private BigDecimal creditMoney;

	/**
	 * 预授权支付代码
	 */
	private String creditPayCode;
	
	/**
	 * 预授权支付代码
	 */
	private String creditPayCodeName;

	/**
	 * 信用卡号
	 */
	private String creditNo;

	/**
	 * 预授权号
	 */
	private String creditPayNo;

	/**
	 * 付款人
	 */
	private String creditMan;
	
	/**
	 * 创建时间
	 */
	private String createDate;

	/**
	 * 创建人
	 */
	private String createEmp;
	
	public String getCreditPayCodeName() {
		return creditPayCodeName;
	}

	public void setCreditPayCodeName(String creditPayCodeName) {
		this.creditPayCodeName = creditPayCodeName;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateEmp() {
		return createEmp;
	}

	public void setCreateEmp(String createEmp) {
		this.createEmp = createEmp;
	}

	public String getNewSubId() {
		return newSubId;
	}

	public void setNewSubId(String newSubId) {
		this.newSubId = newSubId;
	}

	public String getConsumeId() {
		return consumeId;
	}

	public void setConsumeId(String consumeId) {
		this.consumeId = consumeId;
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

	public BigDecimal getCreditMoney() {
		return creditMoney;
	}

	public void setCreditMoney(BigDecimal creditMoney) {
		this.creditMoney = creditMoney;
	}

	public String getCreditPayCode() {
		return creditPayCode;
	}

	public void setCreditPayCode(String creditPayCode) {
		this.creditPayCode = creditPayCode;
	}

	public String getCreditNo() {
		return creditNo;
	}

	public void setCreditNo(String creditNo) {
		this.creditNo = creditNo;
	}

	public String getCreditPayNo() {
		return creditPayNo;
	}

	public void setCreditPayNo(String creditPayNo) {
		this.creditPayNo = creditPayNo;
	}

	public String getCreditMan() {
		return creditMan;
	}

	public void setCreditMan(String creditMan) {
		this.creditMan = creditMan;
	}
	
}
