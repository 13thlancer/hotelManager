package com.whxx.hms.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.whxx.hms.model.MainInfo;

@Repository
public interface MainInfoMapper {
	
	/**
	 * 查询预订单详情
	 * @Title: selectMainInfoById 
	 * @Description: TODO
	 * @param mainInfoId
	 * @return
	 * @return: MainInfo
	 */
	MainInfo selectMainInfoById(String mainInfoId);
	
	/**
	 * 根据主预订单号查询记录
	 * @Title: selectByPriMainId 
	 * @Description: TODO
	 * @param priMainId
	 * @return
	 * @return: List<MainInfo>
	 */
	List<MainInfo> selectByPriMainId(String priMainId);
	
	/**
	 * 新增
	 * @Title: insertMainInfo 
	 * @Description: TODO
	 * @param mainInfo
	 * @return: void
	 */
	void insertMainInfo(MainInfo mainInfo);
	
	
	/**
	 * 修改
	 * @Title: insertMainInfo 
	 * @Description: TODO
	 * @param mainInfo
	 * @return: void
	 */
	void updateMainInfoById(MainInfo mainInfo);
	
	
	void deleteById(String mainInfoId);
	
	/**
	 *查询MainId后缀最大值
	 * @Title: selectMainIdSuffix 
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	String selectMainIdSuffix();

	List<MainInfo> getListMainInfo(Map<String, String> paramMap);
	
	/**
	 * 查询主单的MainId(预付款信息累加到主单上面)
	 * @Title: selectMainIdByIsMain 
	 * @Description: TODO
	 * @param priMainId
	 * @return
	 * @return: String
	 */
	String selectMainIdByIsMain(String priMainId);
	
}
