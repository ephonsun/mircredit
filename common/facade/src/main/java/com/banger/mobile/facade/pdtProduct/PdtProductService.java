package com.banger.mobile.facade.pdtProduct;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.pdtProduct.BuyCustomer;
import com.banger.mobile.domain.model.pdtProduct.BuyCustomerBean;
import com.banger.mobile.domain.model.pdtProduct.PdtProduct;
import com.banger.mobile.domain.model.pdtProduct.PdtProductCustomer;
import com.banger.mobile.domain.model.pdtProduct.UserAndCountersBean;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 25, 2012 1:10:26 PM
 * 类说明
 */
public interface PdtProductService {
	/**
	 * 新建产品
	 * @param pdtProduct
	 * @return
	 */
	public Integer insertPdtProduct(PdtProduct pdtProduct);
	
	/**
     * 查询所有产品明细信息
     * @return PageUtil<PdtProduct>
     * @param map
     * @param page 分页
     */
    public PageUtil<PdtProduct> getAllProductPage(Map<String,Object> map, Page page);
    
    /**
     * 根据模版搜索 拼接sql语句
     */
    public Map<String, String> queryProductFields(Map map,Integer tempId);
    
	/**
	 * 通过产品ID获取产品实体
	 * @param productId
	 * @return
	 */
	public PdtProduct getPdtProduct(Integer productId);
	
	/**
	 * 产品名称是否唯一
	 * @param pdtProduct
	 * @return
	 */
	public boolean isProductNameExist(PdtProduct pdtProduct);
	
	/**
	 * 产品编号是否唯一
	 * @param pdtProduct
	 * @return
	 */
	public boolean isProductCodeExist(PdtProduct pdtProduct);
	
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
	public String deletePdtProduct(Integer productId);
	
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
     * @param object BuyCustomer
     */
    public void saveBuyProductCustomer(BuyCustomer buyCustomerBean,int customerId,String usertype,int userid);
        
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
    public void updateCounterUser(String pcIds,Integer userId,Integer userType);
    
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
	 * 通过产品ID获取产品实体 
	 * @param productId
	 * @return
	 */
	public PdtProduct getAllPdtProductById(Integer productId);
}



