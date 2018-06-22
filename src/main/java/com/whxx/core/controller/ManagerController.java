package com.whxx.core.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.whxx.core.annotation.BussinessLog;
import com.whxx.core.common.CoreConstant;
import com.whxx.core.common.ManagerStatus;
import com.whxx.core.dict.UserDict;
import com.whxx.core.exception.BizExceptionEnum;
import com.whxx.core.exception.BussinessException;
import com.whxx.core.factory.PageFactory;
import com.whxx.core.model.Manager;
import com.whxx.core.poiEntity.ManagerPoiEntity;
import com.whxx.core.poiEntity.ManagerPoiExportEntity;
import com.whxx.core.service.ManagerSrv;
import com.whxx.core.tips.ErrorTip;
import com.whxx.core.tips.SuccessTip;
import com.whxx.core.tips.Tip;
import com.whxx.core.utils.Const;
import com.whxx.core.utils.Md5Utils;
import com.whxx.core.utils.ToolUtil;
import com.whxx.core.utils.poi.PoiUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.NoPermissionException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/mgr")
public class ManagerController extends BaseController{


	private static String PREFIX = "/system/user/";

    @Autowired
    private ManagerSrv managerSrv;

    /**
     * 跳转到查看管理员列表的页面
     */
    @RequestMapping("/user_add")
    public String addView() {
        return PREFIX + "user_add";
    }

