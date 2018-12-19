/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :在线用户dao
 * Author     :yangy
 * Create Date:2012-8-13
 */
package com.banger.mobile.dao.user;

import java.util.List;

import com.banger.mobile.domain.model.user.CdOnlineStatus;

/**
 * @author yangyang
 * @version $Id: CdOnlineStatusDao.java,v 0.1 2012-8-13 下午3:21:04 yangyong Exp $
 */
public interface CdOnlineStatusDao {

    /**
     * 返回用户在线状态列表
     * @return
     */
    List<CdOnlineStatus> getCdOnlineStatus();
}
