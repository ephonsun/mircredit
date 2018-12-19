/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :积分商城中我的客户DAO...
 * Author     :yangy
 * Create Date:Aug 15, 2012
 */
package com.banger.mobile.dao.points;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.points.JfMyCustomer;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yangy
 * Date: 13-8-21
 * Time: 下午4:50
 * To change this template use File | Settings | File Templates.
 */
public interface JfMyCustomerDao {

    /**
     * 查询我的客户接口
     *
     * @param map 参数
     * @return
     */
    public PageUtil<JfMyCustomer> searchMyCustomer(Map<String, Object> map,Page page);

    /**
     * 移除我的客户接口
     *
     * @param map 参数
     * @return
     */
    public void removeMyCustomer(Map<String, Object> map);

    /**
     * 添加我的客户接口
     *
     * @param cust t积分客户实体
     * @return
     */
    public void addMyCustomer(JfMyCustomer cust);

    /**
     * 查询客户编码存在个数
     * @param map
     * @return
     */
    public Integer getMyCustomerByCustomerCode(Map<String, Object> map);
}
