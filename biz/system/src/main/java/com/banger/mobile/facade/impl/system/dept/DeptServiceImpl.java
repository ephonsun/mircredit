/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :机构管理...
 * Author     :cheny
 * Create Date:2012-5-18
 */
package com.banger.mobile.facade.impl.system.dept;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.dept.DeptDao;
import com.banger.mobile.domain.Enum.dept.EnumDept;
import com.banger.mobile.domain.model.dept.DeptUserBean;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.dept.UserOnlineBean;
import com.banger.mobile.domain.model.dept.UserSubordinateBean;
import com.banger.mobile.domain.model.user.IUserInfo;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.dept.DeptService;
import com.banger.mobile.facade.sysWebFlowControl.SysWebFlowControlService;
import com.banger.mobile.util.StringUtil;

/**
 * @author cheny
 * @version $Id: DeptServiceImpl.java,v 0.1 2012-5-18 下午4:40:13 cheny Exp $
 */
public class DeptServiceImpl implements DeptService{
    
    protected final Log   log = LogFactory.getLog(getClass());
    
    private DeptDao deptDao; //机构dao
    
    private CrmCustomerService crmCustomerService;
    
    private SysWebFlowControlService sysWebFlowControlService;
    
    public void setDeptDao(DeptDao deptDao) {
        this.deptDao = deptDao;
    }

    public void setSysWebFlowControlService(SysWebFlowControlService sysWebFlowControlService) {
        this.sysWebFlowControlService = sysWebFlowControlService;
    }

    public void setCrmCustomerService(CrmCustomerService crmCustomerService) {
        this.crmCustomerService = crmCustomerService;
    }
    
    /**
     * 新增机构
     * @param dept
     * @param parentId
     * @param userId
     */
    public void insertDept(SysDept dept,Integer parentId,Integer userId){
    	SysDept deptParent = deptDao.getDeptById(parentId);
    	dept.setDeptParentId(deptParent.getDeptId());
        if (deptParent.getIsLeaf() == 1) {
            deptParent.setIsLeaf(0);
            deptParent.setUpdateUser(userId);
            this.updateDept(deptParent);
        }
        String org = deptParent.getDeptSearchCode();//上级机构的deptSearchCode
        List<SysDept> deptList = this.getChildDept(dept.getDeptParentId());
        String searchkey = this.getSearchKey(org, deptList);
        dept.setDeptSearchCode(searchkey);
        if(StringUtil.isNullOrEmpty(dept.getDeptCode()))dept.setDeptCode(searchkey);

        //添加sortno
        List<SysDept> depts = this.getChildDeptSortno(dept.getDeptParentId());
        if (depts.size() == 0) {
            dept.setSortno(1);
        } else {
            SysDept sysDept = depts.get(depts.size() - 1);
            dept.setSortno(sysDept.getSortno() + 1);
        }
        dept.setIsDel(0);
        dept.setIsLeaf(1);
        dept.setCreateUser(userId);
        if(dept.getRemark()==null)dept.setRemark("");
        int id = deptDao.insertDept(dept);
        sysWebFlowControlService.insertFlowControl(id);
    }

