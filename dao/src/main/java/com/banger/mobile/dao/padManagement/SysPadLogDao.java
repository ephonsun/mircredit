/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :PAD使用记录DAO
 * Author     :liyb
 * Create Date:2013-6-21
 */
package com.banger.mobile.dao.padManagement;

import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.padManagement.PadUseLogBean;
import com.banger.mobile.domain.model.padManagement.SysPadLog;

/**
 * @author liyb
 * @version $Id: SysPadLog.java,v 0.1 2013-6-21 下午01:36:39 liyb Exp $
 */
public interface SysPadLogDao {

    /**
     * 根据时间分组查询pad使用记录
     * @param map
     * @return
     */
    public List<PadUseLogBean> getByPadDateLogGroup(Map<String,Object> map);
    
    public List<PadUseLogBean> getPadLogGroup(Map<String,Object> map);
    
    /**
     * 插入PAD使用记录
     * @param log
     */
    public void addPadLog(SysPadLog log);
    
    /**
     * 查询pad使用记录数
     * @param padInfoId
     * @return
     */
    public Integer getPadLogCount(Integer padInfoId);
}
