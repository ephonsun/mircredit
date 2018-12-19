/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :风险测评模板类型
 * Author     :Administrator
 * Create Date:Jul 16, 2012
 */
package com.banger.mobile.dao.RskTempType.ibatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.RskTempType.RskTempTypeDao;
import com.banger.mobile.domain.model.rskTempType.RskTempType;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author Administrator
 * @version $Id: RskTempTypeDaoiBatis.java,v 0.1 Jul 16, 2012 10:06:04 AM Administrator Exp $
 */
public class RskTempTypeDaoiBatis extends GenericDaoiBatis implements RskTempTypeDao{

    public RskTempTypeDaoiBatis() {
        super(RskTempType.class);
    }

    /**
     * 添加
     */
    public void addRskTempType(RskTempType rskTempType) {
        this.getSqlMapClientTemplate().insert("addRskTempType", rskTempType);
    }

    /**
     * 删除
     */
    public void deleteRskTempType(int tempTypeId) {
        this.getSqlMapClientTemplate().delete("deleteRskTempType", tempTypeId);
    }

    /**
     * 查询
     */
    public RskTempType queryRskTempType(int tempTypeId) {
        return (RskTempType)this.getSqlMapClientTemplate().queryForObject("queryRskTempType",tempTypeId);
    }

    /**
     * 列表
     */
    public PageUtil<RskTempType> rskTempTypeList(Map<String, Object> parameters, Page page) {
        ArrayList<RskTempType> list = (ArrayList<RskTempType>) this.findQueryPage(
                "", "", parameters, page);
        if (list == null) {
            list = new ArrayList<RskTempType>();
        }
        return new PageUtil<RskTempType>(list, page);
    }

    /**
     * 更新
     */
    public void updRskTempType(RskTempType rskTempType) {
        this.getSqlMapClientTemplate().update("updateRskTempType", rskTempType);
    }

    /**
     * 所有风险测评模板类型
     */
    public List<RskTempType> getRskTempTypeList() {
        return this.getSqlMapClientTemplate().queryForList("queryAllType");
    }

}
