/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :Administrator
 * Create Date:Jun 1, 2012
 */
package com.banger.mobile.dao.phoneConfig.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.phoneConfig.PhoneConfigDao;
import com.banger.mobile.domain.model.phoneConfig.PhoneConfig;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author Administrator
 * @version $Id: PhoneConfigDaoiBatis.java,v 0.1 Jun 1, 2012 10:01:35 AM Administrator Exp $
 */
public class PhoneConfigDaoiBatis extends GenericDaoiBatis implements PhoneConfigDao  {
	
	private Map<Integer,PhoneConfig> caches;

    public PhoneConfigDaoiBatis(Class persistentClass) {
        super(PhoneConfig.class);
    }
    public PhoneConfigDaoiBatis(){
        super(PhoneConfig.class);
    }

    /**
     * 查询参数
     * @return
     * @see com.banger.mobile.dao.phoneConfig.PhoneConfigDao#query()
     */
    @SuppressWarnings("unchecked")
	public PhoneConfig query(int userId) {
        if(this.caches==null){
        	this.caches = new HashMap<Integer,PhoneConfig>();
        	List<PhoneConfig> configs = (List<PhoneConfig>)this.getSqlMapClientTemplate().queryForList("queryPhoneConfigAll");
        	if(configs!=null && configs.size()>0)
        	{
        		for(PhoneConfig pc : configs){
        			this.caches.put(pc.getUserId(),pc);
        		}
        	}
        }
        if(!this.caches.containsKey(Integer.valueOf(userId))){
        	PhoneConfig pc = (PhoneConfig)this.getSqlMapClientTemplate().queryForObject("queryPhoneConfig",userId);
        	if(pc!=null)this.caches.put(Integer.valueOf(userId),pc);
        	else return null;
        }
        return this.caches.get(Integer.valueOf(userId));
    }
    
    private void clearCache(){
    	this.caches=null;
    }

    /**
     * 更新参数
     * 
     * @see com.banger.mobile.dao.phoneConfig.PhoneConfigDao#update()
     */
    public void update(PhoneConfig phoneConfig) {
        this.getSqlMapClientTemplate().update("updatePhoneConfig", phoneConfig);
        this.clearCache();
    }
    /**
     * 新增个人基础配置
     * @param userId
     * @see com.banger.mobile.dao.phoneConfig.PhoneConfigDao#addPhoneConfig(int)
     */
    public void addPhoneConfig(PhoneConfig phoneConfig) {
        this.getSqlMapClientTemplate().insert("addPhoneConfig", phoneConfig);
        this.clearCache();
    }
    /**
     * 是否弹出通话窗口
     */
    public void updatePhoneConfigIsPopUp(PhoneConfig phoneConfig) {
        this.getSqlMapClientTemplate().update("updatePhoneConfigIsPopUp", phoneConfig);
        this.clearCache();
    }
    /**
     * 是否屏幕取词
     */
    public void updatePhoneConfigIsScreamWord(PhoneConfig phoneConfig) {
        this.getSqlMapClientTemplate().update("updatePhoneConfigIsScreamWord", phoneConfig);
        this.clearCache();
    }

}
