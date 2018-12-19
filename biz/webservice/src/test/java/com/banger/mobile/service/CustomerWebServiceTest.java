/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :huangk
 * Create Date:2013-3-22
 */
package com.banger.mobile.service;

import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;
import org.junit.Test;

import com.banger.mobile.facade.webservice.CustomerWebService;


/**
 * @author huangk
 * @version $Id: CustomerWebServiceTest.java,v 0.1 2013-3-22 上午11:51:29 huangk Exp $
 */
public class CustomerWebServiceTest {

    
    /**
     * 客户资料接口
     * @param customerId
     * @param eventId 1.营销 2.申请 3.调查 4.审批 5.落实 6.催收
     * @param type 1.录音  2.照片 3.视频 4.短信 5.彩信 6.资料表
     * @param page 页数
     * @param crmCustomerService
     */
    @Test
    public void TestGetCustomerData(){
        try {
            Service serviceModel = new ObjectServiceFactory().create(CustomerWebService.class);
            CustomerWebService service = (CustomerWebService) new XFireProxyFactory().create(serviceModel,"http://127.0.0.1:8080/services/BangerCrmCustomerService");
            service.getCustomerData(4,1,2,10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getcustomerlist(){
        try {
            Service serviceModel = new ObjectServiceFactory().create(CustomerWebService.class);
            CustomerWebService service = (CustomerWebService) new XFireProxyFactory().create(serviceModel,"http://127.0.0.1:8080/services/BangerCrmCustomerService");
            String result = service.getCustomerList("zhangfp","",1,"");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void saveCustomer(){
        try {
            Service serviceModel = new ObjectServiceFactory().create(CustomerWebService.class);
            CustomerWebService service = (CustomerWebService) new XFireProxyFactory().create(serviceModel,"http://127.0.0.1:8080/services/BangerCrmCustomerService");
            String crmStringJson = "{\"idCard\":\"null\",\"address\":\"null\",\"sex\":\"\",\"customerName\":\"null\",\"remark\":\"null\",\"phone\":\"\",\"mobilePhone2\":\"\",\"mobilePhone1\":\"18888000000\",\"isLimited\":0,\"isCustomerEdit\":1,\"defaultPhoneType\":1,\"customerId\":-1}";
            String result = service.saveCustomer("zhangfp",crmStringJson);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
