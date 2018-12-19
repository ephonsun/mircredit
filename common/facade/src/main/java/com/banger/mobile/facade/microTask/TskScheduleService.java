package com.banger.mobile.facade.microTask;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.microProduct.PdtProductCustomerBean;
import com.banger.mobile.domain.model.microTask.TskSchedule;

public interface TskScheduleService {
	/**
	 * 根据日程id查询日程详情
	 */
	public TskSchedule getScheduleById(int scheduleId);
	/**
	 * 分页查找日程列表
	 * @param parameterMap
	 * @return
	 */
	public List<TskSchedule> getScheduleListByPage(Map<String , Object> parameterMap ,Page page);
	
	/**
	 * 查找所有沟通进度配置信息
	 * @return
	 */
	public List<Map> getAllCommProgress();
	/**
	 * 客户类型列表
	 * @return
	 */
	public List<Map> getCustomerTypeList();
	
	/**
	 * 新增
	 * @param tskSchedule
	 */
	public void addNewSchedule(TskSchedule tskSchedule);
	
	/**
	 * 修改
	 * @param tskSchedule
	 */
	public void updateSchedule(TskSchedule tskSchedule);
	
	/**
	 * 根据用户id判断用户是否已有日程并判断是否插入
	 * @param map
	 * @return
	 */
	public Integer judgeCustomerSchedule(Map<String,Object> map);

    /**
     * 根据用户id判断用户是否已有日程
     * @param paramMap
     * @return
     */
    public Integer judgeScheduleByCustomerId(Map<String,Object> paramMap);

	/**
	 * 客户列表
	 * @param parameterMap
	 * @param page
	 * @return
	 */
	public List<TskSchedule> getCustomerList(Map<String , Object> parameterMap ,Page page);
	/**
	 * 客户详细信息
	 * @param map
	 * @return
	 */
	public TskSchedule getCustomerDetail(Map<String,Object> map);

    /**
     * 获取用户意向产品信息
     * @param paramMap
     * @param page
     * @return
     */
    public List<TskSchedule> getCustomerPDTProductList(Map<String,Object> paramMap,Page page);

    Integer getCount(Map<String, Object> paramMap);

    Integer getCustomerPDTProductCount(Map<String, Object> paramMap);

    /**
     * 批量插入用户日程
     * @param list
     */
    public void insertTskScheduleBatch(List<TskSchedule> list);

    public List<PdtProductCustomerBean> getCusPdtProductListForPad(Map<String,Object> paramMap,Page page);

    PageUtil<TskSchedule> getTskScheduleCard(Map<String, Object> paramMap, Page page);

    List<TskSchedule> getScheduleByCusIds(String cusIds);

    String getNoScheduleCusIds(String customerIds);

    void mergeCusSchedule(String needMergeCusIds, String mergeEndCusId);

    void delCusSchedule(String customerIds);

    TskSchedule getUnFinishedSchedule(Map<String, Object> paramMap);

    List<TskSchedule> getInTimeScheduleListByPage(
            Map<String, Object> parameterMap, Page page);

    Integer getNo1CommProgressId();

    Integer getNextScheduleId();

    void insertTskSchedule(TskSchedule tskSchedule);

    Integer getCusScheduleCount(Integer customerId);

    List<TskSchedule> getInTimeScheduleList(Map<String, Object> paramMap);

    Integer getInTimeScheduleCount(Map<String, Object> paramMap);
    /**
     * @param paras
     */
    public void updateTaskSchedule(Map<String, Object> paras);

    List<TskSchedule> getScheduleByCusIds(Map<String, Object> paramMap);


    Integer getTskScheduleCountCard(Map<String,Object> map);
}
