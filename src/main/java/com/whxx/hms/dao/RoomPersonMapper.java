package com.whxx.hms.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.whxx.hms.model.RoomPerson;

@Repository
public interface RoomPersonMapper {
	
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
	 * 新增入住人信息
	 * @Title: insertConsume 
	 * @Description: TODO
	 * @param consume
	 * @return: void
	 */
	void insertRoomPerson(RoomPerson roomPerson);
	
	/**
	 * 修改入住人信息
	 * @Title: updateById 
	 * @Description: TODO
	 * @param roomPerson
	 * @return: void
	 */
	void updateById(RoomPerson roomPerson);
	
	/**
	 * 删除入住人信息
	 * @Title: deleteById 
	 * @Description: TODO
	 * @param roomPersonId
	 * @return: void
	 */
	void deleteById(String roomPersonId);
	
	
}
