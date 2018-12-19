/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-8-31
 */
package com.banger.mobile.domain.model.customer;

import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author cheny
 * @version $Id: CrmExportBean.java,v 0.1 2012-8-31 下午6:49:58 cheny Exp $
 */
public class CrmExportBean extends CrmCustomer{

    private static final long serialVersionUID = 9203129992137740508L;
    
    private String            account;                                 //用户名
    private String            deptCode;                                //机构编号
    private Integer           extFieldId;                              //主键
    private Integer           customerId;                              //客户ID
    private Date              extDate01;                               //扩展字段
    private Date              extDate02;                               //扩展字段
    private Date              extDate03;                               //扩展字段
    private Date              extDate04;                               //扩展字段
    private Date              extDate05;                               //扩展字段
    private Date              extDate06;                               //扩展字段
    private Date              extDate07;                               //扩展字段
    private Date              extDate08;                               //扩展字段
    private Date              extDate09;                               //扩展字段
    private Date              extDate10;                               //扩展字段
    private Date              extDate11;                               //扩展字段
    private Date              extDate12;                               //扩展字段
    private Date              extDate13;                               //扩展字段
    private Date              extDate14;                               //扩展字段
    private Date              extDate15;                               //扩展字段
    private Date              extDate16;                               //扩展字段
    private Date              extDate17;                               //扩展字段
    private Date              extDate18;                               //扩展字段
    private Date              extDate19;                               //扩展字段
    private Date              extDate20;                               //扩展字段
    private Double            extFloat01;                              //扩展字段
    private Double            extFloat02;                              //扩展字段
    private Double            extFloat03;                              //扩展字段
    private Double            extFloat04;                              //扩展字段
    private Double            extFloat05;                              //扩展字段
    private Double            extFloat06;                              //扩展字段
    private Double            extFloat07;                              //扩展字段
    private Double            extFloat08;                              //扩展字段
    private Double            extFloat09;                              //扩展字段
    private Double            extFloat10;                              //扩展字段
    private Double            extFloat11;                              //扩展字段
    private Double            extFloat12;                              //扩展字段
    private Double            extFloat13;                              //扩展字段
    private Double            extFloat14;                              //扩展字段
    private Double            extFloat15;                              //扩展字段
    private Double            extFloat16;                              //扩展字段
    private Double            extFloat17;                              //扩展字段
    private Double            extFloat18;                              //扩展字段
    private Double            extFloat19;                              //扩展字段
    private Double            extFloat20;                              //扩展字段
    private String            extLongString01;                         //扩展字段
    private String            extLongString02;                         //扩展字段
    private String            extLongString03;                         //扩展字段
    private String            extLongString04;                         //扩展字段
    private String            extLongString05;                         //扩展字段
    private String            extLongString06;                         //扩展字段
    private String            extLongString07;                         //扩展字段
    private String            extLongString08;                         //扩展字段
    private String            extLongString09;                         //扩展字段
    private String            extLongString10;                         //扩展字段
    
    private String            extLongString11;                         //扩展字段
    private String            extLongString12;                         //扩展字段
    private String            extLongString13;                         //扩展字段
    private String            extLongString14;                         //扩展字段
    private String            extLongString15;                         //扩展字段
    private String            extLongString16;                         //扩展字段
    private String            extLongString17;                         //扩展字段
    private String            extLongString18;                         //扩展字段
    private String            extLongString19;                         //扩展字段
    private String            extLongString20;                         //扩展字段
    private String            extString01;                             //扩展字段
    private String            extString02;                             //扩展字段
    private String            extString03;                             //扩展字段
    private String            extString04;                             //扩展字段
    private String            extString05;                             //扩展字段
    private String            extString06;                             //扩展字段
    private String            extString07;                             //扩展字段
    private String            extString08;                             //扩展字段
    private String            extString09;                             //扩展字段
    private String            extString10;                             //扩展字段
    
