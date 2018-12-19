/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :功能权限树实体
 * Author     :cheny
 * Create Date:2012-5-31
 */
package com.banger.mobile.domain.model.funcAuth;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author cheny
 * @version $Id: FuncAuthBean.java,v 0.1 2012-5-31 下午1:04:20 cheny Exp $
 */
public class FuncAuthBean implements Serializable{

    private static final long serialVersionUID = 220364774895509381L;

    private Integer funcId;       //功能id
    private Integer funcParentId;       //功能父id
    private String funcName;      //功能名称
    private String type;          //功能类型  FUNC功能  MENU菜单
    private String url;           //网页地址
    
    
    /**
     * 
     */
    public FuncAuthBean() {
        super();
    }
    
    /**
     * @param funcId
     * @param menuId
     * @param funcName
     * @param type
     * @param url
     */
    public FuncAuthBean(Integer funcId, Integer funcParentId, String funcName, String type, String url) {
        super();
        this.funcId = funcId;
        this.funcParentId = funcParentId;
        this.funcName = funcName;
        this.type = type;
        this.url = url;
    }

    public Integer getFuncId() {
        return funcId;
    }
    public void setFuncId(Integer funcId) {
        this.funcId = funcId;
    }
 
    public Integer getFuncParentId() {
        return funcParentId;
    }

    public void setFuncParentId(Integer funcParentId) {
        this.funcParentId = funcParentId;
    }

    public String getFuncName() {
        return funcName;
    }
    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String geturl() {
        return url;
    }
    public void seturl(String url) {
        this.url = url;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof FuncAuthBean)) {
            return false;
        }
        FuncAuthBean rhs = (FuncAuthBean) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.funcId, rhs.funcId).append(this.url, rhs.url)
            .append(this.funcName, rhs.funcName).append(this.funcParentId, rhs.funcParentId)
            .append(this.type, rhs.type).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(267697751, 876502947).appendSuper(super.hashCode())
            .append(this.funcId).append(this.url).append(this.funcName).append(this.funcParentId)
            .append(this.type).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("url", this.url).append("type", this.type)
            .append("funcName", this.funcName).append("menuId", this.funcParentId)
            .append("funcId", this.funcId).toString();
    }
    
    
    
}
