package com.banger.mobile.dao.loan.ibatis;

import com.banger.mobile.dao.loan.LnProfitLossItemDAO;
import com.banger.mobile.domain.model.loan.LnProfitLossItem;
import com.banger.mobile.ibatis.GenericDaoiBatis;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ygb on 2017/8/3.
 */
public class LnProfitLossItemDAOImpl  extends GenericDaoiBatis implements LnProfitLossItemDAO {

    /**
     * Constructor that takes in a class to see which type of entity to persist
     *
     * @param persistentClass the class type you'd like to persist
     */

    public LnProfitLossItemDAOImpl(Class persistentClass) {
        super(LnProfitLossItem.class);
    }
    public LnProfitLossItemDAOImpl() {
        super(LnProfitLossItem.class);
    }

    @Override
    public List<LnProfitLossItem> selectItemListByType(Integer loanId, String type) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("loanId",loanId);
        map.put("type",type);
        return this.getSqlMapClientTemplate().queryForList("selectItemListByType",map);
    }

    @Override
    public LnProfitLossItem selectOneItemById(Integer itemId) {
        return (LnProfitLossItem)this.getSqlMapClientTemplate().queryForObject("selectOneItemById",itemId);
    }

    @Override
    public void deleteLnProfitLossItem(Integer itemId) {
        this.getSqlMapClientTemplate().delete("deleteLnProfitLossItem",itemId);
    }

    @Override
    public void deleteLnProfitLossItems(Integer loanId) {
        this.getSqlMapClientTemplate().delete("deleteLnProfitLossItems",loanId);
    }

    @Override
    public void insertLnProfitLossItem(LnProfitLossItem lnProfitLossItem) {
        this.getSqlMapClientTemplate().insert("insertLnProfitLossItem",lnProfitLossItem);
    }

    @Override
    public void updateLnProfitLossItem(LnProfitLossItem lnProfitLossItem) {
        this.getSqlMapClientTemplate().update("updateLnProfitLossItem",lnProfitLossItem);
    }

    @Override
    public BigDecimal sumByType(Integer loanId, String type) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("loanId", loanId);
        param.put("type", type);
        return (BigDecimal) this.getSqlMapClientTemplate().queryForObject("sumProfitLossItemByType", param);
    }
}
