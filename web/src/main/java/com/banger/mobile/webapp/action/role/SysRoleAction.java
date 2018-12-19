/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :角色管理Action
 * Author     :liyb
 * Create Date:2012-5-22
 */
package com.banger.mobile.webapp.action.role;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.banger.mobile.domain.model.menuAuth.SysMenuAuth;
import com.banger.mobile.facade.menuAuth.MenuAuthService;
import com.banger.mobile.facade.specialDataAuth.SpecialDataAuthService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.specialData.SpecialDataAuth;
import com.banger.mobile.domain.Enum.dept.EnumDept;
import com.banger.mobile.domain.Enum.role.EnumSysRole;
import com.banger.mobile.domain.model.funcAuth.SysFuncAuth;
import com.banger.mobile.domain.model.role.SysRole;
import com.banger.mobile.facade.funcAuth.FuncAuthService;
import com.banger.mobile.facade.log.OpeventLogService;
import com.banger.mobile.facade.log.OpeventLoginLogService;
import com.banger.mobile.facade.role.SysRoleService;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author liyb
 * @version $Id: SysRoleAction.java,v 0.1 2012-5-22 下午01:58:23 liyb Exp $
 */
public class SysRoleAction extends BaseAction {

    private static final long serialVersionUID = 7001954844825896652L;
    private SysRoleService sysRoleService;//角色service
    private FuncAuthService funcAuthService; //功能权限service
    private MenuAuthService menuAuthService; //菜单权限service
    private SpecialDataAuthService specialDataAuthService;//特殊数据权限

    private PageUtil<SysRole> rolePage;//角色分页对象
    private SysRole sysRole;//角色实体
    private String saveFlag;//保存标识
    private Integer flag;
    private String oldName;
    private String roleIdDel;
    private OpeventLoginLogService opeventLoginLogService;  //操作日志service

    private JSONArray fancAuthTreeJson;  //功能权限树json
    private JSONArray menuAuthTreeJson;  //菜单权限树json
    private JSONArray dataAuthTreeJson;  //特殊数据权限树json
    private String funcIds;           //功能id集合

    private List<Integer> nodeIds;
    private List<Integer> menuNodeIds;
    private List<Integer> dataNodeIds;

    private Integer adminOrDeptAdimn;

    /**
     * 角色分页列表
     *
     * @return
     */
    public String showSysRolePage() {
        try {
            String[] roleIds = this.getLoginInfo().getRoles();
            if (roleIds.length > 0) {
                for (String id : roleIds) {
                    if (id.equals("1")) {
                        adminOrDeptAdimn = 1;
                        break;
                    }
                    if (id.equals("2")) {
                        adminOrDeptAdimn = 2;
                        break;
                    }
                }
            }
            rolePage = sysRoleService.getSysRolePage(sysRole, this.getPage());
            return SUCCESS;
        } catch (Exception e) {
            log.error("showSysRolePage action error:" + e.getMessage());
            return ERROR;
        }
    }

    /**
     * 分页
     */
    public String getSysRolePage() {
        try {
            String[] roleIds = this.getLoginInfo().getRoles();
            if (roleIds.length > 0) {
                for (String id : roleIds) {
                    if (id.equals("1")) {
                        adminOrDeptAdimn = 1;
                        break;
                    }
                    if (id.equals("2")) {
                        adminOrDeptAdimn = 2;
                        break;
                    }
                }
            }
            rolePage = sysRoleService.getSysRolePage(sysRole, this.getPage());
            return SUCCESS;
        } catch (Exception e) {
            log.error("showSysRolePage action error:" + e.getMessage());
            return ERROR;
        }
    }

