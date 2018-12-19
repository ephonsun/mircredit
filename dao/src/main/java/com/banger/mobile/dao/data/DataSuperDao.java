package com.banger.mobile.dao.data;

public interface DataSuperDao {
	/**
    * 查找该客户数据所属的客户名字
    * @param customerDataId
    * 
    * @return 顾客名字
    */
	public  String getCustomerNameByDataId(int customerDataId);
	
}
