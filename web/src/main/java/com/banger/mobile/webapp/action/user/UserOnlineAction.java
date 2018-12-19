/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :在线用户管理...
 * Author     :yangy
 * Create Date:2012-8-18
 */
package com.banger.mobile.webapp.action.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.Enum.user.EnumUserType;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.dept.DeptUserBean;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.dept.UserOnlineBean;
import com.banger.mobile.domain.model.dept.UserSubordinateBean;
import com.banger.mobile.domain.model.system.CrmCustomerType;
import com.banger.mobile.domain.model.user.CdOnlineStatus;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.dept.DeptService;
import com.banger.mobile.facade.system.CdSystemService;
import com.banger.mobile.facade.user.SysUserOnlineService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author yangyang
 * @version $Id: BelongDeptAction.java,v 0.1 2012-8-18 下午3:27:25 yangyong Exp $
 */
public class UserOnlineAction extends BaseAction {

    private static final long      serialVersionUID = 488778573275089071L;

    private DeptService            deptService;                                     //机构service
    private DeptFacadeService      deptFacadeService;                               //机构Service
    private SysUserService         sysUserService;
    private PageUtil<CrmCustomer>  customerList;                                    //客户列表                 
    private CrmCustomer            crmCustomer      = new CrmCustomer();            //客户实体
    private Map<String, Object>    parameterMap     = new HashMap<String, Object>(); //客户查询MAP

    private DeptUserBean           deptUserBean;                                    //列表对象
    private PageUtil<UserOnlineBean> deptUserList;                                    //活动分页对象
    private int                    deptId;
    private JSONArray              deptJson;                                        //机构树json
    private String                 deptName;                                        //页面输出deptName
    private int                    totalAmount;                                     //总记录数
    private int                    flag;                                            //包含下属标识
    private SysDept                dept;                                            //机构对象 新增 修改
    private int                    flagInt;
    private String                 isSelectCus;
    private Integer                recordCount;                                     //总客户记录数
    private List<CrmCustomerType>  cusTypelist;                                     //客户类型集合
    private Integer                recordId;                                        //客户归属id
    private String                 recordName;                                      //客户归属name
    private List<CdOnlineStatus>    cdOnlineStatusList;                 
    private SysUserOnlineService sysUserOnlineService;
    private CdSystemService  cdSystemService;


    public void setCdSystemService(CdSystemService cdSystemService) {
        this.cdSystemService = cdSystemService;
    }

    


    public void setSysUserOnlineService(SysUserOnlineService sysUserOnlineService) {
        this.sysUserOnlineService = sysUserOnlineService;
    }

    
    public List<CdOnlineStatus> getCdOnlineStatusList() {
        return cdOnlineStatusList;
    }



    public void setCdOnlineStatusList(List<CdOnlineStatus> cdOnlineStatusList) {
        this.cdOnlineStatusList = cdOnlineStatusList;
    }



    public String getIsSelectCus() {
        return isSelectCus;
    }

    public void setIsSelectCus(String isSelectCus) {
        this.isSelectCus = isSelectCus;
    }

