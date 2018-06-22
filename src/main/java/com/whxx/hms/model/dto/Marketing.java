package com.whxx.hms.model.dto;

/**
 * 市场销售
 * @ClassName: Marketing 
 * @Description: TODO
 * @author: zengxin
 * @date: 2018年5月31日 上午9:33:23
 */
public class Marketing {
	
		/**
		 * 销售人员sales_man.code
		 */
		private String salesman;
		
		/**
		 * 销售人员sales_man.code
		 */
		private String salesmanName;
		
		/**
		 * 佣金码
		 */
		private String commissionCode;
		

		/**
		 * 佣金码
		 */
		private String commissionCodeName;
	
		/**
		 * 渠道code_base.channel.code
		 */
		private String channel;
		
		/**
		 * 渠道code_base.channel.code
		 */
		private String channelName;
	
		/**
		 * 支付代码code_transaction.code
		 */
		private String payCode;
		
		/**
		 * 支付代码code_transaction.code
		 */
		private String payCodeName;
		
	
		public String getSalesmanName() {
			return salesmanName;
		}

		public void setSalesmanName(String salesmanName) {
			this.salesmanName = salesmanName;
		}

		public String getCommissionCodeName() {
			return commissionCodeName;
		}

		public void setCommissionCodeName(String commissionCodeName) {
			this.commissionCodeName = commissionCodeName;
		}

		public String getChannelName() {
			return channelName;
		}

		public void setChannelName(String channelName) {
			this.channelName = channelName;
		}

		public String getPayCodeName() {
			return payCodeName;
		}

		public void setPayCodeName(String payCodeName) {
			this.payCodeName = payCodeName;
		}

		public String getSalesman() {
			return salesman;
		}

		public void setSalesman(String salesman) {
			this.salesman = salesman;
		}

		public String getCommissionCode() {
			return commissionCode;
		}

		public void setCommissionCode(String commissionCode) {
			this.commissionCode = commissionCode;
		}

		public String getChannel() {
			return channel;
		}

		public void setChannel(String channel) {
			this.channel = channel;
		}

		public String getPayCode() {
			return payCode;
		}

		public void setPayCode(String payCode) {
			this.payCode = payCode;
		}

}
