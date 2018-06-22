package com.whxx.hms.model.dto;

import java.math.BigDecimal;

/**
 * 展示预收款信息
 * @ClassName: PrepayInfo 
 * @Description: TODO
 * @author: zengxin
 * @date: 2018年5月31日 上午9:33:58
 */
public class PrepayInfo {

	/**
	 * 预订单号
	 */
	private String priMainId;
	
    /**
     * 预收款笔数
     */
    private Integer prepayCount;
    
	/**
	 * 预收款总金额
	 */
	private BigDecimal totalPrepay;
	
	
	/**
     * 预授权笔数
     */
    private Integer creditCount;
    
	/**
	 * 预授权总金额
	 */
	private BigDecimal totalCredit;
	
	/**
	 * 当前余额
	 */
	private BigDecimal curBalance;
	
	
	/**
	 * 当前信用
	 */
	private BigDecimal curCredit;
	
	
	/**
	 * 当前总账
	 */
	private BigDecimal curTotal;
	
	
	/**
	 * 联单余额
	 */
	private BigDecimal unionBalance;
	
	
	/**
	 * 联单信用
	 */
	private BigDecimal unionCredit;
	
	
	/**
	 * 联单总账
	 */
	private BigDecimal unionTotal;


	public String getPriMainId() {
		return priMainId;
	}


	public void setPriMainId(String priMainId) {
		this.priMainId = priMainId;
	}

	public Integer getPrepayCount() {
		return prepayCount;
	}


	public void setPrepayCount(Integer prepayCount) {
		this.prepayCount = prepayCount;
	}


	public BigDecimal getTotalPrepay() {
		return totalPrepay;
	}


	public void setTotalPrepay(BigDecimal totalPrepay) {
		this.totalPrepay = totalPrepay;
	}


	public Integer getCreditCount() {
		return creditCount;
	}


	public void setCreditCount(Integer creditCount) {
		this.creditCount = creditCount;
	}


	public BigDecimal getTotalCredit() {
		return totalCredit;
	}


	public void setTotalCredit(BigDecimal totalCredit) {
		this.totalCredit = totalCredit;
	}


	public BigDecimal getCurBalance() {
		return curBalance;
	}


	public void setCurBalance(BigDecimal curBalance) {
		this.curBalance = curBalance;
	}


	public BigDecimal getCurCredit() {
		return curCredit;
	}


	public void setCurCredit(BigDecimal curCredit) {
		this.curCredit = curCredit;
	}


	public BigDecimal getCurTotal() {
		return curTotal;
	}


	public void setCurTotal(BigDecimal curTotal) {
		this.curTotal = curTotal;
	}


	public BigDecimal getUnionBalance() {
		return unionBalance;
	}


	public void setUnionBalance(BigDecimal unionBalance) {
		this.unionBalance = unionBalance;
	}


	public BigDecimal getUnionCredit() {
		return unionCredit;
	}


	public void setUnionCredit(BigDecimal unionCredit) {
		this.unionCredit = unionCredit;
	}


	public BigDecimal getUnionTotal() {
		return unionTotal;
	}


	public void setUnionTotal(BigDecimal unionTotal) {
		this.unionTotal = unionTotal;
	}
	
}
