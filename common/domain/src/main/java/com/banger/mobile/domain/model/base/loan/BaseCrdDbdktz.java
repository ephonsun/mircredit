package com.banger.mobile.domain.model.base.loan;

import com.banger.mobile.domain.model.base.BaseObject;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author zhangfp
 * @version $Id: BaseCrdDbdktz v 0.1 ${} 上午10:26 zhangfp Exp $
 */
public class BaseCrdDbdktz extends BaseObject implements Serializable {
    private String djserno;
    private String pk1;
    private String jjbh;
    private String dkzh;     //贷款账号
    private String rq;
    private String jgm;
    private String khm;
    private String khmc;
    private String htbh;
    private String zwhtbh;
    private String qdrq;
    private String zdrq;
    private BigDecimal dkje;  //贷款金额
    private BigDecimal dkye;  //贷款余额
    private String jszh;
    private String zt;
    private String zrr;

    private Integer nf;
    private Integer yf;
    private String jbjgm;
    private String yzdrq;
    private String kmh;
    private Integer bz;
    private String sjfl;
    private BigDecimal zcdkye;
    private BigDecimal yqdkye;
    private BigDecimal dzdkye;
    private BigDecimal dzhangdkye;
    private String wjfl;
    private String qfrq;
    private BigDecimal yslxlj;
    private BigDecimal sslxlj;
    private BigDecimal qxlj;
    private BigDecimal bnqx;
    private BigDecimal bwqx;
    private String  zjhkrq;
    private String  scfkrq;
    private String  bjyqqsrq;
    private String  lxyqqsrq;
    private String  dkyqyy;
    private String  jxfs;
    private String  hkfs;
    private String  hkzq;
    private String  jqrq;
    private String  xhrq;
    private BigDecimal zyzc;
    private String zylx;
    private String zblbz;
    private String sfbldk;
    private Integer yqqs;
    private Integer lxyqqs;
    private String djr;
    private String zrr2;
    private String xgrq;
    private Integer zqcs;
    private BigDecimal jzll;
    private BigDecimal fdll;
    private BigDecimal ll;
    private BigDecimal yqll;
    private BigDecimal wyll;
    private String shjfl;

    public String getDjserno() {
        return djserno;
    }

    public void setDjserno(String djserno) {
        this.djserno = djserno;
    }

    public Integer getNf() {
        return nf;
    }

    public void setNf(Integer nf) {
        this.nf = nf;
    }

    public Integer getYf() {
        return yf;
    }

    public void setYf(Integer yf) {
        this.yf = yf;
    }

    public String getJbjgm() {
        return jbjgm;
    }

    public void setJbjgm(String jbjgm) {
        this.jbjgm = jbjgm;
    }

    public String getYzdrq() {
        return yzdrq;
    }

    public void setYzdrq(String yzdrq) {
        this.yzdrq = yzdrq;
    }

    public String getKmh() {
        return kmh;
    }

    public void setKmh(String kmh) {
        this.kmh = kmh;
    }

    public Integer getBz() {
        return bz;
    }

    public void setBz(Integer bz) {
        this.bz = bz;
    }

    public String getSjfl() {
        return sjfl;
    }

    public void setSjfl(String sjfl) {
        this.sjfl = sjfl;
    }

    public BigDecimal getZcdkye() {
        return zcdkye;
    }

    public void setZcdkye(BigDecimal zcdkye) {
        this.zcdkye = zcdkye;
    }

    public BigDecimal getYqdkye() {
        return yqdkye;
    }

    public void setYqdkye(BigDecimal yqdkye) {
        this.yqdkye = yqdkye;
    }

    public BigDecimal getDzdkye() {
        return dzdkye;
    }

    public void setDzdkye(BigDecimal dzdkye) {
        this.dzdkye = dzdkye;
    }

    public BigDecimal getDzhangdkye() {
        return dzhangdkye;
    }

    public void setDzhangdkye(BigDecimal dzhangdkye) {
        this.dzhangdkye = dzhangdkye;
    }

    public String getWjfl() {
        return wjfl;
    }

    public void setWjfl(String wjfl) {
        this.wjfl = wjfl;
    }

    public String getQfrq() {
        return qfrq;
    }

    public void setQfrq(String qfrq) {
        this.qfrq = qfrq;
    }

    public BigDecimal getYslxlj() {
        return yslxlj;
    }

    public void setYslxlj(BigDecimal yslxlj) {
        this.yslxlj = yslxlj;
    }

    public BigDecimal getSslxlj() {
        return sslxlj;
    }

    public void setSslxlj(BigDecimal sslxlj) {
        this.sslxlj = sslxlj;
    }

    public BigDecimal getQxlj() {
        return qxlj;
    }

    public void setQxlj(BigDecimal qxlj) {
        this.qxlj = qxlj;
    }

    public BigDecimal getBnqx() {
        return bnqx;
    }

    public void setBnqx(BigDecimal bnqx) {
        this.bnqx = bnqx;
    }

    public BigDecimal getBwqx() {
        return bwqx;
    }

    public void setBwqx(BigDecimal bwqx) {
        this.bwqx = bwqx;
    }

    public String getZjhkrq() {
        return zjhkrq;
    }

    public void setZjhkrq(String zjhkrq) {
        this.zjhkrq = zjhkrq;
    }

    public String getScfkrq() {
        return scfkrq;
    }

    public void setScfkrq(String scfkrq) {
        this.scfkrq = scfkrq;
    }