    /**新增机构
     * @param dept
     * @see com.banger.mobile.facade.dept.DeptService#insertDept(com.banger.mobile.domain.model.dept.SysDept)
     */
    public void insertDept(SysDept dept, String deptParentName, int deptId,int userId) {
        SysDept deptParent = null;
        List<SysDept> deptParents = this.getDeptByName(deptParentName);
        if(deptParents.size()>0){
            for (SysDept sysDept : deptParents) {
                if(deptId == sysDept.getDeptId()) deptParent = sysDept;
            }
        }
        dept.setDeptParentId(deptParent.getDeptId());
        if (deptParent.getIsLeaf() == 1) {
            deptParent.setIsLeaf(0);
            deptParent.setUpdateUser(userId);
            this.updateDept(deptParent);
        }
        String org = deptParent.getDeptSearchCode();//上级机构的deptSearchCode
        List<SysDept> deptList = this.getChildDept(dept.getDeptParentId());
        String searchkey = this.getSearchKey(org, deptList);
        dept.setDeptSearchCode(searchkey);

        //添加sortno
        List<SysDept> depts = this.getChildDeptSortno(dept.getDeptParentId());
        if (depts.size() == 0) {
            dept.setSortno(1);
        } else {
            SysDept sysDept = depts.get(depts.size() - 1);
            dept.setSortno(sysDept.getSortno() + 1);
        }
        dept.setIsDel(0);
        dept.setIsLeaf(1);
        dept.setCreateUser(userId);
        //祛除前后空格
        dept.setDeptName(dept.getDeptName().trim());
        dept.setDeptCode(dept.getDeptCode().trim());
        dept.setRemark(dept.getRemark().trim());
        int id = deptDao.insertDept(dept);
        sysWebFlowControlService.insertFlowControl(id);
    }
    /**
     * 部门名称唯一性
     * @param map
     * @return  true 存在       false 不存在
     */
    public boolean validateDeptName(Map<String,Object>  map){
        SysDept dept= deptDao.validateDeptNameIsExist(map);
        if(null==dept) return false;
        return true;
    }
    /**
     * 部门编码唯一性
     * @param map
     * @return
     */
    public boolean validateDeptCode(Map<String,Object>  map){
        SysDept dept= deptDao.validateDeptCodeIsExist(map);
        if(null == dept) return false;
        return true;
    }
    /**
     * 根据Code返回SysDept
     * @param map
     * @return
     */
    public SysDept getDeptByCode(Map<String,Object>  map){
        return deptDao.validateDeptCodeIsExist(map);
    }
    /**
     * 编辑机构
     */
    public JSONObject editorDept(SysDept dept,String deptParentName,int deptParentId,int userId){
        JSONObject jso = new JSONObject();
        Map<String, Object> map = new HashMap<String, Object>();
        int flag = 0;
        int deptId = dept.getDeptId();
        int pId = deptDao.getDeptById(deptId).getDeptParentId();
        SysDept pDept = null;
        if(deptParentId == 1){//根节点及第一级虚拟节点 （不可编辑归属）
            SysDept rDept = deptDao.getDeptById(dept.getDeptId());
            
            //唯一性验证
            map.put("deptParentId", rDept.getDeptParentId());
            map.put("deptName", dept.getDeptName());
            map.put("deptCode", dept.getDeptCode());
            map.put("deptId", deptId);
            if (validateDeptName(map)) {
                jso.put("deptname_isexist", EnumDept.DEPTNAME_ISEXIST.getValue());
            }
            if (validateDeptCode(map)) {
                jso.put("deptcode_isexist", EnumDept.DEPTCODE_ISEXIST.getValue());
            }
            if(!validateDeptName(map) && !validateDeptCode(map)){
                dept.setDeptParentId(rDept.getDeptParentId());
                dept.setIsDel(0);
                dept.setIsLeaf(0);
                dept.setSortno(rDept.getSortno());
                dept.setDeptSearchCode(rDept.getDeptSearchCode());
                dept.setUpdateUser(userId);
                this.updateDept(dept);
            }
            return jso;
          
        }else if(deptParentId == 0 || pId == deptParentId){//归属机构不变
            pDept = deptDao.getDeptById(pId);
            flag = 1;
           
        }else{//更改归属机构
            List<SysDept> pDepts = this.getDeptByName(deptParentName);
            if(pDepts.size()>0){
                for (SysDept sysDept : pDepts) {
                    if(deptParentId == sysDept.getDeptId()) pDept = sysDept;
                }
            }
            flag = 2;
        }
        if(pDept == null){
            jso.put("deptname_notexist", EnumDept.DEPTNAME_NOTEXIST.getValue());
        }else{
            dept.setDeptParentId(pDept.getDeptId());
            //唯一性验证
            map.put("deptName", dept.getDeptName());
            map.put("deptParentId", pDept.getDeptId());
            map.put("deptCode", dept.getDeptCode());
            map.put("deptId", deptId);
            if (validateDeptName(map)) {
                jso.put("deptname_isexist", EnumDept.DEPTNAME_ISEXIST.getValue());
            }
            if (validateDeptCode(map)) {
                jso.put("deptcode_isexist", EnumDept.DEPTCODE_ISEXIST.getValue());
            }
        }
        if (flag == 1){//归属机构不变
            if(!validateDeptName(map) && !validateDeptCode(map)) {
                this.modifyDept(dept, deptParentName, userId);
            }
            return jso;
        }else {//归属机构变化，删除共享
            if (this.verifyDeptId(dept)) {
                jso.put("belongdept_error", EnumDept.VERIFYDEPT1.getValue() + deptParentName
                                            + EnumDept.VERIFYDEPT2.getValue());
            } else {
                if (this.verifySameName(dept)) {
//                    jso.put("samedeptname", EnumDept.SAMEDEPTNAME.getValue());
                    jso.put("deptname_isexist", EnumDept.DEPTNAME_ISEXIST.getValue());
                } else {
                    String searchCode = deptDao.getDeptById(deptId).getDeptSearchCode();
                    List<Integer> userIds = deptDao.getUserDel(searchCode);
                    String ids = "";
                    if (userIds != null && userIds.size() > 0) {
                        for (Integer u : userIds) {
                            ids += u + ",";
                        }
                        ids = ids.substring(0, ids.length() - 1);
                    }
                    crmCustomerService.cancelShareInfoByUserIds(ids);
                    this.modifyDept(dept, deptParentName, userId);
                }
            }
            return jso;
        }
        
        
    }
    
    
    /**
     * 验证归属机构是否是下级机构
     * @param currDept
     * @return
     */
   public boolean verifyDeptId(SysDept currDept){
       SysDept dept = this.getDeptById(currDept.getDeptId());//数据库里面的记录    
       int cparentId = currDept.getDeptParentId(); //当前数据中父部门id
       int dparentId = dept.getDeptParentId();  //数据库中父部门id
       if(cparentId != dparentId){
           String searchCode = dept.getDeptSearchCode();
           Map<String,Object> hashmap = new HashMap<String,Object>();
           hashmap.put("deptSearchCode", searchCode);
           hashmap.put("deptParentId",cparentId);
           List<SysDept> deptList = this.getSubListDept(hashmap);
           if(deptList == null)return false;
           if(deptList.size()>0) return true;
       }
        return false;
    }
   /**
    * 验证同级机构是否有重名
    * @param currDept
    * @return
    */
   public boolean verifySameName(SysDept currDept){
       Map<String,Object> hashmap = new HashMap<String,Object>();
       hashmap.put("deptParentId", currDept.getDeptParentId());
       hashmap.put("deptId", currDept.getDeptId());
       List<SysDept> list = this.getBrotherDept(hashmap);
       if(list.size()> 0){
           for (SysDept sysDept : list) {
               if(sysDept.getDeptName().equals(currDept.getDeptName())) return true;
           }
       } 
       return false;
   }
   /**
    *  归属本部门及下属部门
    */
   public List<SysDept> getSubListDept(Map<String,Object> map){
       return deptDao.getSubListDept(map);
   }
   /**
    * 查找兄弟部门除自己
    */
   public List<SysDept> getBrotherDept(Map<String,Object> map){
       return deptDao.getBrotherDept(map);
   }
   /**
    * 归属机构
    * @param currDept
    * @param deptParentName
    * @param userId
    */
    private void modifyDept(SysDept currDept,String deptParentName,int userId){
        currDept.setUpdateUser(userId);
        int deptId = currDept.getDeptId();
        SysDept cDept = this.getDeptById(deptId);
        currDept.setIsLeaf(cDept.getIsLeaf());
        currDept.setIsDel(0);  
        SysDept dept = this.getDeptById(currDept.getDeptId());
        if(!currDept.getDeptParentId().equals(dept.getDeptParentId())){
            SysDept parentDept = this.getDeptById(currDept.getDeptParentId());
            if(parentDept.getIsLeaf()==1){
                parentDept.setIsLeaf(0);
                parentDept.setUpdateUser(userId);
                this.updateDept(parentDept);
            }
           
            //添加sortno
            List<SysDept> ListDepts = this.getChildDeptSortno(currDept.getDeptParentId());
            if(ListDepts.size()==0){
                currDept.setSortno(1);  
            }else{
                SysDept sysDept = ListDepts.get(ListDepts.size()-1);
                currDept.setSortno(sysDept.getSortno()+1);
            }
            //添加searchkey
            this.setDeptSearchKey(currDept,userId);
            
        }else{
            currDept.setDeptSearchCode(dept.getDeptSearchCode());
            currDept.setSortno(dept.getSortno());
        }
        
	    //祛除前后空格
	    dept.setDeptName(dept.getDeptName().trim());
	    dept.setDeptCode(dept.getDeptCode().trim());
	    dept.setRemark(dept.getRemark().trim());
        this.updateDept(currDept);
    }
    /**
     * 获得父节点的searchkey
     * @param dept
     * @return
     */
    private String getParentSearchKey(SysDept dept)
    {
        SysDept parentDept = this.getDeptById(dept.getDeptParentId());
        return parentDept.getDeptSearchCode();
    }
    /**
     * 递归获得子节点的searchkey
     * @param dept
     */
    private void setDeptSearchKey(SysDept dept,int userId)
    {
        String parentKey = getParentSearchKey(dept);
        List<SysDept> list = this.getChildDept(dept.getDeptParentId());
        String searchKey = getSearchKey(parentKey,list);
        dept.setDeptSearchCode(searchKey);
        dept.setUpdateUser(userId);
        this.updateDept(dept);
        List<SysDept> childList = this.getChildDept(dept.getDeptId());
        for(SysDept cDept : childList)
        {   
            cDept.setUpdateUser(userId);
            this.setDeptSearchKey(cDept,userId);
            this.updateDept(cDept);
        }
    }
    
