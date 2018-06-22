package com.whxx.hms.service;

import java.util.Map;

@SuppressWarnings("rawtypes")
public interface PmsIndexSrv {

	/**
	 * 首页（不同支付方式金额汇总和留言预览）
	 * @Title: indexTotal2Messages 
	 * @Description: TODO
	 * @return
	 * @return: Map
	 */
	Map indexTotal2Messages(Integer hotelGroupId,Integer hotelId);
	
	/**
	 * 四种不同状态列表和数量统计
	 * @Title: fourStatusList 
	 * @Description: TODO
	 * @return
	 * @return: Map
	 */
	Map fourStatusList(Integer hotelGroupId,Integer hotelId,String statusType);
	
	/**
	 * 首页客房状态图
	 * @Title: pieChartData 
	 * @Description: TODO
	 * @return
	 * @return: Map
	 */
	Map roomStatusGraph(Integer hotelGroupId,Integer hotelId);
	
	/**
	 * 首页入住状态图
	 * @Title: pieChartData 
	 * @Description: TODO
	 * @return
	 * @return: Map
	 */
	Map inStatusGraph(Integer hotelGroupId,Integer hotelId);
	
	/**
	 * 首页数据表格（汇总情况、业务流转、本日预测）
	 * @Title: indexDatatable 
	 * @Description: TODO
	 * @param date
	 * @return
	 * @return: Map
	 */
	Map indexDatatable(String date,Integer hotelGroupId,Integer hotelId);
}
