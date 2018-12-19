package com.banger.mobile.facade.microTask;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.microTask.TskMicroTask;

/**
 * 工作台任务提醒service
 * User: yuanme
 * Date: 13-4-11
 * Time: 下午2:42
 */
public interface TskMicroTaskWorkbenchService {
    //今天的任务列表
    public PageUtil<TskMicroTask> getTodayTaskListPage(Integer userId, Page page);

    //已过期的任务列表
    public PageUtil<TskMicroTask> getOutDateTaskListPage(Integer userId,Page page);
}
