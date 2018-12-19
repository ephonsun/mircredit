/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :通话白名单
 * Author     :yujh
 * Create Date:Aug 22, 2012
 */
package com.banger.mobile.dao.sysWhiteList.iBatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.sysWhiteList.SysWhiteListDao;
import com.banger.mobile.domain.model.sysWhiteList.SysWhiteList;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author yujh
 * @version $Id: SysWhiteListDaoiBatis.java,v 0.1 Aug 22, 2012 1:37:48 PM Administrator Exp $
 */
public class SysWhiteListDaoiBatis extends GenericDaoiBatis implements SysWhiteListDao {
    private Map<String,SysWhiteList> caches;

    public SysWhiteListDaoiBatis(Class persistentClass) {
        super(persistentClass);
    }

    public SysWhiteListDaoiBatis() {
        super(SysWhiteListDaoiBatis.class);
    }

    /**
     * 删除
     */
    public void deleteSysWhiteList(Map<String, Object> map) {
        this.getSqlMapClientTemplate().delete("deleteSysWhiteList", map);
    }

    /**
     * id查询
     */
    public SysWhiteList getWhiteListById(int id) {
        return (SysWhiteList) this.getSqlMapClientTemplate().queryForObject("getWhiteListById", id);
    }

    /**
     * 分页
     */
    public PageUtil<SysWhiteList> getWhiteListPage(Map<String, Object> map, Page page) {
        ArrayList<SysWhiteList> list = (ArrayList<SysWhiteList>) this.findQueryPage(
                "getWhiteListPage", "getWhiteListPageCount", map, page);
        if (list == null) {
            list = new ArrayList<SysWhiteList>();
        }
        return new PageUtil<SysWhiteList>(list, page);
    }

    /**
     * 添加
     */
    public void insertSysWhiteList(SysWhiteList whiteList) {
        this.getSqlMapClientTemplate().insert("insertSysWhiteList", whiteList);
    }

    /**
     * 批量更新
     */
    public void updateSysWhiteList(SysWhiteList whiteList) {
        this.getSqlMapClientTemplate().update("updateSysWhiteList", whiteList);
    }

    /**
     * 查询
     */
    public List<SysWhiteList> getWhiteListByObj(Map<String, Object> map) {
        return (List<SysWhiteList>)this.getSqlMapClientTemplate().queryForList("getWhiteListByObj", map);
    }

    /**
     * 导入更新
     */
    public void updateByImport(SysWhiteList sl) {
        this.getSqlMapClientTemplate().update("updateByImport", sl);
    }

    /**
     * 根据电话号码查询
     */
    @SuppressWarnings("unchecked")
    public List<SysWhiteList> queryByPhoneNo(String phoneNo) {
        return this.getSqlMapClientTemplate().queryForList("queryByPhoneNo", phoneNo);
    }

    /**
     * 查询所有通话白名单号码
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map<String,SysWhiteList> getAllPhones(){
        if(this.caches==null){
            this.caches = new HashMap<String,SysWhiteList>();
            List<SysWhiteList> list = this.getSqlMapClientTemplate().queryForList("queryByPhoneNoAll");
            if(this.caches!=null && this.caches.size()>0){
                for(SysWhiteList white : list){
                    this.caches.put(white.getPhoneNo(),white);
                }
            }
        }
        return this.caches;
    }

    /**
     * @param List
     * @see com.banger.mobile.dao.sysWhiteList.SysWhiteListDao#insertSysWhiteListBatch(java.util.List)
     */
    public void insertSysWhiteListBatch(List<SysWhiteList> List) {
        this.exectuteBatchInsert("insertSysWhiteList", List);
    }

    /**
     * @param List
     * @see com.banger.mobile.dao.sysWhiteList.SysWhiteListDao#updateSysWhiteListBatch(java.util.List)
     */
    public void updateSysWhiteListBatch(List<SysWhiteList> List) {
        this.executeBatchUpdate("updateByImport", List);
    }

}
