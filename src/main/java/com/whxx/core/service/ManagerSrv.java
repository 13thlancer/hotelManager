package com.whxx.core.service;

import com.whxx.core.model.Manager;
import com.whxx.core.poiEntity.ManagerPoiEntity;
import com.whxx.core.tips.Tip;

import java.util.List;
import java.util.Map;

public interface ManagerSrv {
	void insertManager(Manager manager);

    Manager getByAccount(String account);

    List<Map<String,Object>> selectUsers(String name, String beginTime, String endTime);

    Manager getByUserId(String userId);

    String getRoleNameById(String id);

    void updateManager(Manager manager);

    void setRoles(String userId, String roleIds);

    void setStatus(String userId, int code);

    void updateManagerPwd(Manager manager);

    List<ManagerPoiEntity> listManager();

    Tip setOrg(String userId, String orgIds);
}