    private String            extString11;                             //扩展字段
    private String            extString12;                             //扩展字段
    private String            extString13;                             //扩展字段
    private String            extString14;                             //扩展字段
    private String            extString15;                             //扩展字段
    private String            extString16;                             //扩展字段
    private String            extString17;                             //扩展字段
    private String            extString18;                             //扩展字段
    private String            extString19;                             //扩展字段
    private String            extString20;                             //扩展字段
    private String            extString21;                             //扩展字段
    private String            extString22;                             //扩展字段
    private String            extString23;                             //扩展字段
    private String            extString24;                             //扩展字段
    private String            extString25;                             //扩展字段
    private String            extString26;                             //扩展字段
    private String            extString27;                             //扩展字段
    private String            extString28;                             //扩展字段
    private String            extString29;                             //扩展字段
    private String            extString30;                             //扩展字段
    private String            extString31;                             //扩展字段
    private String            extString32;                             //扩展字段
    private String            extString33;                             //扩展字段
    private String            extString34;                             //扩展字段
    private String            extString35;                             //扩展字段
    private String            extString36;                             //扩展字段
    private String            extString37;                             //扩展字段
    private String            extString38;                             //扩展字段
    private String            extString39;                             //扩展字段
    private String            extString40;                             //扩展字段
    private String            extString41;                             //扩展字段
    private String            extString42;                             //扩展字段
    private String            extString43;                             //扩展字段
    private String            extString44;                             //扩展字段
    private String            extString45;                             //扩展字段
    private String            extString46;                             //扩展字段
    private String            extString47;                             //扩展字段
    private String            extString48;                             //扩展字段
    private String            extString49;                             //扩展字段
    private String            extString50;                             //扩展字段
    private String            extString51;                             //扩展字段
    private String            extString52;                             //扩展字段
    private String            extString53;                             //扩展字段
    private String            extString54;                             //扩展字段
    private String            extString55;                             //扩展字段
    private String            extString56;                             //扩展字段
    private String            extString57;                             //扩展字段
    private String            extString58;                             //扩展字段
    private String            extString59;                             //扩展字段
    private String            extString60;                             //扩展字段
    public Integer getExtFieldId() {
        return extFieldId;
    }
    public void setExtFieldId(Integer extFieldId) {
        this.extFieldId = extFieldId;
    }
    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public Date getExtDate01() {
        return extDate01;
    }
    public void setExtDate01(Date extDate01) {
        this.extDate01 = extDate01;
    }
    public Date getExtDate02() {
        return extDate02;
    }
    public void setExtDate02(Date extDate02) {
        this.extDate02 = extDate02;
    }
    public Date getExtDate03() {
        return extDate03;
    }
    public void setExtDate03(Date extDate03) {
        this.extDate03 = extDate03;
    }
    public Date getExtDate04() {
        return extDate04;
    }
    public void setExtDate04(Date extDate04) {
        this.extDate04 = extDate04;
    }
    public Date getExtDate05() {
        return extDate05;
    }
    public void setExtDate05(Date extDate05) {
        this.extDate05 = extDate05;
    }
    