    /**
     * 查询一级子部门searchCode
     * @param deptParentId
     * @return
     */
    public List<SysDept> getChildDept(int deptParentId){
        return deptDao.getChildDept(deptParentId);
    }
    
    /**
     * 查询一级子部门sortno
     * @param deptParentId
     * @return
     */
    public List<SysDept> getChildDeptSortno(int deptParentId){
        return deptDao.getChildDeptSortno(deptParentId);
    }
    /**根据id删除机构
     * @param deptId
     * @see com.banger.mobile.facade.dept.DeptService#deleteDeptById(int)
     */
    public void deleteDeptById(int deptId) {
        deptDao.deleteDeptById(deptId);
    }

    /**修改机构信息
     * @param dept
     * @see com.banger.mobile.facade.dept.DeptService#updateDept(com.banger.mobile.domain.model.dept.SysDept)
     */
    public void updateDept(SysDept dept) {
        deptDao.updateDept(dept);
    }

    /**根据id查找机构信息
     * @param deptId
     * @return
     * @see com.banger.mobile.facade.dept.DeptService#getDeptById(int)
     */
    public SysDept getDeptById(int deptId) {
        return deptDao.getDeptById(deptId);
    }

    /**用户列表分页
     * @param parameters
     * @param page
     * @return
     * @see com.banger.mobile.facade.dept.DeptService#getDeptUserPage(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<DeptUserBean> getDeptUserPage(Map<String, Object> parameters, Page page) {
        return deptDao.getDeptUserPage(parameters, page);
    }
    /**
     * 包含下属部门分页
     * @param parameters
     * @param page
     * @return
     * @see com.banger.mobile.facade.dept.DeptService#getDeptUserPage(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<DeptUserBean> getDeptSubsUserPage(Map<String, Object> parameters, Page page) {
        return deptDao.getDeptSubsUserPage(parameters, page);
    }
    
    /**
     * 生成部门searchkey
     * @param org 父部门的searchkey
     * @param deptList 兄弟节点的集合
     * @return
     */
    private String getSearchKey(String org,List<SysDept> deptList){
        String searchkey;
        if (deptList.size() == 0){
           String s = "1";
           searchkey = org + StringUtil.padLeft(s,3,'0');
        }else{
            SysDept cdept = deptList.get(0);
            String str = cdept.getDeptSearchCode();
            int key = Integer.parseInt(str.substring(str.length() - 3)) + 1;
            searchkey = org + StringUtil.padLeft(String.valueOf(key),3,'0');
        }
        return searchkey;
    }
    
