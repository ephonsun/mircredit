/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :任务附件-Dao-接口实现
 * Author     :QianJie
 * Create Date:Mar 2, 2013
 */
package com.banger.mobile.dao.microTask.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.microTask.TskMicroTaskAttachmentDao;
import com.banger.mobile.domain.model.microTask.TskMicroTaskAttachment;
import com.banger.mobile.ibatis.GenericDaoiBatis;
import com.banger.mobile.util.StringUtil;

/**
 * @author QianJie
 * @version $Id: TskMicroTaskAttachmentDaoiBatis.java,v 0.1 Mar 2, 2013 11:15:25 AM QianJie Exp $
 */
public class TskMicroTaskAttachmentDaoiBatis extends GenericDaoiBatis implements TskMicroTaskAttachmentDao {

    public TskMicroTaskAttachmentDaoiBatis() {
        super(TskMicroTaskAttachment.class);
    }
    
    public TskMicroTaskAttachmentDaoiBatis(Class persistentClass) {
        super(TskMicroTaskAttachment.class);
    }

    /**
     * 添加任务附件
     * @param tskMicroTaskAttachment
     * @see com.banger.mobile.dao.microTask.TskMicroTaskAttachmentDao#addTskMicroTaskAttachment(com.banger.mobile.domain.model.microTask.TskMicroTaskAttachment)
     */
    public void addTskMicroTaskAttachment(TskMicroTaskAttachment tskMicroTaskAttachment) {
        this.getSqlMapClientTemplate().insert("addTskMicroTaskAttachment",tskMicroTaskAttachment);
    }

    /**
     * 删除任务附件
     * @param conds
     * @see com.banger.mobile.dao.microTask.TskMicroTaskAttachmentDao#delTskMicroTaskAttachmentByConds(java.util.Map)
     */
    public void delTskMicroTaskAttachmentByConds(Map<String, Object> conds) {
        Map<String,Object> fConds = new HashMap<String, Object>();
        for(Map.Entry<String, Object> entry : conds.entrySet())   
        { 
            if(entry.getValue() instanceof String){
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil.ReplaceSQLChar(entry.getValue().toString())));
            }else{
                fConds.put(entry.getKey(), entry.getValue());
            }
        }
        this.getSqlMapClientTemplate().delete("delTskMicroTaskAttachmentByConds",fConds);
    }

    /**
     * 查询所有务附件数据
     * @param conds
     * @return
     * @see com.banger.mobile.dao.microTask.TskMicroTaskAttachmentDao#getAllTskMicroTaskAttachmentByConds(java.util.Map)
     */
    public List<TskMicroTaskAttachment> getAllTskMicroTaskAttachmentByConds(
                                                                            Map<String, Object> conds) {
        Map<String,Object> fConds = new HashMap<String, Object>();
        for(Map.Entry<String, Object> entry : conds.entrySet())   
        { 
            if(entry.getValue() instanceof String){
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil.ReplaceSQLChar(entry.getValue().toString())));
            }else{
                fConds.put(entry.getKey(), entry.getValue());
            }
        }
        return this.getSqlMapClientTemplate().queryForList("getAllTskMicroTaskAttachmentByConds",fConds);
    }

}
