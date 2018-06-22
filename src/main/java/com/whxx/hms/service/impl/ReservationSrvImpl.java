package com.whxx.hms.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.whxx.core.controller.BaseController;
import com.whxx.core.utils.AppUtils;
import com.whxx.core.utils.CommUtil;
import com.whxx.core.utils.DateUtil;
import com.whxx.core.utils.FormatUtil;
import com.whxx.core.utils.ToolUtil;
import com.whxx.core.utils.UUIDUtil;
import com.whxx.hms.dao.BuildingMapper;
import com.whxx.hms.dao.CommissionCodeMapper;
import com.whxx.hms.dao.ConsumeMapper;
import com.whxx.hms.dao.FloorMapper;
import com.whxx.hms.dao.MainInfoMapper;
import com.whxx.hms.dao.MessagesMapper;
import com.whxx.hms.dao.ModifyLogsMapper;
import com.whxx.hms.dao.PayCodeMapper;
import com.whxx.hms.dao.PriceCodeResMapper;
import com.whxx.hms.dao.ReservationMapper;
import com.whxx.hms.dao.RoomMapper;
import com.whxx.hms.dao.RoomPersonMapper;
import com.whxx.hms.dao.RoomStatusMapper;
import com.whxx.hms.dao.RoomTypeMapper;
import com.whxx.hms.dao.SalesManMapper;
import com.whxx.hms.dao.SubInfoMapper;
import com.whxx.hms.dao.Tbpp01aMapper;
import com.whxx.hms.model.Consume;
import com.whxx.hms.model.MainInfo;
import com.whxx.hms.model.Messages;
import com.whxx.hms.model.ModifyLogs;
import com.whxx.hms.model.Reservation;
import com.whxx.hms.model.RoomPerson;
import com.whxx.hms.model.RoomStatus;
import com.whxx.hms.model.SubInfo;
import com.whxx.hms.model.dto.DropdownDto;
import com.whxx.hms.model.dto.HotelGroupId;
import com.whxx.hms.model.dto.Marketing;
import com.whxx.hms.model.dto.MessageDto;
import com.whxx.hms.model.dto.PreLicenceDto;
import com.whxx.hms.model.dto.PreLicenceListDto;
import com.whxx.hms.model.dto.Preorder;
import com.whxx.hms.model.dto.PrepayDto;
import com.whxx.hms.model.dto.PrepayInfo;
import com.whxx.hms.model.dto.RateCodeDto;
import com.whxx.hms.model.dto.RoomDto;
import com.whxx.hms.model.dto.RoomPersonListDto;
import com.whxx.hms.model.dto.RoomTypeDto;
import com.whxx.hms.model.dto.RsvInfo;
import com.whxx.hms.model.dto.SubInfoDto;
import com.whxx.hms.service.ReservationSrv;
import com.whxx.hms.util.ContrastObj;

import ch.qos.logback.classic.Logger;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.http.HttpStatus;

@Service
@Transactional
@SuppressWarnings("rawtypes")
public class ReservationSrvImpl extends BaseController implements ReservationSrv {
	
	Logger logger = (Logger) AppUtils.getLogger("resavation", true);
	
	@Autowired
	private MainInfoMapper mainInfoMapper;
	
	@Autowired
	private SubInfoMapper subInfoMapper;
	
	@Autowired
	private MessagesMapper messagesMapper;
	
	@Autowired
	private ReservationMapper reservationMapper;
	
	@Autowired
	private ConsumeMapper consumeMapper;
	
	@Autowired
	private RoomStatusMapper roomStatusMapper;
	
	@Autowired
	private RoomMapper roomMapper;
	
	@Autowired
	private RoomTypeMapper roomTypeMapper;
	
	@Autowired
	private RoomPersonMapper roomPersonMapper;
	
	@Autowired
	private CommissionCodeMapper commissionCodeMapper;
	
	@Autowired
	private SalesManMapper salesManMapper;
	
	@Autowired
	private Tbpp01aMapper tbpp01aMapper;
	
	@Autowired
	private PriceCodeResMapper priceCodeResMapper;
	
	@Autowired
	private PayCodeMapper payCodeMapper;
	
	@Autowired
	private BuildingMapper buildingMapper;
	
	@Autowired
	private FloorMapper floorMapper;
	
	@Autowired
	private ModifyLogsMapper modifyLogsMapper;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RoomTypeDto> listRoomTypeApp(Map param) {
		// TODO Auto-generated method stub
		//预订时间（格式yyyy-MM-dd hh:mm:ss）
		String planStart = FormatUtil.toString(param.get("planStart"));
		String date = DateUtil.formatDate(planStart, "yyyy-MM-dd");
		String dateStr = DateUtil.formatDate(planStart, "yyyyMMdd");
		param.put("date", date);
		param.put("dateStr", dateStr);
		return this.roomTypeMapper.roomTypeDropList(param);
	}
	
	@Override
	public Map createNewRsv(Reservation rsv) {
		List<Preorder> preorderList = rsv.getPreorderList();//预订信息
		RsvInfo rsvInfo = rsv.getRsvInfo();//入住信息
		Marketing marketing = rsv.getMarketing();//市场销售
		//1、主表mainId前缀yyyyMMdd
		String prefix= DateUtil.format(new Date(), "yyMMdd");
		//查询mainId后缀最大值
		String suffix=this.mainInfoMapper.selectMainIdSuffix();
		suffix=String.format("%05d", new Integer(suffix)+1);
		String priMainId=prefix+suffix;
		try {
			//2、预订单新增主表
			for(int i = 0 ; i < preorderList.size(); i++){
				Preorder order = preorderList.get(i);
				MainInfo mainInfo = instanceMainInfo(order, rsvInfo, marketing, priMainId);
				//首条记录（当预订类型不为团队,默认为主单）
				if(i == 0){
					mainInfo.setIsMain("Y");
					mainInfo.setMainId(priMainId);
				}else{
					//查询mainId后缀最大值00001
					String suffixNew=this.mainInfoMapper.selectMainIdSuffix();
					suffixNew=String.format("%05d", new Integer(suffixNew)+1);
					mainInfo.setMainId(prefix + suffixNew);
				}
				this.mainInfoMapper.insertMainInfo(setCreateEmpAndTime(mainInfo));
				logger.info("============主表新增成功=========");
				//4、预订单新增明细表
				for(int j = 0; j < order.getRoomNum(); j++){
					SubInfo subInfo = new SubInfo();
					BeanUtil.copyProperties(mainInfo, subInfo);
					subInfo.setSubInfoId(UUIDUtil.getUUID());
					subInfo.setLineNo(ToolUtil.getLineNo());
					subInfo.setRealPrice(order.getDayPrice());
					subInfo.setBenefitReason(order.getBenefitReason());
					subInfo.setBenefitReasonName(order.getBenefitReasonName());
					//查询subId后缀最大值00001
					String subSuffix=this.subInfoMapper.selectSubIdSuffix();
					subSuffix=String.format("%05d", new Integer(subSuffix)+1);
					subInfo.setIsMain("N");
					if(j == 0 && i== 0){
						subInfo.setIsMain("Y");  //首条记录默认为主单（方便预授权和预收款将金额转移到主单上面）
						if("G".equals(order.getRsvClass())){
							//团队需要新增一条记录作为主账户（保存预授权和预收款信息）
							groupAddMainInfo(preorderList,rsvInfo,marketing,priMainId);
							mainInfo.setIsMain("N");
						}
					}
					String subId=prefix+subSuffix;
					subInfo.setSubId(subId);
					subInfo.setRoomNum(1);
					this.subInfoMapper.insertSubInfo(setCreateEmpAndTime(subInfo));
					logger.info("============子表新增成功=========");
				}
				//更新房价真正价格(页面修改房价后，填写优惠价格，更新房间的实际价格)
				updateRoomStatusRealPrice(order);
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("===========预订新增失败=========" + e.getMessage(),e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "预订单新增失败", rsv);
		}
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "预订单新增成功", priMainId);
	}
	
