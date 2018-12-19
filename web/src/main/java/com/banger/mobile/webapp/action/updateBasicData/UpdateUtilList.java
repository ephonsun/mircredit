/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :Administrator
 * Create Date:Sep 15, 2012
 */
package com.banger.mobile.webapp.action.updateBasicData;

import java.util.List;

import com.banger.mobile.domain.model.base.BaseObject;

/**
 * @author yujh
 * @version $Id: UpdateUtilList.java,v 0.1 Sep 15, 2012 5:06:32 PM Administrator Exp $
 */
public class UpdateUtilList extends BaseObject implements java.io.Serializable{
    private static final long serialVersionUID = 1954817109795384907L;
    private List<String>  fileNameList;
    private Integer       count=0;//当前进度
    private Integer       a;      
    private Integer       b;
    private Integer       c;
    private Integer       d;
    public List<String> getFileNameList() {
        return fileNameList;
    }
    public void setFileNameList(List<String> fileNameList) {
        this.fileNameList = fileNameList;
    }
    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
    public Integer getA() {
        return a;
    }
    public void setA(Integer a) {
        this.a = a;
    }
    public Integer getB() {
        return b;
    }
    public void setB(Integer b) {
        this.b = b;
    }
    public Integer getC() {
        return c;
    }
    public void setC(Integer c) {
        this.c = c;
    }
    public Integer getD() {
        return d;
    }
    public void setD(Integer d) {
        this.d = d;
    }
        

}
