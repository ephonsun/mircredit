/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :答录配置dao实现类
 * Author     :yujh
 * Create Date:Jun 4, 2012
 */
package com.banger.mobile.dao.answerConfig.ibatis;

import java.util.Map;

import com.banger.mobile.dao.answerConfig.AnswerConfigDao;
import com.banger.mobile.domain.model.answerConfig.AnswerConfig;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author yujh
 * @version $Id: AnswerConfigDaoiBatis.java,v 0.1 Jun 4, 2012 4:15:35 PM Administrator Exp $
 */
@SuppressWarnings("unchecked")
public class AnswerConfigDaoiBatis extends GenericDaoiBatis implements AnswerConfigDao {

    public AnswerConfigDaoiBatis(Class persistentClass) {
        super(persistentClass);
    }
    public AnswerConfigDaoiBatis(){
        super(AnswerConfig.class);
    }

    /**
     * 查询
     * @return
     * @see com.banger.mobile.dao.answerConfig.AnswerConfigDao#queryAnswerConfig()
     */
    public AnswerConfig queryAnswerConfig(Map<String, Object> map) {
        AnswerConfig answerConfig=new AnswerConfig();
        return(AnswerConfig)this.getSqlMapClientTemplate().queryForObject("queryAnswerConfig",map);
    }

    /**
     * 修改
     * @param answerConfig
     * @see com.banger.mobile.dao.answerConfig.AnswerConfigDao#updateAnswerConfig(com.banger.mobile.domain.model.answerConfig.AnswerConfig)
     */
    public void updateAnswerConfig(AnswerConfig answerConfig) {
        this.getSqlMapClientTemplate().update("updateAnswerConfig", answerConfig);
    }
    /**
     * 添加
     * @param answerConfig
     * @see com.banger.mobile.dao.answerConfig.AnswerConfigDao#addAnswerConfig(com.banger.mobile.domain.model.answerConfig.AnswerConfig)
     */
    public void addAnswerConfig(AnswerConfig answerConfig) {
        this.getSqlMapClientTemplate().insert("addAnswerConfig", answerConfig);
    }
    /**
     * 删除
     * @param userId
     */
    public void deleteAnswerConfig(int userId) {
        this.getSqlMapClientTemplate().delete("deleteAnswerConfig", userId);
    }
    /**
     * 根据id查询答录配置
     * @param id
     * @return
     */
    public AnswerConfig queryAnswerConfigById(Integer id) {
        return(AnswerConfig)this.getSqlMapClientTemplate().queryForObject("queryAnswerConfigById",id);
    }

}
