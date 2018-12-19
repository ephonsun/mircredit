/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :未读留言 来电dao实现
 * Author     :yangy
 * Create Date:2012-6-11
 */
package com.banger.mobile.dao.unreadMessage.ibatis;

import com.banger.mobile.dao.unreadMessage.CrmUnreadMessageDao;
import com.banger.mobile.domain.model.remindConfig.RemindConfig;
import com.banger.mobile.domain.model.unreadMessage.CrmUnreadMessage;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author yangyang
 * @version $Id: CrmUnreadMessageDaoiBatis.java,v 0.1 2012-6-11 下午4:06:03 yangyong Exp $
 */
public class CrmUnreadMessageDaoiBatis extends GenericDaoiBatis implements CrmUnreadMessageDao {

    public CrmUnreadMessageDaoiBatis(Class persistentClass) {
        super(CrmUnreadMessage.class);
    }

    public CrmUnreadMessageDaoiBatis() {
        super(CrmUnreadMessage.class);
    }
    /**
     * @return
     * @see com.banger.mobile.dao.unreadMessage.CrmUnreadMessageDao#queryCrmUnreadMessage()
     */
    public CrmUnreadMessage queryCrmUnreadMessage(Integer userId) {
        return (CrmUnreadMessage)this.getSqlMapClientTemplate().queryForObject("queryCrmUnreadMessage",userId);
    }

}
