/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :用户管理...
 * Author     :yangy
 * Create Date:2012-5-21
 */
package com.banger.mobile.webapp.action.user;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.banger.mobile.constants.Constants;
import com.banger.mobile.domain.Enum.user.EnumUserType;
import com.banger.mobile.domain.model.answerConfig.AnswerConfig;
import com.banger.mobile.domain.model.base.system.BaseSysTeam;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.role.SysRole;
import com.banger.mobile.domain.model.user.SysDeptAuth;
import com.banger.mobile.domain.model.user.SysRoleAuth;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.answerConfig.AnswerConfigService;
import com.banger.mobile.facade.crmRecordRemind.CrmRecordRemindService;
import com.banger.mobile.facade.crmRecordSetting.CrmRecordSettingService;
import com.banger.mobile.facade.crmRingSetting.CrmRingSettingService;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.dept.DeptService;
import com.banger.mobile.facade.finance.FeKnowledgeTypeService;
import com.banger.mobile.facade.log.OpeventLoginLogService;
import com.banger.mobile.facade.phoneConfig.PhoneConfigService;
import com.banger.mobile.facade.remindConfig.RemindConfigService;
import com.banger.mobile.facade.role.SysRoleService;
import com.banger.mobile.facade.rolemember.SysRoleMemberService;
import com.banger.mobile.facade.system.team.SysTeamService;
import com.banger.mobile.facade.user.SysDeptAuthService;
import com.banger.mobile.facade.user.SysRoleAuthService;
import com.banger.mobile.facade.user.SysUserOnlineService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.util.Md5Encrypt;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author Administrator
 * @version $Id: SysUserAction.java,v 0.1 2012-5-21 下午3:24:46 Administrator Exp $
 */
public class SysUserAction extends BaseAction {

    private static final long       serialVersionUID = -8740996598377685949L;

    private SysUserService          sysUserService;
    private SysTeamService          sysTeamService;
    private CrmRecordSettingService crmRecordSettingService;
    private SysUser                 sysUser;
    private SysRoleService          sysRoleService;                                         //角色service
    private SysRoleMemberService    sysRoleMemberService;
    private SysUserOnlineService    sysUserOnlineService;                                 //在线用户
    private CrmCustomerService      crmCustomerService;                                     //客户Service
    private PhoneConfigService      phoneConfigService;
    private RemindConfigService     remindConfigService;
    private CrmRecordRemindService  crmRecordRemindService;
    private CrmRingSettingService   crmRingSettingService;
    private FeKnowledgeTypeService  feKnowledgeTypeService;
    private AnswerConfigService     answerConfigService;
    private SysRoleAuthService      sysRoleAuthService;                                     //可管理角色
    private SysDeptAuthService      sysDeptAuthService;                                     //可管理机构
    private DeptService             deptService;                                            //机构service
    private DeptFacadeService       deptFacadeService;
    private OpeventLoginLogService  opeventLoginLogService;                                 //操作日志service
    private Map<Integer, String>    activedMap       = new LinkedHashMap<Integer, String>();
    private JSONArray               deptJson;                                               //机构树json
    private JSONArray               deptManagerJson;
    private String                  roleNames        = "";
    private List<SysRole>           sysRolelist      = new ArrayList<SysRole>();
    private List<SysRoleAuth>       sysRoleAuthList  = new ArrayList<SysRoleAuth>();
    private Map<Integer, String>    roleMap          = new HashMap<Integer, String>();


    public void setSysUserOnlineService(SysUserOnlineService sysUserOnlineService) {
        this.sysUserOnlineService = sysUserOnlineService;
    }

    public void setCrmCustomerService(CrmCustomerService crmCustomerService) {
        this.crmCustomerService = crmCustomerService;
    }

    public void setCrmRecordSettingService(CrmRecordSettingService crmRecordSettingService) {
        this.crmRecordSettingService = crmRecordSettingService;
    }

    public void setOpeventLoginLogService(OpeventLoginLogService opeventLoginLogService) {
        this.opeventLoginLogService = opeventLoginLogService;
    }

