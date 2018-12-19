package com.banger.mobile.dao.communication.iBatis;

import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.communication.PartitionDao;
import com.banger.mobile.domain.model.communication.CommPartition;
import com.banger.mobile.ibatis.GenericDaoiBatis;

public class PartitionDaoiBatis extends GenericDaoiBatis implements
		PartitionDao {

	public PartitionDaoiBatis(Class persistentClass) {
		super(persistentClass);
	}

	public PartitionDaoiBatis() {
		super(PartitionDaoiBatis.class);
	}

	public Integer insertPartition(CommPartition commPartition) {
		return (Integer) this.getSqlMapClientTemplate().insert(
				"addCommPartition", commPartition);
	}

	public Boolean updatePartition(CommPartition commPartition) {
		this.getSqlMapClientTemplate().update("updateCommPartition",
				commPartition);
		return true;
	}

	public Boolean deletePartition(CommPartition commPartition) {
		this.getSqlMapClientTemplate().delete("deleteCommPartition",
				commPartition);
		return true;
	}

	public CommPartition getCommPartition(Integer id) {
		return (CommPartition) this.getSqlMapClientTemplate().queryForObject(
				"getCommPartitionById", id);
	}

	public List<CommPartition> getCommPartitionList() {
		return this.getSqlMapClientTemplate().queryForList(
				"getCommPartitionList");
	}

	public CommPartition getDesCommPartition(Map<String, Object> map) {
		return (CommPartition) this.getSqlMapClientTemplate().queryForObject("getDesCommPartition",map);
	}

	public Integer getCommPartitionByTitle(Map<String, Object> map) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("getCommPartitionByName",map);
	}

}
