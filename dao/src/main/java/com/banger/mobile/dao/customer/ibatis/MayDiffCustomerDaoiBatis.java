/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :合并客户
 * Author     :zhangxiang
 * Version    :1.0
 * Create Date:May 2, 2012
 */
package com.banger.mobile.dao.customer.ibatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.customer.MayDiffCustomerDao;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.customer.MayDiffCustomerName;
import com.banger.mobile.ibatis.GenericDaoiBatis;

public class MayDiffCustomerDaoiBatis extends GenericDaoiBatis implements MayDiffCustomerDao {

    public MayDiffCustomerDaoiBatis() {
        super(MayDiffCustomerName.class);
    }

    public MayDiffCustomerDaoiBatis(Class persistentClass) {
        super(MayDiffCustomerName.class);
    }

    /**
     * 分页查询
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<MayDiffCustomerName> getMayDiffCustomerNamePage(Map<String, Object> parameters, Page page) {
        List<MayDiffCustomerName> list = (List<MayDiffCustomerName>) this.findQueryPage(
                "getMayDiffCustomerNameParameterPageMap", "getMayDiffCustomerNameCount", parameters, page);
        if (list == null) {
            list = new ArrayList<MayDiffCustomerName>();
        }
        return new PageUtil<MayDiffCustomerName>(list, page);
    }

    /**
     * 取得相同客户
     * @param parameters
     * @return
     */
    public List<CrmCustomer> getSameCustomerList(Map<String, Object> parameters) {
        return this.getSqlMapClientTemplate().queryForList("getSameCustomerList", parameters);
    }

    /**
     * 合并客户联系记录
     * @param p1
     */
    public void mergeRecordInfo(Map<String, Object> p1) {
        this.getSqlMapClientTemplate().update("mergeRecordInfo", p1);
    }

    /**
     * 伪删除客户
     * @param customerId
     */
    public void deleteCrmCustomer(Integer customerId) {
        this.getSqlMapClientTemplate().update("deleteCrmCustomer", customerId);
    }

    /**
     * 合并任务
     * @param p2
     */
    public void mergeTask(Map<String, Object> p2) {
        this.getSqlMapClientTemplate().update("mergeTask", p2);
    }

    /**
     * 合并计划
     * @param p2
     */
    public void mergePlan(Map<String, Object> p2){
        this.getSqlMapClientTemplate().update("mergePLAN", p2);
    }

    /**
     * 合并亲友
     * @param p3
     */
    public void mergeFriends(Map<String, Object> p3) {
        this.getSqlMapClientTemplate().update("mergeFriends", p3);
        this.getSqlMapClientTemplate().update("mergeOthersFriends", p3);
    }

    /**
     * 合并短信
     * @param p4
     */
    public void mergeSMS(Map<String, Object> p4) {
        this.getSqlMapClientTemplate().update("mergeSMS", p4);
    }

    /**
     * 合并彩信
     * @param p5
     */
    public void mergeMMS(Map<String, Object> p5) {
        this.getSqlMapClientTemplate().update("mergeMMS", p5);
    }

    /**
     * 合并产品购买记录
     * @param p6
     */
    public void mergeProductCustomer(Map<String, Object> p6) {
        this.getSqlMapClientTemplate().update("mergeProductCustomer", p6);
    }

