/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :柜台人员
 * Author     :yujh
 * Create Date:Sep 4, 2012
 */
package com.banger.mobile.webapp.action.crmCounterUser;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.crmCounterUser.CrmCounterUser;
import com.banger.mobile.domain.model.dept.DeptUserBean;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.dept.UserSubordinateBean;
import com.banger.mobile.domain.model.role.SysRole;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.crmCounterUser.CrmCounterUserService;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.dept.DeptService;
import com.banger.mobile.facade.role.SysRoleService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author yujh
 * @version $Id: CrmCounterUserAction.java,v 0.1 Sep 4, 2012 1:33:52 PM Administrator Exp $
 */
public class CrmCounterUserAction extends BaseAction {

    private static final long        serialVersionUID = 8646059936226585942L;

    private CrmCounterUserService    crmCounterUserService;
    private CrmCounterUser           crmCounterUser;
    private DeptService              deptService;
    private SysDept                  dept;                                   //机构对象 新增 修改
    private int                      deptId;
    private String                   deptParentName;
    private int                      flagInt;
    private JSONArray                deptJson;                               //机构树json
    private String                   deptName;                               //页面输出deptName
    private int                      totalAmount;                            //总记录数
    private int                      flag;                                   //包含下属标识
    private int                      root;                                   // 根节点
    private List<SysRole>            sysRoleList;
    private SysRoleService           sysRoleService;
    private DeptFacadeService        deptFacadeService;
    private PageUtil<CrmCounterUser> crmCounterUserList;
    private SysUserService           sysUserService;
    private int                      crmCounterUserId;

