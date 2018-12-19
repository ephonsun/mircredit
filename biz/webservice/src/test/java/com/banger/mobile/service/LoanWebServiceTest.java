package com.banger.mobile.service;

import com.banger.mobile.facade.webservice.LoanWebService;
import com.banger.mobile.importUtil.ImportUtil;
import com.banger.mobile.util.IDCardUtil;
import com.banger.mobile.util.Md5Encrypt;
import com.banger.mobile.util.StringUtil;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;
import org.junit.Test;

import java.net.MalformedURLException;
import java.util.Date;

/**
 * @author Administrator
 * @version $Id: LoanWebServiceTest.java v 0.1 ${} 下午1:47 Administrator Exp $
 */
public class LoanWebServiceTest {
//    @Test
//    public void getAllLoanList() {
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(LoanWebService.class);
//            LoanWebService service = (LoanWebService) new XFireProxyFactory().create(
//                serviceModel, "http://127.0.0.1:8080/services/BangerCrmLoanService");
//            String result = service.getAllLoanList("zhangfp",null,-1,1);
//            System.out.println(result);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//
//    }

    @Test
    public void getAppInfo(){
        try {
            Service service=new ObjectServiceFactory().create(LoanWebService.class);
            LoanWebService loanWebService=(LoanWebService)new XFireProxyFactory().create(service,"http://170.100.120.54:9080/micro/services/BangerCrmLoanService");

            String result=loanWebService.getAppFormGua("008371",64);
            System.out.println(result);
        }catch (Exception e){
            e.printStackTrace();
        
        }
    }


