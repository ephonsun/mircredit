/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :地图模块webservice
 * Author     :yangy
 * Create Date:2013-4-09
 */
package com.banger.mobile.facade.webservice;

import javax.jws.WebService;

/**
 * User: yangy
 * Date: 13-4-9
 * Time: 上午11:36
 */
@WebService
public interface MapWebService {

    /**
     * PAD地图标注添加客户
     * @param account
     * @param customerJson
     * @return
     */
    public String addCustomerLngLat(String account,String customerJson);

    /**
     * PAD地图我的客户/搜索客户
     * @param account
     * @param condition
     * @return
     */
    public String getMyMapCustomer(String account, String condition);

    /**
     * PAD根据客户id获取经纬度
     * @param customerId
     * @return
     */
    public String getCustomerLngLat(Integer customerId);


    //客户经理（经纬度列表  user为查询条件  模糊匹配 姓名、帐号）
    String getUserLocationList(String account,String user);

}
