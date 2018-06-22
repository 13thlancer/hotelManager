/**   
 * Copyright © 2018 武汉现代物华科技发展有限公司信息分公司. All rights reserved.
 * 
 * @Title: Pp01SrvImpl.java 
 * @Prject: Whhotel
 * @Package: com.whxx.hotel.service.impl
 * @author: 彭浩
 * @date: 2018年4月2日 上午10:03:37 
 * @version: V1.0   
 */
package com.whxx.hms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.whxx.core.utils.UUIDUtil;
import com.whxx.hms.dao.Pp01Mapper;
import com.whxx.hms.dao.Pp01aMapper;
import com.whxx.hms.model.Tbpp01;
import com.whxx.hms.model.Tbpp01a;
import com.whxx.hms.service.Pp01Srv;



/** 
 * 通用代码档
 * @ClassName: Pp01SrvImpl
 * @author: 彭浩
 * @date: 2018年4月2日 上午10:03:37  
 */
@Service
@Transactional
@SuppressWarnings("rawtypes")
public class Pp01SrvImpl implements Pp01Srv {
	@Autowired
	Pp01aMapper pp01aMapper;

	@Autowired
	Pp01Mapper pp01Mapper;
	
	
	public List<Map> selectPp01ByPage(Map param){
		return pp01Mapper.selectPp01ByPage(param);
	}
	
	/** 
	 * 
	 * @Title: selectPp01aByPage
	 * @param param
	 * @return
	 * @return: List<Map>
	 */
	public List<Map> selectPp01aByPage(Map param){
		return pp01Mapper.selectPp01aByPage(param);
	}
	
	
	
	
	/** 
	 * (non Javadoc) 
	 * @Title: insertTbpp01
	 * @param tbpp01
	 * @
	 * @see com.whxx.hotel.service.Pp01Srv#insertTbpp01(com.whxx.hotel.model.Tbpp01)
	 */
	@Override
	public void insertTbpp01(Tbpp01 tbpp01)  {
		
		tbpp01.setTbpp01Id(UUIDUtil.getUUID());
		//tbpp01.setDelFlag("0");
		tbpp01.setVersion(0);
		pp01Mapper.insertTbpp01(tbpp01);
	}

	/** 
	 * (non Javadoc) 
	 * @Title: insertTbpp01a
	 * @param tbpp01a
	 * @
	 * @see com.whxx.hotel.service.Pp01Srv#insertTbpp01a(com.whxx.hotel.model.Tbpp01a)
	 */
	@Override
	public void insertTbpp01a(Tbpp01a tbpp01a)  {
		tbpp01a.setTbpp01aId(UUIDUtil.getUUID());
		tbpp01a.setVersion(0);
		pp01Mapper.insertTbpp01a(tbpp01a);
	}

	/** 
	 * (non Javadoc) 
	 * @Title: selectConfigCode
	 * @param map
	 * @return
	 * @
	 * @see com.whxx.hotel.service.Pp01Srv#selectConfigCode(java.util.Map)
	 */
	@Override
	public List<Map> selectConfigCode(Map map)  {

		return pp01Mapper.selectConfigCode(map);
	}

	/** 
	 * (non Javadoc) 
	 * @Title: selectConfigCodeApp
	 * @param map
	 * @return
	 * @
	 * @see com.whxx.hotel.service.Pp01Srv#selectConfigCodeApp(java.util.Map)
	 */
	@Override
	public List<Map> selectConfigCodeApp(Map map)  {

		return pp01Mapper.selectConfigCodeApp(map);
	}

	/** 
	 * (non Javadoc) 
	 * @Title: selectIfDelete
	 * @param tbpp01Id
	 * @return
	 * @
	 * @see com.whxx.hotel.service.Pp01Srv#selectIfDelete(java.lang.String)
	 */
	@Override
	public List<Map> selectIfDelete(String tbpp01Id)  {

		return pp01Mapper.selectIfDelete(tbpp01Id);
	}

