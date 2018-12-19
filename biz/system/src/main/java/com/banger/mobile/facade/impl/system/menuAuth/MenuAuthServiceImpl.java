/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :菜单权限...
 * Author     :cheny
 * Create Date:2012-8-20
 */
package com.banger.mobile.facade.impl.system.menuAuth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.banger.mobile.domain.model.funcAuth.FuncAuthBean;
import com.banger.mobile.domain.model.funcAuth.SysFuncAuth;
import com.banger.mobile.domain.model.menuAuth.MenuAuthBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.banger.mobile.dao.customer.AdvancedCustomerDao;
import com.banger.mobile.dao.menuAuth.MenuAuthDao;
import com.banger.mobile.domain.model.communication.CommPartition;
import com.banger.mobile.domain.model.customer.CrmUserQueryBean;
import com.banger.mobile.domain.model.finance.FeColumn;
import com.banger.mobile.domain.model.menuAuth.SysMenu;
import com.banger.mobile.domain.model.menuAuth.SysMenuAuth;
import com.banger.mobile.domain.model.user.IUserInfo;
import com.banger.mobile.facade.communication.PartitionService;
import com.banger.mobile.facade.finance.FeColumnService;
import com.banger.mobile.facade.funcAuth.FuncAuthService;
import com.banger.mobile.facade.menuAuth.MenuAuthService;

/**
 * @author cheny
 * @version $Id: MenuServiceImpl.java,v 0.1 2012-8-20 下午1:11:34 cheny Exp $
 */
public class MenuAuthServiceImpl implements MenuAuthService{

    private MenuAuthDao menuAuthDao;
    private AdvancedCustomerDao advanceDao;
    private FuncAuthService funcAuthService;
    private FeColumnService feColumnService;
    private PartitionService partitionService;
    
    public AdvancedCustomerDao getAdvanceDao() {
		return advanceDao;
	}
	public void setAdvanceDao(AdvancedCustomerDao advanceDao) {
		this.advanceDao = advanceDao;
	}
	
	public FuncAuthService getFuncAuthService() {
        return funcAuthService;
    }
    public void setFuncAuthService(FuncAuthService funcAuthService) {
        this.funcAuthService = funcAuthService;
    }
    public void setMenuAuthDao(MenuAuthDao menuAuthDao) {
        this.menuAuthDao = menuAuthDao;
    }
    /**
     * 获得登录信息
     * @return
     */
    private IUserInfo getUserLoginInfo(){
        HttpServletRequest req = org.apache.struts2.ServletActionContext.getRequest();
        HttpSession session = req.getSession();
        return (IUserInfo)session.getAttribute("LoginInfo");
    }



    /**
     * 查询登录用户相应的相应的菜单
     */
    public List<SysMenu> getMenuList(){
    	Integer userId = getUserLoginInfo().getUserId();
    	List<SysMenu> menus = new ArrayList<SysMenu>();
    	List<SysMenu> sysMenus = menuAuthDao.getMenuList(getMenuIdsByRoleId());
    	List<CrmUserQueryBean> uqMenus = advanceDao.getUserQueryMenuList(userId);
    	//List<FeColumn> colMenus = feColumnService.getAllColumnList();
    	//List<CommPartition> commList=partitionService.getAllCommPartitions();
    	boolean flag = false;
    	for(SysMenu sm : sysMenus)
    	{
    		if(sm.getMenuParentId()>1 && !flag)
    		{
    			flag=true;
    	    	for(CrmUserQueryBean uq : uqMenus)
    	    	{
    	    		SysMenu m = new SysMenu();
    	    		m.setMenuId(uq.getUserQueryId()+1000);
    	    		m.setMenuCode("");
    	    		m.setMenuName(uq.getQueryName());
    	    		m.setMenuParentId(1);
    	    		m.setUrl("../advancedCustomer/showCustomerPage.html?queryId="+uq.getUserQueryId());
    	    		m.setMenuType("customer");
    	    		menus.add(m);
    	    	}
//    	    	for(FeColumn col:colMenus){
//    	    		SysMenu m = new SysMenu();
//    	    		m.setMenuId(col.getColumnId()+500);
//    	    		m.setMenuCode("");
//    	    		m.setMenuName(col.getColumnName());
//    	    		m.setMenuParentId(11);
//    	    		m.setUrl("../finance/showFinanceMainPage.html?columnId="+col.getColumnId());
//    	    		m.setMenuType("");
//    	    		menus.add(m);
//    	    	}
//    	    	for (CommPartition comm:commList) {
//    	    	    SysMenu m = new SysMenu();
//                    m.setMenuId(comm.getPartitionId()+700);
//                    m.setMenuCode("");
//                    m.setMenuName(comm.getPartitionName());
//                    m.setMenuParentId(12);
//                    m.setUrl("../commTheme/initThemePage.html?partitionId="+comm.getPartitionId());
//                    m.setMenuType("");
//                    menus.add(m);
//                }
    		}
    		menus.add(sm);
    	}
    	
        return menus;
    }

