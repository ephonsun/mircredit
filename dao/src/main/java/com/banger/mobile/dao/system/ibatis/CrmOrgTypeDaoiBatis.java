package com.banger.mobile.dao.system.ibatis;

import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.system.CrmOrgTypeDao;
import com.banger.mobile.domain.model.maritalStatus.MaritalStatus;
import com.banger.mobile.domain.model.orgType.OrgType;
import com.banger.mobile.ibatis.GenericDaoiBatis;

public class CrmOrgTypeDaoiBatis extends GenericDaoiBatis implements CrmOrgTypeDao {
	
    public CrmOrgTypeDaoiBatis() {
        super(OrgType.class);
    }
    
    /**
     * 
     * @param persistentClass
     */
    public CrmOrgTypeDaoiBatis(Class persistentClass) {
        super(OrgType.class);
    }

    
	@Override
	public void addCrmOrgType(OrgType orgType) {
		this.getSqlMapClientTemplate().insert("addCrmOrgType", orgType);
	}

	@Override
	public void updateCrmOrgType(OrgType orgType) {
		this.getSqlMapClientTemplate().update("updateCrmOrgType",orgType);
	}

	@Override
	public void deleteCrmOrgType(int id) {
		this.getSqlMapClientTemplate().update("deleteCrmOrgTypeById",id);
	}

	@Override
	public OrgType getCrmOrgTypeById(int id) {
		return (OrgType)this.getSqlMapClientTemplate().queryForObject("getCrmOrgTypeById",id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrgType> getAllCrmOrgType() {
		return this.getSqlMapClientTemplate().queryForList("getAllCrmOrgType");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrgType> getSameCrmOrgTypeByName(OrgType orgType) {
		return this.getSqlMapClientTemplate().queryForList("getSameCrmOrgTypeByName",orgType);	
	}

	@Override
	public OrgType getMaxSortNoCrmOrgType() {
		return (OrgType)this.getSqlMapClientTemplate().queryForObject("getMaxSortNoCrmOrgType");
	}

	@Override
	public OrgType getMinSortNoCrmOrgType() {
		return (OrgType)this.getSqlMapClientTemplate().queryForObject("getMinSortNoCrmOrgType");
	}

	@Override
	public OrgType getNeedMoveCrmOrgType(Map<String, Object> parameters) {
		return (OrgType)this.getSqlMapClientTemplate().queryForObject("getNeedMoveCrmOrgType",parameters);
	}

}
