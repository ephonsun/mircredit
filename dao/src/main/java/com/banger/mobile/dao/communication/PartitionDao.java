package com.banger.mobile.dao.communication;

import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.communication.CommPartition;

public interface PartitionDao {
	/**
	 * 插入分区
	 * 
	 * @param commPartition
	 * @return
	 */
	Integer insertPartition(CommPartition commPartition);

	/**
	 * 编辑分区
	 * 
	 * @param commPartition
	 * @return
	 */
	Boolean updatePartition(CommPartition commPartition);

	/**
	 * 删除分区
	 * 
	 * @param commPartition
	 * @return
	 */
	Boolean deletePartition(CommPartition commPartition);
	
	/**
	 * 获取分区
	 * @param id
	 * @return
	 */
	CommPartition getCommPartition(Integer id);
	
	/**
	 * 获取交流区下的所有分区
	 * @return
	 */
	List<CommPartition> getCommPartitionList();
	
	/**
	 * 取上移下移的分区
	 * @param map
	 * @return
	 */
	CommPartition getDesCommPartition(Map<String,Object> map);
	
	/**
	 * 通过标题查询
	 * @param map
	 * @return
	 */
	Integer getCommPartitionByTitle(Map<String,Object> map);
}
