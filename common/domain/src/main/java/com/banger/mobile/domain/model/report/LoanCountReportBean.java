package com.banger.mobile.domain.model.report;

import java.io.Serializable;

/**
 * @author zhangfp
 * @version $Id: LoanCountReportBean v 0.1 ${} 上午11:33 zhangfp Exp $
 */
public class LoanCountReportBean implements Serializable {
    private Integer loanStatusId;
    private Integer statusCount;
    private Integer applyUserId;
    private Integer surveyUserId;
    private Integer beforeAssignDeptId;
    private Integer afterAssignDepthId;

    public Integer getLoanStatusId() {
        return loanStatusId;
    }

    public void setLoanStatusId(Integer loanStatusId) {
        this.loanStatusId = loanStatusId;
    }

    public Integer getStatusCount() {
        return statusCount;
    }

    public void setStatusCount(Integer statusCount) {
        this.statusCount = statusCount;
    }

    public Integer getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(Integer applyUserId) {
        this.applyUserId = applyUserId;
    }

    public Integer getSurveyUserId() {
        return surveyUserId;
    }

    public void setSurveyUserId(Integer surveyUserId) {
        this.surveyUserId = surveyUserId;
    }

    public Integer getBeforeAssignDeptId() {
        return beforeAssignDeptId;
    }

    public void setBeforeAssignDeptId(Integer beforeAssignDeptId) {
        this.beforeAssignDeptId = beforeAssignDeptId;
    }

    public Integer getAfterAssignDepthId() {
        return afterAssignDepthId;
    }

    public void setAfterAssignDepthId(Integer afterAssignDepthId) {
        this.afterAssignDepthId = afterAssignDepthId;
    }
}
