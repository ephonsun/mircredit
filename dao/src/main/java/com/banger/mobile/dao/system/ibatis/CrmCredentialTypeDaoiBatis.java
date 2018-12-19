package com.banger.mobile.dao.system.ibatis;

import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.system.CrmCredentialTypeDao;
import com.banger.mobile.domain.model.credentialType.CredentialType;
import com.banger.mobile.domain.model.system.CrmCustomerType;
import com.banger.mobile.ibatis.GenericDaoiBatis;

public class CrmCredentialTypeDaoiBatis extends GenericDaoiBatis implements CrmCredentialTypeDao {
	
	
    public CrmCredentialTypeDaoiBatis() {
        super(CredentialType.class);
    }
    
    /**
     * 
     * @param persistentClass
     */
    public CrmCredentialTypeDaoiBatis(Class persistentClass) {
        super(CredentialType.class);
    }
    

	@Override
	public void addCrmCredentialType(CredentialType credentialType) {
		this.getSqlMapClientTemplate().insert("addCrmCredentialType", credentialType);

	}

	@Override
	public void updateCrmCredentialType(CredentialType credentialType) {
		this.getSqlMapClientTemplate().update("updateCrmCredentialType",credentialType);
	}

	@Override
	public void deleteCrmCredentialType(int id) {
		this.getSqlMapClientTemplate().update("deleteCrmCredentialTypeById",id);
	}

	@Override
	public CredentialType getCrmCredentialTypeById(int id) {
		return (CredentialType)this.getSqlMapClientTemplate().queryForObject("getCrmCredentialTypeById",id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CredentialType> getAllCrmCredentialType() {
		return this.getSqlMapClientTemplate().queryForList("getAllCrmCredentialType");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CredentialType> getSameCrmCredentialTypeByName(CredentialType credentialType) {
        return this.getSqlMapClientTemplate().queryForList("getSameCrmCredentialTypeByName",credentialType);
	}

	@Override
	public CredentialType getMaxSortNoCrmCredentialType() {
		return (CredentialType)this.getSqlMapClientTemplate().queryForObject("getMaxSortNoCrmCredentialType");
	}

	@Override
	public CredentialType getMinSortNoCredentialType() {
		 return (CredentialType)this.getSqlMapClientTemplate().queryForObject("getMinSortNoCrmCredentialType");
	}

	@Override
	public CredentialType getNeedMoveCrmCredentialType(Map<String, Object> parameters) {
		return (CredentialType)this.getSqlMapClientTemplate().queryForObject("getNeedMoveCrmCredentialType",parameters);
	}

}
