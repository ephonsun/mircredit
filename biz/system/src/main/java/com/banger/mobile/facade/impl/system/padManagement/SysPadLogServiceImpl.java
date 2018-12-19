/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :PAD使用记录service实现
 * Author     :liyb
 * Create Date:2013-6-21
 */
package com.banger.mobile.facade.impl.system.padManagement;

import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.padManagement.SysPadLogDao;
import com.banger.mobile.domain.model.padManagement.PadUseLogBean;
import com.banger.mobile.facade.padManagement.SysPadLogService;

/**
 * @author liyb
 * @version $Id: SysPadLogServiceImpl.java,v 0.1 2013-6-21 下午01:54:39 liyb Exp $
 */
public class SysPadLogServiceImpl implements SysPadLogService {

    private SysPadLogDao sysPadLogDao;

    public void setSysPadLogDao(SysPadLogDao sysPadLogDao) {
        this.sysPadLogDao = sysPadLogDao;
    }

    /**
     * 根据时间分组查询pad使用记录
     * @param map
     * @return
     */
    public List<PadUseLogBean> getByPadDateLogGroup(Map<String, Object> map) {
        return sysPadLogDao.getByPadDateLogGroup(map);
    }

    
    public List<PadUseLogBean> getPadLogGroup(Map<String, Object> map) {
        return sysPadLogDao.getPadLogGroup(map);
    }

    /**
     * 查询pad是否有使用记录
     * @param padInfoId
     * @return true:存在记录  false:不存在记录
     */
    public boolean getPadLogCount(Integer padInfoId) {
        Integer count=sysPadLogDao.getPadLogCount(padInfoId);
        if(count>0){
            return true;
        }else return false;
    }
    
}
