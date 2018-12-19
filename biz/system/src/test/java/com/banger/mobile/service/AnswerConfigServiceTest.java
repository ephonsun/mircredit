/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :答录配置service测试类
 * Author     :yujh
 * Create Date:Jun 4, 2012
 */
package com.banger.mobile.service;

import com.banger.mobile.domain.model.answerConfig.AnswerConfig;
import com.banger.mobile.facade.BaseServiceTestCase;
import com.banger.mobile.facade.answerConfig.AnswerConfigService;

/**
 * @author yujh
 * @version $Id: AnswerConfigServiceTest.java,v 0.1 Jun 4, 2012 4:50:21 PM Administrator Exp $
 */
public class AnswerConfigServiceTest extends BaseServiceTestCase{
    private AnswerConfigService answerConfigService;

    public void setAnswerConfigService(AnswerConfigService answerConfigService) {
        this.answerConfigService = answerConfigService;
    }
//    public void testServiceNotNull(){
//        assertNotNull(answerConfigService);
//    }
//    public void testQuery(){
//        System.out.print(this.answerConfigService.queryAnswerConfig(1));
//    }
    public void testUpdate(){
        AnswerConfig answerConfig = new AnswerConfig();
        answerConfig.setIsMute(1);
        answerConfig.setRingCount(4);
        answerConfig.setVoiceFilePath("d:");
        answerConfig.setUserId(3);
        this.answerConfigService.updateAnswerConfig(answerConfig);
    }
//    public void testAdd(){
//        this.answerConfigService.addAnswerConfig(3);
//    }
}
