/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :营销任务执行action
 * Author     :yuanme
 * Version    :1.0
 * Create Date:May 3, 2012
 */
package com.banger.mobile.webapp.action.report;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.banger.mobile.domain.model.tskMarketing.TskMarketingPlanReport;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.tskMarketing.TskMarketingPlanReportService;
import com.banger.mobile.facade.tskMarketing.TskMarketingPlanService;
import com.banger.mobile.util.ExcelUtil;
import com.banger.mobile.webapp.action.BaseAction;

public class TskMarketingPlanReportAction extends BaseAction {
    private static final long             serialVersionUID = 1589326398928860527L;

    private TskMarketingPlanService       tskMarketingPlanService;
    private DeptFacadeService             deptFacadeService;
    private TskMarketingPlanReportService tskMarketingPlanReportService;

    private String                        containSub;
    private Date                          startDate;
    private Date                          endDate;
    private String                        userIds;
    private String                        deptIds;
    private String                        reportType;

    // 打开页面
    public String planReport() {
        try {
            if (deptFacadeService.isInChargeOfDepartment()) {
                request.setAttribute("isManage", 1);
            }
            String userName = this.getLoginInfo().getUserName();
            Integer userId = this.getLoginInfo().getUserId();
            request.setAttribute("userName", userName);
            request.setAttribute("userId", userId);
            return SUCCESS;
        } catch (Exception e) {
            log.error("planReport error", e);
            return ERROR;
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

    //查询报表数据
    public String planReportQuery() {
        try {
            String userName = this.getLoginInfo().getUserName();
            Integer userId = this.getLoginInfo().getUserId();
            request.setAttribute("userName", userName);
            request.setAttribute("startDate", startDate);
            request.setAttribute("endDate", endDate);
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            request.setAttribute("reportDate", dateformat.format(Calendar.getInstance().getTime()));
            request.setAttribute("userId", userId);
            if (containSub == null) {
                containSub = "1";
            }

            Map<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("planDateFrom", startDate);
            parameterMap.put("planDateTo", endDate);
            if (StringUtils.isNotEmpty(userIds)) {
                parameterMap.put("userIds", userIds);
            } else{
            	parameterMap.put("userIds", getUserIdsString(false));
            } 
            if (StringUtils.isNotEmpty(deptIds)) {
                if (containSub.equals("1")) {
                    String userIds = deptFacadeService.getStringUserIdContainsByDeptIds(deptIds);
                    parameterMap.put("userIds", userIds);
                } else {
                    parameterMap.put("deptIds", deptIds);
                }

            }

            List<TskMarketingPlanReport> list = tskMarketingPlanService
                .getMarketingPlanReport(parameterMap);
            //排名暂时不做处理，按查询处理顺序赋值
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setSortNo(i + 1);
            }

            request.setAttribute("dataList", list);
            return SUCCESS;
        } catch (Exception e) {
            log.error("planReport error", e);
            return ERROR;
        }
    }

    //报表 导出
    public void planReportExport() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        OutputStream outputStream = null;
        HSSFWorkbook workbook = null;
        try {
            String title = "";
            if (startDate != null && endDate != null) {
                title += df.format(startDate) + "至" + df.format(endDate);
            } else if (startDate != null && endDate == null) {
                title += df.format(startDate) + "之后";
            } else if (startDate == null && endDate != null) {
                title += "截止至" + df.format(endDate);
            }
            String reportName = title + "营销计划执行情况统计表 ";
            getResponse().setHeader(
                "Content-disposition",
                "attachment; filename=" + new String(reportName.getBytes("gbk"), "iso8859-1")
                        + ".xls");//设定输出文件头
            getResponse().setContentType("application/msexcel");//定义输出类型
            getResponse().setCharacterEncoding("UTF-8");
            outputStream = getResponse().getOutputStream();

            Map<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("planDateFrom", startDate);
            parameterMap.put("planDateTo", endDate);
            if(StringUtils.isNotEmpty(userIds)) {
                parameterMap.put("userIds", userIds);
            } else{
            	parameterMap.put("userIds", getUserIdsString(false));
            } 
            if(StringUtils.isNotEmpty(deptIds)) {
                if (containSub.equals("1")) {
                    String userIds = deptFacadeService.getStringUserIdContainsByDeptIds(deptIds);
                    parameterMap.put("userIds", userIds);
                } else {
                    parameterMap.put("deptIds", deptIds);
                }

            }

            List<TskMarketingPlanReport> list = tskMarketingPlanService
                .getMarketingPlanReport(parameterMap);

            //排名暂时不做处理，按查询处理顺序赋值
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setSortNo(i + 1);
            }
            workbook = tskMarketingPlanReportService.exportPlanReportExcel(list, this
                .getLoginInfo().getUserName());
        } catch (Exception e) {
            log.error("planReportExport action error:", e);
            workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet();
            ExcelUtil.writeExcelDetailRow(sheet, new String[] { "导出报表发生系统异常" }, 0);
        } finally {
            ExcelUtil.writeToResponse(workbook, outputStream);
        }
    }

    /* getter and setter */

    public void setTskMarketingPlanService(TskMarketingPlanService tskMarketingPlanService) {
        this.tskMarketingPlanService = tskMarketingPlanService;
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }

    public String getContainSub() {
        return containSub;
    }

    public void setContainSub(String containSub) {
        this.containSub = containSub;
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

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public void setTskMarketingPlanReportService(TskMarketingPlanReportService tskMarketingPlanReportService) {
        this.tskMarketingPlanReportService = tskMarketingPlanReportService;
    }
}
