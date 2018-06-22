package com.whxx.hms.controller.curuse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.whxx.core.controller.BaseController;
import com.whxx.core.exception.BizExceptionEnum;
import com.whxx.core.exception.BussinessException;
import com.whxx.core.factory.PageFactory;
import com.whxx.core.kit.ObjectKit;
import com.whxx.core.tips.ErrorTip;
import com.whxx.core.tips.Tip;
import com.whxx.core.utils.ToolUtil;
import com.whxx.hms.model.Tbpp01;
import com.whxx.hms.model.Tbpp01a;
import com.whxx.hms.service.Pp01Srv;



/** 
 * 通用代码档
 * @ClassName: Pp01Controller
 * @author: 彭浩
 * @date: 2018年4月10日 上午11:29:40  
 */
@Controller
@RequestMapping({ "/pp01" })
@SuppressWarnings({"rawtypes","unchecked"})
public class Pp01Controller extends BaseController {
	
	private static String PREFIX = "/hotel/pp01/";

	@Resource
	public Pp01Srv pp01Srv;

	 /** 
     * 跳转代码主档管理主页
     * @Title: topp01
     * @return
     * @return: String
     */
    @RequestMapping("")
    public String topp01( Model model) {
    	return PREFIX + "pp01";
    }
    
    /**
     * 跳转到新增代码主档页面
     */
    @RequestMapping(value = "/toadd")
    public String toadd() {
        return PREFIX + "pp01_add";
    }
    
    
    /**
    * 修改页面
    */
    @RequestMapping(value = "/tomodify/{id}")
    public String tomodify(@PathVariable String id, Model model) {
    	if (ToolUtil.isEmpty(id)) {
			throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
		}
		Map pp01 = this.pp01Srv.selectPp01ById(id);

		model.addAttribute("pp01", pp01);
    	return PREFIX + "pp01_modify";
    }

    
    
    /** 
     * 获取代码主档列表
     * @Title: list
     * @param reuqest
     * @param response
     * @return
     * @return: Object
     */
	@RequestMapping(value = "/list")
    @ResponseBody
    public Object list(HttpServletRequest reuqest,HttpServletResponse response) {
    	
    	Map<String,Object> param = new HashMap<String,Object>();
    	param.put("configCode", reuqest.getParameter("configCode"));
    	param.put("configName", reuqest.getParameter("configName"));
    	
    	new PageFactory<Map<String, Object>>().defaultPage();
    	List<Map> list = pp01Srv.selectPp01ByPage(param);
    	
        PageInfo pageInfo = new PageInfo(list);
        Page pageList = (Page) list;
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("total",pageInfo.getTotal());
        result.put("rows", pageList);
        
        return result;
    }


    /**
     * 新增代码主档
     */
    @RequestMapping(value = "/pp01Add")
    @ResponseBody
    public Tip add(@RequestBody Tbpp01 pp01, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        Map param = new HashMap();
        param.put("configCode", pp01.getConfigCode());
        param.put("hotelGroupId", pp01.getHotelGroupId());
        param.put("hotelId", pp01.getHotelId());

        
        if(null!=pp01Srv.selectRepeatCode(param)){
        	return new ErrorTip(403, "配置项目代码不能重复");
        }
        
        pp01Srv.insertTbpp01(setCreateEmpAndTime(pp01));
        return SUCCESS_TIP;
    }



    /**
     * 删除代码主档
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Tip delete(@RequestParam String tbpp01Id) {
        if (ToolUtil.isEmpty(tbpp01Id)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }

        pp01Srv.deleteTbpp01(tbpp01Id);
        return SUCCESS_TIP;
    }

    
    
    /**
     * 修改代码主档
     */
    @RequestMapping(value = "/pp01Edit")
    @ResponseBody
    public Tip edit(@RequestBody Tbpp01 pp01, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }

