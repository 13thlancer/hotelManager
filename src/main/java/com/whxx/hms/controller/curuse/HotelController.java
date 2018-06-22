/**   
 * Copyright © 2018 武汉现代物华科技发展有限公司信息分公司. All rights reserved.
 * 
 * @Title: HotelController.java 
 * @Package: com.whxx.hotel.controller
 * @author: 彭浩
 * @date: 2018年3月5日 下午3:29:01 
 * @version: V1.0   
 */
package com.whxx.hms.controller.curuse;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.whxx.core.controller.BaseController;
import com.whxx.hms.service.HotelSrv;


/** 
 * HotelController
 * @ClassName: HotelController
 * @author: 彭浩
 * @date: 2018年3月26日 下午2:08:01  
 */
@Controller
@RequestMapping("/hotel")
public class HotelController extends BaseController {
	public static Logger log = LoggerFactory.getLogger(HotelController.class);
	private static String PREFIX = "/hotel/hotel/";

    @Resource
    HotelSrv hotelSrv;

   
    
    /**
     * 酒店选择下拉框
     */
    @RequestMapping(value = "/selectHotelDropdown")
    @ResponseBody
    public Object selectHotelDropdown(HttpServletRequest reuqest,HttpServletResponse response) {
        	
    	Map<String,Object> param = new HashMap<String,Object>();
    	param.put("hotelGroupId", reuqest.getParameter("hotelGroupId"));
    	
    	return JSONObject.toJSON(hotelSrv.selectHotelDropdown(param));
    }
    
    
   

}
