/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-12-25
 */
package com.banger.mobile.facade.pdtProduct;

import java.util.List;

import net.sf.json.JSONArray;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.pdtProduct.PdtTemplate;

/**
 * @author cheny
 * @version $Id: PdtTemplateService.java,v 0.1 2012-12-25 上午10:34:03 cheny Exp $
 */
public interface PdtTemplateService {

    /**
     * 产品模板列表
     */
    public PageUtil<PdtTemplate> getPdtTemplateList(PdtTemplate template, Page page);
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
     * 保存模板
     */
    public void addPdtTemplate(PdtTemplate temp);
    /**
     * 更新模板
     */
    public void updatePdtTemplate(PdtTemplate temp);
    /**
     * 上移下移
     */
    public void moveTemplate(int templateId,String flag);
    /**
     * 启用停用
     */
    public String activePdtTemplate(PdtTemplate temp,int flag);
    /**
     * 删除
     */
    public String delPdtTemplate(PdtTemplate temp);
    
    /**
     * 去所有模板，字段，值（缓存）
     * @param isSearch
     * @param isNeedDel
     * @return
     */
    public List<PdtTemplate> getTemplates(boolean isSearch,boolean isNeedDel);
    
    /**
     * 组转营销任务需要的产品模板树
     * @return
     */
    public JSONArray getProductTemplatesTree();
    
}
