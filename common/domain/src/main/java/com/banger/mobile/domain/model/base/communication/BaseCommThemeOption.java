/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :CommThemeOption投票贴选项实体基类
 * Author     :liyb
 * Create Date:2012-12-24
 */
package com.banger.mobile.domain.model.base.communication;

import java.io.Serializable;

import com.banger.mobile.domain.model.base.BaseObject;

/**
 * @author liyb
 * @version $Id: BaseCommThemeOption.java,v 0.1 2012-12-24 下午02:13:30 liyb Exp $
 */
public class BaseCommThemeOption extends BaseObject implements Serializable {

    private static final long serialVersionUID = -8703003051910708458L;
    private Integer           optionId;                                 //主键ID
    private Integer           themeId;                                  //主题ID
    private String            optionContent;                            //选项内容
    private Integer           selectOptionCount;                        //选择数量
    public Integer getOptionId() {
        return optionId;
    }
    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }
    public Integer getThemeId() {
        return themeId;
    }
    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }
    public String getOptionContent() {
        return optionContent;
    }
    public void setOptionContent(String optionContent) {
        this.optionContent = optionContent;
    }
    public Integer getSelectOptionCount() {
        return selectOptionCount;
    }
    public void setSelectOptionCount(Integer selectOptionCount) {
        this.selectOptionCount = selectOptionCount;
    }
    
}
