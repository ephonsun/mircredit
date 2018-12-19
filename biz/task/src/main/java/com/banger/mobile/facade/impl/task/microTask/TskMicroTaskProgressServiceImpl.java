/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2013-1-28
 */
package com.banger.mobile.facade.impl.task.microTask;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.microTask.TaskProgress;
import com.banger.mobile.domain.model.microTask.TskMicroTask;
import com.banger.mobile.domain.model.microTask.TskMicroTaskExecute;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.loan.LnLoanService;
import com.banger.mobile.facade.microTask.TskMicroTaskExecuteService;
import com.banger.mobile.facade.microTask.TskMicroTaskProgressService;
import com.banger.mobile.facade.microTask.TskMicroTaskService;
import com.banger.mobile.facade.microTask.TskMicroTaskTargetService;
import com.banger.mobile.util.StringUtil;

/**
 * @author yuanme
 * @version $Id: TskMicroTaskProgressServiceImpl.java,v 0.1 2013-1-28 下午1:16:18 Administrator Exp $
 */
public class TskMicroTaskProgressServiceImpl implements TskMicroTaskProgressService {

    private TskMicroTaskExecuteService tskMicroTaskExecuteService;
    private TskMicroTaskService tskMicroTaskService;
    private LnLoanService lnLoanService;
    private TskMicroTaskTargetService tskMicroTaskTargetService;
    private DeptFacadeService deptFacadeService;

    public void setTskMicroTaskExecuteService(TskMicroTaskExecuteService tskMicroTaskExecuteService) {
        this.tskMicroTaskExecuteService = tskMicroTaskExecuteService;
    }

