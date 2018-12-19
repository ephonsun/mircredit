package com.banger.mobile.dao.tskContact;

import java.util.Map;
import java.util.List;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.base.tskContact.BaseTskContact;
import com.banger.mobile.domain.model.tskContact.CusRelatedTskContactBean;
import com.banger.mobile.domain.model.tskContact.CustomerAttrPlugin;
import com.banger.mobile.domain.model.tskContact.CustModelTaskDetail;
import com.banger.mobile.domain.model.tskContact.TskContact;
import com.banger.mobile.domain.model.tskContact.TskTaskListBean;
import com.banger.mobile.domain.model.tskContact.TskContactReportBean;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 17, 2012 10:28:56 AM
 * 类说明
 */
public interface TskContactDao {

    /**
     * 任务全景展示
     * @param map
     * @return
     */
    public List<TskTaskListBean> showTskViewList(Map<String, Object> map);
    
	
    /**
     * 任务管理列表
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<TskTaskListBean> getTskTaskPage(Map<String, Object> parameters, Page page) ;
    
    /**
     * 任务所对应的联系量
     */
    public Integer getTaskConnactCount(Map<String, Object> parameters);
    
	/**
	 * 新建联系任务
	 * @param tskContact
	 * @return
	 */
	public Integer insertTskContact(TskContact tskContact);
	
	/**
	 * 编辑联系任务
	 * @param tskContact
	 */
	public void updateTskContact(TskContact tskContact);
	
	/**
	 * 删除联系任务
	 * @param contactId
	 */
	public void deleteTskContact(Integer contactId);
	
	/**
	 * 彻底删除联系任务
	 * @param contactId:任务ID  isComplete:是否彻底删除
	 */
	public void deleteTskContact(Integer contactId, boolean isComplete);
	
	/**
	 * 中止/重启任务
	 * @param tskContact
	 */
	public void  suspendTskContact(TskContact tskContact);
	
	/**
	 * 确认新建任务
	 * @param contactId
	 */
	public void comfirmAddTask(Integer contactId);
	
	
	/**
	 * 根据任务ID获取联系任务实体
	 * @param contactId
	 * @return
	 */
	public TskContact getTskContact(Map<String, Object> map);
	
	/**
     * 查询指定的任务量
     */
	public Integer getTskContactCount(Map<String, Object> map);

    /**
     * 联系计划选择客户 任务树
     */
    public List<BaseTskContact> getPlanCustTaskTree(Map<String,Object> map);
    
    /**
     * 关联任务的分页
     * @param map
     * @param page
     * @return
     */
    public PageUtil<TskTaskListBean> getCusRelatedTskContact(Map<String, Object> map,Page page);
    
    /**
     * 客户管理 任务分页
     */
    public PageUtil<CustModelTaskDetail> getTaskListForCustomerModel(Map<String, Object> parameters, Page page);
    /**
     * 查询组织机构以及用户树
     * @return
     */
    public List<CustomerAttrPlugin> getCustomerAttrPluginList(Map<String, Object> parameters);
    
    /**
     * 任务完成统计报表(分配者)
     * @param parameters
     * @return
     */
    public List<TskContactReportBean> getTaskReportAssignList(Map<String, Object> parameters);
    /**
     * 任务完成统计报表
     * @param parameters
     * @return
     */
    public List<TskContactReportBean> getTaskReportList(Map<String, Object> parameters);
    
    /**
     * 任务完成统计报表明细列表
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<CusRelatedTskContactBean> getTaskReportDetailPage(Map<String, Object> parameters, Page page,Integer searchType);
    /**
     * 编辑拜访记录查询下次联系任务
     */
    public TskContact getNextTaskForRecord(Map<String,Object> map);

}



