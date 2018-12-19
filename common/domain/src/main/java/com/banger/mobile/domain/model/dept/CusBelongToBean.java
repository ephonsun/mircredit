/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :部门实体
 * Author     :cheny
 * Create Date:2012-3-28
 */
package com.banger.mobile.domain.model.dept;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;



public class CusBelongToBean extends BaseObject implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5562338066745911470L;
	private Integer id;                     //机构ID或人员ID
    private String textName;                //机构名称或人员名称
    private Integer parentId;            	//上级部门ID
    private String type;                 	//类型 （机构或人员）
    private String deptName;				//机构名
    
    
    /**
     * 
     */
    public CusBelongToBean() {
        super();
    }


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getTextName() {
		return textName;
	}


	public void setTextName(String textName) {
		this.textName = textName;
	}


	public Integer getParentId() {
		return parentId;
	}


	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}
	

	public String getDeptName() {
		return deptName;
	}


	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public CusBelongToBean(Integer id, String textName, Integer parentId,
			String type, String deptName) {
		super();
		this.id = id;
		this.textName = textName;
		this.parentId = parentId;
		this.type = type;
		this.deptName = deptName;
	}


	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof CusBelongToBean)) {
			return false;
		}
		CusBelongToBean rhs = (CusBelongToBean) object;
		return new EqualsBuilder().appendSuper(super.equals(object))
				.append(this.deptName, rhs.deptName).append(this.id, rhs.id)
				.append(this.parentId, rhs.parentId)
				.append(this.textName, rhs.textName)
				.append(this.type, rhs.type).isEquals();
	}


	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(2076437193, -1521680465)
				.appendSuper(super.hashCode()).append(this.deptName)
				.append(this.id).append(this.parentId).append(this.textName)
				.append(this.type).toHashCode();
	}
    
}