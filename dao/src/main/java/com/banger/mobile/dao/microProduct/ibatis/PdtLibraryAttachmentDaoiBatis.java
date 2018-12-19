/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :知识附件-Dao-接口实现
 * Author     :QianJie
 * Create Date:Dec 3, 2012
 */
package com.banger.mobile.dao.microProduct.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.microProduct.PdtLibraryAttachmentDao;
import com.banger.mobile.domain.model.microProduct.PdtLibraryAttachment;
import com.banger.mobile.ibatis.GenericDaoiBatis;
import com.banger.mobile.util.StringUtil;

/**
 * @author QianJie
 * @version $Id: PdtLibraryAttachmentDaoiBatis.java,v 0.1 Dec 3, 2012 2:40:02 PM QianJie Exp $
 */
public class PdtLibraryAttachmentDaoiBatis extends GenericDaoiBatis implements PdtLibraryAttachmentDao {

    public PdtLibraryAttachmentDaoiBatis() {
        super(PdtLibraryAttachment.class);
    }
    
    public PdtLibraryAttachmentDaoiBatis(Class persistentClass) {
        super(PdtLibraryAttachment.class);
    }

    /**
     * 添加知识附件
     * @param pdtLibraryAttachment
     * @return
     * @see com.banger.mobile.dao.product.PdtLibraryAttachmentDao#addPdtLibraryAttachment(com.banger.mobile.domain.model.product.PdtLibraryAttachment)
     */
    public int addPdtLibraryAttachment(PdtLibraryAttachment pdtLibraryAttachment) {
        return ((Integer)this.getSqlMapClientTemplate().insert("addPdtLibraryAttachment",pdtLibraryAttachment)).intValue();
    }

    /**
     * 删除知识附件
     * @param conds
     * @see com.banger.mobile.dao.product.PdtLibraryAttachmentDao#delPdtLibraryAttachmentByConds(java.util.Map)
     */
    public void delPdtLibraryAttachmentByConds(Map<String, Object> conds) {
        Map<String,Object> fConds = new HashMap<String, Object>();
        for(Map.Entry<String, Object> entry : conds.entrySet())   
        { 
            if(entry.getValue() instanceof String){
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil.ReplaceSQLChar(entry.getValue().toString())));
            }else{
                fConds.put(entry.getKey(), entry.getValue());
            }
        }
        this.getSqlMapClientTemplate().delete("delPdtLibraryAttachmentByConds",fConds);
    }

    /**
     * 查询知识附件
     * @param conds
     * @return
     * @see com.banger.mobile.dao.product.PdtLibraryAttachmentDao#getPdtLibraryAttachmentByConds(java.util.Map)
     */
    public List<PdtLibraryAttachment> getPdtLibraryAttachmentByConds(Map<String, Object> conds) {
        Map<String,Object> fConds = new HashMap<String, Object>();
        for(Map.Entry<String, Object> entry : conds.entrySet())   
        { 
            if(entry.getValue() instanceof String){
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil.ReplaceSQLChar(entry.getValue().toString())));
            }else{
                fConds.put(entry.getKey(), entry.getValue());
            }
        }
        return this.getSqlMapClientTemplate().queryForList("getAllPdtLibraryAttachmentByConds",fConds);
    }

    /**
     * 查询知识附件（包括基本附件信息）
     * @param conds
     * @return
     */
    public List<PdtLibraryAttachment> getAllPdtLibraryAttachmentByConds(Map<String, Object> conds){
        Map<String,Object> fConds = new HashMap<String, Object>();
        for(Map.Entry<String, Object> entry : conds.entrySet())
        {
            if(entry.getValue() instanceof String){
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil.ReplaceSQLChar(entry.getValue().toString())));
            }else{
                fConds.put(entry.getKey(), entry.getValue());
            }
        }
        return this.getSqlMapClientTemplate().queryForList("getAllPdtLibraryAttachmentByConds",fConds);
    }

}
