package com.whxx.hms.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.whxx.core.utils.AppUtils;
import com.whxx.core.utils.DateUtil;
import com.whxx.hms.dao.ConsumeMapper;
import com.whxx.hms.dao.MessagesMapper;
import com.whxx.hms.dao.RoomStatusMapper;
import com.whxx.hms.dao.SubInfoMapper;
import com.whxx.hms.model.dto.MainInfoDto;
import com.whxx.hms.model.dto.MessageDto;
import com.whxx.hms.model.dto.SubInfoDto;
import com.whxx.hms.service.PmsIndexSrv;

import ch.qos.logback.classic.Logger;

@Service
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class PmsIndexSrvImpl implements PmsIndexSrv {

Logger logger = (Logger) AppUtils.getLogger("pms-index", true);
	
	@Autowired
	private SubInfoMapper subInfoMapper;
	
	@Autowired
	private MessagesMapper messagesMapper;
	
	@Autowired
	private ConsumeMapper consumeMapper;
	
	@Autowired
	private RoomStatusMapper roomStatusMapper;
	
	/**
	 * 首页汇总金额和3条留言(non Javadoc) 
	 * @Title: indexTotal2Messages
	 * @Description: TODO
	 * @return
	 * @see com.whxx.hms.service.PmsIndexSrv#indexTotal2Messages()
	 */
	@Override
	public Map indexTotal2Messages(Integer hotelGroupId,Integer hotelId) {
		// TODO Auto-generated method stub
		Map map  = new HashMap<>();
		List<MessageDto> messagesList = this.messagesMapper.indexMessagesList(hotelGroupId,hotelId);
		String date = DateUtil.format(new Date(), "yyyy-MM-dd");
		Map param  = new HashMap<>();
		param.put("hotelGroupId", hotelGroupId);
		param.put("hotelId", hotelId);
		param.put("date", date);
		List<Map> indexTotal =  consumeMapper.selectIndexTotalByType(param);
		map.put("indexTotal", indexTotal);
		map.put("messagesList", messagesList);
		return map;
	}

	/**
	 * 本日未到、本日未离、本日已到、本日已离
	 * (non Javadoc) 
	 * @Title: fourStatusList
	 * @Description: TODO
	 * @return
	 * @see com.whxx.hms.service.PmsIndexSrv#fourStatusList()
	 */
	@Override
	public Map fourStatusList(Integer hotelGroupId,Integer hotelId,String statusType) {
		// TODO Auto-generated method stub
		Map map  = new HashMap<>();
		//集合
		List<MainInfoDto> orderList = new ArrayList<>();
		//数量
		Integer count = 0;
		String date = DateUtil.format(new Date(), "yyyy-MM-dd");
		SubInfoDto subInfoDto = new SubInfoDto();
		subInfoDto.setHotelGroupId(hotelGroupId);
		subInfoDto.setHotelId(hotelId);
		
		//本日未到数量
		subInfoDto.setPlanStart(date);
		subInfoDto.setMainStatus("R");
		count = subInfoMapper.selectDayNoInNums(subInfoDto);
		map.put("noInNums", count);
		//本日未离数量
		subInfoDto.setPlanStart(null);
		subInfoDto.setPlanEnd(date);
		subInfoDto.setMainStatus("I");
		count = subInfoMapper.selectInDayNums(subInfoDto);
		map.put("noLeaveNums", count);
		//本日已到数量
		subInfoDto.setPlanStart(date);
		subInfoDto.setPlanEnd(null);
		subInfoDto.setMainStatus("I");
		count = subInfoMapper.selectInDayNums(subInfoDto);
		map.put("inNums", count);
		//本日已离数量
		subInfoDto.setPlanEnd(date);
		subInfoDto.setPlanStart(null);
		subInfoDto.setMainStatus("O");
		count = subInfoMapper.selectInDayNums(subInfoDto);
		map.put("leavedNums", count);
		
		
		//本日未到
		if("1".equals(statusType)){
			subInfoDto.setPlanStart(date);
			subInfoDto.setMainStatus("R");
			orderList = this.subInfoMapper.selectDayNoInList(subInfoDto);
			map.put("orderList", orderList);
		}
		//本日未离
		if("2".equals(statusType)){
		    subInfoDto.setPlanStart(null);
			subInfoDto.setPlanEnd(date);
			subInfoDto.setMainStatus("I");
			orderList = this.subInfoMapper.selectInDayList(subInfoDto);
			map.put("orderList", orderList);
		}
		//本日已到
		if("3".equals(statusType)){
			subInfoDto.setPlanStart(date);
			subInfoDto.setPlanEnd(null);
			subInfoDto.setMainStatus("I");
			orderList = this.subInfoMapper.selectInDayList(subInfoDto);
			map.put("orderList", orderList);
		}
		//本日已离
		if("4".equals(statusType)){
			subInfoDto.setPlanEnd(date);
			subInfoDto.setPlanStart(null);
			subInfoDto.setMainStatus("O");
			orderList = this.subInfoMapper.selectInDayList(subInfoDto);
			map.put("orderList", orderList);
		}
		return map;
	}

	
	/**
	 * 首页数据表格
	 * (non Javadoc) 
	 * @Title: indexDatatable
	 * @Description: TODO
	 * @param date
	 * @return
	 * @see com.whxx.hms.service.PmsIndexSrv#indexDatatable(java.lang.String)
	 */
	@Override
	public Map indexDatatable(String date,Integer hotelGroupId,Integer hotelId) {
		// TODO Auto-generated method stub
		Map param = new HashMap<>();
		Map result = new HashMap<>();
		String dateStr = date.replace("-", "");
		param.put("hotelGroupId", hotelGroupId);
		param.put("hotelId", hotelId);
		param.put("dateStr", dateStr);
		param.put("date", date);
		List<Map> totalList = this.roomStatusMapper.selectDatagridTotalInfo(param);
		List<Map> bussList = this.roomStatusMapper.selectDatagridBussInfo(param);
		List<Map> forecastList = this.roomStatusMapper.selectDatagridForecastInfo(param);
		result.put("totalList", totalList);
		result.put("bussList", bussList);
		result.put("forecastList", forecastList);
		return result;
	}

	
	/**
	 * 客房状态图
	 * (non Javadoc) 
	 * @Title: roomStatusGrah
	 * @Description: TODO
	 * @return
	 * @see com.whxx.hms.service.PmsIndexSrv#roomStatusGrah()
	 */
	@Override
	public Map roomStatusGraph(Integer hotelGroupId,Integer hotelId) {
		// TODO Auto-generated method stub
		Map map  = new HashMap<>();//返回结果
		Map param  = new HashMap<>();
		param.put("hotelGroupId", hotelGroupId);
		param.put("hotelId", hotelId);
		//客房状态图（饼状图）
		String date = DateUtil.format(new Date(), "yyyyMMdd");
		param.put("date", date);
		List<Map> roomStatus = roomStatusMapper.roomStatusPieChart(param);
		map.put("roomPieChart", roomStatus);
		//房型（柱状图）
		param.put("inFlag", "N");
		List<Map> roomType = roomStatusMapper.roomTypeBarChart(param);
		map.put("roomTypeBarChart", roomType);
		return map;
	}

	
	/**
	 * 入住状态图
	 * (non Javadoc) 
	 * @Title: inStatusGrah
	 * @Description: TODO
	 * @return
	 * @see com.whxx.hms.service.PmsIndexSrv#inStatusGrah()
	 */
	@Override
	public Map inStatusGraph(Integer hotelGroupId,Integer hotelId) {
		// TODO Auto-generated method stub
		Map map  = new HashMap<>();//返回结果
		Map param  = new HashMap<>();
		param.put("hotelGroupId", hotelGroupId);
		param.put("hotelId", hotelId);
		//入住状态图（饼状图）
		String date = DateUtil.format(new Date(), "yyyyMMdd");
		param.put("date", date);
		List<Map> roomStatus = subInfoMapper.inRoomPieChart(param);
		map.put("inRoomPieChart", roomStatus);
		//房型在住（柱状图）
		param.put("inFlag", "Y");
		List<Map> roomType = roomStatusMapper.roomTypeBarChart(param);
		map.put("roomTypeBarChart", roomType);
		return map;
	}

}
