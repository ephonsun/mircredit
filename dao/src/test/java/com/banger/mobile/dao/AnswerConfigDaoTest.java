/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :答录配置dao测试类
 * Author     :yujh
 * Create Date:Jun 4, 2012
 */
package com.banger.mobile.dao;

import com.banger.mobile.dao.answerConfig.AnswerConfigDao;
import com.banger.mobile.domain.model.answerConfig.AnswerConfig;

/**
 * @author yujh
 * @version $Id: AnswerConfigDaoTest.java,v 0.1 Jun 4, 2012 4:35:22 PM Administrator Exp $
 */
public class AnswerConfigDaoTest extends BaseDaoTestCase{
    private AnswerConfigDao answerConfigDao;

    public void setAnswerConfigDao(AnswerConfigDao answerConfigDao) {
        this.answerConfigDao = answerConfigDao;
    }
//    public void testDaoNotNull(){
//        assertNotNull(answerConfigDao);
//    }
    public void testQuery(){
//        System.out.print(answerConfigDao.queryAnswerConfig(1));
    }
//    public void testUpdate(){
//        AnswerConfig answerConfig = new AnswerConfig();
//        answerConfig.setIsMute(1);
//        answerConfig.setRingCount(4);
//        answerConfig.setVoiceFilePath("d:");
//        answerConfig.setUserId(1);
//        answerConfigDao.updateAnswerConfig(answerConfig);
//    }
//    public void testAdd(){
//        AnswerConfig answerConfig = new AnswerConfig();
//        answerConfig.setIsMute(1);
//        answerConfig.setRingCount(4);
//        answerConfig.setVoiceFilePath("d:");
//        answerConfig.setUserId(1);
//        answerConfigDao.addAnswerConfig(answerConfig);
//    }
}
