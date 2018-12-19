package com.banger.mobile.dao.loan.ibatis;

import com.banger.mobile.dao.loan.AnaCoBorrowerAndGuarantorDao;
import com.banger.mobile.dao.loan.LnLoanProfitandlossXfDetailDao;
import com.banger.mobile.domain.model.base.loan.BaseAnaCoBorrower;
import com.banger.mobile.domain.model.base.loan.BaseAnaGuarantor;
import com.banger.mobile.domain.model.loan.LnLoanProfitandlossJyDetail;
import com.banger.mobile.domain.model.loan.LnLoanProfitandlossXfDetail;
import com.banger.mobile.ibatis.GenericDaoiBatis;

import java.util.List;
import java.util.Map;

public class LnLoanProfitandlossXfDetailDaoiBatis extends GenericDaoiBatis implements LnLoanProfitandlossXfDetailDao {
  
    public LnLoanProfitandlossXfDetailDaoiBatis(Class persistentClass) {
        super(LnLoanProfitandlossXfDetail.class);
    }

    public LnLoanProfitandlossXfDetailDaoiBatis(){
        super(LnLoanProfitandlossXfDetail.class);
    }

	@Override
	public List<LnLoanProfitandlossXfDetail> selectXfDetailByPrimary(
			LnLoanProfitandlossXfDetail lnLoanProfitandlossXfDetail) {
		
		return this.getSqlMapClientTemplate().queryForList("selectXfDetailByPrimary", lnLoanProfitandlossXfDetail);
	}

	@Override
	public LnLoanProfitandlossXfDetail updateXfDetail(
			LnLoanProfitandlossXfDetail lnLoanProfitandlossXfDetail) {
		Integer	lnTer=(Integer) this.getSqlMapClientTemplate().insert("updateXfDetail",lnLoanProfitandlossXfDetail);
		return lnLoanProfitandlossXfDetail;
		
	}

	@Override
	public LnLoanProfitandlossXfDetail insertXfDetail(
			LnLoanProfitandlossXfDetail lnLoanProfitandlossXfDetail) {
		Integer	lnTer=(Integer) this.getSqlMapClientTemplate().insert("insertXfDetail",lnLoanProfitandlossXfDetail);
		LnLoanProfitandlossXfDetail ln = new LnLoanProfitandlossXfDetail();
		ln.setProfitandlossJyDetailId(lnTer);
		return ln;
	}

	@Override
	public void deleteXfDetail(
			LnLoanProfitandlossXfDetail lnLoanProfitandlossXfDetail) {
		this.getSqlMapClientTemplate().delete("deleteXfDetail", lnLoanProfitandlossXfDetail);
		
	}

   
}
