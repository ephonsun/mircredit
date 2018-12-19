/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :操作事件
 * Author     :yujh
 * Create Date:Aug 14, 2012
 */
package com.banger.mobile.facade.log;

import java.util.Date;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.log.OpeventLoginLogInfo;

/**
 * @author yujh
 * @version $Id: OpeventLoginLogService.java,v 0.1 Aug 14, 2012 10:53:43 AM Administrator Exp $
 */
public interface OpeventLoginLogService {
    /**
     * 
     * @param opeventObjId  操作对象id  
     * @param opeventAction 操作信息
     * @param clientTypeId  终端类型 1：web 2：pad
     * @param state 状态 1：成功 0：不成功
     * @param remark 备注
     */
    public void
    addLog(int opeventObjId, String opeventAction,
                       int clientTypeId, int state);

    /**
     * 获得登录日志
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<OpeventLoginLogInfo> getLoginLogPage(Map<String, Object> parameters, Page page);

    /**
     * 删除日志
     */
    public void deleteLog();
}
