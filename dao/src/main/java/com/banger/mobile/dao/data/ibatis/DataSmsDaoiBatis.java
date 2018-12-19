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
import com.banger.mobile.dao.data.DataSmsDao;
import com.banger.mobile.domain.model.data.Sms;
/**
 * @author yuanme
 * @version $Id: DataAuthDaoiBatis.java,v 0.1 2012-5-29 上午10:51:00 yuanme Exp $
 */
public class DataSmsDaoiBatis extends DataSuperDaoiBatis implements DataSmsDao {

    public DataSmsDaoiBatis() {
        super(DataSmsDao.class);
    }

    /**
     * @param persistentClass
     */
    @SuppressWarnings("unchecked")
    public DataSmsDaoiBatis(Class persistentClass) {
        super(DataSmsDao.class);
    }

    //以下为新资料管理功能
    /**
     * 根据客户查看短信列表（分页）
     * @param parameterMap
     * @param page
     * @return
     */
    public PageUtil<Sms> getCustomerSmsDataPage(Map<String, Object> parameterMap, Page page) {
        List<Sms> list = (List<Sms>) this.findQueryPage(
                "getCustomerSmsDataPage", "getCustomerSmsDataCount", parameterMap, page);
        return new PageUtil<Sms>(list, page);
    }

}
