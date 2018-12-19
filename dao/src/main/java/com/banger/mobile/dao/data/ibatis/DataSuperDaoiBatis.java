package com.banger.mobile.dao.data.ibatis;

import com.banger.mobile.dao.data.DataSuperDao;
import com.banger.mobile.dao.data.DataVideoDao;
import com.banger.mobile.ibatis.GenericDaoiBatis;

public class DataSuperDaoiBatis extends GenericDaoiBatis implements DataSuperDao{

	public DataSuperDaoiBatis() {
	    super(DataSuperDao.class);
	}

    @SuppressWarnings("unchecked")
	public DataSuperDaoiBatis(Class persistentClass) {
        super(persistentClass);
    }

    public String getCustomerNameByDataId(int customerDataId) {
		return (String) this.getSqlMapClientTemplate().queryForObject("getCustomerNameByDataId",customerDataId);
		
		
	}

}
