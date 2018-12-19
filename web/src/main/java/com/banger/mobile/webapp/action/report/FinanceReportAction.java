/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :任务完成情况统计报表Action
 * Author     :liyb
 * Create Date:2012-12-4
 */
package com.banger.mobile.webapp.action.report;

import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.Enum.task.EnumTask;
import com.banger.mobile.domain.model.base.finance.BaseFeArticle;
import com.banger.mobile.domain.model.finance.FeColumn;
import com.banger.mobile.domain.model.report.ComparatorSysUserBean;
import com.banger.mobile.domain.model.report.FinanceReportBean;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.domain.model.user.SysUserBean;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.finance.FeColumnService;
import com.banger.mobile.facade.report.FinanceReportService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.util.ExcelUtil;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.util.TimeWrapper;
import com.banger.mobile.webapp.action.BaseAction;

import edu.emory.mathcs.backport.java.util.Collections;

/**
 * @author liyb
 * @version $Id: FinanceReportAction.java,v 0.1 2012-12-4 下午02:32:58 liyb Exp $
 */
public class FinanceReportAction extends BaseAction {

    private static final long       serialVersionUID = 1L;
    private DeptFacadeService       deptFacadeService;
    private Integer                 roleType;             //登录用户角色类型
    private String                  userName;             //制表人
    private Integer                 userId;               //用户Id
    private Date                    reportTime;           //制表时间
    private String                  startDate;            //开始日期
    private String                  endDate;              //结束日期
    private String                  userIds;              //下属的用户ID集合

    private FeColumnService         feColumnService;      //栏目Service
    private List<FeColumn>          feColumnList;         //所有栏目List
    private String                  columns;              //所有栏目字符串
    private int                     colsCount;            //栏目数
    private JSONArray               columnJson;
    private String                  columnIds;            //栏目ID字符串集合

    private FinanceReportService    financeReportService;
    private List<FinanceReportBean> financeReportList;
    private SysUserService          sysUserService;
    private List<SysUserBean>       userList;
    
    private Integer                 columnId;              //栏目ID
    private Integer                 aritcleFlag;           //明细标识 0：未读条数 1：未读其中必读条数
    private PageUtil<BaseFeArticle> articlePage;

    /**
     * 初始化登陆人角色
     */
    public Integer initLoginRole() {
        this.request.setAttribute("userLoginName", this.getLoginInfo().getUserName());
        this.request.setAttribute("userLoginDeptId", getLoginInfo().getDeptId());
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

    public int compareStr(String str, String x[]) {
        int suffix = 0;//记录字符串数组的下标
        for (int i = 0; i < x.length; i++) {
            if (str.equals(x[i].trim()))
                suffix = i + 1;
        }
        return suffix;
    }

    /**
     * 初始化栏目
     */
    public void initColumn() {
        JSONArray array=new JSONArray();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("isStart", 1);
        feColumnList = feColumnService.getAllColumnList(map);
        if (feColumnList.size() > 0) {
            Map<String,Object> colMap=new HashMap<String, Object>();
            String cols="";
            String colIds="";
            for (FeColumn column : feColumnList) {
                colMap.put("text", column.getColumnName());
                colMap.put("value", column.getColumnId());
                array.add(colMap);
                cols=column.getColumnName()+","+cols;
                colIds=column.getColumnId()+","+colIds;
            }
            columns=cols.substring(0,cols.lastIndexOf(","));
            columnIds=colIds.substring(0,colIds.lastIndexOf(","));
        }
        columnJson=array;
    }

    /**
     * 初始化财经要点报表页面
     * @return
     */
    public String initFinanceReport() {
        try {
            initLoginRole();
            initColumn();
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("initFinanceReport action error:" + e.getMessage());
            return ERROR;
        }
    }

    /**
     * 财经要点报表列表
     * @return
     */
    public String financeReportPageList() {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            String userIdsString = getUserIdsString();
            if (!StringUtil.isBlank(userIds)) {
                map.put("userIds", userIds);
            } else if(!StringUtil.isBlank(userIdsString)){
                map.put("userIds", getUserIdsString());
            }else{
            	map.put("userIds","0");
            }
            map.put("startDate", startDate);
            map.put("endDate", endDate);
            map.put("columnIds", columnIds);
            colsCount=columnIds.split(",").length;
            map.put("colsCount", colsCount);
            userList = sysUserService.getUserListByIds(map.get("userIds").toString());
            ComparatorSysUserBean comparator = new ComparatorSysUserBean();
            Collections.sort(userList, comparator);
            financeReportList = financeReportService.getFinanceReportList(map,startDate,endDate,userList);
            this.userName = this.getLoginInfo().getUserName();
            this.reportTime = new TimeWrapper(new Date().getTime());
            
            HttpSession session = getRequest().getSession();   
            session.setAttribute("userList", userList);
            session.setAttribute("financeReportList", financeReportList);
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("financeReportPageList action error:" + e.getMessage());
            return ERROR;
        }
    }
    
