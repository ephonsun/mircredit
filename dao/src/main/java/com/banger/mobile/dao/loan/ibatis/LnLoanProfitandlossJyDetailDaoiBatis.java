package com.banger.mobile.dao.loan.ibatis;

import com.banger.mobile.dao.loan.AnaApplyInfoDao;
import com.banger.mobile.dao.loan.LnLoanProfitandlossJyDetailDao;
import com.banger.mobile.domain.model.loan.AnaApplyInfo;
import com.banger.mobile.domain.model.loan.AnaChildren;
import com.banger.mobile.domain.model.loan.ApplyAddressInfo;
import com.banger.mobile.domain.model.loan.LnLoanBalanceVehicle;
import com.banger.mobile.domain.model.loan.LnLoanProfitandlossJyDetail;
import com.banger.mobile.ibatis.GenericDaoiBatis;

import java.util.List;
import java.util.Map;

public class LnLoanProfitandlossJyDetailDaoiBatis extends GenericDaoiBatis implements LnLoanProfitandlossJyDetailDao{
   
    public LnLoanProfitandlossJyDetailDaoiBatis(Class persistentClass) {
        super(LnLoanProfitandlossJyDetail.class);
    }

    public LnLoanProfitandlossJyDetailDaoiBatis(){
        super(LnLoanProfitandlossJyDetail.class);
    }

	@Override
	public void deleteXfDetail(
			LnLoanProfitandlossJyDetail lnLoanProfitandlossJyDetail) {
		this.getSqlMapClientTemplate().delete("deleteJyDetail", lnLoanProfitandlossJyDetail);

		
	}

	@Override
	public List<LnLoanProfitandlossJyDetail> selectJyDetaiByPrimary(
			LnLoanProfitandlossJyDetail lnLoanProfitandlossJyDetail) {
		return this.getSqlMapClientTemplate().queryForList("selectJyDetaiByPrimary", lnLoanProfitandlossJyDetail);

	}

	@Override
	public LnLoanProfitandlossJyDetail insertJyDetai(
			LnLoanProfitandlossJyDetail lnLoanProfitandlossJyDetail) {
		Integer	lnTer=(Integer) this.getSqlMapClientTemplate().insert("insertJyDetai",lnLoanProfitandlossJyDetail);
		LnLoanProfitandlossJyDetail ln = new LnLoanProfitandlossJyDetail();
		ln.setProfitandlossJyDetailId(lnTer);
		return ln;
	}

	@Override
	public LnLoanProfitandlossJyDetail updateXfJyDetai(
			LnLoanProfitandlossJyDetail lnLoanProfitandlossJyDetail) {
		Integer	lnTer=(Integer) this.getSqlMapClientTemplate().update("updateXfJyDetai",lnLoanProfitandlossJyDetail);
		return lnLoanProfitandlossJyDetail;
	}

	@Override
	public void deleteXfDetailByProfitandlossID(Integer loanProfitandlossId) {
		this.getSqlMapClientTemplate().delete("deleteJyDetailByProfitandlossID", loanProfitandlossId);
		
	}

   
}
