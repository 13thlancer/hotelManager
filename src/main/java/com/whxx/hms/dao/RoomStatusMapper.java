package com.whxx.hms.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.whxx.hms.model.RoomStatus;
import com.whxx.hms.model.dto.DropdownDto;

@Repository
@SuppressWarnings("rawtypes")
public interface RoomStatusMapper {
	
	/**
	 * 修改
	 * @Title: insertMainInfo 
	 * @Description: TODO
	 * @param mainInfo
	 * @return: void
	 */
	void updateRoomStatus(RoomStatus roomStatus);


	List<RoomStatus> getListRoomStatus(Map<String, String> paramMap);
	
	/**
	 * 首页房型的柱状图（在住和入住）
	 * @Title: roomTypeStatistic 
	 * @Description: TODO
	 * @param param
	 * @return
	 * @return: Map
	 */
	
	List<Map> roomTypeBarChart(Map param);
	
	/**
	 * 首页饼状图
	 * @Title: roomStatusPieChart 
	 * @Description: TODO
	 * @param param
	 * @return
	 * @return: Map
	 */
	List<Map> roomStatusPieChart(Map param);
	
	/**
	 * 数据网格汇总情况 
	 * @Title: selectDatagridTotalInfo 
	 * @Description: TODO
	 * @param param
	 * @return
	 * @return: List<Map>
	 */
	List<Map> selectDatagridTotalInfo(Map param);
	
	/**
	 * 查询首页数据网格业务流转
	 * @Title: selectDatagridBussInfo 
	 * @Description: TODO
	 * @param param
	 * @return
	 * @return: List<Map>
	 */
	List<Map> selectDatagridBussInfo(Map param);
	
	/**
	 * 查询首页数据网格本日预测 
	 * @Title: selectDatagridForecastInfo 
	 * @Description: TODO
	 * @param param
	 * @return
	 * @return: List<Map>
	 */
	List<Map> selectDatagridForecastInfo(Map param);
	
	/**
	 * 查询可用房列表（用于自动排房）
	 * @Title: roomCodeList 
	 * @Description: TODO
	 * @param roomStatus
	 * @return
	 * @return: List<DropdownDto>
	 */
	List<DropdownDto> roomCodeList(RoomStatus roomStatus);
}
