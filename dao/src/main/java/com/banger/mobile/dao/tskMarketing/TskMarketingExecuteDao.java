/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :营销任务明细表-Dao-接口
 * Author     :QianJie
 * Create Date:Dec 27, 2012
 */
package com.banger.mobile.dao.tskMarketing;

import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.tskMarketing.TskMarketingExecute;

/**
 * @author QianJie
 * @version $Id: TskMarketingExecuteDao.java,v 0.1 Dec 27, 2012 11:45:10 AM QianJie Exp $
 */
public interface TskMarketingExecuteDao {
    
    /**
     * 添加营销任务明细
     * @param tskMarketingExecute
     */
    public void addTskMarketingExecute(TskMarketingExecute tskMarketingExecute);
    
    /**
     * 批量添加营销任务明细
     * @param tskMarketing
     */
    public void addTskMarketingExecuteBatch(List<TskMarketingExecute> list);
    
    /**
     * 编辑营销任务明细
     * @param tskMarketingExecute
     */
    public void editTskMarketingExecute(TskMarketingExecute tskMarketingExecute);

    /**
     * 删除营销任务明细
     * @param conds
     */
    public void delTskMarketingByConds(Map<String, Object> conds);
    
    /**
     * 查询所有营销任务执行者数据
     * @param conds
     * @return
     */
    public List<TskMarketingExecute> getAllTskMarketingExecuteByConds(Map<String, Object> conds);
    
    /**
     * 营销管理更改执行者的机构ID
     * @param map
     */
    public void updateExecuteUserDeptId(Map<String, Object> map);
}
