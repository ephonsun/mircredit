package com.banger.mobile.dao.loan.ibatis;

import com.banger.mobile.dao.loan.LnCancelReasonDao;
import com.banger.mobile.domain.model.loan.LnCancelReason;
import com.banger.mobile.ibatis.GenericDaoiBatis;

import java.util.HashMap;
import java.util.Map;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: Zhangfp Date: 13-2-6 Time: 上午9:16 To change
 * this template use File | Settings | File Templates.
 * 
 * 贷款撤销原因
 */
public class LnCancelReasonDaoiBatis extends GenericDaoiBatis implements
		LnCancelReasonDao {
	/**
	 * Constructor that takes in a class to see which type of entity to persist
	 * 
	 * @param persistentClass
	 *            the class type you'd like to persist
	 */
	public LnCancelReasonDaoiBatis() {
		super(LnCancelReason.class);
	}

	public LnCancelReasonDaoiBatis(Class persistentClass) {
		super(LnCancelReason.class);
	}

	public List<LnCancelReason> getCancelReasonList() {
		return this.getSqlMapClientTemplate().queryForList(
				"getCancelReasonList");
	}

    /**
     * 根据ID查找拒绝原因信息
     * 
     * @param cancelReasonId
     * @return
     */
    public LnCancelReason selectCancelReasonById(Integer cancelReasonId){
        return (LnCancelReason)this.getSqlMapClientTemplate().queryForObject("selectCancelReasonById",cancelReasonId);
    }


	public void deleteById(Integer lnCanReaId) {
		// TODO Auto-generated method stub
      this.getSqlMapClientTemplate().update("deleteCancelReason",lnCanReaId);
	}

	public void insertCancelReason(LnCancelReason lnCancelReason) {
		// TODO Auto-generated method stub
       this.getSqlMapClientTemplate().insert("insertCancelReason",lnCancelReason);
	}

	public Integer exsitsCancelReason(Integer lnCanReaId,
			LnCancelReason lnCancelReason) {
		// TODO Auto-generated method stub
		return null;
	}

	public LnCancelReason getCancelReasonById(Integer lnCanReaId) {
		// TODO Auto-generated method stub
		return (LnCancelReason) this.getSqlMapClientTemplate().queryForObject(
				"selCancelReasonById",lnCanReaId);
	}

	public void updateCancelReason(LnCancelReason lnCancelReason) {
		// TODO Auto-generated method stub
       this.getSqlMapClientTemplate().update("updateCancelReason", lnCancelReason);
	}


	public LnCancelReason getCancelReasonByName(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return (LnCancelReason) this.getSqlMapClientTemplate().queryForObject("selCancelReasonByName",map);
	}

	public LnCancelReason getCancelReasonBySortNo(Integer sortNo) {
		// TODO Auto-generated method stub
		return (LnCancelReason) this.getSqlMapClientTemplate().queryForObject("selCancelReasonBySortNo",sortNo);
	}
    
}
