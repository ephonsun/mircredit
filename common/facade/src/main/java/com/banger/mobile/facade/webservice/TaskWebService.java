package com.banger.mobile.facade.webservice;

import javax.jws.WebService;

@WebService
public interface TaskWebService {
    // 任务管理
    // 任务列表/搜索任务
    // 获取所有任务列表input值为空,isLate传-1
    public String getTaskList(String account, String input, Integer status, Integer page,
                              Integer isLate);

    // 任务列表总数
    // 获取所有任务列表总数input值为空,isLate传-1
    public String getTaskListCount(String account, String input, Integer status, Integer isLate);

    // 执行电话任务列表
    // 获取所有电话列表input值为空,status传-1
    public String getPhoneTaskList(String account, String input, Integer taskId,
                                   Integer pageNumber, Integer status);

    // 执行电话任务列表总数
    // 获取所有电话列表总数input值为空,status传-1
    public String getPhoneTaskListCount(String account, String input, Integer taskId,
                                         Integer status);

    // 执实地营销任务列表
    public String getMarketTaskList(String account, String startDate, String endDate, String input,
                                    Integer taskId, Integer pageNumber);

    // 执行实地营销任务列表总数
    public String getMarketTaskListCount(String account, String startDate, String endDate,
                                          String input, Integer taskId);

    // 日程列表
    String getScheduleList(String account, String input, Integer status, Integer contactWayType,
                           Integer page, Integer isLate, Integer customerId);

    // 日程列表总数
    String getScheduleListCount(String account, String input, Integer status,
                                 Integer contactWayType, Integer isLate, Integer customerId);

    // 修改日程联系进度
    String setContactProgress(Integer scheduleId, Integer contactProgressId);

    // 设置日程完成
    String setScheduleComplete(Integer scheduleId);

    // 设置日程未完成
    String cancelScheduleComplete(Integer scheduleId);

    // 日程获取客户意向产品
    String getIntentProductList(String account, Integer customerId, Integer page);

    // 日程获取客户意向产品总数
    String getIntentProductCount(String account, Integer customerId);

    // 客户任务安排
    public String getCustomerTaskList(String account, Integer customerId, Integer page);

    // 根据id查找任务详情
    public String getTaskById(String account, Integer taskId);

    // 执行实地营销任务
    public String executeTask(String account, Integer taskId, Integer customerId, String address,
                              String remark, String lat, String lng, String recordDate);

    // 管理录音
    public String relatedRecord(String account, Integer taskTargetId, Integer customerId);

    String getUnFinishedSchedule(String account, Integer customerId);
}
