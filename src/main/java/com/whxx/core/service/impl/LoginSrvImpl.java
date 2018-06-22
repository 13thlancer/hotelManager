package com.whxx.core.service.impl;

import com.whxx.core.dao.LoginMapper;
import com.whxx.core.model.Manager;
import com.whxx.core.model.Menu;
import com.whxx.core.service.LoginSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class LoginSrvImpl implements LoginSrv {

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public Manager getManager(String username) {
        Manager manager = loginMapper.getManager(username);
        return manager;
    }

    @Override
    public List<Map<String,Object>> getMenuList(String managerId) {
        return loginMapper.getMenuList(managerId);
    }

    @Override
    public List<Menu> getAuthList(String managerId) {
        return loginMapper.getAuthList(managerId);
    }

}