    /**
     * 验证角色是否存在
     */
    public void validateRole() {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            JSONObject jso = new JSONObject();
            SysRole role = null;
            if (oldName != null) {//修改验证
                if (!oldName.equals(sysRole.getRoleName())) {
                    role = sysRoleService.getSysRoleById(sysRole);
                }
            } else {//添加验证
                role = sysRoleService.getSysRoleById(sysRole);
            }
            if (role != null) {
                jso.put("role_name", EnumSysRole.VALIDATION_ROLE_NAME.getValue());
            }
            out.print(jso.toString());
            out.close();
        } catch (IOException e) {
        }
    }

    /**
     * 添加菜单功能权限
     */
    private void addMenuFuncAuth(SysRole sysRole) {
        if (funcIds.length() != 0) {
            String[] fIds = funcIds.split(",");
            for (String funcId : fIds) {
                SysFuncAuth funcAuth = new SysFuncAuth();
                funcAuth.setCreateUser(this.getLoginInfo().getUserId());
                funcAuth.setFuncId(Integer.valueOf(funcId));
                funcAuth.setRoleId(sysRole.getRoleId());
                funcAuthService.insertFuncAuth(funcAuth);
            }
        }

    }
    /**
     * 添加菜单功能权限
     */
    private void addSpecaialDataAuth(Integer roleId, String dataIds) {
        if (dataIds.length() != 0) {
            String[] mIds = dataIds.split(",");
            for (String id : mIds) {
                SpecialDataAuth auth = new SpecialDataAuth();
                auth.setCreateUser(this.getLoginInfo().getUserId());
                auth.setDataId(Integer.valueOf(id));
                auth.setRoleId(roleId);
                specialDataAuthService.insertDataAuth(auth);
            }
        }
    }

    /**
     * 添加菜单权限
     */
    private void addMenuAuth(Integer roleId, String menuIds) {
        if (menuIds.length() != 0) {
            String[] mIds = menuIds.split(",");
            for (String id : mIds) {
                SysMenuAuth auth = new SysMenuAuth();
                auth.setCreateUser(this.getLoginInfo().getUserId());
                auth.setMenuId(Integer.valueOf(id));
                auth.setRoleId(roleId);
                menuAuthService.addMenuAuth(auth);
            }
        }
    }

    /**
     * 添加角色
     *
     * @return
     */
    public String saveSysRole() {
        try {
            if (sysRole == null) {
                fancAuthTreeJson = funcAuthService.getAllFuncJson();
                menuAuthTreeJson = menuAuthService.getRoleMenuJson();
                dataAuthTreeJson = specialDataAuthService.getAllDataJson();
                return "toAddPage";
            } else {
                sysRole.setCreateUser(this.getLoginInfo().getUserId());
                //添加角色
                sysRoleService.insertSysRole(sysRole);
                //添加功能权限
                this.addMenuFuncAuth(sysRole);
                String menuIds = request.getParameter("menuIds");
                this.addMenuAuth(sysRole.getRoleId(), menuIds);//添加菜单权限
                String dataIds=request.getParameter("dataIds");
                addSpecaialDataAuth(sysRole.getRoleId(),dataIds);//添加特殊数据权限
                opeventLoginLogService.addLog(10, EnumSysRole.MODEL_ACTION_SAVE.getValue(), 1, 1);
                if (saveFlag.equals("save")) {//保存
                    return "saveRole";
                } else {//保存并新建
                    return "saveCreateRole";
                }
            }
        } catch (Exception e) {
            opeventLoginLogService.addLog(10, EnumSysRole.MODEL_ACTION_SAVE.getValue(), 1, 0);
            log.error("saveSysRole action error:" + e.getMessage());
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 编辑角色
     *
     * @return
     */
    public String updateSysRole() {
        try {
            if (flag != null && flag == 1) {//跳转编辑页面
                sysRole = sysRoleService.getSysRoleById(sysRole);
                //设置功能权限
                fancAuthTreeJson = funcAuthService.getAllFuncJson();
                menuAuthTreeJson = menuAuthService.getRoleMenuJson();
                dataAuthTreeJson = specialDataAuthService.getAllDataJson();
                nodeIds = funcAuthService.getTreeNodeIds(sysRole.getRoleId());
                menuNodeIds = menuAuthService.getTreeNodeIds(sysRole.getRoleId());
                dataNodeIds=specialDataAuthService.getTreeNodeIds(sysRole.getRoleId());
                return "toUpdataPage";
            } else {//提交编辑信息

                //修改功能权限
                List<Integer> funcIdList = new ArrayList<Integer>();
                if (funcIds.length() != 0) {
                    String[] fIds = funcIds.split(",");
                    for (String funcId : fIds) {
                        funcIdList.add(Integer.valueOf(funcId));
                    }
                }
                funcAuthService.updateFuncAuth(sysRole.getRoleId(), funcIdList, this.getLoginInfo().getUserId());
                funcIdList.clear();//清空原有数组共用LIST
                String menuIds = request.getParameter("menuIds");
                if (menuIds.length() != 0) {
                    String[] mIds = menuIds.split(",");
                    for (String menuId : mIds) {
                        funcIdList.add(Integer.valueOf(menuId));
                    }
                }
                //修改菜单权限
                menuAuthService.updateMenuAuth(sysRole.getRoleId(), funcIdList, this.getLoginInfo().getUserId());
                funcIdList.clear();//清空原有数组共用LIST
                String dataIds = request.getParameter("dataIds");
                if (dataIds.length() != 0) {
                    String[] dIds = dataIds.split(",");
                    for (String dataId : dIds) {
                        funcIdList.add(Integer.valueOf(dataId));
                    }
                }
                //修改特殊数据权限
                specialDataAuthService.updateDataAuth(sysRole.getRoleId(), funcIdList, getLoginInfo().getUserId());
                //修改角色
                sysRole.setUpdateUser(this.getLoginInfo().getUserId());
                sysRoleService.updateSysRole(sysRole);
                opeventLoginLogService.addLog(10, EnumSysRole.MODEL_ACTION_UPDATE.getValue(), 1, 1);
                return SUCCESS;
            }
        } catch (Exception e) {
            // e.printStackTrace();
            opeventLoginLogService.addLog(10, EnumSysRole.MODEL_ACTION_UPDATE.getValue(), 1, 0);
            log.error("updateSysRole action error:" + e.getMessage());
            return ERROR;
        }
    }

    /**
     * 查看角色
     */
    public String getSysRoleDetail() {
        try {
            sysRole = sysRoleService.getSysRoleById(sysRole);
            //设置角色功能权限
            fancAuthTreeJson = funcAuthService.getRoleDetailFuncJson(sysRole.getRoleId());
            //设置角色菜单权限
            menuAuthTreeJson = menuAuthService.getDetailMenuByRoleId(sysRole.getRoleId());
            //设置特殊数据权限
            dataAuthTreeJson = specialDataAuthService.getRoleDetailDataJson(sysRole.getRoleId());
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("getSysRoleDetail action error:" + e.getMessage());
            return ERROR;
        }
    }

    /**
     * 删除验证是否在使用,执行删除
     */
    public void validateDel() {
        HttpServletResponse response = ServletActionContext.getResponse();
        PrintWriter out = null;
        try {
            out = response.getWriter();
            Integer count = sysRoleService.getIsUseRoleCount(sysRole.getRoleId());
            if (count > 0) {
                String str = EnumSysRole.SYSROLE.getValue() + sysRoleService.getSysRoleById(sysRole).getRoleName() + EnumSysRole.IS_USE_DEL.getValue();
                out.print(str);
            } else {
                SysRole role = sysRoleService.getSysRoleById(sysRole);
                role.setUpdateUser(this.getLoginInfo().getUserId());
                role.setIsDel(1);
                sysRoleService.updateSysRole(role);
            }
            opeventLoginLogService.addLog(10, EnumSysRole.MODEL_ACTION_DEL.getValue(), 1, 1);
        } catch (Exception e) {
            opeventLoginLogService.addLog(10, EnumSysRole.MODEL_ACTION_DEL.getValue(), 1, 0);
            out.print(EnumSysRole.DEL_FAIL.getValue());
        }
    }

    public SysRoleService getSysRoleService() {
        return sysRoleService;
    }

    public void setSysRoleService(SysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }

    public PageUtil<SysRole> getRolePage() {
        return rolePage;
    }

    public void setRolePage(PageUtil<SysRole> rolePage) {
        this.rolePage = rolePage;
    }

    public SysRole getSysRole() {
        return sysRole;
    }

    public void setSysRole(SysRole sysRole) {
        this.sysRole = sysRole;
    }

    public List<Integer> getMenuNodeIds() {
        return menuNodeIds;
    }

    public void setMenuNodeIds(List<Integer> menuNodeIds) {
        this.menuNodeIds = menuNodeIds;
    }

    public List<Integer> getDataNodeIds() {
        return dataNodeIds;
    }

    public void setDataNodeIds(List<Integer> dataNodeIds) {
        this.dataNodeIds = dataNodeIds;
    }
    public String getSaveFlag() {
        return saveFlag;
    }

    public void setSaveFlag(String saveFlag) {
        this.saveFlag = saveFlag;
    }

    public JSONArray getMenuAuthTreeJson() {
        return menuAuthTreeJson;
    }

    public void setMenuAuthTreeJson(JSONArray menuAuthTreeJson) {
        this.menuAuthTreeJson = menuAuthTreeJson;
    }

    public void setDataAuthTreeJson(JSONArray dataAuthTreeJson) {
        this.dataAuthTreeJson = dataAuthTreeJson;
    }

    public JSONArray getDataAuthTreeJson() {
        return dataAuthTreeJson;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getRoleIdDel() {
        return roleIdDel;
    }

    public void setMenuAuthService(MenuAuthService menuAuthService) {
        this.menuAuthService = menuAuthService;
    }



    public void setRoleIdDel(String roleIdDel) {
        this.roleIdDel = roleIdDel;
    }


    public void setOpeventLoginLogService(OpeventLoginLogService opeventLoginLogService) {
        this.opeventLoginLogService = opeventLoginLogService;
    }

    public void setSpecialDataAuthService(SpecialDataAuthService specialDataAuthService) {
        this.specialDataAuthService = specialDataAuthService;
    }

    public void setFuncAuthService(FuncAuthService funcAuthService) {
        this.funcAuthService = funcAuthService;
    }

    public JSONArray getFancAuthTreeJson() {
        return fancAuthTreeJson;
    }

    public void setFancAuthTreeJson(JSONArray fancAuthTreeJson) {
        this.fancAuthTreeJson = fancAuthTreeJson;
    }

    public String getFuncIds() {
        return funcIds;
    }

    public void setFuncIds(String funcIds) {
        this.funcIds = funcIds;
    }

    public List<Integer> getNodeIds() {
        return nodeIds;
    }

    public void setNodeIds(List<Integer> nodeIds) {
        this.nodeIds = nodeIds;
    }

    public Integer getAdminOrDeptAdimn() {
        return adminOrDeptAdimn;
    }

    public void setAdminOrDeptAdimn(Integer adminOrDeptAdimn) {
        this.adminOrDeptAdimn = adminOrDeptAdimn;
    }


}
