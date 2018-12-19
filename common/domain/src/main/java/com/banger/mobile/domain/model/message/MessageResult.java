package com.banger.mobile.domain.model.message;

import java.io.Serializable;

/**
 * Created by thinkpad on 2014/10/11.
 */
public class MessageResult implements Serializable {
    private String djSerno;
    private String serno;
    private String caseNo;
    private String requestResult;
    private String failReason;
    private String hcjg;//身份核查结果
    private String qfjg;//签发机构
    private String hcrq;//核查日期
    private String hclsh;//核查流水号
    private String hctp;//核查图片
    private String errormsg;//错误信息
    private Integer isError;//报文是否报错

    public static final Integer ERROR=1;//报文报错

    public String getErrormsg() {
        return errormsg;
    }

    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }

    public Integer getIsError() {
        return isError;
    }

    public void setIsError(Integer isError) {
        this.isError = isError;
    }

    public String getHctp() {
        return hctp;
    }

    public void setHctp(String hctp) {
        this.hctp = hctp;
    }

    public String getHcjg() {
        return hcjg;
    }

    public void setHcjg(String hcjg) {
        this.hcjg = hcjg;
    }

    public String getQfjg() {
        return qfjg;
    }

    public void setQfjg(String qfjg) {
        this.qfjg = qfjg;
    }

    public String getHcrq() {
        return hcrq;
    }

    public void setHcrq(String hcrq) {
        this.hcrq = hcrq;
    }

    public String getHclsh() {
        return hclsh;
    }

    public void setHclsh(String hclsh) {
        this.hclsh = hclsh;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public String getFailReason() {
        return failReason;
    }

    public String getRequestResult() {
        return requestResult;
    }

    public void setRequestResult(String requestResult) {
        this.requestResult = requestResult;
    }

    public void setSerno(String serno) {
        this.serno = serno;
    }

    public String getSerno() {
        return serno;
    }

    public String getDjSerno() {
        return djSerno;
    }

    public void setDjSerno(String djSerno) {
        this.djSerno = djSerno;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

}
