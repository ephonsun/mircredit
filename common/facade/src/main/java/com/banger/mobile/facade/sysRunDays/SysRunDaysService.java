/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :系统运行时间...
 * Author     :cheny
 * Create Date:2012-9-6
 */
package com.banger.mobile.facade.sysRunDays;

import java.util.List;

import com.banger.mobile.domain.model.sysRunDays.SysRunDays;

/**
 * @author cheny
 * @version $Id: SysRunDaysService.java,v 0.1 2012-9-6 下午2:00:55 cheny Exp $
 */
public interface SysRunDaysService {
    /**
     * 插入运行时间
     */
    public void insertSysRunDays(SysRunDays runDays);
    /**
     * 查询运行天数
     */
    public List<SysRunDays> getSysRunDays();
}