	/** 
	 * (non Javadoc) 
	 * @Title: selectRepeatSeq
	 * @param map
	 * @return
	 * @
	 * @see com.whxx.hotel.service.Pp01Srv#selectRepeatSeq(java.util.Map)
	 */
	@Override
	public String selectRepeatSeq(Map param)  {

		return pp01Mapper.selectRepeatSeq(param);
	}

	/** 
	 * (non Javadoc) 
	 * @Title: selectRepeatCode
	 * @param configCode
	 * @return
	 * @
	 * @see com.whxx.hotel.service.Pp01Srv#selectRepeatCode(java.lang.String)
	 */
	@Override
	public String selectRepeatCode(Map param)  {

		return pp01Mapper.selectRepeatCode(param);
	}

	/** 
	 * (non Javadoc) 
	 * @Title: selectPp01ById
	 * @param tbpp01Id
	 * @return
	 * @
	 * @see com.whxx.hotel.service.Pp01Srv#selectPp01ById(java.lang.String)
	 */
	@Override
	public Map selectPp01ById(String tbpp01Id)  {

		return pp01Mapper.selectPp01ById(tbpp01Id);
	}

	/** 
	 * (non Javadoc) 
	 * @Title: selectPp01aById
	 * @param tbpp01aId
	 * @return
	 * @
	 * @see com.whxx.hotel.service.Pp01Srv#selectPp01aById(java.lang.String)
	 */
	@Override
	public Map selectPp01aById(String tbpp01aId)  {

		return pp01Mapper.selectPp01aById(tbpp01aId);
	}

	/** 
	 * (non Javadoc) 
	 * @Title: updateTbpp01
	 * @param tbpp01
	 * @
	 * @see com.whxx.hotel.service.Pp01Srv#updateTbpp01(com.whxx.hotel.model.Tbpp01)
	 */
	@Override
	public void updateTbpp01(Tbpp01 tbpp01)  {
		pp01Mapper.updateTbpp01(tbpp01);
	}

	/** 
	 * (non Javadoc) 
	 * @Title: updateTbpp01a
	 * @param tbpp01a
	 * @
	 * @see com.whxx.hotel.service.Pp01Srv#updateTbpp01a(com.whxx.hotel.model.Tbpp01a)
	 */
	@Override
	public void updateTbpp01a(Tbpp01a tbpp01a)  {
		pp01Mapper.updateTbpp01a(tbpp01a);
	}

	/** 
	 * (non Javadoc) 
	 * @Title: deleteTbpp01
	 * @param tbpp01Id
	 * @
	 * @see com.whxx.hotel.service.Pp01Srv#deleteTbpp01(java.lang.String)
	 */
	@Override
	public void deleteTbpp01(String tbpp01Id)  {
		pp01Mapper.deleteTbpp01(tbpp01Id);
	}

	/** 
	 * (non Javadoc) 
	 * @Title: deleteTbpp01a
	 * @param tbpp01aId
	 * @
	 * @see com.whxx.hotel.service.Pp01Srv#deleteTbpp01a(java.lang.String)
	 */
	@Override
	public void deleteTbpp01a(String tbpp01aId)  {
		pp01Mapper.deleteTbpp01a(tbpp01aId);
	}

	@Override
	public Tbpp01a getTbpp01a(long hotelId, long hotelGroupId, String desc9) {
		// TODO Auto-generated method stub
		Tbpp01a tbpp01a=pp01aMapper.getTbpp01a(hotelId,hotelGroupId,desc9);
		return tbpp01a;
	}

	@Override
	public void insertTbpp01Sys(Tbpp01 tbpp01) {

		tbpp01.setTbpp01Id(UUIDUtil.getUUID());
		//tbpp01.setDelFlag("0");
		tbpp01.setVersion(0);
		pp01Mapper.insertTbpp01Sys(tbpp01);
	}

	@Override
	public List<Map> selectPp01ByPageSys(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return pp01Mapper.selectPp01ByPageSys(param);
	}
		

}
