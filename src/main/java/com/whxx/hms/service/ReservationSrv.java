package com.whxx.hms.service;

import java.util.List;
import java.util.Map;

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

@SuppressWarnings("rawtypes")
public interface ReservationSrv {
	
	/**
	 * 查询房型列表下拉框
	 * @Title: listRoomTypeApp 
	 * @Description: TODO
	 * @param param
	 * @return
	 * @return: List<RoomType>
	 */
	List<RoomTypeDto> listRoomTypeApp(Map param);

	/**
	 * 处理新增预订单
	 * @Title: createNewRsv 
	 * @Description: TODO
	 * @param rsv
	 * @return
	 * @return: Map
	 */
	Map createNewRsv(Reservation rsv);
	
	/**
	 * 修改预订单
	 * @Title: updateNewRsv 
	 * @Description: TODO
	 * @param rsv
	 * @return
	 * @return: Map
	 */
	Map updateNewRsv(Reservation rsv);
	
	/**
	 * 删除预订单
	 * @Title: deleteNewRsv 
	 * @Description: TODO
	 * @param mainInfoId
	 * @return
	 * @return: Map
	 */
	Map deleteNewRsv(String mainInfoId);
	
	/**
	 * 新增预订单后返回订单结果
	 * @Title: getNewRsvResult 
	 * @Description: TODO
	 * @param priMainId
	 * @return
	 * @return: Map
	 */
	Reservation getNewRsvResult(String priMainId);
	
	/**
	 * 预订单预收款
	 * @Title: prepay2NewRsv 
	 * @Description: TODO
	 * @param priMainId
	 * @return
	 * @return: Map
	 */
	Map prepay2NewRsv(PrepayDto prepayDto);
	
	/**
	 * 预订单预授权
	 * @Title: prelicence2NewRsv 
	 * @Description: TODO
	 * @param priMainId
	 * @return
	 * @return: Map
	 */
	Map prelicence2NewRsv(PreLicenceListDto preLicenceListDto);
	
	/**
	 * 查询预收款列表
	 * @Title: prelicenceList 
	 * @Description: TODO
	 * @param priMainId
	 * @return
	 * @return: List<PreLicenceDto>
	 */
	List<PreLicenceDto> prelicenceList(String priMainId);
	
	/**
	 * 修改预授权
	 * @Title: updatePrelicence 
	 * @Description: TODO
	 * @param preLicenceList
	 * @return
	 * @return: Map
	 */
	Map updatePrelicence(PreLicenceListDto preLicenceListDto);
	
	/**
	 * 撤回预授权信息
	 * @Title: prelicenceList 
	 * @Description: TODO
	 * @param priMainId
	 * @return
	 * @return: List<PreLicenceDto>
	 */
	Map cancelPrelicence(PreLicenceDto preLicenceDto);
	
	
	/**
	 * 预授权转移
	 * @Title: updatePrelicence 
	 * @Description: TODO
	 * @param preLicenceList
	 * @return
	 * @return: Map
	 */
	Map transferPrelicence(PreLicenceDto preLicenceDto);
	
	/**
	 * 预订单其它信息
	 * @Title: otherInfo2NewRsv 
	 * @Description: TODO
	 * @param otherInfo
	 * @return
	 * @return: Map
	 */
	Map message2NewRsv(MessageDto messageDto);
	
	/**
	 * 处理留言
	 * @Title: updateOtherInfo4Rsv 
	 * @Description: TODO
	 * @param otherInfo
	 * @return
	 * @return: Map
	 */
	Map updateMessages(String messagesId);
	
	/**
	 * 留言列表
	 * @Title: messagesList 
	 * @Description: TODO
	 * @return
	 * @return: Map
	 */
	Map messagesList(Integer hotelGroupId,Integer hotelId);
	
	/**
	 * 修改预订单状态
	 * @Title: updateMainStatus 
	 * @Description: TODO
	 * @param mainId
	 * @return
	 * @return: Map
	 */
	Map updateMainStatus(String mainId,String priMainId);
	
	/**
	 * 排房根据房型查询可用房间号
	 * @Title: roomDropDownList 
	 * @Description: TODO
	 * @param param
	 * @return
	 * @return: List<DropdownDto>
	 */
	List<RoomDto> roomDropDownList(Map param);
	
	/**
	 * 更新subinfo信息
	 * @Title: updateSubInfo 
	 * @Description: TODO
	 * @param param
	 * @return
	 * @return: Map
	 */
	Map updateSubInfo(Map param);
	
