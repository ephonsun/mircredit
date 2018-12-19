package com.banger.mobile.dao.pdtProduct.ibatis;

import java.util.List;

import com.banger.mobile.dao.pdtProduct.PdtProductFieldDao;
import com.banger.mobile.domain.model.pdtProduct.PdtProductField;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 26, 2012 3:17:51 PM
 * 类说明
 */
public class PdtProductFieldDaoiBatis extends GenericDaoiBatis implements PdtProductFieldDao{
	public PdtProductFieldDaoiBatis(Class persistentClass) {
		super(PdtProductField.class);
		// TODO Auto-generated constructor stub
	}	
	public PdtProductFieldDaoiBatis() {
		super(PdtProductField.class);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 插入产品字段
	 * @param basePdtProductField
	 * @return
	 */
	public Integer batchInsertProductField(List<PdtProductField> list){
		return this.exectuteBatchInsert("insertPdtProductField", list);
	}
	/**
	 * 产品类型模板字段是否被使用
	 * @param templateId
	 * @param fieldId
	 * @return
	 */
	public boolean isPdtTempFieldInUse(Integer templateId,Integer fieldId){
		PdtProductField pdtProductField = new PdtProductField();
		pdtProductField.setTemplateId(templateId);
		pdtProductField.setTemplateFieldId(fieldId);
		Integer count = (Integer)this.getSqlMapClientTemplate().queryForObject("getPdtProductFieldCount",pdtProductField);
		if(count!=null&&count!=0){
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 删除产品模板字段
	 * @param templateId
	 * @param fieldId
	 * @return
	 */
	public Integer deletePdtTempField(Integer templateId,Integer fieldId){
		PdtProductField pdtProductField = new PdtProductField();
		pdtProductField.setTemplateId(templateId);
		pdtProductField.setTemplateFieldId(fieldId);		
		return (Integer)this.getSqlMapClientTemplate().update("deleteProductField",pdtProductField);
	}
	
	/**
	 * 根据产品ID获取自定义字段
	 * @param productId
	 * @return
	 */
	public List<PdtProductField> getPdtFieldListByPdtId(Integer productId){
		return this.getSqlMapClientTemplate().queryForList("getPdtFieldListByPdtId", productId);
	}	
	
	
	/**
	 * 根据产品ID删除全部字段
	 * @param productId
	 * @return
	 */
	public Integer deleteFieldByProductId(Integer productId){
		return (Integer)this.getSqlMapClientTemplate().delete("deleteFieldByProductId",productId);
	}
	 /**
     * 批量插入自定义字段
     */
    public void addProductFeildBatch(List<PdtProductField> list){
        this.exectuteBatchInsert("insertPdtProductField", list);
    }
}



