/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :在线用户dao
 * Author     :yangy
 * Create Date:2012-8-13
 */
package com.banger.mobile.dao.user.ibatis;

import java.util.List;

import com.banger.mobile.dao.user.CdOnlineStatusDao;
import com.banger.mobile.domain.model.system.CdCity;
import com.banger.mobile.domain.model.user.CdOnlineStatus;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author yangyang
 * @version $Id: CdOnlineStatusDaoiBatis.java,v 0.1 2012-8-13 下午3:45:32 yangyong Exp $
 */
public class CdOnlineStatusDaoiBatis extends GenericDaoiBatis  implements CdOnlineStatusDao {

    @SuppressWarnings("unchecked")
    public CdOnlineStatusDaoiBatis() {
        super(CdOnlineStatus.class);
    }
    
    /**
     * 返回用户在线状态列表
     * @return
     * @see com.banger.mobile.dao.user.CdOnlineStatusDao#getCdOnlineStatus()
     */
    public List<CdOnlineStatus> getCdOnlineStatus() {
        return (List<CdOnlineStatus>)this.getSqlMapClientTemplate().queryForList("getCdOnlineStatus");
    }

}
