package com.banger.mobile.domain.model.quartz;

import java.io.Serializable;
import java.util.HashMap;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.HashCodeBuilder;


/**
 * 任务在手动触发时，对前台输入数据进行封装
 * 
 * @author zhangxiang
 * @version $Id: JobData.java,v 0.1 2009-7-24 下午01:39:40 zhangxiang Exp $
 */
public class JobData extends BaseObject {

    /**
     * 
     */
    private static final long serialVersionUID = 4654782990844043260L;

    private HashMap           data             = new HashMap();

    public HashMap getData() {
        return data;
    }

    public void setData(HashMap data) {
        this.data = data;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-273026925, 1311521159).appendSuper(super.hashCode())
            .append(this.data).toHashCode();
    }

}
