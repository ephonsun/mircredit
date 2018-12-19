/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :参数传递...
 * Author     :yangy
 * Create Date:2012-9-5
 */
package com.banger.mobile.domain.model.customer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import com.banger.mobile.domain.collection.DataTable;
import com.banger.mobile.domain.model.base.customer.BaseFamilyName;
import com.banger.mobile.domain.model.crmCounterUser.CrmCounterUser;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.pdtProduct.BuyCustomerBean;
import com.banger.mobile.domain.model.pdtProduct.PdtProductField;
import com.banger.mobile.domain.model.pdtProduct.PdtTemplateField;
import com.banger.mobile.domain.model.system.CrmCustomerIndustry;
import com.banger.mobile.domain.model.system.CrmCustomerType;
import com.banger.mobile.domain.model.templateField.CrmTemplateField;
import com.banger.mobile.domain.model.user.SysRoleMember;
import com.banger.mobile.domain.model.user.SysUser;

/**
 * 参数传递
 * @author yangyang
 * @version $Id: CrmCustomerImportBean.java,v 0.1 2012-9-5 下午5:35:49 yangyong Exp $
 */
public class CrmCustomerTransfer implements Serializable {

    private static final long                 serialVersionUID = 1279537450656204580L;

    private Map<String, String>               cityMap;
    private Map<String, String>               provMap;
    private Map<String, String>               codeMap;
    private Map<String, SysDept>              deptMap;
    private Map<String, SysUser>              userMap;
    private Map<String, Integer>              repeatCustomerMap;
    private Map<String, CrmCustomer>          repeatCustomerBeanMap;
    private Map<String, CrmTemplateField>     customizedFields;                       //扩展字段
    private DataTable                         errortbl;
    private String                            customerNoStr;
    private Integer[]                         InChargeOfDeptUserIds;
    private Integer[]                         InChargeOfDeptIds;
    private List<CrmCustomer>                 addCrmCustomerList;
    private List<CrmCustomer>                 updateCrmCustomerList;
    private List<BuyCustomerBean>             addBuyCustomerList;
    private List<BaseFamilyName>              familyNameList;
    private List<?>                           addBeanList;
    private List<?>                           updateBeanList;
    private boolean                           isInChargeOfDepartment;
    private Map<String, CrmCustomerType>      customerTypeMap;
    private Map<String, CrmCustomerIndustry>  customerIndustryMap;
    private Map<Integer, SysRoleMember>       adminAndDepartmentMap;
    private List<CrmCustomerExtBean>          extList;
    private String                            templateIds;
    private HSSFWorkbook                      hssfWorkbook;
    private Workbook                          workbook;
    private Map<BuyCustomerBean, CrmCustomer> buyCustomerMap;
    private String                            account;
    private Map<String, String>               accountMap;
    private Map<String, String>               deptCodeMap;
    private Map<String, CrmCounterUser>       counterUserMap;
    private Map<String, String>               parameterMap;
    private HashMap<BuyCustomerBean, String>  entireCustomerNoMap;
    private Map<Integer, List<PdtTemplateField>> feildMap;        //key:模板Id value:字段集合
    private Map<Integer, List<String>>        codedataMap;        //key:字段Id value:下拉项集合
    private List<PdtProductField>             productFeildList;   //产品自定义字段集合
    private Map<String, String>               productNameMap;     //key:（导入excel中）产品名称 value:行号
    private Map<String, String>               productCodeMap;     //key:（导入excel中）产品编号 value:行号
    private List<String>                      productNameList;    //系统中所有产品名称
    private List<String>                      productCodeList;    //系统中所有产品编号
    private Map<Integer,String>               templateMap;        //产品模板
    private Map<String,Date>               	  sellTimeMap;        //产品销售时间
    private Map<String, Boolean>              impCusConfMap;  
    private Map<String, Object>               customerCodeMap = new HashMap<String, Object>();
    private Integer addCount;
    private Integer executeErrorBatch;                                 //每批处理错误的条数

    public Map<String, Object> getCustomerCodeMap() {
        return customerCodeMap;
    }

    public void setCustomerCodeMap(Map<String, Object> customerCodeMap) {
        this.customerCodeMap = customerCodeMap;
    }

    public Map<String, String> getAccountMap() {
        return accountMap;
    }

    public void setAccountMap(Map<String, String> accountMap) {
        this.accountMap = accountMap;
    }

    public Map<String, String> getDeptCodeMap() {
        return deptCodeMap;
    }

    public void setDeptCodeMap(Map<String, String> deptCodeMap) {
        this.deptCodeMap = deptCodeMap;
    }

    public Integer getAddCount() {
		return addCount;
	}

	public void setAddCount(Integer addCount) {
		this.addCount = addCount;
	}


	public Map<String, CrmCustomer> getRepeatCustomerBeanMap() {
        return repeatCustomerBeanMap;
    }

