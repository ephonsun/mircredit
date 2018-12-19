/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :未读留言 来电dao接口
 * Author     :yangy
 * Create Date:2012-6-11
 */
package com.banger.mobile.dao.unreadMessage;

import com.banger.mobile.domain.model.unreadMessage.CrmUnreadMessage;

/**
 * @author yangyang
 * @version $Id: CrmUnreadMessageDao.java,v 0.1 2012-6-11 下午4:01:05 yangyong Exp $
 */
public interface CrmUnreadMessageDao {

    /**
     * 查询
     * @return
     */
    public CrmUnreadMessage queryCrmUnreadMessage(Integer userId);
}
