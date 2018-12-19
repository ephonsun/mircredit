/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :营销任务明细表-Dao-接口实现
 * Author     :QianJie
 * Create Date:Dec 27, 2012
 */
package com.banger.mobile.dao.tskMarketing.iBatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.tskMarketing.TskMarketingExecuteDao;
import com.banger.mobile.domain.model.tskMarketing.TskMarketingExecute;
import com.banger.mobile.ibatis.GenericDaoiBatis;
import com.banger.mobile.util.StringUtil;

/**
 * @author QianJie
 * @version $Id: TskMarketingExecuteDaoiBatis.java,v 0.1 Dec 27, 2012 11:45:28 AM QianJie Exp $
 */
public class TskMarketingExecuteDaoiBatis extends GenericDaoiBatis implements TskMarketingExecuteDao {

    public TskMarketingExecuteDaoiBatis() {
        super(TskMarketingExecute.class);
    }
    
    public TskMarketingExecuteDaoiBatis(Class persistentClass) {
        super(TskMarketingExecute.class);
    }

    /**
     * 添加营销任务明细
     * @param tskMarketingExecute
     * @see com.banger.mobile.dao.tskMarketing.TskMarketingExecuteDao#addTskMarketingExecute(com.banger.mobile.domain.model.tskMarketing.TskMarketingExecute)
     */
    public void addTskMarketingExecute(TskMarketingExecute tskMarketingExecute) {
        this.getSqlMapClientTemplate().insert("addTskMarketingExecute",tskMarketingExecute);
    }
    
    /**
     * 批量添加营销任务明细
     * @param list
     * @see com.banger.mobile.dao.tskMarketing.TskMarketingExecuteDao#addTskMarketingExecuteBatch(java.util.List)
     */
    public void addTskMarketingExecuteBatch(List<TskMarketingExecute> list) {
        this.exectuteBatchInsert("addTskMarketingExecute", list);
    }

    /**
     * 删除营销任务明细
     * @param conds
     * @see com.banger.mobile.dao.tskMarketing.TskMarketingExecuteDao#delTskMarketingAttachmentByConds(java.util.Map)
     */
    public void delTskMarketingByConds(Map<String, Object> conds) {
        Map<String,Object> fConds = new HashMap<String, Object>();
        for(Map.Entry<String, Object> entry : conds.entrySet())   
        { 
            if(entry.getValue() instanceof String){
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil.ReplaceSQLChar(entry.getValue().toString())));
            }else{
                fConds.put(entry.getKey(), entry.getValue());
            }
        }
        this.getSqlMapClientTemplate().delete("delTskMarketingExecuteByConds",fConds);
    }

    /**
     * 编辑营销任务明细
     * @param tskMarketingExecute
     * @see com.banger.mobile.dao.tskMarketing.TskMarketingExecuteDao#editTskMarketingExecute(com.banger.mobile.domain.model.tskMarketing.TskMarketingExecute)
     */
    public void editTskMarketingExecute(TskMarketingExecute tskMarketingExecute) {
        this.getSqlMapClientTemplate().update("editTskMarketingExecute",tskMarketingExecute);
    }

    /**
     * 查询所有营销任务执行者数据
     * @param conds
     * @return
     * @see com.banger.mobile.dao.tskMarketing.TskMarketingExecuteDao#getAllTskMarketingExecuteByConds(java.util.Map)
     */
    public List<TskMarketingExecute> getAllTskMarketingExecuteByConds(Map<String, Object> conds) {
        Map<String,Object> fConds = new HashMap<String, Object>();
        for(Map.Entry<String, Object> entry : conds.entrySet())   
        { 
            if(entry.getValue() instanceof String){
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil.ReplaceSQLChar(entry.getValue().toString())));
            }else{
                fConds.put(entry.getKey(), entry.getValue());
            }
        }
        return this.getSqlMapClientTemplate().queryForList("getAllTskMarketingExecuteByConds",fConds);
    }

    /**
     * 营销管理更改执行者的机构ID
     * @param map
     */
	public void updateExecuteUserDeptId(Map<String, Object> map) {
		this.getSqlMapClientTemplate().update("UpdateExecuteUserDeptId",map);
	}
}
