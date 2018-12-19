/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :风险测评模板类型
 * Author     :Administrator
 * Create Date:Jul 16, 2012
 */
package com.banger.mobile.dao.RskTempType;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.rskTempType.RskTempType;

/**
 * @author Administrator
 * @version $Id: RskTempTypeDao.java,v 0.1 Jul 16, 2012 9:54:13 AM Administrator Exp $
 */
public interface RskTempTypeDao {
	/**
	 * 列表
	 * @param parameters
	 * @param page
	 * @return
	 */
    public PageUtil<RskTempType> rskTempTypeList(Map<String, Object> parameters, Page page);
    /**
     * 添加
     * @param rskTempType
     */
    public void addRskTempType(RskTempType rskTempType);
    /**
     * 删除
     * @param rskTempTypeId
     */
    public void deleteRskTempType(int rskTempTypeId);
    /**
     * 修改
     * @param rskTempType
     */
    public void updRskTempType(RskTempType rskTempType);
    /**
     * 查看
     * @param rskTempTypeId
     * @return
     */
    public RskTempType queryRskTempType(int rskTempTypeId);
    
    /**
     * 所有风险测评模板类型
     */
    public List<RskTempType> getRskTempTypeList();
}