    /**
     *
     * @param taskId
     * @param loanStatus
     * @param userId
     * @return
     * @see com.banger.mobile.facade.microTask.TskMicroTaskProgressService#getLoanTaskCountByUser(java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    public TaskProgress getLoanTaskCountByUser(Integer taskId, Integer loanStatus, Integer userId) {
        TaskProgress p = new TaskProgress();
        p.setTaskTarget(0);
        p.setFinishCount(0);
        p.setNewCustomerCount(0);
        p.setOldCustomerCount(0);

        TskMicroTask tskMicroTask=  tskMicroTaskService.getTskMicroTaskById(taskId);
        Date taskStartDate = tskMicroTask.getStartDate();
        Date taskEndDate = tskMicroTask.getEndDate();

        Map<String, Object> conds = new HashMap<String, Object>();
        conds.put("taskId", taskId);
        conds.put("userId", userId);

        Map<String, Object> param = new HashMap<String, Object>();
        Integer finishCount =0;
        if(loanStatus==1){
            param.put("loanStatusId",2);
            param.put("applyUserId",userId);
            param.put("applyStartDate",taskStartDate);
            param.put("applyEndDate",taskEndDate);
            finishCount = lnLoanService.getLoanCountByTask(param);
        }
        else if(loanStatus==2){
            param.put("loanStatusId",3);
            param.put("assignUserId",userId);
            param.put("assignStartDate",taskStartDate);
            param.put("assignEndDate",taskEndDate);
            finishCount = lnLoanService.getLoanCountByTask(param);
        }else if(loanStatus==3){
            param.put("loanStatusId",4);
            param.put("submitUserId",userId);
            param.put("submitStartDate",taskStartDate);
            param.put("submitEndDate",taskEndDate);
            finishCount = lnLoanService.getLoanCountByTask(param);
        }else if(loanStatus==4){
            param.put("loanStatusId",5);
            param.put("submitUserId",userId);
            param.put("approvePassStartDate",taskStartDate);
            param.put("approvePassEndDate",taskEndDate);
            finishCount = lnLoanService.getLoanCountByTask(param);
        }else{
            param.put("loanStatusId",6);
            param.put("submitUserId",userId);
            param.put("lendStartDate",taskStartDate);
            param.put("lendEndDate",taskEndDate);
            finishCount = lnLoanService.getLoanCountByTask(param);
        }

        param.put("isOldCustomer",1);
        Integer oldCustomerCount = lnLoanService.getLoanCountByTask(param);
        List<TskMicroTaskExecute> list = tskMicroTaskExecuteService.getAllTskMicroTaskExecuteByCondsProgress(conds);

        if(list.size() > 0){
            TskMicroTaskExecute e = list.get(0);

            p.setTaskTarget(e.getTargetUser());
            p.setFinishCount(finishCount);
            p.setNewCustomerCount(finishCount - oldCustomerCount);
            p.setOldCustomerCount(oldCustomerCount);
        }

        return p;
    }

    /**
     *
     * @param taskId
     * @param loanStatus
     * @param deptId
     * @return
     * @see com.banger.mobile.facade.microTask.TskMicroTaskProgressService#getLoanTaskCountByDept(java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    public TaskProgress getLoanTaskCountByDept(Integer taskId, Integer loanStatus, Integer deptId) {
        TaskProgress p = new TaskProgress();
        p.setTaskTarget(0);
        p.setFinishCount(0);
        p.setNewCustomerCount(0);
        p.setOldCustomerCount(0);

        TskMicroTask tskMicroTask=  tskMicroTaskService.getTskMicroTaskById(taskId);
        Date taskStartDate = tskMicroTask.getStartDate();      //任务开始时间
        Date taskEndDate = tskMicroTask.getEndDate();          //任务结束时间

        Map<String, Object> conds = new HashMap<String, Object>();
        conds.put("taskId", taskId);
        conds.put("deptId", deptId);

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("taskId", taskId);
        String deptIdsStr = deptFacadeService.getInChargeDeptIdsByDeptId(deptId);
        if(deptIdsStr ==null){
            paramMap.put("deptId",deptId);
        }else{
            String[] deptIds =deptIdsStr.split(",");
            Integer[] deptIdss=new Integer[deptIds.length];
            for(int i=0;i<deptIds.length;i++){
                deptIdss[i]=Integer.parseInt(deptIds[i]);

            }
            paramMap.put("deptIds",deptIdss);
        }

        List<TskMicroTaskExecute> userList =tskMicroTaskExecuteService.getSysUsersByTask(paramMap);     //查询贷款任务机构下的被分配的人员
        StringBuffer sb=new StringBuffer();
        for(TskMicroTaskExecute user:userList){
            if(sb.length()>1){
                sb.append(","+ user.getUserId());
            }else{
                sb.append(user.getUserId());
            }
        }
        String[] arr = sb.toString().split(",");
        Integer[] users = null;
        if (StringUtil.isNotEmpty(sb.toString())) {
            users=new Integer[arr.length];
            for (int i = 0; i < users.length; i++) {
                users[i] = Integer.parseInt(arr[i]);
            }
        }

        Map<String, Object> param = new HashMap<String, Object>();
        Integer finishCount =0;                 //任务已完成笔数
        if(loanStatus==1){                      //贷款状态为待分配
            param.put("loanStatusId",2);
            param.put("applyUserIds",users);
            param.put("applyStartDate",taskStartDate);
            param.put("applyEndDate",taskEndDate);
            finishCount = lnLoanService.getLoanCountByTask(param);
        }
        else if(loanStatus==2){                //贷款状态为待调查
            param.put("loanStatusId",3);
            param.put("assignUserIds",users);
            param.put("assignStartDate",taskStartDate);
            param.put("assignEndDate",taskEndDate);
            finishCount = lnLoanService.getLoanCountByTask(param);
        }else if(loanStatus==3){
            param.put("loanStatusId",4);       //贷款状态为待审批
            param.put("submitUserIds",users);
            param.put("submitStartDate",taskStartDate);
            param.put("submitEndDate",taskEndDate);
            finishCount = lnLoanService.getLoanCountByTask(param);
        }else if(loanStatus==4){
            param.put("loanStatusId",5);       //贷款状态为待放贷
            param.put("submitUserIds",users);
            param.put("approvePassStartDate",taskStartDate);
            param.put("approvePassEndDate",taskEndDate);
            finishCount = lnLoanService.getLoanCountByTask(param);
        }else{
            param.put("loanStatusId",6);       //贷款状态为已放贷(已完成)
            param.put("submitUserIds",users);
            param.put("lendStartDate",taskStartDate);
            param.put("lendEndDate",taskEndDate);
            finishCount = lnLoanService.getLoanCountByTask(param);
        }

        param.put("isOldCustomer",1);         //老客户
        Integer oldCustomerCount = lnLoanService.getLoanCountByTask(param);
        List<TskMicroTaskExecute> list = tskMicroTaskExecuteService.getAllTskMicroTaskExecuteByCondsProgress(conds);
        if(list.size() > 0){
            TskMicroTaskExecute e = list.get(0);

            p.setTaskTarget(e.getTargetDept());
            p.setFinishCount(finishCount);
            p.setNewCustomerCount(finishCount - oldCustomerCount);
            p.setOldCustomerCount(oldCustomerCount);
        }

        return p;
    }

    /**
     *
     * @param taskId
     * @param userId
     * @return
     * @see com.banger.mobile.facade.microTask.TskMicroTaskProgressService#getCompleteMarketTaskByUser(java.lang.Integer, java.lang.Integer)
     */
    public TaskProgress getCompleteMarketTaskByUser(Integer taskId, Integer userId) {
        TaskProgress p = new TaskProgress();
        p.setTaskTarget(0);
        p.setFinishCount(0);
        p.setNewCustomerCount(0);
        p.setOldCustomerCount(0);

        Map<String, Object> conds = new HashMap<String, Object>();
        conds.put("taskId", taskId);
        conds.put("userId", userId);

        List<TskMicroTaskExecute> list = tskMicroTaskExecuteService.getAllTskMicroTaskExecuteByCondsProgress(conds);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("taskId",taskId);
        param.put("userId",userId);
        param.put("isFinish",1);
        Integer finishCount = tskMicroTaskTargetService.getTargetListByPageCount(param);
        param.put("isOldCustomer",1);
        Integer oldCustomerCount = tskMicroTaskTargetService.getTargetListByPageCount(param);
        if(list.size() > 0){
            TskMicroTaskExecute e = list.get(0);

            p.setTaskTarget(e.getTargetUser());
            p.setFinishCount(finishCount);
            p.setNewCustomerCount(finishCount - oldCustomerCount);
            p.setOldCustomerCount(oldCustomerCount);
        }

        return p;
    }

