package com.whxx.hms.model.dto;

import java.math.BigDecimal;

/**
 * 房型下拉框
 * @ClassName: HotelGroupId 
 * @Description: TODO
 * @author: zengxin
 * @date: 2018年6月7日 上午10:04:45
 */
public class RoomTypeDto {
	/**
     *  房型码
     */
    private String id ;
    
    /**
     *  房型名称
     */
    private String text ;
    
    /**
     *  空净
     */
    private Integer vacantClean ;
    
    /**
     *  空脏
     */
    private Integer vacantDirty ;
    
	/**
     *  可用房数量
     */
    private Integer availNum ;
    
    /**
     *  超预留数量
     */
    private Integer overNum ;
    
    /**
     *  门市价
     */
    private BigDecimal msj ;
    
    
	public BigDecimal getMsj() {
		return msj;
	}

	public void setMsj(BigDecimal msj) {
		this.msj = msj;
	}

	public Integer getVacantClean() {
		return vacantClean;
	}

	public void setVacantClean(Integer vacantClean) {
		this.vacantClean = vacantClean;
	}

	public Integer getVacantDirty() {
		return vacantDirty;
	}

	public void setVacantDirty(Integer vacantDirty) {
		this.vacantDirty = vacantDirty;
	}

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

	public Integer getAvailNum() {
		return availNum;
	}

	public void setAvailNum(Integer availNum) {
		this.availNum = availNum;
	}

	public Integer getOverNum() {
		return overNum;
	}

	public void setOverNum(Integer overNum) {
		this.overNum = overNum;
	}

}
