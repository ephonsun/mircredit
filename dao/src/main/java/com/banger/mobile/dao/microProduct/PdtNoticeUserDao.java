/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :产品通知用户-Dao-接口
 * Author     :QianJie
 * Create Date:Dec 18, 2012
 */
package com.banger.mobile.dao.microProduct;

import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.microProduct.PdtNoticeUser;

/**
 * @author QianJie
 * @version $Id: PdtNoticeUserDao.java,v 0.1 Dec 18, 2012 3:54:02 PM QianJie Exp $
 */
public interface PdtNoticeUserDao {

    /**
     * 批量添加产品通知-用户
     * @param list
     */
    public void addPdtNoticeUserBatch(List<PdtNoticeUser> list);
    
    /**
     * 批量删除产品通知-用户
     * @param conds
     */
    public void delPdtNoticeUserReadByConds(Map<String, Object> conds);
}
