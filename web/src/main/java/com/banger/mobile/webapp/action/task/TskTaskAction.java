/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :营销任务管理Action
 * Author     :liyb
 * Create Date:2012-7-16
 */
package com.banger.mobile.webapp.action.task;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.Enum.task.EnumTask;
import com.banger.mobile.domain.model.base.customer.BaseCrmCustomer;
import com.banger.mobile.domain.model.task.TaskTargetCustomer;
import com.banger.mobile.domain.model.task.TskTask;
import com.banger.mobile.domain.model.task.TskTaskAttachment;
import com.banger.mobile.domain.model.task.TskTaskTarget;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.param.SysParamService;
import com.banger.mobile.facade.task.TskTaskAttachmentService;
import com.banger.mobile.facade.task.TskTaskService;
import com.banger.mobile.facade.task.TskTaskTargetService;
import com.banger.mobile.util.DateUtil;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author liyb
 * @version $Id: TskTaskAction.java,v 0.1 2012-7-16 上午10:29:53 liyb Exp $
 */
public class TskTaskAction extends BaseAction {

    private static final long            serialVersionUID = 3890932978939020057L;
    private TskTaskService               tskTaskService;                                         //任务管理Service
    private TskTaskAttachmentService     attachmentService;                                      //任务附件service
    private TskTaskTargetService         tskTaskTargetService;                                   //任务目标客户service
    private PageUtil<TaskTargetCustomer> tskTargetCusPage;                                       //任务目标客户分页
    private TaskTargetCustomer           targetCustomer;
    private List<TskTaskAttachment>      attrList;                                               //任务附件List
    private TskTaskAttachment            taskAttr;                                               //任务附件Bean
    private PageUtil<TskTask>            tskTaskPage;                                            //任务管理分页
    private TskTask                      tskTask;                                                //任务管理Bean
    private Map<Integer, String>         taskTypeMap      = new LinkedHashMap<Integer, String>(); //任务类型Map
    private String                       startDate;                                              //执行开始日期
    private String                       endDate;                                                //执行开始日期
    private TskTaskTarget                target;                                                 //任务目标客户Bean
    private CrmCustomerService           crmCustomerService;                                     //客户Service
    private Integer                      roleType         = 0;                                   //角色类型
    private Integer                      parentTaskStatus = 0;                                   //子任务状态
    private DeptFacadeService            deptFacadeService;
    private SysParamService              sysParamService;
    private Map<Integer, String>         taskSituMap      = new LinkedHashMap<Integer, String>(); //任务情况Map
    private Integer                      taskSituId;

    /**
     * 附件上传参数
     */
    private static final int             BUFFERED_SIZE    = 4 * 1024;
    private File                         fileInput;
    private String                       fileInputFileName;
    private String[]                     fileNameOld;
    private String[]                     fileNameTask;
    private String[]                     fileSize;

    /**
     * 页面参数
     */
    private String                       belongTo;                                                //归属
    private String                       excUserIds;                                              //执行者ID数组
    private String                       customerIds;                                             //客户编号数组
    private Integer                      excUserTaskCusCount;                                     //当前执行者任务联系客户数
    private String                       treeType;
    private Integer                      treeId;
    private String                       line;//1:有 0:无
    
    private Integer customerId;
    private Integer taskId;
    private Integer customerCount;
    
    /**
     * 刷新参数
     */
    private Integer refreshAssignId;//分配者
    private Integer refreshTaskType;//任务类型
    private String refreshStartDate;//执行开始时间
    private String refreshEndDate;//执行结束时间
    private String refreshTaskTitle;//任务标题
    private String refreshProductName;//产品名称
    private String refreshRemark;//备注
    
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
        this.request.setAttribute("userLoginDeptId", getLoginInfo().getDeptId());
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
     * 初始化任务情况
     */
    public void initTaskSituation() {
        taskSituMap.put(0, EnumTask.TASK_UNfINISH.getValue());
        taskSituMap.put(2, EnumTask.TASK_TODAY.getValue());
        taskSituMap.put(3, EnumTask.TASK_THREEDAY.getValue());
        taskSituMap.put(4, EnumTask.TASK_ONEWEEK.getValue());
        taskSituMap.put(5, EnumTask.TASK_EXPIRED.getValue());
        taskSituMap.put(6, EnumTask.TASK_FINFISH.getValue());
        taskSituMap.put(7, EnumTask.TASK_STOP.getValue());
    }

    /**
     * 初始化任务类型
     */
    public void initTaskType() {
        taskTypeMap.put(1, "联系任务");
        taskTypeMap.put(2, "营销任务");
    }

