/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户资料 dao实现
 * Author     :yuanme
 * Create Date:2012-5-29
 */
package com.banger.mobile.dao.data.ibatis;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.data.DataMmsDao;
import com.banger.mobile.domain.model.data.Mms;
/**
 * @author yuanme
 * @version $Id: DataAuthDaoiBatis.java,v 0.1 2012-5-29 上午10:51:00 yuanme Exp $
 */
public class DataMmsDaoiBatis extends DataSuperDaoiBatis implements DataMmsDao{

    public DataMmsDaoiBatis() {
        super(DataMmsDao.class);
    }

    /**
     * @param persistentClass
     */
    @SuppressWarnings("unchecked")
    public DataMmsDaoiBatis(Class persistentClass) {
        super(DataMmsDao.class);
    }

    //以下为新资料管理功能
    /**
     * 根据客户查看短信列表（分页）
     * @param parameterMap
     * @param page
     * @return
     */
    public PageUtil<Mms> getCustomerMmsDataPage(Map<String, Object> parameterMap, Page page) {
        List<Mms> list = (List<Mms>) this.findQueryPage(
                "getCustomerMmsDataPage", "getCustomerMmsDataCount", parameterMap, page);
        return new PageUtil<Mms>(list, page);
    }

}
