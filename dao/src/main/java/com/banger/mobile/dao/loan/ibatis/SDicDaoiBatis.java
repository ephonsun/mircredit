package com.banger.mobile.dao.loan.ibatis;


import com.banger.mobile.dao.loan.SDicDao;
import com.banger.mobile.domain.model.loan.BaseSDic;
import com.banger.mobile.ibatis.GenericDaoiBatis;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Zhangfp
 * Date: 13-2-6
 * Time: 上午9:11
 * To change this template use File | Settings | File Templates.
 *
 * 客户字典表
 */
public class SDicDaoiBatis extends GenericDaoiBatis implements SDicDao {

    public SDicDaoiBatis(){
        super(BaseSDic.class);
    }
    /**
     * Constructor that takes in a class to see which type of entity to persist
     *
     * @param persistentClass the class type you'd like to persist
     */
    public SDicDaoiBatis(Class persistentClass) {
        super(persistentClass);
    }

    /**
     * 查询贷款信息字典
     * @param param
     * @return
     */
    public List<BaseSDic> queryDicByType(Map<String ,Object> param) {
        return this.getSqlMapClientTemplate().queryForList("queryDicByType",param);
    }
}
