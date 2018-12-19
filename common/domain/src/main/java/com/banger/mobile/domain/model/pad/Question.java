/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-7-18
 */
package com.banger.mobile.domain.model.pad;

import java.util.List;

/**
 * @author yuanme
 * @version Question.java,v 0.1 2012-7-18 上午11:29:28
 */
public class Question {
    private Integer      questionId;
    private String       questionDetail;
    private Integer      questionType;
    private List<Option> questionOptionList;
    private Integer      sortno;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestionDetail() {
        return questionDetail;
    }

    public void setQuestionDetail(String questionDetail) {
        this.questionDetail = questionDetail;
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public List<Option> getQuestionOptionList() {
        return questionOptionList;
    }

    public void setQuestionOptionList(List<Option> questionOptionList) {
        this.questionOptionList = questionOptionList;
    }

    public Integer getSortno() {
        return sortno;
    }

    public void setSortno(Integer sortno) {
        this.sortno = sortno;
    }
}
