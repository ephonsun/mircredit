package com.banger.mobile.dao.inform.ibatis;

import com.banger.mobile.dao.inform.InformDao;
import com.banger.mobile.domain.model.inform.Inform;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * Created by BH-TCL on 15-7-13.
 */
public class InformDaoIBatis extends GenericDaoiBatis implements InformDao {

    public InformDaoIBatis() {
        super(Inform.class);
    }

    public InformDaoIBatis(Class persistentClass) {
        super(persistentClass);
    }
    @Override
    public void updateInform(Inform inform) {
        this.getSqlMapClientTemplate().update("updateInform",inform);
    }

    @Override
    public Inform getInform(Integer id) {
        return (Inform)this.getSqlMapClientTemplate().queryForObject("queryInform",id);
    }
}