    /**
     *
     * @param taskId
     * @param deptId
     * @return
     * @see com.banger.mobile.facade.microTask.TskMicroTaskProgressService#getCompleteMarketTaskByDept(java.lang.Integer, java.lang.Integer)
     */
    public TaskProgress getCompleteMarketTaskByDept(Integer taskId, Integer deptId) {
        TaskProgress p = new TaskProgress();
        p.setTaskTarget(0);
        p.setFinishCount(0);
        p.setNewCustomerCount(0);
        p.setOldCustomerCount(0);

        Map<String, Object> conds = new HashMap<String, Object>();
        conds.put("taskId", taskId);
        conds.put("deptId",deptId);

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("taskId", taskId);
        String deptIdsStr = deptFacadeService.getInChargeDeptIdsByDeptId(deptId);
        if(deptIdsStr ==null){
            paramMap.put("deptId",deptId);
        }else{
            String[] deptIds =deptIdsStr.split(",");
            Integer[] deptIdss=new Integer[deptIds.length];
            for(int i=0;i<deptIds.length;i++){
                deptIdss[i]=Integer.parseInt(deptIds[i]);

            }
            paramMap.put("deptIds",deptIdss);
        }

        List<TskMicroTaskExecute> list = tskMicroTaskExecuteService.getAllTskMicroTaskExecuteByCondsProgress(conds);
        List<TskMicroTaskExecute> userList =tskMicroTaskExecuteService.getSysUsersByTask(paramMap);     //查询贷款任务机构下的被分配的人员
        StringBuffer sb=new StringBuffer();
        for(TskMicroTaskExecute user:userList){
            if(sb.length()>1){
                sb.append(","+ user.getUserId());
            }else{
                sb.append(user.getUserId());
            }
        }
        String[] arr = sb.toString().split(",");
        Integer[] users = null;
        if (StringUtil.isNotEmpty(sb.toString())) {
            users=new Integer[arr.length];
            for (int i = 0; i < users.length; i++) {
                users[i] = Integer.parseInt(arr[i]);
            }
        }

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("taskId",taskId);
        param.put("userIds",users);
        param.put("isFinish",1);
        Integer finishCount = tskMicroTaskTargetService.getTargetListByPageCount(param);
        param.put("isOldCustomer",1);
        Integer oldCustomerCount = tskMicroTaskTargetService.getTargetListByPageCount(param);




        if(list.size() > 0){
            TskMicroTaskExecute e = list.get(0);

            p.setTaskTarget(e.getTargetDept());
            p.setFinishCount(finishCount);
            p.setNewCustomerCount(finishCount - oldCustomerCount);
            p.setOldCustomerCount(oldCustomerCount);
        }

        return p;
    }

