/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :liyb
 * Create Date:2013-12-25
 */
package com.banger.mobile.webapp.action.microReport;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.microReport.LoanTaskReportBean;
import com.banger.mobile.domain.model.product.ProductObj;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.microReport.MicroTaskReportService;
import com.banger.mobile.util.ExcelUtil;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.util.TimeWrapper;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * 任务报表Action
 * @author liyb
 * @version $Id: MicroTaskReportAction.java,v 0.1 2013-12-25 下午03:21:26 liyb Exp $
 */
public class MicroTaskReportAction extends BaseAction {
    private static final long serialVersionUID = -984030492846711697L;
    private DeptFacadeService deptFacadeService;
    private MicroTaskReportService microTaskReportService;
    private List<LoanTaskReportBean> loanTaskList;
    private List<LoanTaskReportBean> tskMarketingList;

    public Integer            roleType;                               //角色类型
    private String            userName;                               //制表人
    private Integer           userId;                                 //用户Id
    private Date              reportTime;                             //制表时间
    private String            startDate;                              //开始日期
    private String            endDate;                                //结束日期
    private String            searchType;                             //统计角度 
    private Integer           containSub;                             //是否查询子机构 1:是 0:否
    private String            userIds;
    private String            deptIds;
    private String            taskName;
    private Integer           taskType;

    public void initRole() {
        request.setAttribute("userLoginName", this.getLoginInfo().getUserName());
        request.setAttribute("userLoginDeptId", getLoginInfo().getDeptId());
        request.setAttribute("userLoginUserId", getLoginInfo().getUserId());
        //判断是否是业务主管
        if (deptFacadeService.isInChargeOfDepartment()) {
            roleType = 0;
        } else {
            roleType = this.getLoginInfo().getUserId();
        }
    }

    /**
     * 初始化贷款任务报表列表
     * @return
     */
    public String initLoanTaskReport() {
        initRole();
        return SUCCESS;
    }

