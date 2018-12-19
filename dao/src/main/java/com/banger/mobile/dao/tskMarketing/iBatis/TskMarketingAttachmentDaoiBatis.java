/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :营销任务附件-Dao-接口实现
 * Author     :QianJie
 * Create Date:Dec 27, 2012
 */
package com.banger.mobile.dao.tskMarketing.iBatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.tskMarketing.TskMarketingAttachmentDao;
import com.banger.mobile.domain.model.tskMarketing.TskMarketingAttachment;
import com.banger.mobile.ibatis.GenericDaoiBatis;
import com.banger.mobile.util.StringUtil;

/**
 * @author QianJie
 * @version $Id: TskMarketingAttachmentDaoiBatis.java,v 0.1 Dec 27, 2012 11:46:49 AM QianJie Exp $
 */
public class TskMarketingAttachmentDaoiBatis extends GenericDaoiBatis implements TskMarketingAttachmentDao {

    public TskMarketingAttachmentDaoiBatis() {
        super(TskMarketingAttachment.class);
    }
    
    public TskMarketingAttachmentDaoiBatis(Class persistentClass) {
        super(TskMarketingAttachment.class);
    }

    /**
     * 添加营销任务附件
     * @param tskMarketingAttachment
     * @see com.banger.mobile.dao.tskMarketing.TskMarketingAttachmentDao#addTskMarketingAttachment(com.banger.mobile.domain.model.tskMarketing.TskMarketingAttachment)
     */
    public void addTskMarketingAttachment(TskMarketingAttachment tskMarketingAttachment) {
        this.getSqlMapClientTemplate().insert("addTskMarketingAttachment",tskMarketingAttachment);
    }

    /**
     * 删除营销任务附件
     * @param conds
     * @see com.banger.mobile.dao.tskMarketing.TskMarketingAttachmentDao#delTskMarketingAttachmentByConds(java.util.Map)
     */
    public void delTskMarketingAttachmentByConds(Map<String, Object> conds) {
        Map<String,Object> fConds = new HashMap<String, Object>();
        for(Map.Entry<String, Object> entry : conds.entrySet())   
        { 
            if(entry.getValue() instanceof String){
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil.ReplaceSQLChar(entry.getValue().toString())));
            }else{
                fConds.put(entry.getKey(), entry.getValue());
            }
        }
        this.getSqlMapClientTemplate().delete("delTskMarketingAttachmentByConds",fConds);
    }

    /**
     * 查询所有营销任务附件数据
     * @param conds
     * @return
     * @see com.banger.mobile.dao.tskMarketing.TskMarketingAttachmentDao#getAllTskMarketingAttachmentByConds(java.util.Map)
     */
    public List<TskMarketingAttachment> getAllTskMarketingAttachmentByConds(
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
        return this.getSqlMapClientTemplate().queryForList("getAllTskMarketingAttachmentByConds",fConds);
    }

}
