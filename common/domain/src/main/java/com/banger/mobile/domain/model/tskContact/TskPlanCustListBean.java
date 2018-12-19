/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-12-15
 */
package com.banger.mobile.domain.model.tskContact;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author cheny
 * @version $Id: TskPlanCustBean.java,v 0.1 2012-12-15 下午3:10:17 cheny Exp $
 */
public class TskPlanCustListBean extends TskPlanSelectBean{

    private static final long serialVersionUID = 7179880273297353889L;
    
    private String customerTitle;           //称谓
    private String sex;                     //性别
    private Integer age;                    //年龄
    private Date birthday;                  //生日
    private Integer isShare;        //是否是共享客户
    
    private Integer finishLevel;           //联系情况 0:未完成 1:完成 2:难以联系
    
    private Integer isDel;

    /**
     * 
     */
    public TskPlanCustListBean() {
        super();
    }

    public String getCustomerTitle() {
        return customerTitle;
    }

    public void setCustomerTitle(String customerTitle) {
        this.customerTitle = customerTitle;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getFinishLevel() {
        return finishLevel;
    }

    public void setFinishLevel(Integer finishLevel) {
        this.finishLevel = finishLevel;
    }

    public Integer getAge() {
        if(birthday!=null){
            SimpleDateFormat date = new SimpleDateFormat("yyyy");
            Date dd = new Date();
            int year = Integer.parseInt(date.format(birthday));
            int nowYear = Integer.parseInt(date.format(dd));
            Integer newAge = nowYear-year;
            if(newAge<120 && newAge>0){
                return newAge;
            }
        }
        return null;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    public Integer getIsShare() {
        return isShare;
    }

    public void setIsShare(Integer isShare) {
        this.isShare = isShare;
    }
    
    public Date getBirthday() {
        return birthday;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1525781797, 446098901).appendSuper(super.hashCode())
            .append(this.birthday).append(this.sex).append(this.age).append(this.isShare)
            .append(this.customerTitle).toHashCode();
    }
    
    

}
