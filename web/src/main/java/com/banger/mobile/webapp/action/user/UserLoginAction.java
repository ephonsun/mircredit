/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :用户登录Action
 * Author     :yangy
 * Create Date:2012-3-28
 */
package com.banger.mobile.webapp.action.user;

import com.banger.mobile.constants.Constants;
import com.banger.mobile.domain.Enum.user.EnumUserType;
import com.banger.mobile.domain.model.phoneConfig.PhoneSetting;
import com.banger.mobile.domain.model.sysRunDays.SysRunDays;
import com.banger.mobile.domain.model.user.*;
import com.banger.mobile.facade.clusters.ClustersConfigService;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.funcAuth.FuncAuthService;
import com.banger.mobile.facade.log.OpeventLoginLogService;
import com.banger.mobile.facade.menuAuth.MenuAuthService;
import com.banger.mobile.facade.param.SysParamService;
import com.banger.mobile.facade.personalConfig.PersonalConfigService;
import com.banger.mobile.facade.record.RecordInfoService;
import com.banger.mobile.facade.sysRunDays.SysRunDaysService;
import com.banger.mobile.facade.system.SysTalkLimitConfigService;
import com.banger.mobile.facade.unreadMessage.MessageNoticeService;
import com.banger.mobile.facade.user.SysUserOnlineMaxService;
import com.banger.mobile.facade.user.SysUserOnlineService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.facade.user.SysWorkDelegateService;
import com.banger.mobile.util.DateUtil;
import com.banger.mobile.util.FileUtil;
import com.banger.mobile.util.Md5Encrypt;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

//import com.banger.mobile.facade.mms.MmsPopupService;
//import com.banger.mobile.facade.sms.SmsBasicInfoService;
//import com.banger.mobile.facade.sms.SmsChannelBizServiceFactory;
//import com.banger.mobile.facade.sms.SmsReceiveLogService;
//import com.banger.mobile.facade.sms.SmsSendHistoryService;

/**
 * @author yangy
 * @version $Id: UserLoginAction.java,v 0.1 2012-3-28 下午3:27:12 yangy Exp $
 */
public class UserLoginAction extends BaseAction {

    private static final long       serialVersionUID = 4591081594874912579L;
    private SysUserService          sysUserService;
    private OpeventLoginLogService  opeventLoginLogService;                               //操作日志service
    private FuncAuthService         funcAuthService;
    //private SmsReceiveLogService    smsReceiveLogService;                                 //接收未读条数
    private CrmCustomerService      crmCustomerService;                                   //客户Service
    private PersonalConfigService   phoneService;                                   	  //电话配置Service
    private SysUserOnlineService    sysUserOnlineService;                                 //在线用户
    private SysWorkDelegateService  sysWorkDelegateService;                               //托管业务
    //private SmsChannelBizServiceFactory    smsChannelBizServiceFactoryImpl;                                 //彩信短信余额
    private RecordInfoService       recordInfoService;
    private SysRunDaysService       sysRunDaysService;                                    //运行天数
    DateFormat                      df1              = new SimpleDateFormat("yyyy-MM-dd");
    private SysUserOnlineMaxService sysUserOnlineMaxService;                              //峰值用户
    private PhoneSetting            phone;                                          	  //通话所有配置信息
    private MenuAuthService         menuAuthService;                                      //菜单权限
    private JSONArray               menuJson;                                             //菜单json                           
    private SysParamService         sysParamService;                                      //不被权限拦截的action service
    private DeptFacadeService       deptFacadeService;                                    //机构权限service
    private String                  roleIds;                                              //登录用户的角色字符串
    private int                     allUsers;                                             //总用户数
    private int                     onLineUsers;                                          //当前在线用户数
    private int                     inChargeOfUsers;                                      //管理总用户数
    private int                     maxUsers;                                             //峰值用户数
    private int                     maxDate;                                              //峰值时间
    private int                     totalDate;                                            //运行天数
    private int                     inChargeOnLineUsers;                                  //机构管理员所管理在线用户数
    private SysUserOnlineMax        onlineMax;                                            //峰值用户
    private int                     runTime;                                              //运行时间
    private int                     hasIcon;                                              //桌面显示图标
    private int                     webSize;                                              //应用服务器剩余空间
    private int                     recordSize;                                           //录音服务器剩余空间
    //private SmsSendHistoryService   smsSendHistoryService;
    //private SmsBasicInfoService     smsBasicInfoService;
    //private MmsPopupService         mmsPopupService;
    private MessageNoticeService	   messageNoticeService;
    private ClustersConfigService   clustersConfigService;								//集群配置服务
    private SysTalkLimitConfigService sysTalkLimitConfigService;//有效通话量设置接口
    private boolean talkLimitRemind;//通话量有效量提醒
    public void setClustersConfigService(ClustersConfigService clustersConfigService) {
        this.clustersConfigService = clustersConfigService;
    }

