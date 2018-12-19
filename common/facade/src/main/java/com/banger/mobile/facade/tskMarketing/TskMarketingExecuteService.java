/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :营销任务明细表-Service-接口
 * Author     :QianJie
 * Create Date:Jan 4, 2013
 */
package com.banger.mobile.facade.tskMarketing;

import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.tskMarketing.TskMarketingExecute;

/**
 * @author QianJie
 * @version $Id: TskMarketingExecuteService.java,v 0.1 Jan 4, 2013 2:56:54 PM QianJie Exp $
 */
public interface TskMarketingExecuteService {

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
     * @param userId 用户ID
     * @param deptId 机构ID
     */
    public void updateExecuteUserDeptId(Integer userId,Integer deptId);
}
