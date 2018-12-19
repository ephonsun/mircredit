package com.banger.mobile.domain.model.report;

import java.io.Serializable;

/**
 * @author zhangfp
 * @version $Id: LoanStatusCountReportBean v 0.1 ${} 下午2:15 zhangfp Exp $
 */
public class LoanStatusCountReportBean implements Serializable {
    private String timeBucket;   //时间段
    private String deptName;    //机构名称
    private String userName;    //人员名称
    private Integer allLoanCount;  //贷款总数(笔)
    private Integer applyLoanCount; //申请贷款数量
    private Integer assignLoanCount; //分配贷款数量
    private Integer examLoanCount;  //调查贷款数量
    private Integer approveLoanCount;  //审批贷款数量
    private Integer makeLoanCount;   //放贷贷款数量
    private Integer loanedLoanCount; //贷后贷款数量
    private Integer successLoanCount; //已结清贷款数量
    private Integer approveRefusedLoanCount; //审批拒绝贷款数量
    private Integer otherRefusedLoanCount; //其他拒绝贷款数量

    private Integer deptId;

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getTimeBucket() {
        return timeBucket;
    }

    public void setTimeBucket(String timeBucket) {
        this.timeBucket = timeBucket;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAllLoanCount() {
        return allLoanCount;
    }

    public void setAllLoanCount(Integer allLoanCount) {
        this.allLoanCount = allLoanCount;
    }

    public Integer getApplyLoanCount() {
        return applyLoanCount;
    }

    public void setApplyLoanCount(Integer applyLoanCount) {
        this.applyLoanCount = applyLoanCount;
    }

    public Integer getAssignLoanCount() {
        return assignLoanCount;
    }

    public void setAssignLoanCount(Integer assignLoanCount) {
        this.assignLoanCount = assignLoanCount;
    }

    public Integer getExamLoanCount() {
        return examLoanCount;
    }

    public void setExamLoanCount(Integer examLoanCount) {
        this.examLoanCount = examLoanCount;
    }

    public Integer getApproveLoanCount() {
        return approveLoanCount;
    }

    public void setApproveLoanCount(Integer approveLoanCount) {
        this.approveLoanCount = approveLoanCount;
    }

    public Integer getMakeLoanCount() {
        return makeLoanCount;
    }

    public void setMakeLoanCount(Integer makeLoanCount) {
        this.makeLoanCount = makeLoanCount;
    }

    public Integer getLoanedLoanCount() {
        return loanedLoanCount;
    }

    public void setLoanedLoanCount(Integer loanedLoanCount) {
        this.loanedLoanCount = loanedLoanCount;
    }

    public Integer getSuccessLoanCount() {
        return successLoanCount;
    }

    public void setSuccessLoanCount(Integer successLoanCount) {
        this.successLoanCount = successLoanCount;
    }

    public Integer getApproveRefusedLoanCount() {
        return approveRefusedLoanCount;
    }

    public void setApproveRefusedLoanCount(Integer approveRefusedLoanCount) {
        this.approveRefusedLoanCount = approveRefusedLoanCount;
    }

    public Integer getOtherRefusedLoanCount() {
        return otherRefusedLoanCount;
    }

    public void setOtherRefusedLoanCount(Integer otherRefusedLoanCount) {
        this.otherRefusedLoanCount = otherRefusedLoanCount;
    }
}
