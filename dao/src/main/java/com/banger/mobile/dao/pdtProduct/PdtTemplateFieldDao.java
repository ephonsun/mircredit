/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-12-25
 */
package com.banger.mobile.dao.pdtProduct;

import java.util.List;

import com.banger.mobile.domain.model.pdtProduct.PdtTemplateField;

/**
 * @author cheny
 * @version $Id: PdtTemplateFieldDao.java,v 0.1 2012-12-25 上午11:03:32 cheny Exp $
 */
public interface PdtTemplateFieldDao {

    /**
     * 新增模板字段
     */
    public Integer insertPdtTemplateField(PdtTemplateField feild);
    /**
     * 根据模板id查询模板下所有字段
     */
    public List<PdtTemplateField> getTempFieldsByTempId(int templateId);
    /**
     * 编辑默认字段
     */
    public void updateDefaultPdtTemplateField(PdtTemplateField feild);
    
    /**
     * 获取所有产品模版字段
     * @return
     */
    public List<PdtTemplateField> getAllPdtTemplateField();

    /**
     * 根据字段id查询字段对象
     */
    public PdtTemplateField getPdtTemplateFieldById(int feildId);
    /**
     * 更新模板字段
     */
    public void updateTemplateField(PdtTemplateField feild);
}
