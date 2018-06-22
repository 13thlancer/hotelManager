package com.whxx.hms.controller.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.whxx.core.controller.BaseController;
import com.whxx.core.utils.AppUtils;
import com.whxx.core.utils.CommUtil;
import com.whxx.core.utils.FormatUtil;
import com.whxx.core.utils.HttpUtil;
import com.whxx.core.utils.ToolUtil;
import com.whxx.hms.model.ModifyLogs;
import com.whxx.hms.model.Reservation;
import com.whxx.hms.model.RoomPerson;
import com.whxx.hms.model.dto.DropdownDto;
import com.whxx.hms.model.dto.HotelGroupId;
import com.whxx.hms.model.dto.MessageDto;
import com.whxx.hms.model.dto.PreLicenceDto;
import com.whxx.hms.model.dto.PreLicenceListDto;
import com.whxx.hms.model.dto.PrepayDto;
import com.whxx.hms.model.dto.RateCodeDto;
import com.whxx.hms.model.dto.RoomDto;
import com.whxx.hms.model.dto.RoomPersonListDto;
import com.whxx.hms.model.dto.RoomTypeDto;
import com.whxx.hms.model.dto.SubInfoDto;
import com.whxx.hms.service.ModifyLogsSrv;
import com.whxx.hms.service.ReservationSrv;
import com.whxx.hms.service.RsvManSrv;
import com.whxx.hms.service.SubInfoSrv;

import ch.qos.logback.classic.Logger;
import cn.hutool.http.HttpStatus;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


/**
 * PMS后台预订接口
 * @ClassName: PreorderController 
 * @Description: TODO
 * @author: zengxin
 * @date: 2018年5月30日 上午10:43:34
 */
