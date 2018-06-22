package com.whxx.core.dao;

import com.whxx.core.model.Role;
import com.whxx.core.model.RoleMenu;
import com.whxx.core.model.ZTreeNode;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RoleMapper {

    void insertRole(Role role);

    Role getById(@Param("id") String roleId);

    String getPname(@Param("pnum") Integer pnum);

    List<ZTreeNode> roleTreeList();

    List<Map<String,Object>> selectRoles(@Param("condition") String roleName);

    String checkNum(@Param("num") Integer num);

    void updateById(Role role);

    void deleteById(@Param("id") String roleId);

    void deleteRolesById(@Param("id") String roleId);

    void insertRoleMenu(RoleMenu roleMenu);

    List<ZTreeNode> roleTreeListByRoleId(String[] strArray);

    Role getByRoleNum(@Param("rolenum") Integer rolenum);

    void deleteRolesByrolenum(@Param("num") Integer rolenum);

    Role getByRoleId(@Param("id") String id);
}