    @Test
    public void getLoanDetail(){
        try {
            Service serviceModel = new ObjectServiceFactory().create(LoanWebService.class);
            LoanWebService service = (LoanWebService) new XFireProxyFactory().create(
                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmLoanService");
            String result = service.getLoanCountAndAmountList();
            System.out.println("result="+result);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
//        System.out.println(Md5Encrypt.md5("123456"));
    }

//    @Test
//    public void addLoan(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(LoanWebService.class);
//            LoanWebService service = (LoanWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmLoanService");
////            String loanJsonString = "{\"loanSubTypeId\":0,\"guarantorList\":[{\"customerId\":0}],\"loanTypeId\":10042,\"coBorrowerList\":[{\"customerId\":0},{\"customerId\":42}],\"customerId\":142";
//            /*
//            {
//            "loanSubTypeId":0,
//            "guarantorList":[{"customerId":0}],
//            "loanTypeId":10042,
//            "coBorrowerList":[{"customerId":0},{"customerId":42}],
//            "customerId":142,
//            "loanId":164}
//             */
//            StringBuilder sb = new StringBuilder();
//            sb.append("{customerId:12,loanTypeId:1,loanSubTypeId:12,loanId:520,");
//            sb.append("coBorrowerList:[{customerId:123}],");
//            sb.append("guarantorList:[{customerId:124}]}");
//            String loanJsonString = sb.toString();
////            Integer result = service.addLoan("huangll",loanJsonString);
//            String result = service.addLoan("huangll",loanJsonString);
//            System.out.println(result);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//    }

//    @Test
//    public void getAssignUserList(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(LoanWebService.class);
//            LoanWebService service = (LoanWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmLoanService");
//            String result = service.getAssignUserList("zhangfp");
//            System.out.println(result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testSubmitLoan(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(LoanWebService.class);
//            LoanWebService service = (LoanWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:808e0/services/BangerCrmLoanService");
//            String result = service.submitLoan("zyl",1669,"",1);
//            System.out.println(result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

//    @Test
//    public void testApplyLoan(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(LoanWebService.class);
//            LoanWebService service = (LoanWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmLoanService");
//            String result = service.applyLoan("zhangfp",178,"zhangfp apply");
//            System.out.println(result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testGetCountByStatus(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(LoanWebService.class);
//            LoanWebService service = (LoanWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmLoanService");
//            String result = service.getStatusLoanCount("wo");
//            System.out.println(result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testGetNeedSubmitLoanList(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(LoanWebService.class);
//            LoanWebService service = (LoanWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmLoanService");
//            String result = service.getNeedSubmitLoanList("zhangfp",null,1);
//            System.out.println(result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testGetLoanDetail(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(LoanWebService.class);
//            LoanWebService service = (LoanWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmLoanService");
//            String result = service.getLoanDetail("huangll",520);
//            System.out.println(result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

//    @Test
//    public void getNeedRepaymentLoanList(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(LoanWebService.class);
//            LoanWebService service = (LoanWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmLoanService");
//            String result = service.getNeedRepaymentLoanList("zhangfp","",-1,1);
//            System.out.println(result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void getStatusLoanCount(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(LoanWebService.class);
//            LoanWebService service = (LoanWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmLoanService");
//            String result = service.getStatusLoanCount("zyl");
//            System.out.println(result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

//    @Test
//    public void testGetApprovalUser(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(LoanWebService.class);
//            LoanWebService service = (LoanWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmLoanService");
//            String result = service.getApprovalUser("zhangfp",402,"zhangfangping","1111111");
//            System.out.println(result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

//
//    @Test
//    public void getAllLoanList(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(LoanWebService.class);
//            LoanWebService service = (LoanWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmLoanService");
//            String result = service.getAllLoanList("zyl","",1,1);
//            System.out.println(result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

//    @Test
//    public void dunLoan(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(LoanWebService.class);
//            LoanWebService service = (LoanWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmLoanService");
//            String result = service.dunLoan("huangll",345,"");
//            System.out.println(result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

//    @Test
//    public void getLoanType(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(LoanWebService.class);
//            LoanWebService service = (LoanWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmLoanService");
//            String result = service.getLoanType("huangll");
//            System.out.println(result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

//    @Test
//    public void getNeedSubmitLoanList(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(LoanWebService.class);
//            LoanWebService service = (LoanWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmLoanService");
//            String result = service.getNeedSubmitLoanList("zhangfp","",1);
//            System.out.println(result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//    @Test
//    public void getNeedResearchLoanList(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(LoanWebService.class);
//            LoanWebService service = (LoanWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmLoanService");
//            String result = service.getNeedResearchLoanList("zhangfp","",1);
//            System.out.println(result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

//    @Test
//    public void getNeedCheckoutLoanList(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(LoanWebService.class);
//            LoanWebService service = (LoanWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmLoanService");
//            String result = service.getNeedCheckoutLoanList("huangll","",1);
//            System.out.println(result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void getExceptionDunLoanList(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(LoanWebService.class);
//            LoanWebService service = (LoanWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmLoanService");
//            String result = service.getExceptionDunLoanList("huangll","86552",1);
//            System.out.println(result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void getNeedAssignLoanList(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(LoanWebService.class);
//            LoanWebService service = (LoanWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmLoanService");
//            String result = service.getNeedAssignLoanList("zhangfangping","",-1, 1);
//            System.out.println(result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void getCustomerLoanList(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(LoanWebService.class);
//            LoanWebService service = (LoanWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmLoanService");
//            String result = service.getCustomerLoanList(543,1);
//            System.out.println(result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void numNowNeedSubmitLoan(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(LoanWebService.class);
//            LoanWebService service = (LoanWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmLoanService");
//            String result = service.numNowNeedSubmitLoan("hua",5,6);
//            System.out.println(result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void numNewBeenAssignedLoan(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(LoanWebService.class);
//            LoanWebService service = (LoanWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmLoanService");
//            String result = service.numNewBeenAssignedLoan("zyl");
//            System.out.println(result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void queryCustomerByIdCardPhone(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(LoanWebService.class);
//            LoanWebService service = (LoanWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmLoanService");
//            String result = service.queryCustomerByIdCardPhone("zyl","","",1);
//            System.out.println(result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void isLoanFail(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(LoanWebService.class);
//            LoanWebService service = (LoanWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmLoanService");
//            String result = service.isLoanFail("zyl",533);
//            System.out.println(result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void numNowNeedResearchLoan(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(LoanWebService.class);
//            LoanWebService service = (LoanWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmLoanService");
//            String result = service.numNowNeedResearchLoan("huangll",48,-1);
//            System.out.println(result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void numNewNeedAssignLoan(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(LoanWebService.class);
//            LoanWebService service = (LoanWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmLoanService");
//            String result = service.numNewNeedAssignLoan("huangll");
//            System.out.println(result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void cancelLoan(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(LoanWebService.class);
//            LoanWebService service = (LoanWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmLoanService");
////            String result = service.cancelLoan("zhangfp",1254,"cancelLoan",6,"");
////            System.out.println(result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void getLendingLoanList(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(LoanWebService.class);
//            LoanWebService service = (LoanWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmLoanService");
//            String result = service.getLendingLoanList("zhangfp","",1);
//            System.out.println(result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void getApprovalLoan(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(LoanWebService.class);
//            LoanWebService service = (LoanWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmLoanService");
////            String json = "{\"customerName\":\"zhangfp\",\"approvalStart\":\"2013-6-4 12:23\"}";
//            String json = "{\"customerName\":\"王晓\",\"idCard\":\"\",\"phone\":\"\",\"approvalEnd\":\"\",\"submitEnd\":\"\"," +
//                    "\"loanType\":-1,\"approvalStart\":\"\",\"submitStart\":\"\"}";
//            String result = service.searchApprovalLoan("zhangfp",json);
//            System.out.println(result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void approvalPass(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(LoanWebService.class);
//            LoanWebService service = (LoanWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmLoanService");
////            String json = "{\"customerName\":\"zhangfp\",\"approvalStart\":\"2013-6-4 12:23\"}";
//            String json = "{\"customerName\":\"mapinsert\",\"idCard\":\"\",\"phone\":\"\",\"approvalEnd\":\"\",\"submitEnd\":\"\"," +
//                    "\"loanType\":\"\",\"approvalStart\":\"\",\"submitStart\":\"\"}";
//            String result = service.approvalPass("zyl",1669,"remark","zhangyl","2013-06-08 16:40");
//            System.out.println(result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void editLoan(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(LoanWebService.class);
//            LoanWebService service = (LoanWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmLoanService");
//            String loanJsonString = "{\"loanMoney\":\"\",\"guarantorList\":[],\"customerId\":227383,\"loanStatusId\":3,\"loanSubTypeId\":0,\"loanTypeId\":1,\"coBorrowerList\":[{\"oldCustomerId\":227381,\"customerId\":227382}],\"oldCustomerId\":227383,\"loanId\":2001}\n";
//            String result = service.editLoan("zhangfp",loanJsonString);
//            System.out.println(result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void changeLoan(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(LoanWebService.class);
//            LoanWebService service = (LoanWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmLoanService");
//            String loanJsonString = "{\"loanId\":100,\"oldCustomerId\":303024,\"customerId\":303027}";
//            String result = service.changeLoan("zhangfp",loanJsonString);
//            System.out.println(result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void writeLoanHistory(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(LoanWebService.class);
//            LoanWebService service = (LoanWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmLoanService");
//            String result = service.writeLoanHistory("zhangfp",81,1,"remark");
//            System.out.println(result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void addEditPhotoRemark(){
//        String uuid = "2cd24f6b-9b5c-46d4-90e5-88aa095744dd";
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(LoanWebService.class);
//            LoanWebService service = (LoanWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmLoanService");
//            String result = service.addEditPhotoRemark("zhangfp",uuid,"remark");
//            System.out.println(result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

//    @Test
//    public void addEditPhotoType(){
//        String uuid = "683f5667-ff59-4ff7-b562-a4ad20a67d6e";
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(LoanWebService.class);
//            LoanWebService service = (LoanWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmLoanService");
//            String result = service.addEditPhotoType("zhangfp",uuid,1,"zhangfp",2);
//            System.out.println(result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        String birStr = IDCardUtil.conver15CardTo18("320520620918414");
//        Date birthday = ImportUtil.parseStringToDateBeforeToday(birStr);
//        System.out.print(birStr);
//    }

//    @Test
//    public void getAppFormDics(){
//        try {
//            Service serviceModel = new ObjectServiceFactory().create(LoanWebService.class);
//            LoanWebService service = (LoanWebService) new XFireProxyFactory().create(
//                    serviceModel, "http://127.0.0.1:8080/services/BangerCrmLoanService");
//            String result = service.getAppFormDics("STD_ZX_XB");
//            System.out.println(result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

}
