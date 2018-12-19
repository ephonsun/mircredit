/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-8-30
 */
package com.banger.mobile.domain.model.customer;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author cheny
 * @version $Id: CustomerExportBar.java,v 0.1 2012-8-30 下午4:16:58 cheny Exp $
 */
public class CustomerExportBar extends BaseObject implements java.io.Serializable{

    private static final long serialVersionUID = -9054687548231348384L;
    
    private Integer totalAmout;   //总数
    private Integer cuurRow=0;      //当前记录数
    private Integer isRun=0;        //是否在运行 
    private Integer isStop;        // 1 终止    0不终止
    /**
     * 
     */
    public CustomerExportBar() {
        super();
    }
    /**
     * @param totalAmout
     * @param cuurRow
     * @param isRun
     * @param isStop
     */
    public CustomerExportBar(Integer totalAmout, Integer cuurRow, Integer isRun, Integer isStop) {
        super();
        this.totalAmout = totalAmout;
        this.cuurRow = cuurRow;
        this.isRun = isRun;
        this.isStop = isStop;
    }
    public Integer getTotalAmout() {
        return totalAmout;
    }
    public void setTotalAmout(Integer totalAmout) {
        this.totalAmout = totalAmout;
    }
    public Integer getCuurRow() {
        return cuurRow;
    }
    public void setCuurRow(Integer cuurRow) {
        this.cuurRow = cuurRow;
    }
    public Integer getIsRun() {
        return isRun;
    }
    public void setIsRun(Integer isRun) {
        this.isRun = isRun;
    }
    public Integer getIsStop() {
        return isStop;
    }
    public void setIsStop(Integer isStop) {
        this.isStop = isStop;
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof CustomerExportBar)) {
            return false;
        }
        CustomerExportBar rhs = (CustomerExportBar) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.isRun, rhs.isRun)
            .append(this.cuurRow, rhs.cuurRow).append(this.totalAmout, rhs.totalAmout)
            .append(this.isStop, rhs.isStop).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1142079393, 1329098483).appendSuper(super.hashCode())
            .append(this.isRun).append(this.cuurRow).append(this.totalAmout).append(this.isStop)
            .toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("isStop", this.isStop)
            .append("totalAmout", this.totalAmout).append("cuurRow", this.cuurRow)
            .append("endRow", this.getEndRow()).append("isRun", this.isRun)
            .append("startRow", this.getStartRow()).toString();
    }
    
    
    
    
  
   
    
}
