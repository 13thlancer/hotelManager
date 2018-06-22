package com.whxx.hms.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.whxx.hms.model.Tbpp01a;


@Repository
public interface Pp01aMapper {

	Tbpp01a getTbpp01a(@Param("hotelId") long hotelId,@Param("hotelGroupId") long hotelGroupId,@Param("desc9") String desc9);

}