    /**
     * 跳转到角色分配页面
     */
    //@RequiresPermissions("/mgr/role_assign")  //利用shiro自带的权限检查
    @RequestMapping("/role_assign/{userId}")
    public String roleAssign(@PathVariable String userId, Model model) {
        if (ToolUtil.isEmpty(userId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        Manager user = managerSrv.getByUserId(userId);
        model.addAttribute("userId", userId);
        model.addAttribute("userAccount", user.getAccount());
        return PREFIX + "user_roleassign";
    }

    @RequestMapping("/org_assign/{userId}")
    public String org_assign(@PathVariable String userId, Model model){
        if (ToolUtil.isEmpty(userId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        Manager user = managerSrv.getByUserId(userId);
        model.addAttribute("userId", userId);
        model.addAttribute("userAccount", user.getAccount());
        return PREFIX + "user_orgassign";
    }


    /**
     * 跳转到编辑管理员页面
     */
    @RequestMapping("/user_edit/{userId}")
    public String userEdit(@PathVariable String userId, Model model) {
//        权限判断
//        assertAuth(userId);
        Manager manager = managerSrv.getByUserId(userId);
        model.addAttribute(manager);
        model.addAttribute("roleName", managerSrv.getRoleNameById(manager.getId()));
//        LogObjectHolder.me().set(user);
        return PREFIX + "user_edit";
    }


    /**
     * 添加管理员
     */
    @BussinessLog(value = "添加管理员", key = "account", dict = UserDict.class)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "manager", value = "用户详细实体manager", required = true, dataType = "Manager")
    @ResponseBody
    public Tip add(@Valid Manager manager, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }

        // 判断账号是否重复
        Manager theManager = managerSrv.getByAccount(manager.getAccount());
        if (theManager != null) {
            throw new BussinessException(BizExceptionEnum.USER_ALREADY_REG);
        }

        // 完善账号信息
        manager.setSalt(Md5Utils.getSalt());
        manager.setAccount(manager.getPhone());
        manager.setPassword(Md5Utils.encrypt(manager.getPassword(), manager.getSalt()));
        manager.setStatus(ManagerStatus.OK.getCode());
        manager.setCreatetime(new Date());

        this.managerSrv.insertManager(manager);
        return SUCCESS_TIP;
    }


    @RequestMapping(value = "/list",  method = RequestMethod.POST)
    @ResponseBody
    public Object list(@RequestParam(required = false) String name, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime) {

        List<Map<String, Object>> users = managerSrv.selectUsers(name, beginTime, endTime);
        for(Map manager: users){
            Integer status = (Integer) manager.get("status");
            if (status == ManagerStatus.OK.getCode()){
                manager.put("statusName", ManagerStatus.OK.getMessage());
            }else if(status == ManagerStatus.FREEZED.getCode()){
                manager.put("statusName", ManagerStatus.FREEZED.getMessage());

            }
        }
        return users;
    }


    /**
     * 修改管理员
     *
     * @throws NoPermissionException
     */
    @RequestMapping(value ="/edit", method = RequestMethod.POST)
    @BussinessLog(value = "修改管理员", key = "account", dict = UserDict.class)
    @ResponseBody
    public Tip edit(@Valid Manager manager, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        managerSrv.updateManager(manager);
        return SUCCESS_TIP;
    }

    /**
     * 分配组织
     */
    @RequestMapping(value = "/setOrg", method = RequestMethod.POST)
    @ResponseBody
    public Tip setOrg(@RequestParam("userId") String userId, @RequestParam("orgIds") String orgIds) {
        if (ToolUtil.isOneEmpty(userId, orgIds)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        return  this.managerSrv.setOrg(userId, orgIds);
    }


    /**
     * 删除管理员（逻辑删除）
     */
    @BussinessLog(value = "删除管理员", key = "userId", dict = UserDict.class)
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Tip delete(@RequestParam String userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        this.managerSrv.setStatus(userId, ManagerStatus.DELETED.getCode());
        return SUCCESS_TIP;
    }


    /**
     * 冻结用户
     */
    @BussinessLog(value = "冻结用户", key = "userId", dict = UserDict.class)
    @RequestMapping(value = "/freeze", method = RequestMethod.POST)
    @ResponseBody
    public Tip freeze(@RequestParam String userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }

        this.managerSrv.setStatus(userId, ManagerStatus.FREEZED.getCode());
        return SUCCESS_TIP;
    }

    /**
     * 解除冻结用户
     */
    @BussinessLog(value = "解除冻结用户", key = "userId", dict = UserDict.class)
    @RequestMapping(value = "/unfreeze",  method = RequestMethod.POST)
    @ResponseBody
    public Tip unfreeze(@RequestParam String userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
//        assertAuth(userId);
        this.managerSrv.setStatus(userId, ManagerStatus.OK.getCode());
        return SUCCESS_TIP;
    }



    /**
     * 分配角色
     */
    @BussinessLog(value = "分配角色", key = "userId,roleIds", dict = UserDict.class)
    @RequestMapping(value = "/setRole", method = RequestMethod.POST)
    @ResponseBody
    public Tip setRole(@RequestParam("userId") String userId, @RequestParam("roleIds") String roleIds) {
        if (ToolUtil.isOneEmpty(userId, roleIds)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        //不能修改超级管理员
//        if (userId.equals(Const.ADMIN_ID)) {
//            throw new BussinessException(BizExceptionEnum.CANT_CHANGE_ADMIN);
//        }
//        assertAuth(userId);
        this.managerSrv.setRoles(userId, roleIds);
        return SUCCESS_TIP;
    }


    /**
     * 跳转到修改密码界面
     */
    @RequestMapping("/user_chpwd")
    public String chPwd() {
        return PREFIX + "user_chpwd";
    }

    /**
     * 修改当前用户的密码
     */
    @RequestMapping(value = "/changePwd", method = RequestMethod.POST)
    @ResponseBody
    public Object changePwd(@RequestParam String oldPwd, @RequestParam String newPwd, @RequestParam String rePwd) {
        if (!newPwd.equals(rePwd)) {
            throw new BussinessException(BizExceptionEnum.TWO_PWD_NOT_MATCH);
        }
        HttpSession session = getSession();
        Manager manager  = (Manager) session.getAttribute("manager");


        String oldMd5 = Md5Utils.encrypt(oldPwd,manager.getSalt());
        if (manager.getPassword().equals(oldMd5)) {

            String newSalt = Md5Utils.getSalt();
            String newMd5 = Md5Utils.encrypt(newPwd, newSalt);

            manager.setSalt(newSalt);
            manager.setPassword(newMd5);

            managerSrv.updateManagerPwd(manager);
            return SUCCESS_TIP;
        } else {
            throw new BussinessException(BizExceptionEnum.OLD_PWD_NOT_RIGHT);
        }
    }


    /**
     * 重置管理员的密码
     */
    @BussinessLog(value = "重置管理员密码", key = "userId", dict = UserDict.class)
    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    @ResponseBody
    public Tip reset(@RequestParam String userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }

        HttpSession session = getSession();
        Manager manager  = (Manager) session.getAttribute("manager");
        String newSalt = Md5Utils.getSalt();
        String newMd5 = Md5Utils.encrypt(Const.DEFAULT_PWD, newSalt);

        manager.setSalt(newSalt);
        manager.setPassword(newMd5);

        managerSrv.updateManagerPwd(manager);
        return SUCCESS_TIP;
    }

}
