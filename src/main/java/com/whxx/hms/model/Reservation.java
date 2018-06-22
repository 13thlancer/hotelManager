package com.whxx.hms.model;

import java.util.List;

import com.whxx.hms.model.dto.Marketing;
import com.whxx.hms.model.dto.Preorder;
import com.whxx.hms.model.dto.PrepayInfo;
import com.whxx.hms.model.dto.RsvInfo;

/**
 * 预订单记录
 * @ClassName: Reservation 
 * @Description: TODO
 * @author: zengxin
 * @date: 2018年5月31日 上午9:34:52
 */
public class Reservation {

	/**
	 * 预定信息
	 */
	private List<Preorder> preorderList;
	
	/**
	 * 住客信息
	 */
	private RsvInfo  rsvInfo;
	
	/**
	 * 市场销售
	 */
	private Marketing marketing;
	
	/**
	 * 预付款信息展示
	 */
	private PrepayInfo  prepayInfo;
	

	public PrepayInfo getPrepayInfo() {
		return prepayInfo;
	}

	public void setPrepayInfo(PrepayInfo prepayInfo) {
		this.prepayInfo = prepayInfo;
	}

	public List<Preorder> getPreorderList() {
		return preorderList;
	}

	public void setPreorderList(List<Preorder> preorderList) {
		this.preorderList = preorderList;
	}

	public RsvInfo getRsvInfo() {
		return rsvInfo;
	}

	public void setRsvInfo(RsvInfo rsvInfo) {
		this.rsvInfo = rsvInfo;
	}

	public Marketing getMarketing() {
		return marketing;
	}

	public void setMarketing(Marketing marketing) {
		this.marketing = marketing;
	}

}
