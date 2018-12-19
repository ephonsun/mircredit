/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-12-26
 */
package com.banger.mobile.facade.pdtProduct;

import java.util.List;

import com.banger.mobile.domain.model.pdtProduct.PdtTemplateField;

/**
 * @author cheny
 * @version $Id: PdtTemplateFieldService.java,v 0.1 2012-12-26 下午3:59:03 cheny Exp $
 */
public interface PdtTemplateFieldService {
    
    /**
     * 根据模板id查询模板下所有字段
     */
    public List<PdtTemplateField> getTempFieldsByTempId(int templateId);
    /**
     * 新增模板字段
     */
    public Integer insertPdtTemplateField(PdtTemplateField feild);
    /**
     * 根据字段id查询字段对象
     */
    public PdtTemplateField getPdtTemplateFieldById(int feildId);
    /**
     * 更新模板字段
     */
    public void updateTemplateField(PdtTemplateField feild);
    /**
     * 上移下移
     */
    public void moveFieldItems(int tempId,int feildId,String type);
    /**
     * 删除验证
     */
    public String velidateDelFeild(int tempId,int feildId);
    /**
     * 删除
     */
    public void deleteTemplateField(int tempId,int feildId,String feildType);
    
    /**
     * 根据字段id查询字段对象(从缓存中取)
     */
    public PdtTemplateField getPdtTempFieldByIdInCache(Integer fieldId);
}
