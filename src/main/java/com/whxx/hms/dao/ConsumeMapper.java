package com.whxx.hms.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.whxx.hms.model.Consume;

@Repository
public interface ConsumeMapper {
	
	/**
	 * 查询账单信息
	 * @Title: selectSubInfoById 
	 * @Description: TODO
	 * @param subInfoId
	 * @return
	 * @return: SubInfo
	 */
	List<Consume> selectConsumeBypriMainId(Consume consume);
	
	/**
	 * 新增
	 * @Title: insertConsume 
	 * @Description: TODO
	 * @param consume
	 * @return: void
	 */
	void insertConsume(Consume consume);
	
	/**
	 * 修改
	 * @Title: updateConsumeById 
	 * @Description: TODO
	 * @param mainInfo
	 * @return: void
	 */
	void updateConsumeById(Consume consume);
	
	/**
	 * 首页查询不同类别的当日汇总金额
	 * @Title: selectIndexTotalByType 
	 * @Description: TODO
	 * @param date
	 * @param accountType
	 * @return
	 * @return: String
	 */
	@SuppressWarnings("rawtypes")
	List<Map> selectIndexTotalByType(Map param);
	
	/**
	 * 查询详情
	 * @Title: selectById 
	 * @Description: TODO
	 * @param consumeId
	 * @return
	 * @return: Consume
	 */
	Consume selectById(String consumeId);
}
