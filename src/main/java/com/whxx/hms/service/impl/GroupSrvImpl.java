/**   
 * Copyright © 2018 武汉现代物华科技发展有限公司信息分公司. All rights reserved.
 * 
 * @Title: GroupSrvImpl.java 
 * @Prject: Whhotel
 * @Package: com.whxx.hotel.service.impl
 * @author: 彭浩
 * @date: 2018年3月26日 下午2:21:57 
 * @version: V1.0   
 */
package com.whxx.hms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.whxx.hms.dao.GroupMapper;
import com.whxx.hms.service.GroupSrv;



/** 
 * GroupSrvImpl
 * @ClassName: GroupSrvImpl
 * @author: 彭浩
 * @date: 2018年3月26日 下午2:21:57  
 */
@SuppressWarnings("rawtypes")
@Service
@Transactional
public class GroupSrvImpl implements GroupSrv {
	
	@Autowired
	GroupMapper groupMapper;



	/** 
	 * (non Javadoc) 
	 * @Title: selectGroupDropdown
	 * @return
	 * @see com.whxx.hotel.service.GroupSrv#selectGroupDropdown()
	 */
	@Override
	public List<Map> selectGroupDropdown() {
		
		return groupMapper.selectGroupDropdown();
	}

}
