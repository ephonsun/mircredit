package com.banger.mobile.dao.pdtProduct.ibatis;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.pdtProduct.PdtProductDao;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.pdtProduct.BuyCustomerBean;
import com.banger.mobile.domain.model.pdtProduct.PdtProduct;
import com.banger.mobile.domain.model.pdtProduct.PdtProductCustomer;
import com.banger.mobile.domain.model.pdtProduct.UserAndCountersBean;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 25, 2012 1:18:04 PM
 * 类说明
 */
public class PdtProductDaoiBatis extends GenericDaoiBatis implements PdtProductDao{

	public PdtProductDaoiBatis(Class persistentClass) {
		super(PdtProduct.class);
		// TODO Auto-generated constructor stub
	}
	public PdtProductDaoiBatis() {
		super(PdtProduct.class);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 新建产品
	 * @param pdtProduct
	 * @return
	 */
	public Integer insertPdtProduct(PdtProduct basePdtProduct){
		return (Integer)this.getSqlMapClientTemplate().insert("insertPdtProduct",basePdtProduct);
	}


	/**
	 * 查询所有产品明细信息
	 * @return PageUtil<PdtProduct>
	 */
	public PageUtil<PdtProduct> getAllProductPage(Map<String,Object> map, Page page){
		ArrayList<PdtProduct> list=(ArrayList<PdtProduct>) this.findQueryPage("getProductPageMap", "getProductCount",map, page);
		return new PageUtil<PdtProduct>(list, page);
	}
	/**
	 * 产品名称是否唯一
	 * @param pdtProduct
	 * @return
	 */
	public boolean isProductNameExist(PdtProduct basePdtProduct){
		int count=(Integer)this.getSqlMapClientTemplate().queryForObject("getProductByName",basePdtProduct);
		if(count>0){
			return true;
		}else {
			return false;
		}
	}

	/**
	 * 产品编号是否唯一
	 * @param pdtProduct
	 * @return
	 */
	public boolean isProductCodeExist(PdtProduct basePdtProduct){
		int count=(Integer)this.getSqlMapClientTemplate().queryForObject("getProductByCode",basePdtProduct);
		if(count>0){
			return true;
		}else {
			return false;
		}
	}

	/**
	 * 通过产品ID获取产品实体
	 * @param productId
	 * @return
	 */
	public PdtProduct getPdtProduct(Integer productId){
		return (PdtProduct)this.getSqlMapClientTemplate().queryForObject("getPdtProductById",productId);
	}
	/**
	 * 编辑产品
	 * @param pdtProduct
	 */
	public void updatePdtProduct(PdtProduct pdtProduct){
		this.getSqlMapClientTemplate().update("updatePdtProduct",pdtProduct);
	}

	/**
	 * 删除产品
	 * @param productId
	 * @return
	 */
	public Integer deletePdtProduct(Integer productId){
		return (Integer)this.getSqlMapClientTemplate().update("deltePdtProduct",productId);
	}

	/**
	 * 停售产品
	 * @param productId
	 * @return
	 */
	public Integer stopPdtProduct(Integer productId){
		return (Integer)this.getSqlMapClientTemplate().update("stopPdtProduct",productId);
	}

	/**
	 * 判断产品字段是否被使用
	 * @param templateId
	 * @return
	 */
	public boolean isPdtTemplateInUse(Integer templateId){
		int count=(Integer)this.getSqlMapClientTemplate().queryForObject("getProductCountByTempId",templateId);
		if(count>0){
			return true;
		}else {
			return false;
		}
	}


	/**
	 * 根据客户编号查找客户
	 *
	 */
	public List<CrmCustomer> findCustomerByNo(String customerNo) {
		return this.getSqlMapClientTemplate().queryForList("findCustomerByNo", customerNo);
	}

	/**
	 * 根据产品购买信息查找是否存在(不包含删除)
	 * @return
	 */
	public Boolean queryBuyCustomerByAll(BuyCustomerBean buyCustomerBean){
		if(buyCustomerBean.getCustomerId()==null)   return false;
		if((Integer)getSqlMapClientTemplate().queryForObject("queryBuyCustomerByAll",buyCustomerBean)>0)return true;
		else return false;
	}

	/**
	 * 添加购买产品的客户
	 * @param object BuyCustomerBean
	 */
	public void saveBuyProductCustomer(BuyCustomerBean buyCustomerBean){
		this.getSqlMapClientTemplate().insert("saveBuyPdtProductCustomer",buyCustomerBean);
	}

	/**
	 * 批量添加购买产品的客户
	 * @param beanList
	 * @see com.banger.mobile.dao.product.ProductDao#saveBuyProductCustomerBatch(java.util.List)
	 */
	public void saveBuyProductCustomerBatch(List<BuyCustomerBean> beanList) {
		this.exectuteBatchInsert("saveBuyPdtProductCustomer", beanList);
	}

	/**
	 * 产品下面有没有购买客户 
	 * @param productId
	 * @return
	 */
	public boolean isExistBuyCustomer(Integer productId){
		int count=(Integer)this.getSqlMapClientTemplate().queryForObject("getBuyCusCountByPdtId",productId);
		if(count>0){
			return true;
		}else {
			return false;
		}
	}

	/**
	 * 购买客户列表
	 * @param map
	 * @param page
	 * @return
	 */
	public PageUtil<PdtProductCustomer> getPdtBuyCustomerPage(Map<String, Object> map,Page page){
		List<PdtProductCustomer> list=this.findQueryPage(
				"getPdtBuyCustomerPage", "getPdtBuyCustomerPageCount", map, page);
		if(list==null){
			list=new ArrayList<PdtProductCustomer>();
		}
		return new PageUtil<PdtProductCustomer>(list,page);
	}


	/**
	 * 产品的已销售额（根据权限）
	 *
	 */
	public BigDecimal getProductTotalSales(Map<String, Object> map){
		return(BigDecimal)this.getSqlMapClientTemplate().queryForObject("getProductTotalSales",map);
	}


	/**
	 * 删除购买客户产品的信息(伪)
	 * @param int pcId
	 */
	public void deleteBuyCustomer(int pcId){
		this.getSqlMapClientTemplate().update("deleteBuyCustomer",pcId);
	}

	/**
	 * 根据部门id查询用户表和柜台人员表集合并排序
	 */
	public List<UserAndCountersBean> getUserAndCounterByDeptId(int deptId){
		return this.getSqlMapClientTemplate().queryForList("getUserAndCounterByDeptId",deptId);
	}

	/**
	 * 修改营销人员
	 */
	public void updateCounterUser(Map<String, Object> map){
		this.getSqlMapClientTemplate().update("updateSellUser",map);
	}
	/**
	 * 批量插入产品
	 */
	public void addProductBatch(List<PdtProduct> list){
		this.exectuteBatchInsert("insertPdtProduct", list);
	}

	/**
	 * 查询客户下的产品购买记录
	 */
	public PageUtil<PdtProduct> getBuyProductByCustomer(Map<String, Object> map,Page page){
		List<PdtProduct> list =(List<PdtProduct>)this.findQueryPage("getBuyProductByCustomer", "getBuyProductCountByCustomer", map, page);
		if(list==null){
			list = new ArrayList<PdtProduct>();
		}
		return new PageUtil<PdtProduct>(list,page);

	}

	/**
	 * 营销任务用到取前5条在售产品
	 * @param map,键：productName,productId
	 * @return
	 */
	public List<PdtProduct> getProductListForTask(Map<String, Object> map){
		return this.getSqlMapClientTemplate().queryForList("getProductListForTask",map);
	}
	/**
	 * 缓存所有产品
	 */
	public List<PdtProduct> getAllProductForCache(){
		return this.getSqlMapClientTemplate().queryForList("getAllProductForCache");
	}

	/**
	 * 通过产品ID获取产品实体 
	 * @param productId
	 * @return
	 */
	public PdtProduct getAllPdtProductById(Integer productId){
		return (PdtProduct)this.getSqlMapClientTemplate().queryForObject("getAllPdtProductById",productId);
	}
}



