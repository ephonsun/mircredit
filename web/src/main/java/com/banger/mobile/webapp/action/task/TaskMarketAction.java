/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :营销任务Action
 * Author     :liyb
 * Create Date:2012-8-24
 */
package com.banger.mobile.webapp.action.task;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.Enum.task.EnumTask;
import com.banger.mobile.domain.model.base.customer.BaseCrmCustomer;
import com.banger.mobile.domain.model.product.ProductObj;
import com.banger.mobile.domain.model.system.CommProgress;
import com.banger.mobile.domain.model.task.AssignTaskBean;
import com.banger.mobile.domain.model.task.TaskTargetCustomer;
import com.banger.mobile.domain.model.task.TskTask;
import com.banger.mobile.domain.model.task.TskTaskTarget;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.product.ProductService;
import com.banger.mobile.facade.system.CommProgressService;
import com.banger.mobile.facade.task.TskTaskService;
import com.banger.mobile.facade.task.TskTaskTargetService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author liyb
 * @version $Id: TaskMarketAction.java,v 0.1 2012-8-24 上午10:09:21 liyb Exp $
 */
public class TaskMarketAction extends BaseAction {
    private static final long serialVersionUID = 5260604297439694121L;
    private TskTaskService    tskTaskService;                         //任务管理Service
    private TskTask           tskTask;                                //任务Bean
    private DeptFacadeService deptFacadeService;
    private Integer           roleType         = 0;                   //角色类型
    private ProductService    productService;                         //产品管理service
    private Integer           productId;
    private CommProgressService    commProgressService;               //沟通进度代码
    private List<CommProgress>     commProgressList;
    private TskTaskTargetService     tskTaskTargetService;            //任务目标客户service
    private PageUtil<TaskTargetCustomer> tskTargetCusPage;            //任务目标客户分页
    private CrmCustomerService     crmCustomerService;                //客户Service
    private Integer taskId;
    private String customerIds;
    private TskTaskTarget target;
    private List<AssignTaskBean>   assignTaskList;                    //分配任务List
    private SysUserService sysUserService;//用户service
    
    /**
     * 页面参数
     */
    private String customerMsg;
    private String managerId;
    private Integer commProssId;
    private String marketType;
    private Integer parentTaskStatus;
    
    private String jumpType;
    private String productName;
    
    private BigDecimal totalMoney;

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
    public Integer initLoginRole() {
        this.request.setAttribute("userLoginName", this.getLoginInfo().getUserName());
        this.request.setAttribute("userLoginUserId", getLoginInfo().getUserId());
        String[] roleName = getLoginInfo().getRoleNames();
        if (compareStr(EnumTask.BUSINESS_COMPETENT.getValue(), roleName) != 0
            || compareStr(EnumTask.ACCOUNT_ADMIN.getValue(), roleName) != 0
            || compareStr(EnumTask.ACCOUNT_AGENT.getValue(), roleName) != 0) {//业务主管、系统管理员、机构系统管理员
            roleType = 0;
        } else {
            roleType = getLoginInfo().getUserId();
        }
        return roleType;
    }
    
    /**
     * 初始化当前登录用户的子任务状态
     * @param task
     */
    public void initParentTskStatus(TskTask task) {
        task.setExecuteUserId(getLoginInfo().getUserId());
        parentTaskStatus = tskTaskService.getParentTskStatus(task);
        if (parentTaskStatus == null) {
            parentTaskStatus = 0;
        }
    }

    /**
     * 初始化产品信息
     * @return
     */
    public void initProductJsonMsg() {
        try {
            PrintWriter out = getResponse().getWriter();
            Map<String, Object> mapParam=new HashMap<String, Object>();
            if(!StringUtil.isBlank(productName)){
                mapParam.put("productName", productName.trim());
            }else{
                mapParam.put("orderby",0);
            }
            List<ProductObj> list = productService.getProductByName(mapParam);
            Map<String, Object> map = new HashMap<String, Object>();
            JSONArray productJson = new JSONArray();
            for (ProductObj obj : list) {
                map.clear();
                map.put("productId", obj.getProductId());//产品ID
                map.put("text", obj.getProductName());//产品名称
                map.put("raiseUpperLimit", obj.getRaiseUpperLimit());//募集上限
                map.put("moneyUnitName", obj.getMoneyUnitName());//募集上限单位
                productJson.add(map);
            }
            out.print(productJson);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("initProductJsonMsg action error:" + e.getMessage());
        }
    }
    
