/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       : 机构action
 * Author     :cheny
 * Create Date:2012-5-21
 */
package com.banger.mobile.webapp.action.dept;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.Enum.dept.EnumDept;
import com.banger.mobile.domain.model.dept.DeptUserBean;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.dept.DeptService;
import com.banger.mobile.facade.log.OpeventLoginLogService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;
import com.banger.mobile.webapp.util.DBUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cheny
 * @version $Id: DeptAction.java,v 0.1 2012-5-21 上午9:11:20 cheny Exp $
 */
public class DeptAction extends BaseAction {

    private static final long serialVersionUID = 4691828975376110384L;

    private DeptService       deptService;                            //机构service
    private SysUserService    sysUserService;
    private DeptFacadeService       deptFacadeService;
    private OpeventLoginLogService opeventLoginLogService;                       //操作日志service

    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    //input
    private SysDept                dept;          //机构对象 新增 修改
    private int                    deptId;
    private String                 deptParentName;
    private int                    flagInt;
    private DeptUserBean           deptUserBean;      //列表对象
    //output
    private PageUtil<DeptUserBean> deptUserList;  //活动分页对象
    private JSONArray              deptJson;      //机构树json
    private String                 deptName;      //页面输出deptName
    private int                    totalAmount;   //总记录数
    private int                    flag;          //包含下属标识
    private int                    root ; // 根节点

    //method
    public String execute() throws Exception {
        return "success";
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }

