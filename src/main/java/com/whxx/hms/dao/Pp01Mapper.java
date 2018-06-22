package com.whxx.hms.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.whxx.hms.model.Tbpp01;
import com.whxx.hms.model.Tbpp01a;


@SuppressWarnings("rawtypes")
@Repository
public interface Pp01Mapper {
	
	/** 
	 * 
	 * @Title: selectPp01ByPage
	 * @param param
	 * @return
	 * @return: List<Map>
	 */
	public List<Map> selectPp01ByPage(Map param);
	
	/** 
	 * 
	 * @Title: selectPp01aByPage
	 * @param param
	 * @return
	 * @return: List<Map>
	 */
	public List<Map> selectPp01aByPage(Map param);
	
	
	/**
	 * 新增一级编码 
	 * @param map
	 * @throws Exception
	 */
	public void insertTbpp01(Tbpp01 tbpp01);

	/**
	 * 新增二级编码 
	 * @param map
	 * @throws Exception
	 */
	public void insertTbpp01a(Tbpp01a tbpp01a);

	
	/**
	 * 查询编码下拉框 
	 * @param map
	 * @throws Exception
	 */
	public List<Map> selectConfigCode(Map map);
	
	/**
	 * 查询App编码下拉框 
	 * @param map
	 * @throws Exception
	 */
	public List<Map> selectConfigCodeApp(Map map);
	
	
	/**
	 * 查询一级项目是否有二级项目
	 * @param map
	 * @throws Exception
	 */
	public List<Map> selectIfDelete(String tbpp01Id);
	/**
	 * 查询二级项目顺序号重复
	 * @param map
	 * @throws Exception
	 */
	public String selectRepeatSeq(Map param);
	/**
	 * 根据查询编码重复 
	 * @param map
	 * @throws Exception
	 */
	public String selectRepeatCode(Map param);
	
	/**
	 * 根据id查询一级编码 
	 * @param map
	 * @throws Exception
	 */
	public Map selectPp01ById(String tbpp01Id);
	
	/**
	 * 根据id查询二级编码 
	 * @param map
	 * @throws Exception
	 */
	public Map selectPp01aById(String tbpp01aId);

	
	/**
	 * 修改一级编码 
	 * @param map
	 * @throws Exception
	 */
	public void updateTbpp01(Tbpp01 tbpp01);

	/**
	 * 修改二级编码 
	 * @param map
	 * @throws Exception
	 */
	public void updateTbpp01a(Tbpp01a tbpp01a);
	
	/**
	 * 删除一级编码 
	 * @param map
	 * @throws Exception
	 */
	public void deleteTbpp01(String tbpp01Id);
	
	/**
	 * 删除二级编码 
	 * @param map
	 * @throws Exception
	 */
	public void deleteTbpp01a(String tbpp01aId);

	public void insertTbpp01Sys(Tbpp01 tbpp01);

	public List<Map> selectPp01ByPageSys(Map<String, Object> param);

}