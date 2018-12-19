package com.banger.mobile.dao.pdtProduct;

import java.util.List;

import com.banger.mobile.domain.model.pdtProduct.PdtProductField;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 26, 2012 3:17:36 PM
 * 类说明
 */
public interface PdtProductFieldDao {
	/**
	 * 插入产品字段
	 * @param basePdtProductField
	 * @return
	 */
	public Integer batchInsertProductField(List<PdtProductField> list);
	/**
	 * 产品类型模板字段是否被使用
	 * @param templateId
	 * @param fieldId
	 * @return
	 */
	public boolean isPdtTempFieldInUse(Integer templateId,Integer fieldId);
	/**
	 * 删除产品模板字段
	 * @param templateId
	 * @param fieldId
	 * @return
	 */
	public Integer deletePdtTempField(Integer templateId,Integer fieldId);

	/**
	 * 根据产品ID获取自定义字段
	 * @param productId
	 * @return
	 */
	public List<PdtProductField> getPdtFieldListByPdtId(Integer productId);
	
	/**
	 * 根据产品ID删除全部字段
	 * @param productId
	 * @return
	 */
	public Integer deleteFieldByProductId(Integer productId);
	/**
	 * 批量插入自定义字段
	 */
	public void addProductFeildBatch(List<PdtProductField> list);
}