    /**
     * 根据deptName查询部门
     * @param deptName
     * @return
     */
    public List<SysDept> getDeptByName(String deptName){
        return deptDao.getDeptByName(deptName);
    }
    
    /**
     * 伪删除机构
     */
    public String deleteDept(int deptId,int userId){
        SysDept dept = this.getDeptById(deptId);
        String deptName = dept.getDeptName();
        List<Integer> userIds = deptDao.getUserDel(dept.getDeptSearchCode());
        if(userIds.size()== 0){
            List<SysDept> depts = deptDao.getDeptAndSubs(dept.getDeptSearchCode());
            String deptIds="";
            for (SysDept d : depts) {
                deptIds += d.getDeptId()+",";
            }
            deptIds = deptIds.substring(0,deptIds.length()-1);
            int count = deptDao.getBelongCustomerCount(deptIds);
            if(count == 0){//删除机构
                for (SysDept sysDept : depts) {
                    sysDept.setIsDel(1);
                    sysDept.setUpdateUser(userId);
                    deptDao.updateDept(sysDept);
                }
            }else{//机构下有客户
                return EnumDept.DEPTNAME.getValue()+deptName+EnumDept.DEPTDEL_CUST.getValue();
            }
        }else{//机构下有人员
            return EnumDept.DEPTNAME.getValue()+deptName+EnumDept.DEPTDEL_ERROR.getValue();
        }
        return "";
    }
    /**
     * 上移部门
     * @param deptId
     * @param userId
     * @return
     */
    public String upMovingDept(int deptId,int userId){
       SysDept dept = this.getDeptById(deptId);
       int sortno = dept.getSortno();
       List<SysDept> depts = this.getChildDeptSortno(dept.getDeptParentId()); 
       for (SysDept sysDept : depts) {
           int psortno = sysDept.getSortno();
           if(sortno == psortno){
            int idx = depts.indexOf(sysDept);
            SysDept prevDept = depts.get(idx-1);
            dept.setSortno(prevDept.getSortno());
            prevDept.setSortno(sortno);
            dept.setUpdateUser(userId);
            prevDept.setUpdateUser(userId);
            this.updateDept(prevDept);
            this.updateDept(dept);
            return "";
           }
       }
       return "";
    }
    /**
     * 下移部门
     * @param deptId
     * @param userId
     * @return
     */
    public String downMovingDept(int deptId,int userId){
        SysDept dept = this.getDeptById(deptId);
        int sortno = dept.getSortno();
        List<SysDept> depts = this.getChildDeptSortno(dept.getDeptParentId()); 
        for (SysDept sysDept : depts) {
            int nsortno = sysDept.getSortno();
            if(sortno == nsortno){
             int idx = depts.indexOf(sysDept);  
             SysDept nextDept = depts.get(idx+1);
             dept.setSortno(nextDept.getSortno());
             dept.setUpdateUser(userId);
             nextDept.setUpdateUser(userId);
             nextDept.setSortno(sortno);
             this.updateDept(dept);
             this.updateDept(nextDept);
             return "";
            }
        }
        return "";
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
    public Integer getLoginUserId(){
        return this.getUserLoginInfo().getUserId();
    }
    
    /**
     * 获取角色id集合
     * @return
     */
    private Integer[] getRolesId() {
       String[] strs = getUserLoginInfo().getRoles();
       Integer[] roleIds = null;
       if (strs.length > 0) {
           roleIds = new Integer[strs.length];
           for (int i = 0; i < strs.length; i++) {
               roleIds[i] = Integer.valueOf(strs[i]);
           }
           return roleIds;
       } else
           return null;
 
    }
    
    /**
     * 查询管理的根机构serachKey
     */
    public List<String> getInCharegeSearchKey(Map<String,Object> map){
        return deptDao.getInCharegeSearchKey(map);
    }
    /**
     * 查询所管理的机构树 
     */
    public List<SysDept> getInCharegeDeptTree(String searchKey){
        return deptDao.getInCharegeDeptTree(searchKey);
    }
    
    /**
     * 查询admin所管理的机构树 List
     */
    public List<SysDept> getAdminInCharegeDeptTreeList(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userId", 1);
        map.put("roleId", 1);
        List<SysDept> depts = new ArrayList<SysDept>();
        List<String> keys = this.getInCharegeSearchKey(map);
        String codes = "";
        for (String key : keys) {
            codes += "DEPT_SEARCH_CODE like" +" "+"'"+ key +"%"+"'"+" "+"or"+" ";
        }
        codes ="( "+ codes.substring(0,codes.lastIndexOf("or"))+" )";
        List<SysDept> deptList = this.getInCharegeDeptTree(codes);
        for (SysDept sysDept : deptList) {
                depts.add(sysDept);
        }
        return depts;
    } 
    
    /**
     * 查询机构管理员所管理的机构树 List
     */
    public List<SysDept> getDeptAdminInCharegeDeptTreeList(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userId", getLoginUserId());
        map.put("roleId", 2);
        List<SysDept> depts = new ArrayList<SysDept>();
        List<String> keys = this.getInCharegeSearchKey(map);
        String codes = "";
        for (String key : keys) {
            codes += "DEPT_SEARCH_CODE like" +" "+"'"+ key +"%"+"'"+" "+"or"+" ";
        }
        if(codes.equals("")) return null;
        codes = "( "+codes.substring(0,codes.lastIndexOf("or"))+" )";
        List<SysDept> deptList = this.getInCharegeDeptTree(codes);
        for (SysDept sysDept : deptList) {
                depts.add(sysDept);
        }
        return depts;
    }
    /**
     * admin 机构管理员 机构list
     * @return
     */
    public List<SysDept> getUserJsonTree(){
        String[] strs = getUserLoginInfo().getRoles();
        for (String str : strs) {
            if(str.equals("1")) return getAdminInCharegeDeptTreeList();
            if(str.equals("2")) return getDeptAdminInCharegeDeptTreeList();
        }
        return getAdminInCharegeDeptTreeList();
    }
    
    /**
     * admin 树转换为json
     * @return
     */
    public JSONArray getAdminDeptJson(){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            JSONArray jsonArray = new JSONArray();
            List<SysDept> depts = getAdminInCharegeDeptTreeList();
            if(depts.size()>0){
                for (SysDept dept : depts) {
                  map.put("id", dept.getDeptId());
                  map.put("pId", dept.getDeptParentId());
                  map.put("name", dept.getDeptName());
                  map.put("deptCode", dept.getDeptCode());
                  map.put("searchCode",dept.getDeptSearchCode());
                  if(dept.getDeptParentId().equals(2)){
                      map.put("open", true);
                  }else{
                      map.put("open", false);
                  }
                  jsonArray.add(map);
              }
            }
            return jsonArray;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
          return null;
        }
    }

    
    
