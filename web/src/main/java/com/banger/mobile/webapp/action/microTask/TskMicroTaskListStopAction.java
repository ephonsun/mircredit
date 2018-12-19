/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :QianJie
 * Create Date:Mar 2, 2013
 */
package com.banger.mobile.webapp.action.microTask;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.banger.mobile.facade.specialDataAuth.SpecialDataAuthService;
import org.apache.commons.lang.StringUtils;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.microTask.TskMicroTask;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.microTask.TskMicroTaskService;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author QianJie
 * @version $Id: TskMicroTaskAction.java,v 0.1 Mar 2, 2013 12:01:56 PM QianJie Exp $
 */
public class TskMicroTaskListStopAction extends BaseAction {

    private static final long   serialVersionUID = -3029151366017359601L;

    private TskMicroTaskService tskMicroTaskService;
    private DeptFacadeService   deptFacadeService;

    private SpecialDataAuthService specialDataAuthService;  //特殊数据权限

    public void setSpecialDataAuthService(SpecialDataAuthService specialDataAuthService) {
        this.specialDataAuthService = specialDataAuthService;
    }

    //查询条件
    private String              taskTitle;
    private Integer             taskType;
    private Date                startDate;
    private Date                endDate;
    private Integer             taskStatus;
    private Integer             isOutDate;
    private String              assignUserType;
    private Integer             loginUserId;
    private String assignUserIds;
    
    private Integer isInChargeOf;
    
    /**
     * 中止的任务
     * @return
     */
    public String allTaskListStop() {
        try {
            loginUserId = this.getLoginInfo().getUserId();
            int userType=3;
            String inChargeUserIds = "", inChargeUserIdsMark = "", inChargeDepts = "";
            
            isInChargeOf = 0;
            //客户经理
            inChargeUserIds = String.valueOf(loginUserId);
            inChargeUserIdsMark = "[" + loginUserId + "]";
            inChargeDepts = "0";
            userType=3;
            
            /*
            //判断是否是业务主管
            if (deptFacadeService.isInChargeOfDepartment()) {
                isInChargeOf = 1;
                String roleIds=StringUtil.getIdsString(getLoginInfo().getRoles());
                boolean flag=specialDataAuthService.getSysDataAuthCondition(roleIds,"taskInfo");
                if(flag){
                    inChargeDepts="-1";
                }else{
                    Integer[] inChargeDeptsIntegers = deptFacadeService
                            .getInChargeOfDeptIds();
                    for (Integer deptId : inChargeDeptsIntegers) {
                        inChargeDepts += deptId.intValue() + ","; // 负责的机构
                    }
                    if (inChargeDepts.length() > 0) {
                        inChargeDepts = inChargeDepts.substring(0,
                                (inChargeDepts.length() - 1));
                    }
                }

                String inChargeUser = deptFacadeService.getUserIdsBelongToManager(getLoginInfo().getRoles(),"taskInfo");
                String[] array=  inChargeUser.split(",");
                inChargeUserIds=inChargeUser;
                for (String userId : array) {
                    inChargeUserIdsMark += "[" + userId + "],";
                }
                if (inChargeUserIds.length() > 0) {
                    inChargeUserIdsMark = inChargeUserIdsMark.substring(0,
                            (inChargeUserIdsMark.length() - 1));
                }
                userType=2;
            } else {
                isInChargeOf = 0;
                //客户经理
                inChargeUserIds = String.valueOf(loginUserId);
                inChargeUserIdsMark = "[" + loginUserId + "]";
                inChargeDepts = "0";
                userType=3;
            }  
			*/
            Map<String, Object> conds = new HashMap<String, Object>();
            conds.put("userId", this.getLoginInfo().getUserId());
            conds.put("inChargeUserIds", inChargeUserIds);
            conds.put("inChargeUserIdsMark", inChargeUserIdsMark);
            conds.put("inChargeDepts", inChargeDepts);
            conds.put("userType", userType);//用户类型
            conds.put("taskStatus", 2);//查询已中止

            PageUtil<TskMicroTask> dataList = tskMicroTaskService.getTaskListPage(conds,
                this.getPage());

            request.setAttribute("dataList", dataList.getItems());
            request.setAttribute("recordCount", dataList.getPage().getTotalRowsAmount());
            return SUCCESS;
        } catch (Exception e) {
            log.error("allTaskListStop", e);
            return ERROR;
        }
    }

