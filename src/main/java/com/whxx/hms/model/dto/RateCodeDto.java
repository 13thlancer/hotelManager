package com.whxx.hms.model.dto;

import java.math.BigDecimal;

/**
 * 房价码下拉框
 * @ClassName: RateCodeDto 
 * @Description: TODO
 * @author: zengxin
 * @date: 2018年6月7日 上午10:04:45
 */
public class RateCodeDto {
	/**
     *  房价码code
     */
    private String id ;
    
    /**
     *  房价码名称
     */
    private String text ;
    
    /**
     *  首日房价
     */
    private BigDecimal dayPrice ;
    
    /**
     *  平均房价
     */
    private BigDecimal avgPrice ;
    
	/**
     *  总房价
     */
    private BigDecimal totalPrice ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public BigDecimal getDayPrice() {
		return dayPrice;
	}

	public void setDayPrice(BigDecimal dayPrice) {
		this.dayPrice = dayPrice;
	}

	public BigDecimal getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(BigDecimal avgPrice) {
		this.avgPrice = avgPrice;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
    
}