    /**
     * admin 树转换为json
     * @return
     */
    public JSONArray getAllDeptJson(){
        Integer[] roleIds = this.getRolesId();
        for (Integer id : roleIds) {
            if(id.equals(1)) return getAdminDeptJson();
            if(id.equals(2)) return getDeptAdminDeptJson();
        }
        return getAdminDeptJson();
    }
    /**
     * 机构管理员 json树  添加默认根节点
     * @return
     */
    public JSONArray getDeptAdminDeptJson(){
      try {
          List<SysDept> depts = getDeptAdminInCharegeDeptTreeList();
          if(depts==null) return null;
          Set<Integer> deptNameList = new HashSet<Integer>();
          for (SysDept sysDept : depts) {
              deptNameList.add(sysDept.getDeptId());
          }
          Map<String, Object> map = new HashMap<String, Object>();
          JSONArray jsonArray = new JSONArray();
          int i = 0;
          String codes="";
          if(depts.size()>0){
              for (SysDept dept : depts) {
                map.put("id", dept.getDeptId());
                if(!deptNameList.contains(dept.getDeptParentId())){
                    map.put("pId", 2);
                    i++;
                    codes += dept.getDeptSearchCode()+",";
                }else{
                    map.put("pId", dept.getDeptParentId());
                }
                map.put("name", dept.getDeptName());
                map.put("searchCode", dept.getDeptSearchCode());
                map.put("deptCode", dept.getDeptCode());
                jsonArray.add(map);
            }
              if(i>=2){//有虚拟节点
                  map.put("id", 2);
                  map.put("pId", 0);
                  map.put("name", EnumDept.DEPTROOT_NAME.getValue());
                  map.put("searchCode", codes);
                  map.put("deptCode", "yes");
                  map.put("open", true);
                  jsonArray.add(map);
              }else{
                 if(jsonArray.size()>0){
                    for (int j=0;j<jsonArray.size()-1;j++) {
                        JSONObject o = jsonArray.getJSONObject(j);
                        if(o.get("pId").equals(2)){//根节点
                           o.put("open", true);
                        }else{
                            o.put("open", false);
                        }
                    }
                 }
                
              }
              
          }
          return jsonArray;
      } catch (Exception e) {
        log.error(e.getMessage(), e);
        return null;
      }
    }
    /**
     * 机构管理员   (不 添加默认根节点) json树 
     * @return
     */
    public JSONArray getDeptAdminDeptJsonRemRoot(){
        
        try {
            List<SysDept> depts = getDeptAdminInCharegeDeptTreeList();
            if(depts==null) return null;
            Set<Integer> deptNameList = new HashSet<Integer>();
            int i=0;
            for (SysDept sysDept : depts) {
                deptNameList.add(sysDept.getDeptId());
            }
            for (SysDept sysDept : depts) {
                if(!deptNameList.contains(sysDept.getDeptParentId())) i++;
            }
            Map<String, Object> map = new HashMap<String, Object>();
            JSONArray jsonArray = new JSONArray();
            if(depts.size()>0){
                for (SysDept dept : depts) {
                  map.put("id", dept.getDeptId());
                  if(!deptNameList.contains(dept.getDeptParentId())){
                      map.put("pId", 2);
                      if(i==1) map.put("open", true);
                  }else{
                      map.put("open", false);
                      map.put("pId", dept.getDeptParentId());
                  }
                  map.put("name", dept.getDeptName());
                  map.put("searchCode", dept.getDeptSearchCode());
                 
                  
                  jsonArray.add(map);
              }
            }
            return jsonArray;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
          return null;
        }
      }
    
