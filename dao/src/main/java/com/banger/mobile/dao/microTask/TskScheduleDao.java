package com.banger.mobile.dao.microTask;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.microProduct.PdtProductCustomerBean;
import com.banger.mobile.domain.model.microTask.TskSchedule;

public interface TskScheduleDao {
	/**
	 * 根据日程id查找日程信息
	 * @param scheduleId
	 * @return
	 */
	public TskSchedule getScheduleById(Integer scheduleId);
	
	/**
	 * 分页查找日程列表 按完成情况 升序 和 时间 倒序 排列
	 * @return
	 */
	public List<TskSchedule> getScheduleListByPage(Map<String , Object> parameterMap ,Page page);
	
	/**
	 * 添加新日程
	 * @param tskSchedule
	 */
	public void addNewSchedule(TskSchedule tskSchedule);
	
	/**
	 * 更新日程
	 * @param tskSchedule
	 */
	public void updateSchedule(TskSchedule tskSchedule);
	
	/**
	 * 获得所有的沟通进度配置信息
	 * @return 
	 */
	public List<Map> getAllCommProgress();
	/**
	 * 看看单独查询总数是否正确
	 * @param map
	 * @return
	 */
	public Integer getCount(Map<String,Object> map);
	/**
	 * 判断用户日程数
	 * @param map
	 * @return
	 */
	public Integer getCustomerScheduleCount(Map<String,Object> map);

    public Integer judgeScheduleByCustomerId(Map<String,Object> paramMap);
	/**
	 * 查询客户列表
	 * @param map
	 * @return
	 */
	public List<TskSchedule> getCustomerList(Map<String,Object> map,Page page);
	/**
	 * 获取客户类型列表
	 * @return
	 */
	public List<Map> getCustomerTypeList();
	/**
	 * 获取用户详细信息
	 * @param map
	 * @return
	 */
	public TskSchedule getCustomerDetail(Map<String,Object> map);

    /**
     * 获取用户意向产品信息
     * @param parameterMap
     * @param page
     * @return
     */
    public PageUtil<TskSchedule> getCustomerPDTProductList(Map<String,Object> parameterMap,Page page);

    Integer getCustomerPDTProductCount(Map<String, Object> paramMap);

    void insertTskScheduleBatch(List<TskSchedule> list);

    public List<PdtProductCustomerBean> getCusPdtProductListForPad(Map<String,Object> paramMap,Page page);

    PageUtil<TskSchedule> getTskScheduleCard(Map<String, Object> paramMap, Page page);

    List<TskSchedule> getScheduleByCusIds(Map<String, Object> paramMap);

    void deleteScheduleByCusIds(Map<String, Object> paramMap);

    void mergeCusSchedule(Map<String, Object> paramMap);

    List<Integer> getScheduleCusIds(Map<String, Object> paramMap);

    Integer getInTimeScheduleCount(Map<String, Object> paramMap);

    List<TskSchedule> getInTimeScheduleList(Map<String, Object> paramMap);

    TskSchedule getUnFinishedSchedule(Map<String, Object> paramMap);

    List<TskSchedule> getInTimeScheduleListByPage(Map<String, Object> parameterMap, Page page);

    Integer getNo1CommProgressId();

    Integer getNextScheduleId();

    void insertTskSchedule(TskSchedule tskSchedule);

    Integer getCusScheduleCount(Map<String, Object> paramMap);

    /**
     * @param paras
     */
    public void updateTaskSchedule(Map<String, Object> paras);

    public Integer getTskScheduleCountCard(Map<String,Object> map);
}
