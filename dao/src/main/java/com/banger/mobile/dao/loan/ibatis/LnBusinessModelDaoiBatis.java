package com.banger.mobile.dao.loan.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.loan.LnBusinessModelDao;
import com.banger.mobile.domain.model.loan.LnBusinessModel;
import com.banger.mobile.ibatis.GenericDaoiBatis;

public class LnBusinessModelDaoiBatis extends GenericDaoiBatis implements LnBusinessModelDao{

	public LnBusinessModelDaoiBatis(Class persistentClass) {
		super(LnBusinessModel.class);
		
	}

	
	public LnBusinessModelDaoiBatis() {
        super(LnBusinessModel.class);
    }


	@Override
	public LnBusinessModel insertLnBusinessModel(LnBusinessModel lnBusinessModel) {
		Integer	lnTer=(Integer) this.getSqlMapClientTemplate().insert("insertLnBusinessModel",lnBusinessModel);
		LnBusinessModel ln = new LnBusinessModel();
		ln.setBusinessId(lnTer);
		return ln;
		
	}


	@Override
	public LnBusinessModel selectByPrimary(LnBusinessModel lnBusinessModel) {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("loanId", lnBusinessModel.getLoanId());
		param.put("businessId", lnBusinessModel.getBusinessId());
		LnBusinessModel list =(LnBusinessModel) this.getSqlMapClientTemplate().queryForObject("selectByPrimary",param);
		return list;
	}


	@Override
	public int updateLnBusinessModel(LnBusinessModel ln) {		
		return this.getSqlMapClientTemplate().update("updateLnBusinessModel", ln);
	}
    
	 
	
}
