/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :任务执行明细-Dao-接口实现
 * Author     :QianJie
 * Create Date:Mar 2, 2013
 */
package com.banger.mobile.dao.microTask.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.microTask.TskMicroTaskExecuteDao;
import com.banger.mobile.domain.model.microTask.TskMicroTaskExecute;
import com.banger.mobile.domain.model.microTask.TskMicroTaskExecuteAssign;
import com.banger.mobile.ibatis.GenericDaoiBatis;
import com.banger.mobile.util.StringUtil;

/**
 * @author QianJie
 * @version $Id: TskMicroTaskExecuteDaoiBatis.java,v 0.1 Mar 2, 2013 11:19:46 AM
 *          QianJie Exp $
 */
public class TskMicroTaskExecuteDaoiBatis extends GenericDaoiBatis implements
		TskMicroTaskExecuteDao {

	public TskMicroTaskExecuteDaoiBatis() {
		super(TskMicroTaskExecute.class);
	}

	public TskMicroTaskExecuteDaoiBatis(Class persistentClass) {
		super(TskMicroTaskExecute.class);
	}

	/**
	 * 添加任务执行者
	 * 
	 * @param tskMicroTaskExecute
	 * @see com.banger.mobile.dao.microTask.TskMicroTaskExecuteDao#addTskMicroTaskExecute(com.banger.mobile.domain.model.microTask.TskMicroTaskExecute)
	 */
	public void addTskMicroTaskExecute(TskMicroTaskExecute tskMicroTaskExecute) {
		this.getSqlMapClientTemplate().insert("addTskMicroTaskExecute",
				tskMicroTaskExecute);
	}

	/**
	 * 批量添加任务执行者
	 * 
	 * @param list
	 * @see com.banger.mobile.dao.microTask.TskMicroTaskExecuteDao#addTskMicroTaskExecuteBatch(java.util.List)
	 */
	public void addTskMicroTaskExecuteBatch(List<TskMicroTaskExecute> list) {
		this.exectuteBatchInsert("addTskMicroTaskExecute", list);
	}

	/**
	 * 删除任务执行者
	 * 
	 * @param conds
	 * @see com.banger.mobile.dao.microTask.TskMicroTaskExecuteDao#delTskMicroTaskExecuteByConds(java.util.Map)
	 */
	public void delTskMicroTaskExecuteByConds(Map<String, Object> conds) {
		Map<String, Object> fConds = new HashMap<String, Object>();
		for (Map.Entry<String, Object> entry : conds.entrySet()) {
			if (entry.getValue() instanceof String) {
				fConds.put(entry.getKey(), StringUtil
						.ReplaceSQLEscapeChar(StringUtil.ReplaceSQLChar(entry
								.getValue().toString())));
			} else {
				fConds.put(entry.getKey(), entry.getValue());
			}
		}
		this.getSqlMapClientTemplate().delete("delTskMicroTaskExecuteByConds",
				fConds);
	}

	/**
	 * 编辑任务执行者
	 * 
	 * @param tskMicroTaskExecute
	 * @see com.banger.mobile.dao.microTask.TskMicroTaskExecuteDao#editTskMicroTaskExecute(com.banger.mobile.domain.model.microTask.TskMicroTaskExecute)
	 */
	public void editTskMicroTaskExecute(TskMicroTaskExecute tskMicroTaskExecute) {
		this.getSqlMapClientTemplate().update("editTskMicroTaskExecute",
				tskMicroTaskExecute);
	}

	/**
	 * @param list
	 * @see com.banger.mobile.dao.microTask.TskMicroTaskExecuteDao#editTskMicroTaskExecute(java.util.List)
	 */
	public void editTskMicroTaskExecute(List<TskMicroTaskExecute> list) {
		this.executeBatchUpdate("editTskMicroTaskExecute", list);
	}

	/**
	 * 查询所有任务执行者数据
	 * 
	 * @param conds
	 * @return
	 * @see com.banger.mobile.dao.microTask.TskMicroTaskExecuteDao#getAllTskMicroTaskExecuteByConds(java.util.Map)
	 */
	public List<TskMicroTaskExecute> getAllTskMicroTaskExecuteByConds(
			Map<String, Object> conds) {
		Map<String, Object> fConds = new HashMap<String, Object>();
		for (Map.Entry<String, Object> entry : conds.entrySet()) {
			if (entry.getValue() instanceof String) {
				fConds.put(entry.getKey(), StringUtil
						.ReplaceSQLEscapeChar(StringUtil.ReplaceSQLChar(entry
								.getValue().toString())));
			} else {
				fConds.put(entry.getKey(), entry.getValue());
			}
		}
		return this.getSqlMapClientTemplate().queryForList(
				"getAllTskMicroTaskExecuteByConds", fConds);
	}

	/**
	 * 查询所有任务执行者数据
	 * 
	 * @param conds
	 * @return
	 * @see com.banger.mobile.dao.microTask.TskMicroTaskExecuteDao#getAllTskMicroTaskExecuteByConds(java.util.Map)
	 */
	public List<TskMicroTaskExecute> getAllTskMicroTaskExecuteByCondsProgress(
			Map<String, Object> conds) {
		Map<String, Object> fConds = new HashMap<String, Object>();
		for (Map.Entry<String, Object> entry : conds.entrySet()) {
			if (entry.getValue() instanceof String) {
				fConds.put(entry.getKey(), StringUtil
						.ReplaceSQLEscapeChar(StringUtil.ReplaceSQLChar(entry
								.getValue().toString())));
			} else {
				fConds.put(entry.getKey(), entry.getValue());
			}
		}
		return this.getSqlMapClientTemplate().queryForList(
				"getAllTskMicroTaskExecuteByCondsProgress", fConds);
	}

	/**
	 * 查询我已经分配出去的任务目标
	 * 
	 * @param conds
	 * @return
	 * @see com.banger.mobile.dao.microTask.TskMicroTaskExecuteDao#getMyTargetByConds(java.util.Map)
	 */
	public Integer getMyTargetByConds(Map<String, Object> conds) {
		Map<String, Object> fConds = new HashMap<String, Object>();
		for (Map.Entry<String, Object> entry : conds.entrySet()) {
			if (entry.getValue() instanceof String) {
				fConds.put(entry.getKey(), StringUtil
						.ReplaceSQLEscapeChar(StringUtil.ReplaceSQLChar(entry
								.getValue().toString())));
			} else {
				fConds.put(entry.getKey(), entry.getValue());
			}
		}
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"getMyTargetByConds", fConds);
	}

	/**
	 * 查询任务执行者已完成任务笔数
	 * 
	 * @param conds
	 * @return
	 * @see com.banger.mobile.dao.microTask.TskMicroTaskExecuteDao#getAllTskMicroTaskExecuteAssignByConds(java.util.Map)
	 */
	public Integer getComTskNumByConds(Map<String, Object> conds) {
		Map<String, Object> fConds = new HashMap<String, Object>();
		for (Map.Entry<String, Object> entry : conds.entrySet()) {
			if (entry.getValue() instanceof String) {
				fConds.put(entry.getKey(), StringUtil
						.ReplaceSQLEscapeChar(StringUtil.ReplaceSQLChar(entry
								.getValue().toString())));
			} else {
				fConds.put(entry.getKey(), entry.getValue());
			}
		}
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"getComTskNumByConds", fConds);
	}

	/**
	 * 查询机构下分配任务的人员
	 * 
	 * @param conds
	 * @return
	 */
	public List<TskMicroTaskExecute> getSysUsersByTask(Map<String, Object> conds) {
		return this.getSqlMapClientTemplate().queryForList("getSysUsersByTask",
				conds);
	}

	/**
	 * 批量更新任务执行者
	 * 
	 * @param list
	 * @see com.banger.mobile.dao.microTask.TskMicroTaskExecuteDao#TskMicroTaskExecuteBatch(java.util.List)
	 */
	public void TskMicroTaskExecuteBatch(List<TskMicroTaskExecute> list) {
	}

	/**
	 * @param paras
	 * @return
	 * @see com.banger.mobile.dao.microTask.TskMicroTaskExecuteDao#getTaskUserExecutable(java.util.Map)
	 */
	public List<TskMicroTaskExecute> getTaskUserExecutable(
			Map<String, Object> paras) {
		return this.getSqlMapClientTemplate().queryForList(
				"getTaskUserExecutable", paras);
	}

	/**
	 * 通过userId查询哪些user已经分配了任务
	 */
	public List<Integer> getIsAllocateTaskByUserIds(Map<String, Object> param) {
		return this.getSqlMapClientTemplate().queryForList(
				"getIsAllocateTaskByUserIds", param);
	}

}
