package com.banger.mobile.dao.loan.ibatis;

import java.util.List;

import com.banger.mobile.dao.loan.LnLoanProfitandlossDao;
import com.banger.mobile.domain.model.loan.LnBusinessModel;
import com.banger.mobile.domain.model.loan.LnLoanProfitandloss;
import com.banger.mobile.ibatis.GenericDaoiBatis;

public class LnLoanProfitandlossDaoiBatis extends GenericDaoiBatis implements LnLoanProfitandlossDao {
     public LnLoanProfitandlossDaoiBatis(Class persistentClass) {
    	 super(LnLoanProfitandloss.class);
    }

    public LnLoanProfitandlossDaoiBatis(){
        super(LnLoanProfitandloss.class);
    }

	
	public LnLoanProfitandloss insertProfitandloss(
			LnLoanProfitandloss lnLoanProfitandloss) {
		Integer	lnTer=(Integer) this.getSqlMapClientTemplate().insert("insertProfitandloss",lnLoanProfitandloss);
		LnLoanProfitandloss ln = new LnLoanProfitandloss();
		ln.setLoanProfitandlossId(lnTer);
		return ln;
	}

	
	public List<LnLoanProfitandloss> selectProfitandlossByPrimary(
			LnLoanProfitandloss lnLoanProfitandloss) {
		
		
		return this.getSqlMapClientTemplate().queryForList("selectProfitandlossByPrimary", lnLoanProfitandloss);
	}

	
	public LnLoanProfitandloss updateProfitandloss(LnLoanProfitandloss in) {
		this.getSqlMapClientTemplate().update("updateProfitandloss", in);
		return in;
	}

	public LnLoanProfitandloss updateProfitandlossBaseInfo(LnLoanProfitandloss in) {
		this.getSqlMapClientTemplate().update("updateProfitandlossBaseInfo", in);
		
		LnLoanProfitandloss ln = new LnLoanProfitandloss();
		ln.setLoanProfitandlossId(in.getLoanProfitandlossId());
		return ln;
	}

    
}
