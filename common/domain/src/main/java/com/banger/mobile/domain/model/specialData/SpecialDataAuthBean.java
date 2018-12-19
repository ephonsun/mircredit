/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :数据权限树实体
 * Author     :yangy
 * Create Date:2012-5-31
 */
package com.banger.mobile.domain.model.specialData;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @author yangy
 * @version $Id: FuncAuthBean.java,v 0.1 2012-5-31 下午1:04:20 yangy Exp $
 */
public class SpecialDataAuthBean implements Serializable{

    private static final long serialVersionUID = 220364774895509381L;

    private Integer dataId;       //数据id
    private Integer dataParentId;       //数据父id
    private String dataName;      //功能名称
    private String type;          //功能类型  FUNC功能  MENU菜单
    private String dataCode;  //数据编号

    public String getDataCode() {
        return dataCode;
    }

    public void setDataCode(String dataCode) {
        this.dataCode = dataCode;
    }

    /**
     *
     */
    public SpecialDataAuthBean() {
        super();
    }

    public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

    public Integer getDataParentId() {
        return dataParentId;
    }

    public void setDataParentId(Integer dataParentId) {
        this.dataParentId = dataParentId;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpecialDataAuthBean that = (SpecialDataAuthBean) o;

        if (dataId != null ? !dataId.equals(that.dataId) : that.dataId != null) return false;
        if (dataName != null ? !dataName.equals(that.dataName) : that.dataName != null) return false;
        if (dataParentId != null ? !dataParentId.equals(that.dataParentId) : that.dataParentId != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    public int hashCode() {
        int result = dataId != null ? dataId.hashCode() : 0;
        result = 31 * result + (dataParentId != null ? dataParentId.hashCode() : 0);
        result = 31 * result + (dataName != null ? dataName.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    public String toString() {
        return "SpecialDataAuthBean{" +
                "dataId=" + dataId +
                ", dataParentId=" + dataParentId +
                ", dataName='" + dataName + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
