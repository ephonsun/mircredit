package com.banger.mobile.domain.model.loan;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-2-28
 * Time: 下午5:08
 * To change this template use File | Settings | File Templates.
 */
public class LoanUser implements Serializable {
    private String belongUserName;
    private String applyUserName;
    private String submitUserName;
    private String createUserName;
    private String verifyUserName;
    private String surveyUserName;

    public String getSurveyUserName() {
        return surveyUserName;
    }

    public void setSurveyUserName(String surveyUserName) {
        this.surveyUserName = surveyUserName;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getApplyUserName() {
        return applyUserName;
    }

    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName;
    }

    public String getBelongUserName() {
        return belongUserName;
    }

    public void setBelongUserName(String belongUserName) {
        this.belongUserName = belongUserName;
    }

    public String getSubmitUserName() {
        return submitUserName;
    }

    public void setSubmitUserName(String submitUserName) {
        this.submitUserName = submitUserName;
    }

    public String getVerifyUserName() {
        return verifyUserName;
    }

    public void setVerifyUserName(String verifyUserName) {
        this.verifyUserName = verifyUserName;
    }
}
