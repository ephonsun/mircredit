/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :全局参数dao
 * Author     :yujh
 * Create Date:May 25, 2012
 */
package com.banger.mobile.dao.param;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.param.SysParam;
import com.banger.mobile.domain.model.param.SysParamFlow;

/**
 * @author yujh
 * @version $Id: SysParamDao.java,v 0.1 May 25, 2012 10:39:15 AM Administrator Exp $
 */
public interface SysParamDao {
    /**
     * 设置是否启用限流
     */
    public void updateIsActive(int num);
    /**
     * web端是否启用限流
     * @param num
     */
    public void updateIsActiveForWeb(int num);
    /**
     * 设置最大流量
     */
    public void updateMaxFlow(int maxBPS);
    /**
     * 查询流量控制参数
     * @return
     */
    public Map<String,Object> getFlowControlParam() ;
    
    /**
     * 查询系统全局参数
     * return Map<String,Object>
     */
    public Map<String,Object> querySysParam();
    
    /**
     * 修改系统全局参数
     * param map
     */
    public void updateSysParam(Map<String, Object> map);
    
    /**
     * 查询不被拦截的action集合
     */
    public String getExcludeActions();
    
    /**
     * 获取系统录音路径
     */
    public String getRecordPath();

    String getParamValueByKey(String key);

    /**
     * 获取录音存储预警值
     */
    public Integer getCueSize();
	/**
	 * 获取贷款最大值
	 * @return
	 */
	public String getMaxAppLoanMoney();
	/**
	 * 获取最大提前催款时间
	 * @return
	 */
	public String getMaxDunLoanTime();
	
	 public String getXFDDoubleApprovalTag();
	 public String getXFDDoubleApprovalValue();
	 public String getJYDDoubleApprovalTag();
	 public String getJYDDoubleApprovalValue();
	    
}