    public String getExtLongString11() {
        return extLongString11;
    }
    public void setExtLongString11(String extLongString11) {
        this.extLongString11 = extLongString11;
    }
    public String getExtLongString12() {
        return extLongString12;
    }
    public void setExtLongString12(String extLongString12) {
        this.extLongString12 = extLongString12;
    }
    public String getExtLongString13() {
        return extLongString13;
    }
    public void setExtLongString13(String extLongString13) {
        this.extLongString13 = extLongString13;
    }
    public String getExtLongString14() {
        return extLongString14;
    }
    public void setExtLongString14(String extLongString14) {
        this.extLongString14 = extLongString14;
    }
    public String getExtLongString15() {
        return extLongString15;
    }
    public void setExtLongString15(String extLongString15) {
        this.extLongString15 = extLongString15;
    }
    public String getExtLongString16() {
        return extLongString16;
    }
    public void setExtLongString16(String extLongString16) {
        this.extLongString16 = extLongString16;
    }
    public String getExtLongString17() {
        return extLongString17;
    }
    public void setExtLongString17(String extLongString17) {
        this.extLongString17 = extLongString17;
    }
    public String getExtLongString18() {
        return extLongString18;
    }
    public void setExtLongString18(String extLongString18) {
        this.extLongString18 = extLongString18;
    }
    public String getExtLongString19() {
        return extLongString19;
    }
    public void setExtLongString19(String extLongString19) {
        this.extLongString19 = extLongString19;
    }
    public String getExtLongString20() {
        return extLongString20;
    }
    public void setExtLongString20(String extLongString20) {
        this.extLongString20 = extLongString20;
    }
    public String getExtString01() {
        return extString01;
    }
    public void setExtString01(String extString01) {
        this.extString01 = extString01;
    }
    public String getExtString02() {
        return extString02;
    }
    public void setExtString02(String extString02) {
        this.extString02 = extString02;
    }
    public String getExtString03() {
        return extString03;
    }
    public void setExtString03(String extString03) {
        this.extString03 = extString03;
    }
    public String getExtString04() {
        return extString04;
    }
    public void setExtString04(String extString04) {
        this.extString04 = extString04;
    }
    public String getExtString05() {
        return extString05;
    }
    public void setExtString05(String extString05) {
        this.extString05 = extString05;
    }
    public String getExtString06() {
        return extString06;
    }
    public void setExtString06(String extString06) {
        this.extString06 = extString06;
    }
    public String getExtString07() {
        return extString07;
    }
    public void setExtString07(String extString07) {
        this.extString07 = extString07;
    }
    public String getExtString08() {
        return extString08;
    }
    public void setExtString08(String extString08) {
        this.extString08 = extString08;
    }
    public String getExtString09() {
        return extString09;
    }
    public void setExtString09(String extString09) {
        this.extString09 = extString09;
    }
    public String getExtString10() {
        return extString10;
    }
    public void setExtString10(String extString10) {
        this.extString10 = extString10;
    }
    public Date getExtDate06() {
        return extDate06;
    }
    public void setExtDate06(Date extDate06) {
        this.extDate06 = extDate06;
    }
    public Date getExtDate07() {
        return extDate07;
    }
    public void setExtDate07(Date extDate07) {
        this.extDate07 = extDate07;
    }
    public Date getExtDate08() {
        return extDate08;
    }
    public void setExtDate08(Date extDate08) {
        this.extDate08 = extDate08;
    }
    public Date getExtDate09() {
        return extDate09;
    }
    public void setExtDate09(Date extDate09) {
        this.extDate09 = extDate09;
    }
    public Date getExtDate10() {
        return extDate10;
    }
    public void setExtDate10(Date extDate10) {
        this.extDate10 = extDate10;
    }
    public Date getExtDate11() {
        return extDate11;
    }
    public void setExtDate11(Date extDate11) {
        this.extDate11 = extDate11;
    }
    public Date getExtDate12() {
        return extDate12;
    }
    public void setExtDate12(Date extDate12) {
        this.extDate12 = extDate12;
    }
    public Date getExtDate13() {
        return extDate13;
    }
    public void setExtDate13(Date extDate13) {
        this.extDate13 = extDate13;
    }
    public Date getExtDate14() {
        return extDate14;
    }
    public void setExtDate14(Date extDate14) {
        this.extDate14 = extDate14;
    }
    public Date getExtDate15() {
        return extDate15;
    }
    public void setExtDate15(Date extDate15) {
        this.extDate15 = extDate15;
    }
    public Date getExtDate16() {
        return extDate16;
    }
    public void setExtDate16(Date extDate16) {
        this.extDate16 = extDate16;
    }
    public Date getExtDate17() {
        return extDate17;
    }
    public void setExtDate17(Date extDate17) {
        this.extDate17 = extDate17;
    }
    public Date getExtDate18() {
        return extDate18;
    }
    public void setExtDate18(Date extDate18) {
        this.extDate18 = extDate18;
    }
    public Date getExtDate19() {
        return extDate19;
    }
    public void setExtDate19(Date extDate19) {
        this.extDate19 = extDate19;
    }
    public Date getExtDate20() {
        return extDate20;
    }
    public void setExtDate20(Date extDate20) {
        this.extDate20 = extDate20;
    }
    public Double getExtFloat01() {
        return extFloat01;
    }
    public void setExtFloat01(Double extFloat01) {
        this.extFloat01 = extFloat01;
    }
    public Double getExtFloat02() {
        return extFloat02;
    }
    public void setExtFloat02(Double extFloat02) {
        this.extFloat02 = extFloat02;
    }
    public Double getExtFloat03() {
        return extFloat03;
    }
    public void setExtFloat03(Double extFloat03) {
        this.extFloat03 = extFloat03;
    }
    public Double getExtFloat04() {
        return extFloat04;
    }
    public void setExtFloat04(Double extFloat04) {
        this.extFloat04 = extFloat04;
    }
    public Double getExtFloat05() {
        return extFloat05;
    }
    public void setExtFloat05(Double extFloat05) {
        this.extFloat05 = extFloat05;
    }
    public Double getExtFloat06() {
        return extFloat06;
    }
    public void setExtFloat06(Double extFloat06) {
        this.extFloat06 = extFloat06;
    }
    public Double getExtFloat07() {
        return extFloat07;
    }
    public void setExtFloat07(Double extFloat07) {
        this.extFloat07 = extFloat07;
    }
    public Double getExtFloat08() {
        return extFloat08;
    }
    public void setExtFloat08(Double extFloat08) {
        this.extFloat08 = extFloat08;
    }
    public Double getExtFloat09() {
        return extFloat09;
    }
    public void setExtFloat09(Double extFloat09) {
        this.extFloat09 = extFloat09;
    }
    public Double getExtFloat10() {
        return extFloat10;
    }
    public void setExtFloat10(Double extFloat10) {
        this.extFloat10 = extFloat10;
    }
    public Double getExtFloat11() {
        return extFloat11;
    }
    public void setExtFloat11(Double extFloat11) {
        this.extFloat11 = extFloat11;
    }
    public Double getExtFloat12() {
        return extFloat12;
    }
    public void setExtFloat12(Double extFloat12) {
        this.extFloat12 = extFloat12;
    }
    public Double getExtFloat13() {
        return extFloat13;
    }
    public void setExtFloat13(Double extFloat13) {
        this.extFloat13 = extFloat13;
    }
    public Double getExtFloat14() {
        return extFloat14;
    }
    public void setExtFloat14(Double extFloat14) {
        this.extFloat14 = extFloat14;
    }
    public Double getExtFloat15() {
        return extFloat15;
    }
    public void setExtFloat15(Double extFloat15) {
        this.extFloat15 = extFloat15;
    }
    public Double getExtFloat16() {
        return extFloat16;
    }
    public void setExtFloat16(Double extFloat16) {
        this.extFloat16 = extFloat16;
    }
    public Double getExtFloat17() {
        return extFloat17;
    }
    public void setExtFloat17(Double extFloat17) {
        this.extFloat17 = extFloat17;
    }
    public Double getExtFloat18() {
        return extFloat18;
    }
    public void setExtFloat18(Double extFloat18) {
        this.extFloat18 = extFloat18;
    }
    public Double getExtFloat19() {
        return extFloat19;
    }
    public void setExtFloat19(Double extFloat19) {
        this.extFloat19 = extFloat19;
    }
    public Double getExtFloat20() {
        return extFloat20;
    }
    public void setExtFloat20(Double extFloat20) {
        this.extFloat20 = extFloat20;
    }
    public String getExtLongString01() {
        return extLongString01;
    }
    public void setExtLongString01(String extLongString01) {
        this.extLongString01 = extLongString01;
    }
    public String getExtLongString02() {
        return extLongString02;
    }
    public void setExtLongString02(String extLongString02) {
        this.extLongString02 = extLongString02;
    }
    public String getExtLongString03() {
        return extLongString03;
    }
    public void setExtLongString03(String extLongString03) {
        this.extLongString03 = extLongString03;
    }
    public String getExtLongString04() {
        return extLongString04;
    }
    public void setExtLongString04(String extLongString04) {
        this.extLongString04 = extLongString04;
    }
    public String getExtLongString05() {
        return extLongString05;
    }
    public void setExtLongString05(String extLongString05) {
        this.extLongString05 = extLongString05;
    }
    public String getExtLongString06() {
        return extLongString06;
    }
    public void setExtLongString06(String extLongString06) {
        this.extLongString06 = extLongString06;
    }
    public String getExtLongString07() {
        return extLongString07;
    }
    public void setExtLongString07(String extLongString07) {
        this.extLongString07 = extLongString07;
    }
    public String getExtLongString08() {
        return extLongString08;
    }
    public void setExtLongString08(String extLongString08) {
        this.extLongString08 = extLongString08;
    }
    public String getExtLongString09() {
        return extLongString09;
    }
    public void setExtLongString09(String extLongString09) {
        this.extLongString09 = extLongString09;
    }
    public String getExtLongString10() {
        return extLongString10;
    }
    public void setExtLongString10(String extLongString10) {
        this.extLongString10 = extLongString10;
    }
    public String getExtString11() {
        return extString11;
    }
    public void setExtString11(String extString11) {
        this.extString11 = extString11;
    }
    public String getExtString12() {
        return extString12;
    }
    public void setExtString12(String extString12) {
        this.extString12 = extString12;
    }
    public String getExtString13() {
        return extString13;
    }
    public void setExtString13(String extString13) {
        this.extString13 = extString13;
    }
    public String getExtString14() {
        return extString14;
    }
    public void setExtString14(String extString14) {
        this.extString14 = extString14;
    }
    public String getExtString15() {
        return extString15;
    }
    public void setExtString15(String extString15) {
        this.extString15 = extString15;
    }
    public String getExtString16() {
        return extString16;
    }
    public void setExtString16(String extString16) {
        this.extString16 = extString16;
    }
    public String getExtString17() {
        return extString17;
    }
    public void setExtString17(String extString17) {
        this.extString17 = extString17;
    }
    public String getExtString18() {
        return extString18;
    }
    public void setExtString18(String extString18) {
        this.extString18 = extString18;
    }
    public String getExtString19() {
        return extString19;
    }
    public void setExtString19(String extString19) {
        this.extString19 = extString19;
    }
    public String getExtString20() {
        return extString20;
    }
    public void setExtString20(String extString20) {
        this.extString20 = extString20;
    }
    public String getExtString21() {
        return extString21;
    }
    public void setExtString21(String extString21) {
        this.extString21 = extString21;
    }
    public String getExtString22() {
        return extString22;
    }
    public void setExtString22(String extString22) {
        this.extString22 = extString22;
    }
    public String getExtString23() {
        return extString23;
    }
    public void setExtString23(String extString23) {
        this.extString23 = extString23;
    }
    public String getExtString24() {
        return extString24;
    }
    public void setExtString24(String extString24) {
        this.extString24 = extString24;
    }
    public String getExtString25() {
        return extString25;
    }
    public void setExtString25(String extString25) {
        this.extString25 = extString25;
    }
    public String getExtString26() {
        return extString26;
    }
    public void setExtString26(String extString26) {
        this.extString26 = extString26;
    }
    public String getExtString27() {
        return extString27;
    }
    public void setExtString27(String extString27) {
        this.extString27 = extString27;
    }
    public String getExtString28() {
        return extString28;
    }
    public void setExtString28(String extString28) {
        this.extString28 = extString28;
    }
    public String getExtString29() {
        return extString29;
    }
    public void setExtString29(String extString29) {
        this.extString29 = extString29;
    }
    public String getExtString30() {
        return extString30;
    }
    public void setExtString30(String extString30) {
        this.extString30 = extString30;
    }
    public String getExtString31() {
        return extString31;
    }
    public void setExtString31(String extString31) {
        this.extString31 = extString31;
    }
    public String getExtString32() {
        return extString32;
    }
    public void setExtString32(String extString32) {
        this.extString32 = extString32;
    }
    public String getExtString33() {
        return extString33;
    }
    public void setExtString33(String extString33) {
        this.extString33 = extString33;
    }
    public String getExtString34() {
        return extString34;
    }
    public void setExtString34(String extString34) {
        this.extString34 = extString34;
    }
    public String getExtString35() {
        return extString35;
    }
    public void setExtString35(String extString35) {
        this.extString35 = extString35;
    }
    public String getExtString36() {
        return extString36;
    }
    public void setExtString36(String extString36) {
        this.extString36 = extString36;
    }
    public String getExtString37() {
        return extString37;
    }
    public void setExtString37(String extString37) {
        this.extString37 = extString37;
    }
    public String getExtString38() {
        return extString38;
    }
    public void setExtString38(String extString38) {
        this.extString38 = extString38;
    }
    public String getExtString39() {
        return extString39;
    }
    public void setExtString39(String extString39) {
        this.extString39 = extString39;
    }
    public String getExtString40() {
        return extString40;
    }
    public void setExtString40(String extString40) {
        this.extString40 = extString40;
    }
    public String getExtString41() {
        return extString41;
    }
    public void setExtString41(String extString41) {
        this.extString41 = extString41;
    }
    public String getExtString42() {
        return extString42;
    }
    public void setExtString42(String extString42) {
        this.extString42 = extString42;
    }
    public String getExtString43() {
        return extString43;
    }
    public void setExtString43(String extString43) {
        this.extString43 = extString43;
    }
    public String getExtString44() {
        return extString44;
    }
    public void setExtString44(String extString44) {
        this.extString44 = extString44;
    }
    public String getExtString45() {
        return extString45;
    }
    public void setExtString45(String extString45) {
        this.extString45 = extString45;
    }
    public String getExtString46() {
        return extString46;
    }
    public void setExtString46(String extString46) {
        this.extString46 = extString46;
    }
    public String getExtString47() {
        return extString47;
    }
    public void setExtString47(String extString47) {
        this.extString47 = extString47;
    }
    public String getExtString48() {
        return extString48;
    }
    public void setExtString48(String extString48) {
        this.extString48 = extString48;
    }
    public String getExtString49() {
        return extString49;
    }
    public void setExtString49(String extString49) {
        this.extString49 = extString49;
    }
    public String getExtString50() {
        return extString50;
    }
    public void setExtString50(String extString50) {
        this.extString50 = extString50;
    }
    public String getExtString51() {
        return extString51;
    }
    public void setExtString51(String extString51) {
        this.extString51 = extString51;
    }
    public String getExtString52() {
        return extString52;
    }
    public void setExtString52(String extString52) {
        this.extString52 = extString52;
    }
    public String getExtString53() {
        return extString53;
    }
    public void setExtString53(String extString53) {
        this.extString53 = extString53;
    }
    public String getExtString54() {
        return extString54;
    }
    public void setExtString54(String extString54) {
        this.extString54 = extString54;
    }
    public String getExtString55() {
        return extString55;
    }
    public void setExtString55(String extString55) {
        this.extString55 = extString55;
    }
    public String getExtString56() {
        return extString56;
    }
    public void setExtString56(String extString56) {
        this.extString56 = extString56;
    }
    public String getExtString57() {
        return extString57;
    }
    public void setExtString57(String extString57) {
        this.extString57 = extString57;
    }
    public String getExtString58() {
        return extString58;
    }
    public void setExtString58(String extString58) {
        this.extString58 = extString58;
    }
    public String getExtString59() {
        return extString59;
    }
    public void setExtString59(String extString59) {
        this.extString59 = extString59;
    }
    public String getExtString60() {
        return extString60;
    }
    public void setExtString60(String extString60) {
        this.extString60 = extString60;
    }
    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public String getDeptCode() {
        return deptCode;
    }
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }
    /**
     * 
     */
    public CrmExportBean() {
        super();
    }
    /**
     * @param account
     * @param deptCode
     * @param extFieldId
     * @param customerId
     * @param extDate01
     * @param extDate02
     * @param extDate03
     * @param extDate04
     * @param extDate05
     * @param extDate06
     * @param extDate07
     * @param extDate08
     * @param extDate09
     * @param extDate10
     * @param extDate11
     * @param extDate12
     * @param extDate13
     * @param extDate14
     * @param extDate15
     * @param extDate16
     * @param extDate17
     * @param extDate18
     * @param extDate19
     * @param extDate20
     * @param extFloat01
     * @param extFloat02
     * @param extFloat03
     * @param extFloat04
     * @param extFloat05
     * @param extFloat06
     * @param extFloat07
     * @param extFloat08
     * @param extFloat09
     * @param extFloat10
     * @param extFloat11
     * @param extFloat12
     * @param extFloat13
     * @param extFloat14
     * @param extFloat15
     * @param extFloat16
     * @param extFloat17
     * @param extFloat18
     * @param extFloat19
     * @param extFloat20
     * @param extLongString01
     * @param extLongString02
     * @param extLongString03
     * @param extLongString04
     * @param extLongString05
     * @param extLongString06
     * @param extLongString07
     * @param extLongString08
     * @param extLongString09
     * @param extLongString10
     * @param extString11
     * @param extString12
     * @param extString13
     * @param extString14
     * @param extString15
     * @param extString16
     * @param extString17
     * @param extString18
     * @param extString19
     * @param extString20
     * @param extString21
     * @param extString22
     * @param extString23
     * @param extString24
     * @param extString25
     * @param extString26
     * @param extString27
     * @param extString28
     * @param extString29
     * @param extString30
     * @param extString31
     * @param extString32
     * @param extString33
     * @param extString34
     * @param extString35
     * @param extString36
     * @param extString37
     * @param extString38
     * @param extString39
     * @param extString40
     * @param extString41
     * @param extString42
     * @param extString43
     * @param extString44
     * @param extString45
     * @param extString46
     * @param extString47
     * @param extString48
     * @param extString49
     * @param extString50
     * @param extString51
     * @param extString52
     * @param extString53
     * @param extString54
     * @param extString55
     * @param extString56
     * @param extString57
     * @param extString58
     * @param extString59
     * @param extString60
     */
    public CrmExportBean(String account, String deptCode, Integer extFieldId, Integer customerId,
                         Date extDate01, Date extDate02, Date extDate03, Date extDate04,
                         Date extDate05, Date extDate06, Date extDate07, Date extDate08,
                         Date extDate09, Date extDate10, Date extDate11, Date extDate12,
                         Date extDate13, Date extDate14, Date extDate15, Date extDate16,
                         Date extDate17, Date extDate18, Date extDate19, Date extDate20,
                         Double extFloat01, Double extFloat02, Double extFloat03,
                         Double extFloat04, Double extFloat05, Double extFloat06,
                         Double extFloat07, Double extFloat08, Double extFloat09,
                         Double extFloat10, Double extFloat11, Double extFloat12,
                         Double extFloat13, Double extFloat14, Double extFloat15,
                         Double extFloat16, Double extFloat17, Double extFloat18,
                         Double extFloat19, Double extFloat20, String extLongString01,
                         String extLongString02, String extLongString03, String extLongString04,
                         String extLongString05, String extLongString06, String extLongString07,
                         String extLongString08, String extLongString09, String extLongString10,
                         String extString11, String extString12, String extString13,
                         String extString14, String extString15, String extString16,
                         String extString17, String extString18, String extString19,
                         String extString20, String extString21, String extString22,
                         String extString23, String extString24, String extString25,
                         String extString26, String extString27, String extString28,
                         String extString29, String extString30, String extString31,
                         String extString32, String extString33, String extString34,
                         String extString35, String extString36, String extString37,
                         String extString38, String extString39, String extString40,
                         String extString41, String extString42, String extString43,
                         String extString44, String extString45, String extString46,
                         String extString47, String extString48, String extString49,
                         String extString50, String extString51, String extString52,
                         String extString53, String extString54, String extString55,
                         String extString56, String extString57, String extString58,
                         String extString59, String extString60) {
        super();
        this.account = account;
        this.deptCode = deptCode;
        this.extFieldId = extFieldId;
        this.customerId = customerId;
        this.extDate01 = extDate01;
        this.extDate02 = extDate02;
        this.extDate03 = extDate03;
        this.extDate04 = extDate04;
        this.extDate05 = extDate05;
        this.extDate06 = extDate06;
        this.extDate07 = extDate07;
        this.extDate08 = extDate08;
        this.extDate09 = extDate09;
        this.extDate10 = extDate10;
        this.extDate11 = extDate11;
        this.extDate12 = extDate12;
        this.extDate13 = extDate13;
        this.extDate14 = extDate14;
        this.extDate15 = extDate15;
        this.extDate16 = extDate16;
        this.extDate17 = extDate17;
        this.extDate18 = extDate18;
        this.extDate19 = extDate19;
        this.extDate20 = extDate20;
        this.extFloat01 = extFloat01;
        this.extFloat02 = extFloat02;
        this.extFloat03 = extFloat03;
        this.extFloat04 = extFloat04;
        this.extFloat05 = extFloat05;
        this.extFloat06 = extFloat06;
        this.extFloat07 = extFloat07;
        this.extFloat08 = extFloat08;
        this.extFloat09 = extFloat09;
        this.extFloat10 = extFloat10;
        this.extFloat11 = extFloat11;
        this.extFloat12 = extFloat12;
        this.extFloat13 = extFloat13;
        this.extFloat14 = extFloat14;
        this.extFloat15 = extFloat15;
        this.extFloat16 = extFloat16;
        this.extFloat17 = extFloat17;
        this.extFloat18 = extFloat18;
        this.extFloat19 = extFloat19;
        this.extFloat20 = extFloat20;
        this.extLongString01 = extLongString01;
        this.extLongString02 = extLongString02;
        this.extLongString03 = extLongString03;
        this.extLongString04 = extLongString04;
        this.extLongString05 = extLongString05;
        this.extLongString06 = extLongString06;
        this.extLongString07 = extLongString07;
        this.extLongString08 = extLongString08;
        this.extLongString09 = extLongString09;
        this.extLongString10 = extLongString10;
        this.extString11 = extString11;
        this.extString12 = extString12;
        this.extString13 = extString13;
        this.extString14 = extString14;
        this.extString15 = extString15;
        this.extString16 = extString16;
        this.extString17 = extString17;
        this.extString18 = extString18;
        this.extString19 = extString19;
        this.extString20 = extString20;
        this.extString21 = extString21;
        this.extString22 = extString22;
        this.extString23 = extString23;
        this.extString24 = extString24;
        this.extString25 = extString25;
        this.extString26 = extString26;
        this.extString27 = extString27;
        this.extString28 = extString28;
        this.extString29 = extString29;
        this.extString30 = extString30;
        this.extString31 = extString31;
        this.extString32 = extString32;
        this.extString33 = extString33;
        this.extString34 = extString34;
        this.extString35 = extString35;
        this.extString36 = extString36;
        this.extString37 = extString37;
        this.extString38 = extString38;
        this.extString39 = extString39;
        this.extString40 = extString40;
        this.extString41 = extString41;
        this.extString42 = extString42;
        this.extString43 = extString43;
        this.extString44 = extString44;
        this.extString45 = extString45;
        this.extString46 = extString46;
        this.extString47 = extString47;
        this.extString48 = extString48;
        this.extString49 = extString49;
        this.extString50 = extString50;
        this.extString51 = extString51;
        this.extString52 = extString52;
        this.extString53 = extString53;
        this.extString54 = extString54;
        this.extString55 = extString55;
        this.extString56 = extString56;
        this.extString57 = extString57;
        this.extString58 = extString58;
        this.extString59 = extString59;
        this.extString60 = extString60;
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1453461617, 1748355341).appendSuper(super.hashCode())
            .append(this.extFieldId).append(this.extDate11).append(this.extString46)
            .append(this.extDate10).append(this.extString47).append(this.extString48)
            .append(this.extString49).append(this.extString42).append(this.extString43)
            .append(this.extString44).append(this.extString45).append(this.extDate18)
            .append(this.extDate19).append(this.extDate16).append(this.extString41)
            .append(this.extDate17).append(this.extString40).append(this.extDate14)
            .append(this.extDate15).append(this.extDate12).append(this.extDate13)
            .append(this.extString59).append(this.extDate20).append(this.extString57)
            .append(this.extLongString10).append(this.extString58).append(this.extString55)
            .append(this.extString56).append(this.extString53).append(this.extString54)
            .append(this.extString52).append(this.extString51).append(this.extString50)
            .append(this.extFloat19).append(this.extFloat18).append(this.extFloat17)
            .append(this.extFloat16).append(this.extLongString01).append(this.extLongString02)
            .append(this.extLongString03).append(this.extLongString04).append(this.extLongString05)
            .append(this.customerId).append(this.extLongString06).append(this.extString60)
            .append(this.extLongString07).append(this.extLongString08).append(this.extLongString09)
            .append(this.extFloat20).append(this.extFloat06).append(this.extFloat05)
            .append(this.extFloat08).append(this.extFloat07).append(this.extFloat09)
            .append(this.extDate01).append(this.extDate02).append(this.extDate03)
            .append(this.extDate04).append(this.extDate05).append(this.extDate06)
            .append(this.extDate07).append(this.extDate08).append(this.extDate09)
            .append(this.account).append(this.extFloat10).append(this.extFloat11)
            .append(this.deptCode).append(this.extFloat12).append(this.extFloat02)
            .append(this.extFloat13).append(this.extFloat01).append(this.extFloat14)
            .append(this.extFloat04).append(this.extFloat15).append(this.extFloat03)
            .append(this.extString16).append(this.extString15).append(this.extString14)
            .append(this.extString13).append(this.extString12).append(this.extString11)
            .append(this.extString19).append(this.extString18).append(this.extString17)
            .append(this.extString21).append(this.extString20).append(this.extString23)
            .append(this.extString22).append(this.extString25).append(this.extString24)
            .append(this.extString27).append(this.extString26).append(this.extString29)
            .append(this.extString28).append(this.extString34).append(this.extString33)
            .append(this.extString32).append(this.extString31).append(this.extString38)
            .append(this.extString37).append(this.extString36).append(this.extString35)
            .append(this.extString39).append(this.extString30).toHashCode();
    }
    
}
