package com.banger.mobile.domain.model.base.loan;

import com.banger.mobile.domain.model.base.BaseObject;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author zhangfp
 * @version $Id: BaseCrdTzHkjh v 0.1 ${} 上午10:33 zhangfp Exp $
 */
public class BaseCrdTzHkjh extends BaseObject implements Serializable {
    private String pk1;
    private String jjbh;
    private String htbh;
    private String zh;
    private Integer hkqs;
    private String hkrq;
    private String sjhkrq;
    private BigDecimal ll;
    private BigDecimal jhje;
    private BigDecimal jhlx;
    private BigDecimal sjje;
    private BigDecimal sjlx;
    private BigDecimal qbx;
    private BigDecimal dkye;
    private String jgm;

    private Integer loanId;

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public String getPk1() {
        return pk1;
    }

    public void setPk1(String pk1) {
        this.pk1 = pk1;
    }

    public String getJjbh() {
        return jjbh;
    }

    public void setJjbh(String jjbh) {
        this.jjbh = jjbh;
    }

    public String getHtbh() {
        return htbh;
    }

    public void setHtbh(String htbh) {
        this.htbh = htbh;
    }

    public String getZh() {
        return zh;
    }

    public void setZh(String zh) {
        this.zh = zh;
    }

    public Integer getHkqs() {
        return hkqs;
    }

    public void setHkqs(Integer hkqs) {
        this.hkqs = hkqs;
    }

    public String getHkrq() {
        return hkrq;
    }

    public void setHkrq(String hkrq) {
        this.hkrq = hkrq;
    }

    public String getSjhkrq() {
        return sjhkrq;
    }

    public void setSjhkrq(String sjhkrq) {
        this.sjhkrq = sjhkrq;
    }

    public BigDecimal getLl() {
        return ll;
    }

    public void setLl(BigDecimal ll) {
        this.ll = ll;
    }

    public BigDecimal getJhje() {
        return jhje;
    }

    public void setJhje(BigDecimal jhje) {
        this.jhje = jhje;
    }

    public BigDecimal getJhlx() {
        return jhlx;
    }

    public void setJhlx(BigDecimal jhlx) {
        this.jhlx = jhlx;
    }

    public BigDecimal getSjje() {
        return sjje;
    }

    public void setSjje(BigDecimal sjje) {
        this.sjje = sjje;
    }

    public BigDecimal getSjlx() {
        return sjlx;
    }

    public void setSjlx(BigDecimal sjlx) {
        this.sjlx = sjlx;
    }

    public BigDecimal getQbx() {
        return qbx;
    }

    public void setQbx(BigDecimal qbx) {
        this.qbx = qbx;
    }

    public BigDecimal getDkye() {
        return dkye;
    }

    public void setDkye(BigDecimal dkye) {
        this.dkye = dkye;
    }

    public String getJgm() {
        return jgm;
    }

    public void setJgm(String jgm) {
        this.jgm = jgm;
    }
}
