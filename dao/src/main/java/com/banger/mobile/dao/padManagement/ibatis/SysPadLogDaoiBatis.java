/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :PAD使用记录DAO实现
 * Author     :liyb
 * Create Date:2013-6-21
 */
package com.banger.mobile.dao.padManagement.ibatis;

import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.padManagement.SysPadLogDao;
import com.banger.mobile.domain.model.padManagement.PadUseLogBean;
import com.banger.mobile.domain.model.padManagement.SysPadLog;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author liyb
 * @version $Id: SysPadLogDaoiBatis.java,v 0.1 2013-6-21 下午01:39:50 liyb Exp $
 */
public class SysPadLogDaoiBatis extends GenericDaoiBatis implements SysPadLogDao {
    public SysPadLogDaoiBatis(){
        super(SysPadLog.class);
    }

    /**
     * 根据时间分组查询pad使用记录
     * @param map
     * @return
     */
    public List<PadUseLogBean> getByPadDateLogGroup(Map<String, Object> map) {
        return this.getSqlMapClientTemplate().queryForList("GetByPadDateLogGroup",map);
    }

    
    public List<PadUseLogBean> getPadLogGroup(Map<String, Object> map) {
        return this.getSqlMapClientTemplate().queryForList("GetPadLogGroup",map);
    }

    /**
     * 插入PAD使用记录
     * @param log
     */
    public void addPadLog(SysPadLog log) {
        this.getSqlMapClientTemplate().insert("AddPadLog",log);
    }

    /**
     * 查询pad使用记录数
     * @param padInfoId
     * @return
     */
    public Integer getPadLogCount(Integer padInfoId) {
        return (Integer) this.getSqlMapClientTemplate().queryForObject("GetPadLogCount",padInfoId);
    }

}
