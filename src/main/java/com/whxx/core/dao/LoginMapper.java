package com.whxx.core.dao;

import com.whxx.core.model.Manager;
import com.whxx.core.model.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface LoginMapper {
    Manager getManager(@Param("account") String username);

    List<Map<String,Object>> getMenuList(@Param("id") String managerId);

    List<Menu> getAuthList(@Param("id") String managerId);
}
