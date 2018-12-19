/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :业务类型业务实现类
 * Author     :liyb
 * Create Date:2012-5-17
 */
package com.banger.mobile.facade.impl.system.recbiztype;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.recbiztype.RecbizTypeDao;
import com.banger.mobile.domain.model.recbistype.RecbizType;
import com.banger.mobile.facade.recbiztype.RecbizTypeService;

/**
 * @author liyb
 * @version $Id: RecbizTypeServiceImpl.java,v 0.1 2012-5-17 下午05:05:49 liyb Exp $
 */
public class RecbizTypeServiceImpl implements RecbizTypeService {

    private RecbizTypeDao recbizTypeDao;
    
    public RecbizTypeDao getRecbizTypeDao() {
        return recbizTypeDao;
    }
    public void setRecbizTypeDao(RecbizTypeDao recbizTypeDao) {
        this.recbizTypeDao = recbizTypeDao;
    }

    /**
     * 根据业务编号删除(单个删除)
     * @param bizTypeId
     */
    public void deleteRecbizTypeById(Integer bizTypeId) {
        recbizTypeDao.deleteRecbizTypeById(bizTypeId);
    }

    /**
     * 修改启用/停用状态
     * @param recbizType
     */
    public void updateActived(RecbizType recbizType) {
        recbizTypeDao.updateActived(recbizType);
    }

    /**
     * 添加业务类型
     * @param recbizType
     */
    public void insertRecbiztype(RecbizType recbizType) {
        recbizType.setCreateDate(new Date());
        recbizTypeDao.insertRecbiztype(recbizType);
    }

    /**
     * 判断业务类型编号和名称是否存在
     * @param sqlId
     * @param recbizType
     * @return
     */
    public Integer validation(String sqlId, RecbizType recbizType) {
        return recbizTypeDao.validation(sqlId, recbizType);
    }

    /**
     * 返回业务类型信息
     * @param recbizType
     * @return RecbizType实体
     */
    public RecbizType getRecbizTypeById(RecbizType recbizType) {
        return recbizTypeDao.getRecbizTypeById(recbizType);
    }

    /**
     * 编辑业务类型
     * @param recbizType
     */
    public void updateRecbiztype(RecbizType recbizType) {
        recbizType.setUpdateDate(new Date());
        recbizTypeDao.updateRecbiztype(recbizType);
    }

    /**
     * 返回所有未删除的业务类型
     * @return
     */
    public List<RecbizType> getRecbizTypeList() {
        return recbizTypeDao.getRecbizTypeList();
    }

    /**
     * PAD端返回未删除已启用的业务类型
     */
    public List<RecbizType> getRecbizTypeForPad() {
        return recbizTypeDao.getRecbizTypeForPad();
    }
    
    /**
     * 判断业务类型是否在使用
     */
    public Integer isTypeUse(Integer bizTypeId) {
        return recbizTypeDao.isTypeUse(bizTypeId);
    }

}
