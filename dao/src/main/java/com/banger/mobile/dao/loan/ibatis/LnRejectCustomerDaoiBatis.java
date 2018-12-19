package com.banger.mobile.dao.loan.ibatis;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.loan.LnRejectCustomerDao;
import com.banger.mobile.domain.model.loan.LnRejectCustomer;
import com.banger.mobile.ibatis.GenericDaoiBatis;
import com.banger.mobile.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BH-TCL on 14-12-3.
 */
public class LnRejectCustomerDaoiBatis extends GenericDaoiBatis implements LnRejectCustomerDao {
    public LnRejectCustomerDaoiBatis(){
        super(LnRejectCustomer.class);
    }

    public LnRejectCustomerDaoiBatis(Class persistentClass){
        super(persistentClass);
    }

    @Override
    public void addLnRejectCustomer(LnRejectCustomer lnRejectCustomer) {
        this.getSqlMapClientTemplate().insert("insertLnRejectCustomer",lnRejectCustomer);
    }

    @Override
    public void updateLnRejectCustomer(LnRejectCustomer lnRejectCustomer) {
        this.getSqlMapClientTemplate().update("updateLnRejectCustomer",lnRejectCustomer);
    }

    @Override
    public PageUtil<LnRejectCustomer> queryLnRejectCustomerPage(Map<String, Object> conds, Page curPage) {
        Map<String, Object> fConds = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : conds.entrySet()) {
            if (entry.getValue() instanceof String) {
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil
                        .ReplaceSQLChar(entry.getValue().toString())));
            } else {
                fConds.put(entry.getKey(), entry.getValue());
            }
        }
        List<LnRejectCustomer> list = (List<LnRejectCustomer>) this.findQueryPage("queryLnRejectCustomerPage", "queryLnRejectCustomerCount",
                fConds, curPage);
        return new PageUtil<LnRejectCustomer>(list, curPage);
    }

    /**
     * 根据ID集合获取记录集
     * @param map
     * @return
     */
    @Override
    public List<LnRejectCustomer> queryRejectCustomerByIds(Map<String, Object> map) {
        return this.getSqlMapClientTemplate().queryForList("queryRejectCustomerByIds",map);
    }
}
