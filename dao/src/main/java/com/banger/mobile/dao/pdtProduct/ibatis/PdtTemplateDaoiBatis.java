/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-12-25
 */
package com.banger.mobile.dao.pdtProduct.ibatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.pdtProduct.PdtTemplateDao;
import com.banger.mobile.domain.model.pdtProduct.PdtTemplate;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author cheny
 * @version $Id: PdtTemplateDaoiBatis.java,v 0.1 2012-12-25 上午10:31:21 cheny Exp $
 */
public class PdtTemplateDaoiBatis extends GenericDaoiBatis implements PdtTemplateDao{

    public PdtTemplateDaoiBatis(){
        super(PdtTemplate.class);
    }

    /**
     * 产品模板列表
     */
    public PageUtil<PdtTemplate> getPdtTemplateList(Map<String, Object> map, Page page) {
        List<PdtTemplate> list =(List<PdtTemplate>)this.findQueryPage("getPdtTemplateList", "getPdtTemplateListCount", map, page);
        if(list==null){
            list = new ArrayList<PdtTemplate>();
        }
        return new PageUtil<PdtTemplate>(list,page);
    }
    /**
     * 查询模板
     */
    public PdtTemplate getPdtTemplateById(PdtTemplate temp){
        return (PdtTemplate) this.getSqlMapClientTemplate().queryForObject("getPdtTemplateById",temp);
    }
    /**
     * 新增模板
     */
    public Integer insertPdtTemplate(PdtTemplate temp){
        return (Integer) this.getSqlMapClientTemplate().insert("insertPdtTemplate",temp);
    }
    /**
     * 查询所有模板
     */
    public List<PdtTemplate> getAllTempList(){
        return this.getSqlMapClientTemplate().queryForList("getAllTempList");
    }
    /**
     * 更新模板
     */
    public void updatePdtTemplate(PdtTemplate temp){
        this.getSqlMapClientTemplate().update("updatePdtTemplate",temp);
    }

    /**
     * 查询所有模板（包括停用和已删除）
     * @return
     */
    public List<PdtTemplate> getAllTemplateList(){
        return this.getSqlMapClientTemplate().queryForList("getAllTemplateList");
    }
}
