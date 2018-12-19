/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :产品及类型树Bean
 * Author     :liyb
 * Create Date:2013-1-5
 */
package com.banger.mobile.domain.model.report;

import java.io.Serializable;

/**
 * @author liyb
 * @version $Id: ProductTypeTreeBean.java,v 0.1 2013-1-5 下午12:46:09 liyb Exp $
 */
public class ProductTypeTreeBean implements Serializable {

    private static final long serialVersionUID = 5717714077353474462L;

    private Integer           id;                                      //父ID
    private Integer           parentId;                                //子ID
    private String            name;                                    //名称
    private String            code;                                    //编号
    private String            type;                                    //类型 'D:产品类型  U:产品信息'
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getParentId() {
        return parentId;
    }
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