    /**
     * 获取任务进度
     * @param userId
     * @return
     */
    public String getTaskScheduleByUserId(Integer userId){
        Map<String, Object> param = new HashMap<String, Object>();
        //查询用户当前执行的任务
        param.put("userId",userId);
        param.put("taskStatus",0);
        param.put("nowDate",new Date());
        param.put("now",0);
        List<TskMicroTask> nowTasks = tskMicroTaskService.getTaskByUserId(param);
        //查看用户超时未完成任务
        param.put("now",1);
        List<TskMicroTask> oldTasks = tskMicroTaskService.getTaskByUserId(param);


        StringBuffer  sb= new StringBuffer();
        int j=0;  //统计未完成任务笔数
        if(nowTasks.size() + oldTasks.size() <=3){    //如果当前未完成加上超时未完成任务数量不大于3条
            for(TskMicroTask nowTask:nowTasks){
                if(nowTask.getTaskType()==1){        //如果任务类型为贷款
                    Integer taskTarget=this.getLoanTaskCountByUser(nowTask.getTaskId(),5,userId).getTaskTarget();
                    Integer finishCount =this.getLoanTaskCountByUser(nowTask.getTaskId(),5,userId).getFinishCount();
                    if(sb.length()>0){
                        sb.append(",");
                    }
                    sb.append(nowTask.getTaskTitle()+":"+finishCount+"/"+taskTarget);
                }else{        //如果贷款任务为营销
                    Integer taskTarget=this.getCompleteMarketTaskByUser(nowTask.getTaskId(),userId).getTaskTarget();
                    Integer finishCount = this.getCompleteMarketTaskByUser(nowTask.getTaskId(),userId).getFinishCount();
                    if(sb.length()>0){
                        sb.append(",");
                    }
                    sb.append(nowTask.getTaskTitle()+":"+finishCount+"/"+taskTarget);
                }
            }
            for(TskMicroTask oldTask:oldTasks){
                if(oldTask.getTaskType()==1){        //如果任务类型为贷款
                    Integer taskTarget=this.getLoanTaskCountByUser(oldTask.getTaskId(),5,userId).getTaskTarget();
                    Integer finishCount =this.getLoanTaskCountByUser(oldTask.getTaskId(),5,userId).getFinishCount();
                    if(sb.length()>0){
                        sb.append(",");
                    }
                    sb.append(oldTask.getTaskTitle()+":"+finishCount+"/"+taskTarget);
                }else {
                    Integer taskTarget=this.getCompleteMarketTaskByUser(oldTask.getTaskId(),userId).getTaskTarget();
                    Integer finishCount = this.getCompleteMarketTaskByUser(oldTask.getTaskId(),userId).getFinishCount();
                    if(sb.length()>0){
                        sb.append(",");
                    }
                    sb.append(oldTask.getTaskTitle()+":"+finishCount+"/"+taskTarget);
                }
            }
        }else {  //如果当前未完成加上超时未完成任务数量大于3条
            for(TskMicroTask nowTask:nowTasks){
                if(j<3){
                    if(nowTask.getTaskType()==1){        //如果任务类型为贷款
                        Integer taskTarget=this.getLoanTaskCountByUser(nowTask.getTaskId(),5,userId).getTaskTarget();
                        Integer finishCount =this.getLoanTaskCountByUser(nowTask.getTaskId(),5,userId).getFinishCount();
                        if(sb.length()>0){
                            sb.append(",");
                        }
                        sb.append(nowTask.getTaskTitle()+":"+finishCount+"/"+taskTarget);
                        j++;
                    }else{
                        Integer taskTarget=this.getCompleteMarketTaskByUser(nowTask.getTaskId(),userId).getTaskTarget();
                        Integer finishCount = this.getCompleteMarketTaskByUser(nowTask.getTaskId(),userId).getFinishCount();
                        if(sb.length()>0){
                            sb.append(",");
                        }
                        sb.append(nowTask.getTaskTitle()+":"+finishCount+"/"+taskTarget);
                        j++;
                    }
                }
                else{
                    return sb.toString();
                }
            }
            for(TskMicroTask oldTask:oldTasks){
                if(j<3){
                    if(oldTask.getTaskType()==1){        //如果任务类型为贷款
                        Integer taskTarget=this.getLoanTaskCountByUser(oldTask.getTaskId(),5,userId).getTaskTarget();
                        Integer finishCount =this.getLoanTaskCountByUser(oldTask.getTaskId(),5,userId).getFinishCount();
                        if(sb.length()>0){
                            sb.append(",");
                        }
                        sb.append(oldTask.getTaskTitle()+":"+finishCount+"/"+taskTarget);
                        j++;
                    } else {
                        Integer taskTarget=this.getCompleteMarketTaskByUser(oldTask.getTaskId(),userId).getTaskTarget();
                        Integer finishCount = this.getCompleteMarketTaskByUser(oldTask.getTaskId(),userId).getFinishCount();
                        if(sb.length()>0){
                            sb.append(",");
                        }
                        sb.append(oldTask.getTaskTitle()+":"+finishCount+"/"+taskTarget);
                        j++;
                    }
                }
                else{
                    return sb.toString();
                }
            }
        }
        return sb.toString();
    }


    public TskMicroTaskService getTskMicroTaskService() {
        return tskMicroTaskService;
    }

    public void setTskMicroTaskService(TskMicroTaskService tskMicroTaskService) {
        this.tskMicroTaskService = tskMicroTaskService;
    }

    public LnLoanService getLnLoanService() {
        return lnLoanService;
    }

    public void setLnLoanService(LnLoanService lnLoanService) {
        this.lnLoanService = lnLoanService;
    }

    public TskMicroTaskTargetService getTskMicroTaskTargetService() {
        return tskMicroTaskTargetService;
    }

    public void setTskMicroTaskTargetService(TskMicroTaskTargetService tskMicroTaskTargetService) {
        this.tskMicroTaskTargetService = tskMicroTaskTargetService;
    }

    public DeptFacadeService getDeptFacadeService() {
        return deptFacadeService;
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }
}
