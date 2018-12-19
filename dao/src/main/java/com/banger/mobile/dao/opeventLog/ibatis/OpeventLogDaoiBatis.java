/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :操作日志dao实现类
 * Author     :yujh
 * Create Date:May 23, 2012
 */
package com.banger.mobile.dao.opeventLog.ibatis;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.opeventLog.OpeventLogDao;
import com.banger.mobile.domain.model.log.OpeventLog;
import com.banger.mobile.domain.model.log.OpeventLogInfo;
import com.banger.mobile.domain.model.user.IUserInfo;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author yujh
 * @version $Id: OpeventLogDaoiBatis.java,v 0.1 May 23, 2012 10:49:52 AM Administrator Exp $
 */
public class OpeventLogDaoiBatis extends GenericDaoiBatis implements OpeventLogDao {

    public OpeventLogDaoiBatis(Class persistentClass) {
        super(persistentClass);
    }

    public OpeventLogDaoiBatis() {
        super(OpeventLog.class);
    }

    /**
     * 添加操作日志
     *  @param obj          操作对象
     * @param act           操作信息
     * @param state         操作状态，1成功，0失败
     * @param remark        备注
     * @see com.banger.mobile.dao.opeventLog.OpeventLogDao#addOpeventLog(java.lang.String, java.lang.String, int, java.lang.String)
     */
    public void addOpeventLog(OpeventLog opeventLog) {

        this.getSqlMapClientTemplate().insert("addOpeventLog", opeventLog);
    }

    /**
     * 分页&查询
     * @param parameters
     * @param page
     * @return
     * @see com.banger.mobile.dao.opeventLog.OpeventLogDao#getOpeventLogPage(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<OpeventLogInfo> getOpeventLogPage(Map<String, Object> parameters, Page page) {
        ArrayList<OpeventLogInfo> list = (ArrayList<OpeventLogInfo>) this.findQueryPage(
                "getOpeventLogPageMap", "getOpeventLogCount", parameters, page);
        if (list == null) {
            list = new ArrayList<OpeventLogInfo>();
        }
        return new PageUtil<OpeventLogInfo>(list, page);
    }


}
