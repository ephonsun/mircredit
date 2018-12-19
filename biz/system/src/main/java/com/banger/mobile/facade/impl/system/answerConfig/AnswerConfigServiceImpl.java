/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :答录配置...
 * Author     :Administrator
 * Create Date:Jun 4, 2012
 */
package com.banger.mobile.facade.impl.system.answerConfig;

import java.util.HashMap;
import java.util.Map;

import com.banger.mobile.dao.answerConfig.AnswerConfigDao;
import com.banger.mobile.domain.model.answerConfig.AnswerConfig;
import com.banger.mobile.facade.answerConfig.AnswerConfigService;
import com.banger.mobile.util.StringUtil;

/**
 * @author Administrator
 * @version $Id: AnswerConfigServiceImpl.java,v 0.1 Jun 4, 2012 4:46:50 PM Administrator Exp $
 */
public class AnswerConfigServiceImpl implements AnswerConfigService {
    private AnswerConfigDao answerConfigDao;

    public void setAnswerConfigDao(AnswerConfigDao answerConfigDao) {
        this.answerConfigDao = answerConfigDao;
    }

    /**
     * 查询答录音信息
     * @return AnswerConfig
     * @see com.banger.mobile.facade.answerConfig.AnswerConfigService#queryAnswerConfig()
     */
    public AnswerConfig queryAnswerConfig(int userId){
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("configType",1);
        return this.answerConfigDao.queryAnswerConfig(map);
    }
    
    /**
     * 查询来电提示音信息
     * @return String  path
     * @see com.banger.mobile.facade.answerConfig.AnswerConfigService#queryCallerConfig()
     */
    public String queryCallerConfig(int userId){
        Map<String, Object> map=new HashMap<String, Object>();
        AnswerConfig answerConfig=new AnswerConfig();
        map.put("userId", userId);
        map.put("configType",2);
        answerConfig=this.answerConfigDao.queryAnswerConfig(map);
        if(answerConfig==null||StringUtil.isEmpty(answerConfig.getVoiceFilePath())){
            return "";
        }else{
            return answerConfig.getVoiceFilePath();
        }
    }
    
    /**
     * 修改
     * @param answerConfig
     * @see com.banger.mobile.facade.answerConfig.AnswerConfigService#updateAnswerConfig(com.banger.mobile.domain.model.answerConfig.AnswerConfig)
     */
    public void updateAnswerConfig(AnswerConfig answerConfig) {
        this.answerConfigDao.updateAnswerConfig(answerConfig);
    }

    /**
     * 新增配置
     * @param userId
     * @see com.banger.mobile.facade.answerConfig.AnswerConfigService#addAnswerConfig(AnswerConfig)
     */
    public void addAnswerConfig(AnswerConfig answerConfig) {
        answerConfig.setRingCount(3);
        answerConfig.setIsMute(1);
        this.answerConfigDao.addAnswerConfig(answerConfig);
    }

    /**
     * 删除答录配置
     */
    public void deleteAnswerConfig(int userId) {
        this.answerConfigDao.deleteAnswerConfig(userId);
    }

    
    public AnswerConfig queryAnswerConfigById(Integer id) {
        return this.answerConfigDao.queryAnswerConfigById(id);
    }

}
