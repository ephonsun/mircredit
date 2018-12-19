package com.banger.mobile.dao.system.ibatis;

import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.system.CrmLegalFormDao;
import com.banger.mobile.domain.model.legalForm.LegalForm;
import com.banger.mobile.ibatis.GenericDaoiBatis;

public class CrmLegalFormDaoiBatis extends GenericDaoiBatis implements
		CrmLegalFormDao {

    public CrmLegalFormDaoiBatis() {
        super(LegalForm.class);
    }    
    /**
     * 
     * @param persistentClass
     */
    public CrmLegalFormDaoiBatis(Class persistentClass) {
        super(LegalForm.class);
    }

    
	@Override
	public void addCrmLegalForm(LegalForm legalForm) {
		this.getSqlMapClientTemplate().insert("addCrmLegalForm", legalForm);
	}

	@Override
	public void updateCrmLegalForm(LegalForm legalForm) {
		this.getSqlMapClientTemplate().update("updateCrmLegalForm",legalForm);
	}

	@Override
	public void deleteCrmLegalForm(int id) {
		this.getSqlMapClientTemplate().update("deleteCrmLegalFormById",id);
	}

	@Override
	public LegalForm getCrmLegalFormById(int id) {
		return (LegalForm)this.getSqlMapClientTemplate().queryForObject("getCrmLegalFormById",id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LegalForm> getAllCrmLegalForm() {
		return this.getSqlMapClientTemplate().queryForList("getAllCrmLegalForm");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LegalForm> getSameCrmLegalFormByName(LegalForm legalForm) {
		return this.getSqlMapClientTemplate().queryForList("getSameCrmLegalFormByName",legalForm);
	}

	@Override
	public LegalForm getMaxSortNoCrmLegalForm() {
		return (LegalForm)this.getSqlMapClientTemplate().queryForObject("getMaxSortNoCrmLegalForm");
	}

	@Override
	public LegalForm getMinSortNoCrmLegalForm() {
		return (LegalForm)this.getSqlMapClientTemplate().queryForObject("getMinSortNoCrmLegalForm");
	}

	@Override
	public LegalForm getNeedMoveCrmLegalForm(Map<String, Object> parameters) {
		return (LegalForm)this.getSqlMapClientTemplate().queryForObject("getNeedMoveCrmLegalForm",parameters);
	}

}
