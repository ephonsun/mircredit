/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :任务报表service接口
 * Author     :liyb
 * Create Date:2013-12-26
 */
package com.banger.mobile.facade.microReport;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.banger.mobile.domain.model.microReport.LoanTaskReportBean;

/**
 * @author liyb
 * @version $Id: MicroTaskReportService.java,v 0.1 2013-12-26 上午11:37:13 liyb Exp $
 */
public interface MicroTaskReportService {
    /**
     * 贷款任务报表统计
     * @param map
     * @param searchType
     * @return
     */
    public List<LoanTaskReportBean> getLoanTaskReportList(Map<String,Object> map,String searchType);
    
    /**
     * 导出贷款任务报表
     * @param loanTskList
     * @param userName 用户名
     * @return
     */
    public HSSFWorkbook exportLoanTaskReportExcel(List<LoanTaskReportBean> loanTskList,boolean isDept,String userName);
    
    /**
     * 导出营销任务报表
     * @param loanTskList
     * @param userName 用户名
     * @return
     */
    public HSSFWorkbook exportMicroTskMarketingReportExcel(List<LoanTaskReportBean> loanTskList,boolean isDept,String userName);
    
    /**
     * 查询任务
     * @param map
     * @return
     */
    public List<LoanTaskReportBean> getTaskByTitle(Map<String,Object> map);
}
