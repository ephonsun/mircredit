/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :任务管理Action
 * Author     :liyb
 * Create Date:2012-5-25
 */
package com.banger.mobile.webapp.action.task;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.Enum.task.EnumTask;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.system.CrmCustomerType;
import com.banger.mobile.domain.model.task.CrmTask;
import com.banger.mobile.domain.model.task.TaskCustomer;
import com.banger.mobile.domain.model.task.TaskTarget;
import com.banger.mobile.domain.model.user.SysUserBean;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.log.OpeventLogService;
import com.banger.mobile.facade.system.CrmCustomerTypeService;
import com.banger.mobile.facade.task.CrmTaskService;
import com.banger.mobile.facade.task.TaskTargetService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.util.DateUtil;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author liyb
 * @version $Id: CrmTaskAction.java,v 0.1 2012-5-25 上午11:25:00 liyb Exp $
 */
public class CrmTaskAction extends BaseAction {

    private static final long serialVersionUID = -4838327428927494718L;
    private CrmTaskService crmTaskService;//任务管理service
    private PageUtil<CrmTask> taskPage;//任务管理分页对象
    private CrmTask task;//任务管理Bean
    private Integer taskFlag;//任务查询标识     0:未完成任务  1:已完成任务  2:过期任务   3:所有任务
    private Map<Integer,String> isFinishMap=new HashMap<Integer,String>();
    private String startDate;//执行开始日期
    private String endDate;//执行开始日期
    private String customerName;//客户名称
    private SysUserService sysUserService;//用户service
    private List<SysUserBean> sysUserList;
    private Integer userId;//用户ID
    private String userName;//用户姓名
    private Integer submitType;//提交类型
    private Integer roleType=0;//角色类型
    
    private CrmCustomerService        crmCustomerService;   //客户service
    private CrmCustomerTypeService    crmCustomerTypeService;   //客户类型service
    private PageUtil<CrmCustomer>     customerList;//客户分页对象                          
    private CrmCustomer               crmCustomer;//客户Bean
    private List<CrmCustomerType>     cusTypelist;//客户类型List
    private String isUsed;//客户ID
    
    private TaskTargetService taskTargetService;//任务目标service
    private String belongToType;
    private DeptFacadeService         deptFacadeService;
    private Integer                   recordId;
    private String                    recordName;
    private OpeventLogService opeventLogService;//操作日志service
    private Map<String,Object> map=new HashMap<String, Object>();
    private TaskTarget target;//任务目标Bean
    private String isFinished;//联系完成情况
    private TaskCustomer taskCustomer;
    private String userNameFlag;
    private Integer tskTaskId;
    
    /**
     * 初始化任务完成状态
     */
    public void initIsFinishMap(){
        isFinishMap.put(0, "未完成");
        isFinishMap.put(1, "已完成");
    }
    
