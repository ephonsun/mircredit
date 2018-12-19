/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :PAD上传记录表Dao
 * Author     :liyb
 * Create Date:2013-6-21
 */
package com.banger.mobile.dao.padManagement;

import com.banger.mobile.domain.model.padManagement.SysPadUploadLog;

/**
 * @author liyb
 * @version $Id: SysPadUploadLogDao.java,v 0.1 2013-6-21 上午10:19:32 liyb Exp $
 */
public interface SysPadUploadLogDao {

    /**
     * 插入流量记录
     * @param uploadLog
     */
    public void addPadUploadLog(SysPadUploadLog uploadLog);
}
