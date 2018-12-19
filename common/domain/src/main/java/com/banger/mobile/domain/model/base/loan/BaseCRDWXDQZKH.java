package com.banger.mobile.domain.model.base.loan;

import com.banger.mobile.domain.model.base.BaseObject;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author zhangfp
 * @version $Id: BaseCRDWXDQZKH v 0.1 ${} 上午9:01 zhangfp Exp $
 */
public class BaseCRDWXDQZKH  extends BaseObject implements Serializable {
    //与ln_loan_info表的对应关系
    private String DJRQ;  //--GEN_DATE
    private String SERNO; //SERIAL_NUMBER
    private String KHXM;  //CUS_NAME
    private String CSRQ;  //CUS_BIRTHDAY
    private Integer XB;    //CS_SEX
    private String ZJHM;  //CUS_IDCARD
    private String HYZK;  //CUS_MARRIAGE
    private Integer JZNX;  //CUS_LOCAL_YEAR
    private String SPOUSENAME; //MATE_NAME
    private String POZJHM;     //MATE_IDCARD
    private String POZY;      //MATE_VOCATION
    private String JTDZ;      //CUS_ADDRESS
    private String JTRW;      //CUS_FAMILY_NUM
    private String SJHM;      //CUS_MOBILE_PHONE
    private String JTDH;      //CUS_PHONE
    private String JZZK;      //CUS_LIVING_STATUS
    private String ZGXL;     //CUS_EDUCATION
    private String ZY;     //CUS_VOCATION
    private String GZDW;     //CUS_COMPANY
    private String DWDZYB;   //--CUS_COMPANY_ADDRESS
    private String QYMC;    //--BIZ_COMPANY
    private String JYFW;     //--BIZ_SCOPE
    private String JYDZ;     //--BIZ_ADDRESS
    private String KYSJ;     //--BIZ_START_DATE
    private String ZZXS;    //--BIZ_ORG
    private String ZZJGDM;  //--BIZ_ORG_CODE
    private Integer TOTALEMPLOYEE; //--BIZ_EMPLOYEE_NUM
    private String QYLX;         //--BIZ_COMPANY_TYPE
    private String JYDZJYDZ;     //--BIZ_PLACE
    private String JYCDMJ;      //--BIZ_AREA
    private BigDecimal ZJ;        //--BIZ_HOUSE_RENT
    private String JCKXKZ;    //--BIZ_LICENCE
    private BigDecimal SQJE;    //--APP_MONEY
    private String JKYT;    //--APP_USEAGE
    private Integer SQQX;     //--APP_LIMIT_YEAR
    private String YDHKRQ;   //--APP_REPAYMENT_DATE
    private BigDecimal MHKJE;   //--APP_REPAYMENT_MONTH
    private String HKLY;   //----APP_REPAYMENT_SOURCE
    private String KNDBR;  //----APP_GNT
    private String DBRZJH;   // --APP_GNT_IDCARD
    private String DBRLXDH;   //--APP_GNT_PHONE
    private String GX;       //--APP_GNT_RELATION
    private String KNDYW; //--APP_GNT_COMPANY
    private BigDecimal MYXSE;    //--CFA_ANNUAL_SALES
    private BigDecimal LRLX;  //--CFA_GROSS_MARGIN
    private BigDecimal LR;       //--CFA_YEAR_MARGIN
    private String LRBJ;  //--CFA_PROFIT_LEVEL
    private BigDecimal CHJE;  //----CFA_INVENTORY
    private BigDecimal YSZKJE;    //--CFA_ACCOUNT_INCOMING
    private BigDecimal FZJE;      //--CFA_DEBT
    private BigDecimal ZZC;     //---CFA_TOTAL
    private BigDecimal QTSR;    //--CFA_OTHER
    private String GYGX;    //--CFA_SUPPLY
    //二期新增字段
    private String XXLY;//信息来源
    private String DJLX;//登记类型
    private BigDecimal DSHJYJE;//待审会决议金额
    private Integer DSHJYQX;//待审会决议期限
    private BigDecimal SQLL;//贷审会决议利率
    private Integer SQJF;//申请积分
    private BigDecimal SQFD;//申请浮动
    private String FICPSN;//法定人
    private String FRZJHM;//法定人证件号
    private String SQJD;//贷款流程状态
    private String DQYH;//贷款当前状态操作员
    private String DJR;//登记人

    public String getDJR() {
        return DJR;
    }

    public void setDJR(String DJR) {
        this.DJR = DJR;
    }

    public void setDQYH(String DQYH) {
        this.DQYH = DQYH;
    }

    public void setSQJD(String SQJD) {
        this.SQJD = SQJD;
    }

    public String getDQYH() {
        return DQYH;
    }

    public String getSQJD() {
        return SQJD;
    }

    public void setXXLY(String XXLY) {
        this.XXLY = XXLY;
    }

