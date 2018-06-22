package com.whxx.hms.controller.hotelSituation;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.whxx.core.controller.BaseController;
import com.whxx.core.factory.PageFactory;
import com.whxx.core.utils.CommUtil;
import com.whxx.core.utils.Convert;
import com.whxx.core.utils.DateUtil;
import com.whxx.core.utils.HttpUtil;
import com.whxx.core.utils.ToolUtil;
import com.whxx.core.utils.UUIDUtil;
import com.whxx.hms.model.RoomStatus;
import com.whxx.hms.model.RoomType;
import com.whxx.hms.model.RoomTypeHistory;
import com.whxx.hms.model.SubInfo;
import com.whxx.hms.model.TbHotel;
import com.whxx.hms.service.HotelCodeSrv;
import com.whxx.hms.service.HotelSituationSrv;
import com.whxx.hms.service.HotelSrv;
import com.whxx.hms.service.RoomSrv;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpStatus;

@Controller
@RestController
@RequestMapping("/hotelSituation")
public class HotelSituationController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(HotelSituationController.class);

	@Autowired
	private HotelCodeSrv hotelCodeSrvImpl;
	@Autowired
	private RoomSrv roomSrvImpl;
	@Autowired
	private HotelSituationSrv hotelSituationSrvImpl;

	@Autowired
	private HotelSrv hotelSrvImpl;

	/**
	 * 官网主页获取酒店hotelId和hotelGroupId
	 * 
	 * @Title: getWebInfo
	 * @param request
	 * @param response
	 * @return
	 * @return: Object
	 * @throws Exception
	 * 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/getHotelInfo")
	public Object getHotelInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map map = new HashedMap<>();
		logger.info("-------通过酒店代码获取hotelId和hotelGroupId----------");
		String hotelCode = request.getParameter("hotelCode");
		TbHotel tbHotel = this.hotelSrvImpl.selectByHotelCode(hotelCode);
		map.put("hotelId", tbHotel.getHotelId());
		map.put("hotelGroupId", tbHotel.getHotelGroupId());
		return CommUtil.setMessage("200", "酒店信息", map);
	}

	/*
	 * 获取所有超预留日志信息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/listRoomTypeHistory")
	public Object listRoomTypeHistory(HttpServletRequest request) {
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		logger.info("=====超预留日志信息查询参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "超预留日志信息查询参数为空", paramMap);
		}
		new PageFactory<Map<String, Object>>().defaultPage();
		List<Map<String, Object>> list = this.hotelSituationSrvImpl.listRoomTypeHistory(paramMap);
		PageInfo pageInfo = new PageInfo(list);
		Page pageList = (Page) list;
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", pageInfo.getTotal());
		result.put("rows", pageList);
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "市场码信息查询结果", result);
	}

	/*
	 * 超预留进行修改 参数hotelId,hotelGroupId,roomTypeId,roomTypeName,overNum
	 */
	@RequestMapping("/supReservation")
	public Object updateSupReservation(HttpServletRequest request, HttpServletResponse response) {
		logger.info("=======房型进行修改=======");
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		logger.info("=====房型修改参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "修改房型参数为空", paramMap);
		}
		int hotelId = Integer.parseInt(paramMap.get("hotelId"));
		int hotelGroupId = Integer.parseInt(paramMap.get("hotelGroupId"));
		int overNum = Integer.parseInt(paramMap.get("overNum"));// 超预留新值
		try {

			RoomType roomTypeSelect = this.hotelCodeSrvImpl.selectRoomTypeByName(paramMap);
			RoomTypeHistory roomTypeHistory = new RoomTypeHistory();
			String roomTypeHistoryId = UUIDUtil.getUUID();
			roomTypeHistory.setRoomTypeHistoryId(roomTypeHistoryId);
			roomTypeHistory.setLineNo(ToolUtil.getLineNo());
			roomTypeHistory.setHotelId(hotelId);
			roomTypeHistory.setHotelGroupId(hotelGroupId);
			roomTypeHistory.setMaxPerson(roomTypeSelect.getMaxPerson());
			roomTypeHistory.setNum(roomTypeSelect.getNum());
			roomTypeHistory.setOverNumNew(overNum);
			roomTypeHistory.setOverNumOld(roomTypeSelect.getOverNum());
			roomTypeHistory.setRemark(roomTypeSelect.getRemark());
			roomTypeHistory.setRoomTypeCode(roomTypeSelect.getRoomTypeCode());
			roomTypeHistory.setRoomTypeEnName(roomTypeSelect.getRoomTypeEnName());
			roomTypeHistory.setRoomTypeName(roomTypeSelect.getRoomTypeName());
			roomTypeHistory.setSeq(roomTypeSelect.getSeq());
			roomTypeHistory.setStopped(roomTypeSelect.getStopped());
			roomTypeHistory.setUpdateDate(roomTypeSelect.getUpdateDate());
			roomTypeHistory.setUpdateEmp(roomTypeSelect.getUpdateEmp());
			roomTypeHistory.setVersion(1);
			this.hotelSituationSrvImpl.insertRoomTypeHistory(setCreateEmpAndTime(roomTypeHistory));

			RoomType roomType = new RoomType();
			BeanUtil.copyProperties(paramMap, roomType);
			roomType.setUpdateDate(new Date());
			roomType.setUpdateEmp(getLoginUserAccount());
			this.hotelCodeSrvImpl.updateRoomType(roomType);

			// 超预留日志进行新添

			logger.info("=====房型修改成功=======");
		} catch (Exception e) {
			logger.error("=====房型修改失败=======" + e.getMessage(), e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "房型修改失败", null);
		}

		return CommUtil.setMessage(HttpStatus.HTTP_OK, "房型修改成功", null);
	}

	/*
	 * 查询房情信息 参数：hotelId,hotelFGroupId,startDate
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/hotelSitInfo")
	public Object getHotelSitInfo(HttpServletRequest request) throws ParseException {
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		logger.info("=====查询房情信息参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "房情信息查询参数为空", paramMap);
		}
		/*
		 * String hotelId = paramMap.get("hotelId"); String hotelGroupId =
		 * paramMap.get("hotelGroupId");
		 */

		// 获取今日日期
		String dateStr = DateUtil.format(new Date(), "yyyy-MM-dd");
		String[] dateArr = dateStr.split("-");
		StringBuilder sb = new StringBuilder();
		for (String string : dateArr) {
			sb.append(string);
		}
		paramMap.put("roomDateStr", sb.toString());

		Map resultMap = new HashMap<>();
		try {

			String startDate = DateUtil.getDateAdd(paramMap.get("StartDate"), -1);// 日期先减一
			/*List<Map<String, Object>> listRooms = this.hotelCodeSrvImpl.listRooms(paramMap);
			int roomSum = listRooms.size();*/
			
			//所有房间总数量
			List<Map> listRoomInfo = new ArrayList<>();

			List<Map<String, Object>> listRoomType = this.hotelCodeSrvImpl.listRoomType(paramMap);
			for (Map<String, Object> mapRoomType : listRoomType) {
				Map mapRoom = new HashMap<>();
				String roomTypeCode = (String) mapRoomType.get("roomTypeCode");// 获取房型
				String roomTypeName = (String) mapRoomType.get("roomTypeName");
				paramMap.put("roomTypeCode", roomTypeCode);

				//List<String> listSubId = this.roomSrvImpl.getRoomList(paramMap);

				List<Map> list = new ArrayList<>();
				// 七天所有数据
				for (int i = 0; i < 7; i++) {
					String sDate = DateUtil.getDateAdd(startDate, 1);// 日期加一

					List<RoomStatus> listRoomStatus = this.hotelSituationSrvImpl.getListRoomStatus(paramMap);
					int roomNum = listRoomStatus.size();//某一房型总数量
					for (RoomStatus roomStatus : listRoomStatus) {
						Map map = new HashMap<>();
						int inRoomCount = 0;// 入住数量
						int repairRoomCount = 0;// 修理房数量
						int takeRoomCount = 0;// 占用房数量
						String inFlag = roomStatus.getInFlag();
						Date start = roomStatus.getLockStart();
						String lockStart = DateUtil.formatDate(start, "yyyy-MM-dd");
						Date end = roomStatus.getLockEnd();
						String lockEnd = DateUtil.formatDate(end, "yyyy-MM-dd");
						if (inFlag.equals("Y")) {
							inRoomCount++;// 入住数量
						}

						if (DateUtil.compareDate(sDate, lockStart) && DateUtil.compareDate(lockEnd, sDate)) {
							repairRoomCount++;// 修理房数量
						}
						
						//
						SubInfo subInfo = this.hotelSituationSrvImpl.getSubInfo(roomStatus.getSubId());
						if (subInfo!=null) {
								String planStart = DateUtil.formatDate(subInfo.getPlanStart(), "yyyy-MM-dd");
								String planEnd = DateUtil.formatDate(subInfo.getPlanEnd(), "yyyy-MM-dd");
								String keepTime = DateUtil.formatDate(subInfo.getKeepTime(), "yyyy-MM-dd");
								if (DateUtil.compareDate(sDate, planStart) && DateUtil.compareDateLess(sDate, keepTime)) {
									takeRoomCount++;// 占用房数量
								}
						}

						int VailRoomCount = roomNum - repairRoomCount - takeRoomCount;// 可售房数量
						map.put("inRoom", inRoomCount);
						map.put("repairRoom", repairRoomCount);
						map.put("VailRoom", VailRoomCount);
						map.put("takeRoom", takeRoomCount);
						map.put("roomNum", roomNum);
						list.add(map);
						
					}

				}

				// mapRoom.put("roomTypeCode", roomTypeCode);
				mapRoom.put("roomTypeName", roomTypeName);
				mapRoom.put("list", list);
				listRoomInfo.add(mapRoom);

			}

			List listSum = new ArrayList<>();
			for (int i = 0; i < 7; i++) {
				Map Mapcount = new HashMap<>();
				int inRoomSum = 0;// 总在住数
				int takeRoomSum = 0;// 总占用数
				int vailRoomSum = 0;// 总可售数
				int repairRoomSum = 0;// 总修理数
				int roomSum=0;//每一房型总数量
				for (int j = 0; j < listRoomInfo.size(); j++) {
					List<Map> listCount = (List) listRoomInfo.get(j).get("list");
					inRoomSum += (int) listCount.get(i).get("inRoom");
					takeRoomSum += (int) listCount.get(i).get("takeRoom");
					vailRoomSum += (int) listCount.get(i).get("vailRoom");
					takeRoomSum += (int) listCount.get(i).get("repairRoom");
					roomSum +=(int) listCount.get(i).get("roomNum");
				}
				Mapcount.put("inRoomSum", inRoomSum);
				Mapcount.put("takeRoomSum", takeRoomSum);
				Mapcount.put("vailRoomSum", vailRoomSum);
				Mapcount.put("repairRoomSum", repairRoomSum);
				Mapcount.put("roomSum", roomSum);
				
				Mapcount.put("inRoomPercent", Convert.getPercent(inRoomSum, roomSum));// 百分比
				Mapcount.put("takeRoomPercent", Convert.getPercent(takeRoomSum, roomSum));
				Mapcount.put("vailRoomPercent", Convert.getPercent(vailRoomSum, roomSum));
				Mapcount.put("repairRoomPercent", Convert.getPercent(repairRoomSum, roomSum));
				listSum.add(Mapcount);
			}

			resultMap.put("listRoomInfo", listRoomInfo);
			resultMap.put("listSum", listSum);
		} catch (Exception e) {
			logger.error("=====房情信息获取失败=======" + e.getMessage(), e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "房情信息获取失败", null);
		}
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "房情信息查询结果", resultMap);
	}

}
