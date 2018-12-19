/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :任务完成统计报表
 * Author     :liyb
 * Create Date:2012-9-5
 */
package com.banger.mobile.webapp.action.report;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.Enum.task.EnumTask;
import com.banger.mobile.domain.collection.DataTable;
import com.banger.mobile.domain.model.tskContact.CusRelatedTskContactBean;
import com.banger.mobile.domain.model.tskContact.TaskPlanCustomerDetailBean;
import com.banger.mobile.domain.model.tskContact.TskContactReportBean;
import com.banger.mobile.domain.model.tskContact.TskTaskPlanReportBean;
import com.banger.mobile.domain.model.tskTaskPurpose.TskTaskPurpose;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.tskContact.TskContactService;
import com.banger.mobile.facade.tskContact.TskPlanService;
import com.banger.mobile.facade.tskContact.TskPlanTargetService;
import com.banger.mobile.facade.tskTaskPurpose.TskTaskPurposeService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.util.ExcelUtil;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.util.TimeWrapper;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author liyb
 * @version $Id: TskTaskReport.java,v 0.1 2012-9-5 上午11:58:42 liyb Exp $
 */
public class TskTaskReportAction extends BaseAction {

    private static final long       serialVersionUID = -4509608295744710981L;

    private DeptFacadeService       deptFacadeService;
    private Integer                 roleType;                                //登录用户角色类型
    private DataTable               table;                                   //报表数据
    private String                  userName;                                //制表人
    private Integer                 userId;                                  //用户Id
    private Date                    reportTime;                              //制表时间
    private String                  startDate;                               //开始日期
    private String                  endDate;                                 //结束日期
    private String                  taskType;                                //任务类型 1：联系任务 2：营销任务
    private String                  userIds;                                 //下属的用户ID集合
    private List<TskContactReportBean> taskReportList;
    private SysUserService sysUserService;
    
    private TskTaskPurposeService   tskTaskPurposeService;                   //联系目的service
    private List<TskTaskPurpose>    taskPurposeList;                         //联系目的list
    private Integer purpostId;//联系目的ID
    private Integer searchType;//统计角度 1:执行者 2:分配者
    private PageUtil<CusRelatedTskContactBean> relatedTaskPage;//任务完成情况统计报表明细分页列表
    private String detailFlag;//明细类型
    private Integer toPageFlag;
    private List<TskTaskPlanReportBean> taskPlanReportList;//计划执行情况报表列表
    private PageUtil<TaskPlanCustomerDetailBean> taskPlanCustomerPage;//计划执行情况报表明细分页列表
    
    /*****************************任务重构*********************************************/
    private TskPlanService       tskPlanService; //联系计划service
    private TskPlanTargetService tskPlanTargetService; //联系计划目标客户service
    private TskContactService    tskContactService; //联系任务service
    