    /**
     * 导出财经要点统计报表
     */
    public void exportFinanceReport(){
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
            String reportName = title+"财经要点阅读情况统计表";
            getResponse().setHeader("Content-disposition","attachment; filename="+new String(reportName.getBytes("gbk"),"iso8859-1")+".xls");//设定输出文件头
            getResponse().setContentType("application/msexcel");//定义输出类型
            getResponse().setCharacterEncoding("UTF-8");
            outputStream = getResponse().getOutputStream();
            HttpSession session = getRequest().getSession();   
            List<FinanceReportBean> finList=(List<FinanceReportBean>) session.getAttribute("financeReportList");
            List<SysUserBean> userList=(List<SysUserBean>) session.getAttribute("userList");
            colsCount=columnIds.split(",").length;
            workbook=financeReportService.exportFinanceReportExcel(finList,userList, this.getLoginInfo().getUserName(),colsCount);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("exportFinanceReport action error:"+e.getMessage());
            log.error(e.getMessage());
            workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet();
            ExcelUtil.writeExcelDetailRow(sheet, new String[] { "导出报表发生系统异常" }, 0);
        }finally {
            ExcelUtil.writeToResponse(workbook, outputStream);
        }
    }
    
    /**
     * 财经要点报表统计明细
     * @return
     */
    public String financeReportDetail(){
        try {
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("userId", userId);
            map.put("columnId", columnId);
            this.getPage().setPageSize(7);
            articlePage=financeReportService.getFianceArticlePage(map, this.getPage(),aritcleFlag, startDate,endDate);
            SysUser ubean=sysUserService.getSysUserById(userId);
            userName=ubean.getUserName();
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("financeReportDetail action error:"+e.getMessage());
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

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public DeptFacadeService getDeptFacadeService() {
        return deptFacadeService;
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

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public FeColumnService getFeColumnService() {
        return feColumnService;
    }

    public void setFeColumnService(FeColumnService feColumnService) {
        this.feColumnService = feColumnService;
    }

    public List<FeColumn> getFeColumnList() {
        return feColumnList;
    }

    public void setFeColumnList(List<FeColumn> feColumnList) {
        this.feColumnList = feColumnList;
    }

    public String getColumns() {
        return columns;
    }

    public void setColumns(String columns) {
        this.columns = columns;
    }

    public void setFinanceReportService(FinanceReportService financeReportService) {
        this.financeReportService = financeReportService;
    }

    public List<FinanceReportBean> getFinanceReportList() {
        return financeReportList;
    }

    public void setFinanceReportList(List<FinanceReportBean> financeReportList) {
        this.financeReportList = financeReportList;
    }

    public List<SysUserBean> getUserList() {
        return userList;
    }

    public void setUserList(List<SysUserBean> userList) {
        this.userList = userList;
    }

    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    public JSONArray getColumnJson() {
        return columnJson;
    }

    public void setColumnJson(JSONArray columnJson) {
        this.columnJson = columnJson;
    }

    public String getColumnIds() {
        return columnIds;
    }

    public void setColumnIds(String columnIds) {
        this.columnIds = columnIds;
    }

    public int getColsCount() {
        return colsCount;
    }

    public void setColsCount(int colsCount) {
        this.colsCount = colsCount;
    }

    public Integer getColumnId() {
        return columnId;
    }

    public void setColumnId(Integer columnId) {
        this.columnId = columnId;
    }

    public Integer getAritcleFlag() {
        return aritcleFlag;
    }

    public void setAritcleFlag(Integer aritcleFlag) {
        this.aritcleFlag = aritcleFlag;
    }

    public PageUtil<BaseFeArticle> getArticlePage() {
        return articlePage;
    }

    public void setArticlePage(PageUtil<BaseFeArticle> articlePage) {
        this.articlePage = articlePage;
    }
}
