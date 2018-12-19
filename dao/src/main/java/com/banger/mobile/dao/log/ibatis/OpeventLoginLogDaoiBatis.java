/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :Administrator
 * Create Date:Aug 14, 2012
 */
package com.banger.mobile.dao.log.ibatis;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.log.OpeventLoginLogDao;
import com.banger.mobile.domain.model.log.OpeventLoginLog;
import com.banger.mobile.domain.model.log.OpeventLoginLogInfo;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author Administrator
 * @version $Id: OpeventLoginLogDaoiBatis.java,v 0.1 Aug 14, 2012 10:31:54 AM Administrator Exp $
 */
public class OpeventLoginLogDaoiBatis extends GenericDaoiBatis implements OpeventLoginLogDao{

    public OpeventLoginLogDaoiBatis(Class persistentClass) {
        super(persistentClass);
    }
    public OpeventLoginLogDaoiBatis(){
        super(OpeventLoginLogDaoiBatis.class);
    }
    /**
     *
     * @param log
     * @see com.banger.mobile.dao.log.OpeventLoginLogDao#addLog(com.banger.mobile.domain.model.log.OpeventLoginLog)
     */
    public void addLog(OpeventLoginLog log) {
        this.getSqlMapClientTemplate().insert("insertLog", log);
    }
    /**
     * 分页
     * @param parameters
     * @param page
     * @return
     * @see com.banger.mobile.dao.log.OpeventLoginLogDao#getLogPage(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<OpeventLoginLogInfo> getLogPage(Map<String, Object> parameters, Page page) {
        ArrayList<OpeventLoginLogInfo> list = (ArrayList<OpeventLoginLogInfo>)this.findQueryPage("forLogPage", "forLogCount", parameters, page);
        if(list==null){
            list = new ArrayList<OpeventLoginLogInfo>();
        }
        return  new PageUtil<OpeventLoginLogInfo>(list, page);
    }
    /**
     * 清空日志
     *
     * @see com.banger.mobile.dao.log.OpeventLoginLogDao#deleteLog()
     */
    public void dropLog() {
        this.getSqlMapClientTemplate().delete("dropLog");
    }

}