    /**
     * 新增编辑树显示
     * @return
     * @see com.banger.mobile.facade.dept.DeptService#getDeptAdminDeptJsonRemoveRoot()
     */
    
    public JSONArray getDeptJsonRemoveRoot(){
        Integer[] roleIds = this.getRolesId();
        for (Integer id : roleIds) {
            if(id.equals(1)) return getAdminDeptJson();
            if(id.equals(2)) return getDeptAdminDeptJsonRemRoot();
        }
        return null;
    }



    /**
     * @param parameters
     * @param page
     * @return
     * @see com.banger.mobile.facade.dept.DeptService#getUserOnlinePage(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<UserOnlineBean> getUserOnlinePage(Map<String, Object> parameters, Page page) {
        return deptDao.getUserOnlinePage(parameters, page);
    }



    /**
     * @param parameters
     * @param page
     * @return
     * @see com.banger.mobile.facade.dept.DeptService#getSubsUserOnlineList(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<UserOnlineBean> getSubsUserOnlineList(Map<String, Object> parameters, Page page) {
        return deptDao.getSubsUserOnlineList(parameters, page);
    }



    /**
     * @param parameters
     * @param page
     * @return
     * @see com.banger.mobile.facade.dept.DeptService#getUserSubordinatePage(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<UserSubordinateBean> getUserSubordinatePage(Map<String, Object> parameters,
                                                                Page page) {
        return deptDao.getUserSubordinatePage(parameters, page);
    }



    /**
     * @param parameters
     * @param page
     * @return
     * @see com.banger.mobile.facade.dept.DeptService#getSubsUserSubordinateList(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<UserSubordinateBean> getSubsUserSubordinateList(Map<String, Object> parameters,
                                                                    Page page) {
        return deptDao.getSubsUserSubordinateList(parameters, page);
    }

    /**
     * @param parameters
     * @return
     * @see com.banger.mobile.facade.dept.DeptService#getUserOnlineOffline(java.util.Map)
     */
    public List<UserOnlineBean> getUserOnlineOffline(Map<String, Object> parameters) {
        return deptDao.getUserOnlineOffline(parameters);
    }

    /**
     * @param parameters
     * @return
     * @see com.banger.mobile.facade.dept.DeptService#getSubsUserOnlineOffline(java.util.Map)
     */
    public List<UserOnlineBean> getSubsUserOnlineOffline(Map<String, Object> parameters) {
        return deptDao.getSubsUserOnlineOffline(parameters);
    }
 
    /**
     * 根据名称和父节点Id获取机构对像
     * @param deptName
     * @param parentId
     * @return
     */
    public SysDept getDeptByName(String deptName,Integer parentId){
    	List<SysDept> deptParents = this.getDeptByName(deptName);
        if(deptParents.size()>0){
            for (SysDept sysDept : deptParents) {
                if(sysDept.getDeptParentId().equals(parentId))return sysDept;
            }
        }
        return null;
    }

	@Override
	public List<DeptUserBean> getDeptUserByIds(String userIds) {
		// TODO Auto-generated method stub
		  return deptDao.getUserDept(userIds);
	}

	@Override
	public List<SysDept> getAllDept() {
		
		return deptDao.getAllDeptForCache();
	}
    
}
