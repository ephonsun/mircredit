package com.banger.mobile.service;

import com.banger.mobile.facade.webservice.TaskWebService;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;
import org.junit.Test;

import java.net.MalformedURLException;

/**
 * @author Administrator
 * @version $Id: LoanWebServiceTest.java v 0.1 ${} 下午1:47 Administrator Exp $
 */
public class TaskWebServiceTest {
//    @Test
//    public void xxxxxxxxxx(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(TaskWebService.class);
//            TaskWebService service = (TaskWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmTaskService");
//            String result = service.getTaskList("xuc", "", 0,1,0);
//            System.out.println(result);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testGetScheduleList(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(TaskWebService.class);
//            TaskWebService service = (TaskWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmTaskService");
//            String result = service.getScheduleList("zhangfp","",-1,-1,1,2,-1);
//            System.out.println(result);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testGetTaskById(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(TaskWebService.class);
//            TaskWebService service = (TaskWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmTaskService");
//            String result = service.getTaskById("zyl",606);
//            System.out.println(result);
//        }catch (MalformedURLException e){
//            e.printStackTrace();
//        }
//    }

//    @Test
//    public void getUnFinishedSchedule(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(TaskWebService.class);
//            TaskWebService service = (TaskWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmTaskService");
//            String result = service.executeTask("huangll",141,260,"a","b");
//            System.out.println(result);
//        }catch (MalformedURLException e){
//            e.printStackTrace();
//        }
//    }

//    @Test
//    public void getTaskList(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(TaskWebService.class);
//            TaskWebService service = (TaskWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmTaskService");
//            //account,input,status,contactWayType,page,isLate,customerId
//            String result = service.getTaskList("yuanme","",-1,1,-1);
//            System.out.println(result);
//        }catch (MalformedURLException e){
//            e.printStackTrace();
//        }
//    }
    
    @Test
    public void testGetCustomerTaskList(){
        try {
            Service serviceModel = new ObjectServiceFactory().create(TaskWebService.class);
            TaskWebService service = (TaskWebService) new XFireProxyFactory().create(
                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmTaskService");
            String result = service.getCustomerTaskList("liyb", 45036, 20);
            System.out.println(result);
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
    }
    
    @Test
    public void testGetMarketTaskList(){
        try {
            Service serviceModel = new ObjectServiceFactory().create(TaskWebService.class);
            TaskWebService service = (TaskWebService) new XFireProxyFactory().create(
                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmTaskService");
            String result = service.getMarketTaskListCount("zyl", "", "", "", 962);
            System.out.println(result);
            
            String result1 = service.getMarketTaskList("huangk", "", "", "", 962, 1);
            System.out.println(result1);
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
    }
    
    @Test
    public void testGetPhoneTaskList(){
        try {
            Service serviceModel = new ObjectServiceFactory().create(TaskWebService.class);
            TaskWebService service = (TaskWebService) new XFireProxyFactory().create(
                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmTaskService");
            String result = service.getPhoneTaskList("zhangyl","",57,1,-1);
            System.out.println(result);
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
    }
    @Test
    public void testGetIntentProductList(){
        try {
            Service serviceModel = new ObjectServiceFactory().create(TaskWebService.class);
            TaskWebService service = (TaskWebService) new XFireProxyFactory().create(
                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmTaskService");
            String result = service.getIntentProductList("wo",496065,1);
            System.out.println(result);
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
    }
    @Test
    public void test(){
        new Child("milk");
    }

}


class People{
    String name;
    public People() {
        System.out.println(1);
    }
    public People(String name) {
        System.out.println(2);
        this.name= name;
    }

}
class Child extends People{
    People father;
    public Child(String name) {
        System.out.println(3);
        this.name= name;
        father = new People(name+":F");
    }
    public Child(){
        System.out.println(4);
    }
}