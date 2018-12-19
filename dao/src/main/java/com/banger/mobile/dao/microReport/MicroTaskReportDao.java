/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :任务报表DAO接口
 * Author     :liyb
 * Create Date:2013-12-26
 */
package com.banger.mobile.dao.microReport;

import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.microReport.LoanTaskReportBean;

/**
 * @author liyb
 * @version $Id: MicroTaskReportDao.java,v 0.1 2013-12-26 上午11:17:42 liyb Exp $
 */
public interface MicroTaskReportDao {

    /**
     * 贷款任务报表统计
     * @param map
     * @param searchType
     * @return
     */
    public List<LoanTaskReportBean> getLoanTaskReportList(Map<String,Object> map,String searchType);
    
    /**
     * 查询任务
     * @param map
     * @return
     */
    public List<LoanTaskReportBean> getTaskByTitle(Map<String,Object> map);
}
