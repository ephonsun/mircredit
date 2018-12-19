package com.banger.mobile.service;


import com.banger.mobile.facade.webservice.MapWebService;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-4-9
 * Time: 下午2:38
 * To change this template use File | Settings | File Templates.
 */
public class MapWebServiceImplTest {
//    @Test
//    public void addCustomerLngLatTest(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(MapWebService.class);
//            MapWebService service = (MapWebService) new XFireProxyFactory().create(
//            serviceModel, "http://127.0.0.1:8080/services/BangerCrmMapService");
//            String result = service.addCustomerLngLat("yangy","{customerId:201,gpsLng:120.08958,gpsLat:30.257432}");
//            System.out.println(result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    @Test
    public void getCustomerLngLatTest(){
        try {
            Service serviceModel = new ObjectServiceFactory().create(MapWebService.class);
            MapWebService service = (MapWebService) new XFireProxyFactory().create(
                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmMapService");
            String result = service.getCustomerLngLat(263);
            System.out.println(result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    @Test
    public void getMyMapCustomerTest(){
        try {
            Service serviceModel = new ObjectServiceFactory().create(MapWebService.class);
            MapWebService service = (MapWebService) new XFireProxyFactory().create(
                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmMapService");
            String result = service.getMyMapCustomer("wo","");
            System.out.println(result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
