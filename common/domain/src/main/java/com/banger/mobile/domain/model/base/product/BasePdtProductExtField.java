/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :QianJie
 * Create Date:Jul 20, 2012
 */
package com.banger.mobile.domain.model.base.product;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.banger.mobile.domain.model.base.BaseObject;

/**
 * @author QianJie
 * @version $Id: BasePdtProductExtField.java,v 0.1 Jul 20, 2012 11:25:56 AM QianJie Exp $
 */
public class BasePdtProductExtField extends BaseObject implements Serializable{

    private static final long serialVersionUID = -7704081272116239294L;
    private Integer           extFieldId;                              //主键
    private Integer           productId;                               //产品ID
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
    private Date              createDate;                              //创建时间
    private Date              updateDate;                              //更新时间
    private Integer           createUser;                              //创建用户
    private Integer           updateUser;                              //更新用户
    
    public BasePdtProductExtField() {
        super();
    }
    
    public BasePdtProductExtField(Integer extFieldId, Integer productId, Date extDate01, Date extDate02,
                                   Date extDate03, Date extDate04, Date extDate05, Date extDate06,
                                   Date extDate07, Date extDate08, Date extDate09, Date extDate10,
                                   Date extDate11, Date extDate12, Date extDate13, Date extDate14,
                                   Date extDate15, Date extDate16, Date extDate17, Date extDate18,
                                   Date extDate19, Date extDate20, Double extDouble01,
                                   Double extDouble02, Double extDouble03, Double extDouble04,
                                   Double extDouble05, Double extDouble06, Double extDouble07,
                                   Double extDouble08, Double extDouble09, Double extDouble10,
                                   Double extDouble11, Double extDouble12, Double extDouble13,
                                   Double extDouble14, Double extDouble15, Double extDouble16,
                                   Double extDouble17, Double extDouble18, Double extDouble19,
                                   Double extDouble20, String extLongString01,
                                   String extLongString02, String extLongString03,
                                   String extLongString04, String extLongString05,
                                   String extLongString06, String extLongString07,
                                   String extLongString08, String extLongString09,
                                   String extLongString10, String extString11, String extString12,
                                   String extString13, String extString14, String extString15,
                                   String extString16, String extString17, String extString18,
                                   String extString19, String extString20, String extString21,
                                   String extString22, String extString23, String extString24,
                                   String extString25, String extString26, String extString27,
                                   String extString28, String extString29, String extString30,
                                   String extString31, String extString32, String extString33,
                                   String extString34, String extString35, String extString36,
                                   String extString37, String extString38, String extString39,
                                   String extString40, String extString41, String extString42,
                                   String extString43, String extString44, String extString45,
                                   String extString46, String extString47, String extString48,
                                   String extString49, String extString50, String extString51,
                                   String extString52, String extString53, String extString54,
                                   String extString55, String extString56, String extString57,
                                   String extString58, String extString59, String extString60,
                                   Date createDate, Date updateDate, Integer createUser, Integer updateUser) {
        super();
        this.extFieldId = extFieldId;
        this.productId = productId;
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
        this.extFloat01 = extDouble01;
        this.extFloat02 = extDouble02;
        this.extFloat03 = extDouble03;
        this.extFloat04 = extDouble04;
        this.extFloat05 = extDouble05;
        this.extFloat06 = extDouble06;
        this.extFloat07 = extDouble07;
        this.extFloat08 = extDouble08;
        this.extFloat09 = extDouble09;
        this.extFloat10 = extDouble10;
        this.extFloat11 = extDouble11;
        this.extFloat12 = extDouble12;
        this.extFloat13 = extDouble13;
        this.extFloat14 = extDouble14;
        this.extFloat15 = extDouble15;
        this.extFloat16 = extDouble16;
        this.extFloat17 = extDouble17;
        this.extFloat18 = extDouble18;
        this.extFloat19 = extDouble19;
        this.extFloat20 = extDouble20;
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
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.createUser = createUser;
        this.updateUser = updateUser;
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

    public Integer getExtFieldId() {
        return extFieldId;
    }
    public void setExtFieldId(Integer extFieldId) {
        this.extFieldId = extFieldId;
    }
    public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
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

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BasePdtProductExtField)) {
            return false;
        }
        BasePdtProductExtField rhs = (BasePdtProductExtField) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.extFloat16,
            rhs.extFloat16).append(this.extString13, rhs.extString13).append(this.extString24,
            rhs.extString24).append(this.extFloat15, rhs.extFloat15).append(this.extString34,
            rhs.extString34).append(this.extFloat20, rhs.extFloat20).append(this.extString23,
            rhs.extString23).append(this.extDate19, rhs.extDate19).append(this.extString40,
            rhs.extString40).append(this.extDate08, rhs.extDate08).append(this.extString22,
            rhs.extString22).append(this.extString44, rhs.extString44).append(this.extFieldId,
            rhs.extFieldId).append(this.extDate07, rhs.extDate07).append(this.extFloat10,
            rhs.extFloat10).append(this.extString54, rhs.extString54).append(this.extString27,
            rhs.extString27).append(this.extString49, rhs.extString49).append(this.extString26,
            rhs.extString26).append(this.updateUser, rhs.updateUser).append(this.extString11,
            rhs.extString11).append(this.extString58, rhs.extString58).append(this.extString52,
            rhs.extString52).append(this.extDate17, rhs.extDate17).append(this.extDate11,
            rhs.extDate11).append(this.extString46, rhs.extString46).append(this.extString35,
            rhs.extString35).append(this.extString42, rhs.extString42).append(this.extString12,
            rhs.extString12).append(this.extString56, rhs.extString56).append(this.extLongString06,
            rhs.extLongString06).append(this.extString37, rhs.extString37).append(this.extString50,
            rhs.extString50).append(this.extString59, rhs.extString59).append(this.extLongString05,
            rhs.extLongString05).append(this.extLongString02, rhs.extLongString02).append(
            this.extFloat13, rhs.extFloat13).append(this.extString41, rhs.extString41).append(
            this.extString47, rhs.extString47).append(this.extDate06, rhs.extDate06).append(
            this.extString32, rhs.extString32).append(this.extFloat19, rhs.extFloat19).append(
            this.extFloat08, rhs.extFloat08).append(this.extString39, rhs.extString39).append(
            this.extString60, rhs.extString60).append(this.extFloat02, rhs.extFloat02).append(
            this.extLongString03, rhs.extLongString03).append(this.extString38, rhs.extString38)
            .append(this.extString53, rhs.extString53).append(this.extString51, rhs.extString51)
            .append(this.extLongString01, rhs.extLongString01).append(this.extFloat14,
                rhs.extFloat14).append(this.extFloat05, rhs.extFloat05).append(this.createUser,
                rhs.createUser).append(this.extString48, rhs.extString48).append(this.extString33,
                rhs.extString33).append(this.extFloat07, rhs.extFloat07).append(this.extFloat03,
                rhs.extFloat03).append(this.extString30, rhs.extString30).append(
                this.extLongString04, rhs.extLongString04)
            .append(this.extString57, rhs.extString57).append(this.extString31, rhs.extString31)
            .append(this.extString20, rhs.extString20).append(this.extString29, rhs.extString29)
            .append(this.extLongString09, rhs.extLongString09).append(this.extLongString08,
                rhs.extLongString08).append(this.extFloat09, rhs.extFloat09).append(this.extDate03,
                rhs.extDate03).append(this.extFloat17, rhs.extFloat17).append(this.extFloat01,
                rhs.extFloat01).append(this.extFloat18, rhs.extFloat18).append(this.extString25,
                rhs.extString25).append(this.extDate05, rhs.extDate05).append(this.extDate12,
                rhs.extDate12).append(this.extDate01, rhs.extDate01).append(this.extString18,
                rhs.extString18).append(this.updateDate, rhs.updateDate).append(this.extFloat12,
                rhs.extFloat12).append(this.extDate04, rhs.extDate04).append(this.extFloat11,
                rhs.extFloat11).append(this.extDate14, rhs.extDate14).append(this.extFloat06,
                rhs.extFloat06).append(this.createDate, rhs.createDate).append(this.extString55,
                rhs.extString55).append(this.extFloat04, rhs.extFloat04).append(this.extString14,
                rhs.extString14).append(this.extString36, rhs.extString36).append(
                this.extLongString10, rhs.extLongString10)
            .append(this.extString21, rhs.extString21).append(this.extDate02, rhs.extDate02)
            .append(this.extString43, rhs.extString43).append(this.extString45, rhs.extString45)
            .append(this.extString15, rhs.extString15).append(this.extLongString07,
                rhs.extLongString07).append(this.extDate18, rhs.extDate18).append(this.extDate10,
                rhs.extDate10).append(this.extString17, rhs.extString17).append(this.extString28,
                rhs.extString28).append(this.extDate16, rhs.extDate16).append(this.extDate13,
                rhs.extDate13).append(this.productId, rhs.productId).append(this.extDate15,
                rhs.extDate15).append(this.extString16, rhs.extString16).append(this.extString19,
                rhs.extString19).append(this.extDate09, rhs.extDate09).append(this.extDate20,
                rhs.extDate20).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1451891519, 1219946697).appendSuper(super.hashCode()).append(
            this.extFloat16).append(this.extString13).append(this.extString24).append(
            this.extFloat15).append(this.extString34).append(this.extFloat20).append(
            this.extString23).append(this.extDate19).append(this.extString40)
            .append(this.extDate08).append(this.extString22).append(this.extString44).append(
                this.extFieldId).append(this.extDate07).append(this.extFloat10).append(
                this.extString54).append(this.extString27).append(this.extString49).append(
                this.extString26).append(this.updateUser).append(this.extString11).append(
                this.extString58).append(this.extString52).append(this.extDate17).append(
                this.extDate11).append(this.extString46).append(this.extString35).append(
                this.extString42).append(this.extString12).append(this.extString56).append(
                this.extLongString06).append(this.extString37).append(this.extString50).append(
                this.extString59).append(this.extLongString05).append(this.extLongString02).append(
                this.extFloat13).append(this.extString41).append(this.extString47).append(
                this.extDate06).append(this.extString32).append(this.extFloat19).append(
                this.extFloat08).append(this.extString39).append(this.extString60).append(
                this.extFloat02).append(this.extLongString03).append(this.extString38).append(
                this.extString53).append(this.extString51).append(this.extLongString01).append(
                this.extFloat14).append(this.extFloat05).append(this.createUser).append(
                this.extString48).append(this.extString33).append(this.extFloat07).append(
                this.extFloat03).append(this.extString30).append(this.extLongString04).append(
                this.extString57).append(this.extString31).append(this.extString20).append(
                this.extString29).append(this.extLongString09).append(this.extLongString08).append(
                this.extFloat09).append(this.extDate03).append(this.extFloat17).append(
                this.extFloat01).append(this.extFloat18).append(this.extString25).append(
                this.extDate05).append(this.extDate12).append(this.extDate01).append(
                this.extString18).append(this.updateDate).append(this.extFloat12).append(
                this.extDate04).append(this.extFloat11).append(this.extDate14).append(
                this.extFloat06).append(this.createDate).append(this.extString55).append(
                this.extFloat04).append(this.extString14).append(this.extString36).append(
                this.extLongString10).append(this.extString21).append(this.extDate02).append(
                this.extString43).append(this.extString45).append(this.extString15).append(
                this.extLongString07).append(this.extDate18).append(this.extDate10).append(
                this.extString17).append(this.extString28).append(this.extDate16).append(
                this.extDate13).append(this.productId).append(this.extDate15).append(
                this.extString16).append(this.extString19).append(this.extDate09).append(
                this.extDate20).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("extString14", this.extString14).append(
            "extString29", this.extString29).append("extFloat19", this.extFloat19).append(
            "extString15", this.extString15).append("updateUser", this.updateUser).append(
            "extDate05", this.extDate05).append("extString12", this.extString12).append(
            "extDate17", this.extDate17).append("extString55", this.extString55).append(
            "extFloat01", this.extFloat01).append("extString43", this.extString43).append(
            "extFloat08", this.extFloat08).append("customerId", this.productId).append(
            "extString53", this.extString53).append("extString11", this.extString11).append(
            "extLongString05", this.extLongString05).append("extString30", this.extString30)
            .append("extString49", this.extString49).append("extDate01", this.extDate01).append(
                "extDate10", this.extDate10).append("extLongString04", this.extLongString04)
            .append("extFloat18", this.extFloat18).append("extString39", this.extString39).append(
                "extString40", this.extString40).append("extString54", this.extString54).append(
                "extDate14", this.extDate14).append("extLongString09", this.extLongString09)
            .append("extString42", this.extString42).append("endRow", this.getEndRow()).append(
                "extFloat20", this.extFloat20).append("extDate09", this.extDate09).append(
                "extString44", this.extString44).append("extFieldId", this.extFieldId).append(
                "extString33", this.extString33).append("extDate08", this.extDate08).append(
                "startRow", this.getStartRow()).append("extDate11", this.extDate11).append(
                "extString31", this.extString31).append("extString52", this.extString52).append(
                "extString41", this.extString41).append("extString48", this.extString48).append(
                "extString47", this.extString47).append("extString13", this.extString13).append(
                "extDate18", this.extDate18).append("extString35", this.extString35).append(
                "extDate15", this.extDate15).append("extString57", this.extString57).append(
                "extString23", this.extString23).append("extString34", this.extString34).append(
                "extString56", this.extString56).append("extDate16", this.extDate16).append(
                "extString45", this.extString45).append("createUser", this.createUser).append(
                "extString22", this.extString22).append("extString58", this.extString58).append(
                "extFloat14", this.extFloat14).append("extString46", this.extString46).append(
                "extFloat03", this.extFloat03).append("extLongString08", this.extLongString08)
            .append("extFloat06", this.extFloat06).append("extDate12", this.extDate12).append(
                "extString59", this.extString59).append("extString60", this.extString60).append(
                "extString19", this.extString19).append("extString37", this.extString37).append(
                "extDate13", this.extDate13).append("extString32", this.extString32).append(
                "extFloat17", this.extFloat17).append("extLongString02", this.extLongString02)
            .append("extString20", this.extString20).append("extDate03", this.extDate03).append(
                "updateDate", this.updateDate).append("extString17", this.extString17).append(
                "extFloat16", this.extFloat16).append("extLongString01", this.extLongString01)
            .append("extFloat09", this.extFloat09).append("extFloat02", this.extFloat02).append(
                "extString16", this.extString16).append("extString38", this.extString38).append(
                "extFloat11", this.extFloat11).append("extDate19", this.extDate19).append(
                "extDate20", this.extDate20).append("extString18", this.extString18).append(
                "extString36", this.extString36).append("extString50", this.extString50).append(
                "extLongString03", this.extLongString03).append("extDate06", this.extDate06)
            .append("extString21", this.extString21).append("extFloat13", this.extFloat13).append(
                "extString51", this.extString51).append("extString24", this.extString24).append(
                "extDate07", this.extDate07).append("extDate04", this.extDate04).append(
                "extFloat12", this.extFloat12).append("extLongString10", this.extLongString10)
            .append("extLongString07", this.extLongString07).append("extFloat05", this.extFloat05)
            .append("extString27", this.extString27).append("extFloat07", this.extFloat07).append(
                "extString26", this.extString26).append("extFloat15", this.extFloat15).append(
                "extFloat04", this.extFloat04).append("extLongString06", this.extLongString06)
            .append("extString28", this.extString28).append("extDate02", this.extDate02).append(
                "extString25", this.extString25).append("createDate", this.createDate).append(
                "extFloat10", this.extFloat10).toString();
    }
    

}
