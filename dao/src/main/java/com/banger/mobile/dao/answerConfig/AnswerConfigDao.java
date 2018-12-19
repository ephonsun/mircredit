/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :答录配置dao
 * Author     :yujh
 * Create Date:Jun 4, 2012
 */
package com.banger.mobile.dao.answerConfig;

import java.util.Map;

import com.banger.mobile.domain.model.answerConfig.AnswerConfig;

/**
 * @author yujh
 * @version $Id: AnswerConfigDao.java,v 0.1 Jun 4, 2012 4:12:21 PM Administrator Exp $
 */
public interface AnswerConfigDao {
    /**
     * 查询
     * @return
     */
    public AnswerConfig queryAnswerConfig(Map<String, Object> map);
    /**
     * 修改
     */
    public void updateAnswerConfig(AnswerConfig answerConfig);
    /**
     * 添加
     * @param answerConfig
     */
    public void addAnswerConfig(AnswerConfig answerConfig);
    /**
     * 删除
     * @param userId
     */
    public void deleteAnswerConfig(int userId);
    /**
     * 根据id查询答录配置
     * @param id
     * @return
     */
    public AnswerConfig queryAnswerConfigById(Integer id);
}
