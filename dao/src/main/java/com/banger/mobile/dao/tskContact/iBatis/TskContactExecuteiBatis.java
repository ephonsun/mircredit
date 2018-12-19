package com.banger.mobile.dao.tskContact.iBatis;

import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.tskContact.TskContactExecuteDao;
import com.banger.mobile.domain.model.base.tskContact.BaseTskContactExecute;
import com.banger.mobile.domain.model.tskContact.ExecuterAttrPlugin;
import com.banger.mobile.domain.model.tskContact.TaskContactExeDetail;
import com.banger.mobile.domain.model.tskContact.TskContactDaily;
import com.banger.mobile.domain.model.tskContact.TskExecuteUser;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 17, 2012 1:33:10 PM
 * 类说明
 */
public class TskContactExecuteiBatis extends GenericDaoiBatis implements TskContactExecuteDao {
	
	public TskContactExecuteiBatis(Class persistentClass) {
		super(BaseTskContactExecute.class);
		// TODO Auto-generated constructor stub
	}
	
	public TskContactExecuteiBatis() {
		super(BaseTskContactExecute.class);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 插入联系任务执行者
	 * @param baseTskContactExecute
	 * @return
	 */
	public Integer insertTaskExecute(List<BaseTskContactExecute> list){
		return this.exectuteBatchInsert("insertTskExecuter", list);
	}
	
	/**
	 * 根据任务Id查询所有的执行者ID集合
	 * @param contactId
	 * @return
	 */
	public List<String> getAllExeUserIdsByTaskId(Integer contactId){
		return this.getSqlMapClientTemplate().queryForList("getExecuteByTaskId", contactId);
	}
	
	/**
	 * 根据主键ID删除执行者
	 * @param executeId
	 */
	public void deleteExecute(List<BaseTskContactExecute> list){
		this.executeBatchDelete("deleteExecute", list);
	}
	
	/**
	 * 根据任务ID获取所有的执行者List
	 * @param contactId
	 * @return
	 */
	public List<BaseTskContactExecute> getAllExeListByContactId(Integer contactId){
		return (List<BaseTskContactExecute>)this.getSqlMapClientTemplate().queryForList("getAllExeListByContactId", contactId);
	}
	
	/**
	 * 任务每日联系量列表
	 * @param contactId
	 * @return
	 */
	public List<TskContactDaily> getDailyContactNumList(Integer contactId){
		return (List<TskContactDaily>)this.getSqlMapClientTemplate().queryForList("getDailyContactNumList",contactId);
	}
	
	/**
	 * 根据任务ID删除所有执行者信息
	 * @param contactId
	 */
	public void deleteExecuteByContactId(Integer contactId){
		this.getSqlMapClientTemplate().delete("delExecuteByTaskId",contactId);
	}
	
	/**
	 * 获得有联系客户的执行者UserId
	 * @param contactId
	 * @return
	 */
	public List<String> getHaveTargetExecuteList(Integer contactId){
		return (List<String>)this.getSqlMapClientTemplate().queryForList("getHaveTargetExecuteList",contactId);
	}

	/**
     *根据任务id查询实体集合
     */
	public List<BaseTskContactExecute> getExecuteListByContactId(Integer contactId){
	    return this.getSqlMapClientTemplate().queryForList("getExecuteListByContactId",contactId);
	}
	
    /**
     * 查询任务机构人员树
     * @param map
     * @return
     */
    public List<ExecuterAttrPlugin> getExecuterAttrPlugin(Map<String, Object> map){
    	return (List<ExecuterAttrPlugin>)this.getSqlMapClientTemplate().queryForList("getExecuterAttrPlugin",map);
    }
	/**
	 * 插入执行者
	 */
	public Integer insertTaskExecute(BaseTskContactExecute execute){
	    return (Integer) this.getSqlMapClientTemplate().insert("insertTskExecuter",execute);
	}
	
    /**
     * 查看任务详情查询各执行者的日均量(人员)
     * @param map
     * @return
     */
    public TaskContactExeDetail getExecuterDetail(Map<String, Object> map){
    	return (TaskContactExeDetail)this.getSqlMapClientTemplate().queryForObject("getExecuterDetail",map);
    }
    /**
     * 查看任务详情查询各执行者的日均量(机构)
     * @param map
     * @return
     */
    public TaskContactExeDetail getExecuterDetailForDept(Map<String, Object> map){
        return (TaskContactExeDetail)this.getSqlMapClientTemplate().queryForObject("getExecuterDetailForDept",map);
    }
    /**
     * 根据任务ID和部门ID获得执行者列表
     * @param map
     * @return
     */
    public List<TskExecuteUser> getTskExecuteUserList(Map<String, Object> map){
    	return this.getSqlMapClientTemplate().queryForList("getExecuteUserList",map);
    }
}

