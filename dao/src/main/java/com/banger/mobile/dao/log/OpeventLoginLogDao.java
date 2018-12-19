/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :操作事件
 * Author     :yujh
 * Create Date:Aug 14, 2012
 */
package com.banger.mobile.dao.log;

import java.util.Date;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.log.OpeventLoginLog;
import com.banger.mobile.domain.model.log.OpeventLoginLogInfo;

/**
 * @author yujh
 * @version $Id: OpeventLoginLogDao.java,v 0.1 Aug 14, 2012 9:55:14 AM Administrator Exp $
 */
public interface OpeventLoginLogDao {
    /**
     * 添加日志
     */
    public void addLog(OpeventLoginLog log);
    /**
     * 分页
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<OpeventLoginLogInfo> getLogPage(Map<String, Object> parameters, Page page);
    /**
     * 删除日志
     */
    public void dropLog();

}