    public void setCrmRecordRemindService(CrmRecordRemindService crmRecordRemindService) {
        this.crmRecordRemindService = crmRecordRemindService;
    }

    public void setCrmRingSettingService(CrmRingSettingService crmRingSettingService) {
        this.crmRingSettingService = crmRingSettingService;
    }

    public void setDeptService(DeptService deptService) {
        this.deptService = deptService;
    }

    public List<SysRoleAuth> getSysRoleAuthList() {
        return sysRoleAuthList;
    }

    public void setSysRoleAuthList(List<SysRoleAuth> sysRoleAuthList) {
        this.sysRoleAuthList = sysRoleAuthList;
    }

    public JSONArray getDeptManagerJson() {
        return deptManagerJson;
    }

    public void setDeptManagerJson(JSONArray deptManagerJson) {
        this.deptManagerJson = deptManagerJson;
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }

    public void setSysDeptAuthService(SysDeptAuthService sysDeptAuthService) {
        this.sysDeptAuthService = sysDeptAuthService;
    }

    public void setSysRoleAuthService(SysRoleAuthService sysRoleAuthService) {
        this.sysRoleAuthService = sysRoleAuthService;
    }

    public List<SysRole> getSysRolelist() {
        return sysRolelist;
    }

    public void setSysRolelist(List<SysRole> sysRolelist) {
        this.sysRolelist = sysRolelist;
    }

    public void setPhoneConfigService(PhoneConfigService phoneConfigService) {
        this.phoneConfigService = phoneConfigService;
    }

    public void setRemindConfigService(RemindConfigService remindConfigService) {
        this.remindConfigService = remindConfigService;
    }

    public void setAnswerConfigService(AnswerConfigService answerConfigService) {
        this.answerConfigService = answerConfigService;
    }

