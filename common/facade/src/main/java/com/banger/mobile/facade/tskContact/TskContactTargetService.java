package com.banger.mobile.facade.tskContact;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.base.tskContact.BaseTskContactTarget;
import com.banger.mobile.domain.model.tskContact.TskContactTarget;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 17, 2012 4:34:57 PM
 * 类说明
 */
public interface TskContactTargetService {
    
    /**
     * 新增联系任务联系客户
     * @param bean
     */
    public void addTskContactTarget(String customerIds,int contactId) throws CloneNotSupportedException;
    
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
     * 联系任务联系客户分页列表
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
     * 新增一条联系任务联系客户
     * @param bean
     */
    public Integer insertTskContactTarget(BaseTskContactTarget target);
    /**
     * 根据任务id查询下次联系的客户姓名
     */
    public String getTargetCustomerByTaskId(int taskId);
    /**
     * 根据任务id及执行者查询已分配的客户
     */
    public List<Integer> getcontactTargetIdsByMap(String userIds,Integer contactId);
    /**
     * 批量添加所有联系客户
     * @param customerIds
     * @param contactId
     * @throws CloneNotSupportedException 
     */
    public void addBatchTskContactTarget(int contactId,List<Integer> ids) throws CloneNotSupportedException;
}



