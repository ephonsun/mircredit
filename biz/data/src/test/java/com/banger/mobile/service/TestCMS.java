/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :ThinkPad
 * Create Date:2013-6-26
 */
package com.banger.mobile.service;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

/**
 * @author ThinkPad
 * @version $Id: TestCMS.java,v 0.1 2013-6-26 下午03:12:00 ThinkPad Exp $
 */
public class TestCMS {
    public static void main(String[] args) {
        String docString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                           + "<Service name=\"CreateCaseService\""
                           + "url=\"http://localhost:7001/ImageCreditServer/services/ProcessMessage\"><User userId=\"000395\""
                           + "userName=\"孙佳\" deptId=\"101347\" loginToken=\"bb07f648fe8cbf8e6dbe1d6bd8cccb35\""
                           + "roleId=\"60009\" bizStep=\"\"/><Application appId=\"Credit\"/><In dataType=\"yddk\""
                           + "customerId=\"\" customerName=\"\"><Parameter dataType=\"yddk\""
                           + "id=\"20130605000395000742\" busiType=\"01221710\" busiName=\"扫描凭证影像\"/></In></Service>";
        // <Route appAlis=\\"Credit\\" branchId=\\"01999\\" type=\\"Chief\\">
        // <Domain
        // ip=\\"170.101.100.192\\" socketPort=\\"5555\\" webServiceUrl=\\"\\" methodName=\\"\\" nameSpace=\\"\\" servletUrl=\\"\\"/><Branch ip=\\"\\" webServiceUrl=\\"http://170.101.100.192:9080/DocumentManageServer/services/CommandService?wsdl\\" methodName=\\"execute\\" nameSpace=\\"http://www.topcheer.com\\" servletUrl=\\"http://170.101.100.192:9080/DocumentManageServer/servlet/download\\"/><Chief ip=\\"170.101.100.192\\" socketPort=\\"9080\\" webServiceUrl=\\"http://170.101.100.192:9080/DocumentManageServer/services/CommandService?wsdl\\" methodName=\\"execute\\" nameSpace=\\"http://www.topcheer.com\\" \"
        // +
        // \"servletUrl=\\"http://170.101.100.192:9080/DocumentManageServer/servlet/download\\"/></Route></Service>
        try {
            // Document doc=DocumentHelper.parseText(docString);
            Service service = new Service();
            Call call = (Call) service.createCall();
            call
                .setTargetEndpointAddress("http://170.101.100.191:9081/ImageAdminPortal/services/ProcessMessage");
            call.setOperationName(new QName("processRequest"));
            String result = (String) call.invoke(new Object[] { docString });
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
