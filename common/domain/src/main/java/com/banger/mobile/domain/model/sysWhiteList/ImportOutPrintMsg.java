/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :导入白名单输出信息
 * Author     :yujh
 * Create Date:Sep 7, 2012
 */
package com.banger.mobile.domain.model.sysWhiteList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author yujh
 * @version $Id: ImportOutPrintMsg.java,v 0.1 Sep 7, 2012 6:18:11 PM Administrator Exp $
 */
public class ImportOutPrintMsg implements Comparable ,Serializable{

    private static final long serialVersionUID = -3638002242837900660L;
    
    private String accountFileName;     //文件名
    private String sumRecord;
    private Integer isAdd;              //是否保存
    private String importSuccessCount;  //导入成功条数
    private String importFailCount;     //导入失败条数
    private String filePath;            //导入失败路径
    private List<String>   failDescriptionListu=new ArrayList<String>(); //导入失败明细（订单号，错误原因）
    
    public String getAccountFileName() {
        return accountFileName;
    }

    public void setAccountFileName(String accountFileName) {
        this.accountFileName = accountFileName;
    }

    public String getSumRecord() {
        return sumRecord;
    }

    public void setSumRecord(String sumRecord) {
        this.sumRecord = sumRecord;
    }

    public Integer getIsAdd() {
        return isAdd;
    }

    public void setIsAdd(Integer isAdd) {
        this.isAdd = isAdd;
    }

    public String getImportSuccessCount() {
        return importSuccessCount;
    }

    public void setImportSuccessCount(String importSuccessCount) {
        this.importSuccessCount = importSuccessCount;
    }

    public String getImportFailCount() {
        return importFailCount;
    }

    public void setImportFailCount(String importFailCount) {
        this.importFailCount = importFailCount;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public List<String> getFailDescriptionListu() {
        return failDescriptionListu;
    }

    public void setFailDescriptionListu(List<String> failDescriptionListu) {
        this.failDescriptionListu = failDescriptionListu;
    }

    /**
     * @see java.lang.Comparable#compareTo(Object)
     */
    public int compareTo(Object object) {
        ImportOutPrintMsg myClass = (ImportOutPrintMsg) object;
        return new CompareToBuilder().append(this.sumRecord, myClass.sumRecord).append(
            this.failDescriptionListu, myClass.failDescriptionListu).append(
            this.importSuccessCount, myClass.importSuccessCount).append(this.accountFileName,
            myClass.accountFileName).append(this.filePath, myClass.filePath).append(this.isAdd,
            myClass.isAdd).append(this.importFailCount, myClass.importFailCount).toComparison();
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof ImportOutPrintMsg)) {
            return false;
        }
        ImportOutPrintMsg rhs = (ImportOutPrintMsg) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.sumRecord,
            rhs.sumRecord).append(this.failDescriptionListu, rhs.failDescriptionListu).append(
            this.importSuccessCount, rhs.importSuccessCount).append(this.accountFileName,
            rhs.accountFileName).append(this.filePath, rhs.filePath).append(this.isAdd, rhs.isAdd)
            .append(this.importFailCount, rhs.importFailCount).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-366268567, -640036453).appendSuper(super.hashCode()).append(
            this.sumRecord).append(this.failDescriptionListu).append(this.importSuccessCount)
            .append(this.accountFileName).append(this.filePath).append(this.isAdd).append(
                this.importFailCount).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("filePath", this.filePath).append(
            "importSuccessCount", this.importSuccessCount).append("isAdd", this.isAdd).append(
            "accountFileName", this.accountFileName)
            .append("importFailCount", this.importFailCount).append("failDescriptionListu",
                this.failDescriptionListu).append("sumRecord", this.sumRecord).toString();
    }
    
}
