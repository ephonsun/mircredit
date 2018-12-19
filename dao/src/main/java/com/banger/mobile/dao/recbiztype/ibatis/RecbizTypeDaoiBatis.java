/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :业务类型接口实现类
 * Author     :liyb
 * Create Date:2012-5-17
 */
package com.banger.mobile.dao.recbiztype.ibatis;

import java.util.List;

import com.banger.mobile.dao.recbiztype.RecbizTypeDao;
import com.banger.mobile.domain.model.recbistype.RecbizType;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author liyb
 * @version $Id: RecbizTypeDaoiBatis.java,v 0.1 2012-5-17 下午03:59:06 liyb Exp $
 */
public class RecbizTypeDaoiBatis extends GenericDaoiBatis implements RecbizTypeDao {

    public RecbizTypeDaoiBatis() {
        super(RecbizType.class);
    }
    
    /**
     * 判断业务类型是否在使用
     * @param bizTypeId
     * @return
     */
    public Integer isTypeUse(Integer bizTypeId) {
        return (Integer) this.getSqlMapClientTemplate().queryForObject("IsTypeUseCount",bizTypeId);
    }

    /**
     * 根据业务编号删除(单个删除)
     * @param bizTypeId
     */
    public void deleteRecbizTypeById(Integer bizTypeId) {
        this.getSqlMapClientTemplate().update("DeleteRecbizTypeById", bizTypeId);
    }

    /**
     * 修改启用/停用状态
     * @param recbizType
     */
    public void updateActived(RecbizType recbizType) {
        this.getSqlMapClientTemplate().update("UpdataActived",recbizType);
    }

    /**
     * 添加业务类型
     * @param recbizType
     */
    public void insertRecbiztype(RecbizType recbizType) {
        this.getSqlMapClientTemplate().insert("InsertRecbiztype", recbizType);
    }

    /**
     * 判断业务类型编号和名称是否存在
     * @param sqlId
     * @param recbizType
     * @return
     */
    public Integer validation(String sqlId, RecbizType recbizType) {
        return (Integer) this.getSqlMapClientTemplate().queryForObject(sqlId,recbizType);
    }

    /**
     * 返回业务类型信息
     * @param recbizType
     * @return RecbizType实体
     */
    public RecbizType getRecbizTypeById(RecbizType recbizType) {
        return (RecbizType) this.getSqlMapClientTemplate().queryForObject("GetRecbizTypeById", recbizType);
    }


    /**
     * 编辑业务类型
     * @param recbizType
     */
    public void updateRecbiztype(RecbizType recbizType) {
        this.getSqlMapClientTemplate().update("UpdateRecbiztype", recbizType);
    }

    /**
     * 返回所有未删除的业务类型
     * @return
     */
    public List<RecbizType> getRecbizTypeList() {
        return this.getSqlMapClientTemplate().queryForList("GetRecbizTypeList");
    }

    /**
     * PAD端返回未删除已启用的业务类型
     */
    public List<RecbizType> getRecbizTypeForPad() {
        return this.getSqlMapClientTemplate().queryForList("GetRecbizTypeForPad");
    }

}
