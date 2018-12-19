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

import com.banger.mobile.facade.BaseServiceTestCase;
import com.banger.mobile.util.QuartzUtil;
import com.banger.mobile.facade.record.RecordInfoService;

/**
 * @author zhangxiang
 * @version $Id: RecordInfoServiceTest.java,v 0.1 May 3, 2012 2:10:27 PM zhangxiang Exp $
 */
public class QuartzUtilTest extends BaseServiceTestCase {
    
    private QuartzUtil quartzUtil;

   
    public void setQuartzUtil(QuartzUtil quartzUtil) {
        this.quartzUtil = quartzUtil;
    }


    /**
     * 测试Manager是否为空
     * @throws Exception
     */
    public void testServiceNotNull()  {
        assertNotNull(quartzUtil);
        TesttJob job = new TesttJob();
        String job_name ="11";
        try {
            Map<Object, Object> params = null;
            System.out.println("【系统启动】");
            quartzUtil.addJob(job_name,job,"0/5 * * * * ?",params);
            
           Thread.sleep(10000);
//            System.out.println("【修改时间】");
//            quartzUtil.modifyJobTime(job_name,"0/10 * * * * ?");
//            Thread.sleep(20000);
//            System.out.println("【移除定时】");
//            quartzUtil.removeJob(job_name);
//            Thread.sleep(10000);
//            
//            System.out.println("\n【添加定时任务】");
//            quartzUtil.addJob(job_name,job,"0/5 * * * * ?");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
