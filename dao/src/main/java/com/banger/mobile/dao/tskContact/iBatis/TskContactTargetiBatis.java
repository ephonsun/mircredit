package com.banger.mobile.dao.tskContact.iBatis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.tskContact.TskContactTargetDao;
import com.banger.mobile.domain.model.base.tskContact.BaseTskContactTarget;
import com.banger.mobile.domain.model.tskContact.TskContactTarget;
import com.banger.mobile.domain.model.tskContact.TskImportBean;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 17, 2012 4:00:15 PM
 * 类说明
 */
public class TskContactTargetiBatis extends GenericDaoiBatis implements TskContactTargetDao{

	public TskContactTargetiBatis(Class persistentClass) {
		super(BaseTskContactTarget.class);
		// TODO Auto-generated constructor stub
	}
	public TskContactTargetiBatis() {
		super(BaseTskContactTarget.class);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 新增联系任务联系客户
	 * @param bean
	 */
	public void addTskContactTarget(List<BaseTskContactTarget> list){
		if(list!=null){
			this.exectuteBatchInsert("insertContactTarget", list);
		}
	}

	public List<String> getAllCustomerIdByContactId(int contactId){
		return (List<String>)this.getSqlMapClientTemplate().queryForList("getAllCustomerIdByContactId",contactId);
	}

	/**
	 * 删除联系客户
	 * @param executeIds
	 */
	public void deleteTskTargetByIds(String targetIds){
		this.getSqlMapClientTemplate().delete("deleteTskTarget",targetIds);
	}

	/**
	 * 修改执行者
	 * @param targetIds
	 * @param executeId
	 */
	public void updateTargetExecuter(Map<String, Object> map){
		this.getSqlMapClientTemplate().update("updateTargetExecuter",map);
	}

	/**
	 * 修改联系客户备注
	 */
	public void updateTargetRemark(BaseTskContactTarget bean){
		this.getSqlMapClientTemplate().update("updateTargetRemark",bean);
	}
	/**
	 * 根据主键ids查询实体集合
	 */
	public List<BaseTskContactTarget> getCustListByTargetIds(String targetIds){
		return this.getSqlMapClientTemplate().queryForList("getCustListByTargetIds",targetIds);
	}

	/**
	 * 联系任务联系客户分页
	 * @param map
	 * @param page
	 * @return
	 */
	public PageUtil<TskContactTarget> getContactCustomerList(Map<String, Object> map,Page page){
		List<TskContactTarget> list = (List<TskContactTarget>)this.findQueryPage("getContactCustomerList",
				"getContactCustomerListCount", map, page);
		if(list==null){
			list = new ArrayList<TskContactTarget>();
		}
		return new PageUtil<TskContactTarget>(list,page);
	}

	/**
	 * 删除联系客户(根据任务ID)
	 * @param contactId
	 */
	public void deleteTargetByContactId(Integer contactId){
		this.getSqlMapClientTemplate().delete("deleteTargetByContactId",contactId);
	}

	/**
	 * 查询已分配的联系客户数
	 * @param map
	 * @return
	 */
	public Integer getTskContactTargetCount(Map<String, Object> map){
		return (Integer)this.getSqlMapClientTemplate().queryForObject("getTskContactTargetCount",map);
	}

	/**
	 * 查询未分配的联系客户数
	 * @param contactId
	 * @return
	 */
	public Integer getTskTargetUnAssignedCount(Integer contactId){
		return (Integer)this.getSqlMapClientTemplate().queryForObject("getTskTargetUnAssignedCount",contactId);
	}

	/**
	 * 根据任务ID删除所有未分配的客户
	 * @param contactId
	 */
	public void deleteUnAssignTarget(Integer contactId){
		this.getSqlMapClientTemplate().delete("deleteUnAssignTarget",contactId);
	}

	/**
	 * 批量添加导入客户
	 */
	public void addTaskCustBatch(List<TskImportBean> parameterList) {
		this.exectuteBatchInsert("insertCustomerForImport", parameterList);
	}
	/**
	 * 根据contactId查询任务下客户ids
	 */
	public List<Integer> getCustIdsByContactId(int contactId){
		return this.getSqlMapClientTemplate().queryForList("getCustIdsByContactId",contactId);
	}
	/**
	 *  根据任务ID查询外部客户联系方式
	 */
	public List<String> getPhoneListByContactId(int contactId){
		return this.getSqlMapClientTemplate().queryForList("getPhoneListByContactId",contactId);
	}
	/**
	 * 新增一条联系任务联系客户
	 * @param bean
	 */
	public Integer insertTskContactTarget(BaseTskContactTarget target){
		target.setCreateDate(new Date());
		return (Integer) this.getSqlMapClientTemplate().insert("insertContactTarget", target);
	}
	/**
	 *查询需要自动完成的任务客户targetIds
	 */
	public List<Integer> getTargetIdsForAutoFinish(Map<String,Object> map){
		return this.getSqlMapClientTemplate().queryForList("getTargetIdsForAutoFinish",map);
	}
	/**
	 * 根据任务id查询下次联系的客户姓名
	 */
	public String getTargetCustomerByTaskId(int taskId){
		return (String) this.getSqlMapClientTemplate().queryForObject("getTargetCustomerByTaskId",taskId);
	}
	/**
	 * 根据任务id及执行者查询已分配的客户
	 */
	public List<Integer> getcontactTargetIdsByMap(Map<String,Object> map){
		return this.getSqlMapClientTemplate().queryForList("getcontactTargetIdsByMap",map);
	}
}