    public void setDJLX(String DJLX) {
        this.DJLX = DJLX;
    }

    public void setFICPSN(String FICPSN) {
        this.FICPSN = FICPSN;
    }

    public void setFRZJHM(String FRZJHM) {
        this.FRZJHM = FRZJHM;
    }

    public String getXXLY() {
        return XXLY;
    }

    public String getDJLX() {
        return DJLX;
    }

    public String getFICPSN() {
        return FICPSN;
    }

    public String getFRZJHM() {
        return FRZJHM;
    }

    public void setDSHJYJE(BigDecimal DSHJYJE) {
        this.DSHJYJE = DSHJYJE;
    }

    public void setDSHJYQX(Integer DSHJYQX) {
        this.DSHJYQX = DSHJYQX;
    }

    public void setSQLL(BigDecimal SQLL) {
        this.SQLL = SQLL;
    }

    public void setSQJF(Integer SQJF) {
        this.SQJF = SQJF;
    }

    public void setSQFD(BigDecimal SQFD) {
        this.SQFD = SQFD;
    }

    public BigDecimal getDSHJYJE() {
        return DSHJYJE;
    }

    public Integer getDSHJYQX() {
        return DSHJYQX;
    }

    public BigDecimal getSQLL() {
        return SQLL;
    }

    public Integer getSQJF() {
        return SQJF;
    }

    public BigDecimal getSQFD() {
        return SQFD;
    }

    public String getDJRQ() {
        return DJRQ;
    }

    public void setDJRQ(String DJRQ) {
        this.DJRQ = DJRQ;
    }

    public String getSERNO() {
        return SERNO;
    }

    public void setSERNO(String SERNO) {
        this.SERNO = SERNO;
    }

    public String getKHXM() {
        return KHXM;
    }

    public void setKHXM(String KHXM) {
        this.KHXM = KHXM;
    }

    public String getCSRQ() {
        return CSRQ;
    }

    public void setCSRQ(String CSRQ) {
        this.CSRQ = CSRQ;
    }

    public Integer getXB() {
        return XB;
    }

    public void setXB(Integer XB) {
        this.XB = XB;
    }

    public String getZJHM() {
        return ZJHM;
    }

    public void setZJHM(String ZJHM) {
        this.ZJHM = ZJHM;
    }

    public String getHYZK() {
        return HYZK;
    }

    public void setHYZK(String HYZK) {
        this.HYZK = HYZK;
    }

    public Integer getJZNX() {
        return JZNX;
    }

    public void setJZNX(Integer JZNX) {
        this.JZNX = JZNX;
    }

    public String getSPOUSENAME() {
        return SPOUSENAME;
    }

    public void setSPOUSENAME(String SPOUSENAME) {
        this.SPOUSENAME = SPOUSENAME;
    }

    public String getPOZJHM() {
        return POZJHM;
    }

    public void setPOZJHM(String POZJHM) {
        this.POZJHM = POZJHM;
    }

    public String getPOZY() {
        return POZY;
    }

    public void setPOZY(String POZY) {
        this.POZY = POZY;
    }

    public String getJTDZ() {
        return JTDZ;
    }

    public void setJTDZ(String JTDZ) {
        this.JTDZ = JTDZ;
    }

   

    public String getJTRW() {
		return JTRW;
	}

	public void setJTRW(String jTRW) {
		JTRW = jTRW;
	}

	public String getSJHM() {
        return SJHM;
    }

    public void setSJHM(String SJHM) {
        this.SJHM = SJHM;
    }

    public String getJTDH() {
        return JTDH;
    }

    public void setJTDH(String JTDH) {
        this.JTDH = JTDH;
    }

    public String getJZZK() {
        return JZZK;
    }

    public void setJZZK(String JZZK) {
        this.JZZK = JZZK;
    }

    public String getZGXL() {
        return ZGXL;
    }

    public void setZGXL(String ZGXL) {
        this.ZGXL = ZGXL;
    }

    public String getZY() {
        return ZY;
    }

    public void setZY(String ZY) {
        this.ZY = ZY;
    }

    public String getGZDW() {
        return GZDW;
    }

    public void setGZDW(String GZDW) {
        this.GZDW = GZDW;
    }

    public String getDWDZYB() {
        return DWDZYB;
    }

    public void setDWDZYB(String DWDZYB) {
        this.DWDZYB = DWDZYB;
    }

    public String getQYMC() {
        return QYMC;
    }

    public void setQYMC(String QYMC) {
        this.QYMC = QYMC;
    }

    public String getJYFW() {
        return JYFW;
    }

    public void setJYFW(String JYFW) {
        this.JYFW = JYFW;
    }

    public String getJYDZ() {
        return JYDZ;
    }

    public void setJYDZ(String JYDZ) {
        this.JYDZ = JYDZ;
    }

    public String getKYSJ() {
        return KYSJ;
    }

    public void setKYSJ(String KYSJ) {
        this.KYSJ = KYSJ;
    }