    /**
     * 客户管理->客户对应的任务
     * @return
     */
    public String customerTaskCard() {
        try {
            initTaskSituation();
            findTskTaskMsg(taskSituId);
            return SUCCESS;
        } catch (Exception e) {
            log.error("customerTaskCard action error:" + e.getMessage());
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 所有未完成的任务
     * @param taskFlag
     */
    public String tskTaskUnFinishPage() {
        try {
            findTskTaskMsg(0);
            return SUCCESS;
        } catch (Exception e) {
            log.error("tskTaskUnFinishPage action error:" + e.getMessage());
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 我执行的任务
     * @return
     */
    public String showMyExecuteTaskPage() {
        try {
            findTskTaskMsg(1);
            return SUCCESS;
        } catch (Exception e) {
            log.error("showMyExecuteTaskPage action error:" + e.getMessage());
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 今天未完成的任务
     * @return
     */
    public String showTodayUnfinishedTask() {
        try {
            findTskTaskMsg(2);
            return SUCCESS;
        } catch (Exception e) {
            log.error("showMyExecuteTaskPage action error:" + e.getMessage());
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 已完成的任务
     * @return
     */
    public String tskTaskCompletePage() {
        try {
            findTskTaskMsg(6);
            return SUCCESS;
        } catch (Exception e) {
            log.error("tskTaskCompletePage action error:" + e.getMessage());
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 已中止的任务
     * @return
     */
    public String tskTaskStopPage() {
        try {
            findTskTaskMsg(7);
            return SUCCESS;
        } catch (Exception e) {
            log.error("tskTaskCompletePage action error:" + e.getMessage());
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 过期的任务
     * @return
     */
    public String tskTaskExpiredPage() {
        try {
            findTskTaskMsg(5);
            return SUCCESS;
        } catch (Exception e) {
            log.error("tskTaskExpiredPage action error:" + e.getMessage());
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 三天内未完成的任务
     * @return
     */
    public String taskThreeDayUnfinishPage() {
        try {
            findTskTaskMsg(3);
            return SUCCESS;
        } catch (Exception e) {
            log.error("taskThreeDayUnfinishPage action error:" + e.getMessage());
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 一周内未完成的任务
     * @return
     */
    public String taskOneWeekUnfinishPage() {
        try {
            findTskTaskMsg(4);
            return SUCCESS;
        } catch (Exception e) {
            log.error("taskOneWeekUnfinishPage action error:" + e.getMessage());
            e.printStackTrace();
            return ERROR;
        }
    }
    
    /**
     * 托盘待分配营销任务
     * @return
     */
    public String taskUnallocatedMarket(){
        try {
            findTskTaskMsg(100);
            return SUCCESS;
        } catch (Exception e) {
            log.error("taskUnallocatedMarket action error:" + e.getMessage());
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 查询任务列表
     * @param taskFlag
     */
    public void findTskTaskMsg(Integer taskFlag) {
        initLoginRole();
        initTaskType();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("loginUserId", getLoginInfo().getUserId());
        if (tskTask != null) {
            if (!StringUtil.isBlank(tskTask.getTaskTitle())) {
                map.put("taskTitle", StringUtil.ReplaceSQLChar(tskTask.getTaskTitle().trim()));
                refreshTaskTitle=tskTask.getTaskTitle().trim();
            }
            if (tskTask.getAssignUserId() != null) {
                map.put("assignUserId", getLoginInfo().getUserId());
                refreshAssignId=getLoginInfo().getUserId();
            }
            if (tskTask.getTaskType() != null) {
                map.put("taskType", tskTask.getTaskType());
                refreshTaskType=tskTask.getTaskType();
            }
            if (!StringUtil.isBlank(tskTask.getRemark())) {
                map.put("remark", StringUtil.ReplaceSQLChar(tskTask.getRemark().trim()));
                refreshRemark=tskTask.getRemark().trim();
            }
            if(!StringUtil.isBlank(tskTask.getProductName())){
                map.put("productName", StringUtil.ReplaceSQLChar(tskTask.getProductName().trim()));
                refreshProductName=tskTask.getProductName().trim();
            }
        }
        //刷新时间参数
        if (!StringUtil.isBlank(startDate)) {
            refreshStartDate=startDate;
        }
        if (!StringUtil.isBlank(endDate)) {
            refreshEndDate=endDate;
        }
        
        /**
         * 执行日期搜索
         */
        String sql="";
        if (!StringUtil.isBlank(startDate)&&StringUtil.isBlank(endDate)) {
            sql=" and TO_DATE ('"+startDate+"', 'yyyy-MM-dd')<=START_DATE ";
            map.put("executeDateSearch", sql);
        }else if(StringUtil.isBlank(startDate)&&!StringUtil.isBlank(endDate)){
            sql=" and TO_DATE ('"+endDate+"', 'yyyy-MM-dd')>=START_DATE ";
            map.put("executeDateSearch", sql);
        }else if(!StringUtil.isBlank(startDate)&&!StringUtil.isBlank(endDate)){
            sql=" and (START_DATE<=TO_DATE ('"+endDate+"', 'yyyy-MM-dd') and END_DATE>=TO_DATE ('"+startDate+"', 'yyyy-MM-dd')) ";
            map.put("executeDateSearch", sql);
        }
        
        if (taskFlag == 0) {//未完成
            map.put("taskStatus", 0);
        } else if (taskFlag == 1) {//我执行的任务
            map.put("executeUserId", getLoginInfo().getUserId());
            map.put("taskStatus", 0);
            map.put("myExcTask", 0);
        } else if (taskFlag == 2) {//今天未完成的任务
            String date = DateUtil.getDateToString(new Date());
            map.put("toDayDate", date);
            map.put("taskStatus", 0);
            startDate = date;
            endDate = date;
        } else if (taskFlag == 6) {//已完成的任务
            map.put("compliteLoginUserId", getLoginInfo().getUserId());
        } else if (taskFlag == 7) {//已中止的任务
            map.put("taskStatus", 2);
        } else if (taskFlag == 5) {//过期未完成
            map.put("expiredEndDate", DateUtil.getDateToString(new Date()));
            map.put("taskStatus", 0);
        } else if (taskFlag == 3) {//三天内未完成的任务
            String date = DateUtil.getDateToString(new Date());
            String twoDay = getAfterDate(2);
            map.put("threeDayStart", date);
            map.put("threeDayEnd", twoDay);
            map.put("taskStatus", 0);
            startDate = date;
            endDate = twoDay;
        } else if (taskFlag == 4) {//一周内未完成的任务
            String start = DateUtil.getDateToString(new Date());
            String end = getAfterDate(6);
            map.put("oneWeekStart", start);
            map.put("oneWeekEnd", end);
            map.put("taskStatus", 0);
            startDate = start;
            endDate = end;
        }else if (taskFlag == 10) {//今天所有的任务(包括未完成、已完成、已中止)
            map.put("taskStatus", 0);
            String date = DateUtil.getDateToString(new Date());
            map.put("finsihEndDate", date);
            
            String twoDay = getAfterDate(2);
            map.put("threeDayStartWork", date);
            map.put("threeDayEndWork", twoDay);
            
            String end = getAfterDate(6);
            map.put("oneWeekStartWork", date);
            map.put("oneWeekEndWork", end);
        }else if(taskFlag == 100){//托盘待分配营销任务
            map.put("systemDeptId", getLoginDeptUserIds());
        }
        if (taskFlag == 6 && taskFlag == 7) {//已完成和已中止的按照执行结束日期降序排列
            map.put("orderBy", " T.END_DATE desc ");
        } else {
            /**
             * 首先显示过期的任务，按照执行结束日期升序排列
             * 然后销售未过期的任务，按照执行结束日期升序排列
             */
            //map.put("orderBy"," case when END_DATE<(select current date from (values 1) as DD) then END_DATE  else END_DATE end asc ");
            map.put("orderBy"," T.END_DATE asc ");
        }
        map.put("userIds", getUserIdsString(false));
        if (target != null) {
            map.put("customerId", target.getCustomerId());
        }
        if(roleType>0){//客户经理
            map.put("marketFlag", 1);
        }
        if(roleType==0&&getLoginInfo().getDeptId()!=3){//业务主管且不是总行
            map.put("markerZgFlag", 1);
        }
        tskTaskPage = tskTaskService.getTskTaskPage(map, this.getPage());
    }
    
    /**
     * @param days 天数
     * @description 返回输入日期n天后的日期
     */
    public String getAfterDate(int days)   
    {   
        Date date=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");   
        Calendar calendar = Calendar.getInstance();      
        calendar.setTime(date);   
        calendar.set(Calendar.DAY_OF_YEAR,calendar.get(Calendar.DAY_OF_YEAR) + days);   
        return df.format(calendar.getTime());   
    }

    /**
     * 根据当前登陆人的角色获取所查询的数据ID
     * @return
     */
    public String getUserIdsString(boolean bool) {
        Integer roleFlag = initLoginRole();
        String userIds = "";
        if (roleFlag == 0) {//存在业务主管
            Integer[] uids = deptFacadeService.getInChargeOfDeptUserIds(bool);
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
     * 新建联系任务
     */
    public String saveTskTaskContact() {
        try {
            if (tskTask == null) {//跳转新建联系任务界面
                initLoginRole();
                return "toContact";
            } else {//保存提交联系任务
                tskTask.setCreateUser(this.getLoginInfo().getUserId());
                tskTask.setAssignUserId(this.getLoginInfo().getUserId());
                tskTask.setIsNextContact(0);
                tskTask.setExecuteDeptId(getLoginInfo().getDeptId());
                tskTask.setIsDel(0);
                Integer taskId = tskTaskService.insertTskTaskContact(tskTask);//添加主任务
                if (taskId != null) {
                    tskTask.setTaskId(taskId);
                    //添加任务多个执行者
                    TskTask task = new TskTask();
                    task.setParentTaskId(taskId);
                    task.setExecuteDeptId(0);
                    task.setCreateUser(this.getLoginInfo().getUserId());
                    task.setCreateDate(new Date());
                    if (belongTo.equals("my")) {//我的
                        task.setExecuteUserId(getLoginInfo().getUserId());
                        tskTaskService.saveTaskExecuteUser(task);
                    } else {//下属
                        List<TskTask> tskList=new ArrayList<TskTask>();
                        String[] userIds = excUserIds.split(",");
                        for (String userId : userIds) {
                            TskTask taskParent = new TskTask();
                            taskParent.setParentTaskId(taskId);
                            taskParent.setExecuteDeptId(0);
                            taskParent.setCreateUser(this.getLoginInfo().getUserId());
                            taskParent.setCreateDate(new Date());
                            taskParent.setExecuteUserId(Integer.parseInt(userId));
//                            tskTaskService.saveTaskExecuteUser(task);
                            tskList.add(taskParent);
                        }
                        tskTaskService.insertTskTaskBatch(tskList);
                    }
                    saveTaskUploadFile(taskId);
                }
                return "toTaskTarget";
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("saveTskTaskContact action error:" + e.getMessage());
            return ERROR;
        }
    }

    /**
     * 插入任务附件
     * @param taskId
     */
    public void saveTaskUploadFile(Integer taskId) {
        if (fileNameTask != null) {
            TskTaskAttachment attr = new TskTaskAttachment();
            String savePath = sysParamService.getTaskAttachmentPath() + "/"
                              + DateUtil.convertDateToString("yyyyMMdd", new Date());
            attr.setFilePath(savePath);
            for (int i = 0; i < fileNameTask.length; i++) {
                attr.setTaskId(taskId);
                attr.setFileName(fileNameTask[i].trim());
                attr.setFileNameOld(fileNameOld[i].trim());
                attr.setFileSize(Long.parseLong(fileSize[i].trim()));
                attr.setCreateUser(getLoginInfo().getUserId());
                attachmentService.saveTaskAttachment(attr);
            }
        }
    }

    /**
     * 返回当前执行者任务联系客户数
     */
    public void initExcUserTaskCusCount(TskTask task) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("taskId", task.getTaskId());
        map.put("executeUserId", getLoginInfo().getUserId());
        excUserTaskCusCount = tskTaskTargetService.getExcUserCustomerCount(map);
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
     * 编辑联系任务
     * @return
     */
    public String updateTaskContact() {
        try {
            //修改基本数据
            tskTask.setUpdateUser(this.getLoginInfo().getUserId());
            tskTaskService.updateTskTaskContact(tskTask);
            /**
             * 处理执行者数据
             */
            if (belongTo.equals("sub")) {
                String[] excUser = excUserIds.split(",");
                for (String userId : excUser) {
                    tskTask.setParentTaskId(tskTask.getTaskId());
                    tskTask.setExecuteUserId(Integer.parseInt(userId));
                    Integer count = tskTaskService.searchTaskExecute(tskTask);
                    if (count == 0) {//数据不存在,插入任务执行者
                        //添加任务多个执行者
                        TskTask task = new TskTask();
                        task.setParentTaskId(tskTask.getTaskId());
                        task.setExecuteDeptId(0);
                        task.setCreateUser(this.getLoginInfo().getUserId());
                        task.setExecuteUserId(Integer.parseInt(userId));
                        tskTaskService.saveTaskExecuteUser(task);
                    }
                }
                //获得无权限范围的执行者ID
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("taskId", tskTask.getTaskId());
                param.put("excUserIds", getUserIdsString(false));
                String excUserId=tskTaskService.getNoPermissionUserIds(param);
                
                //删除不在此执行者ID数组中的子任务
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("parentTaskId", tskTask.getTaskId());
                if(excUserId!=null){
                    map.put("executeUserIds", excUserIds+","+excUserId);
                }else{
                    map.put("executeUserIds", excUserIds);
                }
                tskTaskService.deleteTaskByExecuteId(map);

            }
            //保存任务附件
            saveTaskUploadFile(tskTask.getTaskId());
            return SUCCESS;
        } catch (Exception e) {
            log.error("updateTaskContact action errot:" + e.getMessage());
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 初始化任务联系客户列表(任务添加)
     * @return
     */
    public String initContactCustomerList() {
        try {
            getContactCustomerList();
            return SUCCESS;
        } catch (Exception e) {
            return ERROR;
        }
    }

    /**
     * 联系客户列表
     * @return
     */
    public String getContactCustomerList() {
        try {
            Integer type=initLoginRole();
            String userIds=getUserIdsString(true);
            Map<String, Object> map = new HashMap<String, Object>();
            tskTask.setExecuteUserId(getLoginInfo().getUserId());
            tskTask.setExcUsersIds(userIds);
            tskTask = tskTaskService.getTskTaskById(tskTask);
            initExecuteUser(tskTask.getTaskId(), userIds);
            this.request.setAttribute("taskAssaginName", tskTask.getAssignName());
            if(type==0){//业务主管
                map.put("roleType", type);
            }
            map.put("taskId", tskTask.getTaskId());
            if (!StringUtil.isBlank(treeType)) {
                if (treeType.trim().equals("D")) {
                    map.put("treeType", treeId);
                } else {
                    map.put("treeId", treeId);
                }
            }
            this.getPage().setPageSize(7);
            map.put("userIdsContact", userIds);//数据范围
            map.put("taskDeptIds", getLoginDeptUserIds());
            customerCount=tskTaskTargetService.getTaskTargetCustomerCount(map);
            tskTargetCusPage = tskTaskTargetService.getTaskTargetCustomer(map, this.getPage());
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    /**
     * 返回当前登录用户所管辖的机构ID集合
     * @return
     */
    public String getLoginDeptUserIds(){
        Integer roleFlag = initLoginRole();
        String deptIds = "";
        if (roleFlag == 0) {//存在业务主管
            Integer[] dids = deptFacadeService.getInChargeOfDeptIds();
            if (dids != null) {
                for (Integer ids : dids) {
                    deptIds = ids + "," + deptIds;
                }
                deptIds = deptIds.substring(0, deptIds.lastIndexOf(","));
            }
        } else {
            deptIds = getLoginInfo().getDeptId().toString();
        }
        return deptIds;
    }

    /**
     * 上传文件
     */
    public void uploadFile() {
        HttpServletResponse response = null;
        String oldName="";
        try {
            if (fileInputFileName != null && !fileInputFileName.equals("")) {
                String savePath = sysParamService.getTaskAttachmentPath() + "/"
                                  + DateUtil.convertDateToString("yyyyMMdd", new Date());
                File f = new File(savePath);
                if (!f.exists()) {//文件不存在则创建
                    f.mkdirs();
                }
                oldName=String.valueOf(System.currentTimeMillis())+new Random().nextInt()+fileInputFileName.substring(fileInputFileName.lastIndexOf("."));
                //将文件上传到服务器
                File imageFile = new File(savePath + File.separator + oldName);
                copy(fileInput, imageFile);
                //返回成功信息
                response = ServletActionContext.getResponse();
                response.setCharacterEncoding("utf-8");
                response.getWriter().append(fileInputFileName+"||"+oldName);
            }
        } catch (Exception e) {
            try {
                response = ServletActionContext.getResponse();
                response.getWriter().println("");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
    /**
     * 拷贝文件
     * @param src
     * @param target
     */
    private static void copy(File src, File target) {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new BufferedInputStream(new FileInputStream(src), BUFFERED_SIZE);
            out = new BufferedOutputStream(new FileOutputStream(target), BUFFERED_SIZE);
            byte[] bs = new byte[BUFFERED_SIZE];
            int i;
            while ((i = in.read(bs)) > 0) {
                out.write(bs, 0, i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null)
                    in.close();
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 下载任务附件
     */
    public void downTaskFile() {
        BufferedInputStream br = null;
        BufferedOutputStream bos=null;
        try {
            taskAttr = attachmentService.getTaskAttrById(taskAttr.getAttachmentId());
            if (taskAttr != null) {
                String fileName = taskAttr.getFileName();
                String fileNameOld = taskAttr.getFileNameOld();
                File file = new File(taskAttr.getFilePath() + File.separator + fileNameOld);
                byte[] buf = new byte[2*1024];
                int len = 0;
                getResponse().reset();//必须加，不然保存不了临时文件     
                getResponse().setContentType("application/octet-stream");
                getResponse().setHeader("Content-Disposition",
                    "attachment; filename=" + new String(fileName.getBytes("gbk"), "iso8859-1"));
                getResponse().setCharacterEncoding("UTF-8");
                br = new BufferedInputStream(new FileInputStream(file));
                bos = new java.io.BufferedOutputStream(getResponse().getOutputStream());   
                while ((len = br.read(buf, 0, buf.length)) != -1) {
                    bos.write(buf, 0, len);
                    len++;
                }
                bos.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if (br != null) {  
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                br=null;
            }
            if(bos!=null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                bos=null;
            }
        }
    }

    /**
     * 加载任务附件
     */
    public void loadTaskAttr(Integer taskId) {
        attrList = attachmentService.getTaskByAttr(taskId);
    }

    /**
     * 移除附件
     */
    public void removeAttr() {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            Integer count = attachmentService.deleteTaskByAttr(taskAttr);
            out.print(count);
        } catch (Exception e) {
            e.printStackTrace();
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
     * 跳转编辑任务页面
     */
    public String toTskTaskUpdatePage() {
        try {
            initLoginRole();
            initExcUserTaskCusCount(tskTask);
            if (tskTask.getTaskType() == 1) {//联系任务
                String userIds=getUserIdsString(true);
                tskTask.setExecuteUserId(getLoginInfo().getUserId());
                tskTask.setExcUsersIds(userIds);
                tskTask = tskTaskService.getTskTaskById(tskTask);
                initExecuteUser(tskTask.getTaskId(),userIds);
                loadTaskAttr(tskTask.getTaskId());
                initParentTskStatus(tskTask);
                return "toTaskContact";
            } else {//营销任务

                return "toTaskMarket";
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("toTskTaskUpdatePage action error:" + e.getMessage());
            return ERROR;
        }
    }

    /**
     * 加载任务联系客户
     * @return
     */
    public String loadTaskCustomer() {
        try {
            getContactCustomerList();
            return SUCCESS;
        } catch (Exception e) {
            log.error("loadTaskCustomer action error:" + e.getMessage());
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
            Integer[] uids = deptFacadeService.getManagerInChargeOfUserIds();
            if (uids != null) {
                for (Integer ids : uids) {
                    userIds = ids + "-" + userIds;
                }
                userIds = userIds+getLoginInfo().getUserId().toString();
            }
        } else {
            userIds = getLoginInfo().getUserId().toString();
        }
        return userIds+"-";
    }

    /**
     * 查看任务
     * @return
     */
    public String detailTask() {
        try {
            tskTask.setExecuteUserId(getLoginInfo().getUserId());
            String userIds=getUserIdsString(true);
            tskTask.setExcUsersIds(userIds);
            if (tskTask.getTaskType() == 1) {//联系任务
                initExcUserTaskCusCount(tskTask);
                tskTask = tskTaskService.getTskTaskById(tskTask);
                initExecuteUser(tskTask.getTaskId(), userIds);
                initParentTskStatus(tskTask);
                loadTaskAttr(tskTask.getTaskId());
                return "taskContactDetail";
            } else if(tskTask.getTaskType() == 2){//营销任务

                return "taskMarketDetail";
            }else{ 
                Integer type=tskTask.getTaskType();
                tskTask = tskTaskService.getTskTaskById(tskTask);
                initExecuteUser(tskTask.getTaskId(), userIds);
                targetCustomer=tskTaskTargetService.getTaskTargetCustomerById(tskTask.getTaskId());
                if(type == 3){//下次联系任务(查看)
                    return "taskNextDetail";
                }else{
                    return "toTskNxetUpdate";
                }
            }
        } catch (Exception e) {
            log.error("detailTask action error:" + e.getMessage());
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 查看任务联系客户列表加载
     * @return
     */
    public String loadTaskCustomerDetail() {
        try {
            getContactCustomerList();
            return SUCCESS;
        } catch (Exception e) {
            log.error("loadTaskCustomerDetail action error:" + e.getMessage());
            return ERROR;
        }
    }

    public String loadTskDetailCustomerList() {
        try {
            getContactCustomerList();
            return SUCCESS;
        } catch (Exception e) {
            log.error("loadTaskCustomerDetail action error:" + e.getMessage());
            return ERROR;
        }
    }

    /**
     * 删除任务
     * @return
     */
    public void delTskTask() {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            Integer count = tskTaskService.deleteTskTask(tskTask.getTaskId());
            out.print(count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 批量修改执行者
     * @return
     */
    public void updateExecuteUser() {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            String[] cusIds = customerIds.split(",");
            if (cusIds != null) {
                for (String customerId : cusIds) {
                    map.clear();
                    map.put("updateUser", getLoginInfo().getUserId());
                    map.put("taskId", tskTask.getTaskId());
                    map.put("executeUserId", excUserIds);
                    map.put("customerId", customerId.trim());
                    tskTaskTargetService.updateExcuteUser(map);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("updateExecuteUser action errot:" + e.getMessage());
        }
    }

    /**
     * 移除联系客户
     * @return
     */
    public void removeTaskCustomer() {
        try {
            tskTaskTargetService.delTsakTargetCustomer(target);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("removeTaskCustomer action errot:" + e.getMessage());
        }
    }

    /**
     * 添加任务联系客户
     * @return
     */
    public void saveTaskCustomer() {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            if (customerIds != null) {
                String uIds=getUserIdsString(true);
                tskTask.setExecuteUserId(getLoginInfo().getUserId());
                tskTask.setExcUsersIds(uIds);
                tskTask = tskTaskService.getTskTaskById(tskTask);
                initExecuteUser(tskTask.getTaskId(), uIds);
                
                String[] userIds = tskTask.getExcUsersIds().split(",");
                String[] cusIds = customerIds.split(",");
                TskTaskTarget taskTarget = new TskTaskTarget();
                taskTarget.setCreateUser(getLoginInfo().getUserId());
                taskTarget.setDeptId(getLoginInfo().getDeptId());
                for (String customerId : cusIds) {
                    BaseCrmCustomer customer = crmCustomerService.getCrmCustomerById(Integer.parseInt(customerId));
                    //是否是共享客户 true:是  false:否
//                    Boolean isShare=crmCustomerService.checkShareCus(Integer.parseInt(customerId), getLoginInfo().getUserId());
                    Integer typeInt=initLoginRole();
                    if (customer.getBelongUserId() != 0||typeInt==0) {//业务主管可以添加自己管辖范围内的待分配客户
                        for (String userId : userIds) {
                            if (customer.getBelongUserId() != 0&&customer.getBelongUserId() == Integer.parseInt(userId.trim())) {
                                /**
                                 * 如果客户归属的客户经理和任务中的执行者匹配，
                                 * 则联系客户的执行者默认为此任务的执行者
                                 * 插入子任务ID
                                 */
                                tskTask.setExecuteUserId(Integer.parseInt(userId));
                                Integer tskId = tskTaskService.getParentTaskId(tskTask);
                                taskTarget.setDeptId(customer.getBelongDeptId());
                                taskTarget.setTaskId(tskId);
                                break;
                            } else {
                                /**
                                 * 反之不相等，
                                 * 则默认为未分配执行者,插入主任务ID
                                 */
                                taskTarget.setTaskId(tskTask.getTaskId());
                            }
                        }
                    } else {
                        //未分配客户
                        taskTarget.setTaskId(tskTask.getTaskId());
                    }
                    taskTarget.setCustomerId(Integer.parseInt(customerId.trim()));
                    boolean b=true;
                    if(b){
                        /**
                         * 首先判断此任务对应的联系客户是否已存在
                         * 不存在则添加，反之不添加
                         */
                        Integer count = tskTaskTargetService.getByTaskCustomerCount(taskTarget);
                        if (count == 0) {
                            tskTaskTargetService.insertTskTaskTarget(taskTarget);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("saveTaskCustomer action errot:" + e.getMessage());
        }
    }

    /**
     * 中止/重启任务
     * @return
     */
    public String stopOrStartTask() {
        try {
            tskTask.setUpdateUser(getLoginInfo().getUserId());
            tskTaskService.changeTaskFinish(tskTask);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("stopOrStartTask action errot:" + e.getMessage());
            return ERROR;
        }
    }

    /**
     * 修改子任务任务状态
     */
    public void changeParentTskStatus() {
        try {
            PrintWriter out = getResponse().getWriter();
            tskTask.setExecuteUserId(getLoginInfo().getUserId());
            Integer count = tskTaskService.changeParentTskStatus(tskTask);
            out.print(count);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("changeParentTskStatus action error:" + e.getMessage());
        }
    }

    /**
     * 更改任务联系客户的联系情况
     */
    public void changeTskCustomerFinish() {
        try {
            PrintWriter out = getResponse().getWriter();
            tskTaskTargetService.isCheckFinishTarget(target);
            tskTask.setExecuteUserId(getLoginInfo().getUserId());
            Integer count = tskTaskTargetService.getByTskTargetNotFinish(tskTask);
            out.print(count);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("changeTskCustomerFinish action error:" + e.getMessage());
        }
    }

    public String initTaskNext() {
        try {
            return "toNextTaskAdd";
        } catch (Exception e) {
            e.printStackTrace();
            log.error("initTaskNext action error:" + e.getMessage());
            return ERROR;
        }
    }

    /**
     * 添加/编辑下次联系任务
     * @return
     */
    public String saveTaskNext() {
        try {
            tskTask.setEndDate(tskTask.getStartDate());
            if(tskTask!=null&&tskTask.getTaskId()!=null){//编辑
                tskTask.setUpdateUser(this.getLoginInfo().getUserId());
                if(tskTask.getTaskStatus()==1){
                    tskTask.setFinishDate(new Date());
                }
                tskTaskService.updateTskTaskContact(tskTask);
            }else{//添加
                tskTask.setCreateUser(this.getLoginInfo().getUserId());
                tskTask.setAssignUserId(this.getLoginInfo().getUserId());
                tskTask.setIsNextContact(1);
                tskTask.setIsDel(0);
                tskTask.setExecuteDeptId(getLoginInfo().getDeptId());
                Integer taskId = tskTaskService.insertTskTaskContact(tskTask);//添加主任务
                if (taskId != null) {
                    //添加任务执行者
                    TskTask task = new TskTask();
                    task.setParentTaskId(taskId);
                    task.setExecuteDeptId(0);
                    task.setCreateUser(this.getLoginInfo().getUserId());
                    task.setExecuteUserId(getLoginInfo().getUserId());
                    tskTaskService.saveTaskExecuteUser(task);

                    //插入联系客户
                    target.setCreateUser(getLoginInfo().getUserId());
                    target.setTaskId(taskId);
                    target.setDeptId(getLoginInfo().getDeptId());
                    tskTaskTargetService.insertTskTaskTarget(target);
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("initTaskNext action error:" + e.getMessage());
            return ERROR;
        }
    }
    /**
     * 加载客户列表页面
     * @return
     */
    public String loadCustomerListVm() {
        getContactCustomerList();
        return SUCCESS;
    }
    
    /**
     * 通话改变客户的下次联系任务
     */
    public void updateNextTaskIsDel() {
        try {
            if(taskId!=null){
                tskTaskTargetService.updataNextTaskCustoemr(customerId, taskId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("changeParentTskStatus action error:" + e.getMessage());
        }
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public TskTaskService getTskTaskService() {
        return tskTaskService;
    }

    public void setTskTaskService(TskTaskService tskTaskService) {
        this.tskTaskService = tskTaskService;
    }

    public PageUtil<TskTask> getTskTaskPage() {
        return tskTaskPage;
    }

    public void setTskTaskPage(PageUtil<TskTask> tskTaskPage) {
        this.tskTaskPage = tskTaskPage;
    }

    public Map<Integer, String> getTaskTypeMap() {
        return taskTypeMap;
    }

    public void setTaskTypeMap(Map<Integer, String> taskTypeMap) {
        this.taskTypeMap = taskTypeMap;
    }

    public TskTask getTskTask() {
        return tskTask;
    }

    public void setTskTask(TskTask tskTask) {
        this.tskTask = tskTask;
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

    public File getFileInput() {
        return fileInput;
    }

    public void setFileInput(File fileInput) {
        this.fileInput = fileInput;
    }

    public String getFileInputFileName() {
        return fileInputFileName;
    }

    public void setFileInputFileName(String fileInputFileName) {
        this.fileInputFileName = fileInputFileName;
    }

    public String[] getFileNameTask() {
        return fileNameTask;
    }

    public void setFileNameTask(String[] fileNameTask) {
        this.fileNameTask = fileNameTask;
    }

    public String[] getFileSize() {
        return fileSize;
    }

    public void setFileSize(String[] fileSize) {
        this.fileSize = fileSize;
    }

    public TskTaskAttachmentService getAttachmentService() {
        return attachmentService;
    }

    public void setAttachmentService(TskTaskAttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    public List<TskTaskAttachment> getAttrList() {
        return attrList;
    }

    public void setAttrList(List<TskTaskAttachment> attrList) {
        this.attrList = attrList;
    }

    public TskTaskAttachment getTaskAttr() {
        return taskAttr;
    }

    public void setTaskAttr(TskTaskAttachment taskAttr) {
        this.taskAttr = taskAttr;
    }

    public String getBelongTo() {
        return belongTo;
    }

    public void setBelongTo(String belongTo) {
        this.belongTo = belongTo;
    }

    public String getExcUserIds() {
        return excUserIds;
    }

    public void setExcUserIds(String excUserIds) {
        this.excUserIds = excUserIds;
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

    public String getCustomerIds() {
        return customerIds;
    }

    public void setCustomerIds(String customerIds) {
        this.customerIds = customerIds;
    }

    public TskTaskTarget getTarget() {
        return target;
    }

    public void setTarget(TskTaskTarget target) {
        this.target = target;
    }

    public CrmCustomerService getCrmCustomerService() {
        return crmCustomerService;
    }

    public void setCrmCustomerService(CrmCustomerService crmCustomerService) {
        this.crmCustomerService = crmCustomerService;
    }

    public Integer getExcUserTaskCusCount() {
        return excUserTaskCusCount;
    }

    public void setExcUserTaskCusCount(Integer excUserTaskCusCount) {
        this.excUserTaskCusCount = excUserTaskCusCount;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public Integer getParentTaskStatus() {
        return parentTaskStatus;
    }

    public void setParentTaskStatus(Integer parentTaskStatus) {
        this.parentTaskStatus = parentTaskStatus;
    }

    public String getTreeType() {
        return treeType;
    }

    public void setTreeType(String treeType) {
        this.treeType = treeType;
    }

    public Integer getTreeId() {
        return treeId;
    }

    public void setTreeId(Integer treeId) {
        this.treeId = treeId;
    }

    public DeptFacadeService getDeptFacadeService() {
        return deptFacadeService;
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }

    public SysParamService getSysParamService() {
        return sysParamService;
    }

    public void setSysParamService(SysParamService sysParamService) {
        this.sysParamService = sysParamService;
    }

    public Map<Integer, String> getTaskSituMap() {
        return taskSituMap;
    }

    public void setTaskSituMap(Map<Integer, String> taskSituMap) {
        this.taskSituMap = taskSituMap;
    }

    public Integer getTaskSituId() {
        return taskSituId;
    }

    public void setTaskSituId(Integer taskSituId) {
        this.taskSituId = taskSituId;
    }

    public String[] getFileNameOld() {
        return fileNameOld;
    }

    public void setFileNameOld(String[] fileNameOld) {
        this.fileNameOld = fileNameOld;
    }

    public TaskTargetCustomer getTargetCustomer() {
        return targetCustomer;
    }

    public void setTargetCustomer(TaskTargetCustomer targetCustomer) {
        this.targetCustomer = targetCustomer;
    }

    public Integer getRefreshAssignId() {
        return refreshAssignId;
    }

    public void setRefreshAssignId(Integer refreshAssignId) {
        this.refreshAssignId = refreshAssignId;
    }

    public Integer getRefreshTaskType() {
        return refreshTaskType;
    }

    public void setRefreshTaskType(Integer refreshTaskType) {
        this.refreshTaskType = refreshTaskType;
    }

    public String getRefreshStartDate() {
        return refreshStartDate;
    }

    public void setRefreshStartDate(String refreshStartDate) {
        this.refreshStartDate = refreshStartDate;
    }

    public String getRefreshEndDate() {
        return refreshEndDate;
    }

    public void setRefreshEndDate(String refreshEndDate) {
        this.refreshEndDate = refreshEndDate;
    }

    public String getRefreshTaskTitle() {
        return refreshTaskTitle;
    }

    public void setRefreshTaskTitle(String refreshTaskTitle) {
        this.refreshTaskTitle = refreshTaskTitle;
    }

    public String getRefreshProductName() {
        return refreshProductName;
    }

    public void setRefreshProductName(String refreshProductName) {
        this.refreshProductName = refreshProductName;
    }

    public String getRefreshRemark() {
        return refreshRemark;
    }

    public void setRefreshRemark(String refreshRemark) {
        this.refreshRemark = refreshRemark;
    }

    public Integer getCustomerCount() {
        return customerCount;
    }

    public void setCustomerCount(Integer customerCount) {
        this.customerCount = customerCount;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }
    
}
