package com.whxx.core.service;

import com.whxx.core.model.Org;
import com.whxx.core.model.ZTreeNode;
import com.whxx.core.poiEntity.OrgPoiEntity;
import com.whxx.core.poiEntity.OrgPoiExportEntity;
import com.whxx.core.tips.Tip;

import java.util.List;
import java.util.Map;

public interface OrgSrv {

    List<Map<String,Object>> listOrg(String orgName);

    String checkOrgByNum(String num);

    String getMaxCodeByPcode(String pCode);

    Org getOrgByPCode(String pCode);

    Map<String,Object> selectOrgById(String id);

    void insertOrg(Org org);

    List<ZTreeNode> orgTreeList();

    Tip updateOrg(Org org);

    Tip delOrgById(String orgId);

    Tip importExcel(List<OrgPoiEntity> orgList);

    List<ZTreeNode> orgTreeListByOrgCode(String userId);

    List<OrgPoiExportEntity> exportOrg();

    Tip upSeq(String orgId);

    Tip downSeq(String orgId);

    List<Map<String,Object>> listUserByOrg(String code);
}
