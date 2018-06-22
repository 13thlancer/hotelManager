package com.whxx.core.dao;

import com.whxx.core.model.Manager;
import com.whxx.core.model.ManagerOrg;
import com.whxx.core.model.ManagerRole;
import com.whxx.core.poiEntity.ManagerPoiEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ManagerMapper {

    void insertManager(Manager manager);

    Manager getByAccount(String account);

    List<Map<String,Object>> selectUsers(@Param("name")String name, @Param("beginTime")String beginTime, @Param("endTime")String endTime);

    Manager getByUserId(@Param("id")String userId);

    List<String> getRoleNameById(@Param("id")String userId);

    void updateManager(Manager manager);

    List<String> getRoleIdById(@Param("id")String userId);

    void deleteRolesById(@Param("id")String userId);

    void insertManagerRole(ManagerRole managerRole);

    void updateStatus(@Param("id")String userId,@Param("code") int code);

    void updateManagerPwd(Manager manager);

    List<ManagerPoiEntity> listManager();

    void deleteOrgById(@Param("id")String userId);

    void insertManagerOrg(ManagerOrg managerOrg);
}