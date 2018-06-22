package com.whxx.hms.model.dto;

public class RoomDto {

	/**
	 * 子订单号
	 */
	private String subId;
    /**
     *   房型代码
     */
    private String roomTypeCode;

    /**
    *   房型名称
     */
    private String roomTypeName;

    /**
    *   房号
     */
    private String code;

    /**
    *   标记
     */
    private String sign;

    /**
    *   特征
     */
    private String roomSpec;
    
    /**
     *   是否干净
      */
     private String cleanStatus;

     /**
     *   是否入住
      */
     private String inFlag;
     
	public String getCleanStatus() {
		return cleanStatus;
	}

	public void setCleanStatus(String cleanStatus) {
		this.cleanStatus = cleanStatus;
	}

	public String getInFlag() {
		return inFlag;
	}

	public void setInFlag(String inFlag) {
		this.inFlag = inFlag;
	}

	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

	public String getRoomTypeCode() {
		return roomTypeCode;
	}

	public void setRoomTypeCode(String roomTypeCode) {
		this.roomTypeCode = roomTypeCode;
	}

	public String getRoomTypeName() {
		return roomTypeName;
	}

	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getRoomSpec() {
		return roomSpec;
	}

	public void setRoomSpec(String roomSpec) {
		this.roomSpec = roomSpec;
	}

}