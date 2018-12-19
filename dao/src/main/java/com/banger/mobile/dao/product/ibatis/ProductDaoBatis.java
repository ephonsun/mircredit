/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :产品管理dao
 * Author     :hk
 * Create Date:Jul 16, 2012
 */
package com.banger.mobile.dao.product.ibatis;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.product.ProductDao;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.pad.CustomerBuyProduct;
import com.banger.mobile.domain.model.pad.Product;
import com.banger.mobile.domain.model.pad.ProductBuyCustomer;
import com.banger.mobile.domain.model.product.BuyCustomer;
import com.banger.mobile.domain.model.product.BuyCustomerBean;
import com.banger.mobile.domain.model.product.ProductBean;
import com.banger.mobile.domain.model.product.ProductDetail;
import com.banger.mobile.domain.model.product.ProductObj;
import com.banger.mobile.domain.model.product.ReportProductBean;
import com.banger.mobile.domain.model.product.ReportProductCountBean;
import com.banger.mobile.domain.model.product.UserAndCountersBean;
import com.banger.mobile.domain.model.user.UserInfo;
import com.banger.mobile.ibatis.GenericDaoiBatis;
import com.ibm.db2.jcc.am.r;

/**
 * @author hk
 * @version $Id: ProductDaoBatis.java,v 0.1 Jul 16, 2012 11:28:47 AM hk Exp $
 */
public class ProductDaoBatis extends GenericDaoiBatis implements ProductDao{

    public ProductDaoBatis() {
        super(ProductBean.class);
    }


    /**
     * 查询所有产品明细信息
     * @return PageUtil<ProductDetail>
     */
    public PageUtil<ProductDetail> getAllProductDetailsPage(Map<String,Object> map, Page page){
        ArrayList<ProductDetail> list=(ArrayList<ProductDetail>) this.findQueryPage("getProductParameterPageMap", "getProductCount",map, page);
        return new PageUtil<ProductDetail>(list, page);
    }

    /**
     * 查询未处理产品明细信息
     * @return PageUtil<ProductDetail>
     */
    public PageUtil<ProductDetail> getProductIsDeal(Map<String,Object> map, Page page){
        ArrayList<ProductDetail> list=(ArrayList<ProductDetail>) this.findQueryPage("getProductIsDeal", "getProductIsDealCount",map, page);
        return new PageUtil<ProductDetail>(list, page);
    }

    /**
     * 根据客户编号查找客户
     *
     */
    public List<CrmCustomer> findCustomerByNo(String customerNo) {
        return this.getSqlMapClientTemplate().queryForList("findCustomerByNo", customerNo);
    }

    /**
     * 根据id,编号等查询产品记录
     *
     */
    public List<ProductBean> getProductByMap(Map<String,Object> map){
        return this.getSqlMapClientTemplate().queryForList("getProductByMap", map);
    }

    /**
     * 根据营销人员条件查询所有营销人员
     * @param String account
     */
    public List<UserInfo> findUserByAccount(String account){
        return this.getSqlMapClientTemplate().queryForList("findUserByAccount",account);
    }

    /**
     * 新建理财产品
     * @param object productBean
     */
    public void saveProduct(ProductBean productBean){
        this.getSqlMapClientTemplate().insert("saveProduct", productBean);
    }

    /**
     * 修改理财产品
     * @param object ProductBean
     */
    public void updateProduct(ProductBean productBean){
        this.getSqlMapClientTemplate().update("updateProduct", productBean);
    }

    /**
     * 删除购买客户产品的信息(伪)
     * @param int pcId
     */
    public void deleteBuyCustomer(int pcId){
        this.getSqlMapClientTemplate().update("deleteBuyCustomer",pcId);
    }

    /**
     * 根据id查看产品详情
     * @param int productId
     */
    public ProductObj getProductById(int productId){
        return (ProductObj) this.getSqlMapClientTemplate().queryForObject("getProductById",productId);
    }

    /**
     * 根据产品id查询客户购买记录（分页）
     * @param int productId
     * @return PageUtil<BugCustomer>
     */
    public PageUtil<BuyCustomer> getAllBuyCustomerPage(Map<String, Object> map,Page page){
        ArrayList<BuyCustomer> list=(ArrayList<BuyCustomer>) this.findQueryPage("getBuyCustomerByPid", "getBuyCustomerCount",map, page);
        return new PageUtil<BuyCustomer>(list, page);
    }

    /**
     * 处理购买产品的客户信息
     * @param int pcId
     */
    public void updateDealCustomer(int pcId){
        this.getSqlMapClientTemplate().update("DealBuyCustomer",pcId);
    }

    /**
     * 添加购买产品的客户
     * @param object BuyCustomerBean
     */
    public void saveBuyProductCustomer(BuyCustomerBean buyCustomerBean){
        this.getSqlMapClientTemplate().insert("saveBuyProductCustomer",buyCustomerBean);
    }

    /**
     * 根据产品姓名查询产品
     */
    public List<ProductObj> getProductByName(Map<String, Object> map){
        return this.getSqlMapClientTemplate().queryForList("getProductByName",map);
    }

