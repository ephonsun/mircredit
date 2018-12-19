/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :产品通知用户-Dao-接口实现
 * Author     :QianJie
 * Create Date:Dec 18, 2012
 */
package com.banger.mobile.dao.microProduct.ibatis;

import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.microProduct.PdtNoticeUserDao;
import com.banger.mobile.domain.model.microProduct.PdtNoticeUser;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author QianJie
 * @version $Id: PdtNoticeUserDaoiBatis.java,v 0.1 Dec 18, 2012 3:54:39 PM QianJie Exp $
 */
public class PdtNoticeUserDaoiBatis extends GenericDaoiBatis implements PdtNoticeUserDao{

    public PdtNoticeUserDaoiBatis() {
        super(PdtNoticeUserDao.class);
    }
    
    public PdtNoticeUserDaoiBatis(Class persistentClass) {
        super(PdtNoticeUserDao.class);
    }

    /**
     * 批量添加产品通知-用户
     * @param list
     * @see com.banger.mobile.dao.product.PdtNoticeUserDao#addPdtNoticeUserBatch(java.util.List)
     */
    public void addPdtNoticeUserBatch(List<PdtNoticeUser> list) {
        this.exectuteBatchInsert("addPdtNoticeUser", list);
    }

    /**
     * 批量删除产品通知-用户
     * @param conds
     * @see com.banger.mobile.dao.product.PdtNoticeUserDao#delPdtNoticeUserReadByConds(java.util.Map)
     */
    public void delPdtNoticeUserReadByConds(Map<String, Object> conds) {
        this.getSqlMapClientTemplate().delete("delPdtNoticeUserReadByConds",conds);
    }

   
}
