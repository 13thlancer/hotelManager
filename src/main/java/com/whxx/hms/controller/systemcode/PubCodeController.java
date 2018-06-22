package com.whxx.hms.controller.systemcode;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.whxx.core.controller.BaseController;
import com.whxx.core.factory.PageFactory;
import com.whxx.core.utils.CommUtil;
import com.whxx.core.utils.HttpUtil;
import com.whxx.core.utils.UUIDUtil;
import com.whxx.hms.model.Tbpp01a;
import com.whxx.hms.service.PubCodeSrv;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpStatus;

@RequestMapping("/pubCode")
@RestController
public class PubCodeController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(PubCodeController.class);
	@Autowired
	private PubCodeSrv pubCodeSrvImpl;

	/*
	 * 通用代码一级配置查询 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/listFirstConfig")
	public Object listFirst(HttpServletRequest request) {
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		/*
		 * paramMap.put("hotelId", "9"); paramMap.put("hotelGroupId", "2");
		 * paramMap.put("level", "2");
		 */
		logger.info("=====通用代码查询参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "一级通用代码查询参数为空", paramMap);
		}
		List list = this.pubCodeSrvImpl.listFirstConfig(paramMap);
		Map map = new HashMap<>();
		map.put("pubCodeFirst", list);
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "一级通用代码配置查询结果", map);
	}
	
	/*
	 * 通用代码一级系统查询 
	 * 参数:configName
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/listFirstSystem")
	public Object listFirstSystem(HttpServletRequest request) {
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		/*
		 * paramMap.put("hotelId", "9"); paramMap.put("hotelGroupId", "2");
		 * paramMap.put("level", "1");
		 */
		logger.info("=====通用代码查询参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "一级通用代码系统查询参数为空", paramMap);
		}
		new PageFactory<Map<String, Object>>().defaultPage();
    	List<Map<String, Object>> list = this.pubCodeSrvImpl.listFirstSystem(paramMap);
    	
        PageInfo pageInfo = new PageInfo(list);
        Page pageList = (Page) list;
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("total",pageInfo.getTotal());
        result.put("rows", pageList);
        return CommUtil.setMessage(HttpStatus.HTTP_OK, "一级通用代码系统参数查询结果", result);
	}
	

	/*
	 * 通用代码二级查询
	 *  参数:一级tbpp01Id
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/listSecond")
	public Object listSecond(HttpServletRequest request) {
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		logger.info("=====通用代码查询参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "二级通用代码查询参数为空", paramMap);
		}
		new PageFactory<Map<String, Object>>().defaultPage();
    	List<Map<String, Object>> list = this.pubCodeSrvImpl.listSecond(paramMap);
        PageInfo pageInfo = new PageInfo(list);
        Page pageList = (Page) list;
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("total",pageInfo.getTotal());
        result.put("rows", pageList);
        return CommUtil.setMessage(HttpStatus.HTTP_OK, "二级通用代码查询结果", result);
	}

	/*
	 * 添加二级通用代码
	 *  参数:tbpp01Id
	 */
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping("/insertSecondCode")
	public Object insertSecondCode(HttpServletRequest request) {
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		logger.info("=====通用代码查询参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "二级通用代码查询参数为空", paramMap);
		}
		String tbpp01Id = request.getParameter("tbpp01Id");
		String seq = request.getParameter("seq");
		String configCode = request.getParameter("configCode");
		String tbpp01aSeq=this.pubCodeSrvImpl.isFirstSeq(tbpp01Id,seq);
		if(tbpp01aSeq!=null) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "序列号不能重复", null);
		}
		String tbpp01aConfigCode=this.pubCodeSrvImpl.isFirstConfigCode(tbpp01Id,configCode);
		if(tbpp01aConfigCode!=null) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "项目代码不能重复", null);
		}
		//新增
		Map map = new HashMap<>();
		Tbpp01a tbpp01a = new Tbpp01a();
		String tbpp01aId = UUIDUtil.getUUID();
		BeanUtil.copyProperties(paramMap, tbpp01a);
		tbpp01a.setTbpp01aId(tbpp01aId);
		tbpp01a.setSysFlag("Y");//默认为系统代码
		tbpp01a.setStopped("N");//是否弃用，默认不弃用
		tbpp01a.setVersion(1);
		try {
			this.pubCodeSrvImpl.insertSecondCode(setCreateAndUpdate(tbpp01a));
			logger.info("=====二级通用代码添加成功=======");
		} catch (Exception e) {
			logger.error("=====二级通用代码添加失败=======" + e.getMessage(),e);
        	return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "二级通用代码添加失败", null);
		}
		
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "二级通用代码添加成功", null);
	}
	
	/*
	 * 通用代码二级查询
	 *  参数:tbpp01aId
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/selectSecondCode")
	public Object selectSecondCode(HttpServletRequest request) {
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		logger.info("=====通用代码查询参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "二级通用代码查询参数为空", paramMap);
		}
		Tbpp01a Tbpp01a=this.pubCodeSrvImpl.selectSecondCodeByName(paramMap);
		Map map = new HashMap<>();
		map.put("Tbpp01a", Tbpp01a);
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "二级通用代码查询结果", map);
	}
	
	/*
	 * 通用代码二级修改
	 *  参数:二tbpp01aId
	 */
	@RequestMapping("/updateSecondCode")
	public Object updateSecondCode(HttpServletRequest request) {
		logger.info("=======二级通用代码进行修改=======");
		String zhName = request.getParameter("zhName");
		String enName = request.getParameter("enName");
		String stopped = request.getParameter("stopped");
		String tbpp01aId = request.getParameter("tbpp01aId");
		Tbpp01a tbpp01a = new Tbpp01a();
		tbpp01a.setZhName(zhName);
		tbpp01a.setEnName(enName);
		tbpp01a.setStopped(stopped);
		tbpp01a.setUpdateDate(new Date());
		tbpp01a.setUpdateEmp(getLoginUserAccount());
		tbpp01a.setTbpp01aId(tbpp01aId);
		try {
			this.pubCodeSrvImpl.updateSecondCode(tbpp01a);
			logger.info("=====二级通用代码修改成功=======");
		} catch (Exception e) {
			logger.error("=====二级通用代码修改失败=======" + e.getMessage(),e);
        	return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "二级通用代码修改失败", null);
		}
		
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "二级通用代码修改成功", null);
	}
	
	/*
	 * 通用代码二级删除
	 *  参数:tbpp01aId
	 */
	@RequestMapping("/deleteSecondCode/{tbpp01aId}")
	public Object deleteSecondCode(HttpServletRequest request,@PathVariable String tbpp01aId) {
		logger.info("=======二级通用代码进行删除=======");
		try {
			this.pubCodeSrvImpl.deleteSecondCodeById(tbpp01aId);
			logger.info("=====二级通用代码删除成功=======");
		} catch (Exception e) {
			logger.error("=====二级通用代码删除失败=======" + e.getMessage(),e);
        	return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "二级通用代码删除失败", null);
		}
		
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "二级通用代码删除成功", null);
	}
	
	
}