    public void setRepeatCustomerBeanMap(Map<String, CrmCustomer> repeatCustomerBeanMap) {
        this.repeatCustomerBeanMap = repeatCustomerBeanMap;
    }

	public List<?> getAddBeanList() {
        return addBeanList;
    }

    public void setAddBeanList(List<?> addBeanList) {
        this.addBeanList = addBeanList;
    }

    public Map<Integer, String> getTemplateMap() {
        return templateMap;
    }

    public void setTemplateMap(Map<Integer, String> templateMap) {
        this.templateMap = templateMap;
    }

    public List<?> getUpdateBeanList() {
        return updateBeanList;
    }

    public Map<String, String> getProductNameMap() {
        return productNameMap;
    }

    public void setProductNameMap(Map<String, String> productNameMap) {
        this.productNameMap = productNameMap;
    }

    public Map<String, String> getProductCodeMap() {
        return productCodeMap;
    }

    public void setProductCodeMap(Map<String, String> productCodeMap) {
        this.productCodeMap = productCodeMap;
    }

    public List<String> getProductNameList() {
        return productNameList;
    }

    public void setProductNameList(List<String> productNameList) {
        this.productNameList = productNameList;
    }

    public List<String> getProductCodeList() {
        return productCodeList;
    }

    public void setProductCodeList(List<String> productCodeList) {
        this.productCodeList = productCodeList;
    }

    public void setUpdateBeanList(List<?> updateBeanList) {
        this.updateBeanList = updateBeanList;
    }

    public List<PdtProductField> getProductFeildList() {
        return productFeildList;
    }

    public void setProductFeildList(List<PdtProductField> productFeildList) {
        this.productFeildList = productFeildList;
    }

    public Workbook getWorkbook() {
        return workbook;
    }

    public void setWorkbook(Workbook workbook) {
        this.workbook = workbook;
    }

    public HashMap<BuyCustomerBean, String> getEntireCustomerNoMap() {
        return entireCustomerNoMap;
    }

    public void setEntireCustomerNoMap(HashMap<BuyCustomerBean, String> entireCustomerNoMap) {
        this.entireCustomerNoMap = entireCustomerNoMap;
    }

    public List<BuyCustomerBean> getAddBuyCustomerList() {
        return addBuyCustomerList;
    }

    public void setAddBuyCustomerList(List<BuyCustomerBean> addBuyCustomerList) {
        this.addBuyCustomerList = addBuyCustomerList;
    }
    public Map<BuyCustomerBean, CrmCustomer> getBuyCustomerMap() {
        return buyCustomerMap;
    }

    public void setBuyCustomerMap(Map<BuyCustomerBean, CrmCustomer> buyCustomerMap) {
        this.buyCustomerMap = buyCustomerMap;
    }
    
    public Map<String, String> getParameterMap() {
        return parameterMap;
    }

    public void setParameterMap(Map<String, String> parameterMap) {
        this.parameterMap = parameterMap;
    }

    public Map<String, CrmCounterUser> getCounterUserMap() {
        return counterUserMap;
    }

