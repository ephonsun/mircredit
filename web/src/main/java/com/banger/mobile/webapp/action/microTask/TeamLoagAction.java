package com.banger.mobile.webapp.action.microTask;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.dept.DeptUserBean;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.dept.UserSubordinateBean;
import com.banger.mobile.domain.model.microTask.TeamLog;
import com.banger.mobile.domain.model.role.SysRole;
import com.banger.mobile.domain.model.user.SysWorkDelegate;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.dept.DeptService;
import com.banger.mobile.facade.microTask.TeamLogService;
import com.banger.mobile.facade.param.SysParamService;
import com.banger.mobile.facade.role.SysRoleService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.facade.user.SysWorkDelegateService;
import com.banger.mobile.util.FileUtil;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import javax.servlet.ServletOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by BH-TCL on 15-5-5.
 */
public class TeamLoagAction extends BaseAction {
    private static Logger logger = Logger.getLogger(TeamLoagAction.class);

    private SysRoleService sysRoleService; // 角色service
    private List<SysRole> sysRoleList;
    private JSONArray deptJson; // 机构树json
    private DeptFacadeService deptFacadeService; // 机构Service
    private PageUtil<UserSubordinateBean> deptUserList; // 活动分页对象
    private SysUserService sysUserService;
    private DeptService deptService; // 机构service
    private int totalAmount; // 总记录数
    private Integer deptId;
    private SysWorkDelegateService sysWorkDelegateService;
    private List<SysWorkDelegate> sysWorkDelegateList;
    private int flagInt;
    private SysDept dept; // 机构对象 新增 修改
    private DeptUserBean deptUserBean; // 列表对象
    private int flag; // 包含下属标识
    private int count;// 需要包含下属机构填写日志的总数
    private int alrCount;//已经填写的人数 包含下属机构
    private int dcount;//不包含下属机构要填写的日志总数
    private int alrDCount;//已经填写的人数 不包含下属机构
    private TeamLog sumTeamLog;//团队日志统计数据 包含下属机构
    private TeamLog sumDTeamLog;//团队日志统计 不包含下属机构
    private Integer userId;//查看某个用户团队日子
    private Integer allDeptId;//查看部门包含下属机构的团队日志
    private String deptOrUserName;// 部门名称或者用户名称
    private SysParamService sysParamService;

    public void setSysParamService(SysParamService sysParamService) {
        this.sysParamService = sysParamService;
    }

