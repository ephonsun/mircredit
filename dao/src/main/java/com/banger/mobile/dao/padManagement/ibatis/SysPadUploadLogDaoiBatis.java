/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :PAD上传记录表Dao实现
 * Author     :liyb
 * Create Date:2013-6-21
 */
package com.banger.mobile.dao.padManagement.ibatis;

import com.banger.mobile.dao.padManagement.SysPadUploadLogDao;
import com.banger.mobile.domain.model.padManagement.SysPadUploadLog;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author liyb
 * @version $Id: SysPadUploadLogDaoiBatis.java,v 0.1 2013-6-21 上午10:32:44 liyb Exp $
 */
public class SysPadUploadLogDaoiBatis extends GenericDaoiBatis implements SysPadUploadLogDao {

    public SysPadUploadLogDaoiBatis(){
        super(SysPadUploadLog.class);
    }

    /**
     * 插入流量记录
     * @param uploadLog
     */
    public void addPadUploadLog(SysPadUploadLog uploadLog) {
        this.getSqlMapClientTemplate().insert("AddPadUploadLog",uploadLog);
    }
    

}
