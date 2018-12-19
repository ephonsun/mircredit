/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :PAD使用记录service
 * Author     :liyb
 * Create Date:2013-6-21
 */
package com.banger.mobile.facade.padManagement;

import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.padManagement.PadUseLogBean;

/**
 * @author liyb
 * @version $Id: SysPadLogService.java,v 0.1 2013-6-21 下午01:45:51 liyb Exp $
 */
public interface SysPadLogService {

    /**
     * 根据时间分组查询pad使用记录
     * @param map
     * @return
     */
    public List<PadUseLogBean> getByPadDateLogGroup(Map<String,Object> map);
    
    public List<PadUseLogBean> getPadLogGroup(Map<String,Object> map);
    
    /**
     * 查询pad是否有使用记录
     * @param padInfoId
     * @return  true:存在记录  false:不存在记录
     */
    public boolean getPadLogCount(Integer padInfoId);
}
