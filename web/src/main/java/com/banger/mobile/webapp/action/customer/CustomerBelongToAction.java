/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户归属Action
 * Author     :zsw
 * Create Date:2012-8-2
 */
package com.banger.mobile.webapp.action.customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.domain.model.user.SysUserBean;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * 
 * @author zsw
 * @version $Id: CustomerBelongToAction.java,v 0.1 2012-5-24 下午02:30:34 zsw Exp $
 */
public class CustomerBelongToAction extends BaseAction  {
	private static final long serialVersionUID = -8718061449074564264L;
	private String belongTo;			//客户归属
    private DeptFacadeService dept;		//机构
    private SysUserService user;		//用户service
    
	public SysUserService getUser() {
		return user;
	}

	public void setUser(SysUserService user) {
		this.user = user;
	}

	public DeptFacadeService getDept() {
		return dept;
	}

	public void setDept(DeptFacadeService dept) {
		this.dept = dept;
	}

	public String getBelongTo() {
		return belongTo;
	}

	public void setBelongTo(String belongTo) {
		this.belongTo = belongTo;
	}
	
	public CustomerBelongToAction()
	{
		this.belongTo = "";
	}

	/**
	 * 得到客户归属数据
	 * @return
	 * @throws IOException 
	 */
	public String getBelongToJson() throws IOException
	{
		HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
		if(this.belongTo.equalsIgnoreCase("my"))
		{
			
		}
		else if(this.belongTo.equalsIgnoreCase("dept"))
		{
			List<SysDept> list = dept.getBusinessManagerInCharegeDeptTreeList();
			JSONArray array = this.toTreeJson(list);
        	out.print(array);
		}
		else
		{
			out.print(toUserJson());
		}
		return null;
	}
	/**
	 * 机构树人员json
	 * @return
	 */
	private JSONArray toUserJson(){
		JSONArray array = new JSONArray();
		List<SysUser> userList = dept.getBusinessManagerInCharegeOfUsers();
		List<SysDept> deptlist = dept.getBusinessManagerInCharegeDeptTreeList();
		JSONObject obj = new JSONObject();
		
		for(SysDept dept : deptlist){
			obj.put("id", dept.getDeptId());
            obj.put("pid",dept.getDeptParentId());
            obj.put("name", dept.getDeptName());
            obj.put("flag", "dept");
            array.add(obj);
		}
		
		for (SysUser user : userList) {
            obj.put("id", user.getUserId());
            obj.put("pid",user.getDeptId());
            obj.put("name", user.getUserName());
            obj.put("flag", "user");
            array.add(obj);
        }
		return array;
	}
	/**
	 * 机构json
	 * @param depts
	 * @return
	 */
	public JSONArray toTreeJson( List<SysDept> depts){
	      try {
	          Set<Integer> deptSet = new HashSet<Integer>();
	          for (SysDept sysDept : depts) {
	        	  deptSet.add(sysDept.getDeptId());
	          }
	          Map<String, Object> map = new HashMap<String, Object>();
	          JSONArray jsonArray = new JSONArray();
	          if(depts.size()>0){
	              for (SysDept dept : depts) {
	                map.put("id", dept.getDeptId());
	                if(dept.getDeptParentId() == 0){
	                    map.put("pId", 0);
	                }else{
	                    if(!deptSet.contains(dept.getDeptParentId())){
	                        map.put("pId", 0);
	                    }else{
	                        map.put("pId", dept.getDeptParentId());
	                    }
	                }
	                map.put("name", dept.getDeptName());
	                map.put("open", true);
	                jsonArray.add(map);
	            }
	          }
	          return jsonArray;
	      } catch (Exception e) {
	          //add by zhangxiang 2012-08-09
	          log.error("CustomerBelongToAction toTreeJson action error:"+e.getMessage());
	        return null;
	      }
	    }
}
