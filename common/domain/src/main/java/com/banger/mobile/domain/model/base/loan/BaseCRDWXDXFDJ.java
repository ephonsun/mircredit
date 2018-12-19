package com.banger.mobile.domain.model.base.loan;

import com.banger.mobile.domain.model.base.BaseObject;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author zhangfp
 * @version $Id: BaseCRDWXDXFDJ v 0.1 ${} 上午9:01 zhangfp Exp $
 */
public class BaseCRDWXDXFDJ  extends BaseObject implements Serializable {
    //与ln_loan_info表的对应关系
    private String DJRQ;  //--GEN_DATE
    private String SERNO; //SERIAL_NUMBER
    private String KHXM;  //CUS_NAME
    private String CSRQ;  //CUS_BIRTHDAY
    private Integer XB;    //CS_SEX
    private String ZJHM;  //CUS_IDCARD
    private String HYZK;  //CUS_MARRIAGE
    private Integer JZNX;  //CUS_LOCAL_YEAR
    private String JTDZ;      //CUS_ADDRESS
    private String JTRW;      //CUS_FAMILY_NUM
    private String SJHM;      //CUS_MOBILE_PHONE
    private String JTDH;      //CUS_PHONE
    private String JZZK;      //CUS_LIVING_STATUS
    private String ZGXL;     //CUS_EDUCATION
    private String ZW;     //CUS_JOB
    private String GZDW;     //CUS_COMPANY
    private String DWDZYB;   //--CUS_COMPANY_ADDRESS
    private String GZNX;    //--CUS_WORK_YEAR
    private String YGZSP;     //----CUS_MONTHLY_INCOMING
    private String ZZJY;     //--BIZ_--CUS_SELF_BIZ
    private String SPOUSENAME;     //----MATE_NAME
    private String POZJHM;    //----MATE_IDCARD
    private String POZY;  //--MATE_VOCATION
    private String POGZNX; //---MATE_WORK_YEAR
    private String PODH;         //---MATE_PHONE
    private String POSJHM;     //----MATE_MOBILE_PHONE
    private String POGZDW;      //--MATE_COMPANY
    private String POZW;        //---MATE_JOB
    private String PODWDZYB;    //---MATE_ADDRESS
    private String POYGZSP;    //--MATE_MONTHLY_INCOMING
    private String XM;    //---CHD_NAME
    private Integer XB1;     //---CHD_SEX
    private String JKZK;   //---CHD_HEALTH
    private String CSRQ1;   //CHD_BIRTHDAY
    private String GZDW1;   //--CHD_COMPANY
    private String ZGXL1;  //------CHD_EDUCATION
    private String FMQK;   // ----CHD_PARENT_INFO
    private BigDecimal SQJE;   //----APP_MONEY
    private String JKYT;       //----APP_LIMIT_YEAR
    private String SQQX; //----APP_LIMIT_YEAR
    private String YDHKRQ;    //--APP_REPAYMENT_DATE
    private BigDecimal MHKJE;  //--APP_REPAYMENT_MONTH
    private String HKLY;       //--APP_REMAYMENT_SOURCE
    private String KNDBR;  //--APP_GNT
    private String DBRZJH;  //--APP_GNT_IDCARD
    private String DBRLXDH;    //--APP_GNT_PHONE
    private String GX;      //---APP_GNT_RELATION
    private String GZDW2;     //--APP_GNT_COMPANY
    private String ZW1;    //--APP_GNT_JOB
    private String DWDZYB2;    //--APP_GNT_ADDRESS
    private String YGZSP2;    //---APP_GNT_PROPERTY
    private String ZCQK;    //----APP_GNT_PROPERTY
    //二期新增字段
    private String XXLY;//信息来源
    private String DJLX;//登记类型
    private BigDecimal DSHJYJE;//待审会决议金额
    private Integer DSHJYQX;//待审会决议期限
    private BigDecimal SQLL;//贷审会决议利率
    private Integer SQJF;//申请积分
    private BigDecimal SQFD;//申请浮动
    private String SQJD;//当前贷款状态
    private String DQYH;//当前的用户
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

