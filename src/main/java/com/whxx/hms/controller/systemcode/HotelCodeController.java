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
import com.whxx.core.utils.ToolUtil;
import com.whxx.core.utils.UUIDUtil;
import com.whxx.hms.model.Building;
import com.whxx.hms.model.Floor;
import com.whxx.hms.model.Room;
import com.whxx.hms.model.RoomType;
import com.whxx.hms.service.HotelCodeSrv;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpStatus;

@RequestMapping("/pubCode")
@RestController
public class HotelCodeController extends BaseController{

	private final static Logger logger = LoggerFactory.getLogger(HotelCodeController.class);
	@Autowired
	private HotelCodeSrv hotelCodeSrvImpl;

	/*
	 * 获取酒店所有楼栋
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/listBuilding")
	public Object listBuilding(HttpServletRequest request) {
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		logger.info("=====酒店楼栋查询参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "酒店楼栋查询参数为空", paramMap);
		}
		new PageFactory<Map<String, Object>>().defaultPage();
    	List<Map<String, Object>> list = this.hotelCodeSrvImpl.listBuilding(paramMap);
    	
        PageInfo pageInfo = new PageInfo(list);
        Page pageList = (Page) list;
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("total",pageInfo.getTotal());
        result.put("rows", pageList);
        return CommUtil.setMessage(HttpStatus.HTTP_OK, "酒店楼栋查询结果", result);
	}
	
	/*
	 * 获取酒店某一楼栋
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/selectBuilding")
	public Object selectBuildingByName(HttpServletRequest request) {
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		logger.info("=====酒店楼栋查询参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "酒店楼栋查询参数为空", paramMap);
		}
		Building building=this.hotelCodeSrvImpl.selectBuildingByName(paramMap);
		Map map = new HashMap<>();
		map.put("building", building);
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "楼栋查询结果", map);
	}
	
	/*
	 * 修改酒店楼栋信息
	 */
	@RequestMapping("/updateBuilding")
	public Object updateBuilding(HttpServletRequest request) {
		logger.info("=======楼栋信息进行修改=======");
		String buildingName = request.getParameter("buildingName");
		String buildingEnName = request.getParameter("buildingEnName");
		String stopped = request.getParameter("stopped");
		String buildingId = request.getParameter("buildingId");
		Building building=new Building();
		building.setBuildingName(buildingName);
		building.setBuildingEnName(buildingEnName);
		building.setStopped(stopped);
		building.setUpdateDate(new Date());
		building.setUpdateEmp(getLoginUserAccount());
		building.setBuildingId(buildingId);
		try {
			this.hotelCodeSrvImpl.updateBuilding(building);
			logger.info("=====楼栋信息修改成功=======");
		} catch (Exception e) {
			logger.error("=====楼栋信息修改失败=======" + e.getMessage(),e);
        	return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "楼栋信息修改失败", null);
		}
		
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "楼栋信息修改成功", null);
	}
	
	/*
	 * 添加新楼栋
	 */
	@RequestMapping("/insertBuilding")
	public Object insertBuilding(HttpServletRequest request) {
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		logger.info("=====添加新楼栋参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "添加新楼栋参数为空", paramMap);
		}
		String buildingCode = request.getParameter("buildingCode");
		String buildingConfigCode=this.hotelCodeSrvImpl.isBuildingConfigCode(buildingCode);
		if(buildingConfigCode!=null) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "项目代码不能重复", null);
		}
		//新增,seq默认为1
		Building building=new Building();
		String buildingId = UUIDUtil.getUUID();
		BeanUtil.copyProperties(paramMap, building);
		building.setBuildingId(buildingId);
		building.setSeq(1);
		building.setStopped("N");//是否弃用，默认不弃用
		building.setLineNo(ToolUtil.getLineNo());
		building.setVersion(1);
		try {
			this.hotelCodeSrvImpl.insertBuilding(setCreateAndUpdate(building));
			logger.info("=====添加新楼栋成功=======");
		} catch (Exception e) {
			logger.error("=====添加新楼栋失败=======" + e.getMessage(),e);
       	return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "添加新楼栋失败", null);
		}
		
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "添加新楼栋成功", null);
	}
	
	/*
	 * 删除楼栋
	 *  参数:buildingId
	 */
	@RequestMapping("/deleteBuilding/{buildingId}")
	public Object deleteBuilding(HttpServletRequest request,@PathVariable String buildingId) {
		logger.info("=======删除楼栋=======");
		try {
			this.hotelCodeSrvImpl.deleteBuildingById(buildingId);
			logger.info("=====删除楼栋成功=======");
		} catch (Exception e) {
			logger.error("=====删除楼栋失败=======" + e.getMessage(),e);
        	return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "删除楼栋失败", null);
		}
		
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "删除楼栋成功", null);
	}
	
	
	/*
	 * 获取酒店所有楼层
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/listFloor")
	public Object listFloor(HttpServletRequest request) {
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		logger.info("=====酒店楼层查询参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "酒店楼层查询参数为空", paramMap);
		}
		new PageFactory<Map<String, Object>>().defaultPage();
		List<Map<String, Object>> list = this.hotelCodeSrvImpl.listFloor(paramMap);
		
		PageInfo pageInfo = new PageInfo(list);
		Page pageList = (Page) list;
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("total",pageInfo.getTotal());
		result.put("rows", pageList);
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "酒店楼层查询结果", result);
	}
	
	/*
	 * 获取酒店某一楼层
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/selectFloor")
	public Object selectFloorByName(HttpServletRequest request) {
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		logger.info("=====酒店楼栋查询参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "酒店楼栋查询参数为空", paramMap);
		}
		Floor floor=this.hotelCodeSrvImpl.selectFloorByName(paramMap);
		Map map = new HashMap<>();
		map.put("floor", floor);
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "楼层查询结果", map);
	}
	
	/*
	 * 修改酒店楼栋信息
	 */
	@RequestMapping("/updateFloor")
	public Object updateFloor(HttpServletRequest request) {
		logger.info("=======楼层信息进行修改=======");
		String floorName = request.getParameter("floorName");
		String floorEnName = request.getParameter("floorEnName");
		String stopped = request.getParameter("stopped");
		String floorId = request.getParameter("floorId");
		Floor floor=new Floor();
		floor.setFloorName(floorName);
		floor.setFloorEnName(floorEnName);
		floor.setStopped(stopped);
		floor.setUpdateDate(new Date());
		floor.setUpdateEmp(getLoginUserAccount());
		floor.setFloorId(floorId);
		try {
			this.hotelCodeSrvImpl.updateFloor(floor);
			logger.info("=====酒店楼栋信息修改成功=======");
		} catch (Exception e) {
			logger.error("=====酒店楼栋信息修改失败=======" + e.getMessage(),e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "酒店楼栋信息修改失败", null);
		}
		
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "酒店楼栋信息修改成功", null);
	}
	
	/*
	 * 添加新楼层
	 *  参数:floorId,buildingCode
	 */
	@RequestMapping("/insertFloor")
	public Object insertFloor(HttpServletRequest request) {
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		logger.info("=====添加新楼层参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "添加新楼层参数为空", paramMap);
		}
		String floorCode = request.getParameter("floorCode");
		String buildingCode = request.getParameter("buildingCode");
		String floorConfigCode=this.hotelCodeSrvImpl.isFloorConfigCode(floorCode,buildingCode);
		if(floorConfigCode!=null) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "项目代码不能重复", null);
		}
		//新增,seq默认为1
		Floor floor=new Floor();
		String floorId = UUIDUtil.getUUID();
		BeanUtil.copyProperties(paramMap, floor);
		floor.setFloorId(floorId);
		floor.setSeq(1);
		floor.setStopped("N");//是否弃用，默认不弃用
		floor.setLineNo(ToolUtil.getLineNo());
		floor.setVersion(1);
		try {
			this.hotelCodeSrvImpl.insertFloor(setCreateAndUpdate(floor));
			logger.info("=====添加新楼栋成功=======");
		} catch (Exception e) {
			logger.error("=====添加新楼栋失败=======" + e.getMessage(),e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "添加新楼栋失败", null);
		}
		
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "添加新楼栋成功", null);
	}
	
	/*
	 * 删除楼栋
	 *  参数:floorId
	 */
	@RequestMapping("/deleteFloor/{floorId}")
	public Object deleteFloor(HttpServletRequest request,@PathVariable String floorId) {
		logger.info("=======删除楼层=======");
		try {
			this.hotelCodeSrvImpl.deleteFloorById(floorId);
			logger.info("=====删除楼层成功=======");
		} catch (Exception e) {
			logger.error("=====删除楼层失败=======" + e.getMessage(),e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "删除楼层失败", null);
		}
		
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "删除楼层成功", null);
	}
	
	/*
	 * 获取酒店所有房型
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/listRoomType")
	public Object listRoomType(HttpServletRequest request) {
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		logger.info("=====酒店房型查询参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "酒店房型查询参数为空", paramMap);
		}
		new PageFactory<Map<String, Object>>().defaultPage();
		List<Map<String, Object>> list = this.hotelCodeSrvImpl.listRoomType(paramMap);
		
		PageInfo pageInfo = new PageInfo(list);
		Page pageList = (Page) list;
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("total",pageInfo.getTotal());
		result.put("rows", pageList);
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "酒店房型查询结果", result);
	}
	
	/*
	 * 获取酒店所有房型不分页
	 */
	@RequestMapping("/listType")
	public Object listType(HttpServletRequest request) {
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		logger.info("=====酒店房型查询参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "酒店房型查询参数为空", paramMap);
		}
		
		List<Map<String, Object>> list = this.hotelCodeSrvImpl.listRoomType(paramMap);
		
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "酒店房型查询结果", list);
	}
	
	/*
	 * 获取酒店某一房型
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/selectRoomType")
	public Object selectRoomTypeByName(HttpServletRequest request) {
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		logger.info("=====酒店楼栋查询参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "酒店楼栋查询参数为空", paramMap);
		}
		RoomType roomType=this.hotelCodeSrvImpl.selectRoomTypeByName(paramMap);
		Map map = new HashMap<>();
		map.put("roomType", roomType);
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "房型查询结果", map);
	}
	
	/*
	 * 修改酒店房型信息
	 */
	@RequestMapping("/updateRoomType")
	public Object updateRoomType(HttpServletRequest request) {
		logger.info("=======房型进行修改=======");
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		logger.info("=====房型修改参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "修改房型参数为空", paramMap);
		}
		
		RoomType roomType=new RoomType();
		BeanUtil.copyProperties(paramMap, roomType);
		roomType.setUpdateDate(new Date());
		roomType.setUpdateEmp(getLoginUserAccount());
		try {
			this.hotelCodeSrvImpl.updateRoomType(roomType);
			logger.info("=====房型修改成功=======");
		} catch (Exception e) {
			logger.error("=====房型修改失败=======" + e.getMessage(),e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "房型修改失败", null);
		}
		
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "房型修改成功", null);
	}
	
	/*
	 * 添加新房型
	 *  参数:roomTypeId
	 */
	@RequestMapping("/insertRoomType")
	public Object insertRoomType(HttpServletRequest request) {
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		logger.info("=====添加新房型参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "添加新房型参数为空", paramMap);
		}
		String roomTypeCode = request.getParameter("roomTypeCode");
		String roomTypeConfigCode=this.hotelCodeSrvImpl.isRoomTypeConfigCode(roomTypeCode);
		if(roomTypeConfigCode!=null) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "项目代码不能重复", null);
		}
		//新增,seq默认为1
		RoomType roomType=new RoomType();
		String roomTypeId = UUIDUtil.getUUID();
		BeanUtil.copyProperties(paramMap, roomType);
		roomType.setRoomTypeId(roomTypeId);
		roomType.setSeq(1);
		roomType.setStopped("N");//是否弃用，默认不弃用
		roomType.setLineNo(ToolUtil.getLineNo());
		try {
			this.hotelCodeSrvImpl.insertRoomType(setCreateAndUpdate(roomType));
			logger.info("=====添加新房型成功=======");
		} catch (Exception e) {
			logger.error("=====添加新房型失败=======" + e.getMessage(),e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "添加新房型失败", null);
		}
		
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "添加新房型成功", null);
	}
	
	/*
	 * 删除房型
	 *  参数:roomTypeId
	 */
	@RequestMapping("/deleteRoomType/{roomTypeId}")
	public Object deleteRoomType(HttpServletRequest request,@PathVariable String roomTypeId) {
		logger.info("=======删除房型=======");
		try {
			this.hotelCodeSrvImpl.deleteRoomTypeById(roomTypeId);
			logger.info("=====删除房型成功=======");
		} catch (Exception e) {
			logger.error("=====删除房型失败=======" + e.getMessage(),e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "删除房型失败", null);
		}
		
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "删除房型成功", null);
	}
	
	/*
	 * 获取所有房号
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/listRooms")
	public Object listRooms(HttpServletRequest request) {
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		logger.info("=====酒店房号查询参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "酒店房号查询参数为空", paramMap);
		}
		new PageFactory<Map<String, Object>>().defaultPage();
    	List<Map<String, Object>> list = this.hotelCodeSrvImpl.listRooms(paramMap);
        PageInfo pageInfo = new PageInfo(list);
        Page pageList = (Page) list;
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("total",pageInfo.getTotal());
        result.put("rows", pageList);
        return CommUtil.setMessage(HttpStatus.HTTP_OK, "酒店房号查询结果", result);
	}
	
	/*
	 * 获取酒店某一房间
	 * roomCode
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/selectRoom")
	public Object selectRoomByRoomCode(HttpServletRequest request) {
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		logger.info("=====酒店楼栋查询参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "酒店楼栋查询参数为空", paramMap);
		}
		Room room=this.hotelCodeSrvImpl.selectRoomByRoomCode(paramMap);
		Map map = new HashMap<>();
		map.put("room", room);
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "房型查询结果", map);
	}
	
	/*
	 * 添加新房号
	 *  参数:roomId
	 */
	@RequestMapping("/insertRoom")
	public Object insertRoom(HttpServletRequest request) {
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		logger.info("=====添加新房号参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "添加新房号参数为空", paramMap);
		}
		//判断房号是否重复
		String roomCode = request.getParameter("roomCode");
		String roomConfigCode=this.hotelCodeSrvImpl.isRoomConfigCode(roomCode);
		if(roomConfigCode!=null) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "房号不能重复", null);
		}
		//新增,seq默认为1
		Room room=new Room();
		String roomTypeId = UUIDUtil.getUUID();
		BeanUtil.copyProperties(paramMap, room);
		room.setRoomId(roomTypeId);
		room.setStopped("N");//是否弃用，默认不弃用
		room.setLineNo(ToolUtil.getLineNo());
		room.setVersion(1);
		try {
			this.hotelCodeSrvImpl.insertRoom(setCreateAndUpdate(room));
			logger.info("=====添加新房号成功=======");
		} catch (Exception e) {
			logger.error("=====添加新房型失败=======" + e.getMessage(),e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "添加新房号失败", null);
		}
		
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "添加新房号成功", null);
	}
	
	/*
	 * 批量添加房号
	 */
	@RequestMapping("/insertRooms")
	public Object insertRooms(HttpServletRequest request) {
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		logger.info("=====批量添加新房号参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "批量添加新房号参数为空", paramMap);
		}
		String roomPrefix = paramMap.get("roomPrefix");//获取房号前缀
		String roomStart = paramMap.get("roomStart");//房号开始
		String roomEnd = paramMap.get("roomEnd");//房号结束
		long startNum = Long.parseLong(roomPrefix+roomStart);
		long endNum = Long.parseLong(roomPrefix+roomEnd);
		for(long i=startNum;i<=endNum;i++) {
			String roomCode=i+"";
			String roomConfigCode=this.hotelCodeSrvImpl.isRoomConfigCode(roomCode);
			if(roomConfigCode!=null) {
				return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "房号不能重复", null);
			}
		}
		//批量添加房号
		try {
			this.hotelCodeSrvImpl.insertRooms(paramMap,startNum,endNum);
			logger.info("=====批量添加新房号成功=======");
		} catch (Exception e) {
			logger.error("=====批量添加新房型失败=======" + e.getMessage(),e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "批量添加新房号失败", null);
		}
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "批量添加新房号成功", null);
	}
	
	/*
	 * 修改酒店房号信息
	 */
	@RequestMapping("/updateRoom")
	public Object updateRoom(HttpServletRequest request) {
		logger.info("=======房号进行修改=======");
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		logger.info("=====房号修改参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "修改房号参数为空", paramMap);
		}
		
		Room room=new Room();
		BeanUtil.copyProperties(paramMap, room);
		room.setUpdateDate(new Date());
		room.setUpdateEmp(getLoginUserAccount());
		try {
			this.hotelCodeSrvImpl.updateRoom(room);
			logger.info("=====房号修改成功=======");
		} catch (Exception e) {
			logger.error("=====房号修改失败=======" + e.getMessage(),e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "房号修改失败", null);
		}
		
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "房号修改成功", null);
	}
	
	/*
	 * 删除房号
	 *  参数:roomId
	 */
	@RequestMapping("/deleteRoomType/{roomId}")
	public Object deleteRoom(HttpServletRequest request,@PathVariable String roomId) {
		logger.info("=======删除房号=======");
		try {
			this.hotelCodeSrvImpl.deleteRoomById(roomId);
			logger.info("=====删除房号成功=======");
		} catch (Exception e) {
			logger.error("=====删除房号失败=======" + e.getMessage(),e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "删除房号失败", null);
		}
		
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "删除房号成功", null);
	}
}