    public void addMenuAuth(SysMenuAuth sysMenuAuth) {
       menuAuthDao.addMenuAuth(sysMenuAuth);
    }

    /**
     * 根据角色查询菜单权限
     */
    public List<SysMenuAuth> getMenuAuthByRoleId(int roleId){
        return menuAuthDao.getMenuAuthByRoleId(roleId);
    }
    /**
     * 根据登录用户(多角色合并)查询相应菜单id
     */
    public String getMenuIdsByRoleId(){
        String menuIds="";
        String[] strs = getUserLoginInfo().getRoles();
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        if(strs.length > 0){
            for (String id : strs) {
                List<SysMenuAuth> menuAuth = getMenuAuthByRoleId(Integer.valueOf(id));
                if(menuAuth.size() > 0){
                    for (SysMenuAuth sysMenuAuth : menuAuth) {
                        map.put(sysMenuAuth.getMenuId(), 1);
                    }
                }
            }
           
        }
        for(Integer i : map.keySet()){
            if(i.equals(71) && !funcAuthService.isRiskEvaluation()) continue;
            if(i.equals(73) && !funcAuthService.isFinancialPlan()) continue;
            menuIds += i+",";
        }
        if(menuIds.length()>0)
             menuIds = menuIds.substring(0,menuIds.length()-1);
        return menuIds;
    }
    
//    public void setJsonNode(JSONObject node,List<SysMenu> list){
//        Integer pId = (Integer)node.get("id");
//        JSONArray ja  = new JSONArray();
//        for(SysMenu m : list)
//        {
//            if(m.getMenuParentId().equals(pId))
//            {
//                JSONObject jo = new JSONObject();
//                jo.put("id", m.getMenuId());
//                jo.put("pId", m.getMenuParentId());
//                jo.put("title", m.getMenuName());
//                jo.put("url", m.getUrl());
//                jo.put("lock", true);
//                setJsonNode(jo,list);
//                ja.add(jo);
//            }
//        }
//        node.put("items",ja);
//       
//    }
    /**
     * 菜单权限json
     * @return
     * @see com.banger.mobile.facade.menuAuth.MenuAuthService#getMenuJson()
     */
    public JSONArray getMenuJson(){
        JSONArray ja  = new JSONArray();
        JSONObject jo = new JSONObject();
        List<SysMenu> list = getMenuList();
        for (SysMenu m : list) {
            if(m.getMenuParentId().equals(0)){
                jo.put("mid", m.getMenuId());
                jo.put("model", m.getMenuName());
                JSONArray jaa  = new JSONArray();
                JSONObject joo = new JSONObject();
                for (SysMenu mu : list){
                    if(!mu.getMenuParentId().equals(0) && mu.getMenuParentId().equals(m.getMenuId())){
                        if(mu.getMenuId()==162) joo.put("confirm", true);
                        joo.put("modelname", mu.getMenuName());
                        joo.put("id", mu.getMenuId());
                        joo.put("pid", "");
                        joo.put("title", mu.getMenuName());
                        joo.put("url", mu.getUrl());
                        joo.put("lock", false);
                        if(mu.getMenuType()!=null && mu.getMenuType().equals("customer")) joo.put("sort", "custom");
                        jaa.add(joo);
                        jo.put("items", jaa);
                    }
                }
                ja.add(jo);
            }
        }
        
        return ja;
    }

    /**
     * 获得功能权限树集合
     * @return
     */
    public List<MenuAuthBean> getMenuAuthTree() {
       return menuAuthDao.getMenuAuthTree();
    }


