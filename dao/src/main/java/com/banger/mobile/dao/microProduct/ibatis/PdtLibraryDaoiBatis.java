/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :产品知识库-Dao-接口实现
 * Author     :QianJie
 * Create Date:Nov 12, 2012
 */
package com.banger.mobile.dao.microProduct.ibatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.microProduct.PdtLibraryDao;
import com.banger.mobile.domain.model.microProduct.PdtLibrary;
import com.banger.mobile.domain.model.microProduct.PdtLibraryBean;
import com.banger.mobile.ibatis.GenericDaoiBatis;
import com.banger.mobile.util.StringUtil;

/**
 * @author QianJie
 * @version $Id: PdtLibraryDaoiBatis.java,v 0.1 Nov 12, 2012 4:13:54 PM QianJie Exp $
 */
public class PdtLibraryDaoiBatis extends GenericDaoiBatis implements PdtLibraryDao {

    public PdtLibraryDaoiBatis() {
        super(PdtLibrary.class);
    }

    public PdtLibraryDaoiBatis(Class persistentClass) {
        super(PdtLibrary.class);
    }

    /**
     * 根据参数得到所有知识库数据
     * @param conds
     * @return
     */
    public List<PdtLibrary> getAllPdtLibraryByConds(Map<String, Object> conds) {
        Map<String,Object> fConds = new HashMap<String, Object>();
        for(Map.Entry<String, Object> entry : conds.entrySet())
        {
            if(entry.getValue() instanceof String){
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil.ReplaceSQLChar(entry.getValue().toString())));
            }else{
                fConds.put(entry.getKey(), entry.getValue());
            }
        }
        return this.getSqlMapClientTemplate().queryForList("getAllPdtLibraryByConds",fConds);
    }

    /**
     * 添加产品知识库分类
     * @param pdtLibrary
     * @return
     * @see com.banger.mobile.dao.product.PdtLibraryDao#addPdtLibrary(com.banger.mobile.domain.model.product.PdtLibrary)
     */
    public int addPdtLibrary(PdtLibrary pdtLibrary) {
        return ((Integer)this.getSqlMapClientTemplate().insert("addPdtLibrary",pdtLibrary)).intValue();
    }

    /**
     * 添加产品知识库(包含主键)
     * @param pdtLibrary
     * @return
     * @see com.banger.mobile.dao.product.PdtLibraryDao#addPdtLibraryWithKey(com.banger.mobile.domain.model.product.PdtLibrary)
     */
    public int addPdtLibraryWithKey(PdtLibrary pdtLibrary) {
        this.getSqlMapClientTemplate().insert("addPdtLibraryWithKey",pdtLibrary);
        return pdtLibrary.getLibraryId();
    }

    /**
     * 删除产品知识库分类
     * @param libraryId
     * @see com.banger.mobile.dao.product.PdtLibraryDao#delPdtLibraryById(int)
     */
    public void delPdtLibraryById(int libraryId) {
        this.getSqlMapClientTemplate().update("delPdtLibraryById",libraryId);
    }

    /**
     * 删除批量产品知识库分类
     * @param libraryIds
     * @see com.banger.mobile.dao.product.PdtLibraryDao#delPdtLibraryByIds(java.lang.String)
     */
    public void delPdtLibraryByIds(String libraryIds) {
        this.getSqlMapClientTemplate().update("delPdtLibraryByIds",libraryIds);
    }

    /**
     * 编辑产品知识库分类
     * @param pdtLibrary
     * @return
     * @see com.banger.mobile.dao.product.PdtLibraryDao#editPdtLibrary(com.banger.mobile.domain.model.product.PdtLibrary)
     */
    public int editPdtLibrary(PdtLibrary pdtLibrary) {
        this.getSqlMapClientTemplate().update("editPdtLibrary",pdtLibrary);
        return pdtLibrary.getLibraryId();
    }

    /**
     * 得到单个产品知识库分类数据
     * @param libraryId
     * @return
     * @see com.banger.mobile.dao.product.PdtLibraryDao#getPdtLibraryById(int)
     */
    public PdtLibrary getPdtLibraryById(int libraryId) {
        Object obj = this.getSqlMapClientTemplate().queryForObject("getPdtLibraryById",libraryId);
        if(obj != null)
            return (PdtLibrary)obj;
        else {
            return null;
        }
    }

    /**
     * 查询产品知识库分类（分页查询）
     * @param conds
     * @param page
     * @return
     * @see com.banger.mobile.dao.product.PdtLibraryDao#getPdtLibraryPage(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<PdtLibraryBean> getPdtLibraryPage(Map<String, Object> conds, Page page) {
        Map<String,Object> fConds = new HashMap<String, Object>();
        for(Map.Entry<String, Object> entry : conds.entrySet())
        {
            if(entry.getValue() instanceof String){
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil.ReplaceSQLChar(entry.getValue().toString())));
            }else{
                fConds.put(entry.getKey(), entry.getValue());
            }
        }
        ArrayList<PdtLibraryBean> list=(ArrayList<PdtLibraryBean>) this.findQueryPage("getPdtLibraryPageByConds", "getPdtLibraryPageCountByConds",fConds, page);
        return new PageUtil<PdtLibraryBean>(list, page);
    }

    public PageUtil<PdtLibraryBean> getPdtLibraryPageForPad(Map<String, Object> conds, Page page) {
        Map<String,Object> fConds = new HashMap<String, Object>();
        for(Map.Entry<String, Object> entry : conds.entrySet())
        {
            if(entry.getValue() instanceof String){
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil.ReplaceSQLChar(entry.getValue().toString())));
            }else{
                fConds.put(entry.getKey(), entry.getValue());
            }
        }
        ArrayList<PdtLibraryBean> list=(ArrayList<PdtLibraryBean>) this.findQueryPage("getPdtLibraryPageByCondsForPad", "getPdtLibraryPageCountByCondsForPad",fConds, page);
        return new PageUtil<PdtLibraryBean>(list, page);
    }

    /**
     * 得到知识库 NEXT SEQ 值
     * @return
     * @see com.banger.mobile.dao.product.PdtLibraryDao#getNextLibraryId()
     */
    public int getNextLibraryId() {
        return (Integer)getSqlMapClientTemplate().queryForObject("getNextLibraryId");
    }

    /**
     * 查询知识库 PAD
     * @param input
     * @return
     * @see com.banger.mobile.dao.product.PdtLibraryDao#queryAllLibraryForPad(java.lang.String)
     */
    public List<PdtLibrary> queryAllLibraryForPad(String input) {
        input = StringUtil.ReplaceSQLEscapeChar(StringUtil.ReplaceSQLChar(input));
        return this.getSqlMapClientTemplate().queryForList("queryAllLibraryForPad", input);
    }

}
