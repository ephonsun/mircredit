package com.banger.mobile.dao.pdtProduct;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.pdtProduct.BuyCustomerBean;
import com.banger.mobile.domain.model.pdtProduct.PdtProduct;
import com.banger.mobile.domain.model.pdtProduct.PdtProductCustomer;
import com.banger.mobile.domain.model.pdtProduct.UserAndCountersBean;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 25, 2012 1:16:54 PM
 * 类说明
 */
public interface PdtProductDao {

	
	/**
	 * 新建产品
	 * @param pdtProduct
	 * @return
	 */
	public Integer insertPdtProduct(PdtProduct basePdtProduct);

	/**
     * 查询所有产品明细信息
     * @return PageUtil<PdtProduct>
     */
    public PageUtil<PdtProduct> getAllProductPage(Map<String,Object> map, Page page);

	/**
	 * 产品名称是否唯一
	 * @param pdtProduct
	 * @return
	 */
	public boolean isProductNameExist(PdtProduct basePdtProduct);
	
	/**
	 * 产品编号是否唯一
	 * @param pdtProduct
	 * @return
	 */
	public boolean isProductCodeExist(PdtProduct basePdtProduct);
	
	/**
	 * 通过产品ID获取产品实体
	 * @param productId
	 * @return
	 */
	public PdtProduct getPdtProduct(Integer productId);
	/**
	 * 编辑产品
	 * @param pdtProduct
	 */
	public void updatePdtProduct(PdtProduct pdtProduct);
	
	/**
	 * 删除产品
	 * @param productId
	 * @return
	 */
	public Integer deletePdtProduct(Integer productId);
	
	/**
	 * 停售产品
	 * @param productId
	 * @return
	 */
	public Integer stopPdtProduct(Integer productId);
	
	/**
	 * 判断产品字段是否被使用
	 * @param templateId
	 * @return
	 */
	public boolean isPdtTemplateInUse(Integer templateId);

    /**
     * 根据客户编号查找客户
     * @param String customerNo
     */
    public List<CrmCustomer> findCustomerByNo(String customerNo);
    
    /**
     * 根据产品购买信息查找是否存在(包含删除) 
     * @return ture 存在
     * @return false 不存在
     */
    public Boolean queryBuyCustomerByAll(BuyCustomerBean buyCustomerBean);
    
    /**
     * 添加购买产品的客户
     * @param object BuyCustomerBean
     */
    public void saveBuyProductCustomer(BuyCustomerBean buyCustomerBean);
    
    /**
     * 批量添加购买产品的客户
     * @param object List<BuyCustomerBean>
     */
    public void saveBuyProductCustomerBatch(List<BuyCustomerBean> beanList);
	
    /**
	 * 产品下面有没有购买客户 
	 * @param productId
	 * @return
	 */
    public boolean isExistBuyCustomer(Integer productId);
    
    /**
     * 购买客户列表
     * @param map
     * @param page
     * @return
     */
    public PageUtil<PdtProductCustomer> getPdtBuyCustomerPage(Map<String, Object> map,Page page);
    
    
    /**
     * 产品的已销售额（根据权限）
     * 
     */
    public BigDecimal getProductTotalSales(Map<String, Object> map);
    
    /**
     * 批量插入产品
     */
    public void addProductBatch(List<PdtProduct> list);
    
    /**
     * 删除购买客户产品的信息(伪)
     * @param int pcId
     */
    public void deleteBuyCustomer(int pcId);
    
    /**
     * 根据部门id查询用户表和柜台人员表集合并排序
     */
    public List<UserAndCountersBean> getUserAndCounterByDeptId(int deptId);

    /**
     * 修改营销人员
     */
    public void updateCounterUser(Map<String, Object> map);
    
    /**
     * 查询客户下的产品购买记录
     */
    public PageUtil<PdtProduct> getBuyProductByCustomer(Map<String, Object> map,Page page);
    
    /**
     * 营销任务用到取前5条在售产品
     * @param map,键：productName,productId
     * @return
     */
    public List<PdtProduct> getProductListForTask(Map<String, Object> map);
    /**
     * 缓存所有产品
     */
    public List<PdtProduct> getAllProductForCache();
    
    /**
	 * 通过产品ID获取产品实体 
	 * @param productId
	 * @return
	 */
	public PdtProduct getAllPdtProductById(Integer productId);
}



