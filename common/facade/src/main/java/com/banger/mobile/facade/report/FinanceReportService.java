/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :财经要点报表service接口
 * Author     :liyb
 * Create Date:2012-12-10
 */
package com.banger.mobile.facade.report;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.base.finance.BaseFeArticle;
import com.banger.mobile.domain.model.report.FinanceReportBean;
import com.banger.mobile.domain.model.user.SysUserBean;

/**
 * @author liyb
 * @version $Id: FinanceReportService.java,v 0.1 2012-12-10 下午01:56:12 liyb Exp $
 */
public interface FinanceReportService {
    /**
     * 查询财经要点报表
     * @param map
     * @return
     */
    public List<FinanceReportBean> getFinanceReportList(Map<String,Object> map,String startDate,String endDate,List<SysUserBean> userBeanList);
    
    /**
     * 财经要点数据表转化为Excel格式
     * @param finList 财经List
     * @param userList 用户List
     * @param userName 当前用户名
     * @return
     */
    public HSSFWorkbook exportFinanceReportExcel(List<FinanceReportBean> finList,List<SysUserBean> userList,String userName,int colsCount);
    
    /**
     * 财经要点报表明细
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<BaseFeArticle> getFianceArticlePage(Map<String, Object> parameters, Page page,Integer flag,String startDate,String endDate);
}
