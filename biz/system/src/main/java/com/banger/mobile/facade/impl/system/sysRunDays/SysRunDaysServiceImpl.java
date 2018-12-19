/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :系统运行时间类...
 * Author     :cheny
 * Create Date:2012-9-6
 */
package com.banger.mobile.facade.impl.system.sysRunDays;

import java.util.List;

import com.banger.mobile.dao.sysRunDays.SysRunDaysDao;
import com.banger.mobile.domain.model.sysRunDays.SysRunDays;
import com.banger.mobile.facade.sysRunDays.SysRunDaysService;

/**
 * @author cheny
 * @version $Id: SysRunDaysServiceImpl.java,v 0.1 2012-9-6 下午2:02:04 cheny Exp $
 */
public class SysRunDaysServiceImpl implements SysRunDaysService{

    private SysRunDaysDao sysRunDaysDao;

    public void setSysRunDaysDao(SysRunDaysDao sysRunDaysDao) {
        this.sysRunDaysDao = sysRunDaysDao;
    }
    
    /**
     * 插入运行时间
     */
    public void insertSysRunDays(SysRunDays runDays){
        sysRunDaysDao.insertSysRunDays(runDays);
    }
    /**
     * 查询运行天数
     */
    public List<SysRunDays> getSysRunDays(){
        return this.sysRunDaysDao.getSysRunDays();
    }
}
