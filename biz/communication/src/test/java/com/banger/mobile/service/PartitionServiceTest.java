package com.banger.mobile.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.banger.mobile.domain.model.communication.CommPartition;
import com.banger.mobile.facade.BaseServiceTestCase;
import com.banger.mobile.facade.communication.PartitionService;

public class PartitionServiceTest extends BaseServiceTestCase{
	private PartitionService partitionService;

	public void setPartitionService(PartitionService partitionService) {
		this.partitionService = partitionService;
	}
	
//	public void testInsertPartition(){
//		CommPartition commPartition = new CommPartition();
//		commPartition.setCreateDate(new Timestamp(new Date().getTime()));
//		commPartition.setCreateUser(1);
//		commPartition.setUserId(0);
//		commPartition.setIsBuiltin(0);
//		commPartition.setIsDel(0);
//		commPartition.setPartitionDescription("2222");
//		commPartition.setPartitionName("1113");
//		int id = partitionService.insertPartition(commPartition);
//		assertTrue(id > 0);
//		setComplete();
//		endTransaction();
//	}
	
//	public void testUpdatePartition(){
//		CommPartition commPartition = new CommPartition();
//		commPartition.setPartitionId(3);
//		commPartition.setUpdateDate(new Timestamp(new Date().getTime()));
//		commPartition.setUpdateUser(2);
//		commPartition.setUserId(0);
//		commPartition.setPartitionDescription("1123");
//		commPartition.setPartitionName("fsdfsd");
//		partitionService.updatePartition(commPartition);
//		setComplete();
//		endTransaction();
//	}
	
//	public void testDeletePartition(){
//		CommPartition commPartition = new CommPartition();
//		commPartition.setPartitionId(3);
//		commPartition.setUpdateDate(new Timestamp(new Date().getTime()));
//		commPartition.setUpdateUser(2);
//		partitionService.deletePartition(commPartition);
//	}
//	
//	public void testGetCommPartition(){
//		CommPartition commPartition = partitionService.getCommPartition(3);
//		assertNotNull(commPartition);
//	}
	
//	public void testGetCommPartitionList(){
//		List<CommPartition> list = partitionService.getCommPartitionList();
//		assertNotSame(list.size(),0);
//	}
	
	public void testmoveUpPartition(){
		CommPartition commPartition = new CommPartition();
		commPartition.setPartitionId(9);
		partitionService.moveUpPartition(commPartition);
		setComplete();
		endTransaction();
	}
}
