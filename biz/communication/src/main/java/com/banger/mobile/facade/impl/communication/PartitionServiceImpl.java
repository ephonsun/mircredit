package com.banger.mobile.facade.impl.communication;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.banger.mobile.dao.communication.PartitionDao;
import com.banger.mobile.domain.model.communication.CommPartition;
import com.banger.mobile.facade.communication.PartitionService;

public class PartitionServiceImpl implements PartitionService {

	private PartitionDao partitionDao;

	public PartitionDao getPartitionDao() {
		return partitionDao;
	}

	public void setPartitionDao(PartitionDao partitionDao) {
		this.partitionDao = partitionDao;
	}

	public Integer insertPartition(CommPartition commPartition) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("partitionName", commPartition.getPartitionName());
		if (partitionDao.getCommPartitionByTitle(map) > 0) {
			return -2;
		} else {
			return partitionDao.insertPartition(commPartition);
		}
	}
	
	
	public Integer updatePartition(CommPartition commPartition) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("partitionName", commPartition.getPartitionName());
		map.put("partitionId", commPartition.getPartitionId());
		if (partitionDao.getCommPartitionByTitle(map) > 0) {
			return -2;
		} else {
			partitionDao.updatePartition(commPartition);
		}
		return 0;
	}

	public Boolean deletePartition(CommPartition commPartition) {
		return partitionDao.deletePartition(commPartition);
	}

	public Boolean moveUpPartition(CommPartition commPartition) {
		return movePartition(commPartition.getPartitionId(), "up");
	}

	public Boolean moveDownPartition(CommPartition commPartition) {
		return movePartition(commPartition.getPartitionId(), "down");
	}

	public CommPartition getCommPartition(Integer id) {
		return partitionDao.getCommPartition(id);
	}

	public JSONArray getCommPartitionList() {

		JSONArray jsonArray = new JSONArray();
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("id", 9999);
		root.put("name", "交流区");
		root.put("open", true);
		jsonArray.add(root);
		List<CommPartition> list = partitionDao.getCommPartitionList();
		for (CommPartition c : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", c.getPartitionId());
			map.put("name",c.getUserName()==null? c.getPartitionName(): c.getPartitionName()+"("+c.getUserName()+")");
			map.put("pId", 9999);
			map.put("isBuiltin", c.getIsBuiltin());
			jsonArray.add(map);
		}
		return jsonArray;
	}

	private Boolean movePartition(Integer id, String type) {
		try {
			CommPartition commPartition = this.getCommPartition(id);
			commPartition.setUpdateDate(new Timestamp(new Date().getTime()));
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("type", type);
			map.put("orderId", commPartition.getPartitionOrder());
			CommPartition baseCommPartition = partitionDao
					.getDesCommPartition(map);
			baseCommPartition
					.setUpdateDate(new Timestamp(new Date().getTime()));
			int orderId = baseCommPartition.getPartitionOrder();
			baseCommPartition.setPartitionOrder(commPartition
					.getPartitionOrder());
			commPartition.setPartitionOrder(orderId);
			partitionDao.updatePartition(commPartition);
			partitionDao.updatePartition(baseCommPartition);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<CommPartition> getAllCommPartitions() {
		return partitionDao.getCommPartitionList();
	}

}
