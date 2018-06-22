package com.whxx.hms.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;


@SuppressWarnings("rawtypes")
@Repository
public interface GroupMapper {

    
    List<Map> selectGroupDropdown();
}
