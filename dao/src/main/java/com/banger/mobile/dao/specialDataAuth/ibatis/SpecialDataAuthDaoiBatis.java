package com.banger.mobile.dao.specialDataAuth.ibatis;

import com.banger.mobile.dao.specialDataAuth.SpecialDataAuthDao;
import com.banger.mobile.domain.model.specialData.SpecialDataAuthBean;
import com.banger.mobile.domain.model.specialData.SpecialDataAuth;
import com.banger.mobile.domain.model.menuAuth.SysMenuAuth;
import com.banger.mobile.ibatis.GenericDaoiBatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yangy
 * Date: 13-6-18
 * Time: 下午3:54
 * To change this template use File | Settings | File Templates.
 */
public class SpecialDataAuthDaoiBatis extends GenericDaoiBatis implements SpecialDataAuthDao {
    /**
     *
     */
    public SpecialDataAuthDaoiBatis() {
        super(SpecialDataAuthBean.class);
    }
    /**
     * @param persistentClass
     */
    public SpecialDataAuthDaoiBatis(Class persistentClass) {
        super(SpecialDataAuthBean.class);
    }
    /**
     * 获得功能权限树集合
     */
    public List<SpecialDataAuthBean> getDataAuthTree(){
        return this.getSqlMapClientTemplate().queryForList("getDataAuthTree");
    }


    /**
     *  新增角色功能权限 -->
     */
    public void insertDataAuth(SpecialDataAuth DataAuth){
        this.getSqlMapClientTemplate().insert("insertSysDataAuth",DataAuth);
    }
    /**
     * 删除角色功能权限  -->
     */
    public void deleteDataAuth(int roleId) {
        this.getSqlMapClientTemplate().delete("deleteSysDataAuth",roleId);
    }


    /**
     *  查看角色对应的功能权限 -->
     */
    public List<SpecialDataAuthBean> getDetailDataByRoleId(int roleId){
        return this.getSqlMapClientTemplate().queryForList("getDetailDataByRoleId",roleId);
    }
    /**
     * 根据roleId查询功能权限
     */
    public List<SpecialDataAuth> getDataAuthByRoleId(int roleId){
        return this.getSqlMapClientTemplate().queryForList("getSysDataAuthByRoleId",roleId);
    }

    public List<SpecialDataAuth> getDataAuthByRoleId(Map<String, Object> map) {
        return this.getSqlMapClientTemplate().queryForList("getSysDataAuthByMap",map);
    }

    public List<SpecialDataAuth> getSysDataAuthCondition(Map<String, Object> map) {
        return this.getSqlMapClientTemplate().queryForList("getSysDataAuthCondition",map);
    }

    /**
     * 根据功能操作删除权限
     * @param DataId
     */
    public void deleteDataAuthByDataId(int DataId){
        this.getSqlMapClientTemplate().delete("deleteDataAuthByDataId",DataId);
    }
    /**
     * 根据角色id集合查询功能id 
     */
    public List<Integer> getDataIdListByRoleId(String roleIds){
        return this.getSqlMapClientTemplate().queryForList("getSysDataIdListByRoleId",roleIds);
    }
}
