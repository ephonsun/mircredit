/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :操作日志dao
 * Author     :yujh
 * Create Date:May 23, 2012
 */
package com.banger.mobile.dao.opeventLog;

import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.log.OpeventLog;
import com.banger.mobile.domain.model.log.OpeventLogInfo;

/**
 * @author yujh
 * @version $Id: OpeventLogDao.java,v 0.1 May 23, 2012 10:45:02 AM Administrator Exp $
 */
public interface OpeventLogDao {
    /**
     * 添加操作日志
     * @param obj           操作对象
     * @param act           操作信息
     * @param state         操作状态，1成功，0失败
     * @param remark        备注
     */
    public void addOpeventLog(OpeventLog  opeventLog);
    /**
     * 分页&查询
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<OpeventLogInfo> getOpeventLogPage(Map<String, Object> parameters, Page page);
}