    /**
     * 初始化沟通进度
     */
    public void initCommProgress(){
        commProgressList=commProgressService.getCommProgressList();
    }

    /**
     * 根据产品ID查询产品的营销任务量
     */
    public void getByTaskProduct() {
        try {
            PrintWriter out = getResponse().getWriter();
            String count=tskTaskService.getByTaskProductCount(productId);
            out.print(count);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("getByTaskProduct action error:" + e.getMessage());
        }
    }

    /**
     * 添加营销任务
     * @return
     */
    public String saveTskTaskMarket() {
        try {
            if (tskTask == null) {
                return "toTaskMarketAdd";
            } else {
                Integer userId = getLoginInfo().getUserId();
                tskTask.setCreateUser(userId);
                tskTask.setAssignUserId(userId);
                tskTask.setExecuteDeptId(getLoginInfo().getDeptId());
                Integer taskId = tskTaskService.insertTaskMarket(tskTask);
                tskTask.setTaskId(taskId);

                Integer[] uids = deptFacadeService.getInChargeOfDeptUserIds();
                List<TskTask> tskList=new ArrayList<TskTask>();
                for (Integer executeUserId : uids) {
                    TskTask task = new TskTask();
                    task.setParentTaskId(taskId);
                    task.setCreateUser(userId);
                    task.setExecuteUserId(executeUserId);
                    SysUser user=sysUserService.getSysUserById(executeUserId);
                    task.setExecuteDeptId(user.getDeptId());
                    task.setCreateDate(new Date());
//                    tskTaskService.saveTaskExecuteUser(task);
                    tskList.add(task);
                }
                tskTaskService.insertTskTaskBatch(tskList);
                return SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("saveTskTaskMarket action error:" + e.getMessage());
            return ERROR;
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
                    userIds = ids + "-" + userIds;
                }
                userIds = userIds+getLoginInfo().getUserId().toString();
            }else userIds = getLoginInfo().getUserId().toString();
        } else {
            userIds = getLoginInfo().getUserId().toString();
        }
        return userIds+"-";
    }

    /**
     * 查询营销任务基本信息
     * @return
     */
    public String getByTaskMarket() {
        try {
            Integer rType=initLoginRole();
            initCommProgress();
            tskTask.setExecuteUserId(getLoginInfo().getUserId());
//            tskTask.setExcUsersIds(getExcUserIdsString());
            initParentTskStatus(tskTask);
            tskTask = tskTaskService.getTskTaskById(tskTask);
            totalMoney=tskTask.getTargetMoney();
            /************Start*************
             * 显示管辖的任务目标（如果是业务主管）
             * 显示本人的任务目标（如果是执行者）
             */
            Map<String,Object> map=new HashMap<String, Object>();
            if(rType==0){//业务主管,显示所管辖的目标
                map.put("executeDeptId", getLoginInfo().getDeptId());
                map.put("taskId", tskTask.getTaskId());
                if(getLoginInfo().getDeptId()!=3){//表示总行
                    Double money=tskTaskService.getTaskTargetMoney(map);
                    if(money==null){
                        tskTask.setTargetMoney(BigDecimal.valueOf(0));
                    }else
                        tskTask.setTargetMoney(BigDecimal.valueOf(money));
                }
                
                Double b=productService.querySumBuyCustomerByPid(tskTask.getProductId(), String.valueOf(getLoginInfo().getUserId()),"");
                request.setAttribute("finishMoney", b);
                if(tskTask.getUserFinishCount()!=null && tskTask.getUserFinishCount()>0){
                    tskTask.setUserFinishRate(String.format("%.2f", (b/tskTask.getUserFinishCount())*100.0));
                }else tskTask.setUserFinishCount(0d);
                
                //个人的任务完成度
                Double deptMoney=productService.querySumBuyCustomerByPid(tskTask.getProductId(), getUserIdsString(),"");
                tskTask.setFinishCount(deptMoney);
                if(tskTask.getTargetMoney()!=null && tskTask.getTargetMoney().compareTo(BigDecimal.ZERO)>0){
                    tskTask.setFinishRate(String.format("%.2f", (BigDecimal.valueOf(deptMoney).divide(tskTask.getTargetMoney(),4,BigDecimal.ROUND_HALF_UP).doubleValue())*100.0));
                }
                
            }else{//客户经理,显示自己的目标
                tskTask.setTargetMoney(BigDecimal.valueOf(tskTask.getUserFinishCount()));
                //个人的任务完成度
                Double b=productService.querySumBuyCustomerByPid(tskTask.getProductId(), String.valueOf(getLoginInfo().getUserId()),"");
                tskTask.setFinishCount(b);
                if(tskTask.getUserFinishCount()>0){
                    tskTask.setFinishRate(String.format("%.2f", (b/tskTask.getUserFinishCount())*100.0));
                }
            }
            
            /***********End ***************/
            if(marketType!=null&&marketType.equals("3")){//查看营销任务
                return "toMarketTskDetail";
            }else{
                if(tskTask.getAssignName().trim().equals(getLoginInfo().getUserName().trim())){
                    return SUCCESS;
                }else{
                    return "noAssignAuth";
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            log.error("getByTaskMarket action error:" + e.getMessage());
            return ERROR;
        }
    }
    
    /**
     * 制定电话任务
     * @return
     */
    public String phontTasks(){
        try {
            initLoginRole();
            initCommProgress();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("taskId", taskId);
            if(!StringUtil.isBlank(customerMsg)){
                map.put("customerMsg", StringUtil.ReplaceSQLChar(customerMsg));
            }
            if(!StringUtil.isBlank(managerId)){
                map.put("managerId", managerId);
            }
            if(commProssId!=null){
                map.put("commProssId", commProssId);
            }
            map.put("userIds", getUserIdsString());
            tskTargetCusPage = tskTaskTargetService.getTaskTargetCustomer(map, this.getPage());
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    
    /**
     * 获得登录用户所管理的部门用户的id集合
     * @return
     */
    public String getUserIdsString() {
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
     * 获得登录用户所管理的部门用户和部门的id集合
     * @return
     */
    public String getDeptUserIdsString(){
        Integer roleFlag=initLoginRole();
        String userIds="";
        String deptIds="";
        String deptUserIds="";
        if(roleFlag==0){//存在业务主管
            Integer[] uids=deptFacadeService.getInChargeOfDeptUserIds();
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
     * 获得登录用户所管理部门的id集合
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
            deptUserIds=deptIds.substring(0,deptIds.lastIndexOf(","));
        }else{
            deptUserIds=getLoginInfo().getUserId().toString()+","+getLoginInfo().getDeptId();
        }
        return deptUserIds;
    }
    
    /**
     * 分配任务
     */
    public String assignTaskMarket(){
        try {
            Map<String,Object> map=new HashMap<String, Object>();
            TskTask tsk=new TskTask();
            tsk.setExecuteUserId(getLoginInfo().getUserId());
            tsk.setTaskId(taskId);
            tskTask = tskTaskService.getTskTaskById(tsk);
            map.put("taskId", taskId);
            map.put("deptUserIds", getDeptUserIdsString());
            map.put("targetMoney", tskTask.getTargetMoney());
            assignTaskList=tskTaskService.getAssignTaskList(map);
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("assignTaskMarket action error:"+e.getMessage());
            return ERROR;
        }
    }
    
    /**
     * 添加电话任务目标客户
     * @return
     */
    public void savePhoneTaskCustomer(){
        try {
            if(customerIds!=null){
                String[] cusIds=customerIds.split(",");
                TskTaskTarget target=new TskTaskTarget();
                target.setCreateUser(getLoginInfo().getUserId());
                target.setTaskId(taskId);
                for (String customerId:cusIds) {
                    BaseCrmCustomer customer = crmCustomerService.getCrmCustomerById(Integer.parseInt(customerId));
                    if(customer.getBelongUserId()!=0){//判断是否是待分配的客户,如果是则不添加
                        target.setCustomerId(Integer.parseInt(customerId.trim()));
                        target.setDeptId(customer.getBelongDeptId());
                        Integer count=tskTaskTargetService.getByTaskCustomerCount(target);
                        if(count==0){
                            tskTaskTargetService.insertTskTaskTarget(target);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("savePhoneTaskCustomer action error:"+e.getMessage());
        }
    }
    
    /**
     * 修改沟通进度
     */
    public void changeCommProgress(){
        try {
            tskTaskTargetService.updateCommProgress(target);
        } catch (Exception e) {
            log.error("changeCommProgress action error:"+e.getMessage());
        }
    }
    
    /**
     * 编辑营销任务
     * @return
     */
    public String updateTaskMarket(){
        try {
            tskTask.setUpdateUser(getLoginInfo().getUserId());
            tskTaskService.updateTaskMarket(tskTask);
            if(jumpType.equals("toAdd")){
                return "toAddUpdate";
            }else{
                return SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("updateTaskMarket action error:"+e.getMessage());
            return ERROR;
        }
    }
    
    /**
     * 编辑任务目标额(针对客户经理)
     */
    public void updateTargetAccount(){
        try {
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("taskId", taskId);
            map.put("targetMoney", tskTask.getTargetMoney());
            map.put("taskStatus", tskTask.getTaskStatus());
            tskTaskService.updateTargetAmount(map);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("updateTargetAccount action error:"+e.getMessage());
        }
    }
    /**
     * 编辑任务目标额(针对机构)
     */
    public void updateTargetDept(){
        try {
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("taskId", taskId);
            map.put("executeDeptId", tskTask.getExecuteDeptId());
            map.put("deptTargetMoney", tskTask.getDeptTargetMoney());
            tskTaskService.updateTargetDept(map);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("updateTargetDept action error:"+e.getMessage());
        }
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

    public DeptFacadeService getDeptFacadeService() {
        return deptFacadeService;
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public CommProgressService getCommProgressService() {
        return commProgressService;
    }

    public void setCommProgressService(CommProgressService commProgressService) {
        this.commProgressService = commProgressService;
    }

    public List<CommProgress> getCommProgressList() {
        return commProgressList;
    }

    public void setCommProgressList(List<CommProgress> commProgressList) {
        this.commProgressList = commProgressList;
    }

    public TskTaskTargetService getTskTaskTargetService() {
        return tskTaskTargetService;
    }

    public void setTskTaskTargetService(TskTaskTargetService tskTaskTargetService) {
        this.tskTaskTargetService = tskTaskTargetService;
    }

    public PageUtil<TaskTargetCustomer> getTskTargetCusPage() {
        return tskTargetCusPage;
    }

    public void setTskTargetCusPage(PageUtil<TaskTargetCustomer> tskTargetCusPage) {
        this.tskTargetCusPage = tskTargetCusPage;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getCustomerIds() {
        return customerIds;
    }

    public void setCustomerIds(String customerIds) {
        this.customerIds = customerIds;
    }

    public CrmCustomerService getCrmCustomerService() {
        return crmCustomerService;
    }

    public void setCrmCustomerService(CrmCustomerService crmCustomerService) {
        this.crmCustomerService = crmCustomerService;
    }

    public String getCustomerMsg() {
        return customerMsg;
    }

    public void setCustomerMsg(String customerMsg) {
        this.customerMsg = customerMsg;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public Integer getCommProssId() {
        return commProssId;
    }

    public void setCommProssId(Integer commProssId) {
        this.commProssId = commProssId;
    }

    public TskTaskTarget getTarget() {
        return target;
    }

    public void setTarget(TskTaskTarget target) {
        this.target = target;
    }

    public String getJumpType() {
        return jumpType;
    }

    public void setJumpType(String jumpType) {
        this.jumpType = jumpType;
    }

    public String getMarketType() {
        return marketType;
    }

    public void setMarketType(String marketType) {
        this.marketType = marketType;
    }

    public Integer getParentTaskStatus() {
        return parentTaskStatus;
    }

    public void setParentTaskStatus(Integer parentTaskStatus) {
        this.parentTaskStatus = parentTaskStatus;
    }

    public List<AssignTaskBean> getAssignTaskList() {
        return assignTaskList;
    }

    public void setAssignTaskList(List<AssignTaskBean> assignTaskList) {
        this.assignTaskList = assignTaskList;
    }

    public SysUserService getSysUserService() {
        return sysUserService;
    }

    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }
}