    /**
     * 加载树
     * @return
     */
    public String showCrmCounterUserPage() {
        sysRoleList = sysRoleService.getAllRoleName();
        try {
            deptJson = deptFacadeService.getBusinessManagerDeptJsonAddRoot();
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
                    if (deptCodes != null && !deptCodes.equals(""))
                        map.put("searchMany", deptCodes);
                    crmCounterUserList = this.crmCounterUserService.getAllCrmCounterUser(map, this
                        .getPage());
                    totalAmount = crmCounterUserList.getPage().getTotalRowsAmount();
                    break;
                } else {//根节点不是虚拟节点
                    if (obj.get("pId").equals(2)) {
                        deptId = (Integer) obj.get("id");
                        Map<String, Object> parameterMap = new HashMap<String, Object>();
                        parameterMap.put("deptId", deptId);
                        parameterMap.put("userId", this.getLoginInfo().getUserId());
                        crmCounterUserList = this.crmCounterUserService.getAllCrmCounterUser(
                            parameterMap, this.getPage());
                        totalAmount = crmCounterUserList.getPage().getTotalRowsAmount();//记录的总数
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
     * 查询本部门用户
     * @return
     */
    public String getDeptUser() {
        try {
            //       deptJson = deptService.getAllDeptJson();
            deptJson = deptFacadeService.getBusinessManagerDeptJsonAddRoot();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("deptId", deptId);
            map.put("userId", this.getLoginInfo().getUserId());
            crmCounterUserList = this.crmCounterUserService.getAllCrmCounterUser(map, this
                .getPage());
            totalAmount = crmCounterUserList.getPage().getTotalRowsAmount();//记录的总数
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
            return this.getDeptUser();//本机构
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
            deptJson = deptFacadeService.getBusinessManagerDeptJsonAddRoot();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("deptSearchCode", dept.getDeptSearchCode());
            if (flag == 0) {
                //本机构用户
                map.put("deptId", deptId);
                crmCounterUserList = this.crmCounterUserService.getAllCrmCounterUser(map, this
                    .getPage());
                totalAmount = crmCounterUserList.getPage().getTotalRowsAmount();//记录的总数
            } else {
                //包含下属机构用户
                crmCounterUserList = this.crmCounterUserService.getAllCrmCounterUser(map, this
                    .getPage());
                totalAmount = crmCounterUserList.getPage().getTotalRowsAmount();
            }
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
            //                deptJson = deptService.getAllDeptJson();
            deptJson = deptFacadeService.getBusinessManagerDeptJsonAddRoot();
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
            crmCounterUserList = this.crmCounterUserService.getAllCrmCounterUser(map, this
                .getPage());
            totalAmount = crmCounterUserList.getPage().getTotalRowsAmount();
            return SUCCESS;
        } catch (Exception e) {
            log.error("getRootPage action error:" + e.getMessage());
            return ERROR;
        }
    }

    /**
     * 添加柜台人员
     */
    public String toAddSysUserPage() {
        try {
            deptJson = deptFacadeService.getBusinessManagerDeptJsonNoFilter();
            String deptName = URLDecoder.decode(request.getParameter("deptName"), "UTF-8");
            String id = "";
            if (request.getParameter("deptId") != null)
                id = request.getParameter("deptId");
            this.request.setAttribute("deptId", id);
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("userId", this.getLoginInfo().getUserId());
            this.request.setAttribute("deptParentName", deptName);
        } catch (Exception e) {
        }
        return SUCCESS;
    }

    /**
     * 新增柜台人员
     */
    public void insertCrmCounterUser() {
        crmCounterUser.setUserId(this.getLoginInfo().getUserId());
        this.crmCounterUserService.insertCounterUser(crmCounterUser);
    }

    /**
     * 验证是否已存在
     */
    public void validateCrmCounterUser() {
        try {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            SysUser sysUser = new SysUser();
            sysUser.setAccount(crmCounterUser.getCounterUserAccount());
            int count = this.sysUserService.validation("checkAccount", sysUser);
            List<CrmCounterUser> list = this.crmCounterUserService
                .validateCrmCounterUser(crmCounterUser);
            if (count > 0 || list.size() > 0) {
                out.print("已存在相同的用户名");
            }
        } catch (IOException e) {
            log.error("validateCrmCounterUser error", e);
        }
    }

    /**
     * 删除
     */
    public void deleteCrmCounterUser() {
        this.crmCounterUserService.deleteCounterUser(crmCounterUser.getCounterUserId());
    }
    /**
     * 更新
     * @return
     */
    public  String showUpdatePage(){
        try {
            deptJson = deptFacadeService.getBusinessManagerDeptJsonNoFilter();
            crmCounterUser =this.crmCounterUserService.queryById(crmCounterUserId);
            deptName=this.deptService.getDeptById(crmCounterUser.getDeptId()).getDeptName();
            
            return SUCCESS;
        } catch (RuntimeException e) {
            log.error("",e);
            return ERROR;
        }
    }
    /**
     * 修改柜台人员
     * @return
     */
    public String updateCrmCuser(){
        crmCounterUser.setUserId(this.getLoginInfo().getUserId());
        this.crmCounterUserService.updateCounterUser(crmCounterUser);
        return SUCCESS;
    }
    

    public CrmCounterUserService getCrmCounterUserService() {
        return crmCounterUserService;
    }

    public void setCrmCounterUserService(CrmCounterUserService crmCounterUserService) {
        this.crmCounterUserService = crmCounterUserService;
    }

    public CrmCounterUser getCrmCounterUser() {
        return crmCounterUser;
    }

    public void setCrmCounterUser(CrmCounterUser crmCounterUser) {
        this.crmCounterUser = crmCounterUser;
    }

    public DeptService getDeptService() {
        return deptService;
    }

    public void setDeptService(DeptService deptService) {
        this.deptService = deptService;
    }

    public SysDept getDept() {
        return dept;
    }

    public void setDept(SysDept dept) {
        this.dept = dept;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDeptParentName() {
        return deptParentName;
    }

    public void setDeptParentName(String deptParentName) {
        this.deptParentName = deptParentName;
    }

    public int getFlagInt() {
        return flagInt;
    }

    public void setFlagInt(int flagInt) {
        this.flagInt = flagInt;
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

    public int getRoot() {
        return root;
    }

    public void setRoot(int root) {
        this.root = root;
    }

    public PageUtil<CrmCounterUser> getCrmCounterUserList() {
        return crmCounterUserList;
    }

    public void setCrmCounterUserList(PageUtil<CrmCounterUser> crmCounterUserList) {
        this.crmCounterUserList = crmCounterUserList;
    }

    public DeptFacadeService getDeptFacadeService() {
        return deptFacadeService;
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }

    public List<SysRole> getSysRoleList() {
        return sysRoleList;
    }

    public void setSysRoleList(List<SysRole> sysRoleList) {
        this.sysRoleList = sysRoleList;
    }

    public SysRoleService getSysRoleService() {
        return sysRoleService;
    }

    public void setSysRoleService(SysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }

    public SysUserService getSysUserService() {
        return sysUserService;
    }

    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    public int getCrmCounterUserId() {
        return crmCounterUserId;
    }

    public void setCrmCounterUserId(int crmCounterUserId) {
        this.crmCounterUserId = crmCounterUserId;
    }
    
}
