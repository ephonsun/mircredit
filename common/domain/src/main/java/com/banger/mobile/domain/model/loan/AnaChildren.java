package com.banger.mobile.domain.model.loan;

import com.banger.mobile.domain.model.base.BaseObject;

import java.io.Serializable;

/**
 * @author zhangfp
 * @version $Id: AnaChildren v 0.1 ${} 下午2:13 zhangfp Exp $
 *
 * 分析表的贷款申请人的家庭成员信息
 */
public class AnaChildren extends BaseObject implements Serializable {

    private static final long serialVersionUID = -1460369540477512346L;

    private Integer childrenId;
    private Integer applyInfoId;
    private String childrenInfo;
    private String childrenInfoOther;

    public Integer getChildrenId() {
        return childrenId;
    }

    public void setChildrenId(Integer childrenId) {
        this.childrenId = childrenId;
    }

    public Integer getApplyInfoId() {
        return applyInfoId;
    }

    public void setApplyInfoId(Integer applyInfoId) {
        this.applyInfoId = applyInfoId;
    }

    public String getChildrenInfo() {
        return childrenInfo;
    }

    public void setChildrenInfo(String childrenInfo) {
        this.childrenInfo = childrenInfo;
    }

    public String getChildrenInfoOther() {
        return childrenInfoOther;
    }

    public void setChildrenInfoOther(String childrenInfoOther) {
        this.childrenInfoOther = childrenInfoOther;
    }
}
