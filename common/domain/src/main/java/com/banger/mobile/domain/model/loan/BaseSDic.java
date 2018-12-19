package com.banger.mobile.domain.model.loan;

import com.banger.mobile.domain.model.base.BaseObject;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author zhangfp
 * @version $Id: BaseSDic v 0.1 ${} 下午2:18 zhangfp Exp $
 */
public class BaseSDic extends BaseObject implements Serializable {

    private String pk1;
    private String cnname;
    private String enname;
    private String type1;
    private String property;
    private String memo;
    private String flag;

    public String getPk1() {
        return pk1;
    }

    public void setPk1(String pk1) {
        this.pk1 = pk1;
    }

    public String getCnname() {
        return cnname;
    }

    public void setCnname(String cnname) {
        this.cnname = cnname;
    }

    public String getEnname() {
        return enname;
    }

    public void setEnname(String enname) {
        this.enname = enname;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