    /**
     * 根据产品购买信息查找是否存在(包含删除)
     * @return
     */
    public Boolean queryBuyCustomerByAll(BuyCustomerBean buyCustomerBean){
        if(buyCustomerBean.getCustomerId()==null)   return false;
        if((Integer)getSqlMapClientTemplate().queryForObject("queryBuyCustomerByAll",buyCustomerBean)>0)return true;
        else return false;
    }

    /**
     * 根据部门id查询用户表和柜台人员表集合并排序
     */
    public List<UserAndCountersBean> getUserAndCounterByDeptId(int deptId){
        return this.getSqlMapClientTemplate().queryForList("getUserAndCounterByDeptId",deptId);
    }

    /**
     * 产品销售明细报表
     */
    public List<ReportProductBean> getReportProduct(Map<String, Object> map){
        return this.getSqlMapClientTemplate().queryForList("getReportProduct",map);
    }

    /**
     * 产品销售统计报表 跟人
     */
    public List<ReportProductCountBean> getReportProductCountByPeople(Map<String, Object> map){
        return this.getSqlMapClientTemplate().queryForList("getReportProductCountByPeople",map);
    }

    /**
     * 产品销售统计报表 跟机构
     */
    public List<ReportProductCountBean> getReportProductCountByDept(Map<String, Object> map){
        return this.getSqlMapClientTemplate().queryForList("getReportProductCountByDept",map);
    }

    /**
     * 根据产品名称模糊搜索包含的产品名称
     */
    public List<ProductBean> findProductByNo(String productName){
        return this.getSqlMapClientTemplate().queryForList("findProductByNo", productName);
    }

    /**
     * 根据产品id查询是否有客户购买记录
     * @param int productId
     * @return int
     */
    public Integer getBuyCustomerByPid(Map<String, Object> map){
        return (Integer)this.getSqlMapClientTemplate().queryForObject("getBuyCustomerCount",map);
    }


    /**
     * 根据客户id查询所有买的所有产品（分页）
     */

    public PageUtil<ProductObj> getCustomerBuyProduct(Map<String,Object> map, Page page){
        ArrayList<ProductObj> list=(ArrayList<ProductObj>) this.findQueryPage("getCustomerBuyProduct", "getCustomerBuyProductCount",map, page);
        return new PageUtil<ProductObj>(list, page);
    }

    /**
     * pad 根据产品ID查询客户相关信息
     */
    public List<ProductBuyCustomer> findProductCustomerForPad(Map<String, Object> map){
        return this.getSqlMapClientTemplate().queryForList("findProductCustomerForPad",map);
    }

    /**
     * pad 根据产品ID查询客户总数
     */
    public int findProductCustomerCountForPad(Map<String, Object> map){
        return (Integer)getSqlMapClientTemplate().queryForObject("findProductCustomerCountForPad",map);
    }

    /**
     * pad 根据产品名称编号收益率查询产品
     */
    public List<Product> findProductForPad(String input, String profitRate) {
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("product", input);
        map.put("profitRate", profitRate);
        return this.getSqlMapClientTemplate().queryForList("getProductForPad",map);
    }

    /**
     * pad 查询所有产品信息
     */
    public List<Product> getAllProductForPad() {
        Map<String, Object> map=new HashMap<String, Object>();
        return this.getSqlMapClientTemplate().queryForList("getProductForPad",map);
    }

    /**
     * pad 根据客户id查询购买的理财产品
     */
    public List<CustomerBuyProduct> getCustomerBuyProductForPad(Integer customerId,int startRows, int endRows) {
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("customerId", customerId);
        map.put("startRows", startRows);
        map.put("endRows", endRows);
        return this.getSqlMapClientTemplate().queryForList("getCustomerBuyProductForPad",map);
    }

    /**
     * pad 根据客户id查询购买的总数
     */
    public int getCustomerBuyProductCountForPad(Integer customerId) {
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("customerId", customerId);
        return (Integer)getSqlMapClientTemplate().queryForObject("getCustomerBuyProductCountForPad",map);
    }

    /**
     * pad 根据产品id查询详情
     */
    public ProductObj getProductByIdForPad(int productId){
        return (ProductObj) this.getSqlMapClientTemplate().queryForObject("getProductById",productId);
    }

    /**
     * 根据产品ID查询此用户的购买记录的总额,此记录的购买时间要在对应的营销任务的时间范围内
     * @param map
     * @return
     */
    public Double querySumBuyCustomerByPid(Map<String, Object> map){
        return (Double)this.getSqlMapClientTemplate().queryForObject("querySumBuyCustomerByPid",map);
    }

    /**
     * 根据募集单位查询是否被占用 
     */
    public int getProductCountByUnitId(Map<String, Object> map){
        return (Integer)this.getSqlMapClientTemplate().queryForObject("getProductCountByUnitId",map);
    }


    /**
     * 批量添加购买产品的客户
     * @param beanList
     * @see com.banger.mobile.dao.product.ProductDao#saveBuyProductCustomerBatch(java.util.List)
     */
    public void saveBuyProductCustomerBatch(List<BuyCustomerBean> beanList) {
        this.exectuteBatchInsert("saveBuyProductCustomer", beanList);
    }
}
