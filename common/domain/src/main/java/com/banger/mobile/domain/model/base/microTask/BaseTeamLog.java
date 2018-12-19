package com.banger.mobile.domain.model.base.microTask;

import com.banger.mobile.domain.model.base.BaseObject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by BH-TCL on 15-4-13.
 */
public class BaseTeamLog extends BaseObject implements Serializable {

    private Integer teamLogId;//主键 同时在统计的时候此变量被用于存储一共已填写日志的人数
    private Integer userId;//用户id
    private Integer zrzjdhyx;//昨日总结电话营销  在统计时被用于sum（zrzjdhyx） 下面类似
    private Integer zrzjsdyx;//昨日总结实地营销
    private Integer zrzjjdzx;//昨日总结接待咨询
    private Integer zrzjjdsq;//昨日总结接待申请
    private Integer zrzjdcbs;//昨日总结调查笔数
    private Integer zrzjfdbs;//昨日总结放贷笔数
    private Integer zrzjdhbs;//昨日总结贷后笔数
    private Integer zrzjshbs;//昨日总结上会笔数
    private Integer jrjhdhyx;//今日计划电话营销
    private Integer jrjhsdyx;//今日计划实地营销
    private Integer jrjhjdzc;//今日计划接待咨询
    private Integer jrjhjdsq;//今日计划接待申请
    private Integer jrjhdcbs;//今日计划调查笔数
    private Integer jrjhfdbs;//今日计划放贷笔数
    private Integer jrjhdhbs;//今日计划贷后笔数
    private Integer jrjhshbs;//今日计划上会笔数
    private Integer dclbs;//待处理笔数
    private Integer byxzbs;//本月新增笔数
    private Integer byzdbs;//本月转贷笔数
    private Integer bydjbs;//本月叠加笔数
    private Integer byljtgbs;//本月累计通过笔数
    private Integer bydqbs;//本月到期笔数
    private Integer byjzhsrwzb;//本月净增户数任务指标
    private Integer byjzyerwzb;//本月净增余额任务指标
    private Integer bytqjqbs;//本月提前结清笔数
    private BigDecimal bytqjqje;//本月提前结清金额
    private BigDecimal bytgwffje;//本月通过未发放金额
    private BigDecimal yjydfkje;//预计至月底放款金额
    private BigDecimal yjydjzje;//预计至月底净增金额
    private Integer byfkbs;//本月放款笔数
    private BigDecimal byfkjqll;//本月放款加权利率
    private Integer byyyehs;//本月有余额户数
    private Integer syyyehs;//上月有余额户数
    private Integer byjzhs;//本月净增户数
    private BigDecimal byfkje;//本月放款金额
    private BigDecimal bydkye;//本月贷款金额
    private BigDecimal sydkye;//上月贷款余额
    private BigDecimal byjzye;//本月净增余额
    private Date createDate;//
    private Date updateDate;//
    private Integer createUser;//
    private Integer updateUser;//更新用户

    public Integer getTeamLogId() {
        return teamLogId;
    }

