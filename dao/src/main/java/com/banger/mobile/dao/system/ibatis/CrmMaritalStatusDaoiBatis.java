package com.banger.mobile.dao.system.ibatis;

import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.system.CrmMaritalStatusDao;
import com.banger.mobile.domain.model.maritalStatus.MaritalStatus;
import com.banger.mobile.ibatis.GenericDaoiBatis;

public class CrmMaritalStatusDaoiBatis extends GenericDaoiBatis implements CrmMaritalStatusDao {

    public CrmMaritalStatusDaoiBatis() {
        super(MaritalStatus.class);
    }
    
    /**
     * 
     * @param persistentClass
     */
    public CrmMaritalStatusDaoiBatis(Class persistentClass) {
        super(MaritalStatus.class);
    }
        
	@Override
	public void addCrmMaritalStatus(MaritalStatus maritalStatus) {
		this.getSqlMapClientTemplate().insert("addCrmMaritalStatus", maritalStatus);
	}

	@Override
	public void updateCrmMaritalStatus(MaritalStatus maritalStatus) {
		this.getSqlMapClientTemplate().update("updateCrmMaritalStatus",maritalStatus);
	}

	@Override
	public void deleteCrmMaritalStatus(int id) {
		this.getSqlMapClientTemplate().update("deleteCrmMaritalStatusById",id);
	}

	@Override
	public MaritalStatus getCrmMaritalStatusById(int id) {
		return (MaritalStatus)this.getSqlMapClientTemplate().queryForObject("getCrmMaritalStatusById",id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MaritalStatus> getAllCrmMaritalStatus() {
		return this.getSqlMapClientTemplate().queryForList("getAllCrmMaritalStatus");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MaritalStatus> getSameCrmMaritalStatusByName(MaritalStatus maritalStatus) {
		return this.getSqlMapClientTemplate().queryForList("getSameCrmMaritalStatusByName",maritalStatus);		
	}

	@Override
	public MaritalStatus getMaxSortNoCrmMaritalStatus() {
		return (MaritalStatus)this.getSqlMapClientTemplate().queryForObject("getMaxSortNoCrmMaritalStatus");
	}

	@Override
	public MaritalStatus getMinSortNoCrmMaritalStatus() {
		return (MaritalStatus)this.getSqlMapClientTemplate().queryForObject("getMinSortNoCrmMaritalStatus");
	}

	@Override
	public MaritalStatus getNeedMoveCrmMaritalStatus(Map<String, Object> parameters) {
		return (MaritalStatus)this.getSqlMapClientTemplate().queryForObject("getNeedMoveCrmMaritalStatus",parameters);	
	}

}
