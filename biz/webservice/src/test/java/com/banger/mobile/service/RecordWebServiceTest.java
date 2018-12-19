/**
 *
 */
package com.banger.mobile.service;

import java.io.File;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.banger.mobile.domain.model.microProduct.PdtProductCustomerBean;
import com.banger.mobile.domain.model.microTask.TskSchedule;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;
import org.junit.Test;

import com.banger.mobile.facade.webservice.RecordWebService;
import com.banger.mobile.util.FileUtil;

/**
 * @author taobao
 *
 */
public class RecordWebServiceTest {

//    @Test
//    public void testGetRecTypeCode() {
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(
//                serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            System.out.println(service.getRecTypeCode());
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    @Test
//    public void testgetCommProgressList() {
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(
//                serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            System.out.println(service.getCommProgressList());
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    @Test
//    public void testfindCustomer() {
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(
//                serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            System.out.println(service.findCustomer("admin", "李"));
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testGetMyCustomer() {
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(
//                serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            String customerString = service.getMyCustomer("admin");
//            System.out.println(customerString);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//    }
//
    @Test
    public void testUserLogin() {
        try {
            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(
                serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
            System.out.println(service.userLogin("yangy", "1111111"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
//
//    @Test
//    public void testUserLogout() {
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(
//                serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            System.out.println(service.userLogout("admin"));
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testgetCustomerInfo() {
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(
//                serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            System.out.println(service.getCustomerInfo(11));
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testgetTemplateList() {
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(
//                serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            System.out.println(service.getTemplateList());
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testgetCustomerTemplateInfo() {
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(
//                serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            System.out.println(service.getCustomerTemplateInfo(22, 86));
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testgetUnreadMessage() {
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(
//                serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            System.out.println(service.getUnreadMessage("huyb"));
//            System.out.println("进入");
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testFileValidate() {
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(
//                serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            System.out.println(service.fileValidate("123.wav",
//                "0363fb46eeac4a595a35516b3097b3f0"));
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//    }
//
    @Test
    public void testGetCustomerRecord() {
        try {
            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(
                serviceModel, "http://170.100.100.180:9080/micro/services/BangerCrmService");
            System.out.println(service.userLogin("yuanme","123456"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
//
//    @Test
//    public void testGetAllRiskResult() {
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(
//                serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            System.out.println(service.getAllRiskResult("zhangyl"));
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testFindRiskResult() {
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(
//                serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            System.out.println(service.findRiskResult("zhangyl", "123"));
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testSaveRiskResultRemark() {
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(
//                serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            System.out.println(service.saveRiskResultRemark(1, "李明"));
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testGetAllRiskTemplate() {
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(
//                serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            System.out.println(service.getAllRiskTemplate());
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testGetAllTemplateQuestion() {
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(
//                serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            System.out.println(service.getAllTemplateQuestion(149));
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testGetCustomerRiskResult() {
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(
//                serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            System.out.println(service.getCustomerRiskResult("admin", 1, 4,
//                "[{\"questionId\":1,\"optionIds\":\"2\"},{\"questionId\":2,\"optionIds\":\"5\"}]"));
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testGetAllIdType() {
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(
//                serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            System.out.println(service.getAllIdType());
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testGetAllIntervalType() {
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(
//                serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            System.out.println(service.getAllIntervalType());
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testGetAllPlan() {
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(
//                serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            System.out.println(service.getAllPlan("admin"));
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testFindPlan() {
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(
//                serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            System.out.println(service.findPlan("admin", "李明", "2012-07-18 15:17:50",
//                "2012-07-30 15:17:50"));
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testNewPlan() {
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(
//                serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            System.out
//                .println(service
//                    .newPlan(
//                        "admin",
//                        "{\"age\":20,\"planDate\":\"2012-11-11 11:11:11\",\"sex\":\"女\",\"planName\":\"个人理财规划\",\"customerName\":\"李华\",\"customerNo\":\"LH323232\",\"phone\":\"15987486895\",\"idNo\":\"1234\",\"houseMonthlyRepayment\":223.0,\"houseValue\":5266.0,\"availableInvestMoney\":563.0,\"retireMonthlyCost\":233.0,\"inflation\":6.0,\"houseCreditRate\":6.0,\"investIncomingRate\":2.0,\"monthlyDeposit\":223.0,\"monthlyFamilyExpend\":22.0,\"monthlyFamilyOutlay\":333.0,\"houseCreditRemaining\":566.0,\"intervalTypeId\":2,\"planId\":0,\"customerId\":0,\"retireAge\":22,\"idTypeId\":1,\"retireYearLimit\":53,\"houseCreditYearLimit\":23}"));
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testEditPlan() {
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(
//                serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            System.out
//                .println(service
//                    .editPlan(
//                        "admin",
//                        "{\"age\":20,\"planId\":1071, \"customerId\":12, \"customerName\":\"老虎五只\",\"customerNo\":\"20111231\",\"planName\":\"个人理财规划\",\"planDate\":\"2012-01-24 12:12:12\",\"sex\":\"男\",\"phone\":\"1231231231\",\"idTypeId\":1,\"idNo\":\"123123123123123\",\"monthlyFamilyExpend\":1.00,\"monthlyFamilyOutlay\":1.00,\"monthlyDeposit\":1.00,\"availableInvestMoney\":1.00,\"houseValue\":1.00,\"houseCreditRemaining\":1.00,\"houseMonthlyRepayment\":1.00,\"houseCreditRate\":1.00,\"houseCreditYearLimit\":2,\"inflation\":1.00,\"intervalTypeId\":2,\"investIncomingRate\":1.00,\"retireAge\":2,\"retireYearLimit\":2,\"retireMonthlyCost\":1.00}"));
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testGetAllProduct() {
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(
//                serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            System.out.println(service.getAllProduct("admin"));
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testFindProduct() {
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(
//                serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            System.out.println(service.findProduct("admin", "计划", "5"));
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testGetAllProductSet() {
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(
//                serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            System.out.println(service.getAllProductSet());
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//    }
    
//    @Test
//    public void testGetProductCustomer(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(
//                serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            System.out.println(service.getProductCustomer(1,1));
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testGetCustomerBuyProduct() {
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(
//                serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            System.out.println(service.getCustomerBuyProduct(1,1));
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//    }

//    @Test
//    public void testGetPlanReport() {
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(
//                serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            System.out.println(service.getPlanReport(1060));
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//    }
    
//    @Test
//    public void testFindProductCustomerForPad() {
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(
//                serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            System.out.println(service.getProductCustomer(3,1,"huangk2"));
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//    }
    
//    @Test
//	public void testFindProductCustomerForPad() {
//		try {
//		      Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//		      RecordWebService service = (RecordWebService) new XFireProxyFactory().create(
//		          serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//		      service.getPhoneMarketList("yuanme", "", 100, 1, -1);
//		 } catch (MalformedURLException e) {
//		          e.printStackTrace();
//		 }
//	}
//
//    /**
//     * 查看日程列表
//     */
//    @Test
//    public void testGetScheduleList(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            //customerName=null,status=0,userId=11,contactType=1,contactDateFrom,contactEnd
//            //account,input,status,contactWayType,page,isLast
//
//            //account:huangll;input:null;status:-1;contactWayType:-1;page:1;isLate:1
//            String tskSchedules = service.getScheduleList("huang","",-1,-1,1,1);
//            System.out.println(tskSchedules);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 获得日程列表数量
//     */
//    @Test
//    public void testGetScheduleListCount(){
//        try{
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            //customerName=null,status=0,userId=11,contactType=1,contactDateFrom,contactEnd
//            //account,input,status,contactWayType,page,isLast
//            int count = service.getScheduleListCount("zhangfp",null,0,1,0);
//            System.out.println(count);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 标记日程沟通进度
//     */
//    @Test
//    public void testSetContactProgress(){
//        try{
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            boolean bool = service.setContactProgress(3,1);
//            System.out.println(bool);
//        }catch (Exception e){
//
//        }
//    }
//
//    /**
//     * 标记日程完成
//     */
//    @Test
//    public void testSetScheduleComplete(){
//        try{
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            boolean bool = service.setScheduleComplete(29);
//            System.out.println(bool);
//        }catch (Exception e){
//
//        }
//    }
//
//    /**
//     * 标记日程未完成
//     */
//    @Test
//    public void testCancelScheduleComplete(){
//        try{
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            boolean bool = service.cancelScheduleComplete(29);
//            System.out.println(bool);
//        }catch (Exception e){
//
//        }
//    }

    /**
     * PAD客户意向产品列表总数
     */
//    @Test
//    public void testGetIntentProductCount(){
//        try{
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            int rows = service.getIntentProductCount("zhangfp",1);
//            System.out.println(rows);
//        }catch (Exception e){
//
//        }
//    }

    /**
     * PAD客户意向产品列表
     */
//    @Test
//    public void testList(){
//        try{
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            System.out.println(service.getAllProduct("yuanme","",1));
//        }catch (Exception e){
//
//        }
//    }

//    @Test
//    public void testGetNeedAssignLoanList(){
//        try{
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            String result = service.getNeedAssignLoanList("zhangfp","2",null,1);
//            System.out.println(result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }
//
//    @Test
//    public void testGetNeedResearchLoanList(){
//        try{
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            String result = service.getNeedResearchLoanList("zhangfp","22222",1);
//            System.out.println(result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

//    @Test
//    public void testGetNeedCheckoutLoanList(){
//        try{
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            String result = service.getNeedCheckoutLoanList("zhangfp",null,1);
//            System.out.println(result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

//    @Test
//    public void testGetNeedRepaymentLoanList(){
//        try{
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            String result = service.getNeedRepaymentLoanList("zhangfp",null,2,1);
//            System.out.println(result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

//    @Test
//    public void testGetExceptionDunLoanList(){
//        try{
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            String result = service.getExceptionDunLoanList("zhangfp",null,1);
//            System.out.println(result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

//    @Test
//    public void testGetAllLoanList(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            String result = service.getAllLoanList("zhangfangping",null,null,1);
//            System.out.println(result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//    @Test
//    public void getLoanDetail(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            String result = service.getLoanDetail("zhangfp",4);
//            System.out.println(result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

//    @Test
//    public void addLoan(){
//        try{
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            StringBuilder sb = new StringBuilder();
//            sb.append("{");
//            sb.append("customerId:12,");
//            sb.append("loanTypeId:1,");
//            sb.append("loanSubTypeId:1,");
//            sb.append("coBorrowerList:[{");
//            sb.append("customerId:123");
//            sb.append("}],");
//            sb.append("guarantorList:[{");
//            sb.append("customerId:124");
//            sb.append("}]");
//            sb.append("}");
//            String loanJsonString = sb.toString();
//            Integer result = service.addLoan("zhangfp",loanJsonString);
//            System.out.println(result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

//    @Test
//    public void getIntentProductCount(){
//        try{
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            Integer rows = service.getIntentProductCount("huangll",53);
//            System.out.print(rows);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void getIntentProductList(){
//        try{
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            String productCustomerBeanList = service.getIntentProductList("huangll",53,1);
//            System.out.println(productCustomerBeanList);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

//    @Test
//    public void getAllLibrary(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

//    @Test
//    public void getAllLoanList(){
//         try {
//             Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//             RecordWebService service = (RecordWebService) new XFireProxyFactory().create(serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//             String result = service.getAllLoanList("huangll",null,-1,1);
//             System.out.println(result);
//         }catch (Exception e){
//             e.printStackTrace();
//         }
//    }

//    @Test
//    public void getNeedSubmitLoanList(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
//            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(serviceModel, "http://127.0.0.1:8080/services/BangerCrmService");
//            String result = service.getNeedSubmitLoanList("huangll",null,1);
//            System.out.println(result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
    @Test
    public void TestSaveVisit(){
        try {
            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(serviceModel,"http://127.0.0.1:8080/services/BangerCrmService");
//            service.saveVisit(2217,45081,"zyl",4,"hhh","2013-08-20 16:30:42","zhangyl","3");
//            service.saveVisit(-1,720,"*CRM-judy116",3,"fff","2013-08-27 11:22","zyl",null);
            service.saveVisit(2092,1386,"樊梨花",3,"ssssssdxxxx","2013-09-02 14:03:45","zhangyl","4");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFileValidateBySize(){
        try {
            Service serviceModel = new ObjectServiceFactory().create(RecordWebService.class);
            RecordWebService service = (RecordWebService) new XFireProxyFactory().create(serviceModel,"http://127.0.0.1:8080/services/BangerCrmService");
//            service.saveVisit(2217,45081,"zyl",4,"hhh","2013-08-20 16:30:42","zhangyl","3");
            Long size = 100L;
            service.fileValidateBySize("aaa",size);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
