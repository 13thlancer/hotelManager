package com.whxx.hms.model.dto;

/**
 * 留言
 * @ClassName: OtherInfo 
 * @Description: TODO
 * @author: zengxin
 * @date: 2018年5月30日 下午2:31:57
 */
public class MessageDto {
	
		/**
		 * 子订单Id
		 */
		private String subId;
	
		/**
		 * 主键
		 */
		private String messagesId;
	
		/**
		*   
		 */
		private Integer hotelGroupId;
	
		/**
		*   
		 */
		private Integer hotelId;
	
		/**
		 * 开始时间
		 */
		private String startTime;
	
		/**
		 * 结束时间
		 */
		private String endTime;
	
		/**
		 * 房号
		 */
		private String roomCode;
	
		/**
		 * 姓名
		 */
		private String person;
		
		/**
		 * 备注
		 */
		private String remark;
		
		/**
		 * 创建时间
		 */
		private String createDate;
		
		public String getCreateDate() {
			return createDate;
		}

		public void setCreateDate(String createDate) {
			this.createDate = createDate;
		}

		public String getSubId() {
			return subId;
		}

		public void setSubId(String subId) {
			this.subId = subId;
		}

		public String getRoomCode() {
			return roomCode;
		}

		public void setRoomCode(String roomCode) {
			this.roomCode = roomCode;
		}

		public String getPerson() {
			return person;
		}

		public void setPerson(String person) {
			this.person = person;
		}

		public String getMessagesId() {
			return messagesId;
		}

		public void setMessagesId(String messagesId) {
			this.messagesId = messagesId;
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

		public String getStartTime() {
			return startTime;
		}

		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}

		public String getEndTime() {
			return endTime;
		}

		public void setEndTime(String endTime) {
			this.endTime = endTime;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

}
