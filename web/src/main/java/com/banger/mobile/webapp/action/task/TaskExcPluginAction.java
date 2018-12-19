/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :组织机构树显示用户，添加多个执行者
 * Author     :liyb
 * Create Date:2012-8-6
 */
package com.banger.mobile.webapp.action.task;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.Enum.task.EnumTask;
import com.banger.mobile.domain.model.dept.DeptUserBean;
import com.banger.mobile.domain.model.task.TskTask;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.dept.DeptService;
import com.banger.mobile.facade.task.TskTaskService;
import com.banger.mobile.facade.task.TskTaskTargetService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author liyb
 * @version $Id: TaskExcPluginAction.java,v 0.1 2012-8-6 上午10:18:58 liyb Exp $
 */
public class TaskExcPluginAction extends BaseAction {

    private static final long      serialVersionUID = 8565376514402617151L;
    private DeptService            deptService;                            //机构service
    private SysUserService         sysUserService;
    private JSONArray              deptJson;                               //机构树json
    private PageUtil<DeptUserBean> deptUserList;                           //活动分页对象
    private String                 deptName;                               //页面输出deptName
    private int                    deptId;
    private TskTaskService         tskTaskService;                         //任务管理Service
    private TskTask                tskTask;
    private TskTaskTargetService   tskTaskTargetService;                   //任务目标客户service
    private String excTypes;
    private List<SysUser> userList;
    private String excUsersIds;//多用户ID字符串
    private Integer treeType;//0:任务添加 
    private DeptFacadeService      deptFacadeService;
    
    /**
     * 字符串比较
     * @param str
     * @param x
     * @return
     */
    public int compareStr(String str, String x[]) {
        int suffix = 0;//记录字符串数组的下标
        for (int i = 0; i < x.length; i++) {
            if (str.equals(x[i].trim()))
                suffix = i + 1;
        }
        return suffix;
    }
    /**
     * 初始化登陆人角色
     */
    public Integer initLoginRole(){
        Integer roleType=0;
        this.request.setAttribute("userLoginName", this.getLoginInfo().getUserName());
        this.request.setAttribute("userLoginDeptId", getLoginInfo().getDeptId());
        String[] roleName=getLoginInfo().getRoleNames();
        if (compareStr(EnumTask.BUSINESS_COMPETENT.getValue(), roleName) != 0 
                || compareStr(EnumTask.ACCOUNT_ADMIN.getValue(), roleName) != 0
                || compareStr(EnumTask.ACCOUNT_AGENT.getValue(), roleName)!=0) {//业务主管、系统管理员、机构系统管理员
            roleType=0;
        }else{
            roleType=getLoginInfo().getUserId();
        }
        return roleType;
    }
    
    /**
     * 根据当前登陆人的角色获取所查询的数据ID
     * @return
     */
    public String getDeptUserIdsString(){
        Integer roleFlag=initLoginRole();
        String userIds="";
        String deptIds="";
        String deptUserIds="";
        if(roleFlag==0){//存在业务主管
            Integer[] uids=deptFacadeService.getManagerInChargeOfUserIds();
            Integer[] depts=deptFacadeService.getInChargeOfDeptIds();
            if(uids!=null){
                for (Integer ids:uids) {
                    userIds=ids+","+userIds;
                }
                userIds=userIds+getLoginInfo().getUserId().toString();
            }else{
                userIds=getLoginInfo().getUserId().toString();
            }
            if(depts!=null){
                for(Integer dIds:depts){
                    deptIds=dIds+","+deptIds;
                }
            }
            deptUserIds=deptIds+userIds;
        }else{
            deptUserIds=getLoginInfo().getUserId().toString()+","+getLoginInfo().getDeptId();
        }
        return deptUserIds;
    }
    
    /**
     * 返回当前登陆人所管辖的部门ID集合
     * @return
     */
    public String getDeptIdsString(){
        Integer roleFlag=initLoginRole();
        String deptIds="";
        String deptUserIds="";
        if(roleFlag==0){//存在业务主管
            Integer[] depts=deptFacadeService.getInChargeOfDeptIds();
            if(depts!=null){
                for(Integer dIds:depts){
                    deptIds=dIds+","+deptIds;
                }
            }
            deptUserIds=deptIds.substring(0,deptIds.length()-1);
        }else{
            deptUserIds=getLoginInfo().getDeptId().toString();
        }
        return deptUserIds;
    }

    /**
     * 机构树显示
     * @return
     */
    public void showDeptExcUserList() {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        try {
            PrintWriter out = response.getWriter();
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("deptId", getDeptIdsString());
            map.put("myUserId", getLoginInfo().getUserId());
            if(tskTask!=null&&tskTask.getTaskId()!=null){//组织机构用户树附带客户分配数
                map.put("taskId", tskTask.getTaskId());
                map.put("taskDeptId", tskTask.getExecuteDeptId());
                if(tskTask.getTaskType()!=null){
                    map.put("taskType", 1);
                }
                if(excTypes!=null){
                    map.put("type", excTypes);
                }
                deptJson=tskTaskService.getCustomerAttrPluginCount(map);
            }else{//查询组织机构以及用户树
                if(tskTask!=null&&tskTask.getExcUsersIds()!=null){
                    map.put("userIds", tskTask.getExcUsersIds());
                    map.put("taskId", treeType);
                }
                if(treeType==0){//任务添加
                    map.put("taskAdd", true);
                }
                map.put("deptUserIds", getDeptUserIdsString());
                
                /**
                 * 过滤掉机构下无用户的机构
                 */
                deptJson=tskTaskService.getCustomerAttrPluginList(map);
            }
            out.print(deptJson);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("showDeptList action error:" + e.getMessage());
        }
    }
    
