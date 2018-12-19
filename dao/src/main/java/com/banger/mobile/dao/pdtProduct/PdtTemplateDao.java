/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-12-25
 */
package com.banger.mobile.dao.pdtProduct;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.pdtProduct.PdtTemplate;

/**
 * @author cheny
 * @version $Id: PdtTemplateDao.java,v 0.1 2012-12-25 上午10:31:00 cheny Exp $
 */
public interface PdtTemplateDao {

    /**
     * 产品模板列表
     */
    public PageUtil<PdtTemplate> getPdtTemplateList(Map<String, Object> map, Page page);
    /**
     * 查询模板
     */
    public PdtTemplate getPdtTemplateById(PdtTemplate temp);
    /**
     * 新增模板
     */
    public Integer insertPdtTemplate(PdtTemplate temp);
    /**
     * 查询所有模板
     */
    public List<PdtTemplate> getAllTempList();
    /**
     * 更新模板
     */
    public void updatePdtTemplate(PdtTemplate temp);
    
    /**
     * 查询所有模板（包括停用和已删除）
     * @return
     */
    public List<PdtTemplate> getAllTemplateList();
}