    /**
     * 初始化当前登陆人的下属机构人员
     */
    public void initCurrentAgentUser(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        try {
            PrintWriter out = response.getWriter();
            Integer[] userId=deptFacadeService.getInChargeOfDeptUserIds();
            String userIds="";
            for (int i = 0; i < userId.length; i++) {
                userIds+=userId[i]+",";
            }
            if(userIds!=null&&!userIds.equals("")){
                userIds=userIds.substring(0, userIds.length()-1);
            }
            sysUserList=sysUserService.getDeptBelongUserList(userIds);
            JSONArray array = new JSONArray();
            for (SysUserBean user : sysUserList) {
                JSONObject obj = new JSONObject();
                obj.put("userId", user.getUserId());
                obj.put("account", user.getAccount());
                obj.put("userName", user.getUserName());
                obj.put("deptName", user.getDeptName());
                obj.put("deptId", user.getDeptId());
                array.add(obj);
            }
            out.print(array);
        } catch (Exception e) {}
    }
    /**
     * 比较字符串
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
    public void initUserRole(){
        userNameFlag=getLoginInfo().getUserName();
        String[] roleName=getLoginInfo().getRoleNames();
        if (compareStr(EnumTask.BUSINESS_COMPETENT.getValue(), roleName) != 0 
                || compareStr(EnumTask.ACCOUNT_ADMIN.getValue(), roleName) != 0) {//业务主管、系统管理员
            roleType=0;
        }else{
            roleType=getLoginInfo().getUserId();
        }
    }
    
    /**
     * 任务管理列表
     * @return
     */
    public String showTaskPage(){
        try {
            initUserRole();
            initIsFinishMap();
            Map<String,Object> map=new HashMap<String, Object>();
            String inChargeOfUserIds="";
            Integer[] arr = deptFacadeService.getInChargeOfDeptUserIds(); //当前用户有权限的机构用户ids
            for(int i=0; i<arr.length; i++){
                if(i==0)
                    inChargeOfUserIds = String.valueOf(arr[i]);
                else
                    inChargeOfUserIds = inChargeOfUserIds + "," + arr[i];
            }
            map.put("deptUserIds", inChargeOfUserIds);
            if(taskFlag==3){//所有任务
                map.put("orderBy", " case when task.IS_FINISH=0 then task.EXECUTE_DATE end,case when task.IS_FINISH=1 then task.EXECUTE_DATE end desc");
                if(task!=null&&task.getIsFinish()!=null){
                    map.put("isFinish", task.getIsFinish());
                }
            }else{
                if (taskFlag!=2) {
                    map.put("isFinish", taskFlag);
                }else{//过期任务
                    map.put("expiredDate", DateUtil.getTimestampToString(new Timestamp(System.currentTimeMillis())));
                }
                if(taskFlag==1){//已完成按照执行日期降序排列
                    map.put("orderBy", " task.EXECUTE_DATE DESC");
                }else {//未完成和过期按照执行日期升序排列
                    map.put("orderBy", " task.EXECUTE_DATE ASC");
                }
            }
            if(task!=null){
                if(!StringUtil.isBlank(task.getTaskTitle())){
                    map.put("taskTitle", task.getTaskTitle().trim());
                }
                if(task.getAssignUser()!=null){
                    map.put("assignUser", getLoginInfo().getUserId());
                }
                if(task.getExecuteUser()!=null){
                    if(task.getExecuteUser()==1){//我的
                        map.put("executeUser", getLoginInfo().getUserId());
                    }else{//下属的
                        map.put("executeUser", userId);
                    }
                }
            }
            if(!StringUtil.isBlank(startDate)){
                map.put("startDate", startDate);
            }
            if(!StringUtil.isBlank(endDate)){
                map.put("endDate", endDate);
            }
            if(!StringUtil.isBlank(customerName)){
                map.put("customerName",customerName.trim());
            }
            taskPage=crmTaskService.getCrmTaskPage(map, this.getPage());
            return SUCCESS;
        } catch (Exception e) {
            log.error("showTaskPage action error:"+e.getMessage());
            return ERROR;
        }
    }
    