    public Integer getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }

    public PageUtil<CrmCustomer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(PageUtil<CrmCustomer> customerList) {
        this.customerList = customerList;
    }

    public CrmCustomer getCrmCustomer() {
        return crmCustomer;
    }

    public void setCrmCustomer(CrmCustomer crmCustomer) {
        this.crmCustomer = crmCustomer;
    }

    public Map<String, Object> getParameterMap() {
        return parameterMap;
    }

    public void setParameterMap(Map<String, Object> parameterMap) {
        this.parameterMap = parameterMap;
    }

    public List<CrmCustomerType> getCusTypelist() {
        return cusTypelist;
    }

    public void setCusTypelist(List<CrmCustomerType> cusTypelist) {
        this.cusTypelist = cusTypelist;
    }


    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    public SysDept getDept() {
        return dept;
    }

    public void setDept(SysDept dept) {
        this.dept = dept;
    }

    public int getFlagInt() {
        return flagInt;
    }

    public void setFlagInt(int flagInt) {
        this.flagInt = flagInt;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public DeptUserBean getDeptUserBean() {
        return deptUserBean;
    }

    public void setDeptUserBean(DeptUserBean deptUserBean) {
        this.deptUserBean = deptUserBean;
    }

    public PageUtil<UserOnlineBean> getDeptUserList() {
        return deptUserList;
    }
    public void setDeptUserList(PageUtil<UserOnlineBean> deptUserList) {
        this.deptUserList = deptUserList;
    }

    public JSONArray getDeptJson() {
        return deptJson;
    }

    public void setDeptJson(JSONArray deptJson) {
        this.deptJson = deptJson;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void setDeptService(DeptService deptService) {
        this.deptService = deptService;
    }

    
    /**
     * 机构树显示
     * @return
     */
    public String showDeptList() {
        cdOnlineStatusList=cdSystemService.getOnlineStatus();
        try {
            deptJson = deptService.getAllDeptJson();
            String codes = "";
            for (int i = deptJson.size() - 1; i >= 0; i--) {
                JSONObject obj = deptJson.getJSONObject(i);
                if (obj.get("pId").equals(0)) {//包含虚拟根节点
                    codes = (String) obj.get("searchCode");
                    String[] searchCodes = codes.split(",");
                    Map<String, Object> map = new HashMap<String, Object>();
                    String deptCodes = "";
                    for (String deptSearchCode : searchCodes) {
                        deptCodes += "DEPT_SEARCH_CODE like" + " " + "'" + deptSearchCode + "%"
                                     + "'" + " " + "or" + " ";
                    }
                    deptCodes = "( " + deptCodes.substring(0, deptCodes.lastIndexOf("or")) + " )";
                    map.put("searchMany", deptCodes);
                    deptUserList = conversionRoleName(deptService.getSubsUserOnlineList(map,
                        this.getPage()));
                    recordCount(deptService.getSubsUserOnlineOffline(map));
                    totalAmount = deptUserList.getPage().getTotalRowsAmount();
                    break;
                } else {//根节点不是虚拟节点
                    if (obj.get("pId").equals(2)) {
                        deptId = (Integer) obj.get("id");
                        Map<String, Object> parameterMap = new HashMap<String, Object>();
                        parameterMap.put("deptId", deptId);
                        deptUserList = conversionRoleName(deptService.getUserOnlinePage(parameterMap,
                            this.getPage()));
                        totalAmount = deptUserList.getPage().getTotalRowsAmount();//记录的总数
                        recordCount(deptService.getUserOnlineOffline(parameterMap));
                        break;
                    }

                }
            }

            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("showDeptList action error:" + e.getMessage());
            return ERROR;
        }
    }

    /**
     * 在线，离线，离开统计
     */
    public void recordCount(List<UserOnlineBean> deptUserList){
        Integer online=0,offline=0,leave=0;
        for (UserOnlineBean userOnlineBean : deptUserList) {
            if(userOnlineBean.getLoginState().equals(EnumUserType.ONLINE.getValue())){
                online++;
            }else if(userOnlineBean.getLoginState().equals(EnumUserType.OFFLINE.getValue())){
                offline++;
            }else{
                leave++;
            }
        }
        request.setAttribute("online", online);
        request.setAttribute("offline", offline);
        request.setAttribute("leave", leave);
    }
    
    /**
     * 虚拟根节点分页
     */
    public String getRootPage() {
        try {
            deptJson = deptService.getAllDeptJson();
            String codes = "";
            JSONObject obj = deptJson.getJSONObject(deptJson.size() - 1);
            codes = (String) obj.get("searchCode");
            String[] searchCodes = codes.split(",");
            Map<String, Object> map = new HashMap<String, Object>();
            String deptCodes = "";
            for (String deptSearchCode : searchCodes) {
                deptCodes += "DEPT_SEARCH_CODE like" + " " + "'" + deptSearchCode + "%" + "'" + " "
                             + "or" + " ";
            }
            deptCodes = deptCodes.substring(0, deptCodes.lastIndexOf("or"));
            if (deptCodes != null && !deptCodes.equals(""))
                map.put("searchMany", deptCodes);
            if (deptUserBean != null && deptUserBean.getAccount() != null
                && !deptUserBean.getAccount().equals(""))
                map.put("account", StringUtil.ReplaceSQLChar(deptUserBean.getAccount()));
            if (deptUserBean != null && deptUserBean.getUserName() != null
                && !deptUserBean.getUserName().equals(""))
                map.put("userName", StringUtil.ReplaceSQLChar(deptUserBean.getUserName()));
            if (deptUserBean != null && deptUserBean.getIsActived() != null
                && !deptUserBean.getIsActived().equals(""))
                map.put("onlineStatusId", deptUserBean.getIsActived());
            deptUserList = conversionRoleName(deptService.getSubsUserOnlineList(map, this.getPage()));
            totalAmount = deptUserList.getPage().getTotalRowsAmount();
            recordCount(deptService.getSubsUserOnlineOffline(map));
            return SUCCESS;
        } catch (Exception e) {
            log.error("getRootPage action error:" + e.getMessage());
            return ERROR;
        }
    }

    /**
     * 查询本部门用户
     * @return
     */
    public String getDeptUserTte() {
        try {
            deptJson = deptService.getAllDeptJson();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("deptId", deptId);
            deptUserList = conversionRoleName(deptService.getUserOnlinePage(map, this.getPage()));
            if(deptId!=0)
                recordCount(deptService.getUserOnlineOffline(map));
            totalAmount = deptUserList.getPage().getTotalRowsAmount();//记录的总数
            return SUCCESS;
        } catch (Exception e) {
            log.error("getDeptUser action error:" + e.getMessage());
            return ERROR;
        }
    }

    /**
     * 分页入口
     * @return
     */
    public String getUserPageList() {
        if (flagInt == 1)
            return this.getDeptUserTte();//本机构
        else if (flagInt == 2)
            return this.getCoditions();//下属机构
        return this.getRootPage();//虚拟节点
    }

    /**
     * 模糊查询用户
     * @return
     */
    public String getCoditions() {
        try {
            if (deptId == 0)
                deptId = 2;
            dept = deptService.getDeptById(deptId);
            deptJson = deptService.getAllDeptJson();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("deptSearchCode", dept.getDeptSearchCode());
            map.put("account", StringUtil.ReplaceSQLChar(deptUserBean.getAccount()));
            map.put("userName", StringUtil.ReplaceSQLChar(deptUserBean.getUserName()));
            map.put("onlineStatusId", deptUserBean.getIsActived());
            if (flag == 0) {
                //本机构用户
                map.put("deptId", deptId);
                deptUserList = conversionRoleName(deptService.getUserOnlinePage(map, this.getPage()));
                recordCount(deptService.getUserOnlineOffline(map));
                totalAmount = deptUserList.getPage().getTotalRowsAmount();//记录的总数
            } else {
                //包含下属机构用户
                deptUserList = conversionRoleName(deptService.getSubsUserOnlineList(map,
                    this.getPage()));
                recordCount(deptService.getSubsUserOnlineOffline(map));
                totalAmount = deptUserList.getPage().getTotalRowsAmount();
            }
            return SUCCESS;
        } catch (Exception e) {
            log.error("getCoditions action error:" + e.getMessage());
            return ERROR;
        }
    }
    /**
     * 组织机构对角色名称进行增加
     * @param beans
     * @return
     */
    public PageUtil<UserOnlineBean> conversionRoleName(PageUtil<UserOnlineBean> beans) {
        try {
        List<UserOnlineBean> bean = new ArrayList<UserOnlineBean>();
        UserOnlineBean item = null;
        String userIds="";
        for (int i = 0; i < beans.getItems().size(); i++) {
            userIds += beans.getItems().get(i).getUserId()+",";
        }
        if(userIds.length()>0) userIds = userIds.substring(0,userIds.length()-1);
        if(!userIds.equals("")){
            Map<Integer,String> map = sysUserService.getRoleNamesByUserId(userIds);
            for (int i = 0; i < beans.getItems().size(); i++) {
                item = beans.getItems().get(i);
                if(map.containsKey(item.getUserId())) item.setRoleNames(map.get(item.getUserId()));
                bean.add(item);
            }
            beans.setItems(bean);
        }
        } catch (Exception e) {
            log.error("conversionRoleName action error:" + e.getMessage());
        }
        return beans;
    }
}
