/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :录音业务service测试类
 * Author     :zhangxiang
 * Version    :1.0
 * Create Date:May 3, 2012
 */
package com.banger.mobile.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.record.RecordDetail;
import com.banger.mobile.domain.model.record.RecordInfo;
import com.banger.mobile.domain.model.record.RecordInfoBean;
import com.banger.mobile.facade.BaseServiceTestCase;
import com.banger.mobile.facade.record.RecordInfoService;

/**
 * @author zhangxiang
 * @version $Id: RecordInfoServiceTest.java,v 0.1 May 3, 2012 2:10:27 PM zhangxiang Exp $
 */
public class RecordInfoServiceTest extends BaseServiceTestCase {

    private RecordInfoService recordInfoService;

    public void setRecordInfoService(RecordInfoService recordInfoService) {
        this.recordInfoService = recordInfoService;
    }

    /**
     * 测试Manager是否为空
     * @throws Exception
     */
    public void testServiceNotNull() throws Exception {
        assertNotNull(recordInfoService);
    }

    /**
     * 测试根据Id得到录音记录
     * @throws Exception
     */
    public void testgetRecordInfoById() throws Exception {
        RecordDetail info = new RecordDetail();
        info = recordInfoService.getRecordInfoById(132035);
        assertEquals("1337577320109", info.getRecordNo());
    }

    /**
     * 测试添加一条录音记录
     * @throws Exception
     */
    public void testaddRecordInfo() throws Exception {
        RecordInfo info = new RecordInfo();
        info.setRecordNo("1330623415831");//设置流水号
        info.setUserId(205);//设置用户id
        info.setCallType(3);//设置呼叫类型
        info.setLocalPhone("13706519094");//设置本地号码
        info.setRemotePhone("13706519094"); //设置对方号码
        info.setStartDate(new Date());//设置开始时间
        info.setEndDate(new Date()); //设置结束时间
        info.setCallTime(30);//设置时长
        info.setCustomerName("test"); //设置客户姓名
        info.setBizType(1); //设置业务类型
        info.setIdCard("362227198506110014");//设置身份证号
        info.setCreditCard("12345678");//设置信用卡号
        info.setRemark("test");//设置备注
        info.setIsCanceled(0);//设置是否作废
        info.setFileName("test");//设置文件名称
        info.setFilePath("/usr/tomcat6035/webapps/ROOT/archivedRecord/20120426/");//设置文件存放路径
        info.setFileSize(28672l);//设置文件大小
        info.setFileMd5("");//设置md5
        info.setUploadDate(new Date()); //设置上传时间
        info.setIsDel(0);//设置是否删除
        info.setIsArchived(0);//是否归档
        info.setArchiveDate(new Date());//设置归档时间
        info.setCreateUser(99999999);//设置创建用户
        info.setUpdateUser(99999999);//设置更新用户
        recordInfoService.addRecordInfo(info);
        assertNotNull(info);
        log.info("id====:" + info.getRecordInfoId());
        setComplete();
        endTransaction();

    }
    
    /**
     * 测试修改录音记录
     * @throws Exception
     */
    public void testupdateRecordInfo() throws Exception {
        int id=132057;
        int isCanceled=1;
        recordInfoService.updateRecordCanceled(id,isCanceled);
    }
    
    
    /**
     * 测试删除录音记录
     * @throws Exception
     */
    public void testdeleteRecordInfo() throws Exception {
        recordInfoService.deleteRecordInfo(795);
        assertNull(recordInfoService.getRecordInfoById(795));
        setComplete();
        endTransaction();
    }
    
    /**
     * 测试录音记录分页
     * @throws Exception
     */
//    public void testgetRecordInfoPage() throws Exception {
//        Map<String, Object> parameterMap = new HashMap<String, Object>();
//        Page page = new Page();
//        PageUtil<RecordInfoBean> crmOperateLogList = recordInfoService
//            .getRecordInfoPage(parameterMap, page);
//        log.info("total page======:" + crmOperateLogList.getPage().getTotalPages());
//        log.info("size======:" + crmOperateLogList.getItems().size());
//    }
    
    public void testgetUnreadCount() throws Exception{
        
    }
}
