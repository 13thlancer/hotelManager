package com.whxx.hms.model.dto;

/**
 * 入住信息
 * @ClassName: RsvInfo 
 * @Description: TODO
 * @author: zengxin
 * @date: 2018年5月31日 上午9:34:15
 */
public class RsvInfo {

	/**
	 * 预定人
	 */
	private String rsvMan;

	/**
	 * 入住人
	 */
	private String inPerson;

	/**
	 * 手机
	 */
	private String mobile;

	/**
	 * 到达时间
	 */
	private String planStart;
	/**
	 * 最晚保留
	 */
	private String keepTime;
	
	public String getRsvMan() {
		return rsvMan;
	}
	public void setRsvMan(String rsvMan) {
		this.rsvMan = rsvMan;
	}
	public String getInPerson() {
		return inPerson;
	}
	public void setInPerson(String inPerson) {
		this.inPerson = inPerson;
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
	public String getKeepTime() {
		return keepTime;
	}
	public void setKeepTime(String keepTime) {
		this.keepTime = keepTime;
	}
}
