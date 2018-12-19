/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :答录配置service
 * Author     :yujh
 * Create Date:Jun 4, 2012
 */
package com.banger.mobile.facade.answerConfig;

import java.util.HashMap;
import java.util.Map;

import com.banger.mobile.domain.model.answerConfig.AnswerConfig;

/**
 * @author yujh
 * @version $Id: AnswerConfigService.java,v 0.1 Jun 4, 2012 4:44:49 PM Administrator Exp $
 */
public interface AnswerConfigService {
    /**
     * 查询答录音信息
     * @return AnswerConfig
     * @see com.banger.mobile.facade.answerConfig.AnswerConfigService#queryAnswerConfig()
     */
    public AnswerConfig queryAnswerConfig(int userId);
    
    /**
     * 查询来电提示音信息
     * @return AnswerConfig
     * @see com.banger.mobile.facade.answerConfig.AnswerConfigService#queryCallerConfig()
     */
    public String queryCallerConfig(int userId);
    
    /**
     * 修改
     */
    public void updateAnswerConfig(AnswerConfig answerConfig);
    /**
     * 新增个人答录配置
     * @param answerConfig
     */
    public void addAnswerConfig(AnswerConfig answerConfig);
    /**
     * 删除答录配置
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
