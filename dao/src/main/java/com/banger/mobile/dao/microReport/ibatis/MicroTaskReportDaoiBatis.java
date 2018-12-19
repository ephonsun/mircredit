/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :任务报表统计DAO接口实现
 * Author     :liyb
 * Create Date:2013-12-26
 */
package com.banger.mobile.dao.microReport.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.microReport.MicroTaskReportDao;
import com.banger.mobile.domain.model.microReport.LoanTaskReportBean;
import com.banger.mobile.ibatis.GenericDaoiBatis;
import com.banger.mobile.util.StringUtil;

/**
 * @author liyb
 * @version $Id: MicroTaskReportDaoiBatis.java,v 0.1 2013-12-26 上午11:20:52 liyb Exp $
 */
public class MicroTaskReportDaoiBatis extends GenericDaoiBatis implements MicroTaskReportDao {
    public MicroTaskReportDaoiBatis(){
        super(LoanTaskReportBean.class);
    }

    /**
     * 贷款任务报表统计
     * @param map
     * @param searchType
     * @return
     */
    public List<LoanTaskReportBean> getLoanTaskReportList(Map<String, Object> map,String searchType) {
        Map<String,Object> fConds = new HashMap<String, Object>();
        for(Map.Entry<String, Object> entry : map.entrySet())   
        { 
            if(entry.getValue() instanceof String){
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil.ReplaceSQLChar(entry.getValue().toString())));
            }else{
                fConds.put(entry.getKey(), entry.getValue());
            }
        }
        List<LoanTaskReportBean> list=null;
        if(searchType.equals("brDept")){//机构
            list=this.getSqlMapClientTemplate().queryForList("getLoanTaskReportDeptList",fConds);
        }else{
            list=this.getSqlMapClientTemplate().queryForList("getLoanTaskReportList",fConds);
        }
        return list;
    }

    /**
     * 查询任务
     * @param map
     * @return
     */
    public List<LoanTaskReportBean> getTaskByTitle(Map<String, Object> map) {
        Map<String,Object> fConds = new HashMap<String, Object>();
        for(Map.Entry<String, Object> entry : map.entrySet())   
        { 
            if(entry.getValue() instanceof String){
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil.ReplaceSQLChar(entry.getValue().toString())));
            }else{
                fConds.put(entry.getKey(), entry.getValue());
            }
        }
        List<LoanTaskReportBean> list=this.getSqlMapClientTemplate().queryForList("getTaskByTitle",fConds);
        return list;
    }

}