    public String getDQYH() {
        return DQYH;
    }

    public String getSQJD() {
        return SQJD;
    }

    public void setSQJD(String SQJD) {
        this.SQJD = SQJD;
    }

    public void setXXLY(String XXLY) {
        this.XXLY = XXLY;
    }

    public void setDJLX(String DJLX) {
        this.DJLX = DJLX;
    }

    public String getXXLY() {
        return XXLY;
    }

    public String getDJLX() {
        return DJLX;
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

    public String getZW() {
        return ZW;
    }

    public void setZW(String ZW) {
        this.ZW = ZW;
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

    public String getGZNX() {
        return GZNX;
    }

    public void setGZNX(String GZNX) {
        this.GZNX = GZNX;
    }

    public String getYGZSP() {
        return YGZSP;
    }

    public void setYGZSP(String YGZSP) {
        this.YGZSP = YGZSP;
    }

    public String getZZJY() {
        return ZZJY;
    }

    public void setZZJY(String ZZJY) {
        this.ZZJY = ZZJY;
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

    public String getPOGZNX() {
        return POGZNX;
    }

    public void setPOGZNX(String POGZNX) {
        this.POGZNX = POGZNX;
    }

    public String getPODH() {
        return PODH;
    }

    public void setPODH(String PODH) {
        this.PODH = PODH;
    }

    public String getPOSJHM() {
        return POSJHM;
    }

    public void setPOSJHM(String POSJHM) {
        this.POSJHM = POSJHM;
    }

    public String getPOGZDW() {
        return POGZDW;
    }

    public void setPOGZDW(String POGZDW) {
        this.POGZDW = POGZDW;
    }

    public String getPOZW() {
        return POZW;
    }

    public void setPOZW(String POZW) {
        this.POZW = POZW;
    }

    public String getPODWDZYB() {
        return PODWDZYB;
    }

    public void setPODWDZYB(String PODWDZYB) {
        this.PODWDZYB = PODWDZYB;
    }

    public String getPOYGZSP() {
        return POYGZSP;
    }

    public void setPOYGZSP(String POYGZSP) {
        this.POYGZSP = POYGZSP;
    }

    public String getXM() {
        return XM;
    }

    public void setXM(String XM) {
        this.XM = XM;
    }

    public Integer getXB1() {
        return XB1;
    }

    public void setXB1(Integer XB1) {
        this.XB1 = XB1;
    }

    public String getJKZK() {
        return JKZK;
    }

    public void setJKZK(String JKZK) {
        this.JKZK = JKZK;
    }

    public String getCSRQ1() {
        return CSRQ1;
    }

    public void setCSRQ1(String CSRQ1) {
        this.CSRQ1 = CSRQ1;
    }

    public String getGZDW1() {
        return GZDW1;
    }

    public void setGZDW1(String GZDW1) {
        this.GZDW1 = GZDW1;
    }

    public String getZGXL1() {
        return ZGXL1;
    }

    public void setZGXL1(String ZGXL1) {
        this.ZGXL1 = ZGXL1;
    }

    public String getFMQK() {
        return FMQK;
    }

    public void setFMQK(String FMQK) {
        this.FMQK = FMQK;
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

    public String getSQQX() {
        return SQQX;
    }

    public void setSQQX(String SQQX) {
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

    public String getGZDW2() {
        return GZDW2;
    }

    public void setGZDW2(String GZDW2) {
        this.GZDW2 = GZDW2;
    }

    public String getZW1() {
        return ZW1;
    }

    public void setZW1(String ZW1) {
        this.ZW1 = ZW1;
    }

    public String getDWDZYB2() {
        return DWDZYB2;
    }

    public void setDWDZYB2(String DWDZYB2) {
        this.DWDZYB2 = DWDZYB2;
    }

    public String getYGZSP2() {
        return YGZSP2;
    }

    public void setYGZSP2(String YGZSP2) {
        this.YGZSP2 = YGZSP2;
    }

    public String getZCQK() {
        return ZCQK;
    }

    public void setZCQK(String ZCQK) {
        this.ZCQK = ZCQK;
    }
}
