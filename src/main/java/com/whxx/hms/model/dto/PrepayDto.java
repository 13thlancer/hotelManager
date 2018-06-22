package com.whxx.hms.model.dto;

import java.math.BigDecimal;

/**
 * 新增预收款信息
 * @ClassName: PrepayInfo 
 * @Description: TODO
 * @author: zengxin
 * @date: 2018年5月31日 上午9:33:58
 */
public class PrepayDto {

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
	 * 付款金额
	 */
	private BigDecimal prepayMoney;

	/**
	 * 支付代码code_transaction.code
	 */
	private String payCode;
	
	/**
	 * 支付代码code_transaction.code
	 */
	private String payCodeName;
	
	/**
	 *订单号
	 */
	private String mchOrderNo;

	/**
	 * 交易单号code_transaction.code
	 */
	private String transactionNo;

	/**
	 * 付款人
	 */
	private String rsvMan;

	/**
	 * 备注
	 */
	private String remark;

	
	public String getPayCodeName() {
		return payCodeName;
	}

	public void setPayCodeName(String payCodeName) {
		this.payCodeName = payCodeName;
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

	public String getPriMainId() {
		return priMainId;
	}

	public void setPriMainId(String priMainId) {
		this.priMainId = priMainId;
	}

	public String getMchOrderNo() {
		return mchOrderNo;
	}

	public void setMchOrderNo(String mchOrderNo) {
		this.mchOrderNo = mchOrderNo;
	}

	public BigDecimal getPrepayMoney() {
		return prepayMoney;
	}

	public void setPrepayMoney(BigDecimal prepayMoney) {
		this.prepayMoney = prepayMoney;
	}

	public String getPayCode() {
		return payCode;
	}

	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}

	public String getTransactionNo() {
		return transactionNo;
	}

	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}

	public String getRsvMan() {
		return rsvMan;
	}

	public void setRsvMan(String rsvMan) {
		this.rsvMan = rsvMan;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
		
}
