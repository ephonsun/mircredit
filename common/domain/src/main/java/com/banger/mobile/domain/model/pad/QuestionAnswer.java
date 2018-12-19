/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-7-18
 */
package com.banger.mobile.domain.model.pad;

/**
 * @author yuanme
 * @version QuestionAnswer.java,v 0.1 2012-7-18 下午3:42:08
 */
public class QuestionAnswer {
    private Integer questionId;
    private String  optionIds;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getOptionIds() {
        return optionIds;
    }

    public void setOptionIds(String optionIds) {
        this.optionIds = optionIds;
    }
}
