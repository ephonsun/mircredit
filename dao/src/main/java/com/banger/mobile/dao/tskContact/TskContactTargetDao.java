package com.banger.mobile.dao.tskContact;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.base.tskContact.BaseTskContactTarget;
import com.banger.mobile.domain.model.tskContact.TskContactTarget;
import com.banger.mobile.domain.model.tskContact.TskImportBean;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 17, 2012 3:31:01 PM
 * 类说明
 */
public interface TskContactTargetDao {

	/**
	 * 新增联系任务联系客户
	 * @param bean
	 */
	public void addTskContactTarget(List<BaseTskContactTarget> list);
	
	/**
	 * 删除联系客户
	 * @param executeIds
	 */
	public void deleteTskTargetByIds(String targetIds);
	
	/**
	 * 删除联系客户(根据任务ID)
	 * @param contactId
	 */
	public void deleteTargetByContactId(Integer contactId);
	
	/**
	 * 修改执行者
	 * @param targetIds
	 * @param executeId
	 */
	public void updateTargetExecuter(Map<String, Object> map);
	
	
	/**
	 * 修改联系客户备注
	 */
	public void updateTargetRemark(BaseTskContactTarget bean);
	/**
	 * 根据主键ids查询实体集合 
	 */
	public List<BaseTskContactTarget> getCustListByTargetIds(String targetIds);
	
	/**
	 * 联系任务联系客户分页
	 * @param map
	 * @param page
	 * @return
	 */
	public PageUtil<TskContactTarget> getContactCustomerList(Map<String, Object> map,Page page);
	/**
	 * 查询已分配的联系客户数
	 * @param map
	 * @return
	 */
	public Integer getTskContactTargetCount(Map<String, Object> map);
	
	/**
	 * 查询未分配的联系客户数
	 * @param contactId
	 * @return
	 */
	public Integer getTskTargetUnAssignedCount(Integer contactId);
	
	/**
	 * 根据任务ID删除所有未分配的客户
	 * @param contactId
	 */
	public void deleteUnAssignTarget(Integer contactId);

    /**
     * 批量添加导入客户
     */
    public void addTaskCustBatch(List<TskImportBean> parameterList);
    /**
     * 根据contactId查询任务下客户ids
     */
    public List<Integer> getCustIdsByContactId(int contactId);
    /**
     *  根据任务ID查询外部客户联系方式
     */
    public List<String> getPhoneListByContactId(int contactId);
    /**
     * 新增一条联系任务联系客户
     * @param bean
     */
    public Integer insertTskContactTarget(BaseTskContactTarget target);
    /**
     *查询需要自动完成的任务客户targetIds
     */
    public List<Integer> getTargetIdsForAutoFinish(Map<String,Object> map);
    /**
     * 根据任务id查询下次联系的客户姓名
     */
    public String getTargetCustomerByTaskId(int taskId);
    /**
     * 根据任务id及执行者查询已分配的客户
     */
    public List<Integer> getcontactTargetIdsByMap(Map<String,Object> map);
    /**
     * 根据任务id查询客户id集合
     */
    public List<String> getAllCustomerIdByContactId(int contactId);
}



