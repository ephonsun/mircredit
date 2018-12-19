package com.banger.mobile.domain.model.customer;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 导入输出参数bean
 * @author yangyang
 * @version $Id: CrmCustomerImport.java,v 0.1 2012-6-8 下午4:53:19 yangyong Exp $
 */
public class CounterOutPintMessage implements Comparable, java.io.Serializable {

    private static final long serialVersionUID = -8039770175454254327L;

    private Integer            sumRecord;                               //总条数

    private Integer           successRecordCount;                      //导入成功条数

    private Integer           failRecordCount;                         //导入失败条数

    private String            errorFilePath;                           //导入失败路径

    private Integer           isNullRecord;                            //空行记录    

    public CounterOutPintMessage() {
        super();
    }

    public Integer getSumRecord() {
        return sumRecord;
    }

    public void setSumRecord(Integer sumRecord) {
        this.sumRecord = sumRecord;
    }

    public Integer getSuccessRecordCount() {
        return successRecordCount;
    }

    public void setSuccessRecordCount(Integer successRecordCount) {
        this.successRecordCount = successRecordCount;
    }

    public Integer getFailRecordCount() {
        return failRecordCount;
    }

    public void setFailRecordCount(Integer failRecordCount) {
        this.failRecordCount = failRecordCount;
    }

    public String getErrorFilePath() {
        return errorFilePath;
    }

    public void setErrorFilePath(String errorFilePath) {
        this.errorFilePath = errorFilePath;
    }

    public Integer getIsNullRecord() {
        return isNullRecord;
    }

    public void setIsNullRecord(Integer isNullRecord) {
        this.isNullRecord = isNullRecord;
    }

    /**
     * @see java.lang.Comparable#compareTo(Object)
     */
    public int compareTo(Object object) {
        CounterOutPintMessage myClass = (CounterOutPintMessage) object;
        return new CompareToBuilder().append(this.errorFilePath, myClass.errorFilePath)
            .append(this.successRecordCount, myClass.successRecordCount)
            .append(this.isNullRecord, myClass.isNullRecord)
            .append(this.failRecordCount, myClass.failRecordCount)
            .append(this.sumRecord, myClass.sumRecord).toComparison();
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof CounterOutPintMessage)) {
            return false;
        }
        CounterOutPintMessage rhs = (CounterOutPintMessage) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.errorFilePath, rhs.errorFilePath)
            .append(this.successRecordCount, rhs.successRecordCount)
            .append(this.isNullRecord, rhs.isNullRecord)
            .append(this.failRecordCount, rhs.failRecordCount)
            .append(this.sumRecord, rhs.sumRecord).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1868093001, -1358187969).appendSuper(super.hashCode())
            .append(this.errorFilePath).append(this.successRecordCount).append(this.isNullRecord)
            .append(this.failRecordCount).append(this.sumRecord).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("isNullRecord", this.isNullRecord)
            .append("sumRecord", this.sumRecord).append("failRecordCount", this.failRecordCount)
            .append("successRecordCount", this.successRecordCount)
            .append("errorFilePath", this.errorFilePath).toString();
    }

}