    /**
     * 初始化机构用户树(过滤纯业务主管)
     */
    public void initDeptUserTree(){
        try {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            Map<String, Object> map = new HashMap<String, Object>();
            String deptIds=getDeptIdsString();
            map.put("deptId", deptIds);
            map.put("myUserId", getLoginInfo().getUserId());
            map.put("taskAdd", true);
            map.put("deptUserIds", deptIds);
            JSONArray deptJson = tskContactService.getCustomerAttrPluginList(map);
            out.print(deptJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    /**
     * 初始化任务完成情况报表页面
     * @author liyb
     */
    public String initTaskReport() {
        initLoginRole();
        initTaskPurpose();
        return SUCCESS;
    }
    
    /**
     * 初始化联系目的
     * @author liyb
     */
    public void initTaskPurpose(){
        taskPurposeList=tskTaskPurposeService.getAllTskTaskPurpose();
    }

    /**
     * 任务完成统计报表列表页面
     * @return
     */
    public String showTaskReportList() {
        try {
            initLoginRole();
            Map<String, Object> map = new HashMap<String, Object>();
            if(searchType==1){
                if (!StringUtil.isBlank(userIds)) {
                    map.put("userIds", userIds);
                }else{
                    map.put("userIds", getUserIdsString());
                }
            }else{//分配者
                map.put("userIds", getLoginInfo().getUserId());
            }
            
            /**
             * 执行日期搜索
             */
            String sql="";
            if (!StringUtil.isBlank(startDate)&&StringUtil.isBlank(endDate)) {
                sql=" and TO_DATE ('"+startDate+"', 'yyyy-MM-dd')<=A.END_DATE ";
                map.put("executeDateSearch", sql);
            }else if(StringUtil.isBlank(startDate)&&!StringUtil.isBlank(endDate)){
                sql=" and TO_DATE ('"+endDate+"', 'yyyy-MM-dd')>=A.START_DATE ";
                map.put("executeDateSearch", sql);
            }else if(!StringUtil.isBlank(startDate)&&!StringUtil.isBlank(endDate)){
                sql=" and (TO_DATE ('"+endDate+"', 'yyyy-MM-dd')>=A.START_DATE and TO_DATE ('"+startDate+"', 'yyyy-MM-dd')<=A.END_DATE) ";
                map.put("executeDateSearch", sql);
            }           
            if(purpostId!=null){
                map.put("purpostId", purpostId);
            }
            taskReportList = tskContactService.getTaskReportList(map,searchType);
            HttpSession session = getRequest().getSession();   
            session.setAttribute("taskReportList", taskReportList);
            this.userName = this.getLoginInfo().getUserName();
            this.reportTime = new TimeWrapper(new Date().getTime());
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("showTaskReportList action error:" + e.getMessage());
            return ERROR;
        }
    }

    /**
     * 任务统计报表明细
     * @return
     */
    public String shwoTaskReportDetail() {
        try {
            SysUser user=sysUserService.getSysUserById(userId);
            getRequest().setAttribute("userNameDetail", user.getUserName());
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("executeUserId", userId);
            /**
             * 执行日期搜索
             */
            if(detailFlag.equals("allPlan")){
                if (!StringUtil.isBlank(startDate)) {
                    map.put("planStartDate", startDate);
                }
                if(!StringUtil.isBlank(endDate)){
                    map.put("planEndDate", endDate);
                }
            }else{
                String sql="";
                if (!StringUtil.isBlank(startDate)&&StringUtil.isBlank(endDate)) {
                    sql=" and TO_DATE ('"+startDate+"', 'yyyy-MM-dd')<=A.END_DATE ";
                    map.put("executeDateSearch", sql);
                }else if(StringUtil.isBlank(startDate)&&!StringUtil.isBlank(endDate)){
                    sql=" and TO_DATE ('"+endDate+"', 'yyyy-MM-dd')>=A.START_DATE ";
                    map.put("executeDateSearch", sql);
                }else if(!StringUtil.isBlank(startDate)&&!StringUtil.isBlank(endDate)){
                    sql=" and (TO_DATE ('"+endDate+"', 'yyyy-MM-dd')>=A.START_DATE and TO_DATE ('"+startDate+"', 'yyyy-MM-dd')<=A.END_DATE) ";
                    map.put("executeDateSearch", sql);
                }   
                if(purpostId!=null){
                    map.put("purpostId", purpostId);
                }
            }
            relatedTaskPage = tskContactService.getTaskReportDetailPage(map,detailFlag,searchType,this.getPage());
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("shwoTaskReportDetail action errot:" + e.getMessage());
            return ERROR;
        }
    }
    
    /**
     * 导出任务统计报表
     */
    public void exportTaskReport(){
        OutputStream outputStream = null;
        HSSFWorkbook workbook = null;
        try {
            String title="";
            if(!StringUtil.isBlank(startDate)&&!StringUtil.isBlank(endDate)){
                title=startDate+"至"+endDate;
            }
            if(!StringUtil.isBlank(startDate)&&StringUtil.isBlank(endDate)){
                title=startDate+"之后";
            }
            if(StringUtil.isBlank(startDate)&&!StringUtil.isBlank(endDate)){
                title="截止至"+endDate;
            }
            String reportName = title+"任务完成统计表";
            getResponse().setHeader("Content-disposition","attachment; filename="+new String(reportName.getBytes("gbk"),"iso8859-1")+".xls");//设定输出文件头
            getResponse().setContentType("application/msexcel");//定义输出类型
            getResponse().setCharacterEncoding("UTF-8");
            outputStream = getResponse().getOutputStream();
//            showTaskReportList();
            HttpSession session = getRequest().getSession();   
            List<TskContactReportBean> list=(List<TskContactReportBean>)session.getAttribute("taskReportList");
            workbook=tskContactService.exportTaskReportExcel(list, this.getLoginInfo().getUserName());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("exportTaskReport action error:"+e.getMessage());
            log.error(e.getMessage());
            workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet();
            ExcelUtil.writeExcelDetailRow(sheet, new String[] { "导出报表发生系统异常" }, 0);
        }finally {
            ExcelUtil.writeToResponse(workbook, outputStream);
        }
    }
    /**
     * 打印报表
     * @return
     */
    public String taskReportPrint(){
        try {
            showTaskReportList();
            return SUCCESS;
        } catch (Exception e) {
            log.error("taskReportPrint action error:"+e.getMessage());
            return ERROR;
        }
        
    }

    /**
     * 根据当前登陆人的角色获取所查询的数据ID
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
                userIds = userIds + getLoginInfo().getUserId().toString();
            }
        } else {
            userIds = getLoginInfo().getUserId().toString();
        }
        return userIds;
    }
    private String getDeptIdsString() {
        Integer roleFlag = initLoginRole();
        String deptIds = "";
        String deptUserIds = "";
        if (roleFlag == 0) {//存在业务主管
            Integer[] depts = deptFacadeService.getInChargeOfDeptIds();
            if (depts != null) {
                for (Integer dIds : depts) {
                    deptIds = dIds + "," + deptIds;
                }
            }
            deptUserIds = deptIds.substring(0, deptIds.length() - 1);
        } else {
            deptUserIds = getLoginInfo().getDeptId().toString();
        }
        return deptUserIds;
    }

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
        this.request.setAttribute("userLoginUserId", getLoginInfo().getUserId());
        String[] roleName = getLoginInfo().getRoleNames();
        if (compareStr(EnumTask.BUSINESS_COMPETENT.getValue(), roleName) != 0) {//业务主管、系统管理员、机构系统管理员
            roleType = 0;
        } else {
            roleType = getLoginInfo().getUserId();
        }
        return roleType;
    }
    
    /**
     * 初始化计划执行情况统计报表
     * @return
     */
    public String initTskTaskPlanReportPage(){
        initLoginRole();
        return SUCCESS;
    }
    
    /**
     * 计划执行情况报表查询
     * @return
     */
    public String showTskTaskPlanReportList(){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            if (!StringUtil.isBlank(userIds)) {
                map.put("userIds", userIds);
            }else{
                map.put("userIds", deptFacadeService.getInChargeOfDeptUserIds(getLoginInfo().getUserId(), 1));
            }
            if (!StringUtil.isBlank(startDate)) {
                map.put("startDate", startDate);
            }
            if(!StringUtil.isBlank(endDate)){
                map.put("endDate", endDate);
            }
            taskPlanReportList=tskPlanService.getTaskPlanReportList(map);
            this.userName = this.getLoginInfo().getUserName();
            this.reportTime = new TimeWrapper(new Date().getTime());
            HttpSession session = getRequest().getSession();
            session.setAttribute("taskPlanReportList", taskPlanReportList);
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            log.equals("showTskTaskPlanReportList action error:"+e.getMessage());
            return ERROR;
        }
    }
    
    /**
     * 计划执行情况报表明细
     * @return
     */
    public String planReportDetail(){
        try {
            Map<String,Object> map=new HashMap<String, Object>();
            SysUser user=sysUserService.getSysUserById(userId);
            getRequest().setAttribute("userNameDetail", user.getUserName());
            map.put("executeUserId", userId);
            if (!StringUtil.isBlank(startDate)) {
                map.put("startDate", startDate);
            }
            if(!StringUtil.isBlank(endDate)){
                map.put("endDate", endDate);
            }
            if(deptFacadeService.isCommon()) map.put("userIds", this.getLoginInfo().getUserId());
            if(deptFacadeService.isInChargeOfDepartment()){
                map.put("userIds", deptFacadeService.getInChargeOfDeptUserIds(getLoginInfo().getUserId(), 0));
            }
            map.put("deptName", this.getLoginInfo().getDeptname());
            taskPlanCustomerPage=tskPlanTargetService.getTaskPlanCustomerPage(map, detailFlag, this.getPage());
            return "planCustomer";
        } catch (Exception e) {
            e.printStackTrace();
            log.error("planReportDetail action error:"+e.getMessage());
            return ERROR;
        }
    }
    
    /**
     * 导出计划执行情况统计报表
     */
    public void exportTaskPlanReport(){
        OutputStream outputStream = null;
        HSSFWorkbook workbook = null;
        try {
            String title="";
            if(!StringUtil.isBlank(startDate)&&!StringUtil.isBlank(endDate)){
                title=startDate+"至"+endDate;
            }
            if(!StringUtil.isBlank(startDate)&&StringUtil.isBlank(endDate)){
                title=startDate+"之后";
            }
            if(StringUtil.isBlank(startDate)&&!StringUtil.isBlank(endDate)){
                title="截止至"+endDate;
            }
            String reportName = title+"计划执行情况统计表";
            getResponse().setHeader("Content-disposition","attachment; filename="+new String(reportName.getBytes("gbk"),"iso8859-1")+".xls");//设定输出文件头
            getResponse().setContentType("application/msexcel");//定义输出类型
            getResponse().setCharacterEncoding("UTF-8");
            outputStream = getResponse().getOutputStream();
            HttpSession session = getRequest().getSession();   
            List<TskTaskPlanReportBean> list=(List<TskTaskPlanReportBean>) session.getAttribute("taskPlanReportList");
            workbook=tskPlanService.exportTaskPlanReportExcel(list, this.getLoginInfo().getUserName());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("exportTaskReport action error:"+e.getMessage());
            log.error(e.getMessage());
            workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet();
            ExcelUtil.writeExcelDetailRow(sheet, new String[] { "导出报表发生系统异常" }, 0);
        }finally {
            ExcelUtil.writeToResponse(workbook, outputStream);
        }
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

    public DataTable getTable() {
        return table;
    }

    public void setTable(DataTable table) {
        this.table = table;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
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
    
    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public SysUserService getSysUserService() {
        return sysUserService;
    }

    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    public TskTaskPurposeService getTskTaskPurposeService() {
        return tskTaskPurposeService;
    }

    public void setTskTaskPurposeService(TskTaskPurposeService tskTaskPurposeService) {
        this.tskTaskPurposeService = tskTaskPurposeService;
    }

    public List<TskTaskPurpose> getTaskPurposeList() {
        return taskPurposeList;
    }

    public void setTaskPurposeList(List<TskTaskPurpose> taskPurposeList) {
        this.taskPurposeList = taskPurposeList;
    }

    public Integer getPurpostId() {
        return purpostId;
    }

    public void setPurpostId(Integer purpostId) {
        this.purpostId = purpostId;
    }

    public Integer getSearchType() {
        return searchType;
    }

    public void setSearchType(Integer searchType) {
        this.searchType = searchType;
    }

    public String getDetailFlag() {
        return detailFlag;
    }

    public void setDetailFlag(String detailFlag) {
        this.detailFlag = detailFlag;
    }

    public List<TskTaskPlanReportBean> getTaskPlanReportList() {
        return taskPlanReportList;
    }

    public void setTaskPlanReportList(List<TskTaskPlanReportBean> taskPlanReportList) {
        this.taskPlanReportList = taskPlanReportList;
    }

    public PageUtil<TaskPlanCustomerDetailBean> getTaskPlanCustomerPage() {
        return taskPlanCustomerPage;
    }

    public void setTaskPlanCustomerPage(PageUtil<TaskPlanCustomerDetailBean> taskPlanCustomerPage) {
        this.taskPlanCustomerPage = taskPlanCustomerPage;
    }

    public Integer getToPageFlag() {
        return toPageFlag;
    }

    public TskPlanService getTskPlanService() {
        return tskPlanService;
    }

    public void setTskPlanService(TskPlanService tskPlanService) {
        this.tskPlanService = tskPlanService;
    }

    public TskPlanTargetService getTskPlanTargetService() {
        return tskPlanTargetService;
    }

    public TskContactService getTskContactService() {
        return tskContactService;
    }

    public void setTskContactService(TskContactService tskContactService) {
        this.tskContactService = tskContactService;
    }

    public void setTskPlanTargetService(TskPlanTargetService tskPlanTargetService) {
        this.tskPlanTargetService = tskPlanTargetService;
    }

    public void setToPageFlag(Integer toPageFlag) {
        this.toPageFlag = toPageFlag;
    }


	public List<TskContactReportBean> getTaskReportList() {
		return taskReportList;
	}


	public void setTaskReportList(List<TskContactReportBean> taskReportList) {
		this.taskReportList = taskReportList;
	}


	public PageUtil<CusRelatedTskContactBean> getRelatedTaskPage() {
		return relatedTaskPage;
	}


	public void setRelatedTaskPage(
			PageUtil<CusRelatedTskContactBean> relatedTaskPage) {
		this.relatedTaskPage = relatedTaskPage;
	}
}
