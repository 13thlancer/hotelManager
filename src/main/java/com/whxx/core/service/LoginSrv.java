package com.whxx.core.service;

import com.whxx.core.model.Manager;
import com.whxx.core.model.Menu;

import java.util.List;
import java.util.Map;

public interface LoginSrv {
    Manager getManager(String username);

    List<Map<String,Object>> getMenuList(String managerId);

    List<Menu> getAuthList(String managerId);
}
