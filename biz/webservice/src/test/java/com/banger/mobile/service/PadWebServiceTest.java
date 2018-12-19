/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :liyb
 * Create Date:2013-7-23
 */
package com.banger.mobile.service;

import java.net.MalformedURLException;

import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;
import org.junit.Test;

import com.banger.mobile.facade.webservice.RecordWebService;

/**
 * @author liyb
 * @version $Id: PadWebServiceTest.java,v 0.1 2013-7-23 下午02:10:01 liyb Exp $
 */
public class PadWebServiceTest {
    
    @Test
    public void getPadUseStatus(){
        try {
            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(
                    serviceModel, "http://127.0.0.1:8090/services/BangerCrmService");
            Integer result = service.getPadUseStatus("liyb", "X650445");
            System.out.println(result);
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
    }
    
//    @Test
//    public void useLog(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8090/services/BangerCrmService");
//            String result = service.useLog("liyb", "X630445", 2);
//            System.out.println("PAD使用记录："+result);
//        }catch (MalformedURLException e){
//            e.printStackTrace();
//        }
//    }
    
//    @Test
//    public void networkFlowLog(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8090/services/BangerCrmService");
//            service.networkFlowLog("liyb", "X650445", 52485,15853);
//        }catch (MalformedURLException e){
//            e.printStackTrace();
//        }
//    }
}
