package com.whxx.hms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.whxx.hms.model.Messages;
import com.whxx.hms.model.dto.MessageDto;



@Repository
public interface MessagesMapper {
	
	/**
	 *  留言列表
	 * @Title: selectMainInfoById 
	 * @Description: TODO
	 * @param mainInfoId
	 * @return
	 * @return: MainInfo
	 */
	List<MessageDto> messagesList(@Param("readFlag")String readFlag,@Param("hotelGroupId")Integer hotelGroupId,@Param("hotelId")Integer hotelId);
	
	/**
	 * 未读条数
	 * @Title: unreadCounts 
	 * @Description: TODO
	 * @return
	 * @return: Integer
	 */
	Integer unreadCounts(@Param("hotelGroupId")Integer hotelGroupId,@Param("hotelId")Integer hotelId);
	
	/**
	 * 新增
	 * @Title: insertMainInfo 
	 * @Description: TODO
	 * @param mainInfo
	 * @return: void
	 */
	void insertMessages(Messages messages);
	
	/**
	 * 修改
	 * @Title: insertMainInfo 
	 * @Description: TODO
	 * @param mainInfo
	 * @return: void
	 */
	void updateMessages(Messages messages);
	
	/**
	 * 查询首页留言列表（默认为3条）
	 * @Title: indexMessagesList 
	 * @Description: TODO
	 * @return
	 * @return: List<Messages>
	 */
	List<MessageDto> indexMessagesList(@Param("hotelGroupId")Integer hotelGroupId,@Param("hotelId")Integer hotelId);
	
	
}