    public void setTeamLogId(Integer teamLogId) {
        this.teamLogId = teamLogId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getZrzjdhyx() {
        return zrzjdhyx;
    }

    public void setZrzjdhyx(Integer zrzjdhyx) {
        this.zrzjdhyx = zrzjdhyx;
    }

    public Integer getZrzjsdyx() {
        return zrzjsdyx;
    }

    public void setZrzjsdyx(Integer zrzjsdyx) {
        this.zrzjsdyx = zrzjsdyx;
    }

    public Integer getZrzjjdzx() {
        return zrzjjdzx;
    }

    public void setZrzjjdzx(Integer zrzjjdzx) {
        this.zrzjjdzx = zrzjjdzx;
    }

    public Integer getZrzjjdsq() {
        return zrzjjdsq;
    }

    public void setZrzjjdsq(Integer zrzjjdsq) {
        this.zrzjjdsq = zrzjjdsq;
    }

    public Integer getZrzjdcbs() {
        return zrzjdcbs;
    }

    public void setZrzjdcbs(Integer zrzjdcbs) {
        this.zrzjdcbs = zrzjdcbs;
    }

    public Integer getZrzjfdbs() {
        return zrzjfdbs;
    }

    public void setZrzjfdbs(Integer zrzjfdbs) {
        this.zrzjfdbs = zrzjfdbs;
    }

    public Integer getZrzjdhbs() {
        return zrzjdhbs;
    }

    public void setZrzjdhbs(Integer zrzjdhbs) {
        this.zrzjdhbs = zrzjdhbs;
    }

    public Integer getZrzjshbs() {
        return zrzjshbs;
    }

    public void setZrzjshbs(Integer zrzjshbs) {
        this.zrzjshbs = zrzjshbs;
    }

    public Integer getJrjhdhyx() {
        return jrjhdhyx;
    }

    public void setJrjhdhyx(Integer jrjhdhyx) {
        this.jrjhdhyx = jrjhdhyx;
    }

    public Integer getJrjhsdyx() {
        return jrjhsdyx;
    }

    public void setJrjhsdyx(Integer jrjhsdyx) {
        this.jrjhsdyx = jrjhsdyx;
    }

    public Integer getJrjhjdzc() {
        return jrjhjdzc;
    }

    public void setJrjhjdzc(Integer jrjhjdzc) {
        this.jrjhjdzc = jrjhjdzc;
    }

    public Integer getJrjhjdsq() {
        return jrjhjdsq;
    }

    public void setJrjhjdsq(Integer jrjhjdsq) {
        this.jrjhjdsq = jrjhjdsq;
    }

    public Integer getJrjhdcbs() {
        return jrjhdcbs;
    }

    public void setJrjhdcbs(Integer jrjhdcbs) {
        this.jrjhdcbs = jrjhdcbs;
    }

    public Integer getJrjhfdbs() {
        return jrjhfdbs;
    }

    public void setJrjhfdbs(Integer jrjhfdbs) {
        this.jrjhfdbs = jrjhfdbs;
    }

    public Integer getJrjhdhbs() {
        return jrjhdhbs;
    }

    public void setJrjhdhbs(Integer jrjhdhbs) {
        this.jrjhdhbs = jrjhdhbs;
    }

    public Integer getJrjhshbs() {
        return jrjhshbs;
    }

    public void setJrjhshbs(Integer jrjhshbs) {
        this.jrjhshbs = jrjhshbs;
    }

    public Integer getDclbs() {
        return dclbs;
    }

    public void setDclbs(Integer dclbs) {
        this.dclbs = dclbs;
    }

    public Integer getByxzbs() {
        return byxzbs;
    }

    public void setByxzbs(Integer byxzbs) {
        this.byxzbs = byxzbs;
    }

    public Integer getByzdbs() {
        return byzdbs;
    }

    public void setByzdbs(Integer byzdbs) {
        this.byzdbs = byzdbs;
    }

    public Integer getBydjbs() {
        return bydjbs;
    }

    public void setBydjbs(Integer bydjbs) {
        this.bydjbs = bydjbs;
    }

    public Integer getByljtgbs() {
        return byljtgbs;
    }

    public void setByljtgbs(Integer byljtgbs) {
        this.byljtgbs = byljtgbs;
    }

    public Integer getBydqbs() {
        return bydqbs;
    }

    public void setBydqbs(Integer bydqbs) {
        this.bydqbs = bydqbs;
    }

    public Integer getByjzhsrwzb() {
        return byjzhsrwzb;
    }

    public void setByjzhsrwzb(Integer byjzhsrwzb) {
        this.byjzhsrwzb = byjzhsrwzb;
    }

    public Integer getByjzyerwzb() {
        return byjzyerwzb;
    }

    public void setByjzyerwzb(Integer byjzyerwzb) {
        this.byjzyerwzb = byjzyerwzb;
    }

    public Integer getBytqjqbs() {
        return bytqjqbs;
    }

    public void setBytqjqbs(Integer bytqjqbs) {
        this.bytqjqbs = bytqjqbs;
    }

    public BigDecimal getBytqjqje() {
        return bytqjqje;
    }

    public void setBytqjqje(BigDecimal bytqjqje) {
        this.bytqjqje = bytqjqje;
    }

    public BigDecimal getBytgwffje() {
        return bytgwffje;
    }

    public void setBytgwffje(BigDecimal bytgwffje) {
        this.bytgwffje = bytgwffje;
    }

    public BigDecimal getYjydfkje() {
        return yjydfkje;
    }

    public void setYjydfkje(BigDecimal yjydfkje) {
        this.yjydfkje = yjydfkje;
    }

    public BigDecimal getYjydjzje() {
        return yjydjzje;
    }

    public void setYjydjzje(BigDecimal yjydjzje) {
        this.yjydjzje = yjydjzje;
    }

    public Integer getByfkbs() {
        return byfkbs;
    }

    public void setByfkbs(Integer byfkbs) {
        this.byfkbs = byfkbs;
    }

    public BigDecimal getByfkjqll() {
        return byfkjqll;
    }

    public void setByfkjqll(BigDecimal byfkjqll) {
        this.byfkjqll = byfkjqll;
    }

    public Integer getByyyehs() {
        return byyyehs;
    }

    public void setByyyehs(Integer byyyehs) {
        this.byyyehs = byyyehs;
    }

    public Integer getSyyyehs() {
        return syyyehs;
    }

    public void setSyyyehs(Integer syyyehs) {
        this.syyyehs = syyyehs;
    }

    public Integer getByjzhs() {
        return byjzhs;
    }

    public void setByjzhs(Integer byjzhs) {
        this.byjzhs = byjzhs;
    }

    public BigDecimal getByfkje() {
        return byfkje;
    }

    public void setByfkje(BigDecimal byfkje) {
        this.byfkje = byfkje;
    }

    public BigDecimal getBydkye() {
        return bydkye;
    }

    public void setBydkye(BigDecimal bydkye) {
        this.bydkye = bydkye;
    }

    public BigDecimal getSydkye() {
        return sydkye;
    }

    public void setSydkye(BigDecimal sydkye) {
        this.sydkye = sydkye;
    }

    public BigDecimal getByjzye() {
        return byjzye;
    }

    public void setByjzye(BigDecimal byjzye) {
        this.byjzye = byjzye;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }
}
