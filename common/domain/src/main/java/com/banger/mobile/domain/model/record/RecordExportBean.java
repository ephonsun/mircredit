/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-9-11
 */
package com.banger.mobile.domain.model.record;

import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * @author cheny
 * @version $Id: RecordExportBean.java,v 0.1 2012-9-11 上午9:32:42 cheny Exp $
 */
public class RecordExportBean extends RecordInfo{

    private static final long serialVersionUID = -98084596792337605L;

    private String            callTypeName;                            //联系类型全称
    private String            bizTypeName;                             //业务类型名称
    private String            recordSourceName;                        //录音记录来源
    private String            isCanceledName;                          //是否有效
    private String            belongUserName;                          //归属人员用户名
    private String            belongUserAccount;                           //归属人员账号
    private String            belongDeptCode;                          //归属人员部门编号
    private String            belongDeptName;                          //归属人员部门姓名
    private String            customerTypeName;                        //客户类型
    private String            customerIndustryName;                    //所处行业
    
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
 
    private String customerNamePinyin;      //拼音缩写
    private String sex;                     //性别
    private String customerNo;              //客户编号
    private String customerTitle;           //称谓
    private Integer age;                    //年龄
    private String custIdCard;              //身份证号
    private Date birthday;                  //生日
    private Integer customerTypeId;         //客户类型ID
    private Integer customerIndustryId;     //所处行业ID
    private String company;                 //单位
    private String custRemark;                  //简介
    private String headshow;                //头像
    private String province;                //省份
    private String city;                    //城市
    private String address;                 //地址
    private String mobilePhone1Remark;        //手机1备注
    private String mobilePhone2Remark;        //手机2备注
    private String email;                   //邮箱
    private String templateIds;             //业务ID
    private Integer isTrash;                //是否在垃圾箱
    private Integer belongDeptId;           //归属机构ID
    private Integer belongUserId;           //归属人员ID
    private Integer isVisit;                //是否是拜访客户
    private Date lastContactDate;           //最后联系时间
    private Integer isReceiveSms;           //是否愿意接收短信
    private String mobilePhone1Regular;     //手机1号码规则
    private String mobilePhone2Regular;     //手机2号码规刚
    
