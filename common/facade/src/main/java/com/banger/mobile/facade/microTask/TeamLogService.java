package com.banger.mobile.facade.microTask;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.microTask.TeamLog;

import java.util.Map;

/**
 * Created by BH-TCL on 15-5-5.
 */
public interface TeamLogService {
    void insertTeamLog(TeamLog teamLog);

    PageUtil<TeamLog> queryFeedBacksPage(Map<String, Object> conds, Page curPage);

    void updateFeedBack(TeamLog teamLog);

    TeamLog getTeamLog(Map<String,Object> map);

    Integer getNeedTeamLogCount(Integer deptId);

    Integer getNeedTeamLogCountWhitWriter(Integer deptId);

    TeamLog getTeamLogSum(Integer deptId);

    Integer getDeptTeamLogCount(Integer deptId);

    Integer getDeptTeamLogCountWhitWriter(Integer deptId);

    TeamLog getDeptTeamLogSum(Integer deptId);

    void getTeamLogExporFile(TeamLog teamLog,String deptOrUserName, String path);
}
