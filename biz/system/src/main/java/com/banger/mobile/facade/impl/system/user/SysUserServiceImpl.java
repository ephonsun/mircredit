/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :用户管理业务实现类
 * Author     :yangy
 * Create Date:2012-5-17
 */
package com.banger.mobile.facade.impl.system.user;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.constants.Constants;
import com.banger.mobile.dao.dept.DeptDao;
import com.banger.mobile.dao.role.SysRoleDao;
import com.banger.mobile.dao.user.SysRoleMemberDao;
import com.banger.mobile.dao.user.SysUserDao;
import com.banger.mobile.domain.Enum.user.EnumUserStatus;
import com.banger.mobile.domain.Enum.user.EnumUserType;
import com.banger.mobile.domain.model.answerConfig.AnswerConfig;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.role.SysRole;
import com.banger.mobile.domain.model.user.*;
import com.banger.mobile.facade.answerConfig.AnswerConfigService;
import com.banger.mobile.facade.crmRecordRemind.CrmRecordRemindService;
import com.banger.mobile.facade.crmRecordSetting.CrmRecordSettingService;
import com.banger.mobile.facade.crmRingSetting.CrmRingSettingService;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.dept.DeptService;
import com.banger.mobile.facade.finance.FeKnowledgeTypeService;
import com.banger.mobile.facade.funcAuth.FuncAuthService;
import com.banger.mobile.facade.loan.LnLoanService;
import com.banger.mobile.facade.log.OpeventLoginLogService;
import com.banger.mobile.facade.param.SysParamService;
import com.banger.mobile.facade.phoneConfig.PhoneConfigService;
import com.banger.mobile.facade.remindConfig.RemindConfigService;
import com.banger.mobile.facade.role.SysRoleService;
import com.banger.mobile.facade.rolemember.SysRoleMemberService;
import com.banger.mobile.facade.user.SysDeptAuthService;
import com.banger.mobile.facade.user.SysRoleAuthService;
import com.banger.mobile.facade.user.SysUserOnlineService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.util.Md5Encrypt;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.SQLException;
import java.util.*;

/**
 * @author yangy
 * @version $Id: SysUserServiceImpl.java,v 0.1 2012-5-17 上午11:50:55 Administrator Exp $
 */
public class SysUserServiceImpl implements SysUserService {

    protected final Log log = LogFactory.getLog(getClass());

    public static final String Pass = "Pass";
    private CrmRecordSettingService crmRecordSettingService;
    private PhoneConfigService phoneConfigService;
    private RemindConfigService remindConfigService;
    private CrmRecordRemindService crmRecordRemindService;
    private CrmRingSettingService crmRingSettingService;
    private AnswerConfigService answerConfigService;
    private SysRoleService sysRoleService;                      //角色service
    private SysRoleAuthService sysRoleAuthService;                  //可管理角色
    private SysDeptAuthService sysDeptAuthService;    //可管理机构
    private SysRoleMemberService sysRoleMemberService;                //用户角色
    private SysUserOnlineService sysUserOnlineService;                //在线用户
    private FuncAuthService funcAuthService;
    private DeptService deptService;                                            //机构service
    private SysUserDao sysUserDao;                          //用户DAO
    private SysRoleDao sysRoleDao;                          //角色DAO
    private DeptDao deptDao;                             //部门DAO
    private SysParamService sysParamService;
    private Map<String, SysUser> userCache;
    private FeKnowledgeTypeService  feKnowledgeTypeService;
    private OpeventLoginLogService opeventLoginLogService;
    private DeptFacadeService deptFacadeService;
    private LnLoanService lnLoanService;
    
    public void setLnLoanService(LnLoanService lnLoanService) {
		this.lnLoanService = lnLoanService;
	}