    public String getDeptOrUserName() {
        return deptOrUserName;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public void setDeptOrUserName(String deptOrUserName) {
        this.deptOrUserName = deptOrUserName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAllDeptId() {
        return allDeptId;
    }

    public void setAllDeptId(Integer allDeptId) {
        this.allDeptId = allDeptId;
    }

    public int getAlrCount() {
        return alrCount;
    }

    public void setAlrCount(int alrCount) {
        this.alrCount = alrCount;
    }

    public TeamLog getSumDTeamLog() {
        return sumDTeamLog;
    }

    public void setSumDTeamLog(TeamLog sumDTeamLog) {
        this.sumDTeamLog = sumDTeamLog;
    }

    public int getAlrDCount() {
        return alrDCount;
    }

    public void setAlrDCount(int alrDCount) {
        this.alrDCount = alrDCount;
    }

    public int getDcount() {
        return dcount;
    }

    public void setDcount(int dcount) {
        this.dcount = dcount;
    }

    public TeamLog getSumTeamLog() {
        return sumTeamLog;
    }

    public void setSumTeamLog(TeamLog sumTeamLog) {
        this.sumTeamLog = sumTeamLog;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    private TeamLog teamLog;

    public TeamLog getTeamLog() {
        return teamLog;
    }

    public void setTeamLog(TeamLog teamLog) {
        this.teamLog = teamLog;
    }

    private TeamLogService teamLogService;

    public void setTeamLogService(TeamLogService teamLogService) {
        this.teamLogService = teamLogService;
    }

    public SysRoleService getSysRoleService() {
        return sysRoleService;
    }

    public void setSysRoleService(SysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }

    public List<SysRole> getSysRoleList() {
        return sysRoleList;
    }

    public void setSysRoleList(List<SysRole> sysRoleList) {
        this.sysRoleList = sysRoleList;
    }

    public JSONArray getDeptJson() {
        return deptJson;
    }

    public void setDeptJson(JSONArray deptJson) {
        this.deptJson = deptJson;
    }

    public DeptFacadeService getDeptFacadeService() {
        return deptFacadeService;
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }

    public PageUtil<UserSubordinateBean> getDeptUserList() {
        return deptUserList;
    }

    public void setDeptUserList(PageUtil<UserSubordinateBean> deptUserList) {
        this.deptUserList = deptUserList;
    }

    public SysUserService getSysUserService() {
        return sysUserService;
    }

    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    public DeptService getDeptService() {
        return deptService;
    }

    public void setDeptService(DeptService deptService) {
        this.deptService = deptService;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public SysWorkDelegateService getSysWorkDelegateService() {
        return sysWorkDelegateService;
    }

    public void setSysWorkDelegateService(SysWorkDelegateService sysWorkDelegateService) {
        this.sysWorkDelegateService = sysWorkDelegateService;
    }

    public List<SysWorkDelegate> getSysWorkDelegateList() {
        return sysWorkDelegateList;
    }

    public void setSysWorkDelegateList(List<SysWorkDelegate> sysWorkDelegateList) {
        this.sysWorkDelegateList = sysWorkDelegateList;
    }

    public TeamLogService getTeamLogService() {
        return teamLogService;
    }

    public int getFlagInt() {
        return flagInt;
    }

    public void setFlagInt(int flagInt) {
        this.flagInt = flagInt;
    }

    public SysDept getDept() {
        return dept;
    }

    public void setDept(SysDept dept) {
        this.dept = dept;
    }

    public DeptUserBean getDeptUserBean() {
        return deptUserBean;
    }

    public void setDeptUserBean(DeptUserBean deptUserBean) {
        this.deptUserBean = deptUserBean;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    /**
     * 跳转到填写日志页面
     *
     * @return
     */
    public String addTeamLog() {
        try {
            Integer userId = getLoginInfo().getUserId();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userId", userId);
            teamLog = teamLogService.getTeamLog(map);
            if (teamLog == null) {
                teamLog = new TeamLog();
                return "success";
            }
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            Date data = sf.parse(sf.format(calendar.getTime()));
            if ((teamLog.getUpdateDate().getTime() - data.getTime()) < 0)
                teamLog = new TeamLog();
            return "success";
        } catch (Exception e) {
            logger.error("TeamLoagAction.addTeamLog", e);
            return "error";
        }
    }

    /**
     * 保存日志输入
     */
    public String saveTeamLog() {
        try {
            Integer userId = getLoginInfo().getUserId();
            teamLog.setUpdateDate(Calendar.getInstance().getTime());
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userId", userId);
            TeamLog team = teamLogService.getTeamLog(map);//查找 是否第一次录入
            if (team == null) {
                //新增
                teamLog.setUserId(userId);
                teamLog.setCreateDate(Calendar.getInstance().getTime());
                teamLog.setCreateUser(userId);
                teamLogService.insertTeamLog(teamLog);
            } else {
                //更新
                teamLog.setTeamLogId(team.getTeamLogId());
                teamLogService.updateFeedBack(teamLog);
            }
            return "success";
        } catch (Exception e) {
            logger.error("TeamLoagAction.saveTeamLog", e);
            return "error";
        }
    }

    public String showTeamLog() {
        //取所有需要输入个人日志角色
        sysRoleList = sysRoleService.getTeamLogRole();

        try {
            deptJson = deptFacadeService.getBusinessManagerDeptJsonAddRoot();
            String codes = "";
            for (int i = deptJson.size() - 1; i >= 0; i--) {
                JSONObject obj = deptJson.getJSONObject(i);
                if (obj.get("pId").equals(0)) {// 包含虚拟根节点
                    codes = (String) obj.get("searchCode");
                    String[] searchCodes = codes.split(",");
                    Map<String, Object> map = new HashMap<String, Object>();
                    String deptCodes = "";
                    for (String deptSearchCode : searchCodes) {
                        deptCodes += "DEPT_SEARCH_CODE like" + " " + "'"
                                + deptSearchCode + "%" + "'" + " " + "or" + " ";
                    }
                    deptCodes = "( "
                            + deptCodes.substring(0,
                            deptCodes.lastIndexOf("or")) + " )";
                    map.put("searchMany", deptCodes);
                    map.put("isActived", 1);
                    map.put("roleMemberId", 2);
                    map.put("userId", this.getLoginInfo().getUserId());
                    deptUserList = conversionRoleName(deptService
                            .getSubsUserSubordinateList(map, this.getPage()));
                    totalAmount = deptUserList.getPage().getTotalRowsAmount();
                    break;
                } else {// 根节点不是虚拟节点
                    if (obj.get("pId").equals(2)) {
                        deptId = (Integer) obj.get("id");
                        Map<String, Object> parameterMap = new HashMap<String, Object>();
                        parameterMap.put("deptId", deptId);
                        parameterMap.put("isActived", 1);
                        parameterMap.put("roleMemberId", 2);
                        parameterMap.put("userId", this.getLoginInfo()
                                .getUserId());
                        deptUserList = conversionRoleName(deptService
                                .getUserSubordinatePage(parameterMap,
                                        this.getPage()));
                        totalAmount = deptUserList.getPage()
                                .getTotalRowsAmount();// 记录的总数
                        break;
                    }

                }
            }
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("isCancel", 1);
            sysWorkDelegateList = sysWorkDelegateService
                    .getSysWorkDelegateList(parameters);
            return SUCCESS;
        } catch (Exception e) {
            log.error("showDeptList action error:" + e.getMessage());
            return ERROR;
        }
    }

    /**
     * 组织机构对角色名称进行增加
     *
     * @param beans
     * @return
     */
    public PageUtil<UserSubordinateBean> conversionRoleName(
            PageUtil<UserSubordinateBean> beans) {
        try {
            //给填写人数统一赋值
            try {
                count = teamLogService.getNeedTeamLogCount(deptId);
                alrCount = teamLogService.getNeedTeamLogCountWhitWriter(deptId);
                dcount = teamLogService.getDeptTeamLogCount(deptId);
                alrDCount = teamLogService.getDeptTeamLogCountWhitWriter(deptId);
            } catch (Exception e) {
                logger.error("填写人数统一赋值失败", e);
            }
            List<UserSubordinateBean> bean = new ArrayList<UserSubordinateBean>();
            UserSubordinateBean item = null;
            String userIds = "";
            for (int i = 0; i < beans.getItems().size(); i++) {
                userIds += beans.getItems().get(i).getUserId() + ",";
            }
            if (userIds.length() > 0)
                userIds = userIds.substring(0, userIds.length() - 1);
            if (!userIds.equals("")) {
                Map<Integer, String> map = sysUserService
                        .getRoleNamesByUserId(userIds);
                //所有需要录入个人日志的角色集
                List<SysRole> roles = sysRoleService.getTeamLogRole();
                //取所有需要录入个人日志的用户集
                for (int i = 0; i < beans.getItems().size(); i++) {
                    item = beans.getItems().get(i);
                    if (map.containsKey(item.getUserId())) {
                        String roleName = map.get(item.getUserId());
                        for (SysRole sysRole : roles) {
                            //角色必须是需要录入个人日志的 用户角色包含需要填写的角色
                            if (roleName.contains(sysRole.getRoleName())) {
                                item.setRoleNames(map.get(item.getUserId()));
                                bean.add(item);
                                break;
                            }
                        }
                    }
                }
                beans.setItems(bean);
            }
        } catch (Exception e) {
            log.error("conversionRoleName action error:" + e.getMessage());
        }
        return beans;
    }


    /**
     * 分页入口
     *
     * @return
     */
    public String getUserPageList() {
        if (flagInt == 1)
            return this.getDeptUser();// 本机构
        else if (flagInt == 2)
            return this.getCoditions();// 下属机构
        return this.getRootPage();// 虚拟节点
    }

    /**
     * 查询本部门用户
     *
     * @return
     */
    public String getDeptUser() {
        try {
            deptJson = deptFacadeService.getBusinessManagerDeptJsonAddRoot();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("deptId", deptId);
            map.put("isActived", 1);
            map.put("roleMemberId", 2);
            map.put("userId", this.getLoginInfo().getUserId());
            deptUserList = conversionRoleName(deptService
                    .getUserSubordinatePage(map, this.getPage()));
            totalAmount = deptUserList.getPage().getTotalRowsAmount();// 记录的总数
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("isCancel", 1);
            sysWorkDelegateList = sysWorkDelegateService
                    .getSysWorkDelegateList(parameters);
            return SUCCESS;
        } catch (Exception e) {
            log.error("getDeptUser action error:" + e.getMessage());
            return ERROR;
        }
    }

    /**
     * 模糊查询用户
     *
     * @return
     */
    public String getCoditions() {
        try {
            if (deptId == 0)
                deptId = 2;
            dept = deptService.getDeptById(deptId);
            deptJson = deptFacadeService.getBusinessManagerDeptJsonAddRoot();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("deptSearchCode", dept.getDeptSearchCode());
            map.put("account",
                    StringUtil.ReplaceSQLChar(deptUserBean.getAccount()));
            map.put("userName",
                    StringUtil.ReplaceSQLChar(deptUserBean.getUserName()));
            if (deptUserBean.getIsActived() != null)
                map.put("roleId", deptUserBean.getIsActived());
            map.put("isActived", 1);
            map.put("roleMemberId", 2);
            map.put("userId", this.getLoginInfo().getUserId());
            if (flag == 0) {
                // 本机构用户
                map.put("deptId", deptId);
                deptUserList = conversionRoleName(deptService
                        .getUserSubordinatePage(map, this.getPage()));
                totalAmount = deptUserList.getPage().getTotalRowsAmount();// 记录的总数
            } else {
                // 包含下属机构用户
                deptUserList = conversionRoleName(deptService
                        .getSubsUserSubordinateList(map, this.getPage()));
                totalAmount = deptUserList.getPage().getTotalRowsAmount();
            }
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("isCancel", 1);
            sysWorkDelegateList = sysWorkDelegateService
                    .getSysWorkDelegateList(parameters);
            return SUCCESS;
        } catch (Exception e) {
            log.error("getCoditions action error:" + e.getMessage());
            return ERROR;
        }
    }

    /**
     * 虚拟根节点分页
     */
    public String getRootPage() {
        try {
            deptJson = deptFacadeService.getBusinessManagerDeptJsonAddRoot();
            String codes = "";
            JSONObject obj = deptJson.getJSONObject(deptJson.size() - 1);
            codes = (String) obj.get("searchCode");
            String[] searchCodes = codes.split(",");
            Map<String, Object> map = new HashMap<String, Object>();
            String deptCodes = "";
            for (String deptSearchCode : searchCodes) {
                deptCodes += "DEPT_SEARCH_CODE like" + " " + "'"
                        + deptSearchCode + "%" + "'" + " " + "or" + " ";
            }
            deptCodes = deptCodes.substring(0, deptCodes.lastIndexOf("or"));
            if (deptCodes != null && !deptCodes.equals(""))
                map.put("searchMany", deptCodes);
            if (deptUserBean != null && deptUserBean.getAccount() != null
                    && !deptUserBean.getAccount().equals(""))
                map.put("account", deptUserBean.getAccount());
            if (deptUserBean != null && deptUserBean.getUserName() != null
                    && !deptUserBean.getUserName().equals(""))
                map.put("userName", deptUserBean.getUserName());
            if (deptUserBean != null && deptUserBean.getIsActived() != null
                    && !deptUserBean.getIsActived().equals(""))
                map.put("roleId", deptUserBean.getIsActived());
            map.put("isActived", 1);
            map.put("roleMemberId", 2);
            map.put("userId", this.getLoginInfo().getUserId());

            deptUserList = conversionRoleName(deptService
                    .getSubsUserSubordinateList(map, this.getPage()));
            totalAmount = deptUserList.getPage().getTotalRowsAmount();
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("isCancel", 1);
            sysWorkDelegateList = sysWorkDelegateService
                    .getSysWorkDelegateList(parameters);
            return SUCCESS;
        } catch (Exception e) {
            log.error("getRootPage action error:" + e.getMessage());
            return ERROR;
        }
    }


    public String lookTeamLog() {
        try {
            if (userId != null) {
                deptOrUserName = sysUserService.getSysUserById(userId).getUserName();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("userId", userId);
                sumTeamLog = teamLogService.getTeamLog(map);
            } else if (allDeptId != null) {//包含下属机构
                deptOrUserName = deptService.getDeptById(allDeptId).getDeptName() + "（包含下属机构）";
                sumTeamLog = teamLogService.getTeamLogSum(allDeptId);
            } else if (deptId != null) {//本机构
                deptOrUserName = deptService.getDeptById(deptId).getDeptName() + "（不包含下属机构）";
                sumTeamLog = teamLogService.getDeptTeamLogSum(deptId);
            }
            if(sumTeamLog==null){
                sumTeamLog=new TeamLog();
            }
            return "success";
        } catch (Exception e) {
            logger.error("TeamLogAction lookTeamLog", e);
            return "error";
        }
    }


    /**
     * 导出excel
     */
    public void exportTeamLog() {
        try {
            logger.info("-----当前登录用户：" + this.getLoginInfo().getAccount() + "----- RejectCustomerAction.exportLnRejcetCustomerList");
            TeamLog expTeamLog = null;
            if (userId != null) {
                deptOrUserName = sysUserService.getSysUserById(userId).getUserName();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("userId", userId);
                expTeamLog = teamLogService.getTeamLog(map);
            } else if (allDeptId != null) {//包含下属机构
                deptOrUserName = deptService.getDeptById(allDeptId).getDeptName() + "（含下属机构）";
                expTeamLog = teamLogService.getTeamLogSum(allDeptId);
            } else if (deptId != null) {//本机构
                deptOrUserName = deptService.getDeptById(deptId).getDeptName() + "（不含下属机构）";
                expTeamLog = teamLogService.getDeptTeamLogSum(deptId);
            }

            if (expTeamLog == null) {
                logger.info("空记录集，不用导出");
                return;
            }

            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            String times = sf.format(Calendar.getInstance().getTime());
            String dir = sysParamService.getRecordPath() + File.separator + "teamLogExportList";
            String curDownFilePath = dir + File.separator + deptOrUserName + times + ".xls";
            File f = new File(dir);
            if (!f.exists()) {
                f.mkdirs();
            }
            teamLogService.getTeamLogExporFile(expTeamLog, deptOrUserName, curDownFilePath);
            File downFile = new File(curDownFilePath);
            if (downFile.exists()) {
                synchronized (this) {
                    FileInputStream fis = new FileInputStream(downFile);// 服务器文件路径
                    ServletOutputStream output = this.getResponse().getOutputStream();
                    this.getResponse().addHeader("Content-Disposition", "attachment;filename="
                            + new String(downFile.getName().getBytes("gbk"),
                            "iso8859-1"));
                    BufferedInputStream bis = new BufferedInputStream(fis);// 输入缓冲流
                    BufferedOutputStream bos = new BufferedOutputStream(output);// 输出缓冲流
                    byte buff[] = new byte[4096];// 缓冲字节数
                    int bytesRead;
                    while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                        bos.write(buff, 0, bytesRead);
                    }
                    if (bis != null) {
                        bis.close();
                    }
                    if (bos != null) {
                        bos.flush();// 清空输出缓冲流
                        bos.close();
                    }
                    if (output != null) {
                        output.close();
                    }
                    FileUtil.deleteFile(downFile.getAbsolutePath());
                }
            }
        } catch (Exception e) {
            logger.error("RejectCustomerAction%:exportLnRejcetCustomerList" + e);
        }
    }
}
