package com.banger.mobile.service;

import com.banger.mobile.facade.webservice.CreditsMallWebServices;
import com.banger.mobile.facade.webservice.MapWebService;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-8-15
 * Time: 下午5:47
 * To change this template use File | Settings | File Templates.
 */
public class CreditsMallServicesTest {
    @Test
    public void getQueryCustomerTest(){
        try {
            Service serviceModel = new ObjectServiceFactory().create(CreditsMallWebServices.class);
            CreditsMallWebServices service = (CreditsMallWebServices) new XFireProxyFactory().create(
                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmCreditsMallService");
            String result = service.queryCustomer("000699902","王进","320520196610184519");
            System.out.println(result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

   @Test
   public void  getCustomerDetailTest(){
       try {
           Service serviceModel = new ObjectServiceFactory().create(CreditsMallWebServices.class);
           CreditsMallWebServices service = (CreditsMallWebServices) new XFireProxyFactory().create(
                   serviceModel, "http://127.0.0.1:8080/services/BangerCrmCreditsMallService");
           String result = service.getCustomerDetail("000602","B32052019661018451901");
           System.out.println(result);
       }catch (Exception e){
           e.printStackTrace();
       }
   }
    @Test
    public void getCustomerAumTest(){
        try {
            Service serviceModel = new ObjectServiceFactory().create(CreditsMallWebServices.class);
            CreditsMallWebServices service = (CreditsMallWebServices) new XFireProxyFactory().create(
                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmCreditsMallService");
            String result = service.getCustomerAum("000602","B32052019661018451901");
            System.out.println(result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void queryGiftTest(){
        try {
            Service serviceModel = new ObjectServiceFactory().create(CreditsMallWebServices.class);
            CreditsMallWebServices service = (CreditsMallWebServices) new XFireProxyFactory().create(
                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmCreditsMallService");
            String result = service.queryGift("", "淘洗", "1", "20", "1");
            System.out.println(result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void exchangeGiftTest() {
        try {
            Service serviceModel = new ObjectServiceFactory().create(CreditsMallWebServices.class);
            CreditsMallWebServices service = (CreditsMallWebServices) new XFireProxyFactory().create(
                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmCreditsMallService");

            String str="{\"receiveName\":\"王进\",\"receiveAddress\":\"鑫鑫市场\",\"sendType\":\"0\",\"row\":[{\"JOD_INFONO\":\"10000000077\",\"JOD_NUM\":\"1\"}],\"receiveMobile\":\"116666474656\",\"receivePostCode\":\"644664\",\"hostNo\":\"10004306032\"}";
            String result = service.exchangeGift(str);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void queryOrderTest() {
        try {
            Service serviceModel = new ObjectServiceFactory().create(CreditsMallWebServices.class);
            CreditsMallWebServices service = (CreditsMallWebServices) new XFireProxyFactory().create(
                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmCreditsMallService");
            String result = service.queryOrder("10004306032","20130828","20130830","20","1");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void queryOrderDetailTest() {
        try {
            Service serviceModel = new ObjectServiceFactory().create(CreditsMallWebServices.class);
            CreditsMallWebServices service = (CreditsMallWebServices) new XFireProxyFactory().create(
                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmCreditsMallService");
            String result = service.queryOrderDetail("10000000025");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void cancelOrderTest() {
        try {
            Service serviceModel = new ObjectServiceFactory().create(CreditsMallWebServices.class);
            CreditsMallWebServices service = (CreditsMallWebServices) new XFireProxyFactory().create(
                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmCreditsMallService");
            String result = service.cancelOrder("10000000005","10004306032");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void queryCustomerPointTest() {
        try {
            Service serviceModel = new ObjectServiceFactory().create(CreditsMallWebServices.class);
            CreditsMallWebServices service = (CreditsMallWebServices) new XFireProxyFactory().create(
                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmCreditsMallService");
            String result = service.queryCustomerPoint("10004306032");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void queryCustomerPointDetailTest() {
        try {
            Service serviceModel = new ObjectServiceFactory().create(CreditsMallWebServices.class);
            CreditsMallWebServices service = (CreditsMallWebServices) new XFireProxyFactory().create(
                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmCreditsMallService");
            String result = service.queryCustomerPointDetail("10004306032","10","");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addMyCustomerTest(){
        try {
            Service serviceModel = new ObjectServiceFactory().create(CreditsMallWebServices.class);
            CreditsMallWebServices service = (CreditsMallWebServices) new XFireProxyFactory().create(
                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmCreditsMallService");
            String result = service.addMyCustomer("yangy","1000021","秦始皇","90","430423198710138233","15898930343", "");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void searchMyCustomerTest(){
        try {
            Service serviceModel = new ObjectServiceFactory().create(CreditsMallWebServices.class);
            CreditsMallWebServices service = (CreditsMallWebServices) new XFireProxyFactory().create(
                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmCreditsMallService");
            String result = service.searchMyCustomer("000602","秦",1);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void removeMyCustomerTest(){
        try {
            Service serviceModel = new ObjectServiceFactory().create(CreditsMallWebServices.class);
            CreditsMallWebServices service = (CreditsMallWebServices) new XFireProxyFactory().create(
                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmCreditsMallService");
            String result = service.removeMyCustomer("000602","B32052019661018451901");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getOrderPhotoTest(){
        try {
            Service serviceModel = new ObjectServiceFactory().create(CreditsMallWebServices.class);
            CreditsMallWebServices service = (CreditsMallWebServices) new XFireProxyFactory().create(
                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmCreditsMallService");
            String result = service.getOrderPhoto("000602","10000000025");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test(){
        List<String> list=null;
        if( CollectionUtils.isEmpty(list)){
            System.out.println("success");
        }

    }

}
