/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :产品管理...
 * Author     :hk
 * Create Date:Jul 16, 2012
 */
package com.banger.mobile.facade.product;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.pad.CustomerBuyProduct;
import com.banger.mobile.domain.model.pad.Product;
import com.banger.mobile.domain.model.pad.ProductBuyCustomer;
import com.banger.mobile.domain.model.product.BuyCustomer;
import com.banger.mobile.domain.model.product.BuyCustomerBean;
import com.banger.mobile.domain.model.product.ProductBean;
import com.banger.mobile.domain.model.product.ProductDetail;
import com.banger.mobile.domain.model.product.ProductObj;
import com.banger.mobile.domain.model.product.UserAndCountersBean;
import com.banger.mobile.domain.model.system.ProductType;
import com.banger.mobile.domain.model.user.UserInfo;

/**
 * @author hk
 * @version $Id: ProductService.java,v 0.1 Jul 16, 2012 5:41:13 PM hk Exp $
 */
public interface ProductService {
    
    /**
     * 查询所有产品明细信息
     * @return PageUtil<ProductDetail>
     * @param map
     * @param page 分页
     */
    public PageUtil<ProductDetail> getAllProductDetailsPage(Map<String,Object> map, Page page);
    
    /**
     * 查询未处理产品明细信息
     * @return PageUtil<ProductDetail>
     */
    public PageUtil<ProductDetail> getProductIsDeal(Map<String,Object> map, Page page);
    
    /**
     * 根据客户编号查找客户
     * @param String customerNo
     */
    public List<CrmCustomer> findCustomerByNo(String customerNo);
    
    /**
     * 根据营销人员条件查询所有营销人员
     * @param String account
     */
    public List<UserInfo> findUserByAccount(String account);
    
    /**
     * 根据id,编号等查询产品记录
     * 
     */
    public List<ProductBean> getProductByMap(Map<String,Object> map);
    
    /**
     * 新建理财产品
     * @param object productBean
     */
    public void saveProduct(ProductBean productBean);
    
    /**
     * 修改理财产品
     * @param Map
     */
    public void updateProduct(ProductBean productBean);
    
    /**
     * 删除理财产品
     * @param Object
     */
    public void deleteProduct(int productId);
    
    /**
     * 根据id查看产品详情
     * @param int productId
     */
    public ProductObj getProductById(int productId);
    
    /**
     * 根据产品id查询客户购买记录（分页）
     * @param Map<String, Object> map
     * @param String isDeal 是否处理 
     * @return PageUtil<BugCustomer>
     */
    public PageUtil<BuyCustomer> getAllBuyCustomerPage(Map<String, Object> map,String isDeal,Page page);
    
    /**
     * 删除购买客户产品的信息(伪)
     * @param int pcId
     */
    public void deleteBuyCustomer(int pcId);
    
    /**
     * 处理购买产品的客户信息
     * @param int pcId
     */
    public void updateDealCustomer(int pcId);
    
    /**
     * 获取当前时间的前一个月的时间yyyy-MM-dd 工具类
     */
    public String dateMonthUtil();
    
    /**
     * 打散重组
     */
    public String translate (String str);
    
    /**
     * 添加购买产品的客户
     * @param object BuyCustomer
     */
    public void saveBuyProductCustomer(BuyCustomer buyCustomerBean,int customerId,String usertype,int userid);
    
    
    /**
     * 根据产品购买信息查找是否存在(包含删除) 
     * @return ture 存在
     * @return false 不存在
     */
    public Boolean queryBuyCustomerByAll(BuyCustomerBean buyCustomerBean);
    /**
     * 根据产品姓名查询产品
     */
    public List<ProductObj> getProductByName(Map<String, Object> map);
    
    /**
     * 根据部门id查询用户表和柜台人员表集合并排序
     */
    public List<UserAndCountersBean> getUserAndCounterByDeptId(int deptId);
    
    /**
     * 根据产品名称模糊搜索包含的产品名称
     */
    public List<ProductBean> findProductByNo(String productName);

    /**
     * 根据产品id查询是否有客户购买记录
     * @param int productId
     * @return int
     */
    public Integer getBuyCustomerByPid(Map<String, Object> map);
    
    /**
     * 根据客户id查询所有买的所有产品（分页）
     */
    public PageUtil<ProductObj> getCustomerBuyProduct(Map<String,Object> map, Page page);
    
    
    
    /**
     * 取得所有产品 for pad
     * @return
     */
    public List<Product> getAllProductForPad();

    /**
     * 查找产品 for pad
     * @param input
     * @param profitRate
     * @return
     */
    public List<Product> findProductForPad(String input, String profitRate);

    /**
     * 取得产品类型 for pad
     * @return
     */
    public List<ProductType> getProductType();

    /**
     * 得到客户购买产品记录 for pad
     * @param customerId
     * @return
     */
    public List<CustomerBuyProduct> getCustomerBuyProductForPad(Integer customerId,int startRows, int endRows);
    
    /**
     * pad 根据产品id查询详情
     */
    public ProductObj getProductByIdForPad(int productId);
    
    /**
     * 查询产品购买客户
     * @param productId
     * @param startRows
     * @param endRows
     * @param userId
     * @return
     */
    public List<ProductBuyCustomer> findProductCustomerForPad(Integer productId,int startRows, int endRows, int userId);
    
    /**
     * pad 根据客户ID查询购买产品总数
     */
    public int getCustomerBuyProductCountForPad(Integer customerId);
    
    /**
     * pad 根据产品ID查询客户总数
     */
    public int findProductCustomerCountForPad(Integer productId,int userId);
    
    /**
     * 根据产品ID查询此用户的购买记录的总额,此记录的购买时间要在对应的营销任务的时间范围内
     * @param int productId
     * @param int userId
     * @return Double
     */
    public Double querySumBuyCustomerByPid(Integer productId,String userId,String str);
    
    /**
     * 根据募集单位查询是否被占用 
     * @param Integer unitId
     * @return bool 
     */
    public boolean getProductCountByUnitId(Integer unitId);
}