    public String getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }

    public void setSysRoleMemberService(SysRoleMemberService sysRoleMemberService) {
        this.sysRoleMemberService = sysRoleMemberService;
    }

    public Map<Integer, String> getRoleMap() {
        return roleMap;
    }

    public void setRoleMap(Map<Integer, String> roleMap) {
        this.roleMap = roleMap;
    }

    public void setSysRoleService(SysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }

    public JSONArray getDeptJson() {
        return deptJson;
    }

    public void setDeptJson(JSONArray deptJson) {
        this.deptJson = deptJson;
    }

    public Map<Integer, String> getActivedMap() {
        return activedMap;
    }

    public void setActivedMap(Map<Integer, String> activedMap) {
        this.activedMap = activedMap;
    }

    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    /**
     * 初始化信息
     */
    public void initParameter() {
        sysRolelist = sysRoleService.getAllRoleName();
        String roleName = "";
        for (SysRole sysRole : sysRolelist) {
            if (sysRole.getRoleTypeId().equals(2))
                roleName += sysRole.getRoleName() + ",";
        }
        if (!roleName.equals(""))
            this.request.setAttribute("roleNames", roleName.substring(0, roleName.length() - 1));
        deptJson = deptService.getAllDeptJson();
        request.setAttribute("account", getLoginInfo().getAccount());
        deptManagerJson = deptFacadeService.getInChargeOfDeptJson();
        activedMap.put(1, EnumUserType.ACTIVED_ENABLED.getValue());
        activedMap.put(0, EnumUserType.ACTIVED_DISABLED.getValue());
    }

    /**
     * 用户新增
     * @return
     */
    public String toAddSysUserPage() {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("userId", this.getLoginInfo().getUserId());
        sysRoleAuthList = sysRoleAuthService.getSysRoleAuthList(parameters);
        initParameter();
        return SUCCESS;
    }

    /**
     * 用户修改
     * @return
     */
    public String toUpdateSysUserPage() {
        Integer userId = Integer.parseInt(this.request.getParameter("userId"));
        sysUser = sysUserService.getSysUserById(userId);
        request.setAttribute("deptParentName", sysUserService.getDeptName(sysUser.getDeptId()));
        SysRole sysRole = null;
        String sysStr = "";
        String adminStr = "";
        String clos = sysUserService.getRoleIds(userId);

        //request.setAttribute("isCompetent", sysUserService.getUserIsCompetent(userId));

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("userId",getLoginInfo().getUserId());
        sysRoleAuthList = sysRoleAuthService.getSysRoleAuthList(parameters);
        parameters.clear();
        parameters.put("userId", sysUser.getUserId());
        List<SysRoleAuth> sysRoleAutht = sysRoleAuthService.getSysRoleAuthList(parameters);
        for (SysRoleAuth sysRoleAuth : sysRoleAutht) {
            sysRole = new SysRole();
            sysRole.setRoleId(sysRoleAuth.getRoleId());
            sysStr += sysRoleService.getSysRoleById(sysRole).getRoleName() + ",";
            if (sysRoleAuth.getRoleId().equals(2)) {
                adminStr += sysRoleAuth.getRoleId() + ",";
            }
        }
        if (!adminStr.equals(""))
            request.setAttribute("adminStr", adminStr.substring(0, adminStr.length() - 1));

        getDeptAdminDeptJsonRemRoot(sysUser.getUserId());
        request.setAttribute("clos", clos);

        if (!sysStr.equals(""))
            request.setAttribute("userRoles", sysStr.substring(0, sysStr.length() - 1));
        initParameter();
        return SUCCESS;
    }

    /**
     * 用户详细
     *
     * @return
     */
    public String toDetailSysUserPage() {
        String userId = request.getParameter("userId");
        sysUser = sysUserService.getSysUserById(Integer.parseInt(userId));
        request.setAttribute("deptParentName", sysUserService.getDeptName(sysUser.getDeptId()));
        SysDept sysDept = null;
        StringBuilder deptAuthStr = new StringBuilder();//业务主管管理机构名
        String roleAuthStr = "";//可管理角色名
        initParameter();
        SysRole sysRole = null;
        sysRolelist.clear();
        String[] roleids = sysUserService.getRoleIds(
                Integer.parseInt(this.request.getParameter("userId"))).split(",");
        for (String string : roleids) {
            sysRole = new SysRole();
            sysRole.setRoleId(Integer.parseInt(string));
            sysRole = sysRoleService.getSysRoleById(sysRole);
            sysRolelist.add(sysRole);
        }
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("userId", sysUser.getUserId());
        sysRoleAuthList = sysRoleAuthService.getSysRoleAuthList(parameters);
        for (SysRoleAuth roleAuth : sysRoleAuthList) {
            sysRole = new SysRole();
            sysRole.setRoleId(roleAuth.getRoleId());
            sysRole = sysRoleService.getSysRoleById(sysRole);
            roleAuthStr += sysRole.getRoleName() + ",";
        }
        try {
            Map<Integer, String> map = sysUserService.getRoleNamesByUserId(userId);
            sysUser.setRoleName(map.get(Integer.parseInt(userId)));
            List<SysDeptAuth> sysDeptAuthList = sysDeptAuthService.getSysDeptAuthList(parameters);
            for (SysDeptAuth sysDeptAuth : sysDeptAuthList) {
                if (sysDeptAuth.getRoleId().equals(3)) {
                    sysDept = deptService.getDeptById(sysDeptAuth.getDeptId());
                    deptAuthStr.append(sysDept.getDeptName() + ",");
                }
            }
            if (deptAuthStr.length()>0)
                request.setAttribute("deptAuthStr", deptAuthStr.substring(0, deptAuthStr.length() - 1));
            else {
                request.setAttribute("deptAuthStr", "我的");
            }
            if (!roleAuthStr.equals(""))
                this.request.setAttribute("roleAuthStr",
                        roleAuthStr.substring(0, roleAuthStr.length() - 1));
        } catch (Exception e) {
        }
        return SUCCESS;
    }

    /**
     * 用户新增
     * @return
     */
    public String addSysUser() {
        try {
            String flag = request.getParameter("flag"); //保存或者保存并新建
            String roleIds = request.getParameter("roleids"); //角色名称集合
            String bussdept = request.getParameter("bussdept"); //业务主管管理机构集合
            String conroleids = request.getParameter("conroleids");//可管理角色名称集合
            sysUser.setDeptId(Integer.parseInt(request.getParameter("deptId")));
            String returnStr = sysUserService.addSysUser(sysUser, getLoginInfo().getUserId(),roleIds,bussdept,conroleids);
//            String returnStr = "";
            if (returnStr.equals("Pass")) {
                phoneConfigService.addPhoneConfig(sysUser.getUserId());
                AnswerConfig answerConfig = new AnswerConfig();
                answerConfig.setUserId(sysUser.getUserId());
                answerConfig.setConfigType(1);
                answerConfig.setVoiceFilePath("");
                answerConfigService.addAnswerConfig(answerConfig);
                remindConfigService.addRemindConfig(sysUser.getUserId());
                crmRecordRemindService.insertCrmRecordRemind(sysUser.getUserId());
                crmRingSettingService.insertCrmRingSetting(sysUser.getUserId());
                crmRecordSettingService.insertCrmRecordSetting(sysUser.getUserId());
                feKnowledgeTypeService.insertRootKnowBaseType(sysUser.getUserId());
                Map<String, Object> parameters = new HashMap<String, Object>();
                parameters.put("userId", getLoginInfo().getUserId());
                sysRoleAuthList = sysRoleAuthService.getSysRoleAuthList(parameters);
                opeventLoginLogService.addLog(9, EnumUserType.USER_ADD.getValue(), 1, 1);
                if (flag.equals("1")){
                    initParameter();
                    return "saveCreateUser";
                }else
                    return "saveUser";
            } else {
                return ERROR;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("getAddPage action error:" + e.getMessage());
            opeventLoginLogService.addLog(9, EnumUserType.USER_ADD.getValue(), 1, 0);
            return ERROR;
        }

    }

    /**
     * 查询当前授权用户数是否超出当前用户数
     * @return
     */
    public String checkUserCount() {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
//            PrintWriter out = response.getWriter();
//            Map<String, Object> map = authorizeService.isSystemAuthorize();
//            int count = Integer.parseInt(map.get("userCount").toString());//授权用户数
//            int userCount = sysUserService.getAllUserCount();//总用户数
//            if (userCount >= count) {
//                out.print(EnumUserType.USER_AUTHORIZE.getValue());
//            } else {
//                out.write("");
//            }
            return null;
        } catch (Exception e) {
            log.error("checkUserCount action error:" + e.getMessage());
            return null;
        }
    }

    /**
     * 判断用户编号和用户名是否存在
     */
    public String valationAOrUserCode() {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            if (sysUser.getUserCode() != null && sysUser.getAccount() != null) {
                int codeCount = sysUserService.validation("checkUserCode", sysUser);
                int accountCount = sysUserService.validation("checkAccount", sysUser);
                if (codeCount > 0 && accountCount > 0) {
                    out.print("code:" + EnumUserType.EXISTSUSERCODE.getValue() + ",account:"
                              + EnumUserType.EXISTSACCOUNT.getValue());
                    return null;
                } else if (codeCount > 0) {
                    out.print("code:" + EnumUserType.EXISTSUSERCODE.getValue());
                    return null;
                } else if (accountCount > 0) {
                    out.print("account:" + EnumUserType.EXISTSACCOUNT.getValue());
                    return null;
                }
            } else if (sysUser.getUserCode() != null) {
                int codeCount = sysUserService.validation("checkUserCode", sysUser);
                if (codeCount > 0) {
                    out.print("code:" + EnumUserType.EXISTSUSERCODE.getValue());
                    return null;
                }
            } else if (sysUser.getAccount() != null) {
                int accountCount = sysUserService.validation("checkAccount", sysUser);
                if (accountCount > 0) {
                    out.print("account:" + EnumUserType.EXISTSACCOUNT.getValue());
                    return null;
                }
            }
            out.write("");
            return null;
        } catch (Exception e) {
            log.error("valationAOrUserCode action error:" + e.getMessage());
            return null;
        }
    }

    /**
     * 修改密码
     */
    public String userPwdUpdate() {
        try {
            SysUser user = sysUserService.getSysUserById(this.getLoginInfo().getUserId());
            String opw = request.getParameter("oldpassword");
            String pw = request.getParameter("password");
            String pws = request.getParameter("passwordStr");
            PrintWriter out = this.getResponse().getWriter();
            if (!user.getPassword().equals(Md5Encrypt.md5(opw))) {
                out.write(EnumUserType.OLDPASSWORDERROR.getValue());
            } else {
                if (user.getPassword().equals(Md5Encrypt.md5(pw))) {
                    out.write(EnumUserType.OLDEQUALSPASSWORDERROR.getValue());
                } else {
                    user.setPassword(Md5Encrypt.md5(pw));
                    user.setIsPasswordReset(1);
                    user.setPasswordStr(Integer.parseInt(pws));
                    user.setUpdateUser(this.getLoginInfo().getUserId());
                    user.setLastChangePassword(new Date());//更新最后一次修改密码时间
                    sysUserService.updateSysUser(user);
                    opeventLoginLogService.addLog(9, EnumUserType.EXISTSUSERCODE.getValue(), 1, 1);
                }
            }
            out.write("");
            return null;
        } catch (Exception e) {
            log.error("userDelete action error:" + e.getMessage());
            opeventLoginLogService.addLog(9, EnumUserType.EXISTSUSERCODE.getValue(), 1, 0);
            return null;
        }
    }
    /**
     * 验证密码是否重复，旧密码是否正确
     * @return
     */
    public String checkPassWord() {
        try {
            SysUser user = sysUserService.getSysUserById(this.getLoginInfo().getUserId());
            String pw = request.getParameter("password");
            PrintWriter out = this.getResponse().getWriter();
            if (user.getPassword().equals(Md5Encrypt.md5(pw))) {
                out.write(EnumUserType.OLDEQUALSPASSWORDERROR.getValue());
                return null;
            }
            out.write("");
            return null;
        } catch (Exception e) {
            log.error("userDelete action error:" + e.getMessage());
            opeventLoginLogService.addLog(9, EnumUserType.EXISTSUSERCODE.getValue(), 1, 0);
            return null;
        }
    }

    /**
     * 验证密码是否重复，旧密码是否正确
     * @return
     */
    public String checkOldPassWord() {
        try {
            SysUser user = sysUserService.getSysUserById(this.getLoginInfo().getUserId());
            String opw = request.getParameter("oldpassword");
            PrintWriter out = this.getResponse().getWriter();
            if (!opw.equals("")) {
                if (!user.getPassword().equals(Md5Encrypt.md5(opw))) {
                    out.write(EnumUserType.OLDPASSWORDERROR.getValue());
                    return null;
                }
            }
            out.write("");
            return null;
        } catch (Exception e) {
            log.error("userDelete action error:" + e.getMessage());
            opeventLoginLogService.addLog(9, EnumUserType.EXISTSUSERCODE.getValue(), 1, 0);
            return null;
        }
    }

    /**
     * 初始化用户修改密码页面
     */
    public String userPwdUpdatePage() {
        return SUCCESS;
    }

    /**
     * 用户删除
     * @return
     */
    public String delSysUserById() {
        try {
            int userId = sysUserService.getSysUserById(
                Integer.parseInt(this.request.getParameter("userId"))).getUserId();
            SysUser user = sysUserService.getSysUserById(userId);
            user.setIsDel(1);
            user.setUpdateUser(this.getLoginInfo().getUserId());
            sysUserService.updateSysUser(user);
            crmCustomerService.cancelShareInfoByUserIds("" + userId);//删除用户共享客户
            crmCustomerService.updateCrmCustomerBybelongUserId(user.getUserId());//设置客户表的归属用户ID为0
            Map<String, Object> parameters =new HashMap<String, Object>();
            parameters.put("userId", userId);
            parameters.put("onlineStatusId", 5);//设置用户为删除
            sysUserOnlineService.updateSysUserOnlineStatus(parameters);
            
            crmRecordSettingService.deleteCrmRecordSetting(user.getUserId());
            //sysRoleMemberService.delSysRoleMember(user.getUserId());//删除用户角色集合
            opeventLoginLogService.addLog(9, EnumUserType.USER_MANAGE.getValue(), 1, 1);
            return null;
        } catch (Exception e) {
            log.error("userDelete action error:" + e.getMessage());
            opeventLoginLogService.addLog(9, EnumUserType.USER_MANAGE.getValue(), 1, 0);
            return null;
        }

    }

    /**
     * 删除用户集合
     * @return
     */
    public String delSysUserList() {
        try {
            String userIds = this.request.getParameter("userIds");
            userIds = userIds.substring(0, userIds.length());
            String[] userids = userIds.split(",");
            List<SysUser> items = new ArrayList<SysUser>();
            for (int i = 0; i < userids.length; i++) {
                items.add(sysUserService.getSysUserById(Integer.parseInt(userids[i])));
            }
            for (SysUser user : items) {
                user.setIsDel(1);
                user.setUpdateUser(getLoginInfo().getUserId());
                sysUserService.updateSysUser(user);
                crmCustomerService.updateCrmCustomerBybelongUserId(user.getUserId());//设置客户表的归属用户ID为0
                /////////////////////////////////////
                Map<String, Object> parameters =new HashMap<String, Object>();
                parameters.put("userId", user.getUserId());
                parameters.put("onlineStatusId", 5);//设置用户为删除
                sysUserOnlineService.updateSysUserOnlineStatus(parameters);
                //sysRoleMemberService.delSysRoleMember(user.getUserId());//删除用户角色集合
                ////////////////////////////////////
                crmRecordSettingService.deleteCrmRecordSetting(user.getUserId());
            }
            crmCustomerService.cancelShareInfoByUserIds("" + userIds);//删除用户共享客户
            opeventLoginLogService.addLog(9, EnumUserType.USER_DEL.getValue(), 1, 1);
        } catch (Exception e) {
            log.error("deleteSysUserList action error:" + e.getMessage());
            opeventLoginLogService.addLog(9, EnumUserType.USER_DEL.getValue(), 1, 0);
        }
        return null;
    }

    /**
     * 密码重置
     */
    public String userPwdConfirm() {
        try {
            SysUser user = sysUserService.getSysUserById(Integer.parseInt(this.request
                .getParameter("userId")));
            user.setPassword(Md5Encrypt.md5(Constants.SESSION_DEFAULTPWD));
            user.setIsPasswordReset(0);
            user.setPasswordStr(1);
            user.setLastChangePassword(new Date());
            sysUserService.updateSysUser(user);
            PrintWriter out = this.getResponse().getWriter();
            out.write(EnumUserType.PASSWORDRESETSUCCESS.getValue());
            opeventLoginLogService.addLog(9, EnumUserType.USERPASSWORDREST.getValue(), 1, 1);
            return null;
        } catch (Exception e) {
            log.error("userDelete action error:" + e.getMessage());
            opeventLoginLogService.addLog(9, EnumUserType.USERPASSWORDREST.getValue(), 1, 0);
            return null;
        }
    }

    /**
     * 用户启用、用户停用
     * @return
     */
    public String checkUserActived() {
        Integer flag = 0;
        try {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/html;charset=utf-8");
            String userId = request.getParameter("userId");
            PrintWriter out = response.getWriter();
            SysUser user = sysUserService.getSysUserById(Integer.parseInt(userId));
            flag = user.getIsActived();
            if (user.getIsActived() > 0) {//数据库当前状态
                user.setIsActived(0);
                //////////////////////////////////////////////
                Map<String, Object> parameters =new HashMap<String, Object>();
                parameters.put("userId", userId);
                parameters.put("onlineStatusId", 4);//设置用户为禁用
                sysUserOnlineService.updateSysUserOnlineStatus(parameters);
                //////////////////////////////////////////////
                opeventLoginLogService.addLog(9, EnumUserType.USER_DISABLED.getValue(), 1, 1);
            } else {
                //////////////////////////////////////////////
                Map<String, Object> parameters =new HashMap<String, Object>();
                parameters.put("userId", userId);
                parameters.put("onlineStatusId", 3);//设置用户为离线
                sysUserOnlineService.updateSysUserOnlineStatus(parameters);
                //////////////////////////////////////////////
                opeventLoginLogService.addLog(9, EnumUserType.USER_ENABLED.getValue(), 1, 1);
                user.setIsActived(1);
            }
            sysUserService.updateSysUser(user);
            out.print("");
            return null;
        } catch (Exception e) {
            log.error("userEnable action error:" + e.getMessage());
            if (flag == 1)
                opeventLoginLogService.addLog(9, EnumUserType.USER_DISABLED.getValue(), 1, 0);
            else
                opeventLoginLogService.addLog(9, EnumUserType.USER_ENABLED.getValue(), 1, 0);
            return null;
        }

    }

    /**
     * 修改用户
     * @return
     */
    public String sysUserUpdate() {
        try {
            String roleIds = request.getParameter("roleids");//角色名称集合
            String oldRoleIds=sysUserService.getRoleIds(sysUser.getUserId());//原来角色名称集合
            String bussdept = ""; //业务主管管理机构集合
            String conroleids = ""; //可管理角色名称集合
            sysUser.setDeptId(Integer.parseInt(request.getParameter("deptId")));

            sysUserService.updateSysUserAndRole(sysUser,roleIds,bussdept,getLoginInfo().getUserId(),conroleids);//更新用户信息
            if(checkRole(roleIds.split(","),oldRoleIds.split(","))){
                //////////////////////////////////////////////
                Map<String, Object> parameters =new HashMap<String, Object>();
                parameters.put("userId", sysUser.getUserId());
                parameters.put("onlineStatusId", 6);//设置用户权限变化
                sysUserOnlineService.updateSysUserOnlineStatus(parameters);
                //////////////////////////////////////////////
            }
            crmCustomerService.setCustomerOfChangeBelong(""+sysUser.getUserId(),""+sysUser.getDeptId());    //更新用户的所属客户归属机构
            opeventLoginLogService.addLog(9, EnumUserType.USER_UPDATE.getValue(), 1, 1);
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            opeventLoginLogService.addLog(9, EnumUserType.USER_UPDATE.getValue(), 1, 0);
            return ERROR;
        }

    }
    /**
     * 判断两个角色是否一致
     * @param InCharge
     * @param Charge
     * @return
     */
    public static Boolean checkRole(String[] InCharge,String[] Charge){
        for (String string : Charge) {
            if(!selectArr(InCharge,string)){
                return true;
            }
        }
        return false;
    }
    public static Boolean selectArr(String[] InCharge, String key) {
        for (int i = 0; i < InCharge.length; i++) {
            if (InCharge[i].equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 用户个人信息
     * @return
     */
    public String getUserInfo() {
        try {
            SysUser user = sysUserService.getSysUserById(getLoginInfo().getUserId());
            request.setAttribute("ACCOUNT", user.getAccount());
            request.setAttribute("USER_NAME", user.getUserName());
            request.setAttribute("USER_CODE", user.getUserCode());
            request.setAttribute("USER_ID", user.getUserId());

            request
                .setAttribute("DEPT_ID", deptService.getDeptById(user.getDeptId()).getDeptCode());
            String deptPath = deptFacadeService.getDeptFullPath(getLoginInfo().getDeptId());//取完全路径
            request.setAttribute("DEPT_NAME", deptPath);
            String ROLE_NAME = "";
            for (String str : getLoginInfo().getRoleNames()) {
                if(str!=null)
                    ROLE_NAME += str + "、";
            }
            request.setAttribute("ROLE_NAME", ROLE_NAME.substring(0, ROLE_NAME.length() - 1));
            request.setAttribute("PASSWORDSTR", user.getPasswordStr());
            BaseSysTeam bst=  sysTeamService.getSysTeamByUserId(getLoginInfo().getUserId());
            if(bst!=null){
            request.setAttribute("TEAM_NAME", bst.getTeamName());
            }
            return SUCCESS;
        } catch (Exception e) {
            log.error("showconfirmPwd action error:" + e.getMessage());
            return null;
        }
    }

    /**
     * 机构管理员   (不 添加默认根节点) json树 
     * @return
     */
    public void getDeptAdminDeptJsonRemRoot(Integer userId) {
        try {

            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("userId", userId);
            Map<String, Integer> admintree = new HashMap<String, Integer>();//机构管理员管理机构
            Map<String, Integer> businesstree = new HashMap<String, Integer>();//业务主管管理机构
            List<SysDeptAuth> sysDeptAuthList = sysDeptAuthService.getSysDeptAuthList(parameters);
            int i =0;
            for (SysDeptAuth sysDeptAuth : sysDeptAuthList) {
                if (sysDeptAuth.getRoleId().equals(2)) {
                    admintree.put("" + sysDeptAuth.getDeptId(), sysDeptAuth.getDeptId());
                }
                if (sysDeptAuth.getRoleId().equals(3)) {
                    businesstree.put("" + sysDeptAuth.getDeptId(), sysDeptAuth.getDeptId());
                }
            }
            List<SysDept> depts = deptService.getUserJsonTree();
            Set<Integer> deptNameList = new HashSet<Integer>();
            for (SysDept sysDept : depts) {
                deptNameList.add(sysDept.getDeptId());
                if(!deptNameList.contains(sysDept.getDeptParentId())) i++;//控制节点展开
            }
            
            Map<String, Object> map = new HashMap<String, Object>();
            Map<String, Object> Vmap = new HashMap<String, Object>();
            JSONArray jsonArray = new JSONArray();
            JSONArray adminArray = new JSONArray();
            if (depts.size() > 0) {
                for (SysDept dept : depts) {
                    map.put("id", dept.getDeptId());
                    Vmap.put("id", dept.getDeptId());
                    if (!deptNameList.contains(dept.getDeptParentId())) {
                        map.put("pId", 2);
                        Vmap.put("pId", 2);
                        if(i==1){
                            map.put("open", true);
                            Vmap.put("open", true);
                        }
                    } else {
                        map.put("pId", dept.getDeptParentId());
                        Vmap.put("pId", dept.getDeptParentId());
                        map.put("open", false);
                        Vmap.put("open", false);
                    }
                    map.put("name", dept.getDeptName());
                    Vmap.put("name", dept.getDeptName());
                    if (admintree.get("" + dept.getDeptId()) != null) {
                        map.put("bool", true);
                    } else {
                        map.put("bool", false);
                    }
                    if (businesstree.get("" + dept.getDeptId()) != null) {
                        Vmap.put("bool", true);
                    } else {
                        Vmap.put("bool", false);
                    }
                    adminArray.add(map);
                    jsonArray.add(Vmap);
                }
            }
            request.setAttribute("adminJsonArray", adminArray);//机构管理员 树
            request.setAttribute("businessJsonArray", jsonArray);//业务主管 树
        } catch (Exception e) {
        }
    }

	public void setFeKnowledgeTypeService(
			FeKnowledgeTypeService feKnowledgeTypeService) {
		this.feKnowledgeTypeService = feKnowledgeTypeService;
	}

	public SysTeamService getSysTeamService() {
		return sysTeamService;
	}

	public void setSysTeamService(SysTeamService sysTeamService) {
		this.sysTeamService = sysTeamService;
	}
	
}