	/**
	 * 初始化mainInfo数据
	 * @Title: instanceMainInfo 
	 * @Description: TODO
	 * @param order
	 * @param rsvInfo
	 * @param marketing
	 * @param priMainId
	 * @return
	 * @return: MainInfo
	 */
	MainInfo instanceMainInfo(Preorder order,RsvInfo rsvInfo,Marketing marketing,String priMainId){
		//前缀yyMMdd
		String prefix= DateUtil.format(new Date(), "yyMMdd");
		MainInfo mainInfo = new MainInfo();
		BeanUtil.copyProperties(rsvInfo, mainInfo);
		BeanUtil.copyProperties(marketing, mainInfo);
		BeanUtil.copyProperties(order, mainInfo);
		mainInfo.setMainInfoId(UUIDUtil.getUUID());
		mainInfo.setLineNo(ToolUtil.getLineNo());
		mainInfo.setIsMain("N");
		mainInfo.setPriMainId(priMainId);
		//查询mainId后缀最大值00001
		String suffixNew=this.mainInfoMapper.selectMainIdSuffix();
		suffixNew=String.format("%05d", new Integer(suffixNew)+1);
		mainInfo.setMainId(prefix + suffixNew);
		//默认为联房
		mainInfo.setUnionFlag("Y");
		mainInfo.setMainStatus("R");
		mainInfo.setMainStatusName("预订");
		mainInfo.setUserType("N");
		mainInfo.setUserTypeName("普通");
		mainInfo.setInType("F");
		mainInfo.setInTypeName("全天房");
		mainInfo.setPreTime(new Date());
		mainInfo.setTel(rsvInfo.getMobile());
		mainInfo.setPlanStart(DateUtil.parseDateTime(order.getPlanStart()));
		mainInfo.setPlanEnd(DateUtil.parseDateTime(order.getPlanEnd()));
		if("G".equals(order.getRsvClass())){
			mainInfo.setMemberNo(order.getGroupNo());
			mainInfo.setMemberNoName(order.getGroupNoName());
		}
		if(!ToolUtil.isEmpty(rsvInfo.getPlanStart())){
			//到达日期 + 到达时间
			String startDateStr = order.getPlanStart() + rsvInfo.getPlanStart();
			logger.info("============主表新增planStart=========" + startDateStr);
			mainInfo.setPlanStart(DateUtil.parseDateTime(startDateStr));
		}
		if(!ToolUtil.isEmpty(rsvInfo.getKeepTime())){
			//到达日期 + 最晚时间
			String startDateStr = order.getPlanStart() + rsvInfo.getKeepTime();
			logger.info("============主表新增keepTime=========" + startDateStr);
			mainInfo.setKeepTime(DateUtil.parseDateTime(startDateStr));
		}
		mainInfo.setVersion(0);
		return mainInfo;
	}
	
	/**
	 * 更新房价当日入住的实际价格
	 * @Title: instanceMainInfo 
	 * @Description: TODO
	 * @param order
	 * @param rsvInfo
	 * @param marketing
	 * @param priMainId
	 * @return
	 * @return: MainInfo
	 */
	void updateRoomStatusRealPrice(Preorder order){
		//5、更新房间实际价格
		RoomStatus roomStatus = new RoomStatus();
		roomStatus.setPriceCode(order.getRatecode());//条件
		roomStatus.setRoomTypeCode(order.getRoomType());//条件
		roomStatus.setRoomDateStr(DateUtil.formatDate(order.getPlanStart(), "yyyyMMdd"));//条件
		roomStatus.setRealPrice(order.getDayPrice());//更新内容
		roomStatus.setBenefitReason(order.getBenefitReason());//更新内容
		roomStatusMapper.updateRoomStatus(roomStatus);
		logger.info("============更新入住当日房间价格成功=========");
	}
	
	/**
	 * 团队预订======新增主账户记录
	 * @Title: groupAddMainInfo 
	 * @Description: TODO
	 * @param preorder 订单
	 * @param rsvInfo  预订人信息
	 * @param marketing  市场营销
	 * @return
	 * @return: Map
	 */
	void groupAddMainInfo(List<Preorder> preorderList,RsvInfo rsvInfo,Marketing marketing,String priMainId){
		try {
			Preorder order = preorderList.get(0);
			//选择列表中最早入住和最晚退房
			MainInfo mainInfo = instanceMainInfo(order, rsvInfo, marketing, priMainId);
			mainInfo.setMemberNo(order.getGroupNo());
			mainInfo.setIsMain("Y");
			this.mainInfoMapper.insertMainInfo(setCreateEmpAndTime(mainInfo));
			logger.info("============团队主表新增成功=========");
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("===========团队主表新增失败=========" + e.getMessage(),e);
		}
	}

