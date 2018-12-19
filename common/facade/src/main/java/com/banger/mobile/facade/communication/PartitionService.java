package com.banger.mobile.facade.communication;

import java.util.List;

import net.sf.json.JSONArray;

import com.banger.mobile.domain.model.communication.CommPartition;

/**
 * 交流区 分区
 * 
 * @author huyb
 * 
 */
public interface PartitionService {

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
	Integer updatePartition(CommPartition commPartition);

	/**
	 * 删除分区
	 * 
	 * @param commPartition
	 * @return
	 */
	Boolean deletePartition(CommPartition commPartition);

	/**
	 * 上移分区
	 * 
	 * @param commPartition
	 * @return
	 */
	Boolean moveUpPartition(CommPartition commPartition);

	/**
	 * 下移分区
	 * 
	 * @param commPartition
	 * @return
	 */
	Boolean moveDownPartition(CommPartition commPartition);
	
	/**
	 * 获取分区
	 * @param id
	 * @return
	 */
	CommPartition getCommPartition(Integer id);
	
	/**
	 * 查询全部分区
	 * @return
	 */
	List<CommPartition> getAllCommPartitions();
	
	/**
	 * 获取交流区下的所有分区
	 * @return
	 */
	JSONArray getCommPartitionList();
}
