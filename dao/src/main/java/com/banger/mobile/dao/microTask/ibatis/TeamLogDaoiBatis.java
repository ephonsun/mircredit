package com.banger.mobile.dao.microTask.ibatis;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.microTask.TeamLogDao;
import com.banger.mobile.domain.model.microTask.TeamLog;
import com.banger.mobile.ibatis.GenericDaoiBatis;
import com.banger.mobile.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BH-TCL on 15-5-5.
 */
public class TeamLogDaoiBatis  extends GenericDaoiBatis implements TeamLogDao {
    /**
     * Constructor that takes in a class to see which type of entity to persist
     *
     * @param persistentClass the class type you'd like to persist
     */
    public TeamLogDaoiBatis(Class persistentClass) {
        super(persistentClass);
    }

    public TeamLogDaoiBatis(){
        super(TeamLog.class);
    }

    @Override
    public void insertTeamLog(TeamLog teamLog) {
        this.getSqlMapClientTemplate().insert("insertTeamLog",teamLog);
    }

    @Override
    public PageUtil<TeamLog> queryFeedBacksPage(Map<String, Object> conds, Page curPage) {
        Map<String, Object> fConds = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : conds.entrySet()) {
            if (entry.getValue() instanceof String) {
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil
                        .ReplaceSQLChar(entry.getValue().toString())));
            } else {
                fConds.put(entry.getKey(), entry.getValue());
            }
        }
        List<TeamLog> list = (List<TeamLog>) this.findQueryPage("queryTeamLogsPage", "queryTeamLogsPageCount",
                fConds, curPage);
        return new PageUtil<TeamLog>(list, curPage);
    }

    @Override
    public void updateFeedBack(TeamLog teamLog) {
        this.getSqlMapClientTemplate().update("updateTeamLog",teamLog);
    }

    @Override
    public TeamLog getTeamLog(Map<String, Object> map) {
        return (TeamLog)this.getSqlMapClientTemplate().queryForObject("getTeamLog",map);
    }

    /**
     * 获取选中部门下所有要填写的人数
     * @param deptId
     * @return
     */
    @Override
    public Integer getNeedTeamLogCount(Integer deptId) {
        return (Integer)this.getSqlMapClientTemplate().queryForObject("getNeedTeamLogCount",deptId);
    }


    /**
     * 获取选中部门下已经填写的人数
     * @param deptId
     * @return
     */
    @Override
    public Integer getNeedTeamLogCountWhitWriter(Integer deptId) {
        return (Integer) this.getSqlMapClientTemplate().queryForObject("getNeedTeamLogCountWhitWriter",deptId);
    }

    /**
     * 获取选中部门下所有已经填写个人日志的数据统计
     * @param deptId
     * @return
     */
    @Override
    public TeamLog getTeamLogSum(Integer deptId) {
        return (TeamLog)this.getSqlMapClientTemplate().queryForObject("getTeamLogSum",deptId);
    }

    /**
     * 本机构的要填写人数
     * @param deptId
     * @return
     */
    @Override
    public Integer getDeptTeamLogCount(Integer deptId) {
        return (Integer) this.getSqlMapClientTemplate().queryForObject("getDeptTeamLogCount",deptId);
    }

    /**
     * 本机构的已经填写人数
     * @param deptId
     * @return
     */
    @Override
    public Integer getDeptTeamLogCountWhitWriter(Integer deptId) {
        return (Integer) this.getSqlMapClientTemplate().queryForObject("getDeptTeamLogCountWhitWriter",deptId);
    }

    /**
     * 本机构已经填写的统计
     * @param deptId
     * @return
     */
    @Override
    public TeamLog getDeptTeamLogSum(Integer deptId) {
        return (TeamLog) this.getSqlMapClientTemplate().queryForObject("getDeptTeamLogSum",deptId);
    }
}