    public String getBjyqqsrq() {
        return bjyqqsrq;
    }

    public void setBjyqqsrq(String bjyqqsrq) {
        this.bjyqqsrq = bjyqqsrq;
    }

    public String getLxyqqsrq() {
        return lxyqqsrq;
    }

    public void setLxyqqsrq(String lxyqqsrq) {
        this.lxyqqsrq = lxyqqsrq;
    }

    public String getDkyqyy() {
        return dkyqyy;
    }

    public void setDkyqyy(String dkyqyy) {
        this.dkyqyy = dkyqyy;
    }

    public String getJxfs() {
        return jxfs;
    }

    public void setJxfs(String jxfs) {
        this.jxfs = jxfs;
    }

    public String getHkfs() {
        return hkfs;
    }

    public void setHkfs(String hkfs) {
        this.hkfs = hkfs;
    }

    public String getHkzq() {
        return hkzq;
    }

    public void setHkzq(String hkzq) {
        this.hkzq = hkzq;
    }

    public String getJqrq() {
        return jqrq;
    }

    public void setJqrq(String jqrq) {
        this.jqrq = jqrq;
    }

    public String getXhrq() {
        return xhrq;
    }

    public void setXhrq(String xhrq) {
        this.xhrq = xhrq;
    }

    public BigDecimal getZyzc() {
        return zyzc;
    }

    public void setZyzc(BigDecimal zyzc) {
        this.zyzc = zyzc;
    }

    public String getZylx() {
        return zylx;
    }

    public void setZylx(String zylx) {
        this.zylx = zylx;
    }

    public String getZblbz() {
        return zblbz;
    }

    public void setZblbz(String zblbz) {
        this.zblbz = zblbz;
    }

    public String getSfbldk() {
        return sfbldk;
    }

    public void setSfbldk(String sfbldk) {
        this.sfbldk = sfbldk;
    }

    public Integer getYqqs() {
        return yqqs;
    }

    public void setYqqs(Integer yqqs) {
        this.yqqs = yqqs;
    }

    public Integer getLxyqqs() {
        return lxyqqs;
    }

    public void setLxyqqs(Integer lxyqqs) {
        this.lxyqqs = lxyqqs;
    }

    public String getDjr() {
        return djr;
    }

    public void setDjr(String djr) {
        this.djr = djr;
    }

    public String getZrr2() {
        return zrr2;
    }

    public void setZrr2(String zrr2) {
        this.zrr2 = zrr2;
    }

    public String getXgrq() {
        return xgrq;
    }

    public void setXgrq(String xgrq) {
        this.xgrq = xgrq;
    }

    public Integer getZqcs() {
        return zqcs;
    }

    public void setZqcs(Integer zqcs) {
        this.zqcs = zqcs;
    }

    public BigDecimal getJzll() {
        return jzll;
    }

    public void setJzll(BigDecimal jzll) {
        this.jzll = jzll;
    }

    public BigDecimal getFdll() {
        return fdll;
    }

    public void setFdll(BigDecimal fdll) {
        this.fdll = fdll;
    }

    public BigDecimal getLl() {
        return ll;
    }

    public void setLl(BigDecimal ll) {
        this.ll = ll;
    }

    public BigDecimal getYqll() {
        return yqll;
    }

    public void setYqll(BigDecimal yqll) {
        this.yqll = yqll;
    }

    public BigDecimal getWyll() {
        return wyll;
    }

    public void setWyll(BigDecimal wyll) {
        this.wyll = wyll;
    }

    public String getShjfl() {
        return shjfl;
    }

    public void setShjfl(String shjfl) {
        this.shjfl = shjfl;
    }
//    public String getDjserno() {
//        return djserno;
//    }
//
//    public void setDjserno(String djserno) {
//        this.djserno = djserno;
//    }

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

    public String getDkzh() {
        return dkzh;
    }

    public void setDkzh(String dkzh) {
        this.dkzh = dkzh;
    }

    public String getRq() {
        return rq;
    }

    public void setRq(String rq) {
        this.rq = rq;
    }

    public String getJgm() {
        return jgm;
    }

    public void setJgm(String jgm) {
        this.jgm = jgm;
    }

    public String getKhm() {
        return khm;
    }

    public void setKhm(String khm) {
        this.khm = khm;
    }

    public String getKhmc() {
        return khmc;
    }

    public void setKhmc(String khmc) {
        this.khmc = khmc;
    }

    public String getHtbh() {
        return htbh;
    }

    public void setHtbh(String htbh) {
        this.htbh = htbh;
    }

    public String getZwhtbh() {
        return zwhtbh;
    }

    public void setZwhtbh(String zwhtbh) {
        this.zwhtbh = zwhtbh;
    }

    public String getQdrq() {
        return qdrq;
    }

    public void setQdrq(String qdrq) {
        this.qdrq = qdrq;
    }

    public String getZdrq() {
        return zdrq;
    }

    public void setZdrq(String zdrq) {
        this.zdrq = zdrq;
    }

    public BigDecimal getDkje() {
        return dkje;
    }

    public void setDkje(BigDecimal dkje) {
        this.dkje = dkje;
    }

    public BigDecimal getDkye() {
        return dkye;
    }

    public void setDkye(BigDecimal dkye) {
        this.dkye = dkye;
    }

    public String getJszh() {
        return jszh;
    }

    public void setJszh(String jszh) {
        this.jszh = jszh;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getZrr() {
        return zrr;
    }

    public void setZrr(String zrr) {
        this.zrr = zrr;
    }
}