    public void setSysUserOnlineMaxService(SysUserOnlineMaxService sysUserOnlineMaxService) {
        this.sysUserOnlineMaxService = sysUserOnlineMaxService;
    }

    //public void setSmsSendHistoryService(SmsSendHistoryService smsSendHistoryService) {
    //    this.smsSendHistoryService = smsSendHistoryService;
    //}

    //public void setSmsBasicInfoService(SmsBasicInfoService smsBasicInfoService) {
    //    this.smsBasicInfoService = smsBasicInfoService;
    //}

    //public void setMmsPopupService(MmsPopupService mmsPopupService) {
    //    this.mmsPopupService = mmsPopupService;
    //}

    public void setSysWorkDelegateService(SysWorkDelegateService sysWorkDelegateService) {
        this.sysWorkDelegateService = sysWorkDelegateService;
    }

    //public void setSmsReceiveLogService(SmsReceiveLogService smsReceiveLogService) {
    //    this.smsReceiveLogService = smsReceiveLogService;
    //}

    public void setSysUserOnlineService(SysUserOnlineService sysUserOnlineService) {
        this.sysUserOnlineService = sysUserOnlineService;
    }

    public void setSysRunDaysService(SysRunDaysService sysRunDaysService) {
        this.sysRunDaysService = sysRunDaysService;
    }

    //public void setSmsChannelBizServiceFactoryImpl(SmsChannelBizServiceFactory smsChannelBizServiceFactoryImpl) {
   //     this.smsChannelBizServiceFactoryImpl = smsChannelBizServiceFactoryImpl;
    //}

    public void setMessageNoticeService(MessageNoticeService messageNoticeService) {
        this.messageNoticeService = messageNoticeService;
    }

