package com.banger.mobile.service;

import com.banger.mobile.facade.webservice.ProductWebService;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;
import org.junit.Test;

import java.net.MalformedURLException;

/**
 * @author Administrator
 * @version $Id: LoanWebServiceTest.java v 0.1 ${} 下午1:47 Administrator Exp $
 */
public class ProductWebServiceTest {
//    @Test
//    public void xxxxxxxxxx(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(ProductWebService.class);
//            ProductWebService service = (ProductWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmProductService");
//            String result = service.queryCustomerByAddress("","三");
//            System.out.println(result);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//    }

//    @Test
//    public void addProductCustomer(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(ProductWebService.class);
//            ProductWebService service = (ProductWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmProductService");
//            String json = "{\"account\":\"huangll\",\"address\":\"考虑考虑\",\"contactTel\":\"65555555665\",\"contactTime\":\"\",\"scheduleRemark\":\"\",\"customerName\":\"啦啦啦\",\"email\":\"\",\"intentState\":\"考虑考虑\",\"intentproduct\":\"测试产品\",\"remark\":\"\",\"productId\":1,\"customerId\":0}\n";
//            String result = service.addProductCustomer(1,json);
//            System.out.println(result);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//    }

//    @Test
//    public void checkIntention(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(ProductWebService.class);
//            ProductWebService service = (ProductWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmProductService");
//            String json = "{\"intentState\":\"6126积极\",\"productId\":42,\"customerId\":432}";
//            String result = service.checkIntention("xuc1",833,141);
//            System.out.println(result);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//    }
//
    @Test
    public void getProductCustomerMicro(){
        try {
            Service serviceModel = new ObjectServiceFactory().create(ProductWebService.class);
            ProductWebService service = (ProductWebService) new XFireProxyFactory().create(
                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmProductService");
            String result = service.getProductCustomerMicro("wo",-1,"",1);
            System.out.println(result);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
//
//    @Test
//    public void addSchedule(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(ProductWebService.class);
//            ProductWebService service = (ProductWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmProductService");
//            String result = service.addSchedule("zyl",232,543,1,"2013-06-22 10:23","tttttt");
//            System.out.println(result);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//    }
    

}
