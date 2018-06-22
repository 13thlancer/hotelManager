/**   
 * Copyright © 2018 武汉现代物华科技发展有限公司信息分公司. All rights reserved.
 * 
 * @Title: GroupController.java 
 * @Package: com.whxx.hotel.controller
 * @author: 彭浩
 * @date: 2018年3月5日 下午3:29:01 
 * @version: V1.0   
 */
package com.whxx.hms.controller.curuse;

import javax.annotation.Resource;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.whxx.core.controller.BaseController;
import com.whxx.hms.service.GroupSrv;


/** 
 * GroupController
 * @ClassName: GroupController
 * @author: 彭浩
 * @date: 2018年3月26日 下午2:08:01  
 */
@Controller
@RequestMapping("/group")
public class GroupController extends BaseController {
	
	private static String PREFIX = "/hotel/group/";

    @Resource
    GroupSrv groupSrv;
  

    /**
     * 集团选择下拉框
     */
    @RequestMapping(value = "/selectGroupDropdown")
    @ResponseBody
    public Object selectGroupDropdown(){
    	return JSONObject.toJSON(groupSrv.selectGroupDropdown());
    }
    

}