    /**
     * 中止的任务 查询
     * @return
     */
    public String allTaskListStopQuery() {
        try {
            loginUserId = this.getLoginInfo().getUserId();
            int userType=3;
            String inChargeUserIds = "", inChargeUserIdsMark = "", inChargeDepts = "";
            
            //客户经理
            inChargeUserIds = String.valueOf(loginUserId);
            inChargeUserIdsMark = "[" + loginUserId + "]";
            inChargeDepts = "0";
            userType=3;
            
            /*
            //判断是否是业务主管
            if (deptFacadeService.isInChargeOfDepartment()) {
                String roleIds=StringUtil.getIdsString(getLoginInfo().getRoles());
                boolean flag=specialDataAuthService.getSysDataAuthCondition(roleIds,"taskInfo");
                if(flag){
                    inChargeDepts="-1";
                }else{
                    Integer[] inChargeDeptsIntegers = deptFacadeService
                            .getInChargeOfDeptIds();
                    for (Integer deptId : inChargeDeptsIntegers) {
                        inChargeDepts += deptId.intValue() + ","; // 负责的机构
                    }
                    if (inChargeDepts.length() > 0) {
                        inChargeDepts = inChargeDepts.substring(0,
                                (inChargeDepts.length() - 1));
                    }
                }

                String inChargeUser = deptFacadeService.getUserIdsBelongToManager(getLoginInfo().getRoles(),"taskInfo");
                String[] array=  inChargeUser.split(",");
                inChargeUserIds=inChargeUser;
                for (String userId : array) {
                    inChargeUserIdsMark += "[" + userId + "],";
                }
                if (inChargeUserIds.length() > 0) {
                    inChargeUserIdsMark = inChargeUserIdsMark.substring(0,
                            (inChargeUserIdsMark.length() - 1));
                }
                 userType=2;
            } else {
                //客户经理
                inChargeUserIds = String.valueOf(loginUserId);
                inChargeUserIdsMark = "[" + loginUserId + "]";
                inChargeDepts = "0";
                userType=3;
            }  
			*/
            Map<String, Object> conds = new HashMap<String, Object>();
            conds.put("userId", this.getLoginInfo().getUserId());
            conds.put("inChargeUserIds", inChargeUserIds);
            conds.put("inChargeUserIdsMark", inChargeUserIdsMark);
            conds.put("inChargeDepts", inChargeDepts);

            //页面查询条件
            conds.put("taskTitle", taskTitle);
            conds.put("taskType", taskType);
            conds.put("startDate", startDate);
            conds.put("endDate", endDate);
            conds.put("taskStatus", 2);
            conds.put("isOutDate", isOutDate);

            if (StringUtils.isNotEmpty(assignUserType) && "my".equals(assignUserType)) {
                conds.put("assignUserId", this.getLoginInfo().getUserId());
            }else if(StringUtils.isNotEmpty(assignUserType)
                    && "brUser".equals(assignUserType)){
                conds.put("assignUserId", assignUserIds);
            }
            conds.put("userType", userType);//用户类型
            PageUtil<TskMicroTask> dataList = tskMicroTaskService.getTaskListPage(conds,
                this.getPage());

            request.setAttribute("dataList", dataList.getItems());
            request.setAttribute("recordCount", dataList.getPage().getTotalRowsAmount());
            return SUCCESS;
        } catch (Exception e) {
            log.error("allTaskListQuery", e);
            return ERROR;
        }
    }
    
    
    //重启任务
    public void restartTask() {
        String taskId = request.getParameter("taskId");
        try {
            if (!StringUtil.isEmpty(taskId)) {
                tskMicroTaskService.restartTskMicroTask(Integer.valueOf(taskId));
            }
        } catch (Exception e) {
            log.error("restartTask", e);
        }
    }

    /* getter setter */
    public DeptFacadeService getDeptFacadeService() {
        return deptFacadeService;
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }

    public TskMicroTaskService getTskMicroTaskService() {
        return tskMicroTaskService;
    }

    public void setTskMicroTaskService(TskMicroTaskService tskMicroTaskService) {
        this.tskMicroTaskService = tskMicroTaskService;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Integer getIsOutDate() {
        return isOutDate;
    }

    public void setIsOutDate(Integer isOutDate) {
        this.isOutDate = isOutDate;
    }

    public String getAssignUserType() {
        return assignUserType;
    }

    public void setAssignUserType(String assignUserType) {
        this.assignUserType = assignUserType;
    }
    
    public Integer getLoginUserId() {
        return loginUserId;
    }

    public void setLoginUserId(Integer loginUserId) {
        this.loginUserId = loginUserId;
    }

    public String getAssignUserIds() {
        return assignUserIds;
    }

    public void setAssignUserIds(String assignUserIds) {
        this.assignUserIds = assignUserIds;
    }

    public Integer getIsInChargeOf() {
        return isInChargeOf;
    }

    public void setIsInChargeOf(Integer isInChargeOf) {
        this.isInChargeOf = isInChargeOf;
    }
}
