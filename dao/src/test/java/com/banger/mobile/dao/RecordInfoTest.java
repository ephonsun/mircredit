/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :¼��ʵ��Dao������
 * Author     :zhangxiang
 * Version    :1.0
 * Create Date:May 2, 2012
 */
package com.banger.mobile.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.record.RecordInfoDao;
import com.banger.mobile.domain.model.record.PhoneRecordBean;
import com.banger.mobile.domain.model.record.RecordDetail;
import com.banger.mobile.domain.model.record.RecordExportBean;
import com.banger.mobile.domain.model.record.RecordInfo;
import com.banger.mobile.domain.model.record.RecordInfoBean;

/**
 * @author zhangxiang
 * @version $Id: RecordInfoTest.java,v 0.1 May 2, 2012 3:03:51 PM zhangxiang Exp $
 */
public class RecordInfoTest extends BaseDaoTestCase {

    private RecordInfoDao recordInfoDao;

    public void setRecordInfoDao(RecordInfoDao recordInfoDao) {
        this.recordInfoDao = recordInfoDao;
    }
    
//    /**
//     * ����dao�Ƿ�测试修改录音状态��
//     * @throws Exception
//     */
//    public void testupdateRecordCanceled(){
//         Map<String, Object> m=new HashMap<String, Object>();
//         m.put("1", "132058");
//         recordInfoDao.updateRecordCanceled(m);
//    }
//    
//    /**
//     * ����dao�Ƿ�Ϊ��
//     * @throws Exception
//     */
//    public void testDaoNotNull() throws Exception {
//        assertNotNull(recordInfoDao);
//    }
//
//    /**
//     * ���Ը��Id�õ�¼���¼
//     * @throws Exception
//     */
//    public void testgetRecordInfoById() throws Exception {
//        RecordDetail info = new RecordDetail();
//        info = recordInfoDao.getRecordInfoById(11);
////        assertEquals("1330623415831", info.getRecordNo());
//    }
//
//    /**��¼���¼
//     * @throws Exception
//     */
//    public void testaddRecordInfo() throws Exception {
//        RecordInfo info = new RecordInfo();
//        info.setRecordInfoId(1);
//        info.setRecordNo("L12");
//        info.setUserId(93);
//        info.setCustomerId(0);
//        info.setCallType(7);
//        info.setRemotePhone("110");
//        info.setStartDate(new Date());
////        info.setEndDate(new Date());
//        info.setCustomerName("张三");
////        info.setBizType(1);
//        info.setIsCanceled(0);
//        info.setIsDel(0);
//        info.setIsArchived(0);
//        info.setContent("新年好");
//        info.setSplitCount(2);
//        info.setStatus("成功");
////        info.setVerifyUserId(1);
//        info.setCreateDate(new Date());
//        info.setCreateUser(11);
//        
////        recordInfoDao.addRecordInfo(info);
//        assertNotNull(info);
////        log.info("id====:" + info.getRecordInfoId());
//        System.out.println(info.getRecordInfoId());
//    }


//    /**
//     * ����ɾ��¼���¼
//     * @throws Exception
//     */
//    public void testdeleteRecordInfo() throws Exception {
//        recordInfoDao.deleteRecordInfo(132023);
//        assertNull(recordInfoDao.getRecordInfoById(132023));
////        recordInfoDao.deleteRecordInfo(795);
//        assertNull(recordInfoDao.getRecordInfoById(795));
//    }
//
//    /**
//     * 录音查询
//     * @throws Exception
//     */
//    public void testgetRecordInfoPage() throws Exception {
//        Map<String, Object> parameterMap = new HashMap<String, Object>();
////        parameterMap.put("recordNo", "133");
////        parameterMap.put("customerName", "t");
////        parameterMap.put("callType", "3");
//        parameterMap.put("datestart", "2012-02-20");
//        parameterMap.put("dateend", "2012-04-23");
//        Page page = new Page();
//        PageUtil<RecordInfoBean> crmOperateLogList = recordInfoDao
//            .getRecordInfoPage(parameterMap, page);
//        log.info("total count======:" +  crmOperateLogList.getPage().getTotalRowsAmount());
//        log.info("total page======:" + crmOperateLogList.getPage().getTotalPages());
//        log.info("page size======:" + crmOperateLogList.getItems().size());
//    }
//
//    /**
//     * ���Թ归档录音查询
//     * @throws Exception
//     */
//    public void testgetArchiveRecordInfoPage() throws Exception {
//        Map<String, Object> parameterMap = new HashMap<String, Object>();
//        parameterMap.put("datestart", "2012-05-18");
//        parameterMap.put("dateend", "2012-05-18");
//        Page page = new Page();
//        PageUtil<RecordInfoBean> crmOperateLogList = recordInfoDao.getRecordInfoPage(parameterMap, page);
//        log.info("total count======:" +  crmOperateLogList.getPage().getTotalRowsAmount());
//        log.info("total page======:" + crmOperateLogList.getPage().getTotalPages());
//        log.info("page size======:" + crmOperateLogList.getItems().size());
//        System.out.println(crmOperateLogList.getItems().size());
//    }
//    
//    /**
//     * 根据归档时长查询录音
//     * @throws Exception
//     */
//    public void testqueryRecordToGd() throws Exception{
//        Map<String, Object> map=new HashMap<String, Object>();
//        map.put("GdDATE", "2012-05-19 13:24:07");
//        List<RecordInfo> rlist=recordInfoDao.queryRecordToGd(map);
//        System.out.println(rlist.size());
//    }
//    
//    /**
//     * 测试根据id查询全字段
//     * @throws Exception
//     */
//    public void testqueryAllById() throws Exception{
//        RecordInfo rlist= recordInfoDao.queryAllById(132021);
//        System.out.println(rlist.getRecordNo());
//    }
//    /**
//     * ����ɾ��¼���¼
//     * @throws Exception
//     */
//    public void testdeleteRecordInfo() throws Exception {
//        recordInfoDao.deleteRecordInfo(132023);
//        assertNull(recordInfoDao.getRecordInfoById(132023));
////        recordInfoDao.deleteRecordInfo(795);
//        assertNull(recordInfoDao.getRecordInfoById(795));
//    }
//
//    /**
//     * 录音查询
//     * @throws Exception
//     */
//    public void testgetRecordInfoPage() throws Exception {
//        Map<String, Object> parameterMap = new HashMap<String, Object>();
////        parameterMap.put("recordNo", "133");
////        parameterMap.put("customerName", "t");
////        parameterMap.put("callType", "3");
//        parameterMap.put("datestart", "2012-02-20");
//        parameterMap.put("dateend", "2012-04-23");
//        Page page = new Page();
//        PageUtil<RecordInfoBean> crmOperateLogList = recordInfoDao
//            .getRecordInfoPage(parameterMap, page);
//        log.info("total count======:" +  crmOperateLogList.getPage().getTotalRowsAmount());
//        log.info("total page======:" + crmOperateLogList.getPage().getTotalPages());
//        log.info("page size======:" + crmOperateLogList.getItems().size());
//    }
//
//    /**
//     * ���Թ归档录音查询
//     * @throws Exception
//     */
//    public void testgetArchiveRecordInfoPage() throws Exception {
//        Map<String, Object> parameterMap = new HashMap<String, Object>();
//        parameterMap.put("datestart", "2012-05-18");
//        parameterMap.put("dateend", "2012-05-18");
//        Page page = new Page();
//        PageUtil<RecordInfoBean> crmOperateLogList = recordInfoDao.getRecordInfoPage(parameterMap, page);
//        log.info("total count======:" +  crmOperateLogList.getPage().getTotalRowsAmount());
//        log.info("total page======:" + crmOperateLogList.getPage().getTotalPages());
//        log.info("page size======:" + crmOperateLogList.getItems().size());
//        System.out.println(crmOperateLogList.getItems().size());
//    }
//    
//    /**
//     * 根据归档时长查询录音
//     * @throws Exception
//     */
//    public void testqueryRecordToGd() throws Exception{
//        Map<String, Object> map=new HashMap<String, Object>();
//        map.put("GdDATE", "2012-05-19 13:24:07");
//        List<RecordInfo> rlist=recordInfoDao.queryRecordToGd(map);
//        System.out.println(rlist.size());
//    }
//    
//    /**
//     * 测试根据id查询全字段
//     * @throws Exception
//     */
//    public void testqueryAllById() throws Exception{
//        RecordInfo rlist= recordInfoDao.queryAllById(132021);
//        System.out.println(rlist.getRecordNo());
//    }
    
    /**
     * 测试查询导出的联系记录
     */
//    public void testqueryRecordInfosByMap() throws  Exception{
//        Map<String, Object> map=new HashMap<String, Object>();
//        List<RecordExportBean> list=recordInfoDao.queryRecordInfosByMap(map);
//        System.out.println(list.size());
//    }
    
//    public void testQueryCustomerCallInfoByCusId() throws  Exception{
//        Page page=new Page();
//        page.setCurrentPage(2);
//        page.setPageStartRow(20);
//        page.setPageEndRow(40);
//        List<RecordInfoBean> list=recordInfoDao.queryCustomerCallInfoByCusId(30,1*10,2*10);
//        System.out.println(list.size());
//    }
    
    public void testGetRecordInfoByNo() throws  Exception{
        Map<String, Object> map=new HashMap<String, Object>();
        PhoneRecordBean phoneRecordBean=recordInfoDao.getRecordInfoByNo("1");
        System.out.println(phoneRecordBean.getRecordNo());
    }

    public void testgetRecentlyRecordForSchedule(){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("customerId",302);
        map.put("contactDate","2013-09-10 09:32:05");
        Integer recordInfoId = recordInfoDao.getRecentlyRecordForSchedule(map);
        System.out.println(recordInfoId);
    }
}
