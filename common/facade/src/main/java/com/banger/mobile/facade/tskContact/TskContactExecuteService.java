package com.banger.mobile.facade.tskContact;

import java.util.List;
import java.util.Map;
import com.banger.mobile.domain.model.base.tskContact.BaseTskContactExecute;
import com.banger.mobile.domain.model.tskContact.ExecuterAttrPlugin;
import com.banger.mobile.domain.model.tskContact.TaskContactExeDetail;
import com.banger.mobile.domain.model.tskContact.TskContactDaily;
import com.banger.mobile.domain.model.tskContact.TskExecuteUser;

import net.sf.json.JSONArray;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 17, 2012 1:37:56 PM
 * 类说明
 */
public interface TskContactExecuteService {
    /**
     * 插入联系任务执行者
     * @param list
     * @return
     */
    public Integer insertTaskExecute(String userIds,Integer contactId);
    

    /**
     * 更新联系任务执行者
     * @param list
     */
    public void updateTaskExecute(String userIds,Integer contactId);
    
    /**
     * 获取所有有权限的机构人员树
     * @return
     */
    public JSONArray getInchargeOfExecuterTree(List<String> userIds);
    
    /**
     * 编辑营销任务获取有权限的机构人员树
     * @param userIds
     * @param b
     * @return
     */
    public JSONArray getInchargeOfExecuterTree(List<String> userIds,boolean b);
    
    /**
     * 获取所有有权限的机构人员树（编辑）
     * 有初始值
     * @param contactId
     * @return
     */
    public JSONArray getInchargeOfExecuterTree(int contactId);
    
    /**
     * 根据任务ID获取所有的执行者
     * @param contactId
     * @return
     */
    public List<BaseTskContactExecute> getAllExeListByContactId(Integer contactId);
    
    /**
     * 任务每日联系量列表
     * @param contactId
     * @return
     */
    public List<TskContactDaily> getDailyContactNumList(Integer contactId);
    
    /**
     * 根据任务ID删除所有执行者信息
     * @param contactId
     */
    public void deleteExecuteByContactId(Integer contactId);
    
    /**
     * 查询任务机构人员树
     * @param map
     * @return
     */
     public JSONArray getExecuterAttrPlugin(Map<String, Object> map) throws CloneNotSupportedException;
     /**
      * 插入执行者
      */
     public Integer insertTaskExecute(BaseTskContactExecute execute);
     
     /**
      * 查看任务详情查询各执行者的日均量
      * @param map
      * @return
      */
     public TaskContactExeDetail getExecuterDetail(String userId,Integer contactId,String flag);
     
     /**
      * 获取批量修改执行者的执行者列表
      * @param contactId
      * @param deptId
      * @return
      */
     public List<TskExecuteUser> getTskExecuteUserList(Integer contactId,Integer deptId);
     
}