        this.pp01Srv.updateTbpp01(setUpdateEmpAndTime(pp01));
        return SUCCESS_TIP;
    }
    
    //-----------------------------代码从档--------------------------------------

    /** 
     * 跳转代码从档管理主页
     * @Title: topp01
     * @return
     * @return: String
     */
    @RequestMapping("/pp01a/{tbpp01Id}")
    public String topp01a( Model model,@PathVariable String tbpp01Id,HttpServletRequest reuqest) {
    	model.addAttribute("tbpp01Id",tbpp01Id);
    	model.addAttribute("configCode",reuqest.getParameter("configCode"));
    	return PREFIX + "pp01a";
    }
    
    /**
     * 跳转到新增代码从档页面
     */
    @RequestMapping(value = "/toaddpp01a")
    public String toaddpp01a(Model model,@RequestParam String configCode,String tbpp01Id) {
    	model.addAttribute("configCode",configCode);
    	model.addAttribute("tbpp01Id",tbpp01Id);
        return PREFIX + "pp01a_add";
    }
    
    
    /**
    * 修改页面
    */
    @RequestMapping(value = "/tomodifypp01a/{id}")
    public String tomodifypp01a(@PathVariable String id, Model model) {
    	if (ToolUtil.isEmpty(id)) {
			throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
		}
		Map pp01a = this.pp01Srv.selectPp01aById(id);

		model.addAttribute("pp01a", pp01a);
    	return PREFIX + "pp01a_modify";
    }
    
    
    
    /** 
     * 获取代码从档列表
     * @Title: list
     * @param reuqest
     * @param response
     * @return
     * @return: Object
     */
	@RequestMapping(value = "/listpp01a")
    @ResponseBody
    public Object listpp01a(HttpServletRequest reuqest,HttpServletResponse response) {
    	
    	Map<String,Object> param = new HashMap<String,Object>();
    	param.put("tbpp01Id", reuqest.getParameter("tbpp01Id"));
    	
    	new PageFactory<Map<String, Object>>().defaultPage();
    	List<Map> list = pp01Srv.selectPp01aByPage(param);
    	
        PageInfo pageInfo = new PageInfo(list);
        Page pageList = (Page) list;
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("total",pageInfo.getTotal());
        result.put("rows", pageList);
        
        return result;
    }


    /**
     * 新增代码从档
     */
    @RequestMapping(value = "/pp01aAdd")
    @ResponseBody
    public Tip addpp01a(@RequestBody Tbpp01a pp01a, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        Map param = new HashMap();
        param.put("configCode", pp01a.getConfigCode());
        param.put("seq", pp01a.getSeq());
        param.put("hotelGroupId", pp01a.getHotelGroupId());
        param.put("hotelId", pp01a.getHotelId());
        
        if(null!=pp01Srv.selectRepeatSeq(param)){
        	return new ErrorTip(403, "顺序号不能重复");
        }
        
        Map pp01 = pp01Srv.selectPp01ById(pp01a.getTbpp01Id());
        		
		if(null!=pp01){
			pp01a.setHotelGroupId(ObjectKit.getLong(pp01.get("hotelGroupId")));
			pp01a.setHotelId(ObjectKit.getLong(pp01.get("hotelId")));
		}
        
        pp01Srv.insertTbpp01a(setCreateEmpAndTime(pp01a));
        Tbpp01 tbpp01=new Tbpp01();
        tbpp01.setLevel("2");
        tbpp01.setConfigName((String)pp01.get("configName"));
        tbpp01.setTbpp01Id((String)pp01.get("tbpp01Id"));
        tbpp01.setNotice((String)pp01.get("notice"));
        pp01Srv.updateTbpp01(tbpp01);
        return SUCCESS_TIP;
    }


    
    
    /**
     * 修改代码从档
     */
    @RequestMapping(value = "/pp01aEdit")
    @ResponseBody
    public Tip editpp01a(@RequestBody Tbpp01a pp01a, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }

        this.pp01Srv.updateTbpp01a(setUpdateEmpAndTime(pp01a));
        return SUCCESS_TIP;
    }
    
    

    /**
     * 删除代码从档
     */
    @RequestMapping(value = "/deletepp01a")
    @ResponseBody
    public Tip deletepp01a(@RequestParam String tbpp01aId) {
        if (ToolUtil.isEmpty(tbpp01aId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }

        pp01Srv.deleteTbpp01a(tbpp01aId);
        return SUCCESS_TIP;
    }
    

    
    /**
     * 代码主档选择下拉框
     */
    @RequestMapping(value = "/selectConfigCode")
    @ResponseBody
    public Object selectConfigCode(HttpServletRequest reuqest){
    	
    	Map map = new HashMap();
    	map.put("configCode",reuqest.getParameter("configCode"));
    	map.put("hotelGroupId",reuqest.getParameter("hotelGroupId"));
    	map.put("hotelId",reuqest.getParameter("hotelId"));
    	
    	return JSONObject.toJSON(pp01Srv.selectConfigCode(map));
    }
    
    
    /**
     * 代码从档选择下拉框
     */
    @RequestMapping(value = "/selectGroupDropdown")
    @ResponseBody
    public Object selectGroupDropdown(HttpServletRequest reuqest){
    	
    	Map map = new HashMap();
    	map.put("configCode",reuqest.getParameter("configCode"));
    	map.put("hotelGroupId",reuqest.getParameter("hotelGroupId"));
    	map.put("hotelId",reuqest.getParameter("hotelId"));
    	
    	return JSONObject.toJSON(pp01Srv.selectConfigCodeApp(map));
    }
    

}