    public String getZZXS() {
        return ZZXS;
    }

    public void setZZXS(String ZZXS) {
        this.ZZXS = ZZXS;
    }

    public String getZZJGDM() {
        return ZZJGDM;
    }

    public void setZZJGDM(String ZZJGDM) {
        this.ZZJGDM = ZZJGDM;
    }

    public Integer getTOTALEMPLOYEE() {
        return TOTALEMPLOYEE;
    }

    public void setTOTALEMPLOYEE(Integer TOTALEMPLOYEE) {
        this.TOTALEMPLOYEE = TOTALEMPLOYEE;
    }

    public String getQYLX() {
        return QYLX;
    }

    public void setQYLX(String QYLX) {
        this.QYLX = QYLX;
    }

    public String getJYDZJYDZ() {
        return JYDZJYDZ;
    }

    public void setJYDZJYDZ(String JYDZJYDZ) {
        this.JYDZJYDZ = JYDZJYDZ;
    }

    public String getJYCDMJ() {
        return JYCDMJ;
    }

    public void setJYCDMJ(String JYCDMJ) {
        this.JYCDMJ = JYCDMJ;
    }

    public BigDecimal getZJ() {
        return ZJ;
    }

    public void setZJ(BigDecimal ZJ) {
        this.ZJ = ZJ;
    }

    public String getJCKXKZ() {
        return JCKXKZ;
    }

    public void setJCKXKZ(String JCKXKZ) {
        this.JCKXKZ = JCKXKZ;
    }

    public BigDecimal getSQJE() {
        return SQJE;
    }

    public void setSQJE(BigDecimal SQJE) {
        this.SQJE = SQJE;
    }

    public String getJKYT() {
        return JKYT;
    }

    public void setJKYT(String JKYT) {
        this.JKYT = JKYT;
    }

    public Integer getSQQX() {
        return SQQX;
    }

    public void setSQQX(Integer SQQX) {
        this.SQQX = SQQX;
    }

    public String getYDHKRQ() {
        return YDHKRQ;
    }

    public void setYDHKRQ(String YDHKRQ) {
        this.YDHKRQ = YDHKRQ;
    }

    public BigDecimal getMHKJE() {
        return MHKJE;
    }

    public void setMHKJE(BigDecimal MHKJE) {
        this.MHKJE = MHKJE;
    }

    public String getHKLY() {
        return HKLY;
    }

    public void setHKLY(String HKLY) {
        this.HKLY = HKLY;
    }

    public String getKNDBR() {
        return KNDBR;
    }

    public void setKNDBR(String KNDBR) {
        this.KNDBR = KNDBR;
    }

    public String getDBRZJH() {
        return DBRZJH;
    }

    public void setDBRZJH(String DBRZJH) {
        this.DBRZJH = DBRZJH;
    }

    public String getDBRLXDH() {
        return DBRLXDH;
    }

    public void setDBRLXDH(String DBRLXDH) {
        this.DBRLXDH = DBRLXDH;
    }

    public String getGX() {
        return GX;
    }

    public void setGX(String GX) {
        this.GX = GX;
    }

    public String getKNDYW() {
        return KNDYW;
    }

    public void setKNDYW(String KNDYW) {
        this.KNDYW = KNDYW;
    }

    public BigDecimal getMYXSE() {
        return MYXSE;
    }

    public void setMYXSE(BigDecimal MYXSE) {
        this.MYXSE = MYXSE;
    }

    public BigDecimal getLRLX() {
        return LRLX;
    }

    public void setLRLX(BigDecimal LRLX) {
        this.LRLX = LRLX;
    }

    public BigDecimal getLR() {
        return LR;
    }

    public void setLR(BigDecimal LR) {
        this.LR = LR;
    }

    public String getLRBJ() {
        return LRBJ;
    }

    public void setLRBJ(String LRBJ) {
        this.LRBJ = LRBJ;
    }

    public BigDecimal getCHJE() {
        return CHJE;
    }

    public void setCHJE(BigDecimal CHJE) {
        this.CHJE = CHJE;
    }

    public BigDecimal getYSZKJE() {
        return YSZKJE;
    }

    public void setYSZKJE(BigDecimal YSZKJE) {
        this.YSZKJE = YSZKJE;
    }

    public BigDecimal getFZJE() {
        return FZJE;
    }

    public void setFZJE(BigDecimal FZJE) {
        this.FZJE = FZJE;
    }

    public BigDecimal getZZC() {
        return ZZC;
    }

    public void setZZC(BigDecimal ZZC) {
        this.ZZC = ZZC;
    }

    public BigDecimal getQTSR() {
        return QTSR;
    }

    public void setQTSR(BigDecimal QTSR) {
        this.QTSR = QTSR;
    }

    public String getGYGX() {
        return GYGX;
    }

    public void setGYGX(String GYGX) {
        this.GYGX = GYGX;
    }
}