    private Date custCreateDate;            //新建时间
    private Date custUpdateDate;            //修改时间
    private Integer custCreateUser;         //新建者
    private Integer custUpdateUser;         //修改者
    /**
     * 
     */
    public RecordExportBean() {
        super();
    }
	public String getCallTypeName() {
		return callTypeName;
	}
	public void setCallTypeName(String callTypeName) {
		this.callTypeName = callTypeName;
	}
	public String getBizTypeName() {
		return bizTypeName;
	}
	public void setBizTypeName(String bizTypeName) {
		this.bizTypeName = bizTypeName;
	}
	public String getRecordSourceName() {
		return recordSourceName;
	}
	public void setRecordSourceName(String recordSourceName) {
		this.recordSourceName = recordSourceName;
	}
	public String getIsCanceledName() {
		return isCanceledName;
	}
	public void setIsCanceledName(String isCanceledName) {
		this.isCanceledName = isCanceledName;
	}
	public String getBelongUserName() {
		return belongUserName;
	}
	public void setBelongUserName(String belongUserName) {
		this.belongUserName = belongUserName;
	}
	public String getBelongUserAccount() {
		return belongUserAccount;
	}
	public void setBelongUserAccount(String belongUserAccount) {
		this.belongUserAccount = belongUserAccount;
	}
	public String getBelongDeptCode() {
		return belongDeptCode;
	}
	public void setBelongDeptCode(String belongDeptCode) {
		this.belongDeptCode = belongDeptCode;
	}
	public String getBelongDeptName() {
		return belongDeptName;
	}
	public void setBelongDeptName(String belongDeptName) {
		this.belongDeptName = belongDeptName;
	}
	public String getCustomerTypeName() {
		return customerTypeName;
	}
	public void setCustomerTypeName(String customerTypeName) {
		this.customerTypeName = customerTypeName;
	}
	public String getCustomerIndustryName() {
		return customerIndustryName;
	}
	public void setCustomerIndustryName(String customerIndustryName) {
		this.customerIndustryName = customerIndustryName;
	}
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
	public String getCustomerNamePinyin() {
		return customerNamePinyin;
	}
	public void setCustomerNamePinyin(String customerNamePinyin) {
		this.customerNamePinyin = customerNamePinyin;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
	public String getCustomerTitle() {
		return customerTitle;
	}
	public void setCustomerTitle(String customerTitle) {
		this.customerTitle = customerTitle;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getCustIdCard() {
		return custIdCard;
	}
	public void setCustIdCard(String custIdCard) {
		this.custIdCard = custIdCard;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Integer getCustomerTypeId() {
		return customerTypeId;
	}
	public void setCustomerTypeId(Integer customerTypeId) {
		this.customerTypeId = customerTypeId;
	}
	public Integer getCustomerIndustryId() {
		return customerIndustryId;
	}
	public void setCustomerIndustryId(Integer customerIndustryId) {
		this.customerIndustryId = customerIndustryId;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getCustRemark() {
		return custRemark;
	}
	public void setCustRemark(String custRemark) {
		this.custRemark = custRemark;
	}
	public String getHeadshow() {
		return headshow;
	}
	public void setHeadshow(String headshow) {
		this.headshow = headshow;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobilePhone1Remark() {
		return mobilePhone1Remark;
	}
	public void setMobilePhone1Remark(String mobilePhone1Remark) {
		this.mobilePhone1Remark = mobilePhone1Remark;
	}
	public String getMobilePhone2Remark() {
		return mobilePhone2Remark;
	}
	public void setMobilePhone2Remark(String mobilePhone2Remark) {
		this.mobilePhone2Remark = mobilePhone2Remark;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTemplateIds() {
		return templateIds;
	}
	public void setTemplateIds(String templateIds) {
		this.templateIds = templateIds;
	}
	public Integer getIsTrash() {
		return isTrash;
	}
	public void setIsTrash(Integer isTrash) {
		this.isTrash = isTrash;
	}
	public Integer getBelongDeptId() {
		return belongDeptId;
	}
	public void setBelongDeptId(Integer belongDeptId) {
		this.belongDeptId = belongDeptId;
	}
	public Integer getBelongUserId() {
		return belongUserId;
	}
	public void setBelongUserId(Integer belongUserId) {
		this.belongUserId = belongUserId;
	}
	public Integer getIsVisit() {
		return isVisit;
	}
	public void setIsVisit(Integer isVisit) {
		this.isVisit = isVisit;
	}
	public Date getLastContactDate() {
		return lastContactDate;
	}
	public void setLastContactDate(Date lastContactDate) {
		this.lastContactDate = lastContactDate;
	}
	public Integer getIsReceiveSms() {
		return isReceiveSms;
	}
	public void setIsReceiveSms(Integer isReceiveSms) {
		this.isReceiveSms = isReceiveSms;
	}
	public String getMobilePhone1Regular() {
		return mobilePhone1Regular;
	}
	public void setMobilePhone1Regular(String mobilePhone1Regular) {
		this.mobilePhone1Regular = mobilePhone1Regular;
	}
	public String getMobilePhone2Regular() {
		return mobilePhone2Regular;
	}
	public void setMobilePhone2Regular(String mobilePhone2Regular) {
		this.mobilePhone2Regular = mobilePhone2Regular;
	}
	public Date getCustCreateDate() {
		return custCreateDate;
	}
	public void setCustCreateDate(Date custCreateDate) {
		this.custCreateDate = custCreateDate;
	}
	public Date getCustUpdateDate() {
		return custUpdateDate;
	}
	public void setCustUpdateDate(Date custUpdateDate) {
		this.custUpdateDate = custUpdateDate;
	}
	public Integer getCustCreateUser() {
		return custCreateUser;
	}
	public void setCustCreateUser(Integer custCreateUser) {
		this.custCreateUser = custCreateUser;
	}
	public Integer getCustUpdateUser() {
		return custUpdateUser;
	}
	public void setCustUpdateUser(Integer custUpdateUser) {
		this.custUpdateUser = custUpdateUser;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result
				+ ((belongDeptCode == null) ? 0 : belongDeptCode.hashCode());
		result = prime * result
				+ ((belongDeptId == null) ? 0 : belongDeptId.hashCode());
		result = prime * result
				+ ((belongDeptName == null) ? 0 : belongDeptName.hashCode());
		result = prime
				* result
				+ ((belongUserAccount == null) ? 0 : belongUserAccount
						.hashCode());
		result = prime * result
				+ ((belongUserId == null) ? 0 : belongUserId.hashCode());
		result = prime * result
				+ ((belongUserName == null) ? 0 : belongUserName.hashCode());
		result = prime * result
				+ ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result
				+ ((bizTypeName == null) ? 0 : bizTypeName.hashCode());
		result = prime * result
				+ ((callTypeName == null) ? 0 : callTypeName.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result
				+ ((custCreateDate == null) ? 0 : custCreateDate.hashCode());
		result = prime * result
				+ ((custCreateUser == null) ? 0 : custCreateUser.hashCode());
		result = prime * result
				+ ((custIdCard == null) ? 0 : custIdCard.hashCode());
		result = prime * result
				+ ((custRemark == null) ? 0 : custRemark.hashCode());
		result = prime * result
				+ ((custUpdateDate == null) ? 0 : custUpdateDate.hashCode());
		result = prime * result
				+ ((custUpdateUser == null) ? 0 : custUpdateUser.hashCode());
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime
				* result
				+ ((customerIndustryId == null) ? 0 : customerIndustryId
						.hashCode());
		result = prime
				* result
				+ ((customerIndustryName == null) ? 0 : customerIndustryName
						.hashCode());
		result = prime
				* result
				+ ((customerNamePinyin == null) ? 0 : customerNamePinyin
						.hashCode());
		result = prime * result
				+ ((customerNo == null) ? 0 : customerNo.hashCode());
		result = prime * result
				+ ((customerTitle == null) ? 0 : customerTitle.hashCode());
		result = prime * result
				+ ((customerTypeId == null) ? 0 : customerTypeId.hashCode());
		result = prime
				* result
				+ ((customerTypeName == null) ? 0 : customerTypeName.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((extDate01 == null) ? 0 : extDate01.hashCode());
		result = prime * result
				+ ((extDate02 == null) ? 0 : extDate02.hashCode());
		result = prime * result
				+ ((extDate03 == null) ? 0 : extDate03.hashCode());
		result = prime * result
				+ ((extDate04 == null) ? 0 : extDate04.hashCode());
		result = prime * result
				+ ((extDate05 == null) ? 0 : extDate05.hashCode());
		result = prime * result
				+ ((extDate06 == null) ? 0 : extDate06.hashCode());
		result = prime * result
				+ ((extDate07 == null) ? 0 : extDate07.hashCode());
		result = prime * result
				+ ((extDate08 == null) ? 0 : extDate08.hashCode());
		result = prime * result
				+ ((extDate09 == null) ? 0 : extDate09.hashCode());
		result = prime * result
				+ ((extDate10 == null) ? 0 : extDate10.hashCode());
		result = prime * result
				+ ((extDate11 == null) ? 0 : extDate11.hashCode());
		result = prime * result
				+ ((extDate12 == null) ? 0 : extDate12.hashCode());
		result = prime * result
				+ ((extDate13 == null) ? 0 : extDate13.hashCode());
		result = prime * result
				+ ((extDate14 == null) ? 0 : extDate14.hashCode());
		result = prime * result
				+ ((extDate15 == null) ? 0 : extDate15.hashCode());
		result = prime * result
				+ ((extDate16 == null) ? 0 : extDate16.hashCode());
		result = prime * result
				+ ((extDate17 == null) ? 0 : extDate17.hashCode());
		result = prime * result
				+ ((extDate18 == null) ? 0 : extDate18.hashCode());
		result = prime * result
				+ ((extDate19 == null) ? 0 : extDate19.hashCode());
		result = prime * result
				+ ((extDate20 == null) ? 0 : extDate20.hashCode());
		result = prime * result
				+ ((extFieldId == null) ? 0 : extFieldId.hashCode());
		result = prime * result
				+ ((extFloat01 == null) ? 0 : extFloat01.hashCode());
		result = prime * result
				+ ((extFloat02 == null) ? 0 : extFloat02.hashCode());
		result = prime * result
				+ ((extFloat03 == null) ? 0 : extFloat03.hashCode());
		result = prime * result
				+ ((extFloat04 == null) ? 0 : extFloat04.hashCode());
		result = prime * result
				+ ((extFloat05 == null) ? 0 : extFloat05.hashCode());
		result = prime * result
				+ ((extFloat06 == null) ? 0 : extFloat06.hashCode());
		result = prime * result
				+ ((extFloat07 == null) ? 0 : extFloat07.hashCode());
		result = prime * result
				+ ((extFloat08 == null) ? 0 : extFloat08.hashCode());
		result = prime * result
				+ ((extFloat09 == null) ? 0 : extFloat09.hashCode());
		result = prime * result
				+ ((extFloat10 == null) ? 0 : extFloat10.hashCode());
		result = prime * result
				+ ((extFloat11 == null) ? 0 : extFloat11.hashCode());
		result = prime * result
				+ ((extFloat12 == null) ? 0 : extFloat12.hashCode());
		result = prime * result
				+ ((extFloat13 == null) ? 0 : extFloat13.hashCode());
		result = prime * result
				+ ((extFloat14 == null) ? 0 : extFloat14.hashCode());
		result = prime * result
				+ ((extFloat15 == null) ? 0 : extFloat15.hashCode());
		result = prime * result
				+ ((extFloat16 == null) ? 0 : extFloat16.hashCode());
		result = prime * result
				+ ((extFloat17 == null) ? 0 : extFloat17.hashCode());
		result = prime * result
				+ ((extFloat18 == null) ? 0 : extFloat18.hashCode());
		result = prime * result
				+ ((extFloat19 == null) ? 0 : extFloat19.hashCode());
		result = prime * result
				+ ((extFloat20 == null) ? 0 : extFloat20.hashCode());
		result = prime * result
				+ ((extLongString01 == null) ? 0 : extLongString01.hashCode());
		result = prime * result
				+ ((extLongString02 == null) ? 0 : extLongString02.hashCode());
		result = prime * result
				+ ((extLongString03 == null) ? 0 : extLongString03.hashCode());
		result = prime * result
				+ ((extLongString04 == null) ? 0 : extLongString04.hashCode());
		result = prime * result
				+ ((extLongString05 == null) ? 0 : extLongString05.hashCode());
		result = prime * result
				+ ((extLongString06 == null) ? 0 : extLongString06.hashCode());
		result = prime * result
				+ ((extLongString07 == null) ? 0 : extLongString07.hashCode());
		result = prime * result
				+ ((extLongString08 == null) ? 0 : extLongString08.hashCode());
		result = prime * result
				+ ((extLongString09 == null) ? 0 : extLongString09.hashCode());
		result = prime * result
				+ ((extLongString10 == null) ? 0 : extLongString10.hashCode());
		result = prime * result
				+ ((extLongString11 == null) ? 0 : extLongString11.hashCode());
		result = prime * result
				+ ((extLongString12 == null) ? 0 : extLongString12.hashCode());
		result = prime * result
				+ ((extLongString13 == null) ? 0 : extLongString13.hashCode());
		result = prime * result
				+ ((extLongString14 == null) ? 0 : extLongString14.hashCode());
		result = prime * result
				+ ((extLongString15 == null) ? 0 : extLongString15.hashCode());
		result = prime * result
				+ ((extLongString16 == null) ? 0 : extLongString16.hashCode());
		result = prime * result
				+ ((extLongString17 == null) ? 0 : extLongString17.hashCode());
		result = prime * result
				+ ((extLongString18 == null) ? 0 : extLongString18.hashCode());
		result = prime * result
				+ ((extLongString19 == null) ? 0 : extLongString19.hashCode());
		result = prime * result
				+ ((extLongString20 == null) ? 0 : extLongString20.hashCode());
		result = prime * result
				+ ((extString01 == null) ? 0 : extString01.hashCode());
		result = prime * result
				+ ((extString02 == null) ? 0 : extString02.hashCode());
		result = prime * result
				+ ((extString03 == null) ? 0 : extString03.hashCode());
		result = prime * result
				+ ((extString04 == null) ? 0 : extString04.hashCode());
		result = prime * result
				+ ((extString05 == null) ? 0 : extString05.hashCode());
		result = prime * result
				+ ((extString06 == null) ? 0 : extString06.hashCode());
		result = prime * result
				+ ((extString07 == null) ? 0 : extString07.hashCode());
		result = prime * result
				+ ((extString08 == null) ? 0 : extString08.hashCode());
		result = prime * result
				+ ((extString09 == null) ? 0 : extString09.hashCode());
		result = prime * result
				+ ((extString10 == null) ? 0 : extString10.hashCode());
		result = prime * result
				+ ((extString11 == null) ? 0 : extString11.hashCode());
		result = prime * result
				+ ((extString12 == null) ? 0 : extString12.hashCode());
		result = prime * result
				+ ((extString13 == null) ? 0 : extString13.hashCode());
		result = prime * result
				+ ((extString14 == null) ? 0 : extString14.hashCode());
		result = prime * result
				+ ((extString15 == null) ? 0 : extString15.hashCode());
		result = prime * result
				+ ((extString16 == null) ? 0 : extString16.hashCode());
		result = prime * result
				+ ((extString17 == null) ? 0 : extString17.hashCode());
		result = prime * result
				+ ((extString18 == null) ? 0 : extString18.hashCode());
		result = prime * result
				+ ((extString19 == null) ? 0 : extString19.hashCode());
		result = prime * result
				+ ((extString20 == null) ? 0 : extString20.hashCode());
		result = prime * result
				+ ((extString21 == null) ? 0 : extString21.hashCode());
		result = prime * result
				+ ((extString22 == null) ? 0 : extString22.hashCode());
		result = prime * result
				+ ((extString23 == null) ? 0 : extString23.hashCode());
		result = prime * result
				+ ((extString24 == null) ? 0 : extString24.hashCode());
		result = prime * result
				+ ((extString25 == null) ? 0 : extString25.hashCode());
		result = prime * result
				+ ((extString26 == null) ? 0 : extString26.hashCode());
		result = prime * result
				+ ((extString27 == null) ? 0 : extString27.hashCode());
		result = prime * result
				+ ((extString28 == null) ? 0 : extString28.hashCode());
		result = prime * result
				+ ((extString29 == null) ? 0 : extString29.hashCode());
		result = prime * result
				+ ((extString30 == null) ? 0 : extString30.hashCode());
		result = prime * result
				+ ((extString31 == null) ? 0 : extString31.hashCode());
		result = prime * result
				+ ((extString32 == null) ? 0 : extString32.hashCode());
		result = prime * result
				+ ((extString33 == null) ? 0 : extString33.hashCode());
		result = prime * result
				+ ((extString34 == null) ? 0 : extString34.hashCode());
		result = prime * result
				+ ((extString35 == null) ? 0 : extString35.hashCode());
		result = prime * result
				+ ((extString36 == null) ? 0 : extString36.hashCode());
		result = prime * result
				+ ((extString37 == null) ? 0 : extString37.hashCode());
		result = prime * result
				+ ((extString38 == null) ? 0 : extString38.hashCode());
		result = prime * result
				+ ((extString39 == null) ? 0 : extString39.hashCode());
		result = prime * result
				+ ((extString40 == null) ? 0 : extString40.hashCode());
		result = prime * result
				+ ((extString41 == null) ? 0 : extString41.hashCode());
		result = prime * result
				+ ((extString42 == null) ? 0 : extString42.hashCode());
		result = prime * result
				+ ((extString43 == null) ? 0 : extString43.hashCode());
		result = prime * result
				+ ((extString44 == null) ? 0 : extString44.hashCode());
		result = prime * result
				+ ((extString45 == null) ? 0 : extString45.hashCode());
		result = prime * result
				+ ((extString46 == null) ? 0 : extString46.hashCode());
		result = prime * result
				+ ((extString47 == null) ? 0 : extString47.hashCode());
		result = prime * result
				+ ((extString48 == null) ? 0 : extString48.hashCode());
		result = prime * result
				+ ((extString49 == null) ? 0 : extString49.hashCode());
		result = prime * result
				+ ((extString50 == null) ? 0 : extString50.hashCode());
		result = prime * result
				+ ((extString51 == null) ? 0 : extString51.hashCode());
		result = prime * result
				+ ((extString52 == null) ? 0 : extString52.hashCode());
		result = prime * result
				+ ((extString53 == null) ? 0 : extString53.hashCode());
		result = prime * result
				+ ((extString54 == null) ? 0 : extString54.hashCode());
		result = prime * result
				+ ((extString55 == null) ? 0 : extString55.hashCode());
		result = prime * result
				+ ((extString56 == null) ? 0 : extString56.hashCode());
		result = prime * result
				+ ((extString57 == null) ? 0 : extString57.hashCode());
		result = prime * result
				+ ((extString58 == null) ? 0 : extString58.hashCode());
		result = prime * result
				+ ((extString59 == null) ? 0 : extString59.hashCode());
		result = prime * result
				+ ((extString60 == null) ? 0 : extString60.hashCode());
		result = prime * result
				+ ((headshow == null) ? 0 : headshow.hashCode());
		result = prime * result
				+ ((isCanceledName == null) ? 0 : isCanceledName.hashCode());
		result = prime * result
				+ ((isReceiveSms == null) ? 0 : isReceiveSms.hashCode());
		result = prime * result + ((isTrash == null) ? 0 : isTrash.hashCode());
		result = prime * result + ((isVisit == null) ? 0 : isVisit.hashCode());
		result = prime * result
				+ ((lastContactDate == null) ? 0 : lastContactDate.hashCode());
		result = prime
				* result
				+ ((mobilePhone1Regular == null) ? 0 : mobilePhone1Regular
						.hashCode());
		result = prime
				* result
				+ ((mobilePhone1Remark == null) ? 0 : mobilePhone1Remark
						.hashCode());
		result = prime
				* result
				+ ((mobilePhone2Regular == null) ? 0 : mobilePhone2Regular
						.hashCode());
		result = prime
				* result
				+ ((mobilePhone2Remark == null) ? 0 : mobilePhone2Remark
						.hashCode());
		result = prime * result
				+ ((province == null) ? 0 : province.hashCode());
		result = prime
				* result
				+ ((recordSourceName == null) ? 0 : recordSourceName.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result
				+ ((templateIds == null) ? 0 : templateIds.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecordExportBean other = (RecordExportBean) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (belongDeptCode == null) {
			if (other.belongDeptCode != null)
				return false;
		} else if (!belongDeptCode.equals(other.belongDeptCode))
			return false;
		if (belongDeptId == null) {
			if (other.belongDeptId != null)
				return false;
		} else if (!belongDeptId.equals(other.belongDeptId))
			return false;
		if (belongDeptName == null) {
			if (other.belongDeptName != null)
				return false;
		} else if (!belongDeptName.equals(other.belongDeptName))
			return false;
		if (belongUserAccount == null) {
			if (other.belongUserAccount != null)
				return false;
		} else if (!belongUserAccount.equals(other.belongUserAccount))
			return false;
		if (belongUserId == null) {
			if (other.belongUserId != null)
				return false;
		} else if (!belongUserId.equals(other.belongUserId))
			return false;
		if (belongUserName == null) {
			if (other.belongUserName != null)
				return false;
		} else if (!belongUserName.equals(other.belongUserName))
			return false;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (bizTypeName == null) {
			if (other.bizTypeName != null)
				return false;
		} else if (!bizTypeName.equals(other.bizTypeName))
			return false;
		if (callTypeName == null) {
			if (other.callTypeName != null)
				return false;
		} else if (!callTypeName.equals(other.callTypeName))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (custCreateDate == null) {
			if (other.custCreateDate != null)
				return false;
		} else if (!custCreateDate.equals(other.custCreateDate))
			return false;
		if (custCreateUser == null) {
			if (other.custCreateUser != null)
				return false;
		} else if (!custCreateUser.equals(other.custCreateUser))
			return false;
		if (custIdCard == null) {
			if (other.custIdCard != null)
				return false;
		} else if (!custIdCard.equals(other.custIdCard))
			return false;
		if (custRemark == null) {
			if (other.custRemark != null)
				return false;
		} else if (!custRemark.equals(other.custRemark))
			return false;
		if (custUpdateDate == null) {
			if (other.custUpdateDate != null)
				return false;
		} else if (!custUpdateDate.equals(other.custUpdateDate))
			return false;
		if (custUpdateUser == null) {
			if (other.custUpdateUser != null)
				return false;
		} else if (!custUpdateUser.equals(other.custUpdateUser))
			return false;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (customerIndustryId == null) {
			if (other.customerIndustryId != null)
				return false;
		} else if (!customerIndustryId.equals(other.customerIndustryId))
			return false;
		if (customerIndustryName == null) {
			if (other.customerIndustryName != null)
				return false;
		} else if (!customerIndustryName.equals(other.customerIndustryName))
			return false;
		if (customerNamePinyin == null) {
			if (other.customerNamePinyin != null)
				return false;
		} else if (!customerNamePinyin.equals(other.customerNamePinyin))
			return false;
		if (customerNo == null) {
			if (other.customerNo != null)
				return false;
		} else if (!customerNo.equals(other.customerNo))
			return false;
		if (customerTitle == null) {
			if (other.customerTitle != null)
				return false;
		} else if (!customerTitle.equals(other.customerTitle))
			return false;
		if (customerTypeId == null) {
			if (other.customerTypeId != null)
				return false;
		} else if (!customerTypeId.equals(other.customerTypeId))
			return false;
		if (customerTypeName == null) {
			if (other.customerTypeName != null)
				return false;
		} else if (!customerTypeName.equals(other.customerTypeName))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (extDate01 == null) {
			if (other.extDate01 != null)
				return false;
		} else if (!extDate01.equals(other.extDate01))
			return false;
		if (extDate02 == null) {
			if (other.extDate02 != null)
				return false;
		} else if (!extDate02.equals(other.extDate02))
			return false;
		if (extDate03 == null) {
			if (other.extDate03 != null)
				return false;
		} else if (!extDate03.equals(other.extDate03))
			return false;
		if (extDate04 == null) {
			if (other.extDate04 != null)
				return false;
		} else if (!extDate04.equals(other.extDate04))
			return false;
		if (extDate05 == null) {
			if (other.extDate05 != null)
				return false;
		} else if (!extDate05.equals(other.extDate05))
			return false;
		if (extDate06 == null) {
			if (other.extDate06 != null)
				return false;
		} else if (!extDate06.equals(other.extDate06))
			return false;
		if (extDate07 == null) {
			if (other.extDate07 != null)
				return false;
		} else if (!extDate07.equals(other.extDate07))
			return false;
		if (extDate08 == null) {
			if (other.extDate08 != null)
				return false;
		} else if (!extDate08.equals(other.extDate08))
			return false;
		if (extDate09 == null) {
			if (other.extDate09 != null)
				return false;
		} else if (!extDate09.equals(other.extDate09))
			return false;
		if (extDate10 == null) {
			if (other.extDate10 != null)
				return false;
		} else if (!extDate10.equals(other.extDate10))
			return false;
		if (extDate11 == null) {
			if (other.extDate11 != null)
				return false;
		} else if (!extDate11.equals(other.extDate11))
			return false;
		if (extDate12 == null) {
			if (other.extDate12 != null)
				return false;
		} else if (!extDate12.equals(other.extDate12))
			return false;
		if (extDate13 == null) {
			if (other.extDate13 != null)
				return false;
		} else if (!extDate13.equals(other.extDate13))
			return false;
		if (extDate14 == null) {
			if (other.extDate14 != null)
				return false;
		} else if (!extDate14.equals(other.extDate14))
			return false;
		if (extDate15 == null) {
			if (other.extDate15 != null)
				return false;
		} else if (!extDate15.equals(other.extDate15))
			return false;
		if (extDate16 == null) {
			if (other.extDate16 != null)
				return false;
		} else if (!extDate16.equals(other.extDate16))
			return false;
		if (extDate17 == null) {
			if (other.extDate17 != null)
				return false;
		} else if (!extDate17.equals(other.extDate17))
			return false;
		if (extDate18 == null) {
			if (other.extDate18 != null)
				return false;
		} else if (!extDate18.equals(other.extDate18))
			return false;
		if (extDate19 == null) {
			if (other.extDate19 != null)
				return false;
		} else if (!extDate19.equals(other.extDate19))
			return false;
		if (extDate20 == null) {
			if (other.extDate20 != null)
				return false;
		} else if (!extDate20.equals(other.extDate20))
			return false;
		if (extFieldId == null) {
			if (other.extFieldId != null)
				return false;
		} else if (!extFieldId.equals(other.extFieldId))
			return false;
		if (extFloat01 == null) {
			if (other.extFloat01 != null)
				return false;
		} else if (!extFloat01.equals(other.extFloat01))
			return false;
		if (extFloat02 == null) {
			if (other.extFloat02 != null)
				return false;
		} else if (!extFloat02.equals(other.extFloat02))
			return false;
		if (extFloat03 == null) {
			if (other.extFloat03 != null)
				return false;
		} else if (!extFloat03.equals(other.extFloat03))
			return false;
		if (extFloat04 == null) {
			if (other.extFloat04 != null)
				return false;
		} else if (!extFloat04.equals(other.extFloat04))
			return false;
		if (extFloat05 == null) {
			if (other.extFloat05 != null)
				return false;
		} else if (!extFloat05.equals(other.extFloat05))
			return false;
		if (extFloat06 == null) {
			if (other.extFloat06 != null)
				return false;
		} else if (!extFloat06.equals(other.extFloat06))
			return false;
		if (extFloat07 == null) {
			if (other.extFloat07 != null)
				return false;
		} else if (!extFloat07.equals(other.extFloat07))
			return false;
		if (extFloat08 == null) {
			if (other.extFloat08 != null)
				return false;
		} else if (!extFloat08.equals(other.extFloat08))
			return false;
		if (extFloat09 == null) {
			if (other.extFloat09 != null)
				return false;
		} else if (!extFloat09.equals(other.extFloat09))
			return false;
		if (extFloat10 == null) {
			if (other.extFloat10 != null)
				return false;
		} else if (!extFloat10.equals(other.extFloat10))
			return false;
		if (extFloat11 == null) {
			if (other.extFloat11 != null)
				return false;
		} else if (!extFloat11.equals(other.extFloat11))
			return false;
		if (extFloat12 == null) {
			if (other.extFloat12 != null)
				return false;
		} else if (!extFloat12.equals(other.extFloat12))
			return false;
		if (extFloat13 == null) {
			if (other.extFloat13 != null)
				return false;
		} else if (!extFloat13.equals(other.extFloat13))
			return false;
		if (extFloat14 == null) {
			if (other.extFloat14 != null)
				return false;
		} else if (!extFloat14.equals(other.extFloat14))
			return false;
		if (extFloat15 == null) {
			if (other.extFloat15 != null)
				return false;
		} else if (!extFloat15.equals(other.extFloat15))
			return false;
		if (extFloat16 == null) {
			if (other.extFloat16 != null)
				return false;
		} else if (!extFloat16.equals(other.extFloat16))
			return false;
		if (extFloat17 == null) {
			if (other.extFloat17 != null)
				return false;
		} else if (!extFloat17.equals(other.extFloat17))
			return false;
		if (extFloat18 == null) {
			if (other.extFloat18 != null)
				return false;
		} else if (!extFloat18.equals(other.extFloat18))
			return false;
		if (extFloat19 == null) {
			if (other.extFloat19 != null)
				return false;
		} else if (!extFloat19.equals(other.extFloat19))
			return false;
		if (extFloat20 == null) {
			if (other.extFloat20 != null)
				return false;
		} else if (!extFloat20.equals(other.extFloat20))
			return false;
		if (extLongString01 == null) {
			if (other.extLongString01 != null)
				return false;
		} else if (!extLongString01.equals(other.extLongString01))
			return false;
		if (extLongString02 == null) {
			if (other.extLongString02 != null)
				return false;
		} else if (!extLongString02.equals(other.extLongString02))
			return false;
		if (extLongString03 == null) {
			if (other.extLongString03 != null)
				return false;
		} else if (!extLongString03.equals(other.extLongString03))
			return false;
		if (extLongString04 == null) {
			if (other.extLongString04 != null)
				return false;
		} else if (!extLongString04.equals(other.extLongString04))
			return false;
		if (extLongString05 == null) {
			if (other.extLongString05 != null)
				return false;
		} else if (!extLongString05.equals(other.extLongString05))
			return false;
		if (extLongString06 == null) {
			if (other.extLongString06 != null)
				return false;
		} else if (!extLongString06.equals(other.extLongString06))
			return false;
		if (extLongString07 == null) {
			if (other.extLongString07 != null)
				return false;
		} else if (!extLongString07.equals(other.extLongString07))
			return false;
		if (extLongString08 == null) {
			if (other.extLongString08 != null)
				return false;
		} else if (!extLongString08.equals(other.extLongString08))
			return false;
		if (extLongString09 == null) {
			if (other.extLongString09 != null)
				return false;
		} else if (!extLongString09.equals(other.extLongString09))
			return false;
		if (extLongString10 == null) {
			if (other.extLongString10 != null)
				return false;
		} else if (!extLongString10.equals(other.extLongString10))
			return false;
		if (extLongString11 == null) {
			if (other.extLongString11 != null)
				return false;
		} else if (!extLongString11.equals(other.extLongString11))
			return false;
		if (extLongString12 == null) {
			if (other.extLongString12 != null)
				return false;
		} else if (!extLongString12.equals(other.extLongString12))
			return false;
		if (extLongString13 == null) {
			if (other.extLongString13 != null)
				return false;
		} else if (!extLongString13.equals(other.extLongString13))
			return false;
		if (extLongString14 == null) {
			if (other.extLongString14 != null)
				return false;
		} else if (!extLongString14.equals(other.extLongString14))
			return false;
		if (extLongString15 == null) {
			if (other.extLongString15 != null)
				return false;
		} else if (!extLongString15.equals(other.extLongString15))
			return false;
		if (extLongString16 == null) {
			if (other.extLongString16 != null)
				return false;
		} else if (!extLongString16.equals(other.extLongString16))
			return false;
		if (extLongString17 == null) {
			if (other.extLongString17 != null)
				return false;
		} else if (!extLongString17.equals(other.extLongString17))
			return false;
		if (extLongString18 == null) {
			if (other.extLongString18 != null)
				return false;
		} else if (!extLongString18.equals(other.extLongString18))
			return false;
		if (extLongString19 == null) {
			if (other.extLongString19 != null)
				return false;
		} else if (!extLongString19.equals(other.extLongString19))
			return false;
		if (extLongString20 == null) {
			if (other.extLongString20 != null)
				return false;
		} else if (!extLongString20.equals(other.extLongString20))
			return false;
		if (extString01 == null) {
			if (other.extString01 != null)
				return false;
		} else if (!extString01.equals(other.extString01))
			return false;
		if (extString02 == null) {
			if (other.extString02 != null)
				return false;
		} else if (!extString02.equals(other.extString02))
			return false;
		if (extString03 == null) {
			if (other.extString03 != null)
				return false;
		} else if (!extString03.equals(other.extString03))
			return false;
		if (extString04 == null) {
			if (other.extString04 != null)
				return false;
		} else if (!extString04.equals(other.extString04))
			return false;
		if (extString05 == null) {
			if (other.extString05 != null)
				return false;
		} else if (!extString05.equals(other.extString05))
			return false;
		if (extString06 == null) {
			if (other.extString06 != null)
				return false;
		} else if (!extString06.equals(other.extString06))
			return false;
		if (extString07 == null) {
			if (other.extString07 != null)
				return false;
		} else if (!extString07.equals(other.extString07))
			return false;
		if (extString08 == null) {
			if (other.extString08 != null)
				return false;
		} else if (!extString08.equals(other.extString08))
			return false;
		if (extString09 == null) {
			if (other.extString09 != null)
				return false;
		} else if (!extString09.equals(other.extString09))
			return false;
		if (extString10 == null) {
			if (other.extString10 != null)
				return false;
		} else if (!extString10.equals(other.extString10))
			return false;
		if (extString11 == null) {
			if (other.extString11 != null)
				return false;
		} else if (!extString11.equals(other.extString11))
			return false;
		if (extString12 == null) {
			if (other.extString12 != null)
				return false;
		} else if (!extString12.equals(other.extString12))
			return false;
		if (extString13 == null) {
			if (other.extString13 != null)
				return false;
		} else if (!extString13.equals(other.extString13))
			return false;
		if (extString14 == null) {
			if (other.extString14 != null)
				return false;
		} else if (!extString14.equals(other.extString14))
			return false;
		if (extString15 == null) {
			if (other.extString15 != null)
				return false;
		} else if (!extString15.equals(other.extString15))
			return false;
		if (extString16 == null) {
			if (other.extString16 != null)
				return false;
		} else if (!extString16.equals(other.extString16))
			return false;
		if (extString17 == null) {
			if (other.extString17 != null)
				return false;
		} else if (!extString17.equals(other.extString17))
			return false;
		if (extString18 == null) {
			if (other.extString18 != null)
				return false;
		} else if (!extString18.equals(other.extString18))
			return false;
		if (extString19 == null) {
			if (other.extString19 != null)
				return false;
		} else if (!extString19.equals(other.extString19))
			return false;
		if (extString20 == null) {
			if (other.extString20 != null)
				return false;
		} else if (!extString20.equals(other.extString20))
			return false;
		if (extString21 == null) {
			if (other.extString21 != null)
				return false;
		} else if (!extString21.equals(other.extString21))
			return false;
		if (extString22 == null) {
			if (other.extString22 != null)
				return false;
		} else if (!extString22.equals(other.extString22))
			return false;
		if (extString23 == null) {
			if (other.extString23 != null)
				return false;
		} else if (!extString23.equals(other.extString23))
			return false;
		if (extString24 == null) {
			if (other.extString24 != null)
				return false;
		} else if (!extString24.equals(other.extString24))
			return false;
		if (extString25 == null) {
			if (other.extString25 != null)
				return false;
		} else if (!extString25.equals(other.extString25))
			return false;
		if (extString26 == null) {
			if (other.extString26 != null)
				return false;
		} else if (!extString26.equals(other.extString26))
			return false;
		if (extString27 == null) {
			if (other.extString27 != null)
				return false;
		} else if (!extString27.equals(other.extString27))
			return false;
		if (extString28 == null) {
			if (other.extString28 != null)
				return false;
		} else if (!extString28.equals(other.extString28))
			return false;
		if (extString29 == null) {
			if (other.extString29 != null)
				return false;
		} else if (!extString29.equals(other.extString29))
			return false;
		if (extString30 == null) {
			if (other.extString30 != null)
				return false;
		} else if (!extString30.equals(other.extString30))
			return false;
		if (extString31 == null) {
			if (other.extString31 != null)
				return false;
		} else if (!extString31.equals(other.extString31))
			return false;
		if (extString32 == null) {
			if (other.extString32 != null)
				return false;
		} else if (!extString32.equals(other.extString32))
			return false;
		if (extString33 == null) {
			if (other.extString33 != null)
				return false;
		} else if (!extString33.equals(other.extString33))
			return false;
		if (extString34 == null) {
			if (other.extString34 != null)
				return false;
		} else if (!extString34.equals(other.extString34))
			return false;
		if (extString35 == null) {
			if (other.extString35 != null)
				return false;
		} else if (!extString35.equals(other.extString35))
			return false;
		if (extString36 == null) {
			if (other.extString36 != null)
				return false;
		} else if (!extString36.equals(other.extString36))
			return false;
		if (extString37 == null) {
			if (other.extString37 != null)
				return false;
		} else if (!extString37.equals(other.extString37))
			return false;
		if (extString38 == null) {
			if (other.extString38 != null)
				return false;
		} else if (!extString38.equals(other.extString38))
			return false;
		if (extString39 == null) {
			if (other.extString39 != null)
				return false;
		} else if (!extString39.equals(other.extString39))
			return false;
		if (extString40 == null) {
			if (other.extString40 != null)
				return false;
		} else if (!extString40.equals(other.extString40))
			return false;
		if (extString41 == null) {
			if (other.extString41 != null)
				return false;
		} else if (!extString41.equals(other.extString41))
			return false;
		if (extString42 == null) {
			if (other.extString42 != null)
				return false;
		} else if (!extString42.equals(other.extString42))
			return false;
		if (extString43 == null) {
			if (other.extString43 != null)
				return false;
		} else if (!extString43.equals(other.extString43))
			return false;
		if (extString44 == null) {
			if (other.extString44 != null)
				return false;
		} else if (!extString44.equals(other.extString44))
			return false;
		if (extString45 == null) {
			if (other.extString45 != null)
				return false;
		} else if (!extString45.equals(other.extString45))
			return false;
		if (extString46 == null) {
			if (other.extString46 != null)
				return false;
		} else if (!extString46.equals(other.extString46))
			return false;
		if (extString47 == null) {
			if (other.extString47 != null)
				return false;
		} else if (!extString47.equals(other.extString47))
			return false;
		if (extString48 == null) {
			if (other.extString48 != null)
				return false;
		} else if (!extString48.equals(other.extString48))
			return false;
		if (extString49 == null) {
			if (other.extString49 != null)
				return false;
		} else if (!extString49.equals(other.extString49))
			return false;
		if (extString50 == null) {
			if (other.extString50 != null)
				return false;
		} else if (!extString50.equals(other.extString50))
			return false;
		if (extString51 == null) {
			if (other.extString51 != null)
				return false;
		} else if (!extString51.equals(other.extString51))
			return false;
		if (extString52 == null) {
			if (other.extString52 != null)
				return false;
		} else if (!extString52.equals(other.extString52))
			return false;
		if (extString53 == null) {
			if (other.extString53 != null)
				return false;
		} else if (!extString53.equals(other.extString53))
			return false;
		if (extString54 == null) {
			if (other.extString54 != null)
				return false;
		} else if (!extString54.equals(other.extString54))
			return false;
		if (extString55 == null) {
			if (other.extString55 != null)
				return false;
		} else if (!extString55.equals(other.extString55))
			return false;
		if (extString56 == null) {
			if (other.extString56 != null)
				return false;
		} else if (!extString56.equals(other.extString56))
			return false;
		if (extString57 == null) {
			if (other.extString57 != null)
				return false;
		} else if (!extString57.equals(other.extString57))
			return false;
		if (extString58 == null) {
			if (other.extString58 != null)
				return false;
		} else if (!extString58.equals(other.extString58))
			return false;
		if (extString59 == null) {
			if (other.extString59 != null)
				return false;
		} else if (!extString59.equals(other.extString59))
			return false;
		if (extString60 == null) {
			if (other.extString60 != null)
				return false;
		} else if (!extString60.equals(other.extString60))
			return false;
		if (headshow == null) {
			if (other.headshow != null)
				return false;
		} else if (!headshow.equals(other.headshow))
			return false;
		if (isCanceledName == null) {
			if (other.isCanceledName != null)
				return false;
		} else if (!isCanceledName.equals(other.isCanceledName))
			return false;
		if (isReceiveSms == null) {
			if (other.isReceiveSms != null)
				return false;
		} else if (!isReceiveSms.equals(other.isReceiveSms))
			return false;
		if (isTrash == null) {
			if (other.isTrash != null)
				return false;
		} else if (!isTrash.equals(other.isTrash))
			return false;
		if (isVisit == null) {
			if (other.isVisit != null)
				return false;
		} else if (!isVisit.equals(other.isVisit))
			return false;
		if (lastContactDate == null) {
			if (other.lastContactDate != null)
				return false;
		} else if (!lastContactDate.equals(other.lastContactDate))
			return false;
		if (mobilePhone1Regular == null) {
			if (other.mobilePhone1Regular != null)
				return false;
		} else if (!mobilePhone1Regular.equals(other.mobilePhone1Regular))
			return false;
		if (mobilePhone1Remark == null) {
			if (other.mobilePhone1Remark != null)
				return false;
		} else if (!mobilePhone1Remark.equals(other.mobilePhone1Remark))
			return false;
		if (mobilePhone2Regular == null) {
			if (other.mobilePhone2Regular != null)
				return false;
		} else if (!mobilePhone2Regular.equals(other.mobilePhone2Regular))
			return false;
		if (mobilePhone2Remark == null) {
			if (other.mobilePhone2Remark != null)
				return false;
		} else if (!mobilePhone2Remark.equals(other.mobilePhone2Remark))
			return false;
		if (province == null) {
			if (other.province != null)
				return false;
		} else if (!province.equals(other.province))
			return false;
		if (recordSourceName == null) {
			if (other.recordSourceName != null)
				return false;
		} else if (!recordSourceName.equals(other.recordSourceName))
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		if (templateIds == null) {
			if (other.templateIds != null)
				return false;
		} else if (!templateIds.equals(other.templateIds))
			return false;
		return true;
	}
	public RecordExportBean(String callTypeName, String bizTypeName,
			String recordSourceName, String isCanceledName,
			String belongUserName, String belongUserAccount,
			String belongDeptCode, String belongDeptName,
			String customerTypeName, String customerIndustryName,
			Integer extFieldId, Integer customerId, Date extDate01,
			Date extDate02, Date extDate03, Date extDate04, Date extDate05,
			Date extDate06, Date extDate07, Date extDate08, Date extDate09,
			Date extDate10, Date extDate11, Date extDate12, Date extDate13,
			Date extDate14, Date extDate15, Date extDate16, Date extDate17,
			Date extDate18, Date extDate19, Date extDate20, Double extFloat01,
			Double extFloat02, Double extFloat03, Double extFloat04,
			Double extFloat05, Double extFloat06, Double extFloat07,
			Double extFloat08, Double extFloat09, Double extFloat10,
			Double extFloat11, Double extFloat12, Double extFloat13,
			Double extFloat14, Double extFloat15, Double extFloat16,
			Double extFloat17, Double extFloat18, Double extFloat19,
			Double extFloat20, String extLongString01, String extLongString02,
			String extLongString03, String extLongString04,
			String extLongString05, String extLongString06,
			String extLongString07, String extLongString08,
			String extLongString09, String extLongString10,
			String extLongString11, String extLongString12,
			String extLongString13, String extLongString14,
			String extLongString15, String extLongString16,
			String extLongString17, String extLongString18,
			String extLongString19, String extLongString20, String extString01,
			String extString02, String extString03, String extString04,
			String extString05, String extString06, String extString07,
			String extString08, String extString09, String extString10,
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
			String extString59, String extString60, String customerNamePinyin,
			String sex, String customerNo, String customerTitle, Integer age,
			String custIdCard, Date birthday, Integer customerTypeId,
			Integer customerIndustryId, String company, String custRemark,
			String headshow, String province, String city, String address,
			String mobilePhone1Remark, String mobilePhone2Remark, String email,
			String templateIds, Integer isTrash, Integer belongDeptId,
			Integer belongUserId, Integer isVisit, Date lastContactDate,
			Integer isReceiveSms, String mobilePhone1Regular,
			String mobilePhone2Regular, Date custCreateDate,
			Date custUpdateDate, Integer custCreateUser, Integer custUpdateUser) {
		super();
		this.callTypeName = callTypeName;
		this.bizTypeName = bizTypeName;
		this.recordSourceName = recordSourceName;
		this.isCanceledName = isCanceledName;
		this.belongUserName = belongUserName;
		this.belongUserAccount = belongUserAccount;
		this.belongDeptCode = belongDeptCode;
		this.belongDeptName = belongDeptName;
		this.customerTypeName = customerTypeName;
		this.customerIndustryName = customerIndustryName;
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
		this.extLongString11 = extLongString11;
		this.extLongString12 = extLongString12;
		this.extLongString13 = extLongString13;
		this.extLongString14 = extLongString14;
		this.extLongString15 = extLongString15;
		this.extLongString16 = extLongString16;
		this.extLongString17 = extLongString17;
		this.extLongString18 = extLongString18;
		this.extLongString19 = extLongString19;
		this.extLongString20 = extLongString20;
		this.extString01 = extString01;
		this.extString02 = extString02;
		this.extString03 = extString03;
		this.extString04 = extString04;
		this.extString05 = extString05;
		this.extString06 = extString06;
		this.extString07 = extString07;
		this.extString08 = extString08;
		this.extString09 = extString09;
		this.extString10 = extString10;
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
		this.customerNamePinyin = customerNamePinyin;
		this.sex = sex;
		this.customerNo = customerNo;
		this.customerTitle = customerTitle;
		this.age = age;
		this.custIdCard = custIdCard;
		this.birthday = birthday;
		this.customerTypeId = customerTypeId;
		this.customerIndustryId = customerIndustryId;
		this.company = company;
		this.custRemark = custRemark;
		this.headshow = headshow;
		this.province = province;
		this.city = city;
		this.address = address;
		this.mobilePhone1Remark = mobilePhone1Remark;
		this.mobilePhone2Remark = mobilePhone2Remark;
		this.email = email;
		this.templateIds = templateIds;
		this.isTrash = isTrash;
		this.belongDeptId = belongDeptId;
		this.belongUserId = belongUserId;
		this.isVisit = isVisit;
		this.lastContactDate = lastContactDate;
		this.isReceiveSms = isReceiveSms;
		this.mobilePhone1Regular = mobilePhone1Regular;
		this.mobilePhone2Regular = mobilePhone2Regular;
		this.custCreateDate = custCreateDate;
		this.custUpdateDate = custUpdateDate;
		this.custCreateUser = custCreateUser;
		this.custUpdateUser = custUpdateUser;
	}
    
    
    
}