@RestController
@RequestMapping(value = "/reservation")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReservationController extends BaseController{
	
	private Logger logger = (Logger) AppUtils.getLogger("order-mainInfo", true);

	@Autowired
	ReservationSrv reservationSrv;
	
	@Autowired
	RsvManSrv rsvManSrv;
	
	@Autowired
	SubInfoSrv subInfoSrv;
	
	@Autowired
	ModifyLogsSrv modifyLogsSrv;
	
	@ApiOperation(value = "查询通用代码档案", notes = "查询通用代码档案")
	@RequestMapping(value = "/configCode",method = RequestMethod.POST)
	public Map configCodeDropList(
			@ApiParam(value = "酒店集团ID", required = true) @RequestParam(value = "hotelGroupId", required = true) Integer hotelGroupId,
			@ApiParam(value = "酒店ID", required = true) @RequestParam(value = "hotelId", required = true) Integer hotelId,
			@ApiParam(value = "configCode", required = true) @RequestParam(value = "configCode", required = true) String configCode,
			HttpServletRequest request, HttpServletResponse response){
		Map map = new HashMap<>();
		Map paramMap = HttpUtil.getParamMap(request);
		logger.info("==========开始查询通用代码档案=============");
		if(ToolUtil.isEmpty(paramMap)){
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "页面参数为空", paramMap);
		}
		List<DropdownDto> configCodeList = this.reservationSrv.selectConfigCode(paramMap);
		map.put("configCodeList", configCodeList);
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "查询通用代码档成功", map);
	}
	
	
	@ApiOperation(value = "房型下拉框", notes = "房型下拉框")
	@RequestMapping(value = "/roomtypeList",method = RequestMethod.POST)
	public Map roomTypeDropList(@ApiParam(value = "酒店集团ID", required = true) @RequestParam(value = "hotelGroupId", required = true) Integer hotelGroupId,
			@ApiParam(value = "酒店ID", required = true) @RequestParam(value = "hotelId", required = true) Integer hotelId,
			@ApiParam(value = "到达时间（yyyy-MM-dd hh:mm:ss）", required = true) @RequestParam(value = "planStart", required = true) String planStart,HttpServletRequest request, HttpServletResponse response){
		Map map = new HashMap<>();
		logger.info("==========开始查询房型下拉框=============");
		if(ToolUtil.isEmpty(hotelGroupId) || ToolUtil.isEmpty(hotelId) || ToolUtil.isEmpty(planStart)){
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "页面参数为空", hotelGroupId);
		}
		Map paramMap = new HashMap<>();
		paramMap.put("hotelId", hotelId);
		paramMap.put("hotelGroupId", hotelGroupId);
		paramMap.put("planStart", planStart);
		List<RoomTypeDto> roomTypeList = this.reservationSrv.listRoomTypeApp(paramMap);
		map.put("roomTypeList", roomTypeList);
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "查询房型下拉框成功", map);
	}
	
	
	@ApiOperation(value = "房价码下拉框", notes = "房价码下拉框")
	@RequestMapping(value = "/ratecodeList",method = RequestMethod.POST)
	public Map ratecodeDropList(@ApiParam(value = "酒店集团ID", required = true) @RequestParam(value = "hotelGroupId", required = true) Integer hotelGroupId,
			@ApiParam(value = "酒店ID", required = true) @RequestParam(value = "hotelId", required = true) Integer hotelId,
			@ApiParam(value = "到达时间（yyyy-MM-dd hh:mm:ss）", required = true) @RequestParam(value = "planStart", required = true) String planStart,
			@ApiParam(value = "到达时间（yyyy-MM-dd hh:mm:ss）", required = true) @RequestParam(value = "planEnd", required = true) String planEnd,
			@ApiParam(value = "房型", required = true) @RequestParam(value = "roomType", required = true) String roomType,
			@ApiParam(value = "房价码类型", required = true) @RequestParam(value = "priceType", required = true) String priceType,HttpServletRequest request, HttpServletResponse response){
		Map map = new HashMap<>();
		logger.info("==========开始查询房价码下拉框=============");
		if(ToolUtil.isEmpty(hotelGroupId) || ToolUtil.isEmpty(hotelId)){
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "页面参数为空", hotelGroupId);
		}
		Map paramMap = new HashMap<>();
		paramMap.put("hotelId", hotelId);
		paramMap.put("hotelGroupId", hotelGroupId);
		paramMap.put("planStart", planStart);
		paramMap.put("planEnd", planEnd);
		paramMap.put("roomType", roomType);
		paramMap.put("priceType", priceType);
		List<RateCodeDto> rateCodeList = this.reservationSrv.ratecodeDropList(paramMap);
		map.put("rateCodeList", rateCodeList);
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "查询房价码下拉框成功", map);
	}
	
	@ApiOperation(value = "销售员下拉框", notes = "销售员下拉框")
	@RequestMapping(value = "/salesManList",method = RequestMethod.POST)
	public Map salesMandropList(
			@ApiParam(value = "酒店集团ID", required = true) @RequestParam(value = "hotelGroupId", required = true) Integer hotelGroupId,
			@ApiParam(value = "酒店ID", required = true) @RequestParam(value = "hotelId", required = true) Integer hotelId,HttpServletRequest request, HttpServletResponse response){
		Map map = new HashMap<>();
		logger.info("==========开始查询销售员下拉框=============");
		if(ToolUtil.isEmpty(hotelGroupId) || ToolUtil.isEmpty(hotelId)){
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "页面参数为空", hotelGroupId);
		}
		Map paramMap = new HashMap<>();
		paramMap.put("hotelId", hotelId);
		paramMap.put("hotelGroupId", hotelGroupId);
		List<DropdownDto> salesManList = this.reservationSrv.salesManDropDownList(paramMap);
		map.put("salesManList", salesManList);
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "销售员下拉框成功", map);
	}
	
	@ApiOperation(value = "佣金码下拉框", notes = "佣金码下拉框")
	@RequestMapping(value = "/commissionCodeList",method = RequestMethod.POST)
	public Map commissionCodeList(HotelGroupId hotel,HttpServletRequest request, HttpServletResponse response){
		Map map = new HashMap<>();
		logger.info("==========开始佣金码下拉框=============");
		if(ToolUtil.isEmpty(hotel)){
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "页面参数为空", hotel);
		}
		List<DropdownDto> commissionCodeList = this.reservationSrv.commissionCodeDropList(hotel);
		map.put("commissionCodeList", commissionCodeList);
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "佣金码下拉框成功", map);
	}
	
	@ApiOperation(value = "团队预订组团单位下拉框", notes = "团队预订组团单位下拉框")
	@RequestMapping(value = "/groupList",method = RequestMethod.POST)
	public Map groupDropList(HotelGroupId hotel,HttpServletRequest request, HttpServletResponse response){
		Map map = new HashMap<>();
		logger.info("==========开始查询组团单位下拉框=============");
		if(ToolUtil.isEmpty(hotel)){
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "页面参数为空", hotel);
		}
		List<DropdownDto> groupList = this.rsvManSrv.selectGroupDropList(hotel);
		map.put("groupList", groupList);
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "查询协议单位下拉框成功", map);
	}
	
	
	@ApiOperation(value = "付款方式下拉框", notes = "付款方式下拉框")
	@RequestMapping(value = "/paycodeList",method = RequestMethod.POST)
	public Map payCodeList(HotelGroupId hotel,HttpServletRequest request, HttpServletResponse response){
		Map map = new HashMap<>();
		logger.info("==========开始付款方式下拉框=============");
		if(ToolUtil.isEmpty(hotel)){
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "页面参数为空", hotel);
		}
		List<DropdownDto> paycodeList = this.reservationSrv.payCodeDropList(hotel);
		map.put("paycodeList", paycodeList);
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "付款方式下拉框成功", map);
	}
	
	@ApiOperation(value = "预授权付款方式下拉框", notes = "预授权付款方式下拉框")
	@RequestMapping(value = "/prelicencePaycodeList",method = RequestMethod.POST)
	public Map prelicenceDropList(HotelGroupId hotel,HttpServletRequest request, HttpServletResponse response){
		Map map = new HashMap<>();
		logger.info("==========开始预授权付款方式下拉框=============");
		if(ToolUtil.isEmpty(hotel)){
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "页面参数为空", hotel);
		}
		List<DropdownDto> paycodeList = this.reservationSrv.paylicenceCodeDropList(hotel);
		map.put("paycodeList", paycodeList);
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "查询预授权付款方式下拉框成功", map);
	}
	
	@ApiOperation(value = "(新增)修改预订单", notes = "(新增)修改预订单")
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	public Map updateNewReservation(@RequestBody Reservation reservation, HttpServletRequest request, HttpServletResponse response){
		logger.info("==========开始修改预订单=============");
		if(ToolUtil.isEmpty(reservation)){
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "页面参数为空", reservation);
		}
		//修改预订单
		Map newRsv = reservationSrv.updateNewRsv(reservation);
		logger.info("==========新增预订单结果=============" + newRsv);
		if(200 == FormatUtil.toInt(newRsv.get("code"))){
			//预订单号
			String priMainId = FormatUtil.toString(newRsv.get("data"));
			//返回预订单查询结果
			Reservation rsvResult = reservationSrv.getNewRsvResult(priMainId);
			logger.info("==========查询预订单结果=============" + rsvResult);
			return CommUtil.setMessage(HttpStatus.HTTP_OK, "查询预订结果成功", rsvResult);
		}
		logger.info("==========新增预订单状态码不为200=============" + newRsv);
		return newRsv;
	}
	
	@ApiOperation(value = "根据预订单号查询详情", notes = "根据预订单号查询详情")
	@RequestMapping(value = "/detailByPriMainId",method = RequestMethod.POST)
	public Map detailByPriMainId(@ApiParam(value = "主预订单ID", required = true) @RequestParam(value = "priMainId", required = true) String priMainId){
		logger.info("==========开始根据预订单号查询详情=============");
		Map map = new HashMap<>();
		if(ToolUtil.isEmpty(priMainId)){
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "页面参数为空", priMainId);
		}
		//返回预订单查询结果
		Reservation rsvResult = reservationSrv.getNewRsvResult(priMainId);
		logger.info("==========查询预订单结果=============" + rsvResult);
		//预订页面4步数据
		map.put("Reservation", rsvResult);
		List<PreLicenceDto> prelicenceList = this.reservationSrv.prelicenceList(priMainId);
		//预授权list
		map.put("prelicenceList", prelicenceList);
		//预收款
		map.put("prepay", new PrepayDto());
		List<SubInfoDto> orderList = this.reservationSrv.preorderlist(priMainId);
		//订单明细列表
		map.put("preorderList", orderList);
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "查询预订结果成功", map);
	}
	
	
	@ApiOperation(value = "子订单明细列表", notes = "子订单明细列表")
	@RequestMapping(value = "/orderList",method = RequestMethod.POST)
	public Map preorderList(@ApiParam(value = "主预订单ID", required = true) @RequestParam(value = "priMainId", required = true) String priMainId){
		logger.info("==========开始查询子订单明细列表=============");
		Map map = new HashMap<>();
		if(ToolUtil.isEmpty(priMainId)){
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "页面参数为空", priMainId);
		}
		List<SubInfoDto> orderList = this.reservationSrv.preorderlist(priMainId);
		map.put("preorderList", orderList);
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "子订单明细列表查询成功", map);
	}
	
	@ApiOperation(value = "删除预订单", notes = "删除预订单")
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	public Map deleteNewReservation(@ApiParam(value = "主预订单主键", required = true) @RequestParam(value = "mainInfoId", required = true) String mainInfoId){
		logger.info("==========开始删除预订单=============");
		if(ToolUtil.isEmpty(mainInfoId)){
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "页面参数为空", mainInfoId);
		}
		//删除预订单
		return reservationSrv.deleteNewRsv(mainInfoId);
	}
	
	@ApiOperation(value = "新增预订单预收款", notes = "新增预订单预收款")
	@RequestMapping(value = "/addPrepay",method = RequestMethod.POST)
	public Map createRsvPrepay(@RequestBody PrepayDto prepayDto, HttpServletRequest request, HttpServletResponse response){
		logger.info("==========开始预订单预收款=============");
		if(ToolUtil.isEmpty(prepayDto)){
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "页面参数为空", prepayDto);
		}
		//新增预订单预收款
		Map prepayMap = reservationSrv.prepay2NewRsv(prepayDto);
		if(200 == FormatUtil.toInt(prepayMap.get("code"))){
			//预订单号
			String priMainId = FormatUtil.toString(prepayMap.get("data"));
			//返回预订单查询结果
			Reservation rsvResult = reservationSrv.getNewRsvResult(priMainId);
			return CommUtil.setMessage(HttpStatus.HTTP_OK, "查询预订结果成功", rsvResult);
		}
		return prepayMap;
	}
	
	@ApiOperation(value = "新增预订单预授权", notes = "新增预订单预授权")
	@RequestMapping(value = "/addPrelicence",method = RequestMethod.POST)
	public Map createRsvPreLicence(@RequestBody PreLicenceListDto preLicenceListDto, HttpServletRequest request, HttpServletResponse response){
		logger.info("==========开始预订单预授权=============");
		if(ToolUtil.isEmpty(preLicenceListDto)){
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "页面参数为空", preLicenceListDto);
		}
		//新增预订单预授权
		Map preLicenceMap = reservationSrv.prelicence2NewRsv(preLicenceListDto);
		if(200 == FormatUtil.toInt(preLicenceMap.get("code"))){
			//预订单号
			String priMainId = FormatUtil.toString(preLicenceMap.get("data"));
			//返回预订单查询结果
			Reservation rsvResult = reservationSrv.getNewRsvResult(priMainId);
			return CommUtil.setMessage(HttpStatus.HTTP_OK, "查询预订结果成功", rsvResult);
		}
		return preLicenceMap;
	}
	
	@ApiOperation(value = "预订单预授权列表", notes = "预订单预授权列表")
	@RequestMapping(value = "/prelicenceList",method = RequestMethod.POST)
	public Map RsvPreLicenceList(@ApiParam(value = "主预订单ID", required = true) @RequestParam(value = "priMainId", required = true) String priMainId){
		logger.info("==========开始预订单预授权=============");
		if(ToolUtil.isEmpty(priMainId)){
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "页面参数为空", priMainId);
		}
		//预订单预授权列表
		Map map = new HashMap<>();
		List<PreLicenceDto> prelicenceList = this.reservationSrv.prelicenceList(priMainId);
		map.put("prelicenceList", prelicenceList);
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "查询预订结果成功", map);
	}
	
	
	@ApiOperation(value = "修改预订单预授权", notes = "修改预订单预授权")
	@RequestMapping(value = "/updatePrelicence",method = RequestMethod.POST)
	public Map updateRsvPreLicence(@RequestBody PreLicenceListDto preLicenceListDto, HttpServletRequest request, HttpServletResponse response){
		logger.info("==========开始预订单预授权=============");
		if(ToolUtil.isEmpty(preLicenceListDto)){
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "页面参数为空", preLicenceListDto);
		}
		//新增预订单预授权
		Map preLicenceMap = reservationSrv.updatePrelicence(preLicenceListDto);
		if(200 == FormatUtil.toInt(preLicenceMap.get("code"))){
			//预订单号
			String priMainId = FormatUtil.toString(preLicenceMap.get("data"));
			//返回预订单查询结果
			Reservation rsvResult = reservationSrv.getNewRsvResult(priMainId);
			return CommUtil.setMessage(HttpStatus.HTTP_OK, "查询预订结果成功", rsvResult);
		}
		return preLicenceMap;
	}
	
	@ApiOperation(value = "取消预订单预授权", notes = "取消预订单预授权")
	@RequestMapping(value = "/cancelPrelicence",method = RequestMethod.POST)
	public Map deleteRsvPreLicence(@RequestBody PreLicenceDto preLicenceDto, HttpServletRequest request, HttpServletResponse response){
		logger.info("==========开始预订单预授权=============");
		if(ToolUtil.isEmpty(preLicenceDto)){
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "页面参数为空", preLicenceDto);
		}
		return reservationSrv.cancelPrelicence(preLicenceDto);
	}
	
	@ApiOperation(value = "预授权转移", notes = "预授权转移")
	@RequestMapping(value = "/prelicenceTansfer",method = RequestMethod.POST)
	public Map preLicenceTransfer(@RequestBody PreLicenceDto preLicence, HttpServletRequest request, HttpServletResponse response){
		logger.info("==========开始预订单预授权=============");
		if(ToolUtil.isEmpty(preLicence)){
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "页面参数为空", preLicence);
		}
		return  reservationSrv.transferPrelicence(preLicence);
	}
	
	
	@ApiOperation(value = "预授权转移列表", notes = "预授权转移列表")
	@RequestMapping(value = "/transferList",method = RequestMethod.POST)
	public Map transferList(
			@ApiParam(value = "酒店集团ID", required = true) @RequestParam(value = "hotelGroupId", required = true) Integer hotelGroupId,
			@ApiParam(value = "酒店ID", required = true) @RequestParam(value = "hotelId", required = true) Integer hotelId,
			@ApiParam(value = "搜索内容", required = false) @RequestParam(value = "searchContent", required = false) String searchContent,
			@ApiParam(value = "预订单状态", required = false) @RequestParam(value = "mainStatus", required = false) String mainStatus){
		logger.info("==========开始查询预授权转移列表=============");
		Map map = new HashMap<>();
		Map paramMap = new HashMap<>();
		paramMap.put("hotelId", hotelId);
		paramMap.put("hotelGroupId", hotelGroupId);
		paramMap.put("search", searchContent);
		paramMap.put("mainStatus", mainStatus);
		List<SubInfoDto> transferList = this.reservationSrv.transferList(paramMap);
		map.put("transferList", transferList);
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "预授权转移列表查询成功", map);
	}
	
	
	@ApiOperation(value = "预授权日志", notes = "预授权日志")
	@RequestMapping(value = "/prelicenceLogs",method = RequestMethod.POST)
	public Map prelicenceLogs(@ApiParam(value = "主预订单ID", required = true) @RequestParam(value = "priMainId", required = true) String priMainId){
		logger.info("==========开始查询预授权日志=============");
		Map map = new HashMap<>();
		List<PreLicenceDto> prelicenceLogs = this.reservationSrv.prelicenceLogs(priMainId);
		map.put("prelicenceLogs", prelicenceLogs);
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "预授权日志查询成功", map);
	}
	
	
	@ApiOperation(value = "根据房型查询房间列表", notes = "根据房型查询房间列表")
	@RequestMapping(value = "/roomlist",method = RequestMethod.POST)
	public Map roomListbyroomType(
			@ApiParam(value = "酒店集团ID", required = true) @RequestParam(value = "hotelGroupId", required = true) String hotelGroupId,
			@ApiParam(value = "酒店ID", required = true) @RequestParam(value = "hotelId", required = true) String hotelId,
			@ApiParam(value = "房型", required = true) @RequestParam(value = "roomType", required = true) String roomType,
			@ApiParam(value = "标记(如VC,VD,OC,OD),默认勾选VC", required = true) @RequestParam(value = "sign", required = true) String sign,
			@ApiParam(value = "楼层", required = false) @RequestParam(value = "floorName", required = false) String floorName){
		logger.info("==========开始查询排房的房间列表=============");
		Map param = new HashMap<>();
		param.put("hotelGroupId", hotelGroupId);
		param.put("hotelId", hotelId);
		param.put("roomTypeCode", roomType);
		param.put("floorName", floorName);
		param.put("sign", sign);
		List<RoomDto> roomList = reservationSrv.roomDropDownList(param);
		Map result = new HashMap<>();
		result.put("roomlist", roomList);
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "根据房型查询房间列表成功", result);
	}
	
	@ApiOperation(value = "预订单排房", notes = "预订单排房")
	@RequestMapping(value = "/arrange",method = RequestMethod.POST)
	public Map arrangeRoom4Rsv(
			@ApiParam(value = "明细主键", required = true) @RequestParam(value = "subInfoId", required = true) String subInfoId,
			@ApiParam(value = "子订单号", required = true) @RequestParam(value = "subId", required = true) String subId,
			@ApiParam(value = "房号", required = true) @RequestParam(value = "roomCode", required = true) String roomCode){
		logger.info("==========开始预订单预订单排房=============");
		if(ToolUtil.isEmpty(subId) || ToolUtil.isEmpty(roomCode)){
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "页面参数为空", roomCode);
		}
		Map param = new HashMap<>();
		param.put("subId", subId);
		param.put("roomCode", roomCode);
		param.put("subInfoId", subInfoId);
		param.put("inStatus", "R");//排房
		return reservationSrv.updateSubInfo(param);
	}
	
	
	@ApiOperation(value = "预订单入住", notes = "预订单入住")
	@RequestMapping(value = "/toIn",method = RequestMethod.POST)
	public Map toInRoom4Rsv(
			@ApiParam(value = "明细主键", required = true) @RequestParam(value = "subInfoId", required = true) String subInfoId,
			@ApiParam(value = "子订单号", required = true) @RequestParam(value = "subId", required = true) String subId,
			@ApiParam(value = "房号", required = true) @RequestParam(value = "roomCode", required = true) String roomCode){
		logger.info("==========开始修改订单的入住状态=============");
		if(ToolUtil.isEmpty(subId) || ToolUtil.isEmpty(roomCode)){
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "页面参数为空", subId);
		}
		Map param = new HashMap<>();
		param.put("subId", subId);
		param.put("roomCode", roomCode);
		param.put("subInfoId", subInfoId);
		param.put("inStauts", "I");
		return reservationSrv.updateSubInfo(param);
	}
	
	
	@ApiOperation(value = "预订单修改备注", notes = "预订单修改备注")
	@RequestMapping(value = "/updateRemark",method = RequestMethod.POST)
	public Map updateRemark(
			@ApiParam(value = "明细主键", required = true) @RequestParam(value = "subInfoId", required = true) String subInfoId,
			@ApiParam(value = "备注", required = true) @RequestParam(value = "remark", required = true) String remark){
		logger.info("==========开始修改订单的备注=============");
		if(ToolUtil.isEmpty(subInfoId)){
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "页面参数为空", subInfoId);
		}
		Map param = new HashMap<>();
		param.put("subInfoId", subInfoId);
		param.put("remark", remark);
		return reservationSrv.updateSubInfo(param);
	}
	
	@ApiOperation(value = "查询订单入住人信息", notes = "查询订单入住人信息")
	@RequestMapping(value = "/rsvManList",method = RequestMethod.POST)
	public Map selectPersonList2Rsv(@ApiParam(value = "子订单号", required = true) @RequestParam(value = "subId", required = true) String subId){
		logger.info("==========查询预订单入住人信息=============");
		if(ToolUtil.isEmpty(subId)){
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "页面参数为空", subId);
		}
		Map map = new HashMap<>();
		List<RoomPerson> personList = this.reservationSrv.selectBySubId(subId);
		map.put("personList", personList);
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "页面参数为空", map);
	}
	
	@ApiOperation(value = "预订单新增入住人信息", notes = "预订单新增入住人信息")
	@RequestMapping(value = "/newRsvman",method = RequestMethod.POST)
	public Map createMan2Rsv(@RequestBody RoomPersonListDto roomPersonListDto, HttpServletRequest request, HttpServletResponse response){
		logger.info("==========新增入住人信息=============");
		if(ToolUtil.isEmpty(roomPersonListDto)){
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "页面参数为空", roomPersonListDto);
		}
		return reservationSrv.createRoomPerson(roomPersonListDto);
	}
	
	@ApiOperation(value = "预订单修改入住人信息", notes = "预订单修改入住人信息")
	@RequestMapping(value = "/updateRsvman",method = RequestMethod.POST)
	public Map updateMan2Rsv(@RequestBody RoomPersonListDto roomPersonListDto, HttpServletRequest request, HttpServletResponse response){
		logger.info("==========修改入住人信息=============");
		if(ToolUtil.isEmpty(roomPersonListDto)){
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "页面参数为空", roomPersonListDto);
		}
		return reservationSrv.updateRoomPerson(roomPersonListDto);
	}
	
	@ApiOperation(value = "预订单删除入住人信息", notes = "预订单删除入住人信息")
	@RequestMapping(value = "/deleteRsvman",method = RequestMethod.POST)
	public Map deleteMan2Rsv(@ApiParam(value = "入住人信息主键", required = true) @RequestParam(value = "roomPersonId", required = true) String roomPersonId){
		logger.info("==========预订单删除入住人信息=============");
		if(ToolUtil.isEmpty(roomPersonId)){
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "页面参数为空", roomPersonId);
		}
		return reservationSrv.deleteRoomPerson(roomPersonId);
	}
	
	@ApiOperation(value = "新增留言信息", notes = "新增留言信息")
	@RequestMapping(value = "/insertMessages",method = RequestMethod.POST)
	public Map RsvNewMessages(@RequestBody MessageDto messageDto, HttpServletRequest request, HttpServletResponse response){
		logger.info("==========开始预订单新增留言信息=============");
		if(ToolUtil.isEmpty(messageDto)){
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "页面参数为空", messageDto);
		}
		//预订单留言信息
		return reservationSrv.message2NewRsv(messageDto);
	}
	
	@ApiOperation(value = "取消单笔预订单", notes = "取消单笔预订单")
	@RequestMapping(value = "/cancelItem",method = RequestMethod.POST)
	public Map cancelRsvSingelItem(@ApiParam(value = "预订单主键ID", required = true) @RequestParam(value = "subInfoId", required = true) String subInfoId){
		logger.info("==========开始取消单笔预订单=============");
		if(ToolUtil.isEmpty(subInfoId)){
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "页面参数为空", subInfoId);
		}
		Map param = new HashMap<>();
		param.put("subInfoId", subInfoId);
		param.put("cancel", "single");
		return reservationSrv.cancelRsv(param);
	}
	
	
	@ApiOperation(value = "取消整个预订单", notes = "取消整个预订单")
	@RequestMapping(value = "/cancelAll",method = RequestMethod.POST)
	public Map cancelRsv(@ApiParam(value = "预订单号", required = true) @RequestParam(value = "priMainId", required = true) String priMainId){
		logger.info("==========开始取消整个预订单=============");
		if(ToolUtil.isEmpty(priMainId)){
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "页面参数为空", priMainId);
		}
		Map param = new HashMap<>();
		param.put("priMainId", priMainId);
		param.put("cancel", "all");
		return reservationSrv.cancelRsv(param);
	}
	
	@ApiOperation(value = "预订单明细列表日志", notes = "预订单明细列表日志")
	@RequestMapping(value = "/orderLog",method = RequestMethod.POST)
	public Map preorderListLogs(@ApiParam(value = "预订单号", required = true) @RequestParam(value = "priMainId", required = true) String priMainId){
		logger.info("==========开始取消整个预订单=============");
		if(ToolUtil.isEmpty(priMainId)){
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "页面参数为空", priMainId);
		}
		Map map = new HashMap<>();
		ModifyLogs modifyLogs = new ModifyLogs();
		modifyLogs.setTableName("subInfo");
		modifyLogs.setPriMainId(priMainId);
		List<ModifyLogs> logList = this.reservationSrv.selectByPriMainId(modifyLogs);
		map.put("logList", logList);
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "预订单明细列表日志", map);
	}
	
	
	@ApiOperation(value = "批量处理列表", notes = "批量处理列表")
	@RequestMapping(value = "/batchOrderList",method = RequestMethod.POST)
	public Map batchOrderList(@ApiParam(value = "主表主预订单号", required = true) @RequestParam(value = "priMainId", required = true) String priMainId){
		Map map = new HashMap<>();
		logger.info("==========开始查询批量处理列表=============");
		if(ToolUtil.isEmpty(priMainId)){
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "页面参数为空", priMainId);
		}
		List<SubInfoDto> subInfoList = this.subInfoSrv.batchOrderList(priMainId);
		map.put("batchOrderList", subInfoList);
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "查询查询批量处理列表成功", map);
	}
	
	@ApiOperation(value = "自动排房", notes = "自动排房")
	@RequestMapping(value = "/autoArrangeRoom",method = RequestMethod.POST)
	public Map autoArrangeRoom(@ApiParam(value = "主表主预订单号", required = true) @RequestParam(value = "priMainId", required = true) String priMainId){
		Map map = new HashMap<>();
		logger.info("==========开始查询批量处理列表=============");
		if(ToolUtil.isEmpty(priMainId)){
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "页面参数为空", priMainId);
		}
		Map subInfoList = this.reservationSrv.autoArrangeRoom(priMainId);
		map.put("autoArrangeRoomList", subInfoList);
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "查询查询批量处理列表成功", map);
	}
	
	
	@ApiOperation(value = "批量选择页面", notes = "批量选择页面(标记、楼栋、楼层、特征、房间号)")
	@RequestMapping(value = "/batchChooseRoom",method = RequestMethod.POST)
	public Map batchChooseRoom(
		@ApiParam(value = "酒店集团ID", required = true) @RequestParam(value = "hotelGroupId", required = true) Integer hotelGroupId,
		@ApiParam(value = "酒店ID", required = true) @RequestParam(value = "hotelId", required = true) Integer hotelId,	
		@ApiParam(value = "标记", required = false) @RequestParam(value = "sign", required = false) String sign,
		@ApiParam(value = "楼栋", required = false) @RequestParam(value = "building", required = false) String building,
		@ApiParam(value = "楼层", required = false) @RequestParam(value = "floor", required = false) String floor,
		@ApiParam(value = "客房特征", required = false) @RequestParam(value = "roomSpec", required = false) String roomSpec){
		logger.info("==========开始查询批量选择房间列表=============");
		Map paramMap = new HashMap<>();
		paramMap.put("hotelGroupId", hotelGroupId);
		paramMap.put("hotelId", hotelId);
		paramMap.put("sign", sign);
		paramMap.put("building", building);
		paramMap.put("floor", floor);
		paramMap.put("roomSpec", roomSpec);
		return this.reservationSrv.batchChooseRoom(paramMap);
	}
	
}