	public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }

    public CrmRecordSettingService getCrmRecordSettingService() {
        return crmRecordSettingService;
    }

    public void setCrmRecordSettingService(CrmRecordSettingService crmRecordSettingService) {
        this.crmRecordSettingService = crmRecordSettingService;
    }

    public void setOpeventLoginLogService(OpeventLoginLogService opeventLoginLogService) {
        this.opeventLoginLogService = opeventLoginLogService;
    }

    public void setFeKnowledgeTypeService(
            FeKnowledgeTypeService feKnowledgeTypeService) {
        this.feKnowledgeTypeService = feKnowledgeTypeService;
    }

    public void setDeptService(DeptService deptService) {
        this.deptService = deptService;
    }

    public void setPhoneConfigService(PhoneConfigService phoneConfigService) {
        this.phoneConfigService = phoneConfigService;
    }

    public void setRemindConfigService(RemindConfigService remindConfigService) {
        this.remindConfigService = remindConfigService;
    }

    public void setCrmRecordRemindService(CrmRecordRemindService crmRecordRemindService) {
        this.crmRecordRemindService = crmRecordRemindService;
    }

    public void setCrmRingSettingService(CrmRingSettingService crmRingSettingService) {
        this.crmRingSettingService = crmRingSettingService;
    }

    public void setAnswerConfigService(AnswerConfigService answerConfigService) {
        this.answerConfigService = answerConfigService;
    }

    public void setSysUserOnlineService(SysUserOnlineService sysUserOnlineService) {
        this.sysUserOnlineService = sysUserOnlineService;
    }

    public Map<String, SysUser> getUserCache() {
        return userCache;
    }

    public void setUserCache(Map<String, SysUser> userCache) {
        this.userCache = userCache;
    }

    public void setSysRoleService(SysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }

    public void setSysRoleAuthService(SysRoleAuthService sysRoleAuthService) {
        this.sysRoleAuthService = sysRoleAuthService;
    }

    public void setSysDeptAuthService(SysDeptAuthService sysDeptAuthService) {
        this.sysDeptAuthService = sysDeptAuthService;
    }

    public void setSysRoleMemberService(SysRoleMemberService sysRoleMemberService) {
        this.sysRoleMemberService = sysRoleMemberService;
    }

    public void setSysParamService(SysParamService sysParamService) {
        this.sysParamService = sysParamService;
    }

    private SysRoleMemberDao sysRoleMemberDao;

    public void setSysRoleDao(SysRoleDao sysRoleDao) {
        this.sysRoleDao = sysRoleDao;
    }

    public void setDeptDao(DeptDao deptDao) {
        this.deptDao = deptDao;
    }

    public SysRoleMemberDao getSysRoleMemberDao() {
        return sysRoleMemberDao;
    }

    public void setSysRoleMemberDao(SysRoleMemberDao sysRoleMemberDao) {
        this.sysRoleMemberDao = sysRoleMemberDao;
    }

    public void setSysUserDao(SysUserDao sysUserDao) {
        this.sysUserDao = sysUserDao;
    }

    public void setFuncAuthService(FuncAuthService funcAuthService) {
        this.funcAuthService = funcAuthService;
    }

    /**
     * 用户新增
     *
     * @param sysUser      用户对象
     * @param createUserId 创建用户
     * @param roleIds      角色集合
     * @param bussdept     业务主管管理机构集合
     * @param conroleids   可管理的角色
     */
    public String addSysUser(SysUser sysUser, Integer createUserId, String roleIds, String bussdept,String conroleids) {
        sysUser.setPassword(Md5Encrypt.md5(Constants.SESSION_DEFAULTPWD));
        SysRoleMember srm = null;
        SysRole sysRole = null;
        sysUser.setAccount(sysUser.getAccount().trim());//清空格
        sysUser.setIsDel(0);
        sysUser.setIsPasswordReset(0);
        sysUser.setPasswordStr(1);
        sysUser.setUpdateUser(createUserId);
        sysUser.setCreateUser(createUserId);
        sysUser.setUserCode(sysUser.getUserCode().trim());
        sysUser.setUserName(sysUser.getUserName().trim());
        sysUser.setJgm("");
        sysUser.setOperateCode("");
        sysUserDao.addSysUser(sysUser);
        //-----------------------------------
        srm = new SysRoleMember();
        sysRole = new SysRole();
        sysRole.setRoleId(Integer.parseInt(roleIds));
        srm.setRoleId(Integer.parseInt(roleIds));                
        srm.setUserId(sysUser.getUserId());
        srm.setCreateUser(createUserId);
        srm.setUpdateUser(createUserId);
        sysRoleMemberService.addSysRoleMember(srm);
        /*
        if (roleIds!=null&&!roleIds.equals("")) {
            String[] ris = roleIds.split(",");
            for (String str : ris) { //多角色保存
                srm = new SysRoleMember();
                sysRole = new SysRole();
                sysRole.setRoleId(Integer.parseInt(roleIds));
                srm.setRoleId(Integer.parseInt(roleIds));                
                srm.setUserId(sysUser.getUserId());
                srm.setCreateUser(createUserId);
                srm.setUpdateUser(createUserId);
                sysRoleMemberService.addSysRoleMember(srm);
            }            
        }
        */
        return "Pass";
    }

    /**
     * 数据导入接口
     *
     * @param account  用户名
     * @param userName 用户姓名
     * @param deptCode 机构编号
     */
    public void initAddSysUser(String account, String userName, String deptCode,Integer createUserId) {
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("deptCode",deptCode);
        SysDept dept=deptService.getDeptByCode(map);//查询出机构编号对应的部门实体
        SysUser user=getUserByAccount(account);
        if (user == null) {
            /////////////// 用户实体构建////////////
            SysUser sysUser = new SysUser();
            sysUser.setDeptId(dept.getDeptId());
            sysUser.setIsDel(0);
            sysUser.setAccount(account);
            sysUser.setUserName(userName);
            sysUser.setUserCode(account);
            sysUser.setIsPasswordReset(0);
            sysUser.setPasswordStr(1);
            sysUser.setUpdateUser(createUserId);
            sysUser.setCreateUser(createUserId);
            sysUser.setIsActived(1);
            sysUser.setRemark("");
            sysUser.setPhone("");
            sysUser.setPhoneExt("");
            sysUser.setLastChangePassword(new Date());
            sysUser.setLastLoginDate(new Date());
            sysUser.setPassword(Md5Encrypt.md5(Constants.SESSION_DEFAULTPWD));
            sysUserDao.addSysUser(sysUser);
            //////////////////END////////////////////

            /////////////////用户角色////////////////
            SysRoleMember srm = new SysRoleMember();
            srm.setRoleId(4);
            srm.setUserId(sysUser.getUserId());
            srm.setCreateUser(createUserId);
            srm.setUpdateUser(createUserId);
            sysRoleMemberService.addSysRoleMember(srm);
            /////////////////END///////////////////////
            /////////////////参数初始化/////////////////
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
//            opeventLoginLogService.addLog(9, EnumUserType.USER_ADD.getValue(), 1, 1);
        }else{
            user.setUpdateUser(createUserId);
            user.setUserName(userName);
            user.setDeptId(dept.getDeptId());
            user.setUpdateDate(new Date());
            sysUserDao.updateSysUser(user);
        }
    }

    /**
     * 用户删除
     *
     * @param id
     * @see com.banger.mobile.facade.user.SysUserService#deleteSysUser(int)
     */
    public void deleteSysUser(int id) {
        sysUserDao.deleteSysUser(id);
    }

    /**
     * 用户更新
     *
     * @param sysUser
     * @see com.banger.mobile.facade.user.SysUserService#updateSysUser(com.banger.mobile.domain.model.user.SysUser)
     */
    public void updateSysUser(SysUser sysUser) {
        sysUserDao.updateSysUser(sysUser);
    }

    public SysUser getSysUserById(int id) {
        return sysUserDao.getSysUserById(id);
    }

    public PageUtil<SysUserBean> getSysUserPage(Map<String, Object> parameters, Page page) {
        return sysUserDao.getSysUserPage(parameters,page);
    }

    /**
     * 根据用户名取用户
     *
     * @param account
     * @return
     * @see com.banger.mobile.facade.user.SysUserService#getUserByAccount(java.lang.String)
     */
    public SysUser getUserByAccount(String account) {
        try {
            List<SysUser> ls = sysUserDao.getSysUserList("getAccount", account);
            if (ls.size() > 0) {
                SysUser u = ls.get(0);
                return u;
            }
            return null;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public SysUser getAllUserByAccount(String account) {
        return sysUserDao.getAllUserByAccount(account);
    }

    public List<SysUser> getAllUserByAccountMap(Map<String,Object> map){
        return  sysUserDao.getAllUserByAccountMap(map);
    }

    public List<SysUser> getAllUserByAccountAndName(Map<String,Object> map){
        return  sysUserDao.getAllUserByAccountMap(map);
    }

    /**
     * 用户密码修改
     *
     * @param userId
     * @param password
     * @see com.banger.mobile.facade.user.SysUserService#pwdConfirm(java.lang.Integer, java.lang.String)
     */
    public void pwdConfirm(Integer userId, String password) {
        SysUser user = sysUserDao.getSysUserById(userId);
        user.setPassword(Md5Encrypt.md5(password));
        user.setIsPasswordReset(1);//初始化之后将是否重置密码设置为1
        sysUserDao.updateSysUser(user);
    }

    /**
     * 输入部门编号取部门名称
     *
     * @param deptid
     * @return
     */
    public String getDeptName(Integer deptid) {
        try {
            return deptDao.getDeptById(deptid).getDeptName();
        } catch (Exception e) {
            return "";
        }

    }

    /**
     * 输入角色ID取角色名称
     *
     * @param roleid
     * @return
     */
    public String getRoleName(Integer roleid) {
        try {
            SysRole item = new SysRole();
            item.setRoleId(roleid);
            return sysRoleDao.getSysRoleById(item).getRoleName();
        } catch (Exception e) {
            return "";
        }

    }

    /**
     * 根据用户ID得到角色集字符串
     *
     * @param userid
     * @return
     * @see com.banger.mobile.service(java.lang.Integer)
     */
    public String getRoleIds(Integer userid) {
        try {
            HashMap<String, Object> pc = new HashMap<String, Object>();
            pc.put("USER_ID", userid);
            List<SysRoleMember> lsr = sysRoleMemberDao.getSysRoleMemberList("GetRoleMember", pc);
            if (lsr.size() > 0) {
                String str = "";
                for (Object obj : lsr) {
                    SysRoleMember role = (SysRoleMember) obj;
                    str += role.getRoleId() + ",";
                }
                return str.substring(0, str.length() - 1);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 用户登录
     *
     * @param name
     * @param password
     * @return
     * @see com.banger.mobile.service(java.lang.String, java.lang.String)
     */
    public String login(String name, String password) {
        HttpServletRequest request = org.apache.struts2.ServletActionContext.getRequest();
        String sessionId = request.getSession().getId();
        HashMap<String, Object> pc = new HashMap<String, Object>();
        pc.put("ACCOUNT", name.trim());
        List<SysUser> ls = sysUserDao.getSysUserList("getAccount", name.trim());
        if (ls.size() > 0) {
            SysUser user = (SysUser) ls.get(0);
            if (user.getPassword().equals(Md5Encrypt.md5(password))) {
                UserInfo info = new UserInfo();
                info.setUserId(user.getUserId());
                info.setAccount(user.getAccount());
                info.setDeptId(user.getDeptId());
                info.setUserName(user.getUserName());
                info.setStatus(EnumUserStatus.ENABLED);
                info.setSessionId(sessionId);
                try {
                    info.setDeptname(deptDao.getDeptById(user.getDeptId()).getDeptName());
                    info.setIp(request.getRemoteAddr());
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
                pc.put("USER_ID", user.getUserId());

                List<SysRoleMember> lsr = sysRoleMemberDao
                        .getSysRoleMemberList("GetRoleMember", pc);
                if (lsr.size() > 0) {
                    String[] roles = new String[lsr.size()];
                    String[] roleNames = new String[lsr.size()];
                    int index = 0;
                    for (Object obj : lsr) {
                        SysRoleMember role = (SysRoleMember) obj;
                        roles[index] = role.getRoleId().toString();
                        SysRole item = new SysRole();
                        item.setRoleId(role.getRoleId());
              //          if (role.getRoleId() != 3 && role.getRoleId() != 4) {
                            roleNames[index] = sysRoleDao.getSysRoleById(item).getRoleName();
                            if (roleNames[index].equals(2))//如果是机构系统管理员
                                info.setBusinessExecutives(true);
              //          }
                        index++;
                    }
                    info.setRoles(roles);
                    info.setRoleNames(roleNames);
                }
                if (user.getIsActived() == 0) {
                    return EnumUserType.ACCOUNT.getValue() + name
                            + EnumUserType.DISABLEDLOGIN.getValue();// "用户名："+name+"，已被禁止登录，请联系系统管理员。";
                } else {
                    request.getSession().setAttribute(Constants.SESSION_LOGININFO, info); //把登录对象放入session中
                    user.setLastLoginDate(new Date());//更新最后登陆时间
                    sysUserDao.updateSysUser(user);

                    Map<String, Object> parameters = new HashMap<String, Object>();
                    parameters.put("userId", getUserLoginInfo().getUserId());
                    List<SysUserOnline> sysUserOnlineList = sysUserOnlineService
                            .getALLSysUserOnline(parameters);
                    if (sysUserOnlineList.size() > 0) {
                        for (SysUserOnline sysUserOnline : sysUserOnlineList) {
                            sysUserOnline.setLoginDate(new Date());
                            sysUserOnline.setOnlineStatusId(1);
                            sysUserOnline.setUserSessionId(getUserLoginInfo().getSessionId());
                            sysUserOnlineService.updateSysUserOnline(sysUserOnline);
                        }
                    } else {
                        SysUserOnline sysUserOnline = new SysUserOnline();
                        sysUserOnline.setOnlineStatusId(1);
                        sysUserOnline.setLoginDate(new Date());
                        sysUserOnline.setUserId(getUserLoginInfo().getUserId());
                        sysUserOnline.setUserSessionId(getUserLoginInfo().getSessionId());
                        sysUserOnline.setUpdateUser(getUserLoginInfo().getUserId());
                        sysUserOnline.setCreateUser(getUserLoginInfo().getUserId());
                        sysUserOnlineService.addSysUserOnline(sysUserOnline);//在线用户设置
                    }
                    //超过三个月没有修改密码
                    if(user.getLastChangePassword() != null){
                        Calendar cal = Calendar.getInstance();
                        Date dt = new Date();
                        cal.setTime(user.getLastChangePassword());
                        cal.add(Calendar.MONTH, 3);
                        if (dt.after(cal.getTime())) {
                            return "timeoutpasswordreset";
                        }
                    }

                    if (user.getIsPasswordReset() == 0)
                        return "passwordreset";
                    else
                        return Pass;
                }
            } else {
                return EnumUserType.ACCOUNT.getValue() + name
                        + EnumUserType.PASSWORDNOTTURE.getValue();//"用户名："+name+"，密码不正确，请输入正确密码。";
            }

        } else {
            return EnumUserType.ACCOUNT.getValue() + name + EnumUserType.EXISTS.getValue();//"用户名："+name+"，不存在，请重新输入。";
        }
    }
    
    
    @Override
	public SysUser checkNameAndPasswordIsValid(String name, String password) {
    	 HashMap<String, Object> pc = new HashMap<String, Object>();
         pc.put("ACCOUNT", name.trim());
         List<SysUser> ls = sysUserDao.getSysUserList("getAccount", name.trim());
         if (ls.size() > 0) {
             SysUser user = (SysUser) ls.get(0);
             if (user.getPassword().equals(Md5Encrypt.md5(password))) {
            	 return user;
             }
         }
         return null;
    }

	/**
     * PAD用户登录
     *
     * @param account
     * @param password
     * @param ip
     * @return
     */
    public String login(String account, String password, String ip) {
        List<SysUser> ls = sysUserDao.getSysUserList("getAccount", account);
        if (ls.size() > 0) {
            SysUser user = (SysUser) ls.get(0);
            //opeventLoginLogService.addLog(user.getUserId(),8, EnumUserType.USER_LOGIN.getValue(), ip, 1, 1, "");//日志
            if (user.getPassword().equals(Md5Encrypt.md5(password))) {
                if (user.getIsActived() == 0) {
                    return EnumUserType.ACCOUNT.getValue() + account
                            + EnumUserType.DISABLEDLOGIN.getValue();//"用户名："+name+"，已被禁止登录，请联系系统管理员。!";
                } else {
                    return Pass;

                }
            } else {
                return EnumUserType.ACCOUNT.getValue() + account
                        + EnumUserType.PASSWORDNOTTURE.getValue();//"用户名："+name+"，密码不正确，请输入正确密码";
            }
        } else {
            return EnumUserType.ACCOUNT.getValue() + account + EnumUserType.EXISTS.getValue();//"用户名："+name+"，不存在，请重新输入";
        }
    }

    /**
     * 电话来电时自动登录
     *
     * @param account
     * @return
     */
    public String autoLogin(String account) {
        HttpServletRequest request = org.apache.struts2.ServletActionContext.getRequest();
        SysUser user = this.getUserByAccount(account);
        HashMap<String, Object> pc = new HashMap<String, Object>();

        if (user.getIsDel() == 0) {
            if (user.getIsActived() == 0) {
                return EnumUserType.ACCOUNT.getValue() + user.getAccount()
                        + EnumUserType.DISABLEDLOGIN.getValue();//"用户名："+name+"，已被禁止登录，请联系系统管理员。!";
            } else {
                UserInfo info = new UserInfo();
                info.setUserId(user.getUserId());
                info.setAccount(user.getAccount());
                info.setDeptId(user.getDeptId());
                info.setUserName(user.getUserName());
                try {
                    info.setDeptname(deptDao.getDeptById(user.getDeptId()).getDeptName());
                    info.setIp(request.getRemoteAddr());
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
                pc.put("USER_ID", user.getUserId());

                List<SysRoleMember> lsr = sysRoleMemberDao
                        .getSysRoleMemberList("GetRoleMember", pc);
                if (lsr.size() > 0) {
                    String[] roles = new String[lsr.size()];
                    String[] roleNames = new String[lsr.size()];
                    int index = 0;
                    for (Object obj : lsr) {
                        SysRoleMember role = (SysRoleMember) obj;
                        roles[index] = role.getRoleId().toString();
                        SysRole item = new SysRole();
                        item.setRoleId(role.getRoleId());
                        roleNames[index] = sysRoleDao.getSysRoleById(item).getRoleName();
                        index++;
                    }
                    info.setRoles(roles);
                    info.setRoleNames(roleNames);

                    List<String> funCodes = funcAuthService.getFuncUrlByRoleIds(roles);
                    info.setFuncCodes(funCodes);
                }

                if (user.getIsActived() == 0) {
                    return EnumUserType.ACCOUNT.getValue() + account
                            + EnumUserType.DISABLEDLOGIN.getValue();// "用户名："+name+"，已被禁止登录，请联系系统管理员。";
                } else {
                    String excludeActions = sysParamService.getExcludeActions();
                    info.setExcludeActions(excludeActions);

                    request.getSession().setAttribute(Constants.SESSION_LOGININFO, info);
                    return Pass;
                }
            }
        } else {
            return EnumUserType.ACCOUNT.getValue() + user.getAccount()
                    + EnumUserType.USERDELETE.getValue();
        }

    }

    /**
     * IPAD用户退出
     *
     * @param account 用户名
     * @param ip      电脑IP
     * @return
     */
    public void logout(String account, String ip) {
        this.getUserByAccount(account);
    }

    /**
     * 根据部门ID取下属用户集合
     *
     * @return
     */
    public List<SysUserBean> getDeptBelongUserList(String userIds) {
        return sysUserDao.getDeptBelongUserList("getDeptBelongUser", userIds);
    }

    /**
     * 判断用户编号和用户名是否存在
     */
    public Integer validation(String sqlId, SysUser sysUser) {
        HashMap<String, Object> pc = new HashMap<String, Object>();
        pc.put("ACCOUNT", sysUser.getAccount());
        pc.put("USER_CODE", sysUser.getUserCode());
        if (sysUser.getUserId() != null)
            pc.put("userId", sysUser.getUserId());
        return this.sysUserDao.getQueryCount(sqlId, pc);
    }

    /**
     * 获得登录信息
     *
     * @return
     */
    private IUserInfo getUserLoginInfo() {
        HttpServletRequest req = org.apache.struts2.ServletActionContext.getRequest();
        HttpSession session = req.getSession();
        return (IUserInfo) session.getAttribute("LoginInfo");
    }

    /**
     * @param map
     * @return
     * @see com.banger.mobile.facade.user.SysUserService(java.lang.String)
     */
    public List<SysUserBean> getSuperiorUserList(Map<String, Object> map) {
        try {
            SysUser sysUser = sysUserDao.getSysUserById(Integer.parseInt("" + map.get("userId")));
            if (sysUser != null) {
                map.put("deptParentId", deptDao.getDeptById(sysUser.getDeptId()).getDeptParentId());
                map.put("deptId", sysUser.getDeptId());
                List<SysUserBean> rust = sysUserDao.getSuperiorUserList(map);
                List<SysUserBean> syslist = new ArrayList<SysUserBean>();
                for (SysUserBean sys : rust) {
                    if (!sys.getUserId().equals("" + map.get("userId")))
                        syslist.add(sys);
                }
                return syslist;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param map
     * @return
     * @see com.banger.mobile.facade.user.SysUserService#getDeptBelongUserTaskList(java.util.Map)
     */
    public List<SysUser> getDeptBelongUserTaskList(Map<String, Object> map) {
        return sysUserDao.getDeptBelongUserTaskList(map);
    }

    /**
     * 查询所有的用户数
     */
    public Integer getAllUserCount() {
        return sysUserDao.getAllData().size();
    }

    /**
     * @param map
     * @return
     * @see com.banger.mobile.facade.user.SysUserService#getDeptUserList(java.util.Map)
     */
    public List<SysUser> getDeptUserList(Map<String, Object> map) {
        return sysUserDao.getDeptUserList(map);
    }

    /**
     * @param ids
     * @return
     * @see com.banger.mobile.facade.user.SysUserService#getUserListByIds(java.lang.String)
     */
    public List<SysUserBean> getUserListByIds(String ids) {
        return sysUserDao.getUserListByIds(ids);
    }

    /**
     * @return
     * @see com.banger.mobile.facade.user.SysUserService
     */
    public boolean getUserIsCompetent(Integer userId) {
        HashMap<String, Object> pc = new HashMap<String, Object>();
        pc.put("USER_ID", userId);
        List<SysRoleMember> lsr = sysRoleMemberDao.getSysRoleMemberList("GetRoleMember", pc);
        boolean flag = false;
        if (lsr.size() > 0) {
            for (SysRoleMember sysRoleMember : lsr) {
                if (sysRoleMember.getRoleId().equals(2)) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    /**
     * 修改用户信息
     * @param sysUser 用户信息
     * @param roleIds   可管理角色
     * @param bussdept    可管理机构
     * @param userId     用户ID
     */
    public void updateSysUserAndRole(SysUser sysUser,String roleIds, String bussdept,Integer userId,String conroleids) {
        //SysDeptAuth sysDeptAuth = null;
        sysUser.setUpdateUser(userId);
        sysUser.setUserCode(sysUser.getUserCode().trim());
        sysUser.setUserName(sysUser.getUserName().trim());
        sysUserDao.updateSysUser(sysUser);
        sysRoleMemberService.sysRoleMemberUpdateRole(sysUser.getUserId(), roleIds,userId);
        /*
        if (bussdept != null && !bussdept.equals("")) {
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("userId", sysUser.getUserId());
            parameters.put("roleId", 3);
            sysDeptAuthService.deleteSysDeptAuth(parameters);
            String[] bussStr = bussdept.split(",");
            for (String string2 : bussStr) {
                sysDeptAuth = new SysDeptAuth();
                sysDeptAuth.setRoleId(3);
                sysDeptAuth.setDeptId(Integer.parseInt(string2));
                sysDeptAuth.setUserId(sysUser.getUserId());
                sysDeptAuth.setCreateUser(getUserLoginInfo().getUserId());
                sysDeptAuth.setUpdateUser(getUserLoginInfo().getUserId());
                sysDeptAuthService.addSysDeptAuth(sysDeptAuth);
            }
        } else {
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("userId", sysUser.getUserId());
            parameters.put("roleId", 3);
            sysDeptAuthService.deleteSysDeptAuth(parameters);
        }
        if (bussdept != null && !bussdept.equals("")) {
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("userId", sysUser.getUserId());
            parameters.put("roleId", 2);
            sysDeptAuthService.deleteSysDeptAuth(parameters);
            String[] bussStr = bussdept.split(",");
            for (String string2 : bussStr) {
                sysDeptAuth = new SysDeptAuth();
                sysDeptAuth.setRoleId(2);
                sysDeptAuth.setDeptId(Integer.parseInt(string2));
                sysDeptAuth.setUserId(sysUser.getUserId());
                sysDeptAuth.setCreateUser(getUserLoginInfo().getUserId());
                sysDeptAuth.setUpdateUser(getUserLoginInfo().getUserId());
                sysDeptAuthService.addSysDeptAuth(sysDeptAuth);
            }
        } else {
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("userId", sysUser.getUserId());
            parameters.put("roleId", 2);
            sysDeptAuthService.deleteSysDeptAuth(parameters);
        }
         

        String[] roleId = roleIds.split(",");
        if (getUserLoginInfo().getAccount().equals("admin")) {
            if (!selectArr(roleId, "2")) {
                sysRoleAuthService.deleteSysRoleAuth(sysUser.getUserId());
                Map<String, Object> parameters = new HashMap<String, Object>();
                parameters.put("userId", sysUser.getUserId());
                parameters.put("roleId", 2);
                sysDeptAuthService.deleteSysDeptAuth(parameters);
            }
        } else {
            if (!selectArr(roleId, "3")) {
                Map<String, Object> parameters = new HashMap<String, Object>();
                parameters.put("userId", sysUser.getUserId());
                parameters.put("roleId", 3);
                sysDeptAuthService.deleteSysDeptAuth(parameters);
            }
        }
        */
        sysRoleAuthService.deleteSysRoleAuth(sysUser.getUserId());
    }

    /**
     * 匹配字符
     *
     * @param InCharge
     * @param key
     * @return
     */
    public static Boolean selectArr(String[] InCharge, String key) {
        for (int i = 0; i < InCharge.length; i++) {
            if (InCharge[i].equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据用户集合查角色名
     */
    public Map<Integer, String> getRoleNamesByUserId(String userIds) {
        Map<Integer, String> map = new HashMap<Integer, String>();
        List<UserRoleName> list = sysUserDao.getRoleNamesByUserIds(userIds);
        if (list != null && list.size() > 0) {
            for (UserRoleName u : list) {
                //if (u.getRoleId() != 3 && u.getRoleId() != 4) {
                    if (map.containsKey(u.getUserId())) {
                        map.put(u.getUserId(), map.get(u.getUserId()) + "," + u.getRoleName());
                    } else {
                        map.put(u.getUserId(), u.getRoleName());
                    }
                //}
            }
        }
        return map;
    }

    /**
     * 返回柜台人员，用户的树形数据
     *
     * @param userId
     * @return
     * @see com.banger.mobile.facade.user.SysUserService#getSysUserAndCounterUser(java.lang.Integer)
     */
    public List<SysUserBean> getSysUserAndCounterUser(Integer userId) {
        return null;
    }

    /**
     * 根据用户集合查主管姓名
     *
     * @param userIds
     * @return
     */
    public Map<Integer, String> getLeadNamesByUserId(String userIds) {
        Map<Integer, String> map = new HashMap<Integer, String>();
        List<SysUserBean> list = sysUserDao.getLeadNamesByUserId(userIds);
        if (list != null && list.size() > 0) {
            for (SysUserBean u : list) {
                if (map.containsKey(Integer.parseInt(userIds))) {
                    map.put(Integer.parseInt(userIds), map.get(Integer.parseInt(userIds)) + "," + u.getUserName());
                } else {
                    map.put(Integer.parseInt(userIds), u.getUserName());
                }
            }
        }
        return map;
    }

    public List<SysUser> getAllData() {
        return sysUserDao.getAllData();
    }

    /**
     * @param sysUser
     * @param createUserId
     * @return
     * @see com.banger.mobile.facade.user.SysUserService#syncAddSysUser(com.banger.mobile.domain.model.user.SysUser, java.lang.Integer)
     */
    public void syncAddSysUser(SysUser sysUser, Integer createUserId) {
        sysUser.setIsDel(0);
        sysUser.setIsPasswordReset(0);
        sysUser.setPasswordStr(1);
        sysUser.setUpdateUser(createUserId);
        sysUser.setCreateUser(createUserId);
        sysUser.setIsActived(1);
        sysUser.setRemark("");
        sysUser.setPhone("");
        sysUser.setPhoneExt("");
        sysUser.setLastChangePassword(new Date());
        sysUser.setLastLoginDate(new Date());
        sysUser.setPassword(Md5Encrypt.md5(Constants.SESSION_DEFAULTPWD));
        sysUserDao.addSysUser(sysUser);
        SysRoleMember srm = new SysRoleMember();
        srm.setRoleId(4);
        srm.setUserId(sysUser.getUserId());
        srm.setCreateUser(createUserId);
        srm.setUpdateUser(createUserId);
        sysRoleMemberService.addSysRoleMember(srm);
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
    }

    /**
     * 根据信贷操作号查用户
     * @param operateCode
     * @return
     */
    public SysUser getSysUserByOperateCode(String operateCode) {
        if (operateCode==null||operateCode.length()<=0)
            return null;
        return sysUserDao.getSysUserByOperateCode(operateCode);
    }

    /**
     * 得到所有业务主管
     * @return
     */
    public List<SysUser> getAllManagerUser() {
        return sysUserDao.getAllManagerUser();
    }

    /**
     * 获取用户在信贷的机构信息
     * @param account
     * @return
     */
    @Override
    public  Map<String,Map<String,String>>  getUserJGINFO(String account) {
        return sysUserDao.getUserJGINFO(account);
    }
  
    public Integer getRoleByUserId(Integer userid){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("userId", Integer.valueOf(userid));
    	List<SysRoleMember> members = sysRoleMemberDao.getSysRoleMemberList("GetRoleMemberByUserIds", map);
    	return members.get(0).getRoleId();
    }

	@Override
	public List<SysUser> getSysUserTeamChiefByUserId(Integer userId) {
		return sysUserDao.getSysUserTeamCheifByUserId(userId);
	}

	@Override
	public List<SysUser> getUserListBelongToSysTeamByUserId(Integer userId) {
		return sysUserDao.getUserListBelongToSysTeamByUserId(userId);
	}

	@Override
	public Integer getReadomTeamManagerUserId(Integer userId,String Type) {
		
		List<Integer> userIdList = sysUserDao.getManagerSysUserUserIdListByTeamMemberUserId(userId);
		
		//---------------------------------------------------------------
		int minUserId = userIdList.get(0);
		int minCount = 0;
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		
		if(userIdList != null && userIdList.size() >0){
			parameterMap.put(Type, userIdList.get(0));
			minCount = lnLoanService.countApproveLoanBySysUserId(parameterMap);
		}
		
		for (int i=0;i<userIdList.size();i++) {
			parameterMap.put(Type, userIdList.get(i));
			int flag = lnLoanService.countApproveLoanBySysUserId(parameterMap);
			if(flag < minCount){
				minCount = flag;
				minUserId = userIdList.get(i);
			}
			
		}
		
		return minUserId;
	}

    /**
     * 获取后台人员列表
     * @param userId
     * @return
     */
    @Override
    public List<Integer> getReadomTeamManager(Integer userId) {

        List<Integer> userIdList = sysUserDao.getManagerSysUserUserIdListByTeamMemberUserId(userId);

        return  userIdList;
    }
	
	@Override
	public List<Integer> getReadomApproveUserId(Boolean isDoubleApproval,String approvalValue) {

		if(isDoubleApproval){
			List<Integer> userIdList = sysRoleMemberService.getUserIdByRole("4",approvalValue);			
			List<Integer> resultList = new ArrayList<Integer>();			
			if(userIdList == null || userIdList.size() < 1) return resultList;		
			Integer firstUserId=getReadomApproveUserId(userIdList);		
			
			userIdList = sysRoleMemberService.getUserIdByRole("4","");			
			if(userIdList == null || userIdList.size() < 2) return resultList;			
			List<Integer> secondUserIdList=new ArrayList<Integer>();
			for (int i=0;i<userIdList.size();i++){
				if(!userIdList.get(i).equals(firstUserId)){
					secondUserIdList.add(userIdList.get(i));
				}
			}			
			Integer secondUserId=getReadomApproveUserId(secondUserIdList);			
			resultList.add(firstUserId);
			resultList.add(secondUserId);
						
			return resultList;
		}else{
			List<Integer> userIdList = sysRoleMemberService.getUserIdByRole("4",approvalValue);			
			List<Integer> resultList = new ArrayList<Integer>();
			if(userIdList == null || userIdList.size() < 1) return resultList;			
			Integer firstUserId=getReadomApproveUserId(userIdList);			
			resultList.add(firstUserId);			
			return resultList;
		}		
	}
	
	private Integer getReadomApproveUserId(List<Integer> userIdList){
		
		if(userIdList == null || userIdList.size() < 1) return 0;
		
		int minUserId = userIdList.get(0);
		int minCount = 0;
		
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("approveCenterUserId1", userIdList.get(0));
		minCount = lnLoanService.countApproveLoanBySysUserId(parameterMap);
		
		for (int i=1;i<userIdList.size();i++) {
			parameterMap.put("approveCenterUserId1", userIdList.get(i));
			int flag = lnLoanService.countApproveLoanBySysUserId(parameterMap);
			
			if(flag < minCount){
				minCount = flag;
				minUserId = userIdList.get(i);
			}
		}
		return minUserId;

	}

	@Override
	public List<SysUser> getManagerByUserIdAndRoleId(Map<String, Object> param) {
		
		return sysUserDao.getManagerByUserIdAndRoleId(param);
	}

	@Override
	public Map<Integer, String> getColumnNames(String sql) throws SQLException {
		
		return sysUserDao.getColumnNames(sql);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> getResultData(String sql) throws SQLException {
		
		return sysUserDao.getResultData(sql);
	}

	@Override
	public int[] excute(String sqlString) throws SQLException {
		return sysUserDao.excute(sqlString);
	}

	
	
}










