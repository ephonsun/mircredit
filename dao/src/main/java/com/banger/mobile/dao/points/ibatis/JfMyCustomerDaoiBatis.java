package com.banger.mobile.dao.points.ibatis;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.points.JfMyCustomerDao;
import com.banger.mobile.domain.model.customer.CrmCustomerRelatives;
import com.banger.mobile.domain.model.points.JfMyCustomer;
import com.banger.mobile.ibatis.GenericDaoiBatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-8-21
 * Time: 下午4:59
 * To change this template use File | Settings | File Templates.
 */
public class JfMyCustomerDaoiBatis extends GenericDaoiBatis implements JfMyCustomerDao {
    public JfMyCustomerDaoiBatis() {
        super(JfMyCustomer.class);
    }
    /**
     * @param persistentClass
     */
    public JfMyCustomerDaoiBatis(Class persistentClass) {
        super(JfMyCustomer.class);
    }

    public PageUtil<JfMyCustomer> searchMyCustomer(Map<String,Object> map,Page page) {
        List<JfMyCustomer> list = (List<JfMyCustomer>)this.findQueryPage("searchMyCustomer", "searchMyCustomerCount", map, page);
        if (list == null) {
            list = new ArrayList<JfMyCustomer>();
        }
        return new PageUtil<JfMyCustomer>(list, page);
    }

    public void  removeMyCustomer(Map<String,Object> map) {
        this.getSqlMapClientTemplate().delete("removeMyCustomer",map);
    }

    public void  addMyCustomer(JfMyCustomer cust) {
        this.getSqlMapClientTemplate().insert("addMyCustomer",cust);
    }

    public Integer getMyCustomerByCustomerCode(Map<String,Object> map) {
        return (Integer)this.getSqlMapClientTemplate().queryForObject("getMyCustomerByCustomerCode",map);
    }
}
