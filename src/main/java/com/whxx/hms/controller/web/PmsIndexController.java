package com.whxx.hms.controller.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.whxx.core.controller.BaseController;
import com.whxx.core.utils.AppUtils;
import com.whxx.core.utils.CommUtil;
import com.whxx.core.utils.FormatUtil;
import com.whxx.hms.service.PmsIndexSrv;
import com.whxx.hms.service.ReservationSrv;

import ch.qos.logback.classic.Logger;
import cn.hutool.http.HttpStatus;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


/**
 * PMS后台首页接口
 * @ClassName: indexController 
 * @Description: TODO
 * @author: zengxin
 * @date: 2018年6月4日 下午5:25:15
 */
@RestController
@RequestMapping(value = "/index")
@SuppressWarnings({"rawtypes"})
public class PmsIndexController extends BaseController{
	
	private Logger logger = (Logger) AppUtils.getLogger("index-info", true);

	@Autowired
	PmsIndexSrv pmsIndexSrv;
	
	@Autowired
	ReservationSrv reservationSrv;
	
	@ApiOperation(value = "首页的留言信息小图标", notes = "首页的留言信息小图标")
	@RequestMapping(value = "/messagesIcon",method = RequestMethod.POST)
	public Map messagesList(
			@ApiParam(value = "hotelGroupId", required = true) @RequestParam(value = "hotelGroupId", required = true) Integer hotelGroupId,
			@ApiParam(value = "hotelId", required = true) @RequestParam(value = "hotelId", required = true) Integer hotelId){
		logger.info("==========开始查询留言信息=============");
		if(null == hotelGroupId || null == hotelId){
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "页面参数为空", hotelGroupId);
		}
		Map messagesListMap = reservationSrv.messagesList(hotelGroupId,hotelId);
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "首页的留言信息小图标列表查询成功", messagesListMap);
	}
	
	@ApiOperation(value = "首页的金额汇总和留言预览前3条", notes = "首页的金额汇总和留言预览前3条")
	@RequestMapping(value = "/totalPayMessages",method = RequestMethod.POST)
	public Map totalMoneyAndMessageList(
			@ApiParam(value = "集团ID", required = true) @RequestParam(value = "hotelGroupId", required = true) Integer hotelGroupId,
			@ApiParam(value = "hotelId", required = true) @RequestParam(value = "hotelId", required = true) Integer hotelId){
		logger.info("==========开始查询首页的金额汇总和留言预览前3条=============");
		if(null == hotelGroupId || null == hotelId){
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "页面参数为空", hotelGroupId);
		}
		Map moneyMap = pmsIndexSrv.indexTotal2Messages(hotelGroupId,hotelId);
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "查询首页的信息成功", moneyMap);
	}
	
	@ApiOperation(value = "首页的四种不同状态列表", notes = "首页的四种不同状态列表（本日未到、本日已到、本日未离、本日已离）")
	@RequestMapping(value = "/dayList",method = RequestMethod.POST)
	public Map fourDiffStatusList(
			@ApiParam(value = "hotelGroupId", required = true) @RequestParam(value = "hotelGroupId", required = true) Integer hotelGroupId,
			@ApiParam(value = "hotelId", required = true) @RequestParam(value = "hotelId", required = true) Integer hotelId,
			@ApiParam(value = "状态类型（本日未到、本日未离、本日已到、本日已离分别为1,2,3,4）", required = true) @RequestParam(value = "statusType", required = true) String statusType){
		logger.info("==========开始查询首页的四种不同状态列表=============");
		if(null == hotelGroupId || null == hotelId || "".equals(FormatUtil.toString(statusType))){
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "页面参数为空", hotelGroupId);
		}
		Map statusMap = pmsIndexSrv.fourStatusList(hotelGroupId,hotelId,statusType);
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "查询首页的信息成功", statusMap);
	}
	
	
	@ApiOperation(value = "首页的客房状态图", notes = "首页的客房状态图")
	@RequestMapping(value = "/roomGraph",method = RequestMethod.POST)
	public Map indexGuestRoomStatusGraph(
			@ApiParam(value = "hotelGroupId", required = true) @RequestParam(value = "hotelGroupId", required = true) Integer hotelGroupId,
			@ApiParam(value = "hotelId", required = true) @RequestParam(value = "hotelId", required = true) Integer hotelId){
		logger.info("==========开始查询金额汇总=============");
		if(null == hotelGroupId || null == hotelId){
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "页面参数为空", hotelGroupId);
		}
		Map roomStatusGraph = pmsIndexSrv.roomStatusGraph(hotelGroupId,hotelId);
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "查询首页的信息成功", roomStatusGraph);
	}
	
	@ApiOperation(value = "首页的入住状态图", notes = "首页的入住状态图")
	@RequestMapping(value = "/inStatusGraph",method = RequestMethod.POST)
	public Map indexRoomInGraph(
			@ApiParam(value = "hotelGroupId", required = true) @RequestParam(value = "hotelGroupId", required = true) Integer hotelGroupId,
			@ApiParam(value = "hotelId", required = true) @RequestParam(value = "hotelId", required = true) Integer hotelId){
		logger.info("==========开始查询首页的入住列表=============");
		if(null == hotelGroupId || null == hotelId){
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "页面参数为空", hotelGroupId);
		}
		Map statusMap = pmsIndexSrv.inStatusGraph(hotelGroupId,hotelId);
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "查询首页的信息成功", statusMap);
	}
	
	
	@ApiOperation(value = "首页的数据表格", notes = "首页的数据表格")
	@RequestMapping(value = "/datatable",method = RequestMethod.POST)
	public Map indexDatatable(
			@ApiParam(value = "hotelGroupId", required = true) @RequestParam(value = "hotelGroupId", required = true) Integer hotelGroupId,
			@ApiParam(value = "hotelId", required = true) @RequestParam(value = "hotelId", required = true) Integer hotelId,
			@ApiParam(value = "date", required = true) @RequestParam(value = "date", required = true) String date){
		logger.info("==========开始查询首页的数据表格=============");
		if(null == hotelGroupId || null == hotelId || "".equals(date)){
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "页面参数为空", hotelGroupId);
		}
		Map statusMap = pmsIndexSrv.indexDatatable(date,hotelGroupId,hotelId);
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "查询首页的信息成功", statusMap);
	}
	
	@ApiOperation(value = "处理留言信息", notes = "处理留言信息")
	@RequestMapping(value = "/updateMessages",method = RequestMethod.POST)
	public Map dealMessages(@ApiParam(value = "留言主键id", required = true) @RequestParam(value = "messagesId", required = true) String messagesId){
		logger.info("==========开始预订单新增留言信息=============");
		//预订单留言信息
		return reservationSrv.updateMessages(messagesId);
	}
	
}
