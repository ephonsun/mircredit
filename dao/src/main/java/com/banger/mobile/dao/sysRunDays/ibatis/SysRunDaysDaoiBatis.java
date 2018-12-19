/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :系统运行时间
 * Author     :cheny
 * Create Date:2012-9-6
 */
package com.banger.mobile.dao.sysRunDays.ibatis;

import java.util.List;

import com.banger.mobile.dao.sysRunDays.SysRunDaysDao;
import com.banger.mobile.domain.model.sysRunDays.SysRunDays;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author cheny
 * @version $Id: SysRunDaysDaoiBatis.java,v 0.1 2012-9-6 下午1:55:38 cheny Exp $
 */
public class SysRunDaysDaoiBatis extends GenericDaoiBatis  implements SysRunDaysDao{
    
    public SysRunDaysDaoiBatis(){
        super(SysRunDays.class);
    }

    /**
     * 插入运行时间
     */
    public void insertSysRunDays(SysRunDays runDays){
        this.getSqlMapClientTemplate().insert("insertSysRunDays",runDays);
    }
    /**
     * 查询运行天数
     */
    public List<SysRunDays> getSysRunDays(){
        return this.getSqlMapClientTemplate().queryForList("getSysRunDays");
    }
}
