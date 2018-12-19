/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :产品通知-Dao-接口实现
 * Author     :QianJie
 * Create Date:Dec 11, 2012
 */
package com.banger.mobile.dao.microProduct.ibatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.microProduct.PdtNoticeDao;
import com.banger.mobile.domain.model.microProduct.PdtNotice;
import com.banger.mobile.ibatis.GenericDaoiBatis;
import com.banger.mobile.util.StringUtil;

/**
 * @author QianJie
 * @version $Id: PdtNoticeDaoiBatis.java,v 0.1 Dec 11, 2012 1:58:03 PM QianJie Exp $
 */
public class PdtNoticeDaoiBatis extends GenericDaoiBatis implements PdtNoticeDao {

    public PdtNoticeDaoiBatis() {
        super(PdtNoticeDaoiBatis.class);
    }

    public PdtNoticeDaoiBatis(Class persistentClass) {
        super(PdtNoticeDaoiBatis.class);
    }

    /**
     * 编辑产品通知
     * @param pdtNotice
     * @return
     * @see com.banger.mobile.dao.product.PdtNoticeDao#editPdtNotice(com.banger.mobile.domain.model.product.PdtNotice)
     */
    public int editPdtNotice(PdtNotice pdtNotice) {
        this.getSqlMapClientTemplate().update("editPdtNotice",pdtNotice);
        return pdtNotice.getNoticeId();
    }

    /**
     * 修改通知已读未读状态
     * @param conds
     * @see com.banger.mobile.dao.product.PdtNoticeDao#editPdtNoticeReadByConds(java.util.Map)
     */
    public void editPdtNoticeReadByConds(Map<String, Object> conds) {

    }

    /**
     * 根据id得到通知对象
     * @param noticeId
     * @return
     * @see com.banger.mobile.dao.product.PdtNoticeDao#getPdtNoticeById(int)
     */
    public PdtNotice getPdtNoticeById(int noticeId) {
        return (PdtNotice)this.getSqlMapClientTemplate().queryForObject("getPdtNoticeById",noticeId);
    }

    /**
     * 查询产品通知（分页查询）
     * @param conds
     * @param page
     * @return
     * @see com.banger.mobile.dao.product.PdtNoticeDao#getPdtNoticePage(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<PdtNotice> getPdtNoticePage(Map<String, Object> conds, Page page) {
        Map<String,Object> fConds = new HashMap<String, Object>();
        for(Map.Entry<String, Object> entry : conds.entrySet())
        {
            if(entry.getValue() instanceof String){
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil.ReplaceSQLChar(entry.getValue().toString())));
            }else{
                fConds.put(entry.getKey(), entry.getValue());
            }
        }
        ArrayList<PdtNotice> list=(ArrayList<PdtNotice>) this.findQueryPage("getPdtNoticePageByConds", "getPdtNoticePageCountByConds",fConds, page);
        return new PageUtil<PdtNotice>(list, page);
    }


}