    /**
     * 添加任务
     * @return
     */
    public String saveTask(){
        try {
            if(submitType==0){//跳转新建任务页面
                initUserRole();
                return "toAddTask";
            }else{//保存
                task.setCreateUser(getLoginInfo().getUserId());
                if (task.getIsFinish()==null) {
                    task.setIsFinish(0);
                }
                task.setAssignUser(getLoginInfo().getUserId());
                task.setTaskType(0);//正常
                Integer taskId=crmTaskService.insertTask(task);
                if(taskId>0){
                    if(isUsed!=null&&!isUsed.equals("")){
                        TaskTarget target=null;
                        String[] customerId=isUsed.split(",");
                        for (int i = 0; i < customerId.length; i++) {
                            target=new TaskTarget();
                            target.setIsFinish(task.getIsFinish());
                            target.setCreateUser(getLoginInfo().getUserId());
                            target.setCustomerId(Integer.parseInt(customerId[i].trim()));
                            target.setTaskId(taskId);
                            taskTargetService.insertTaskTarget(target);
                        }
                    }
                    opeventLogService.addOpeventLog("任务管理", "新建任务："+task.getTaskTitle(), 1, "进行新建任务操作!");
                }
                return "save";
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("saveTask action error:"+e.getMessage());
            opeventLogService.addOpeventLog("任务管理", "新建任务："+task.getTaskTitle(), 0, "进行新建任务操作!");
            return ERROR;
        }
    }
    
    /**
     * 初始化客户类型
     * @return
     */
    public void initCustomerType(){
        cusTypelist = crmCustomerTypeService.getAllCrmCustomerType();
    }
    
    /**
     * 初始化需添加的客户
     * @return
     */
    public String initAddCustomer(){
        try {
            initCustomerType();
            String inChargeOfDeptIds = "";
            String inChargeOfUserIds = "";
            if(isUsed!=null&&!StringUtil.isBlank(isUsed)){
                map.put("isUsed", isUsed.substring(0, isUsed.length()-1));
            }
            if(crmCustomer!=null){
                if(crmCustomer.getCustomerTypeId()!=null)
                {
                    map.put("customerTypeId", crmCustomer.getCustomerTypeId());
                }
                if(!StringUtil.isEmpty(crmCustomer.getCustomerNo())){
                    map.put("customerNo", crmCustomer.getCustomerNo());
                }
                if(!StringUtil.isEmpty(crmCustomer.getCustomerName())){
                    map.put("customerName", crmCustomer.getCustomerName());
                }
            }
            map.put("isTrash", 0);
            //客户归属
            if(!StringUtil.isEmpty(belongToType)){
                Integer[] arr = deptFacadeService.getInChargeOfDeptIds(); //当前用户有权限的机构ids
                if(arr!=null){
                    for(int i=0; i<arr.length; i++){
                        if(i==0)
                            inChargeOfDeptIds = String.valueOf(arr[i]);
                        else
                            inChargeOfDeptIds = inChargeOfDeptIds + "," + arr[i];
                    }
                }
                map.put("BelongTo", belongToType);
                if(belongToType.equals("brAll")){//所有
                    inChargeOfUserIds = this.getLoginInfo().getUserId().toString();
                    if(!StringUtil.isEmpty(inChargeOfDeptIds)){
                        map.put("InChargeOfDeptIds", inChargeOfDeptIds);
                    }
                    map.put("InChargeOfUserIds", inChargeOfUserIds);
                }else if(belongToType.equals("brMine")){//我的
                    inChargeOfUserIds = this.getLoginInfo().getUserId().toString();
                    map.put("InChargeOfUserIds", inChargeOfUserIds);
                }else if(belongToType.equals("brUser")){//下属的
                    recordId = Integer.parseInt(request.getParameter("recordId"));
                    recordName = request.getParameter("recordName");
                    inChargeOfUserIds = request.getParameter("recordId");
                    map.put("InChargeOfUserIds", inChargeOfUserIds);
                }else if(belongToType.equals("brDept")){//机构的
                    String rid = request.getParameter("recordId");
                    if(!StringUtil.isEmpty(rid))
                    {
                        recordId = Integer.parseInt(rid);
                        recordName = request.getParameter("recordName");
                        inChargeOfDeptIds = request.getParameter("recordId");
                        map.put("InChargeOfDeptIds", inChargeOfDeptIds);
                    }
                    else
                    {
                        map.put("InChargeOfDeptIds", rid);
                        recordName = "";
                    }
                }else if(belongToType!=null&&belongToType.equals("brUnAllocate")){//待分配的
                    map.put("InChargeOfDeptIds", inChargeOfDeptIds);
                }
            }
            
            customerList = crmCustomerService.getCrmCustomerPage(map, this.getPage());
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("initAddCustomer action error:"+e.getMessage());
            return ERROR;
        }
    }
    
    /**
     * 提交添加的客户数据
     * @return
     */
    public void addCustomerMsg(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        try {
            PrintWriter out = response.getWriter();
            String[] customer=isUsed.split(",");
            JSONArray array = new JSONArray();
            List<TaskTarget> list=null;
            if (task!=null&&task.getTaskId()!=null) {
                list=taskTargetService.getTargetCustomerList(task.getTaskId());
            }
            Integer isFinish=0;
            String phone="";
            for (int i = 0; i < customer.length; i++) {
                CrmCustomer customerBase=(CrmCustomer) crmCustomerService.getCrmCustomerById(Integer.parseInt(customer[i]));
                if(customerBase!=null){
                    JSONObject obj = new JSONObject();
                    obj.put("customerId", customerBase.getCustomerId());//客户ID
                    if(customerBase.getDefaultPhoneType()==1){//固话
                        phone=(customerBase.getMobilePhone1()!=null)?customerBase.getMobilePhone1():"";
                    }else if(customerBase.getDefaultPhoneType()==2){//手机
                        phone=(customerBase.getPhone()!=null)?customerBase.getPhone():"";
                    }else if(customerBase.getDefaultPhoneType()==3){//传真
                        phone=(customerBase.getFax()!=null)?customerBase.getFax():"";
                    }
                    obj.put("mobilePhone", phone);//客户电话
                    obj.put("customerName", customerBase.getCustomerName());//客户姓名
                    obj.put("customerTypeName", customerBase.getCustomerTypeName());//客户类型
                    obj.put("deptName", customerBase.getDeptName());//归属机构
                    obj.put("userName", customerBase.getUserName());//客户经理
                    if (list!=null&&list.size()>0) {
                        for (int j = 0; j < list.size(); j++) {
                            TaskTarget target=list.get(j);
                            if (target.getCustomerId().equals(Integer.parseInt(customer[i]))) {
                                isFinish=target.getIsFinish();
                            }
                        }
                    }
                    obj.put("isFinish", isFinish);//完成情况
                    array.add(obj);
                }
            }
            out.print(array);
        } catch (Exception e) {e.printStackTrace();}
    }
    
    /**
     * 删除任务
     * @return
     */
    public String delTask(){
        try {
            Integer i=crmTaskService.deleteTask(task.getTaskId());
            if (i>0) {//任务删除成功，删除该任务关联的目标
                taskTargetService.deleteTaskTarget(task.getTaskId());
            }
            if (task.getTaskType()==0) {
                opeventLogService.addOpeventLog("任务管理", "删除任务", 1, "进行删除任务操作!");
            }else{
                opeventLogService.addOpeventLog("任务管理", "删除下次联系任务", 1, "进行删除下次联系任务操作!");
            }
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("delTask action error:"+e.getMessage());
            if (task.getTaskType()==0) {
                opeventLogService.addOpeventLog("任务管理", "删除任务", 0, "进行删除任务操作!");
            }else{
                opeventLogService.addOpeventLog("任务管理", "删除下次联系任务", 0, "进行删除下次联系任务操作!");
            }
            return ERROR;
        }
    }
    
    /**
     * 查看任务
     * @return
     */
    public String showTaskDetail(){
        try {
            List<TaskTarget> list=taskTargetService.getTargetCustomerList(task.getTaskId());
            isUsed="";
            for (int i = 0; i < list.size(); i++) {
                isUsed=list.get(i).getCustomerId()+","+isUsed;
            }
            task=crmTaskService.getCrmTaskById(task.getTaskId());
            if(task.getTaskType()==0){//正常任务
                return SUCCESS;
            }else{//下次联系任务
                return "nextTask";
            }
        } catch (Exception e) {
            log.error("showTaskDetail action error:"+e.getMessage());
            return ERROR;
        }
    }
    
    /**
     * 编辑任务
     * @return
     */
    public String updateTask(){
        try {
            if(submitType==0){//初始化需编辑的任务
                initUserRole();
                List<TaskTarget> list=taskTargetService.getTargetCustomerList(task.getTaskId());
                isUsed="";
                for (int i = 0; i < list.size(); i++) {
                    isUsed=list.get(i).getCustomerId()+","+isUsed;
                }
                task=crmTaskService.getCrmTaskById(task.getTaskId());
                if(roleType==0){//业务主管
                    if(!getLoginInfo().getUserId().equals(task.getAssignUser())&&getLoginInfo().getUserId().equals(task.getExecuteUser())){//分配者不是自己，执行者是自己
//                        return "onlyShow";
                        return "toUpdateByMy";
                    }else{//分配者是自己，可编辑
                        return "toUpdate";   
                    }
                }else{
                    if (!getLoginInfo().getUserId().equals(task.getAssignUser())&&getLoginInfo().getUserId().equals(task.getExecuteUser())) {//客户经理分配者不是自己并且执行者是自己(只能够点击完成情况与联系情况)
                        return "toUpdateByMy";
                    }else{
                        return "toUpdate";
                    }
                }
            }else{//保存任务编辑的内容
                task.setUpdateUser(getLoginInfo().getUserId());
                if (task.getIsFinish()==null) {
                    task.setIsFinish(0);
                }
                if(submitType==1){//客户经理是执行者保存,只编辑完成情况
                    crmTaskService.changeIsFinsish(task);
                }else if(submitType==2){//分配者是当前用户,保存修改信息
                    Integer taskId=crmTaskService.updateTask(task);
                    if(taskId>0){
                        TaskTarget tar=new TaskTarget();
                        tar.setTaskId(task.getTaskId());
                        Integer targetId=taskTargetService.deleteTaskTargetMsg(tar);
                        if(targetId>0){
                            TaskTarget target=null;
                            String[] customerId=isUsed.split(",");
                            String[] isfinish=isFinished.split(",");
                            for (int i = 0; i < customerId.length; i++) {
                                target=new TaskTarget();
                                target.setIsFinish(Integer.parseInt(isfinish[i].trim()));
                                target.setCreateUser(getLoginInfo().getUserId());
                                target.setCustomerId(Integer.parseInt(customerId[i].trim()));
                                target.setTaskId(task.getTaskId());
                                taskTargetService.insertTaskTarget(target);
                            }
                        }
                    }
                }
                return SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("updateTask action error:"+e.getMessage());
            return ERROR;
        }
    }
    
    /**
     * 更改任务目标的客户联系情况
     */
    public void changeTargetIsFinish(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        PrintWriter out=null;
        try {
            out = response.getWriter();
            target.setUpdateUser(getLoginInfo().getUserId());
            taskTargetService.updateIsFinish(target);
        }catch (Exception e) {
            e.printStackTrace();
            out.print(-1);
        }
    }
    
    /**
     * 编辑下次联系任务
     * @return
     */
    public String updateNextTask(){
        try {
            if (submitType==0) {
                taskCustomer=crmTaskService.getNextTask(task.getTaskId());
                return "toUpdateNext";
            }else{
                if (task.getIsFinish()==null) {
                    task.setIsFinish(0);
                }
                task.setUpdateUser(getLoginInfo().getUserId());
                crmTaskService.updateTask(task);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("updateNextTask action error:"+e.getMessage());
            return ERROR;
        }
    }
    
    /**
     * 查看下次任务
     * @return
     */
    public String showTaskDetailNext(){
        try {
            taskCustomer=crmTaskService.getNextTask(task.getTaskId());
            return SUCCESS;
        } catch (Exception e) {
            return ERROR;
        }
    }
    
    /**
     * 检查指定任务的联系客户是否都完成
     * @return
     */
    public void isCheckFinish(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        PrintWriter out=null;
        boolean flag=true;//已完成
        try {
            out = response.getWriter();
            List<TaskTarget> list=taskTargetService.getTargetCustomerList(task.getTaskId());
            for (int i = 0; i < list.size(); i++) {
                TaskTarget target=list.get(i);
                if(target.getIsFinish()==0){//存在未完成
                    flag=false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.print(flag);
    }
    
    /**
     * 返回客户对应的任务信息
     * @return
     */
    public String getCustomerTaskList(){
        try {
            initIsFinishMap();
            Map<String,Object> parameters=new HashMap<String, Object>();
            if (task!=null&&task.getIsFinish()!=null) {
                parameters.put("isFinish", task.getIsFinish());
            }
            parameters.put("customerId", target.getCustomerId());
            taskPage=crmTaskService.getCustomerTaskPage(parameters, this.getPage());
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    
    /**
     * 监控联系客户联系情况是否全部完成，如果全部完成任务状态自动改成完成，反之未完成
     * @return
     */
    public void isCheckTaskFinish(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        try {
            task.setUpdateUser(getLoginInfo().getUserId());
            crmTaskService.changeIsFinsish(task);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public CrmTaskService getCrmTaskService() {
        return crmTaskService;
    }
    public void setCrmTaskService(CrmTaskService crmTaskService) {
        this.crmTaskService = crmTaskService;
    }
    public PageUtil<CrmTask> getTaskPage() {
        return taskPage;
    }
    public void setTaskPage(PageUtil<CrmTask> taskPage) {
        this.taskPage = taskPage;
    }
    public CrmTask getTask() {
        return task;
    }
    public void setTask(CrmTask task) {
        this.task = task;
    }

    public Integer getTaskFlag() {
        return taskFlag;
    }

    public void setTaskFlag(Integer taskFlag) {
        this.taskFlag = taskFlag;
    }

    public Map<Integer, String> getIsFinishMap() {
        return isFinishMap;
    }

    public void setIsFinishMap(Map<Integer, String> isFinishMap) {
        this.isFinishMap = isFinishMap;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public SysUserService getSysUserService() {
        return sysUserService;
    }

    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    public List<SysUserBean> getSysUserList() {
        return sysUserList;
    }

    public void setSysUserList(List<SysUserBean> sysUserList) {
        this.sysUserList = sysUserList;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getSubmitType() {
        return submitType;
    }

    public void setSubmitType(Integer submitType) {
        this.submitType = submitType;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public CrmCustomerService getCrmCustomerService() {
        return crmCustomerService;
    }

    public void setCrmCustomerService(CrmCustomerService crmCustomerService) {
        this.crmCustomerService = crmCustomerService;
    }

    public CrmCustomerTypeService getCrmCustomerTypeService() {
        return crmCustomerTypeService;
    }

    public void setCrmCustomerTypeService(CrmCustomerTypeService crmCustomerTypeService) {
        this.crmCustomerTypeService = crmCustomerTypeService;
    }

    public PageUtil<CrmCustomer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(PageUtil<CrmCustomer> customerList) {
        this.customerList = customerList;
    }

    public CrmCustomer getCrmCustomer() {
        return crmCustomer;
    }

    public void setCrmCustomer(CrmCustomer crmCustomer) {
        this.crmCustomer = crmCustomer;
    }

    public List<CrmCustomerType> getCusTypelist() {
        return cusTypelist;
    }

    public void setCusTypelist(List<CrmCustomerType> cusTypelist) {
        this.cusTypelist = cusTypelist;
    }

    public String getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(String isUsed) {
        this.isUsed = isUsed;
    }

    public TaskTargetService getTaskTargetService() {
        return taskTargetService;
    }

    public void setTaskTargetService(TaskTargetService taskTargetService) {
        this.taskTargetService = taskTargetService;
    }

    public String getBelongToType() {
        return belongToType;
    }

    public void setBelongToType(String belongToType) {
        this.belongToType = belongToType;
    }

    public DeptFacadeService getDeptFacadeService() {
        return deptFacadeService;
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    public OpeventLogService getOpeventLogService() {
        return opeventLogService;
    }

    public void setOpeventLogService(OpeventLogService opeventLogService) {
        this.opeventLogService = opeventLogService;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public TaskTarget getTarget() {
        return target;
    }

    public void setTarget(TaskTarget target) {
        this.target = target;
    }

    public String getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(String isFinished) {
        this.isFinished = isFinished;
    }

    public TaskCustomer getTaskCustomer() {
        return taskCustomer;
    }

    public void setTaskCustomer(TaskCustomer taskCustomer) {
        this.taskCustomer = taskCustomer;
    }

    public String getUserNameFlag() {
        return userNameFlag;
    }

    public void setUserNameFlag(String userNameFlag) {
        this.userNameFlag = userNameFlag;
    }

    public Integer getTskTaskId() {
        return tskTaskId;
    }

    public void setTskTaskId(Integer tskTaskId) {
        this.tskTaskId = tskTaskId;
    }
}
