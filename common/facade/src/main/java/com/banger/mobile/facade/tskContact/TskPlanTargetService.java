/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-12-18
 */
package com.banger.mobile.facade.tskContact;

import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.tskContact.TaskPlanCustomerDetailBean;
import com.banger.mobile.domain.model.tskContact.TskPlanTarget;

/**
 * @author cheny
 * @version $Id: TskPlanTargetService.java,v 0.1 2012-12-18 下午2:37:52 cheny Exp $
 */
public interface TskPlanTargetService {
    /**
     * 新增联系计划目标客户
     */
    public Integer insertTskPlanTarget(TskPlanTarget target);
    /**
     *  编辑 联系计划目标客户
     */
    public void updateTskPlanTarget(TskPlanTarget target);
    /**
     * 查询计划执行情况客户列表明细
     * @param parameters 查询条件
     * @param detailFlag 明细类型
     * @return
     */
    public PageUtil<TaskPlanCustomerDetailBean> getTaskPlanCustomerPage(Map<String, Object> parameters,String detailFlag,Page page);
}