    public void setCounterUserMap(Map<String, CrmCounterUser> counterUserMap) {
        this.counterUserMap = counterUserMap;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public HSSFWorkbook getHssfWorkbook() {
        return hssfWorkbook;
    }

    public void setHssfWorkbook(HSSFWorkbook hssfWorkbook) {
        this.hssfWorkbook = hssfWorkbook;
    }

    public String getTemplateIds() {
        return templateIds;
    }

    public void setTemplateIds(String templateIds) {
        this.templateIds = templateIds;
    }

    public List<CrmCustomerExtBean> getExtList() {
        return extList;
    }

    public void setExtList(List<CrmCustomerExtBean> extList) {
        this.extList = extList;
    }

    public Map<Integer, SysRoleMember> getAdminAndDepartmentMap() {
        return adminAndDepartmentMap;
    }

    public void setAdminAndDepartmentMap(Map<Integer, SysRoleMember> adminAndDepartmentMap) {
        this.adminAndDepartmentMap = adminAndDepartmentMap;
    }

    public Map<String, CrmCustomerType> getCustomerTypeMap() {
        return customerTypeMap;
    }

    public void setCustomerTypeMap(Map<String, CrmCustomerType> customerTypeMap) {
        this.customerTypeMap = customerTypeMap;
    }

    public Map<String, CrmCustomerIndustry> getCustomerIndustryMap() {
        return customerIndustryMap;
    }

    public void setCustomerIndustryMap(Map<String, CrmCustomerIndustry> customerIndustryMap) {
        this.customerIndustryMap = customerIndustryMap;
    }

    public boolean isInChargeOfDepartment() {
        return isInChargeOfDepartment;
    }

    public void setInChargeOfDepartment(boolean isInChargeOfDepartment) {
        this.isInChargeOfDepartment = isInChargeOfDepartment;
    }

    public Map<String, SysDept> getDeptMap() {
        return deptMap;
    }

    public void setDeptMap(Map<String, SysDept> deptMap) {
        this.deptMap = deptMap;
    }

    public Map<String, SysUser> getUserMap() {
        return userMap;
    }

    public void setUserMap(Map<String, SysUser> userMap) {
        this.userMap = userMap;
    }

    public List<BaseFamilyName> getFamilyNameList() {
        return familyNameList;
    }

    public void setFamilyNameList(List<BaseFamilyName> familyNameList) {
        this.familyNameList = familyNameList;
    }

    public List<CrmCustomer> getAddCrmCustomerList() {
        return addCrmCustomerList;
    }

    public void setAddCrmCustomerList(List<CrmCustomer> addCrmCustomerList) {
        this.addCrmCustomerList = addCrmCustomerList;
    }

    public List<CrmCustomer> getUpdateCrmCustomerList() {
        return updateCrmCustomerList;
    }

    public void setUpdateCrmCustomerList(List<CrmCustomer> updateCrmCustomerList) {
        this.updateCrmCustomerList = updateCrmCustomerList;
    }

    public String getCustomerNoStr() {
        return customerNoStr;
    }

    public Map<String, Integer> getRepeatCustomerMap() {
        return repeatCustomerMap;
    }

    public void setRepeatCustomerMap(Map<String, Integer> repeatCustomerMap) {
        this.repeatCustomerMap = repeatCustomerMap;
    }

    public void setCustomerNoStr(String customerNoStr) {
        this.customerNoStr = customerNoStr;
    }

    public Integer[] getInChargeOfDeptUserIds() {
        return InChargeOfDeptUserIds;
    }

    public void setInChargeOfDeptUserIds(Integer[] inChargeOfDeptUserIds) {
        InChargeOfDeptUserIds = inChargeOfDeptUserIds;
    }

    public Integer[] getInChargeOfDeptIds() {
        return InChargeOfDeptIds;
    }

    public void setInChargeOfDeptIds(Integer[] inChargeOfDeptIds) {
        InChargeOfDeptIds = inChargeOfDeptIds;
    }

    public DataTable getErrortbl() {
        return errortbl;
    }

    public void setErrortbl(DataTable errortbl) {
        this.errortbl = errortbl;
    }

    public Map<String, String> getCityMap() {
        return cityMap;
    }

    public void setCityMap(Map<String, String> cityMap) {
        this.cityMap = cityMap;
    }

    public Map<String, String> getProvMap() {
        return provMap;
    }

    public Map<Integer, List<PdtTemplateField>> getFeildMap() {
        return feildMap;
    }

    public void setFeildMap(Map<Integer, List<PdtTemplateField>> feildMap) {
        this.feildMap = feildMap;
    }

    public Map<Integer, List<String>> getCodedataMap() {
        return codedataMap;
    }

    public void setCodedataMap(Map<Integer, List<String>> codedataMap) {
        this.codedataMap = codedataMap;
    }

    public void setProvMap(Map<String, String> provMap) {
        this.provMap = provMap;
    }

    public Map<String, String> getCodeMap() {
        return codeMap;
    }

    public void setCodeMap(Map<String, String> codeMap) {
        this.codeMap = codeMap;
    }

    public Map<String, CrmTemplateField> getCustomizedFields() {
        return customizedFields;
    }

    public void setCustomizedFields(Map<String, CrmTemplateField> customizedFields) {
        this.customizedFields = customizedFields;
    }

    public Map<String, Boolean> getImpCusConfMap() {
		return impCusConfMap;
	}

	public void setImpCusConfMap(Map<String, Boolean> impCusConfMap) {
		this.impCusConfMap = impCusConfMap;
	}

	public Integer getExecuteErrorBatch()
	{
		return executeErrorBatch;
	}

	public void setExecuteErrorBatch(Integer executeErrorBatch)
	{
		this.executeErrorBatch = executeErrorBatch;
	}

	/**
     * @see java.lang.Comparable#compareTo(Object)
     */
    public int compareTo(Object object) {
        CrmCustomerTransfer myClass = (CrmCustomerTransfer) object;
        return new CompareToBuilder().append(this.customizedFields, myClass.customizedFields)
            .toComparison();
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof CrmCustomerTransfer)) {
            return false;
        }
        CrmCustomerTransfer rhs = (CrmCustomerTransfer) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.customizedFields, rhs.customizedFields).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1261619285, -2034632259).appendSuper(super.hashCode())
            .append(this.customizedFields).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("customizedFields", this.customizedFields)
            .append("codeMap", this.getCodeMap()).append("cityMap", this.getCityMap())
            .append("provMap", this.getProvMap()).toString();
    }

	public Map<String, Date> getSellTimeMap() {
		return sellTimeMap;
	}

	public void setSellTimeMap(Map<String, Date> sellTimeMap) {
		this.sellTimeMap = sellTimeMap;
	}
    
}
