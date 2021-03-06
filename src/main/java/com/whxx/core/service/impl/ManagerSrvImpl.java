package com.whxx.core.service.impl;

import com.whxx.core.common.CoreConstant;
import com.whxx.core.dao.ManagerMapper;
import com.whxx.core.dao.OrgMapper;
import com.whxx.core.model.Manager;
import com.whxx.core.model.ManagerOrg;
import com.whxx.core.model.ManagerRole;
import com.whxx.core.model.Org;
import com.whxx.core.poiEntity.ManagerPoiEntity;
import com.whxx.core.service.ManagerSrv;
import com.whxx.core.tips.ErrorTip;
import com.whxx.core.tips.SuccessTip;
import com.whxx.core.tips.Tip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ManagerSrvImpl implements ManagerSrv {

	 @Autowired
	    private ManagerMapper managerMapper;

	    @Autowired
	    private OrgMapper orgMapper;

	    @Override
	    public void insertManager(Manager manager) {
	        managerMapper.insertManager(manager);
	    }

	    @Override
	    public Manager getByAccount(String account) {
	        return managerMapper.getByAccount(account);
	    }

	    @Override
	    public List<Map<String, Object>> selectUsers(String name, String beginTime, String endTime) {
	        List<Map<String, Object>> userList =  managerMapper.selectUsers(name,beginTime,endTime);
	        for(Map manager:userList){
	            if(manager.containsKey("id")) {
	                String managerId = manager.get("id").toString();
	                List<String> roles =  managerMapper.getRoleNameById(managerId);
	                StringBuffer roleNames = new StringBuffer();
	                if(!roles.isEmpty()) {
	                    for (String roleName : roles) {
	                        roleNames.append(",");
	                        roleNames.append(roleName);
	                    }
	                    manager.put("roleName", roleNames.toString().substring(1));
	                }else{
	                    manager.put("roleName", roleNames.toString().substring(0));
	                }
	            }
	        }

	        return userList;
	    }

	    @Override
	    public Manager getByUserId(String userId) {
	        return managerMapper.getByUserId(userId);
	    }

	    @Override
	    public String getRoleNameById(String userId) {
	        List<String> roles =  managerMapper.getRoleNameById(userId);
	        StringBuffer roleNames = new StringBuffer();
	        if(!roles.isEmpty()){
	            for(String roleName:roles){
	                roleNames.append(",");
	                roleNames.append(roleName);

	            }
	            return  roleNames.toString().substring(1);
	        }
	        return  roleNames.toString().substring(0);
	    }

	    @Override
	    public void updateManager(Manager manager) {
	        managerMapper.updateManager(manager);
	    }

	    @Override
	    public void setRoles(String userId, String roleIds) {
	        // 删除该用户所有的角色
	        this.managerMapper.deleteRolesById(userId);

	        // 添加新的角色
	        for (String id : roleIds.split(",")) {
	            ManagerRole managerRole = new ManagerRole();
	            managerRole.setManagerid(userId);
	            managerRole.setRoleid(id);

	            this.managerMapper.insertManagerRole(managerRole);
	        }
	    }

	    @Override
	    public void setStatus(String userId, int code) {
	        managerMapper.updateStatus(userId,code);
	    }

	    @Override
	    public void updateManagerPwd(Manager manager) {
	        managerMapper.updateManagerPwd(manager);
	    }

	    @Override
	    public List<ManagerPoiEntity> listManager() {
	        return managerMapper.listManager();
	    }

	    @Override
	    public Tip setOrg(String userId, String codes) {
	        String[] cds = codes.split(",");
	        if(cds.length>1){
	            return new ErrorTip(CoreConstant.MANAGER_MULTI_ORG_CODE, CoreConstant.MANAGER_MULTI_ORG_MSG);
	        }
	        // 删除该用户所有的组织
	        this.managerMapper.deleteOrgById(userId);
	        // 添加新的角色
	        for (String code : codes.split(",")) {
	            Org org = this.orgMapper.getOrgByOrgCode(code);
	            ManagerOrg managerOrg = new ManagerOrg();
	            managerOrg.setManagerId(userId);
	            managerOrg.setOrgId(org.getId());
	            this.managerMapper.insertManagerOrg(managerOrg);
	        }
	        return new SuccessTip();
	    }
}
