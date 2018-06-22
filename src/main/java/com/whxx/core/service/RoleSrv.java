package com.whxx.core.service;

import com.whxx.core.model.Role;
import com.whxx.core.model.ZTreeNode;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RoleSrv {
    void insertRole(Role role);

    Role selectById(String roleId);

    String getPname(Integer pnum);

    List<ZTreeNode> roleTreeList();

    List<Map<String,Object>> selectRoles(@Param("condition") String roleName);

    String checkNum(Integer num);

    void updateById(Role role);

    void delRoleById(String roleId);

    void setAuthority(String roleId, String ids);

    List<ZTreeNode> roleTreeListByRoleId(String userId);

    Role selectByRoleNum(Integer rolenum);

    Role selectByRoleId(String id);
}