    public int getRunTime() {
        return runTime;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    public SysUserOnlineMax getOnlineMax() {
        return onlineMax;
    }

    public void setOnlineMax(SysUserOnlineMax onlineMax) {
        this.onlineMax = onlineMax;
    }

    public int getHasIcon() {
        return hasIcon;
    }

    public void setHasIcon(int hasIcon) {
        this.hasIcon = hasIcon;
    }

    public CrmCustomerService getCrmCustomerService() {
        return crmCustomerService;
    }

    public void setCrmCustomerService(CrmCustomerService crmCustomerService) {
        this.crmCustomerService = crmCustomerService;
    }

    public void setOpeventLoginLogService(OpeventLoginLogService opeventLoginLogService) {
        this.opeventLoginLogService = opeventLoginLogService;
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }

    public void setRecordInfoService(RecordInfoService recordInfoService) {
        this.recordInfoService = recordInfoService;
    }

    public void setSysParamService(SysParamService sysParamService) {
        this.sysParamService = sysParamService;
    }

    public void setMenuAuthService(MenuAuthService menuAuthService) {
        this.menuAuthService = menuAuthService;
    }

    public PhoneSetting getPhone() {
        return phone;
    }

    public void setPhone(PhoneSetting phone) {
        this.phone = phone;
    }

    public void setFuncAuthService(FuncAuthService funcAuthService) {
        this.funcAuthService = funcAuthService;
    }

    public int getWebSize() {
        return webSize;
    }

    public void setWebSize(int webSize) {
        this.webSize = webSize;
    }

    public int getRecordSize() {
        return recordSize;
    }

    public void setRecordSize(int recordSize) {
        this.recordSize = recordSize;
    }

    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public JSONArray getMenuJson() {
        return menuJson;
    }

    public void setMenuJson(JSONArray menuJson) {
        this.menuJson = menuJson;
    }

    public int getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(int allUsers) {
        this.allUsers = allUsers;
    }

    public int getOnLineUsers() {
        return onLineUsers;
    }

    public void setOnLineUsers(int onLineUsers) {
        this.onLineUsers = onLineUsers;
    }

    public int getInChargeOfUsers() {
        return inChargeOfUsers;
    }

    public void setInChargeOfUsers(int inChargeOfUsers) {
        this.inChargeOfUsers = inChargeOfUsers;
    }

    public int getMaxUsers() {
        return maxUsers;
    }

    public void setMaxUsers(int maxUsers) {
        this.maxUsers = maxUsers;
    }

    public int getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(int maxDate) {
        this.maxDate = maxDate;
    }

    public int getTotalDate() {
        return totalDate;
    }

    public PersonalConfigService getPhoneService() {
        return phoneService;
    }

    public void setPhoneService(PersonalConfigService phoneService) {
        this.phoneService = phoneService;
    }

    public int getInChargeOnLineUsers() {
        return inChargeOnLineUsers;
    }

    public void setInChargeOnLineUsers(int inChargeOnLineUsers) {
        this.inChargeOnLineUsers = inChargeOnLineUsers;
    }

    public void setTotalDate(int totalDate) {
        this.totalDate = totalDate;
    }

    /**
     * 用户登陆
     * @return
     */
    public String login() {
        try {
            String account = request.getParameter("account");
            String password = request.getParameter("password");
            String str = sysUserService.login(account, password);
            if (str.equals("Pass")) {
                List<String> funCodes = funcAuthService.getFuncUrlByRoleIds(this.getLoginInfo()
                        .getRoles());
                this.getLoginInfo().setFuncCodes(funCodes);
                //绑定不被权限拦截的action
                String actions = sysParamService.getExcludeActions();
                this.getLoginInfo().setExcludeActions(actions);
                if(deptFacadeService.isInChargeOfDepartment()) {
                    this.getLoginInfo().setDataCompetence(3+"");
                }else{
                    this.getLoginInfo().setDataCompetence(4+"");
                }
                Map<String, Object> parameters = new HashMap<String, Object>();
                parameters.put("userId", this.getLoginInfo().getUserId());
                List<SysUserOnline> sysUserOnlineList = sysUserOnlineService.getSysUserOnlineList();//取出当前系统的在线用户和离开用户数
                Integer maxNum = sysUserOnlineList.size();
                List<SysUserOnlineMax> sysUserOnlineMaxList = sysUserOnlineMaxService
                        .getSysUserOnlineMaxList(parameters);
                SysUserOnlineMax sysUserOnlineMax = new SysUserOnlineMax();
                if (sysUserOnlineMaxList.size() > 0) {//峰值用户表中记录大于0说明有数据，做更新，否则新增
                    sysUserOnlineMax = sysUserOnlineMaxList.get(0);
                    if (sysUserOnlineMax.getMaxNum() < maxNum) {//当峰值少于在线用户和离开用户时更新
                        sysUserOnlineMax.setMaxDate(new Date());
                        sysUserOnlineMax.setMaxNum(maxNum);
                        sysUserOnlineMaxService.updateSysUserOnlineMax(sysUserOnlineMax);
                    }
                } else {
                    sysUserOnlineMax.setMaxDate(new Date());
                    sysUserOnlineMax.setMaxNum(maxNum);
                    sysUserOnlineMax.setUpdateUser(this.getLoginInfo().getUserId());
                    sysUserOnlineMax.setCreateUser(this.getLoginInfo().getUserId());
                    sysUserOnlineMaxService.addSysUserOnlineMax(sysUserOnlineMax);
                }
                opeventLoginLogService.addLog(8, EnumUserType.USER_LOGIN.getValue(), 1, 1);
            }
            PrintWriter out = this.getResponse().getWriter();
            out.write(str);
            out.flush();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("login action error:" + e.getMessage());
            //opeventLoginLogService.addLog(0,8, EnumUserType.USER_LOGIN.getValue(),, 1, 0, "");
            return null;
        }

    }

    /**
     * 登录页面初始化
     * @return
     */
    public String getLoginPage() {
        try {
            return SUCCESS;
        } catch (Exception e) {
            return ERROR;
        }

    }

    /**
     * 超时登录页面初始化
     * @return
     */
    public String showTimeOutLogin() {
        return SUCCESS;
    }

    /**
     * 登录页面初始化
     * @return
     */
    public String initMessage() {
        Map<String,Integer> map = this.messageNoticeService.getMessageCounts(this.getLoginInfo().getUserId());
        for(String type : map.keySet())
        {
            request.setAttribute(type,map.get(type));
        }
        return SUCCESS;
    }

    /**
     * 获取未读，未接
     * @return
     */
    public String getCallMessage() {
        try {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();

            if(deptFacadeService.isCommon() || deptFacadeService.isInChargeOfDepartment()){
                String unreadMessage = ""
                        + recordInfoService.getUnreadCount(this.getLoginInfo().getUserId());//未读留言
                String missingCall = ""
                        + recordInfoService.getMissedCount(this.getLoginInfo().getUserId());//未接来电
                String smsReadRemind = "";
                       // + smsReceiveLogService.GetAllSmsReceiveLogCountByUserId(this.getLoginInfo().getUserId()); //未读短信
                out.print(unreadMessage + "," + missingCall+","+smsReadRemind);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 初始密码初始化
     * @return
     */
    public String showconfirmPwd() {
        this.request.setAttribute("Account", this.getLoginInfo().getAccount());
        return SUCCESS;
    }

    public void initMainPage() {
        if (deptFacadeService.isInChargeOfDepartment() || deptFacadeService.isCommon()) {
            hasIcon = 1;
        }
        menuJson = menuAuthService.getMenuJson(); //菜单权限json
        IUserInfo loginInfo = this.getLoginInfo();
        if (loginInfo != null) { //取不到在线用户
            String[] roles = loginInfo.getRoles();
            if (roles.length > 0) {
                for (String str : roles) {
                    roleIds += str + ",";
                }
                roleIds = roleIds.substring(0, roleIds.length() - 1);
            }
            this.request.setAttribute("userName", this.getLoginInfo().getUserName());
            this.request.setAttribute("account", this.getLoginInfo().getAccount());
            this.request.setAttribute("userId", this.getLoginInfo().getUserId());
            this.request.setAttribute("deptName", this.getLoginInfo().getDeptname());
            String[] roleList = getLoginInfo().getRoleNames();
            List<String> roleNames = new ArrayList<String>();
            for (int i = 0; i < roleList.length; i++) {
                if (StringUtil.isNotEmpty(roleList[i]))
                    roleNames.add(roleList[i]);
            }
            request.setAttribute("roleNames", roleNames);
            if (!deptFacadeService.isSystemAdmin()) {
                GetRecordsNumber();
                recordInfoIsShow();
                setPhoneSetting();
                getTalklimitRemind();
            } else {
                talkLimitRemind = false;
            }
        }
    }

    /**
     * 判断用户是否需要提醒昨天通话已经超过限制
     */
    private void getTalklimitRemind() {
        talkLimitRemind = sysTalkLimitConfigService.isTalklimitConfigRemind(this.getLoginInfo().getUserId());
    }
    /**
     * 首页页面初始化
     * @return
     */
    public String getMainPage() {
        try {
            initMainPage();
            return SUCCESS;
//            boolean bool = authorizeService.isAuthFile();
//            if (bool) {
//                initMainPage();
//                if (getLoginInfo().getAccount().equals("admin")) {
//                    return SUCCESS;
//                }
//                Map<String, Object> map = authorizeService.isSystemAuthorize();
//                if (map != null) {
//                    if (Boolean.parseBoolean(map.get("isAuth").toString()) == true) {//已授权
//                        /**
//                         * 验证授权信息
//                         */
//                        if (map.get("expiredDate") != null) {
//                            Integer i = DateUtil.getSystemDate(map.get("expiredDate").toString());
//                            if (i == 1) {//系统过期
//                                return "systemExpired";
//                            }
//                        } else {
//                            Integer count = Integer.parseInt(map.get("userCount").toString());
//                            if (count != 0) {
//                                if (count == -1) {//授权码错误，未授权
//                                    return "noAuth";
//                                }
//                            }
//                        }
//                        return SUCCESS;
//                    } else {//未授权
//                        return "noAuth";
//                    }
//                } else {
//                    return ERROR;
//                }
//            } else {
//                return "noAuth";
//            }
        } catch (Exception e) {
            e.printStackTrace();
            log.debug(e.getMessage());
            return ERROR;
        }
    }

    /**
     * 读取话机配置
     */
    private void setPhoneSetting()
    {
        this.phone = this.phoneService.getPersonConfig(this.getLoginInfo().getUserId());
        this.phone.setClusters(this.clustersConfigService.getClustersConfig());
        this.phone.setTransport(this.phone.getClusters().getTransport());
    }

    /**
     * 主界面内未读留言，未接电话，未读短信，彩信余额数，过期任务
     */
    public void GetRecordsNumber() {

        int callRemind=0;
        int messageRemind=0;
        int smsReadRemind=0;
        if(deptFacadeService.isCommon() || deptFacadeService.isInChargeOfDepartment()){
            callRemind= recordInfoService.getMissedCount(this.getLoginInfo().getUserId());//未接来电
            request.setAttribute("callRemind", callRemind);
            messageRemind = recordInfoService.getUnreadCount(this.getLoginInfo().getUserId());//未读留言
            request.setAttribute("messageRemind", messageRemind);
            //smsReadRemind = smsReceiveLogService.GetAllSmsReceiveLogCountByUserId(this.getLoginInfo().getUserId()); //未读短信
            request.setAttribute("smsReadRemind", smsReadRemind);
        }

//        Map<String, Object> balance = smsChannelBizServiceFactoryImpl.getSmsChannelBizService().QueryAccountBalance(getLoginInfo()
//                .getUserId());
//        request.setAttribute("totalBalance",
//                balance.get("totalBalance") == null ? 0.0 : balance.get("totalBalance"));
//        request.setAttribute("smsBalance",
//                balance.get("smsBalance") == null ? 0.0 : balance.get("smsBalance"));
//        request.setAttribute("mmsBalance",
//                balance.get("mmsBalance") == null ? "0.0" : balance.get("mmsBalance"));
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("delegateUserId", getLoginInfo().getUserId());
        parameters.put("isCancel", 1);
        SysWorkDelegate sysWorkDelegate = null;
        if (sysWorkDelegateService.getSysWorkDelegateList(parameters).size() > 0)
            sysWorkDelegate = sysWorkDelegateService.getSysWorkDelegateList(parameters).get(0);
        if (sysWorkDelegate != null) {
            request.setAttribute("workDelegate", "yes");
            request.setAttribute("workUserId",
                    sysUserService.getSysUserById(sysWorkDelegate.getUserId()).getAccount());
        }
        SysUser sysUser = sysUserService.getSysUserById(getLoginInfo().getUserId());
        try {
            Date EndTime = new Date();
            String BeginTime = DateUtil.getDateToString(sysUser.getLastChangePassword());
            EndTime = DateUtil.addMonth(EndTime, -3);
            long t = DateUtil.countDays(DateUtil.getDateToString(EndTime), BeginTime, "yyyy-MM-dd");
            if (t <= 5) {
                request.setAttribute("passWordReind", "yes");
                Date beginTime = new Date();
                beginTime = DateUtil.addDay(beginTime, (int) t);
                request.setAttribute("lastChangePassword", DateUtil.getDateToString(beginTime));
            }
        } catch (Exception e) {
        }
    }

    /**
     * 系统管理员，机构系统管理员不显示，未读未接
     */
    public void recordInfoIsShow() {
        String[] roleids = this.getLoginInfo().getRoles();
        boolean fg = false;
        for (String str : roleids) {
            if (str.equals("3") || str.equals("4")){
                fg = true;
                break;
            }
        }
        if (fg)
            request.setAttribute("flag", "yes");
    }

    /**
     * 初始密码修改成功页面
     */
    public String showPwdSuccessPage() {
        try {
            this.request.setAttribute("Account", this.getLoginInfo().getAccount());
            return SUCCESS;
        } catch (Exception e) {
            log.error("showPwdSuccess action error:" + e.getMessage());
            return null;
        }
    }

    /**
     * 初始密码修改成功页面
     */
    public String showTimeOutPwdSuccessPage() {
        try {
            this.request.setAttribute("Account", this.getLoginInfo().getAccount());
            return SUCCESS;
        } catch (Exception e) {
            log.error("showPwdSuccess action error:" + e.getMessage());
            return null;
        }
    }

    /**
     * 初始密码修改
     */
    public String pwdConfirm() {
        try {
            String password = "" + this.request.getParameter("password");
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            String pws = request.getParameter("passwordStr");
            SysUser user = sysUserService.getSysUserById(this.getLoginInfo().getUserId());
            if (user.getPassword().equals(Md5Encrypt.md5(password))) {
                out.write(EnumUserType.OLDEQUALSPASSWORDERROR.getValue());
            } else {
                user.setLastChangePassword(new Date());
                user.setPasswordStr(Integer.parseInt(pws));
                sysUserService.updateSysUser(user);
                sysUserService.pwdConfirm(getLoginInfo().getUserId(), password);
                opeventLoginLogService
                        .addLog(9, EnumUserType.UPDATE_USER_PASSWORD.getValue(), 1, 1);
                List<String> funCodes = funcAuthService.getFuncUrlByRoleIds(this.getLoginInfo()
                        .getRoles());
                this.getLoginInfo().setFuncCodes(funCodes);
                if(deptFacadeService.isInChargeOfDepartment()) {
                    this.getLoginInfo().setDataCompetence(3+"");
                }else{
                    this.getLoginInfo().setDataCompetence(4+"");
                }
                //绑定不被权限拦截的action
                String actions = sysParamService.getExcludeActions();
                getLoginInfo().setExcludeActions(actions);
                getChangeUserStatus();
            }
            return null;
        } catch (Exception e) {
            log.error("pwdConfirm action error:" + e.getMessage());
            opeventLoginLogService.addLog(9, EnumUserType.UPDATE_USER_PASSWORD.getValue(), 1, 0);
            return null;
        }
    }

    /**
     * 更新用户状态
     */
    public void getChangeUserStatus(){
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("userId", getLoginInfo().getUserId());
        List<SysUserOnline> sysUserOnlineList = sysUserOnlineService
                .getSysUserOnlineList(parameters);
        if(sysUserOnlineList.size()>0){
            SysUserOnline sysUserOnline = sysUserOnlineList.get(0);
            sysUserOnline.setOnlineStatusId(1);
            sysUserOnlineService.updateSysUserOnline(sysUserOnline);
        }else{
            SysUserOnline sysUserOnline = new SysUserOnline();
            sysUserOnline.setOnlineStatusId(1);
            sysUserOnline.setLoginDate(new Date());
            sysUserOnline.setUserId(this.getLoginInfo().getUserId());
            sysUserOnline.setUserSessionId(this.getLoginInfo().getSessionId());
            sysUserOnline.setUpdateUser(this.getLoginInfo().getUserId());
            sysUserOnline.setCreateUser(this.getLoginInfo().getUserId());
            sysUserOnlineService.addSysUserOnline(sysUserOnline);//在线用户设置
        }
    }

    /**
     * 三个月超时密码修改
     */
    public String lastPwdConfirm() {
        try {
            String password = "" + this.request.getParameter("password");
            String opw = this.request.getParameter("oldpassword");
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            String pws = request.getParameter("passwordStr");
            SysUser user = sysUserService.getSysUserById(this.getLoginInfo().getUserId());
            if (!user.getPassword().equals(Md5Encrypt.md5(opw))) {
                out.write(EnumUserType.OLDPASSWORDERROR.getValue());
            } else {
                if (user.getPassword().equals(Md5Encrypt.md5(password))) {
                    out.write(EnumUserType.OLDEQUALSPASSWORDERROR.getValue());
                } else {
                    user.setLastChangePassword(new Date());
                    user.setPasswordStr(Integer.parseInt(pws));
                    sysUserService.updateSysUser(user);
                    sysUserService.pwdConfirm(this.getLoginInfo().getUserId(), password);
                    opeventLoginLogService.addLog(9, EnumUserType.UPDATE_USER_PASSWORD.getValue(),
                            1, 1);
                    List<String> funCodes = funcAuthService.getFuncUrlByRoleIds(this.getLoginInfo()
                            .getRoles());
                    this.getLoginInfo().setFuncCodes(funCodes);
                    if(deptFacadeService.isInChargeOfDepartment()) {
                        this.getLoginInfo().setDataCompetence(3+"");
                    }else{
                        this.getLoginInfo().setDataCompetence(4+"");
                    }
                    //绑定不被权限拦截的action
                    String actions = sysParamService.getExcludeActions();
                    this.getLoginInfo().setExcludeActions(actions);
                }
            }
            getChangeUserStatus();
            return null;
        } catch (Exception e) {
            log.error("pwdConfirm action error:" + e.getMessage());
            opeventLoginLogService.addLog(9, EnumUserType.UPDATE_USER_PASSWORD.getValue(), 1, 0);
            return null;
        }
    }

    /**1
     * 改变登陆用户状态
     * @return
     */
    public String changeUserStatus() {
        try {
            String type = request.getParameter("type");
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("userId", this.getLoginInfo().getUserId());
            List<SysUserOnline> sysUserOnlineList = sysUserOnlineService
                    .getSysUserOnlineList(parameters);
            SysUserOnline sysUserOnline = sysUserOnlineList.get(0);
            if (type.equals("online"))
                sysUserOnline.setOnlineStatusId(1);
            else
                sysUserOnline.setOnlineStatusId(2);
            sysUserOnlineService.updateSysUserOnline(sysUserOnline);
        } catch (Exception e) {
            log.error("changeUserStatus action error:" + e.getMessage());
        }
        return null;
    }

    /**
     * 用户登出
     * @return
     */
    public String logout() {
        IUserInfo loginInfo = this.getLoginInfo();
        if(loginInfo!=null){
            System.out.println("登出用户帐号:"+loginInfo.getAccount());
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("userId", loginInfo.getUserId());
            List<SysUserOnline> sysUserOnlineList = sysUserOnlineService
                    .getSysUserOnlineList(parameters);
            if(sysUserOnlineList!=null&&sysUserOnlineList.size()>0){
                SysUserOnline sysUserOnline = sysUserOnlineList.get(0);
                sysUserOnline.setOnlineStatusId(3);
                sysUserOnlineService.updateSysUserOnline(sysUserOnline);
            }
            opeventLoginLogService.addLog(8, EnumUserType.USER_LOGOUT.getValue(), 1, 1);
            request.getSession().removeAttribute(Constants.SESSION_LOGININFO);
            request.getSession().invalidate();
        }
        return  SUCCESS;
    }

    /**
     * 点击关闭按钮用户登出
     * @return
     */
    public void closeLogOut() {
        IUserInfo loginInfo = this.getLoginInfo();
        String userId =request.getParameter("userId");
        if(loginInfo!=null){
            if(this.getRequest().getParameterMap().containsKey("oldAccount")){
                String oldAccount = this.getRequest().getParameter("oldAccount");
                String oldUserId =sysUserService.getAllUserByAccount(oldAccount).getUserId()+"";
                if(!loginInfo.getUserId().toString().equals(oldUserId)){
                    Map<String, Object> parameters = new HashMap<String, Object>();
                    parameters.put("userId",Integer.parseInt(oldUserId));
                    List<SysUserOnline> sysUserOnlineList = sysUserOnlineService.getSysUserOnlineList(parameters);
                 if(sysUserOnlineList!=null&&sysUserOnlineList.size()>0){
                        SysUserOnline sysUserOnline = sysUserOnlineList.get(0);
                        sysUserOnline.setOnlineStatusId(3);
                        sysUserOnlineService.updateSysUserOnline(sysUserOnline);
                    }
                    opeventLoginLogService.addLog(8, EnumUserType.USER_LOGOUT.getValue(), 1, 1);
                }
            }
            Map<String, Object> parameters = new HashMap<String, Object>();
            SysUserOnline sysUserOnline=null;
            parameters.put("userId",loginInfo.getUserId());
            List<SysUserOnline> sysUserOnlineList = sysUserOnlineService.getSysUserOnlineList(parameters);
            if(sysUserOnlineList!=null&&sysUserOnlineList.size()>0){
                sysUserOnline = sysUserOnlineList.get(0);
                sysUserOnline.setOnlineStatusId(3);
                sysUserOnlineService.updateSysUserOnline(sysUserOnline);
            }
            opeventLoginLogService.addLog(8, EnumUserType.USER_LOGOUT.getValue(), 1, 1);
            parameters.clear();
            parameters.put("userId",userId);
            List<SysUserOnline> onlineUserList = sysUserOnlineService.getSysUserOnlineList(parameters);
            if(onlineUserList!=null&&onlineUserList.size()>0){
                SysUserOnline userOnline = onlineUserList.get(0);
                 if(!sysUserOnline.getOnlineStatusId().equals(userOnline.getOnlineStatusId())){
                     request.getSession().removeAttribute(Constants.SESSION_LOGININFO);
                     request.getSession().invalidate();
                 }
            }
        }
    }

    /**
     * 系统桌面工作台
     */
    public String getWorkBench() {
        //查询录音存放预警
        Integer cueSize = sysParamService.getCueSize();
        if(cueSize!=null){
            cueSize = cueSize/1024;
            request.setAttribute("cueSize", cueSize);
        }
        String[] ids = this.getLoginInfo().getRoles();
        Set<String> roleIds = new HashSet<String>();
        for (String str : ids) {
            roleIds.add(str);
        }
        allUsers = sysUserService.getAllUserCount();//总用户数
        Integer[] userids = sysUserOnlineService.getOnlineUserIds();//在线用户数
        if (userids != null)
            onLineUsers = userids.length;
        else
            onLineUsers = 0;

        /**************系统磁盘空间************/
        File file = new File(request.getRealPath(""));
        webSize = (int) (file.getFreeSpace() / (1024 * 1024 * 1024));
        String path = sysParamService.getRecordPath();
        File recFile = new File(path);
        if(FileUtil.isValidSystemFilePath(path)){
            if (!recFile.exists()) {
                recordSize = -1;
            } else {
                recordSize = (int) (recFile.getFreeSpace() / (1024 * 1024 * 1024));
            }
        }else{
            recordSize = -1;
        }


        /***********处理桌面显示 ************/
        int flag = 0;
        if (roleIds.contains("1")) {//系统管理员
            List<SysUserOnlineMax> sysUserOnlineMaxList = sysUserOnlineMaxService
                    .getSysUserOnlineMaxList(null);
            if(sysUserOnlineMaxList.size()>0)
                onlineMax = sysUserOnlineMaxList.get(0);
            else{
                onlineMax =new SysUserOnlineMax();
                onlineMax.setMaxNum(0);
            }
            List<SysRunDays> runDays = sysRunDaysService.getSysRunDays();
            Date nearDate = runDays.get(0).getRunDate();//最近一次启动时间
            Calendar cal = Calendar.getInstance();
            Date now = cal.getTime();//当前时间
            List<SysRunDays> stopStartTime = new ArrayList<SysRunDays>();
            for (int i = 0; i < runDays.size(); i++) {
                if (runDays.get(i).getRunType().equals(0)) { //停止
                    stopStartTime.add(runDays.get(i));
                    for (int j = i + 1; j < runDays.size(); j++) {
                        if (runDays.get(j).getRunType().equals(1)) {//开启
                            stopStartTime.add(runDays.get(j));
                            break;
                        }
                    }
                }
            }
            long tt = 0;
            for (int k = 0; k < stopStartTime.size() - 1; k++) {
                tt += stopStartTime.get(k).getRunDate().getTime()
                        - stopStartTime.get(k + 1).getRunDate().getTime();
            }
            tt += now.getTime() - nearDate.getTime();
            runTime = (int) (tt / (1000 * 60 * 60 * 24));

            return "admin";
        } else {
            if (roleIds.contains("3")) {
            	flag =23;
            }
            if (roleIds.contains("4")) {
            	flag =23;
            }
            if (roleIds.contains("5")) {
            	flag =23;
            }
            if (roleIds.contains("6")) {
            	flag =3;
            }
            if (roleIds.contains("7")) {
            	flag =3;
            }
        }
        
 
        
        Map<Integer,String> map = sysUserService.getRoleNamesByUserId(this.getLoginInfo().getUserId().toString());
        if(map.get(this.getLoginInfo().getUserId()).equals("机构系统管理员")){
            flag = 2;
        }
        
        if (flag == 2) {//机构管理员
            Integer[] cIds = deptFacadeService.getInChargeOfDeptUsersCount();
            if (cIds != null) {
                inChargeOfUsers = cIds.length;//管理用户数
            } else
                inChargeOfUsers = 0;
            inChargeOnLineUsers = getInChargeOnlineCount(userids, cIds);//管理的在线用户数
            return "deptAdmin";
        }
        if (flag == 23) {//机构管理员、业务主管|客户经理

            return "deptAdminAndManager";
        }
        if (flag == 24) {//机构管理员、客户经理
            return "deptAdminAndManager";
        }
        if (flag == 3) {//业务主管、客户经理
            return "managerOrCommon";
        }
        if (flag == 4) {//客户经理
            return "managerOrCommon";
        }

        return ERROR;
    }

    /**
     * 系统提示
     * @return
     */
    public String getSystemRemind() {
        try {
            Integer[] userids = sysUserOnlineService.getOnlineUserIds();//在线用户数

            //查询录音存放预警
            Integer cueSize = sysParamService.getCueSize();
            if(cueSize!=null){
                cueSize = cueSize/1024;
                request.setAttribute("cueSize", cueSize);
            }

            /**************系统磁盘空间************/
            File file = new File(request.getRealPath(""));
            webSize = (int) (file.getFreeSpace() / (1024 * 1024 * 1024));
            String path = sysParamService.getRecordPath();
            File recFile = new File(path);
            if(FileUtil.isValidSystemFilePath(path)){
                if (!recFile.exists()) {
                    recordSize = -1;
                } else {
                    recordSize = (int) (recFile.getFreeSpace() / (1024 * 1024 * 1024));
                }
            }else{
                recordSize = -1;
            }

            Integer[] cIds = deptFacadeService.getInChargeOfDeptUsersCount();
            if (cIds != null) {
                inChargeOfUsers = cIds.length;
            } else
                inChargeOfUsers = 0;
            inChargeOnLineUsers = getInChargeOnlineCount(userids, cIds);
            return SUCCESS;
        } catch (Exception e) {
            log.error("getSystemRemind action error:" + e.getMessage());
            return ERROR;
        }
    }

    /**
     * 获得所管理的在线用户数
     * @param onLineIds
     * @param inChargeIds
     * @return
     */
    private int getInChargeOnlineCount(Integer[] onLineIds, Integer[] inChargeIds) {
        int inChargeOnLineUsers = 0;
        Set<Integer> set = new HashSet<Integer>();
        if (inChargeIds != null && inChargeIds.length > 0 && onLineIds != null
                && onLineIds.length > 0) {
            for (Integer oId : onLineIds) {
                set.add(oId);
            }
            for (Integer cId : inChargeIds) {
                if (set.contains(cId))
                    inChargeOnLineUsers++;
            }
        }
        return inChargeOnLineUsers;
    }

    public String getServiceTime() {
        try {
            PrintWriter out = this.getResponse().getWriter();
            out.write(String.valueOf(new Date().getTime()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * sql注入跳转页面
     * @return
     */
    public String getCheckSqlPage() {
        try {
            return SUCCESS;
        }catch(Exception e) {
            log.error("getCheckSqlPage action error:" + e.getMessage());
            return ERROR;
        }
    }

    /**
     *  获取当前用户的余额
     */
    public void changedMoney(){
        try {
            PrintWriter out = this.getResponse().getWriter();
            JSONObject jso = new JSONObject();            
            jso.put("totalBalance","0.0");
            jso.put("smsBalance","0.0");
            jso.put("mmsBalance","0.0");
            
            out.print(jso);
            out.flush();
            out.close();
        }catch(Exception e) {
            log.error("changeMoney action error:" + e.getMessage());
        }
    }

    public boolean isTalkLimitRemind() {
        return talkLimitRemind;
    }

    public void setTalkLimitRemind(boolean talkLimitRemind) {
        this.talkLimitRemind = talkLimitRemind;
    }

    public void setSysTalkLimitConfigService(
            SysTalkLimitConfigService sysTalkLimitConfigService) {
        this.sysTalkLimitConfigService = sysTalkLimitConfigService;
    }
}
