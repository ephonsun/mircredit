package com.banger.mobile.dao.system.ibatis;

import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.system.CrmLivingConditionDao;
import com.banger.mobile.domain.model.livingCondition.LivingCondition;
import com.banger.mobile.ibatis.GenericDaoiBatis;

public class CrmLivingConditionDaoiBatis extends GenericDaoiBatis implements CrmLivingConditionDao {

    public CrmLivingConditionDaoiBatis() {
        super(LivingCondition.class);
    }
    
    /**
     * 
     * @param persistentClass
     */
    public CrmLivingConditionDaoiBatis(Class persistentClass) {
        super(LivingCondition.class);
    }
    
    
	@Override
	public void addCrmLivingCondition(LivingCondition livingCondition) {
		this.getSqlMapClientTemplate().insert("addCrmLivingCondition", livingCondition);
	}

	@Override
	public void updateCrmLivingCondition(LivingCondition livingCondition) {
		this.getSqlMapClientTemplate().update("updateCrmLivingCondition",livingCondition);
	}

	@Override
	public void deleteCrmLivingCondition(int id) {
		this.getSqlMapClientTemplate().update("deleteCrmLivingConditionById",id);
	}

	@Override
	public LivingCondition getCrmLivingConditionById(int id) {
		return (LivingCondition)this.getSqlMapClientTemplate().queryForObject("getCrmLivingConditionById",id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LivingCondition> getAllCrmLivingCondition() {
		return this.getSqlMapClientTemplate().queryForList("getAllCrmLivingCondition");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LivingCondition> getSameCrmLivingConditionByName(LivingCondition livingCondition) {
		return this.getSqlMapClientTemplate().queryForList("getSameCrmLivingConditionByName",livingCondition);
	}

	@Override
	public LivingCondition getMaxSortNoCrmLivingCondition() {
		return (LivingCondition)this.getSqlMapClientTemplate().queryForObject("getMaxSortNoCrmLivingCondition");
	}

	@Override
	public LivingCondition getMinSortNoCrmLivingCondition() {
		return (LivingCondition)this.getSqlMapClientTemplate().queryForObject("getMinSortNoCrmLivingCondition");
	}

	@Override
	public LivingCondition getNeedMoveCrmLivingCondition(Map<String, Object> parameters) {
		return (LivingCondition)this.getSqlMapClientTemplate().queryForObject("getNeedMoveCrmLivingCondition",parameters);		
	}

}