    /**
     * 编辑/查看任务返回当前权限范围的用户ID
     * @return
     */
    public String getExcUserIdsString(){
        Integer roleFlag = initLoginRole();
        String userIds = "";
        if (roleFlag == 0) {//存在业务主管
            Integer[] uids = deptFacadeService.getInChargeOfDeptUserIds();
            if (uids != null) {
                for (Integer ids : uids) {
                    userIds = ids + "," + userIds;
                }
                userIds = userIds+getLoginInfo().getUserId().toString();
            }else userIds = getLoginInfo().getUserId().toString();
        } else {
            userIds = getLoginInfo().getUserId().toString();
        }
        return userIds;
    }
    
    /**
     * 加载修改执行者列表
     * @return
     */
    public String initUpdateExcUserList(){
        try {
            String userIds=getExcUserIdsString();
            tskTask.setExecuteUserId(getLoginInfo().getUserId());
            tskTask.setExcUsersIds(userIds);
            tskTask=tskTaskService.getTskTaskById(tskTask);
            initExecuteUser(tskTask.getTaskId(),userIds);
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("initUpdateExcUserList action error:"+e.getMessage());
            return ERROR;
        }
    }
    
    /**
     * 查询机构下在此任务中的用户
     * @return
     */
    public String getDeptUserListPage(){
        try {
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("deptId", deptId);
            map.put("userIds", excUsersIds);
            userList=sysUserService.getDeptBelongUserTaskList(map);
            String userIds=getExcUserIdsString();
            tskTask.setExecuteUserId(getLoginInfo().getUserId());
            tskTask.setExcUsersIds(userIds);
            tskTask=tskTaskService.getTskTaskById(tskTask);
            initExecuteUser(tskTask.getTaskId(),userIds);
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("getDeptUserListPage action errot:"+e.getMessage());
            return ERROR;
        }
    }
    
    /**
     * 组合执行者
     */
    public void initExecuteUser(Integer taskId,String userIds){
        Map<String,Object> parameters=new HashMap<String, Object>();
        parameters.put("taskIds", tskTask.getTaskId());
        parameters.put("userIds", userIds);
        List<TskTask> tskList=tskTaskService.getParentTskTaskList(parameters);
        Map<Integer,String> map=new HashMap<Integer, String>();
        for (int j = 0; j < tskList.size(); j++) {
            String excUserName="";
            String excUserId="";
            //执行者名称
            if(map.containsKey(tskList.get(j).getParentTaskId())){
                excUserName=map.get(tskList.get(j).getParentTaskId())+","+tskList.get(j).getExecuteName();
                map.put(tskList.get(j).getParentTaskId(), excUserName);
            }else{
                map.put(tskList.get(j).getParentTaskId(), tskList.get(j).getExecuteName());
            }
            //执行者ID
            if(map.containsKey(0)){
                excUserId=map.get(0)+","+tskList.get(j).getExcUsersIds();
                map.put(0, excUserId);
            }else{
                map.put(0, tskList.get(j).getExcUsersIds());
            }
        }
        tskTask.setExecuteName(map.get(tskTask.getTaskId()));
        tskTask.setExcUsersIds(map.get(0));
    }
    
    /**
     * 查询任务指定的执行者对应的联系客户数
     * @return
     */
    public void getExcUserCusCount() {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        try {
            PrintWriter out = response.getWriter();
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("taskId", tskTask.getTaskId());
            map.put("executeUserId", tskTask.getExecuteUserId());
            Integer count=tskTaskTargetService.getExcUserCustomerCount(map);
            out.print(count);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("getExcUserCusCount action error:" + e.getMessage());
        }
    }
    
    /**
     * 验证任务是否已中止
     */
    public void isStopTask(){
        try {
            PrintWriter out = getResponse().getWriter();
            Integer count=tskTaskService.isStop(tskTask.getTaskId());
            out.print(count);
        } catch (Exception e) {}
    }

    public DeptService getDeptService() {
        return deptService;
    }

    public void setDeptService(DeptService deptService) {
        this.deptService = deptService;
    }

    public SysUserService getSysUserService() {
        return sysUserService;
    }

    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    public JSONArray getDeptJson() {
        return deptJson;
    }

    public void setDeptJson(JSONArray deptJson) {
        this.deptJson = deptJson;
    }

    public PageUtil<DeptUserBean> getDeptUserList() {
        return deptUserList;
    }

    public void setDeptUserList(PageUtil<DeptUserBean> deptUserList) {
        this.deptUserList = deptUserList;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public TskTaskService getTskTaskService() {
        return tskTaskService;
    }

    public void setTskTaskService(TskTaskService tskTaskService) {
        this.tskTaskService = tskTaskService;
    }

    public TskTask getTskTask() {
        return tskTask;
    }

    public void setTskTask(TskTask tskTask) {
        this.tskTask = tskTask;
    }

    public String getExcTypes() {
        return excTypes;
    }

    public void setExcTypes(String excTypes) {
        this.excTypes = excTypes;
    }

    public List<SysUser> getUserList() {
        return userList;
    }

    public void setUserList(List<SysUser> userList) {
        this.userList = userList;
    }

    public String getExcUsersIds() {
        return excUsersIds;
    }

    public void setExcUsersIds(String excUsersIds) {
        this.excUsersIds = excUsersIds;
    }

    public TskTaskTargetService getTskTaskTargetService() {
        return tskTaskTargetService;
    }

    public void setTskTaskTargetService(TskTaskTargetService tskTaskTargetService) {
        this.tskTaskTargetService = tskTaskTargetService;
    }

    public Integer getTreeType() {
        return treeType;
    }

    public void setTreeType(Integer treeType) {
        this.treeType = treeType;
    }

    public DeptFacadeService getDeptFacadeService() {
        return deptFacadeService;
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }
}
