package com.whxx.hms.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.whxx.hms.model.dto.Marketing;
import com.whxx.hms.model.dto.MessageDto;
import com.whxx.hms.model.dto.PreLicenceDto;
import com.whxx.hms.model.dto.Preorder;
import com.whxx.hms.model.dto.RsvInfo;
import com.whxx.hms.model.dto.SubInfoDto;

@Repository
@SuppressWarnings("rawtypes")
public interface ReservationMapper {
	
	List<Preorder> mainOrderList(String priMainId);
	
	RsvInfo rsvInfo(String priMainId);
	
	Marketing marketing(String priMainId);
	
	Map prepayInfo(String priMainId);
	
	Map prelicence(String priMainId);
	
	Map unionpay(String priMainId);
	
	MessageDto otherInfo(String mainId);
	
	List<PreLicenceDto> prelicenceList(String priMainId);
	
	List<SubInfoDto> subOrderList(String priMainId);
	
	List<SubInfoDto> transferList(Map param);
	
	
}