    /**
     * 贷款任务报表查询
     * @return
     */
    public String showLoanTaskReportList() {
        try {
            Map<String,Object> map=new HashMap<String, Object>();
            if(!searchType.equals("brDept")){
                if(!StringUtil.isBlank(userIds)){
                    map.put("userIds", userIds);
                }else{
                    map.put("userIds", getUserIdsString(false));
                }
            }else{
                if(containSub == 1){//包括子机构
                    map.put("deptIds", getContainSubDeptIds(deptIds));
                }else{
                    map.put("deptIds", deptIds);
                }
            }
            map.put("taskTitle", taskName);
            map.put("startDate", startDate);
            map.put("endDate", endDate);
            map.put("taskType", 1);
            loanTaskList=microTaskReportService.getLoanTaskReportList(map, searchType);
            this.userName = this.getLoginInfo().getUserName();
            this.reportTime = new TimeWrapper(new Date().getTime());
            
            /**
             * 把报表信息存入session,导出报表使用
             */
            HttpSession session = getRequest().getSession(); 
            session.setAttribute("loanTaskList", loanTaskList);
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    
    /**
     * 导出贷款任务报表
     */
    public void exprotLoanTaskReport(){
        OutputStream outputStream = null;
        HSSFWorkbook workbook = null;
        try {
            String title = "";
            boolean flag=false;
            if (!StringUtil.isBlank(startDate)&&!StringUtil.isBlank(endDate)) {
                title += startDate + "至" + endDate;
            } else if (!StringUtil.isBlank(startDate)&&StringUtil.isBlank(endDate)) {
                title += startDate + "之后";
            } else if (StringUtil.isBlank(startDate)&&!StringUtil.isBlank(endDate)) {
                title += "截止至" + endDate;
            }
            String reportName = title + "贷款任务完成统计表";
            getResponse().setHeader("Content-disposition","attachment; filename=" + new String(reportName.getBytes("gbk"), "iso8859-1")+ ".xls");//设定输出文件头
            getResponse().setContentType("application/msexcel");//定义输出类型
            getResponse().setCharacterEncoding("UTF-8");
            outputStream = getResponse().getOutputStream();
            HttpSession session = getRequest().getSession();
            List<LoanTaskReportBean> list=(List<LoanTaskReportBean>) session.getAttribute("loanTaskList");
            if(searchType.equals("brDept")){
                flag=true;
            }
            workbook=microTaskReportService.exportLoanTaskReportExcel(list, flag, this.getLoginInfo().getUserName());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("exprotLoanTaskReport action error:" + e.getMessage());
            log.error(e.getMessage());
            workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet();
            ExcelUtil.writeExcelDetailRow(sheet, new String[] { "导出报表发生系统异常" }, 0);
        } finally {
            ExcelUtil.writeToResponse(workbook, outputStream);
        }
    }

    /**
     * 获得登录用户所管理的部门用户的id集合
     * flag 为true是包含 为false不包含
     * @return
     */
    public String getUserIdsString(boolean flag) {
        String userIds = "";
        if (deptFacadeService.isInChargeOfDepartment()) {//存在业务主管
            Integer[] uids = deptFacadeService.getInChargeOfDeptUserIds(flag);
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
     * 根据部门id集合查询它们及下属的部门id集合 （工具类）
     * @param deptids
     * @return
     */
    public String getContainSubDeptIds(String deptids) {
        List<SysDept> deptList = deptFacadeService.getContainDeptListByDeptIds(deptids);
        String newDeptIds = "";
        for (SysDept dept : deptList) {
            if (newDeptIds.equals("")) {
                newDeptIds = dept.getDeptId().toString();
            } else {
                newDeptIds = newDeptIds + "," + dept.getDeptId().toString();
            }
        }
        return newDeptIds;
    }
    
    
    /**
     * 初始化任务完成情况报表
     * @return
     */
    public String initMicroTaskCompleteReport() {
        initRole();
        return SUCCESS;
    }
    
    /**
     * 任务完成情况报表统计查询
     * @return
     */
    public String showMicroTaskCompleteList(){
        try {
            this.userName = this.getLoginInfo().getUserName();
            this.reportTime = new TimeWrapper(new Date().getTime());
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    
    /**
     * 初始化营销任务报表列表
     * @return
     */
    public String initMicroTaskMarketingReport() {
        initRole();
        return SUCCESS;
    }
    
    /**
     * 营销任务完成情况统计报表查询
     * @return
     */
    public String showMicroTaskMarketingList(){
        try {
            Map<String,Object> map=new HashMap<String, Object>();
            if(!searchType.equals("brDept")){
                if(!StringUtil.isBlank(userIds)){
                    map.put("userIds", userIds);
                }else{
                    map.put("userIds", getUserIdsString(false));
                }
            }else{
                if(containSub == 1){//包括子机构
                    map.put("deptIds", getContainSubDeptIds(deptIds));
                }else{
                    map.put("deptIds", deptIds);
                }
            }
            map.put("taskTitle", taskName);
            map.put("startDate", startDate);
            map.put("endDate", endDate);
            map.put("taskType", 2);
            tskMarketingList=microTaskReportService.getLoanTaskReportList(map, searchType);
            this.userName = this.getLoginInfo().getUserName();
            this.reportTime = new TimeWrapper(new Date().getTime());
            
            /**
             * 把报表信息存入session,导出报表使用
             */
            HttpSession session = getRequest().getSession(); 
            session.setAttribute("tskMarketingList", tskMarketingList);
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    
    /**
     * 导出营销任务完成统计报表
     */
    public void exprotMicroTskMarketingReport(){
        OutputStream outputStream = null;
        HSSFWorkbook workbook = null;
        try {
            String title = "";
            boolean flag=false;
            if (!StringUtil.isBlank(startDate)&&!StringUtil.isBlank(endDate)) {
                title += startDate + "至" + endDate;
            } else if (!StringUtil.isBlank(startDate)&&StringUtil.isBlank(endDate)) {
                title += startDate + "之后";
            } else if (StringUtil.isBlank(startDate)&&!StringUtil.isBlank(endDate)) {
                title += "截止至" + endDate;
            }
            String reportName = title + "营销任务完成统计表";
            getResponse().setHeader("Content-disposition","attachment; filename=" + new String(reportName.getBytes("gbk"), "iso8859-1")+ ".xls");//设定输出文件头
            getResponse().setContentType("application/msexcel");//定义输出类型
            getResponse().setCharacterEncoding("UTF-8");
            outputStream = getResponse().getOutputStream();
            HttpSession session = getRequest().getSession();
            List<LoanTaskReportBean> list=(List<LoanTaskReportBean>) session.getAttribute("tskMarketingList");
            if(searchType.equals("brDept")){
                flag=true;
            }
            workbook=microTaskReportService.exportMicroTskMarketingReportExcel(list, flag, this.getLoginInfo().getUserName());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("exprotMicroTskMarketingReport action error:" + e.getMessage());
            log.error(e.getMessage());
            workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet();
            ExcelUtil.writeExcelDetailRow(sheet, new String[] { "导出报表发生系统异常" }, 0);
        } finally {
            ExcelUtil.writeToResponse(workbook, outputStream);
        }
    }
    
    
    /**
     * 初始化产品信息
     * @return
     */
    public void initTaskJsonMsg() {
        try {
            PrintWriter out = getResponse().getWriter();
            Map<String, Object> mapParam=new HashMap<String, Object>();
            if(!StringUtil.isBlank(taskName)){
                mapParam.put("taskTitle", taskName.trim());
            }
            mapParam.put("taskType", taskType);
            List<LoanTaskReportBean> list = microTaskReportService.getTaskByTitle(mapParam);
            Map<String, Object> map = new HashMap<String, Object>();
            JSONArray taskJson = new JSONArray();
            for (LoanTaskReportBean obj : list) {
                map.clear();
                map.put("taskId", obj.getTaskId());
                map.put("text", obj.getTaskTitle());//任务名称
                taskJson.add(map);
            }
            out.print(taskJson);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("initTaskJsonMsg action error:" + e.getMessage());
        }
    }
    
    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
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

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public Integer getContainSub() {
        return containSub;
    }

    public void setContainSub(Integer containSub) {
        this.containSub = containSub;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public String getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(String deptIds) {
        this.deptIds = deptIds;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setMicroTaskReportService(MicroTaskReportService microTaskReportService) {
        this.microTaskReportService = microTaskReportService;
    }

    public List<LoanTaskReportBean> getLoanTaskList() {
        return loanTaskList;
    }

    public void setLoanTaskList(List<LoanTaskReportBean> loanTaskList) {
        this.loanTaskList = loanTaskList;
    }

    public List<LoanTaskReportBean> getTskMarketingList() {
        return tskMarketingList;
    }

    public void setTskMarketingList(List<LoanTaskReportBean> tskMarketingList) {
        this.tskMarketingList = tskMarketingList;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

}