	@Override
	public Map updateNewRsv(Reservation rsv) {
		List<Preorder> preorderList = rsv.getPreorderList();// 预订信息
		RsvInfo rsvInfo = rsv.getRsvInfo();// 入住信息
		Marketing marketing = rsv.getMarketing();// 市场销售
		//查询预订单号
		String priMainId = preorderList.get(0).getPriMainId();
		if (null == priMainId || "".equals(priMainId)) {
			//i、预订单号为空,表示新增预订单
			logger.info("=========开始新增预订单============");
			return createNewRsv(rsv);
		}
		logger.info("=========开始修改预订单============");
	    //ii、预订单号存在，表示修改
	    String prefix= DateUtil.format(new Date(), "yyMMdd");
	    try {
			//1、预订单修改主表
			for(int i = 0 ; i < preorderList.size(); i++){
				Preorder order = preorderList.get(i);
				//2、如果不包含主键，表示新增主单
				if("".equals(FormatUtil.toString(order.getMainInfoId()))){
					MainInfo mainInfo = instanceMainInfo(order, rsvInfo, marketing, priMainId);
					//首条记录（当预订类型不为团队,默认为主单）
					if(i == 0){
						mainInfo.setIsMain("Y");
						mainInfo.setMainId(priMainId);
					}else{
						//查询mainId后缀最大值00001
						String suffixNew=this.mainInfoMapper.selectMainIdSuffix();
						suffixNew=String.format("%05d", new Integer(suffixNew)+1);
						mainInfo.setMainId(prefix + suffixNew);
					}
					if(i == 0 && "G".equals(order.getRsvClass())){
						//团队需要新增一条记录作为主账户（保存预授权和预收款信息）
						groupAddMainInfo(preorderList,rsvInfo,marketing,priMainId);
						mainInfo.setIsMain("N");
					}
					this.mainInfoMapper.insertMainInfo(setCreateEmpAndTime(mainInfo));
					logger.info("============主表新增成功=========");
					//3、预订单新增明细表
					for(int j = 0; j < order.getRoomNum(); j++){
						SubInfo subInfo = new SubInfo();
						BeanUtil.copyProperties(mainInfo, subInfo);
						subInfo.setSubInfoId(UUIDUtil.getUUID());
						subInfo.setLineNo(ToolUtil.getLineNo());
						//查询subId后缀最大值00001
						String subSuffix=this.subInfoMapper.selectSubIdSuffix();
						subSuffix=String.format("%05d", new Integer(subSuffix)+1);
						subInfo.setIsMain("N");
						String subId=prefix+subSuffix;
						subInfo.setSubId(subId);
						subInfo.setRoomNum(1);
						this.subInfoMapper.insertSubInfo(setCreateEmpAndTime(subInfo));
						logger.info("============子表新增成功=========");
					}
				}else{
					MainInfo mainInfo = instanceMainInfo(order, rsvInfo, marketing, priMainId);
					//4、如果包含主键，表示修改
					MainInfo oldMain = this.mainInfoMapper.selectMainInfoById(order.getMainInfoId());
					if(null == oldMain){
						logger.info("===========根据mainInfoId查询不到订单信息=========" + order.getMainInfoId());
						return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "根据mainInfoId查询不到订单信息", rsv);
					}
					BeanUtil.copyProperties(order, mainInfo);
					mainInfo.setVersion(1);
					this.mainInfoMapper.updateMainInfoById(setUpdateEmpAndTime(mainInfo));
					MainInfo newMain = this.mainInfoMapper.selectMainInfoById(order.getMainInfoId());
					ContrastObj<MainInfo>  consrast = new ContrastObj<MainInfo>();
					consrast.contrastObj("mainInfo",oldMain, newMain);
					logger.info("===========修改mainInfo信息成功=========");
					//5、判断房间数量（如果大于原始数量新增，如果小于原始数量删除（优先删除房间为空的数据））
					Integer oldRoomNum = oldMain.getRoomNum();
					logger.info("============原始主单房间数量=========" + oldRoomNum);
					Integer newRoomNum = order.getRoomNum();
					logger.info("============修改主单的新房间数量=========" + newRoomNum);
					//6、修改明细表
					String mainId = oldMain.getMainId();
					SubInfo subInfo = new SubInfo();
					BeanUtil.copyProperties(mainInfo, subInfo);
					subInfo.setRoomNum(1);
					subInfo.setMainId(mainId);
					this.subInfoMapper.updateSubInfoById(setUpdateEmpAndTime(subInfo));
					if(oldRoomNum > newRoomNum){
						logger.info("============准备删除明细表数量=========" + (oldRoomNum - newRoomNum));
						List<SubInfo> subList = this.subInfoMapper.selectByMainId(mainId);
						List<String> subInfoIds = subList.stream()
										.map(SubInfo::getSubInfoId)
										.collect(Collectors.toList());
						for(int j = 0 ; j < oldRoomNum - newRoomNum ; j++){
							this.subInfoMapper.deleteById(subInfoIds.get(j));
						}
						logger.info("============删除明细表成功=========");
					}else if(oldRoomNum < newRoomNum){
						logger.info("============准备新增明细表数量=========" + (newRoomNum - oldRoomNum));
						for(int j = 0 ; j < newRoomNum - oldRoomNum ; j++){
							SubInfo newSub = new SubInfo();
							BeanUtil.copyProperties(order, newSub);
							newSub.setSubInfoId(UUIDUtil.getUUID());
							newSub.setLineNo(ToolUtil.getLineNo());
							newSub.setRoomNum(1);
							newSub.setMainId(mainId);
							//查询subId后缀最大值00001
							String subSuffix=this.subInfoMapper.selectSubIdSuffix();
							subSuffix=String.format("%05d", new Integer(subSuffix)+1);
							newSub.setIsMain("N");
							String subId=prefix+subSuffix;
							newSub.setSubId(subId);
							newSub.setRoomNum(1);
							this.subInfoMapper.insertSubInfo(newSub);
						}
					}
					logger.info("============主表修改成功=========");
				}
				//5、更新房间实际价格
				updateRoomStatusRealPrice(order);
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("===========预订修改失败=========" + e.getMessage(),e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "预订单修改失败", rsv);
		}
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "预订单修改成功", priMainId);
	}
	
	
	@Override
	public Map deleteNewRsv(String mainInfoId) {
		MainInfo mainInfo = this.mainInfoMapper.selectMainInfoById(mainInfoId);
		if(null == mainInfo){
			logger.info("=========mainInfoId没有查询到主单信息===========" + mainInfoId);
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "mainInfoId没有查询到主单信息", mainInfoId);
		}
		String mainId = mainInfo.getMainId();
		try {
			//删除主表
			this.mainInfoMapper.deleteById(mainInfoId);
			//删除明细表
			this.subInfoMapper.deleteByMainId(mainId);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("===========预订删除失败=========" + e.getMessage(),e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "预订单删除失败", mainInfoId);
		}
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "预订单删除成功", mainInfoId);
	}
	
	@Override
	public Reservation getNewRsvResult(String priMainId) {
		// TODO Auto-generated method stub
		//预订房间信息
		List<Preorder> preorderList = this.reservationMapper.mainOrderList(priMainId);
		//预订人信息
		RsvInfo rsvInfo = this.reservationMapper.rsvInfo(priMainId);
		//市场销售
		Marketing marketing = this.reservationMapper.marketing(priMainId);
		//预收款信息
		PrepayInfo prepayInfo = new PrepayInfo();
		//预收款笔数和金额
		Map prepayMap = reservationMapper.prepayInfo(priMainId);
		//预授权笔数和金额
		Map prelicenceMap = reservationMapper.prelicence(priMainId);
		//联房金额
		Map unionMap = reservationMapper.unionpay(priMainId);
		//初始化首页的预付款信息
		prepayInfo.setPriMainId(priMainId);
		prepayInfo.setPrepayCount(FormatUtil.toInt(prepayMap.get("prepayCount")));
		prepayInfo.setTotalPrepay(FormatUtil.toBc(prepayMap.get("totalPrepay")));
		prepayInfo.setCreditCount(FormatUtil.toInt(prelicenceMap.get("creditCount")));
		prepayInfo.setTotalCredit(FormatUtil.toBc(prelicenceMap.get("totalCredit")));
		prepayInfo.setCurBalance(FormatUtil.toBc(prepayMap.get("totalPrepay")));
		prepayInfo.setCurCredit(FormatUtil.toBc(prelicenceMap.get("totalCredit")));
		BigDecimal curTotal = FormatUtil.toBc(prepayMap.get("totalPrepay"))
				.add(FormatUtil.toBc(prelicenceMap.get("totalCredit")));
		prepayInfo.setCurTotal(curTotal);
		prepayInfo.setUnionBalance(FormatUtil.toBc(unionMap.get("unionPay")));
		prepayInfo.setUnionCredit(FormatUtil.toBc(unionMap.get("unionCredit")));
		BigDecimal unionTotal = FormatUtil.toBc(unionMap.get("unionCredit"))
				.add(FormatUtil.toBc(unionMap.get("unionPay")));
		prepayInfo.setUnionTotal(unionTotal);
		//赋值
		Reservation reservation = new Reservation();
		reservation.setPreorderList(preorderList);
		reservation.setRsvInfo(rsvInfo);
		reservation.setMarketing(marketing);
		reservation.setPrepayInfo(prepayInfo);
		return reservation;
	}

	@Override
	public Map prepay2NewRsv(PrepayDto prepayDto) {
		// TODO Auto-generated method stub
		try {
			//1、更新预付款信息
			//======将预付款金额累加到主表的主预订单号记录上==========
			MainInfo mainInfo = new MainInfo();
			mainInfo.setPriMainId(prepayDto.getPriMainId());
			mainInfo.setIsMain("Y");
			mainInfo.setPayAll(prepayDto.getPrepayMoney());
			this.mainInfoMapper.updateMainInfoById(setUpdateEmpAndTime(mainInfo));
			
			//======将预付款金额累加到明细表的主预订单号记录上==========
			SubInfo subInfo = new SubInfo();
			subInfo.setPriMainId(prepayDto.getPriMainId());
			subInfo.setIsMain("Y");
			subInfo.setPay(prepayDto.getPrepayMoney());
			this.subInfoMapper.updateSubInfoById(setUpdateEmpAndTime(subInfo));
			logger.info("=======预付款信息更新成功===========");
			
			//2、入账信息
			Consume consume = new Consume();
			instanceConsume(consume);
			consume.setAccountType("预收款");
			consume.setPreFlag("1");
			consume.setGold(prepayDto.getPrepayMoney());
			consume.setHotelGroupId(prepayDto.getHotelGroupId());
			consume.setHotelId(prepayDto.getHotelId());
			consume.setPriMainId(prepayDto.getPriMainId());
			consume.setMainId(prepayDto.getPriMainId());
			consume.setSubId(prepayDto.getPriMainId());
			consume.setMyNo(prepayDto.getMchOrderNo());
			consume.setThirdNo(prepayDto.getTransactionNo());
			this.consumeMapper.insertConsume(setCreateEmpAndTime(consume));
			logger.info("=======预付款信息入账成功===========");
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("=======预付款信息更新失败==========="+e.getMessage(),e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "预付款入账失败", prepayDto);
		}
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "预付款入账成功", prepayDto.getPriMainId());
	}

	@Override
	public Map prelicence2NewRsv(PreLicenceListDto preLicenceListDto) {
		// TODO Auto-generated method stub
		List<PreLicenceDto> preLicenceList = preLicenceListDto.getPrelicenceListDto();
		String priMainId = preLicenceList.get(0).getPriMainId();
		try {
			//1、更新预授权信息
			for(int i = 0; i < preLicenceList.size(); i++){
				PreLicenceDto preLicenceDto = preLicenceList.get(i);
				//======将预授权金额累加到明细表的主预订单号记录上==========
				MainInfo mainInfo = new MainInfo();
				mainInfo.setPriMainId(preLicenceDto.getPriMainId());
				mainInfo.setIsMain("Y");
				mainInfo.setCreditNo(preLicenceDto.getCreditNo());
				mainInfo.setCreditMan(preLicenceDto.getCreditMan());
				mainInfo.setCreditPayCode(preLicenceDto.getCreditPayCode());
				mainInfo.setCreditPayNo(preLicenceDto.getCreditPayNo());
				mainInfo.setCreditAll(preLicenceDto.getCreditMoney());
				this.mainInfoMapper.updateMainInfoById(setUpdateEmpAndTime(mainInfo));
				
				//======将预授权金额累加到明细表的主预订单号记录上==========
				SubInfo subInfo = new SubInfo();
				subInfo.setPriMainId(preLicenceDto.getPriMainId());
				subInfo.setIsMain("Y");
				subInfo.setCreditNo(preLicenceDto.getCreditNo());
				subInfo.setCreditMan(preLicenceDto.getCreditMan());
				subInfo.setCreditPayCode(preLicenceDto.getCreditPayCode());
				subInfo.setCreditPayNo(preLicenceDto.getCreditPayNo());
				subInfo.setCredit(preLicenceDto.getCreditMoney());
				this.subInfoMapper.updateSubInfoById(setUpdateEmpAndTime(subInfo));
				logger.info("=======预授权信息更新成功===========");
				
				String mainId = this.mainInfoMapper.selectMainIdByIsMain(preLicenceDto.getPriMainId());
				logger.info("=======主表主单Id===========" + mainId);
				String subId = this.subInfoMapper.selectSubIdByIsMain(preLicenceDto.getPriMainId());
				logger.info("=======子表主单Id===========" + subId);
				//2、入账信息
				Consume consume = new Consume();
				instanceConsume(consume);
				consume.setAccountType("预授权");
				consume.setPreFlag("2");
				consume.setGold(preLicenceDto.getCreditMoney());
				consume.setHotelGroupId(preLicenceDto.getHotelGroupId());
				consume.setHotelId(preLicenceDto.getHotelId());
				consume.setPriMainId(preLicenceDto.getPriMainId());
				consume.setMainId(mainId);
				consume.setSubId(subId);
			    consume.setBankNo(preLicenceDto.getCreditNo());
			    consume.setPayNo(preLicenceDto.getCreditPayNo());
			    consume.setPayPerson(preLicenceDto.getCreditMan());
				this.consumeMapper.insertConsume(setCreateEmpAndTime(consume));
				logger.info("=======预授权信息入账成功===========");
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("=======预授权信息更新失败==========="+e.getMessage(),e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "预授权入账失败", preLicenceList);
		}
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "预授权入账成功", priMainId);
	}
	
	
	@Override
	public List<PreLicenceDto> prelicenceList(String priMainId) {
		// TODO Auto-generated method stub
		return this.reservationMapper.prelicenceList(priMainId);
	}

	@Override
	public Map updatePrelicence(PreLicenceListDto preLicenceListDto) {
		// TODO Auto-generated method stub
		List<PreLicenceDto> preLicenceList = preLicenceListDto.getPrelicenceListDto();
		String priMainId = preLicenceList.get(0).getPriMainId();
		try {
			//1、更新预授权信息
			for(int i = 0; i < preLicenceList.size(); i++){
				PreLicenceDto preLicenceDto = preLicenceList.get(i);
				String mainId = this.mainInfoMapper.selectMainIdByIsMain(preLicenceDto.getPriMainId());
				logger.info("=======主表主单Id===========" + mainId);
				String subId = this.subInfoMapper.selectSubIdByIsMain(preLicenceDto.getPriMainId());
				logger.info("=======子表主单Id===========" + subId);
				
				Consume consume = new Consume();
				instanceConsume(consume);
				consume.setConsumeId(UUIDUtil.getUUID());
				consume.setAccountType("预授权");
				consume.setPreFlag("2");
				consume.setGold(preLicenceDto.getCreditMoney());
				consume.setHotelGroupId(preLicenceDto.getHotelGroupId());
				consume.setHotelId(preLicenceDto.getHotelId());
				consume.setPriMainId(preLicenceDto.getPriMainId());
				consume.setMainId(mainId);
				consume.setSubId(subId);
			    consume.setBankNo(preLicenceDto.getCreditNo());
			    consume.setPayNo(preLicenceDto.getCreditPayNo());
			    consume.setPayPerson(preLicenceDto.getCreditMan());
			    
			    //======将预授权金额累加到明细表的主预订单号记录上==========
				MainInfo mainInfo = new MainInfo();
				mainInfo.setPriMainId(preLicenceDto.getPriMainId());
				mainInfo.setIsMain("Y");
				mainInfo.setCreditNo(preLicenceDto.getCreditNo());
				mainInfo.setCreditMan(preLicenceDto.getCreditMan());
				mainInfo.setCreditPayCode(preLicenceDto.getCreditPayCode());
				mainInfo.setCreditPayNo(preLicenceDto.getCreditPayNo());
				mainInfo.setCreditAll(preLicenceDto.getCreditMoney());
				
				//======将预授权金额累加到明细表的主预订单号记录上==========
				SubInfo subInfo = new SubInfo();
				subInfo.setPriMainId(preLicenceDto.getPriMainId());
				subInfo.setIsMain("Y");
				subInfo.setCreditNo(preLicenceDto.getCreditNo());
				subInfo.setCreditMan(preLicenceDto.getCreditMan());
				subInfo.setCreditPayCode(preLicenceDto.getCreditPayCode());
				subInfo.setCreditPayNo(preLicenceDto.getCreditPayNo());
				subInfo.setCredit(preLicenceDto.getCreditMoney());
			    
				//2、新增入账信息
				if("".equals(preLicenceDto.getConsumeId())){
					this.consumeMapper.insertConsume(setCreateEmpAndTime(consume));
					logger.info("=======预授权信息新增入账成功===========");
					//======将预授权金额累加到明细表的主预订单号记录上==========
					this.mainInfoMapper.updateMainInfoById(setUpdateEmpAndTime(mainInfo));
					//======将预授权金额累加到明细表的主预订单号记录上==========
					this.subInfoMapper.updateSubInfoById(setUpdateEmpAndTime(subInfo));
					logger.info("=======预授权信息更新成功===========");
				}else{
					Consume oldConsume = this.consumeMapper.selectById(preLicenceDto.getConsumeId());
					if(null == oldConsume){
						logger.info("=======根据consumeId没有查询到数据===========");
						return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "根据consumeId没有查询到数据", oldConsume);
					}
					consume.setConsumeId(preLicenceDto.getConsumeId());
					this.consumeMapper.updateConsumeById(setUpdateEmpAndTime(consume));
					//======将预授权金额累加到明细表的主预订单号记录上==========
					logger.info("=====consume旧值的金额========" + oldConsume.getGold());
					logger.info("=====consume旧值的负数金额========" + oldConsume.getGold().multiply(new BigDecimal("-1")));
					BigDecimal newGold = (oldConsume.getGold().multiply(new BigDecimal("-1"))).add(preLicenceDto.getCreditMoney());
					mainInfo.setCreditAll(newGold);
					this.mainInfoMapper.updateMainInfoById(setUpdateEmpAndTime(mainInfo));
					//======将预授权金额累加到明细表的主预订单号记录上==========
					subInfo.setCredit(newGold);
					this.subInfoMapper.updateSubInfoById(setUpdateEmpAndTime(subInfo));
					logger.info("=======预授权信息修改入账成功===========");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("=======预授权信息更新失败==========="+e.getMessage(),e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "预授权入账失败", preLicenceList);
		}
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "预授权入账成功", priMainId);
	}

	@Override
	public Map cancelPrelicence(PreLicenceDto preLicenceDto) {
		// TODO Auto-generated method stub
		try {
			//======将预授权金额累加到明细表的主预订单号记录上==========
			MainInfo mainInfo = new MainInfo();
			mainInfo.setPriMainId(preLicenceDto.getPriMainId());
			mainInfo.setIsMain("Y");
			mainInfo.setCreditNo(preLicenceDto.getCreditNo());
			mainInfo.setCreditMan(preLicenceDto.getCreditMan());
			mainInfo.setCreditPayCode(preLicenceDto.getCreditPayCode());
			mainInfo.setCreditPayNo(preLicenceDto.getCreditPayNo());
			mainInfo.setCreditAll(preLicenceDto.getCreditMoney().multiply(new BigDecimal("-1")));
			this.mainInfoMapper.updateMainInfoById(setUpdateEmpAndTime(mainInfo));
			logger.info("=======预授权撤销主表更新成功===========");
			
			//======将预授权金额累加到明细表的主预订单号记录上==========
			SubInfo subInfo = new SubInfo();
			subInfo.setPriMainId(preLicenceDto.getPriMainId());
			subInfo.setIsMain("Y");
			subInfo.setCreditNo(preLicenceDto.getCreditNo());
			subInfo.setCreditMan(preLicenceDto.getCreditMan());
			subInfo.setCreditPayCode(preLicenceDto.getCreditPayCode());
			subInfo.setCreditPayNo(preLicenceDto.getCreditPayNo());
			subInfo.setCredit(preLicenceDto.getCreditMoney().multiply(new BigDecimal("-1")));
			this.subInfoMapper.updateSubInfoById(setUpdateEmpAndTime(subInfo));
			logger.info("=======预授权撤销子表更新成功===========");
			
			String mainId = this.mainInfoMapper.selectMainIdByIsMain(preLicenceDto.getPriMainId());
			logger.info("=======主表主单Id===========" + mainId);
			String subId = this.subInfoMapper.selectSubIdByIsMain(preLicenceDto.getPriMainId());
			logger.info("=======子表主单Id===========" + subId);
			
			Consume consume = new Consume();
			instanceConsume(consume);
			consume.setConsumeId(preLicenceDto.getConsumeId());
			consume.setAccountType("预授权");
			consume.setPreFlag("4");
			consume.setGold(preLicenceDto.getCreditMoney());
			consume.setHotelGroupId(preLicenceDto.getHotelGroupId());
			consume.setHotelId(preLicenceDto.getHotelId());
			consume.setPriMainId(preLicenceDto.getPriMainId());
			consume.setMainId(mainId);
			consume.setSubId(subId);
		    consume.setBankNo(preLicenceDto.getCreditNo());
		    consume.setPayNo(preLicenceDto.getCreditPayNo());
		    consume.setPayPerson(preLicenceDto.getCreditMan());
		    consume.setRemark("预授权撤回");
			this.consumeMapper.updateConsumeById(setUpdateEmpAndTime(consume));
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("=======预授权信息更新失败==========="+e.getMessage(),e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "预授权入账失败", preLicenceDto);
		}
		logger.info("=======预授权信息撤回信息成功===========");
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "预授权入账成功", null);
	}
	
	
	private void instanceConsume(Consume consume){
		consume.setConsumeId(UUIDUtil.getUUID());
		consume.setLineNo(ToolUtil.getLineNo());
		consume.setInRoom("Y");
		consume.setPreFlag("3");
		consume.setAccountType("入账类型");
		consume.setGoodType("小商品类型");
		consume.setInType("1");
	}
	
	/**
	 * 新增留言
	 * (non Javadoc) 
	 * @Title: message2NewRsv
	 * @Description: TODO
	 * @param messageDto
	 * @return
	 * @see com.whxx.hms.service.ReservationSrv#message2NewRsv(com.whxx.hms.model.dto.MessageDto)
	 */
	@Override
	public Map message2NewRsv(MessageDto messageDto) {
		// TODO Auto-generated method stub
		try {
			//1、新增留言
			Messages messages = new Messages();
			BeanUtil.copyProperties(messageDto, messages);
			messages.setMessagesId(UUIDUtil.getUUID());
			messages.setLineNo(ToolUtil.getLineNo());
			messages.setMainId(messageDto.getSubId());
			messages.setReadFlag("N");
			//messages.setStartTime(DateUtil.parseDate(messageDto.getStartTime()));
			//messages.setEndTime(DateUtil.parseDate(messageDto.getEndTime()));
			this.messagesMapper.insertMessages(setCreateEmpAndTime(messages));
			logger.info("============留言新增成功=========");
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("=======留言新增失败==========="+e.getMessage(),e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "预订单留言新增失败", messageDto);
		}
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "预订单留言新增成功", messageDto.getSubId());
	}
	
	
	@Override
	public Map updateMessages(String messagesId) {
		// TODO Auto-generated method stub
		try {
			//1、修改留言信息
			Messages messages = new Messages();
			messages.setMessagesId(messagesId);
			messages.setReadFlag("Y");
			this.messagesMapper.updateMessages(setUpdateEmpAndTime(messages));
			logger.info("============留言处理成功=========");
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("=======留言处理失败==========="+e.getMessage(),e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "留言处理失败", messagesId);
		}
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "预订单留言修改成功",null);
	}
	
	
	@Override
	public Map updateMainStatus(String mainId,String priMainId) {
		// TODO Auto-generated method stub
		try {
			MainInfo mainInfo = new MainInfo();
			mainInfo.setMainId(mainId);
			mainInfo.setMainStatus("O");
			this.mainInfoMapper.updateMainInfoById(mainInfo);
			
			SubInfo subInfo = new SubInfo();
			subInfo.setMainId(mainId);
			subInfo.setMainStatus("O");
			this.subInfoMapper.updateSubInfoById(subInfo);
			logger.info("============预订单取消成功=========");
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("=======预订单取消失败==========="+e.getMessage(),e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "预订单取消取消失败", mainId);
		}
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "预订单取消成功", priMainId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map messagesList(Integer hotelGroupId,Integer hotelId) {
		// TODO Auto-generated method stub
		Map map = new HashMap<>();
		try {
			Integer unreadCounts = this.messagesMapper.unreadCounts(hotelGroupId,hotelId);
			List<MessageDto> unreadList = this.messagesMapper.messagesList("N",hotelGroupId,hotelId);
			List<MessageDto> readList = this.messagesMapper.messagesList("Y",hotelGroupId,hotelId);
			map.put("unreadCounts", unreadCounts);
			map.put("unreadList", unreadList);
			map.put("readList", readList);
			logger.info("============首页留言列表查询成功=========");
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("=======首页留言列表查询失败==========="+e.getMessage(),e);
		}
		return map;
	}

	@Override
	public List<RoomDto> roomDropDownList(Map param) {
		// TODO Auto-generated method stub
		if("".equals(FormatUtil.toString(param.get("sign")))){
			return new ArrayList<>();
		}
		//真正返回页面列表
		List<RoomDto> newRoomList = new ArrayList<>();
		//原始列表
		List<RoomDto> rawRoomList = this.roomMapper.roomDropDownList(param);
		if(FormatUtil.toString(param.get("sign")).contains("VC")){
			List<RoomDto> vcList  = rawRoomList.stream()
									 .filter(d -> "Y".equals(d.getCleanStatus()) && "N".equals(d.getInFlag()))
									 .collect(Collectors.toList());
			newRoomList.addAll(vcList);
		}
		if(FormatUtil.toString(param.get("sign")).contains("VD")){
			List<RoomDto> vdList  = rawRoomList.stream()
									 .filter(d -> "N".equals(d.getCleanStatus()) && "N".equals(d.getInFlag()))
									 .collect(Collectors.toList());
			newRoomList.addAll(vdList);
		}
		if(FormatUtil.toString(param.get("sign")).contains("OC")){
			List<RoomDto> ocList  = rawRoomList.stream()
									 .filter(d -> "Y".equals(d.getCleanStatus()) && "Y".equals(d.getInFlag()))
									 .collect(Collectors.toList());
			newRoomList.addAll(ocList);
		}
		if(FormatUtil.toString(param.get("sign")).contains("OD")){
			List<RoomDto> odList  = rawRoomList.stream()
									 .filter(d -> "N".equals(d.getCleanStatus()) && "Y".equals(d.getInFlag()))
									 .collect(Collectors.toList());
			newRoomList.addAll(odList);
		}
		return newRoomList;
	}
	
	/**
	 * 更新房号和备注和入住状态
	 * (non Javadoc) 
	 * @Title: updateSubInfo
	 * @Description: TODO
	 * @param param
	 * @return
	 * @see com.whxx.hms.service.ReservationSrv#updateSubInfo(java.util.Map)
	 */
	@Override
	public Map updateSubInfo(Map param) {
		// TODO Auto-generated method stub
		String subInfoId = FormatUtil.toString(param.get("subInfoId"));
		String subId = FormatUtil.toString(param.get("subId"));
		String roomCode = FormatUtil.toString(param.get("roomCode"));
		String remark = FormatUtil.toString(param.get("remark"));
		String inStatus = FormatUtil.toString(param.get("inStatus"));
		try {
			//更新房号
			if(!"".equals(roomCode) && !"".equals(subInfoId) && "R".equals(inStatus)){
				//旧记录
				SubInfo oldSub = this.subInfoMapper.selectSubInfoById(subInfoId);
				
				SubInfo subInfo = new SubInfo();
				subInfo.setSubInfoId(subInfoId);
				subInfo.setRoomNo(roomCode);
				this.subInfoMapper.updateSubInfoById(setUpdateEmpAndTime(subInfo));
				//新记录
				subInfo = this.subInfoMapper.selectSubInfoById(subInfoId);
				logger.info("============subInfo订单更新房号成功=========");
				
				ContrastObj<MainInfo>  consrast = new ContrastObj<MainInfo>();
				consrast.contrastObj("subInfo",oldSub, subInfo);
			}
			//更新备注
			if(!"".equals(remark)&&!"".equals(subInfoId)){
				//旧记录
				SubInfo oldSub = this.subInfoMapper.selectSubInfoById(subInfoId);
				
				SubInfo subInfo = new SubInfo();
				subInfo.setSubInfoId(subInfoId);
				subInfo.setRemark(remark);
				this.subInfoMapper.updateSubInfoById(setUpdateEmpAndTime(subInfo));
				logger.info("============更新订单备注成功=========");
				//新记录
				subInfo = this.subInfoMapper.selectSubInfoById(subInfoId);
				
				ContrastObj<MainInfo>  consrast = new ContrastObj<MainInfo>();
				consrast.contrastObj("subInfo",oldSub, subInfo);
			}
			//更新入住状态
			if("I".equals(inStatus) && !"".equals(subInfoId) && !"".equals(roomCode)){
				//旧记录
				SubInfo oldSub = this.subInfoMapper.selectSubInfoById(subInfoId);
				
				SubInfo subInfo = new SubInfo();
				subInfo.setSubInfoId(subInfoId);
				subInfo.setRoomNo(roomCode);
				subInfo.setMainStatus(inStatus);
				this.subInfoMapper.updateSubInfoById(setUpdateEmpAndTime(subInfo));
				logger.info("============subInfo更新订单入住状态成功=========");
				//新记录
				subInfo = this.subInfoMapper.selectSubInfoById(subInfoId);
				
				ContrastObj<MainInfo>  consrast = new ContrastObj<MainInfo>();
				consrast.contrastObj("subInfo",oldSub, subInfo);
				
				//更新room_status表
				subInfo = this.subInfoMapper.selectSubInfoById(subInfoId);
				RoomStatus roomStatus = new RoomStatus();
				roomStatus.setRoomCode(roomCode);//修改条件
				roomStatus.setRoomDateStr(DateUtil.format(subInfo.getPlanStart(), "yyyyMMdd"));//修改条件
				roomStatus.setSubId(subId);//被修改内容
				this.roomStatusMapper.updateRoomStatus(setUpdateEmpAndTime(roomStatus));
				logger.info("============roomStatus更新订单入住状态成功=========");
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("========子订单信息更新失败======="  + e.getMessage(),e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "子订单信息更新失败", roomCode + "/" + subId);
		}
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "子订单信息更新成功", roomCode);
	}
	
	
	@Override
	public Map cancelRsv(Map param) {
		// TODO Auto-generated method stub
		String subInfoId = FormatUtil.toString(param.get("subInfoId"));
		String priMainId = FormatUtil.toString(param.get("priMainId"));
		String cancel = FormatUtil.toString(param.get("cancel"));
		try {
			//取消单笔订单
			if("single".equals(cancel) && !"".equals(subInfoId)){
				//旧的订单是否存在预付款
				SubInfo oldSub = this.subInfoMapper.selectSubInfoById(subInfoId);
				if(ToolUtil.isEmpty(oldSub.getPay())){
					logger.info("============当前预订单余额不为0,不能取消=========" + subInfoId);
					return CommUtil.setMessage(HttpStatus.HTTP_MOVED_TEMP, "当前预订单余额不为0,不能取消", subInfoId);
				}
				SubInfo subInfo = new SubInfo();
				subInfo.setSubInfoId(subInfoId);
				subInfo.setMainStatus("C");//R=预订 I=在住 C=取消 O=结帐退房 S=挂账
				this.subInfoMapper.updateSubInfoById(setUpdateEmpAndTime(subInfo));
				subInfo = this.subInfoMapper.selectSubInfoById(subInfoId);
				//异动写入数据库
				ContrastObj<SubInfo>  consrast = new ContrastObj<SubInfo>();
				this.modifyLogsMapper.insertModifyLogs(consrast.contrastObj("subInfo",oldSub, subInfo));
				logger.info("============取消单笔预订单成功=========" + subInfo);
			}
			//取消整个预订单
			if("all".equals(cancel) && !"".equals(priMainId)){
				//原始记录
				List<MainInfo> oldMainInfoList = this.mainInfoMapper.selectByPriMainId(priMainId);
				List<SubInfo> oldSubInfoList = this.subInfoMapper.selectByPriMainId(priMainId);
				//查询是否存在预付款信息设施（如果有，无法取消）
				List<String> mainIds = oldMainInfoList.stream()
										.filter(d -> null != d.getPayAll())
										.map(MainInfo::getMainInfoId)
										.distinct()
										.collect(Collectors.toList());
				if(CollectionUtil.isEmpty(mainIds)){
					logger.info("============开始取消整个预订单=========" + priMainId);
					MainInfo mainInfo = new MainInfo();
					mainInfo.setPriMainId(priMainId);
					mainInfo.setMainStatus("C");
					this.mainInfoMapper.updateMainInfoById(setUpdateEmpAndTime(mainInfo));
					
					SubInfo subInfo = new SubInfo();
					subInfo.setPriMainId(priMainId);
					subInfo.setMainStatus("C");
					this.subInfoMapper.updateSubInfoById(setUpdateEmpAndTime(subInfo));
					logger.info("============取消整个预订单成功=========" + priMainId);
					
					List<MainInfo> newMainInfoList = this.mainInfoMapper.selectByPriMainId(priMainId);
					List<SubInfo> newSubInfoList = this.subInfoMapper.selectByPriMainId(priMainId);
					
					for(int i = 0; i < newMainInfoList.size() - 1 ; i++){
						//异动写入数据库
						ContrastObj<MainInfo>  consrast = new ContrastObj<MainInfo>();
						this.modifyLogsMapper.insertModifyLogs(consrast.contrastObj("mainInfo",oldMainInfoList.get(i), newMainInfoList.get(i)));
					}
					
					for(int i = 0; i < newSubInfoList.size() - 1 ; i++){
						//异动写入数据库
						ContrastObj<SubInfo>  consrast = new ContrastObj<SubInfo>();
						this.modifyLogsMapper.insertModifyLogs(consrast.contrastObj("subInfo",oldSubInfoList.get(i), newMainInfoList.get(i)));
					}
				}else{
					logger.info("============当前预订单余额不为0,不能取消=========" + subInfoId);
					return CommUtil.setMessage(HttpStatus.HTTP_MOVED_TEMP, "当前预订单余额不为0,不能取消", null);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("========取消预订单失败======="  + e.getMessage(),e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "取消预订单失败", null);
		}
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "取消预订单成功", null);
	}

	@Override
	public Map createRoomPerson(RoomPersonListDto roomPersonListDto) {
		// TODO Auto-generated method stub
		List<RoomPerson> roomPersonList = roomPersonListDto.getRoomPersonList();
		RoomPerson roomperson = new RoomPerson();
		try {
			for(int i = 0 ; i < roomPersonList.size(); i++){
				BeanUtil.copyProperties(roomPersonList.get(i), roomperson);
				roomperson.setRoomPersonId(UUIDUtil.getUUID());
				roomperson.setLineNo(ToolUtil.getLineNo());
				roomperson.setMainId(roomperson.getSubId());
				this.roomPersonMapper.insertRoomPerson(setCreateAndUpdate(roomperson));
			}
			logger.info("============新增入住人信息成功=========");
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("========新增住客信息失败======="  + e.getMessage(),e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "新增住客信息失败", roomPersonList);
		}
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "新增住客信息成功", roomPersonList);
	}
	
	@Override
	public List<RoomPerson> selectBySubId(String subId) {
		// TODO Auto-generated method stub
		return this.roomPersonMapper.selectBySubId(subId);
	}

	@Override
	public Map updateRoomPerson(RoomPersonListDto roomPersonListDto) {
		// TODO Auto-generated method stub
		List<RoomPerson> roomPersonList = roomPersonListDto.getRoomPersonList();
		RoomPerson roomperson = new RoomPerson();
		try {
			for(int i = 0 ; i < roomPersonList.size() - 1; i++){
				BeanUtil.copyProperties(roomPersonList.get(i), roomperson);
				if("".equals(FormatUtil.toString(roomPersonList.get(i).getRoomPersonId()))){
					roomperson.setRoomPersonId(UUIDUtil.getUUID());
					roomperson.setLineNo(ToolUtil.getLineNo());
					roomperson.setMainId(roomperson.getSubId());
					this.roomPersonMapper.insertRoomPerson(setCreateAndUpdate(roomperson));
				}else{
					this.roomPersonMapper.updateById(setUpdateEmpAndTime(roomperson));
				}
			}
			logger.info("============修改入住人信息成功=========");
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("========修改住客信息失败======="  + e.getMessage(),e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "修改住客信息失败", roomPersonList);
		}
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "修改住客信息成功", roomPersonList);
	}

	@Override
	public Map deleteRoomPerson(String roomPersonId) {
		// TODO Auto-generated method stub
		try {
			this.roomPersonMapper.deleteById(roomPersonId);
			logger.info("============删除入住人信息成功=========");
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("========删除住客信息失败======="  + e.getMessage(),e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "删除住客信息失败", roomPersonId);
		}
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "删除住客信息成功", roomPersonId);
	}

	@Override
	public List<DropdownDto> salesManDropDownList(Map param) {
		// TODO Auto-generated method stub
		return this.salesManMapper.salesManDropDownList(param);
	}

	@Override
	public List<SubInfoDto> preorderlist(String priMainId) {
		// TODO Auto-generated method stub
		return this.reservationMapper.subOrderList(priMainId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SubInfoDto> transferList(Map param) {
		// TODO Auto-generated method stub
		if(!"".equals(FormatUtil.toString(param.get("mainStatus")))){
			String[] states = FormatUtil.toString(param.get("mainStatus")).split(",");
			List<String> statesList =  Arrays.stream(states).collect(Collectors.toList()); 
			param.put("mainStatus", statesList);
		}
		return this.reservationMapper.transferList(param);
	}

	@Override
	public Map transferPrelicence(PreLicenceDto preLicenceDto) {
		String consumeId = preLicenceDto.getConsumeId();
		logger.info("=======预授权转移consumeId===========" + consumeId);
		if("".equals(FormatUtil.toString(consumeId))){
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "转移列表参数为空", null);
		}
		//预授权详情
		Consume consume = this.consumeMapper.selectById(consumeId);
		String oldMainId = consume.getMainId();//主单号旧值
		String oldSubId = consume.getSubId();//子单号旧值
		BigDecimal gold = consume.getGold();//授权金额
		String newSubId = preLicenceDto.getNewSubId();
		logger.info("=======预授权转移newSubId===========" + newSubId);
		try {
			// TODO Auto-generated method stub
			//======将预授权金额累加到明细表的主预订单号记录上==========
			MainInfo mainInfo = new MainInfo();
			mainInfo.setMainId(oldMainId);
			mainInfo.setIsMain("Y");
			mainInfo.setCreditAll(gold.multiply(new BigDecimal("-1")));
			this.mainInfoMapper.updateMainInfoById(setUpdateEmpAndTime(mainInfo));
			
			//======将预授权金额累加到明细表的主预订单号记录上==========
			SubInfo subInfo = new SubInfo();
			subInfo.setSubId(oldSubId);
			subInfo.setIsMain("Y");
			subInfo.setCredit(gold.multiply(new BigDecimal("-1")));
			this.subInfoMapper.updateSubInfoById(setUpdateEmpAndTime(subInfo));
			
		    String priMainIdNew = this.subInfoMapper.priMainIdBySubId(newSubId);
		    String mainIdNew = this.mainInfoMapper.selectMainIdByIsMain(priMainIdNew);
		    String subIdNew = this.subInfoMapper.selectSubIdByIsMain(priMainIdNew);
			//======将预授权金额累加到明细表的主预订单号记录上==========
			MainInfo mainInfoNew = new MainInfo();
			mainInfoNew.setMainId(mainIdNew);
			mainInfoNew.setCreditAll(gold);
			this.mainInfoMapper.updateMainInfoById(setUpdateEmpAndTime(mainInfoNew));
			
			//======将预授权金额累加到明细表的主预订单号记录上==========
			SubInfo subInfoNew = new SubInfo();
			subInfoNew.setSubId(subIdNew);
			subInfo.setCredit(gold);
			this.subInfoMapper.updateSubInfoById(setUpdateEmpAndTime(subInfo));
			logger.info("=======预授权信息转移成功===========");
		}  catch (Exception e) {
			// TODO: handle exception
			logger.error("========预授权转移失败======="  + e.getMessage(),e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "预授权转移失败", preLicenceDto);
		}
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "预授权信息转移成功", null);
		
	}

	@Override
	public List<PreLicenceDto> prelicenceLogs(String priMainId) {
		// TODO Auto-generated method stub
		List<PreLicenceDto> preLicenceList = this.reservationMapper.prelicenceList(priMainId);
		return preLicenceList;
	}

	@Override
	public List<DropdownDto> commissionCodeDropList(HotelGroupId hotel) {
		// TODO Auto-generated method stub
		return this.commissionCodeMapper.commissionCodeDropList(hotel);
	}

	@Override
	public List<DropdownDto> selectConfigCode(Map param) {
		// TODO Auto-generated method stub
		return this.tbpp01aMapper.selectConfigCode(param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RateCodeDto> ratecodeDropList(Map param) {
		// TODO Auto-generated method stub
		String planStart = FormatUtil.toString(param.get("planStart"));
		String planEnd = FormatUtil.toString(param.get("planEnd"));
		planStart = DateUtil.formatDate(planStart, "yyyyMMdd");
		planEnd = DateUtil.formatDate(planEnd, "yyyyMMdd");
		param.put("startDate", planStart);
		param.put("endDate", planEnd);
		return this.priceCodeResMapper.ratecodeDropList(param);
	}

	@Override
	public List<DropdownDto> payCodeDropList(HotelGroupId hotel) {
		// TODO Auto-generated method stub
		return this.payCodeMapper.payCodeDropList(hotel);
	}

	@Override
	public List<DropdownDto> paylicenceCodeDropList(HotelGroupId hotel) {
		// TODO Auto-generated method stub
		return this.payCodeMapper.paylicenceCodeDropList(hotel);
	}
	
	/**
	 * 批量选择房间
	 * (non Javadoc) 
	 * @Title: batchChooseRoom
	 * @Description: TODO
	 * @param paramMap
	 * @return
	 * @see com.whxx.hms.service.ReservationSrv#batchChooseRoom(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map batchChooseRoom(Map  paramMap) {
		// TODO Auto-generated method stub
		Map map = new HashMap<>();
		try {
			//标记
			List<DropdownDto> signList = new ArrayList<>();
			DropdownDto sign = new DropdownDto();
			sign.setId("VC");
			sign.setText("VC");
			signList.add(sign);
			sign = new DropdownDto();
			sign.setId("VD");
			sign.setText("VD");
			signList.add(sign);
			sign = new DropdownDto();
			sign.setId("OC");
			sign.setText("OC");
			signList.add(sign);
			sign = new DropdownDto();
			sign.setId("OD");
			sign.setText("OD");
			signList.add(sign);
			map.put("signList", signList);
			//楼栋
			List<DropdownDto> buildingList = this.buildingMapper.buildingDropList(paramMap);
			map.put("buildingList", buildingList);
			//楼层
			if("".equals(FormatUtil.toString(paramMap.get("building")))){
				paramMap.put("building", "A");
			}
			List<DropdownDto> floorList = this.floorMapper.floorDropList(paramMap);
			map.put("floorList", floorList);
			//客房特征
			paramMap.put("configCode", "ROOM_LOCATION");
			List<DropdownDto> roomSpecList = this.tbpp01aMapper.selectConfigCode(paramMap);
			map.put("roomSpecList", roomSpecList);
			//房间
			List<RoomDto> roomList = this.roomMapper.roomDropDownList(paramMap);
			//房间列表排序
			roomList.sort((RoomDto h1, RoomDto h2) -> h1.getCode().compareTo(h2.getCode()));
			map.put("roomList", roomList);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("========查询房间下拉框失败======="  + e.getMessage(),e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "查询房间下拉框失败", map);
		}
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "查询房间下拉框成功", map);
	}

	/**
	 * 自动排房
	 * (non Javadoc) 
	 * @Title: autoArrangeRoom
	 * @Description: TODO
	 * @param priMainId
	 * @return
	 * @see com.whxx.hms.service.ReservationSrv#autoArrangeRoom(java.lang.String)
	 */
	@Override
	public Map autoArrangeRoom(String  priMainId) {
		// TODO Auto-generated method stub
		Map map = new HashMap<>();
		try {
			List<SubInfoDto> subInfoList = this.subInfoMapper.batchOrderList(priMainId);
			//遍历获取房号
			for(SubInfoDto item : subInfoList){
				SubInfo subInfo = this.subInfoMapper.getSubInfo(item.getSubInfoId());
				RoomStatus roomStatus = new RoomStatus();
				roomStatus.setPriceCode(subInfo.getRatecode());
				roomStatus.setRoomTypeCode(subInfo.getRoomType());
				roomStatus.setRoomDateStr(DateUtil.formatDate(subInfo.getPlanStart(), "yyyyMMdd"));
				List<DropdownDto> roomCodeList = this.roomStatusMapper.roomCodeList(roomStatus);
			}
			/* Map<String,Long> result1= subInfoList
					 					.stream()
					 					.collect(Collectors.groupingBy(SubInfoDto::getRoomType,Collectors.counting()));*/
			//logger.info("result1===" + result1);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("========查询房间下拉框失败======="  + e.getMessage(),e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "查询房间下拉框失败", map);
		}
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "查询房间下拉框成功", map);
	}
	@Override
	public List<ModifyLogs> selectByPriMainId(ModifyLogs modifyLogs) {
		// TODO Auto-generated method stub
		return this.modifyLogsMapper.selectByPriMainId(modifyLogs);
	}

	
}
