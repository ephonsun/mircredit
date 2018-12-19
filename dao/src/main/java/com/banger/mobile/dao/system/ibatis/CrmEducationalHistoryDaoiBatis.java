package com.banger.mobile.dao.system.ibatis;

import java.util.List;
import java.util.Map;
import com.banger.mobile.dao.system.CrmEducationalHistoryDao;
import com.banger.mobile.domain.model.credentialType.CredentialType;
import com.banger.mobile.domain.model.educationalHistory.EducationalHistory;
import com.banger.mobile.ibatis.GenericDaoiBatis;

public class CrmEducationalHistoryDaoiBatis extends GenericDaoiBatis implements
		CrmEducationalHistoryDao {

    public CrmEducationalHistoryDaoiBatis() {
        super(EducationalHistory.class);
    }
    
    /**
     * 
     * @param persistentClass
     */
    public CrmEducationalHistoryDaoiBatis(Class persistentClass) {
        super(EducationalHistory.class);
    }
    

	@Override
	public void addCrmEducationalHistory(EducationalHistory educationalHistory) {
		this.getSqlMapClientTemplate().insert("addCrmEducationalHistory", educationalHistory);
	}

	@Override
	public void updateCrmEducationalHistory(EducationalHistory educationalHistory) {
		this.getSqlMapClientTemplate().update("updateCrmEducationalHistory",educationalHistory);
	}

	@Override
	public void deleteCrmEducationalHistory(int id) {
		this.getSqlMapClientTemplate().update("deleteCrmEducationalHistoryById",id);
	}

	@Override
	public EducationalHistory getCrmEducationalHistoryById(int id) {
		return (EducationalHistory)this.getSqlMapClientTemplate().queryForObject("getCrmEducationalHistoryById",id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EducationalHistory> getAllCrmEducationalHistory() {
		return this.getSqlMapClientTemplate().queryForList("getAllCrmEducationalHistory");		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EducationalHistory> getSameCrmEducationalHistoryByName(EducationalHistory educationalHistory) {
		return this.getSqlMapClientTemplate().queryForList("getSameCrmEducationalHistoryByName",educationalHistory);
	}

	@Override
	public EducationalHistory getMaxSortNoCrmEducationalHistory() {
		return (EducationalHistory)this.getSqlMapClientTemplate().queryForObject("getMaxSortNoCrmEducationalHistory");
		
	}

	@Override
	public EducationalHistory getMinSortNoCrmEducationalHistory() {
		return (EducationalHistory)this.getSqlMapClientTemplate().queryForObject("getMinSortNoCrmEducationalHistory");
	}

	@Override
	public EducationalHistory getNeedMoveCrmEducationalHistory(Map<String, Object> parameters) {
		return (EducationalHistory)this.getSqlMapClientTemplate().queryForObject("getNeedMoveCrmEducationalHistory",parameters);
	}

}
