package com.whxx.hms.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.whxx.hms.model.SubInfo;
import com.whxx.hms.model.dto.MainInfoDto;
import com.whxx.hms.model.dto.SubInfoDto;

@Repository
@SuppressWarnings("rawtypes")
public interface SubInfoMapper {
	
	/**
	 * 查询预订单子单的详情
	 * @Title: selectSubInfoById 
	 * @Description: TODO
	 * @param subInfoId
	 * @return
	 * @return: SubInfo
	 */
	SubInfo selectSubInfoById(String subInfoId);
	
	/**
	 * 根据mainId查询子表信息
	 * @Title: selectByMainId 
	 * @Description: TODO
	 * @param mainId
	 * @return
	 * @return: List<SubInfo>
	 */
	List<SubInfo> selectByMainId(String mainId);
	
	/**
	 * 根据priMainId查询所有子单信息
	 * @Title: selectByPriMainId 
	 * @Description: TODO
	 * @param priMainId
	 * @return
	 * @return: List<SubInfo>
	 */
	List<SubInfo> selectByPriMainId(String priMainId);
	
	/**
	 * 新增
	 * @Title: insertSubInfo 
	 * @Description: TODO
	 * @param subInfo
	 * @return: void
	 */
	void insertSubInfo(SubInfo subInfo);
	
	/**
	 * 修改
	 * @Title: updateSubInfoById 
	 * @Description: TODO
	 * @param mainInfo
	 * @return: void
	 */
	void updateSubInfoById(SubInfo subInfo);
	
	/**
	 * 根据主订单号删除子明细表
	 * @Title: deleteById 
	 * @Description: TODO
	 * @param mainId
	 * @return: void
	 */
	void deleteByMainId(String mainId);
	
	/**
	 * 查询SubId后缀最大值
	 * @Title: selectMainIdSuffix 
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	String selectSubIdSuffix();

	SubInfo getSubInfo(String subInfoId);
	
	/**
	 * 查询本日未到
	 * @Title: selectDayNoInNums 
	 * @Description: TODO
	 * @param subInfoDto
	 * @return
	 * @return: Integer
	 */
	Integer selectDayNoInNums(SubInfoDto subInfoDto);
	
	/**
	 * 查询查询首页本日未离、本日已到、本日已离
	 * @Title: selectDayNoInNums 
	 * @Description: TODO
	 * @param subInfoDto
	 * @return
	 * @return: Integer
	 */
	Integer selectInDayNums(SubInfoDto subInfoDto);
	
	/**
	 * 查询本日未到
	 * @Title: selectDayNoInList 
	 * @Description: TODO
	 * @param subInfoDto
	 * @return
	 * @return: List<SubInfoDto>
	 */
	List<MainInfoDto> selectDayNoInList(SubInfoDto subInfoDto);
	
	/**
	 * 首页本日未离、本日已到、本日已离
	 * @Title: selectDayNoInList 
	 * @Description: TODO
	 * @param subInfoDto
	 * @return
	 * @return: List<SubInfoDto>
	 */
	List<MainInfoDto> selectInDayList(SubInfoDto subInfoDto);
	
	/**
	 * 入住状态图
	 * @Title: inRoomPieChart 
	 * @Description: TODO
	 * @param param
	 * @return
	 * @return: List<Map>
	 */
	List<Map> inRoomPieChart(Map param);
	
	/**
	 * 根据入住单号查询预订单号
	 * @Title: selectPriMainIdBySubId 
	 * @Description: TODO
	 * @param subId
	 * @return
	 * @return: String
	 */
	String selectPriMainIdBySubId(String subId);
	
	/**
	 * 查询子单的SubId(预付款信息累加到主单上面)
	 * @Title: selectSubIdByIsMain 
	 * @Description: TODO
	 * @param priMainId
	 * @return
	 * @return: String
	 */
	String selectSubIdByIsMain(String priMainId);
	
	/**
	 * 根据subId查询mainId
	 * @Title: mainIdBySubId 
	 * @Description: TODO
	 * @param subId
	 * @return
	 * @return: String
	 */
	String priMainIdBySubId(String subId);
	
	/**
	 * 批量处理列表
	 * @Title: batchOrderList 
	 * @Description: TODO
	 * @param priMainId
	 * @return
	 * @return: List<SubInfoDto>
	 */
	List<SubInfoDto> batchOrderList(String priMainId);
	
	/**
	 * 主键删除
	 * @Title: deleteById 
	 * @Description: TODO
	 * @param subInfoId
	 * @return: void
	 */
	void deleteById(String subInfoId);
	
}