    /**
     * 树转换为json
     * @return
     */
    public JSONArray getAllMenuJson(){
        Map<String, Object> map = new HashMap<String, Object>();
        JSONArray jsonArray = new JSONArray();
        List<MenuAuthBean> beans = this.getMenuAuthTree();
        if(beans.size()>0){
            for (MenuAuthBean menu : beans) {
                map.put("id", menu.getMenuId());
                map.put("pId", menu.getMenuParentId());
                map.put("name", menu.getMenuName());
                map.put("authType", menu.getType());
                if(menu.getMenuParentId().equals(0)){
                    map.put("open", true);
                }
                jsonArray.add(map);
            }
        }
        return jsonArray;
    }

    /**
     * 树转换为json
     * @return
     */
    public JSONArray getRoleMenuJson(){
        Map<String, Object> map = new HashMap<String, Object>();
        JSONArray jsonArray = new JSONArray();
        List<MenuAuthBean> beans = menuAuthDao.getRoleMenuAuthTree();
        if(beans.size()>0){
            for (MenuAuthBean menu : beans) {
                map.put("id", menu.getMenuId());
                map.put("pId", menu.getMenuParentId());
                map.put("name", menu.getMenuName());
                map.put("authType", menu.getType());
                if(menu.getMenuParentId().equals(0)){
                    map.put("open", true);
                }
                jsonArray.add(map);
            }
        }
        return jsonArray;
    }

    /**
     * 返回角色对应菜单权限节点的id集合
     */
    public List<Integer> getTreeNodeIds(int roleId){
        List<Integer> nodeIds = new ArrayList<Integer>();
        List<MenuAuthBean> beans = menuAuthDao.getDetailMenuByRoleId(roleId);
        for (int i=0;i<beans.size();i++) {
            nodeIds.add(beans.get(i).getMenuId());
        }
        return nodeIds;
    }

    public void updateMenuAuth(int roleId, List<Integer> menuIds, int userId) {
        List<SysMenuAuth> list = menuAuthDao.getMenuAuthByRoleId(roleId);
        SysMenuAuth menuAuth = new SysMenuAuth();
        if(list.size() > 0){
            //原来权限没有的要新增
            for (Integer menuId : menuIds) {
                boolean flag = false;
                for (SysMenuAuth sysMenuAuth : list) {
                    if(sysMenuAuth.getMenuId().equals(menuId)){
                        flag = true;
                    }
                }
                if(!flag){
                    menuAuth.setRoleId(roleId);
                    menuAuth.setMenuId(menuId);
                    menuAuth.setCreateUser(userId);
                    menuAuthDao.addMenuAuth(menuAuth);
                }
            }
            //删除修改前的权限
            for (SysMenuAuth sysMenuAuth : list) {
                menuAuth = sysMenuAuth;
                boolean flag = false;
                for (Integer menuId : menuIds) {
                    if(sysMenuAuth.getMenuId().equals(menuId)){
                        flag = true;
                    }
                }
                if(!flag){
                    menuAuthDao.deleteMenuAuthByMenuId(menuAuth.getMenuAuthId());
                }
            }
        }else{
            //数据库里面没有全部新增
            for (Integer menuId : menuIds) {
                menuAuth.setCreateUser(userId);
                menuAuth.setMenuId(menuId);
                menuAuth.setRoleId(roleId);
                menuAuthDao.addMenuAuth(menuAuth);
            }
        }
    }

    /**
     * 根据角色查询菜单权限JSON树数据
     */
    public JSONArray getDetailMenuByRoleId(Integer roleId) {
        Map<String, Object> map = new HashMap<String, Object>();
        JSONArray jsonArray = new JSONArray();
        List<MenuAuthBean> beans = menuAuthDao.getDetailMenuByRoleId(roleId);
        if(beans.size()>0){
            for (MenuAuthBean menu : beans) {
                map.put("id", menu.getMenuId());
                map.put("pId", menu.getMenuParentId());
                map.put("name", menu.getMenuName());
                map.put("authType", menu.getType());
                if(menu.getMenuParentId().equals(0)){
                    map.put("open", true);
                }
                jsonArray.add(map);
            }
        }
        return jsonArray;
    }

    public FeColumnService getFeColumnService() {
		return feColumnService;
	}
	public void setFeColumnService(FeColumnService feColumnService) {
		this.feColumnService = feColumnService;
	}
	public PartitionService getPartitionService() {
		return partitionService;
	}
	public void setPartitionService(PartitionService partitionService) {
		this.partitionService = partitionService;
	}
    
}