	/**
	 * 新增入住人信息
	 * @Title: createRoomPerson 
	 * @Description: TODO
	 * @param roomPersonDto
	 * @return
	 * @return: Map
	 */
	Map createRoomPerson(RoomPersonListDto roomPersonListDto);
	
	
	/**
	 * 根据订单号查询入住人信息
	 * @Title: selectBySubId 
	 * @Description: TODO
	 * @param subId
	 * @return
	 * @return: List<RoomPerson>
	 */
	List<RoomPerson> selectBySubId(String subId);
	
	/**
	 * 修改入住人信息
	 * @Title: createRoomPerson 
	 * @Description: TODO
	 * @param roomPersonDto
	 * @return
	 * @return: Map
	 */
	Map updateRoomPerson(RoomPersonListDto roomPersonListDto);
	
	/**
	 * 删除入住人信息
	 * @Title: createRoomPerson 
	 * @Description: TODO
	 * @param roomPersonDto
	 * @return
	 * @return: Map
	 */
	Map deleteRoomPerson(String roomPersonId);
	
	
	/**
	 * 取消订单
	 * @Title: cancelRsv 
	 * @Description: TODO
	 * @param roomPersonDtoList
	 * @return
	 * @return: Map
	 */
	Map cancelRsv(Map param);
	
	
	/**
	 * 查询销售员的下拉框
	 * @Title: insert 
	 * @Description: TODO
	 * @param tbaccessLog
	 * @return: void
	 */
	List<DropdownDto> salesManDropDownList(Map param);
	
	/**
	 * 查询子订单明细（预订）
	 * @Title: preorderlist 
	 * @Description: TODO
	 * @param priMainId
	 * @return
	 * @return: List<SubInfoDto>
	 */
	List<SubInfoDto> preorderlist(String priMainId);
	
	/**
	 * 查询预授权转移列表
	 * @Title: transferList 
	 * @Description: TODO
	 * @param searchContent
	 * @return
	 * @return: List<SubInfoDto>
	 */
	List<SubInfoDto> transferList(Map param);
	
	/**
	 * 查询预授权日志
	 * @Title: prelicenceLogs 
	 * @Description: TODO
	 * @param priMainId
	 * @return
	 * @return: List<Map>
	 */
	List<PreLicenceDto> prelicenceLogs(String priMainId);
	
	/**
	 * 佣金码下拉框
	 * @Title: commissionCodeDropList 
	 * @Description: TODO
	 * @param hotel
	 * @return
	 * @return: List<DropdownDto>
	 */
	List<DropdownDto> commissionCodeDropList(HotelGroupId hotel);
	
	/**
	 * 根据configcode查询通用代码档
	 * @Title: selectConfigCode 
	 * @Description: TODO
	 * @param hotel
	 * @return
	 * @return: List<DropdownDto>
	 */
	List<DropdownDto> selectConfigCode(Map param);
	
	/**
	 * 查询房价码
	 * @Title: ratecodeDropList 
	 * @Description: TODO
	 * @param param
	 * @return
	 * @return: List<RateCodeDto>
	 */
	List<RateCodeDto> ratecodeDropList( Map param);
	
	
	/**
	 * 付款方式（所有付款方式不区分类别）
	 * @Title: payCodeDropList 
	 * @Description: TODO
	 * @param hotel
	 * @return
	 * @return: List<DropdownDto>
	 */
	List<DropdownDto> payCodeDropList(HotelGroupId hotel);
	
	
	/**
	 *  付款方式（只包含国内卡和国外卡）
	 * @Title: payCodeDropList 
	 * @Description: TODO
	 * @param hotel
	 * @return
	 * @return: List<DropdownDto>
	 */
	List<DropdownDto> paylicenceCodeDropList(HotelGroupId hotel);
	
	/**
	 * 批量选择房间
	 * @Title: batchChooseRoom 
	 * @Description: TODO
	 * @param hotel
	 * @return
	 * @return: Map
	 */
	Map batchChooseRoom(Map paramMap);
	
	/**
	 * 查询异动日志
	 * @Title: selectByPriMainId 
	 * @Description: TODO
	 * @param modifyLogs
	 * @return
	 * @return: List<ModifyLogs>
	 */
	List<ModifyLogs> selectByPriMainId(ModifyLogs modifyLogs);
	
	/**
	 * 自动排房
	 * @Title: autoArrangeRoom 
	 * @Description: TODO
	 * @param priMainId
	 * @return
	 * @return: Map
	 */
	Map autoArrangeRoom(String  priMainId);
}