    /**
     * 手机相同    分页查询 
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<MayDiffCustomerName> getMayDiffCustomerPhonePage(Map<String, Object> parameters, Page page) {
        List<MayDiffCustomerName> list = (List<MayDiffCustomerName>) this.findQueryPage(
                "getMayDiffCustomerPhoneParameterPageMap", "getMayDiffCustomerPhoneCount", parameters, page);
        if (list == null) {
            list = new ArrayList<MayDiffCustomerName>();
        }
        return new PageUtil<MayDiffCustomerName>(list, page);
    }

    /**
     * 姓名固话相同    分页查询 
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<MayDiffCustomerName> getMayDiffCustomerNamePhonePage(Map<String, Object> parameters, Page page) {
        List<MayDiffCustomerName> list = (List<MayDiffCustomerName>) this.findQueryPage(
                "getMayDiffCustomerNamePhoneParameterPageMap", "getMayDiffCustomerNamePhoneCount", parameters, page);
        if (list == null) {
            list = new ArrayList<MayDiffCustomerName>();
        }
        return new PageUtil<MayDiffCustomerName>(list, page);
    }

    /**
     * 姓名单位相同    分页查询 
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<MayDiffCustomerName> getMayDiffCustomerNameCompanyPage(Map<String, Object> parameters, Page page) {
        List<MayDiffCustomerName> list = (List<MayDiffCustomerName>) this.findQueryPage(
                "getMayDiffCustomerNameCompanyParameterPageMap", "getMayDiffCustomerNameCompanyCount", parameters, page);
        if (list == null) {
            list = new ArrayList<MayDiffCustomerName>();
        }
        return new PageUtil<MayDiffCustomerName>(list, page);
    }

    /**
     * 身份证相同    分页查询 
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<MayDiffCustomerName> getMayDiffCustomerIdcardPage(Map<String, Object> parameters, Page page) {
        List<MayDiffCustomerName> list = (List<MayDiffCustomerName>) this.findQueryPage(
                "getMayDiffCustomerIdCardParameterPageMap", "getMayDiffCustomerIdCardCount", parameters, page);
        if (list == null) {
            list = new ArrayList<MayDiffCustomerName>();
        }
        return new PageUtil<MayDiffCustomerName>(list, page);
    }

    /**
     * 联系记录数
     * @param customerId
     * @return
     */
    public int countRecordInfo(Integer customerId) {
        return (Integer) this.getSqlMapClientTemplate().queryForObject("countRecordInfo", customerId);
    }

    /**
     * 任务数量
     * @param customerId
     * @return
     */
    public int countTask(Integer customerId) {
        return (Integer) this.getSqlMapClientTemplate().queryForObject("countTask", customerId);
    }

    /**
     * 亲友数量
     * @param customerId
     * @return
     */
    public int countRelative(Integer customerId) {
        return (Integer) this.getSqlMapClientTemplate().queryForObject("countRelative", customerId);
    }

    /**
     * 产品数量
     * @param customerId
     * @return
     */
    public int countProduct(Integer customerId) {
        return (Integer) this.getSqlMapClientTemplate().queryForObject("countProduct", customerId);
    }

    /**
     * 相同姓名固话数量
     * @param parameterMap
     * @return
     */
    public int getMayDiffCustomerNamePhoneNumber(Map<String, Object> parameterMap) {
        return (Integer) this.getSqlMapClientTemplate().queryForObject("getMayDiffCustomerNamePhoneCount", parameterMap);
    }

    /**
     * 相同姓名公司数量
     * @param parameterMap
     * @return
     */
    public int getMayDiffCustomerNameCompanyNumber(Map<String, Object> parameterMap) {
        return (Integer) this.getSqlMapClientTemplate().queryForObject("getMayDiffCustomerNameCompanyCount", parameterMap);
    }

    /**
     * 相同身份证数量
     * @param parameterMap
     * @return
     */
    public int getMayDiffCustomerIdCardNumber(Map<String, Object> parameterMap) {
        return (Integer) this.getSqlMapClientTemplate().queryForObject("getMayDiffCustomerIdCardCount", parameterMap);
    }

    /**
     * 相同姓名数量
     * @param parameterMap
     * @return
     */
    public int getMayDiffCustomerPhoneNumber(Map<String, Object> parameterMap) {
        return (Integer) this.getSqlMapClientTemplate().queryForObject("getMayDiffCustomerPhoneCount", parameterMap);
    }

    /**
     * 相同姓名数量
     * @param parameterMap
     * @return
     */
    public int getMayDiffCustomerNameNumber(Map<String, Object> parameterMap) {
        return (Integer) this.getSqlMapClientTemplate().queryForObject("getMayDiffCustomerNameCount", parameterMap);
    }

    /**
     * 删除客户共享信息
     */
    public void deleteCustomerSharedInfo(Map<String, Object> parameterMap) {
        this.getSqlMapClientTemplate().update("deleteCustomerSharedInfo", parameterMap);
    }

}
