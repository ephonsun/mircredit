package com.banger.mobile.service;

import com.banger.mobile.domain.model.base.customer.BaseCrmCustomer;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.facade.BaseServiceTestCase;
import com.banger.mobile.facade.customer.CrmCustomerService;

import java.util.List;

public class CrmCustomerServiceTest extends BaseServiceTestCase {

    private CrmCustomerService crmCustomerService;

    public void setCrmCustomerService(CrmCustomerService crmCustomerService) {
        this.crmCustomerService = crmCustomerService;
    }

    public void testServiceNotNull() throws Exception {
        assertNotNull(crmCustomerService);
    }

    public void testgetRecordInfoById() throws Exception {
    	BaseCrmCustomer info = new BaseCrmCustomer();
//        info = crmCustomerService.getCrmCustomerById(9);
//        assertEquals("001", info.getCustomerNo());
    }

    public void testaddRecordInfo() throws Exception {
//    	CrmCustomer info = new CrmCustomer();
//    	info.setAddress("杭州");
//    	info.setAge(25);
//    	info.setBelongDeptId(1);
//    	info.setBelongUserId(1);
//    	info.setBirthday(new Date());
//    	info.setCity("杭州");
//    	info.setCompany("baihang");
//    	info.setCreateDate(new Date());
//    	info.setCreateUser(1);
//    	info.setCustomerIndustryId(0);
//    	info.setCustomerName("xuhj");
//    	info.setCustomerNamePinyin("xuhj");
//    	info.setCustomerNo("001");
//    	info.setCustomerTitle("xuhj先生");
//    	info.setCustomerTypeId(0);
//    	info.setDefaultPhoneType(2);
//    	info.setEmail("");
//    	info.setFax("");
//    	info.setFaxExt("");
//    	info.setIdCard("330122198804091734");
//    	info.setIsDel(0);
//    	info.setIsTrash(0);
//    	info.setIsVisit(0);
//    	info.setLastContactDate(new Date());
//    	info.setMobilePhone1("13605817053");
//    	info.setPhone("");
//    	info.setPhoneExt("");
//    	info.setProvince("浙江");
//    	info.setRemark("kehujianjie");
//    	info.setSex("男");
//    	info.setTemplateIds("");
//    	info.setUpdateDate(new Date());
//    	info.setUpdateUser(1);
//    	
////    	crmCustomerService.addCrmCustomer(info);
//        assertNotNull(info);
//        log.info("id====:" + info.getCustomerId());
//        setComplete();
//        endTransaction();

    }
    
    public void testupdateRecordInfo() throws Exception {
//    	try{
//    		
//    		CrmCustomer info = new CrmCustomer();
//            info.setCustomerId(240);
//            info.setCustomerNo("102");
//            info.setUpdateUser(100);
//            crmCustomerService.updateCrmCustomer(info);
//            //info = crmCustomerService.getCrmCustomerById(9);
//            //log.info("update date====:" + info.getUpdateDate());
//            setComplete();
//            endTransaction();
//    	}catch(Exception e){
//    		e.printStackTrace();
//    		
//    	}
    	
    }
    
    public void testdeleteRecordInfo() throws Exception {
//    	crmCustomerService.deleteCrmCustomer(9);
//        assertNull(crmCustomerService.getCrmCustomerById(9));
        //setComplete();
        //endTransaction();
    }
    
    public void testgetRecordInfoPage() throws Exception {
//        Map<String, Object> parameterMap = new HashMap<String, Object>();
//        Page page = new Page();
//        PageUtil<CrmCustomer> crmOperateLogList = crmCustomerService
//            .getCrmCustomerPage(parameterMap, page);
//        log.info("total page======:" + crmOperateLogList.getPage().getTotalPages());
//        log.info("size======:" + crmOperateLogList.getItems().size());
    }
    
    public void testgetMyCrmCustomerForPad(){
    	List<CrmCustomer> cuslist = crmCustomerService.getMyCrmCustomerForPad("30");
    }

    public void testCrmImportBatch(){


//        List<CrmCustomer> list = new ArrayList<CrmCustomer>();
//        CrmCustomer crmCustomer = new CrmCustomer();
//        crmCustomer.setIdCard("8989898989099899");
//        crmCustomer.setCustomerName("zhangzhang");
//        crmCustomer.setEmail("1");
//        crmCustomer.setMobilePhone1("00000000000000");
//        crmCustomer.setPhone("124324444");
//        crmCustomer.setAddress("111111111");
//        crmCustomer.setBelongDeptId(3);
//        crmCustomer.setBelongUserId(10);
//        crmCustomer.setIsTrash(0);
//        crmCustomer.setIsDel(0);
//        crmCustomer.setIsVisit(0);
//        crmCustomer.setCreateUser(10);
//        crmCustomer.setUpdateUser(10);
//        crmCustomer.setMemo("memo");
//
//        list.add(crmCustomer);
//        crmCustomerService.crmImportBatch(list,list);

    }
}
