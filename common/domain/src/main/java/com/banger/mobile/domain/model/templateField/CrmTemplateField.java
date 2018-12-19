/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :QianJie
 * Create Date:May 28, 2012
 */
package com.banger.mobile.domain.model.templateField;

import java.util.ArrayList;
import java.util.List;

import com.banger.mobile.domain.model.base.templateField.BaseCrmTemplateField;
import com.banger.mobile.domain.model.fieldCodeData.CrmFieldCodeData;

import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author QianJie
 * @version $Id: CrmTemplateField.java,v 0.1 May 28, 2012 2:44:06 PM QianJie Exp $
 */
public class CrmTemplateField extends BaseCrmTemplateField{
	private List<CrmFieldCodeData> codes;
	private String fieldName="";      //自定义字段的实体属性名称
    
	/**
	 * 
	 * @param flag 0新增编辑 1查询 2查看
	 * @return
	 */
    public Integer getColSpan(Integer flag) {
    	String type = this.getTemplateFieldType();
    	if("Date".equalsIgnoreCase(type) && flag.intValue()==1)return 2;
    	if("Number".equalsIgnoreCase(type) && flag.intValue()==1)return 2;
		if("TextArea".equalsIgnoreCase(type) && flag.intValue()!=1)return 2;
		return 1;
	}
    
    public String getFieldName()
    {
    	if(this.fieldName==null || "".equals(this.fieldName))
    	{
    		String fn = this.getExtFieldName();
    		if(fn!=null && !"".equals(fn))
    		{
    			String[] fs = fn.split("\\_");
    			for(int i=0;i<fs.length;i++)
    			{
    				if(i==0)this.fieldName+=fs[i].toLowerCase();
    				else this.fieldName+=fs[i].substring(0,1).toUpperCase()+fs[i].substring(1).toLowerCase();
    			}
    		}
    	}
    	return this.fieldName;
    }
    
    public String getFieldNameEnd()
    {
    	return getFieldName()+"End";
    }

	public List<CrmFieldCodeData> getCodes() {
		return codes;
	}

	public void setCodes(List<CrmFieldCodeData> codes) {
		this.codes = codes;
	}

	private static final long serialVersionUID = -2932343991517476729L;

    public CrmTemplateField() {
        super();
        this.codes = new ArrayList<CrmFieldCodeData>();
    }

    private String fieldTypeName;    //模版字段数据类型名称
    public String getFieldTypeName() {
        return fieldTypeName;
    }

    public void setFieldTypeName(String fieldTypeName) {
        this.fieldTypeName = fieldTypeName;
    }
    
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-127684667, -1054588903).appendSuper(super.hashCode())
            .toHashCode();
    }


    

}