    /**
     * 机构树显示
     * @return
     */
    public String showDeptList() {
        try {
            request.setAttribute("isDeptAdmin", deptFacadeService.isDeptAdmin()?getLoginInfo().getAccount():"yes");
            deptJson = deptService.getAllDeptJson();
            String codes = "";
            if(null!=deptJson){
                for(int i=deptJson.size()-1;i>=0;i--){
                    JSONObject obj = deptJson.getJSONObject(i);
                    if(obj.get("pId").equals(0)){//包含虚拟根节点
                        codes = (String)obj.get("searchCode");
                        String[] searchCodes = codes.split(",");
                        Map<String, Object> map = new HashMap<String, Object>();
                        String deptCodes = "";
                        for (String deptSearchCode : searchCodes) {
                            deptCodes += "DEPT_SEARCH_CODE like" +" "+"'"+ deptSearchCode +"%"+"'"+" "+"or"+" ";
                        }
                        deptCodes = "( "+deptCodes.substring(0, deptCodes.lastIndexOf("or"))+" )";
                        map.put("searchMany", deptCodes);
                        deptUserList = conversionRoleName(deptService.getDeptSubsUserPage(map, this.getPage()));
                        totalAmount = deptUserList.getPage().getTotalRowsAmount();
                        break;
                    }else{//根节点不是虚拟节点
                        if(obj.get("pId").equals(2)) {
                            deptId = (Integer)obj.get("id");
                            Map<String, Object> parameterMap = new HashMap<String, Object>();
                            parameterMap.put("deptId", deptId);
                            deptUserList = conversionRoleName(deptService.getDeptUserPage(parameterMap,
                                    this.getPage()));
                            totalAmount = deptUserList.getPage().getTotalRowsAmount();//记录的总数
                            break;
                        }

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
 * 虚拟根节点分页
 */
    public String getRootPage(){
        try {
            deptJson = deptService.getAllDeptJson();
            String codes = "";
            JSONObject obj = deptJson.getJSONObject(deptJson.size()-1);
            codes = (String)obj.get("searchCode");
            String[] searchCodes = codes.split(",");
            Map<String, Object> map = new HashMap<String, Object>();
            String deptCodes = "";
            for (String deptSearchCode : searchCodes) {
                deptCodes += "DEPT_SEARCH_CODE like" +" "+"'"+ deptSearchCode +"%"+"'"+" "+"or"+" ";
            }
            deptCodes = deptCodes.substring(0, deptCodes.lastIndexOf("or"));
            if(deptCodes !=null && !deptCodes.equals(""))
                map.put("searchMany", deptCodes);
            if(deptUserBean!=null && deptUserBean.getAccount()!=null && !deptUserBean.getAccount().equals(""))
                map.put("account",StringUtil.ReplaceSQLChar(deptUserBean.getAccount()));
            if(deptUserBean!=null && deptUserBean.getUserName()!=null && !deptUserBean.getUserName().equals(""))
                map.put("userName",StringUtil.ReplaceSQLChar(deptUserBean.getUserName()));
            if(deptUserBean!=null &&deptUserBean.getIsActived()!=null && !deptUserBean.getIsActived().equals(""))
                map.put("isActived",deptUserBean.getIsActived());
            deptUserList = conversionRoleName(deptService.getDeptSubsUserPage(map, this.getPage()));
            totalAmount = deptUserList.getPage().getTotalRowsAmount();
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
    public String getDeptUser() {
        try {
            deptJson = deptService.getAllDeptJson();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("deptId", deptId);
            deptUserList = conversionRoleName(deptService.getDeptUserPage(map, this.getPage()));
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
        request.setAttribute("isDeptAdmin", deptFacadeService.isDeptAdmin()?getLoginInfo().getAccount():"yes");
        if (flagInt == 1) return this.getDeptUser();//本机构
        else if (flagInt == 2)return this.getCoditions();//下属机构
        return this.getRootPage();//虚拟节点
    }

    /**
     * 模糊查询用户
     * @return
     */
    public String getCoditions() {
        try {
            if(deptId == 0) deptId = 2;
            dept = deptService.getDeptById(deptId);
            deptJson = deptService.getAllDeptJson();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("deptSearchCode", dept.getDeptSearchCode());
            map.put("account",StringUtil.ReplaceSQLChar(deptUserBean.getAccount()));
            map.put("userName",StringUtil.ReplaceSQLChar(deptUserBean.getUserName()));
            map.put("isActived", deptUserBean.getIsActived());
            if (flag == 0) {
                //本机构用户
                map.put("deptId", deptId);
                deptUserList = conversionRoleName(deptService.getDeptUserPage(map, this.getPage()));
                totalAmount = deptUserList.getPage().getTotalRowsAmount();//记录的总数
            } else {
                //包含下属机构用户
                deptUserList = conversionRoleName(deptService.getDeptSubsUserPage(map, this.getPage()));
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
    public PageUtil<DeptUserBean> conversionRoleName(PageUtil<DeptUserBean> beans) {
        try {
        List<DeptUserBean> bean = new ArrayList<DeptUserBean>();
        DeptUserBean item = null;
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

    /**
     *  新增机构 界面
     * @return
     */
    public String showDeptJson() {
        initInsertParam();
        return SUCCESS;
    }
    /**
     * 数据分析页面
     * @return
     */
    public String showDataList(){
    	System.out.println("showDataList()...............");
    	return SUCCESS;
    }

    @SuppressWarnings("rawtypes")
	public String getSqlResultData(){
//    	HttpServletResponse response = ServletActionContext.getResponse();
//      response.setContentType("text/html;charset=utf-8");
//      this.request.removeAttribute("map");
//		this.request.removeAttribute("results");
    	String sqlString = request.getParameter("sqlString");
    	String type = request.getParameter("type");
    	System.out.println(sqlString);
    	StringBuffer sb = new StringBuffer();
    	if(type.equals("execute")){
    		try {
				int[] results=sysUserService.excute(sqlString);
				System.out.println(results);
				int k = 0;
				sb.append("<p>-------执行结果为：</p>");
				for(Integer result:results){
				sb.append(++k+")"+"执行成功条数:  "+result+"条。<br/>");
				}
			} catch (Exception e) {
				sb.append("<p style='color:red;'>执行失败！检查SQL是否正确书写!</p><br/>" + e);
			}
    			this.renderText(sb.toString());
    			return null;
    	} else{
    		try {
    			System.out.println("search.....................................");
    			System.out.println("sqlString:--"+sqlString);
    			String sql = sqlString;
    			if(sql.indexOf(";")!=-1){
    				sql = sql.substring(0,sql.indexOf(";")).toUpperCase();
    			}
//    			if(!sql.startsWith("SELECT")&&sql.indexOf(".")!=-1){
//    				if(sql.){
//
//    				}
//    			}
//    			StringUtil.

    			Map<Integer,String> map = sysUserService.getColumnNames(sql);
				System.out.println("map.....:"+map);
				List<Map> results = sysUserService.getResultData(sql);
				this.request.setAttribute("map", map);
				this.request.setAttribute("results", results);
//				out.print("<p>--------查询结果为：</p>");
//				out.close();
				System.out.println("toGrid.....:");
//				this.renderText(text);
				return "toGrid";

			} catch (Exception e) {
				this.renderText("<p style='color:red;'>执行失败！检查SQL是否正确书写!</p><br/>"+e);
			}
    	}
			return null;

    }

    /**
     * 初始化新增机构参数
     */
    private void initInsertParam(){
        deptJson = deptService.getDeptJsonRemoveRoot();
        try {
            deptParentName = URLDecoder.decode(request.getParameter("deptParentName"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("UnsupportedEncodingException",e);
        }
    }
    /**
     * 新增部门
     * @return
     */
    public String insertDept() {
        try {
            int userId = this.getLoginInfo().getUserId();//没有登录
            deptService.insertDept(dept, deptParentName, deptId,userId);
            opeventLoginLogService.addLog(4,EnumDept.MODEL_ACTION_SAVE.getValue(), 1, 1);
            initInsertParam();
            String state=request.getParameter("state");
            if(state.equals("save"))
                return "save";
            else
                return "saveAndNew";
        } catch (Exception e) {
            opeventLoginLogService.addLog(4,EnumDept.MODEL_ACTION_SAVE.getValue(), 1, 0);
            log.error("insertDept action error:" + e.getMessage());
            return ERROR;
        }
    }
/**
 * 新增验证
 */
    public String validateInsterDept(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            JSONObject jso = new JSONObject();
            SysDept deptParent = null;
            List<SysDept> depts = deptService.getDeptByName(deptParentName);
            if(depts.size()>0){
                for (SysDept sysDept : depts) {
                    if(deptId == sysDept.getDeptId()){
                        deptParent = sysDept;
                    }
                }
            }
            if(deptParent == null){
             jso.put("deptname_notexist",EnumDept.DEPTNAME_NOTEXIST.getValue());
            }else{
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("deptName", dept.getDeptName());
                map.put("deptParentId", deptParent.getDeptId());
                map.put("deptCode", dept.getDeptCode());
                if (deptService.validateDeptName(map)) {
                    jso.put("deptname_isexist",EnumDept.DEPTNAME_ISEXIST.getValue());
                }
                if (deptService.validateDeptCode(map)) {
                    jso.put("deptcode_isexist",EnumDept.DEPTCODE_ISEXIST.getValue());
                }

            }
            out.print(jso.toString());
            out.close();
            return null;
        } catch (Exception e) {
           //e.printStackTrace();
           log.error("validateInsterDept action error:" + e.getMessage());
           return ERROR;
        }
    }

    /**
     * 编辑界面显示
     * @return
     */
    public String deptUpdateForm() {
        try {
            deptJson = deptService.getDeptJsonRemoveRoot();
            dept = deptService.getDeptById(deptId);
            String rootId = (String)request.getParameter("rootId");
            int rId = Integer.valueOf(rootId);
            boolean flag = false;
            for(int i=0;i<deptJson.size();i++){
                JSONObject obj = deptJson.getJSONObject(i);
                if(obj.get("id").equals(deptId)){
                    if(obj.get("pId").equals(2)) flag = true;//虚拟节点的第一级子节点
                }
            }
            if(flag) root = 1;
            if(deptId == rId){//根节点
                root = 1;
            }
            if (deptId == 3) {
                deptParentName = "yes";
            } else {
                SysDept parentDept = deptService.getDeptById(dept.getDeptParentId());
                deptParentName = parentDept.getDeptName();
            }
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("deptUpdateForm action error:" + e.getMessage());
            return ERROR;
        }
    }

    /**
     * 编辑部门
     * @return
     */
    public String updateDept() {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            int userId = this.getLoginInfo().getUserId();//没有登录
            String dpId = request.getParameter("deptParentId");

            JSONObject jso  = deptService.editorDept(dept, deptParentName,Integer.valueOf(dpId),userId);
            out.print(jso.toString());
            out.close();
            opeventLoginLogService.addLog(4,EnumDept.MODEL_ACTION_UPDATE.getValue(), 1, 1);
            return null;
        } catch (Exception e) {
            opeventLoginLogService.addLog(4,EnumDept.MODEL_ACTION_UPDATE.getValue(), 1, 0);
            e.printStackTrace();
            log.error("updateDept action error:" + e.getMessage());
            return ERROR;
        }
    }

    /**
     *伪 删除机构
     * @return
     */
    public String deleteDept() {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            int userId = this.getLoginInfo().getUserId();
            String str = deptService.deleteDept(deptId, userId);
            out.print(str);
            opeventLoginLogService.addLog(4,EnumDept.MODEL_ACTION_DEL.getValue(), 1, 1);
            return null;
        } catch (Exception e) {
            opeventLoginLogService.addLog(4,EnumDept.MODEL_ACTION_DEL.getValue(), 1, 0);
            e.printStackTrace();
            log.error("deleteDept action error:" + e.getMessage());
            return ERROR;
        }
    }

    /***
     * 上移部门
     * @return
     */
    public String upMovingDept() {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            int userId = this.getLoginInfo().getUserId();
            //          int userId = 1;
            String str = deptService.upMovingDept(deptId, userId);
            out.print(str);
            return null;
        } catch (Exception e) {
//            e.printStackTrace();
            log.error("upMovingDept action error:" + e.getMessage());
            return ERROR;
        }
    }

    /**
     * 下移部门
     * @return
     */
    public String downMovingDept() {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            int userId = this.getLoginInfo().getUserId();
            String str = deptService.downMovingDept(deptId, userId);
            out.print(str);
            return null;
        } catch (Exception e) {
//            e.printStackTrace();
            log.error("downMovingDept action error:" + e.getMessage());
            return ERROR;
        }
    }

    public void setDeptService(DeptService deptService) {
        this.deptService = deptService;
    }

    public PageUtil<DeptUserBean> getDeptUserList() {
        return deptUserList;
    }

    public void setDeptUserList(PageUtil<DeptUserBean> deptUserList) {
        this.deptUserList = deptUserList;
    }

    public SysDept getDept() {
        return dept;
    }

    public void setDept(SysDept dept) {
        this.dept = dept;
    }

    public JSONArray getDeptJson() {
        return deptJson;
    }

    public void setDeptJson(JSONArray deptJson) {
        this.deptJson = deptJson;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptParentName() {
        return deptParentName;
    }

    public void setDeptParentName(String deptParentName) {
        this.deptParentName = deptParentName;
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

    public int getFlagInt() {
        return flagInt;
    }

    public void setFlagInt(int flagInt) {
        this.flagInt = flagInt;
    }



    public void setOpeventLoginLogService(OpeventLoginLogService opeventLoginLogService) {
        this.opeventLoginLogService = opeventLoginLogService;
    }

    public DeptUserBean getDeptUserBean() {
        return deptUserBean;
    }

    public void setDeptUserBean(DeptUserBean deptUserBean) {
        this.deptUserBean = deptUserBean;
    }

    public int getRoot() {
        return root;
    }

    public void setRoot(int root) {
        this.root = root;
    }



}
