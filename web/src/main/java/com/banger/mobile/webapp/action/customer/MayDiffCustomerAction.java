/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :录音操作Action
 * Author     :xuhj
 * Version    :1.0
 * Create Date:May 3, 2012
 */
package com.banger.mobile.webapp.action.customer;

import com.banger.mobile.util.StringUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.customer.MayDiffCustomerName;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.system.CrmCustomerType;
import com.banger.mobile.facade.customer.MayDiffCustomerService;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.log.OpeventLoginLogService;
import com.banger.mobile.facade.specialDataAuth.SpecialDataAuthService;
import com.banger.mobile.facade.system.CrmCustomerTypeService;
import com.banger.mobile.util.DateUtil;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * 
 * @author yuanme
 * @version MayDiffCustomerAction.java,v 0.1 2012-8-16 下午3:21:40
 */
public class MayDiffCustomerAction extends BaseAction {
    private static final long             serialVersionUID = 1571949684153996822L;

    private MayDiffCustomerService        mayDiffCustomerService;                 //客户Service
    private CrmCustomerTypeService        crmCustomerTypeService;                 //客户类型Service  
    private DeptFacadeService             deptFacadeService;                      //机构Service
    private OpeventLoginLogService        opeventLoginLogService;                 //操作日志service
    private PageUtil<MayDiffCustomerName> mayDiffCustomerNameList;                //客户列表  
    private List<CrmCustomer>             mayDiffCustomerList;                    //客户列表  
    private CrmCustomer                   crmCustomer;                            //客户实体
    private List<CrmCustomerType>         cusTypelist;                            //客户类型集合
    private Map<String, Object>           parameterMap;                           //客户查询MAP
    private Integer                       recordCount;                            //总客户记录数
    private Boolean                       isInChargeof;                           //是否是业务主管
    private String                        diffType;                               //相同类型
    private String                        diffTypeName;
    private int                           tabNum1=0;                                //标签上数量
    private int                           tabNum2=0;
    private int                           tabNum3=0;
    private int                           tabNum4=0;
    private int                           tabNum5=0;
    private SpecialDataAuthService specialDataAuthService;  //特殊数据权限

    /**
     * 构造函数
     */
    public MayDiffCustomerAction() {
        this.crmCustomer = new CrmCustomer();
        this.parameterMap = new HashMap<String, Object>();
    }

    /**
     * 第一次打开主页面
     * @return
     */
    public String FirstLoad() {
        request.setAttribute("dataCode", this.getLoginInfo().getDataCompetence());
        String roleIds=StringUtil.getIdsString(getLoginInfo().getRoles());
        boolean flag=specialDataAuthService.getSysDataAuthCondition(roleIds,"customerInfo");
        request.setAttribute("dataflag", flag);
        cusTypelist = crmCustomerTypeService.getAllCrmCustomerType();
        isInChargeof = deptFacadeService.isInChargeOfDepartment();
        request.setAttribute("userName", this.getLoginInfo().getUserName());
        return SUCCESS;
    }

    /**
     * 查询一组相同数据的客户列表
     * @return
     */
    public String getSameCustomerList() {
        try {
        	isInChargeof = deptFacadeService.isInChargeOfDepartment();
        	beforeQueryEntityList();
            // 页面递交过来的值需要处理一下，比如相同姓名固话，去掉()
            Object temp = parameterMap.get("customerName");
            if (temp != null) {
                String tempS = temp.toString();
                //tempS = tempS.replace("(", "").replace(")", "");
                parameterMap.put("customerName", tempS);
            }
            mayDiffCustomerList = mayDiffCustomerService.getSameCustomerList(parameterMap);
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("取得相同客户列表错误... " + e.getMessage());
            return ERROR;
        }
    }

    /**
     * 根据参数设置不同相同类型
     */
    private void setDiffTypeName() {
        if (diffType != null) {
            if (diffType.equals("name")) {
                diffTypeName = "客户姓名";
            } else if (diffType.equals("phone")) {
                diffTypeName = "手机号码";
            } else if (diffType.equals("idcard")) {
                diffTypeName = "身份证";
            } else if (diffType.equals("namephone")) {
                diffTypeName = "姓名和固话";
            } else if (diffType.equals("namecompany")) {
                diffTypeName = "姓名和单位";
            }
        }
    }

    /**
     * 获得所有子页卡的count
     * @return
     */
    public String FirstLoadName() {
        try {
            setDiffTypeName();

            //取得各个标签相同客户数量
            isInChargeof = deptFacadeService.isInChargeOfDepartment();
            beforeQueryEntityList();
            tabNum1 = mayDiffCustomerService.getMayDiffCustomerNameNumber(parameterMap);
            tabNum2 = mayDiffCustomerService.getMayDiffCustomerPhoneNumber(parameterMap);
            tabNum3 = mayDiffCustomerService.getMayDiffCustomerIdCardNumber(parameterMap);
            tabNum4 = mayDiffCustomerService.getMayDiffCustomerNamePhoneNumber(parameterMap);
            tabNum5 = mayDiffCustomerService.getMayDiffCustomerNameCompanyNumber(parameterMap);

            if (diffType.equals("name")) {
                recordCount = tabNum1;
            } else if (diffType.equals("phone")) {
                recordCount = tabNum2;
            } else if (diffType.equals("idcard")) {
                recordCount = tabNum3;
            } else if (diffType.equals("namephone")) {
                recordCount = tabNum4;
            } else if (diffType.equals("namecompany")) {
                recordCount = tabNum5;
            }

            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("第一次加载可能重复客户，姓名 " + e.getMessage());
            return ERROR;
        }
    }

    /**
     * 查询有多少组相同数据
     * @return
     */
    public String DoQueryDiffName() {
        try {
            showDiffCustomerListPage(); // 取得相同姓名列表
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询可能重复客户，姓名 " + e.getMessage());
            return ERROR;
        }
    }

    public void showDiffCustomerListPage() {
    	isInChargeof = deptFacadeService.isInChargeOfDepartment();
    	beforeQueryEntityList();
        Page pg = this.getPage();
        pg.setPageSize(10);
        if (diffType.equals("name")) {
            mayDiffCustomerNameList = mayDiffCustomerService.getMayDiffCustomerNamePage(
                parameterMap, pg);
        } else if (diffType.equals("phone")) {
            mayDiffCustomerNameList = mayDiffCustomerService.getMayDiffCustomerPhonePage(
                parameterMap, pg);
        } else if (diffType.equals("idcard")) {
            mayDiffCustomerNameList = mayDiffCustomerService.getMayDiffCustomerIdcardPage(
                parameterMap, pg);
        } else if (diffType.equals("namephone")) {
            mayDiffCustomerNameList = mayDiffCustomerService.getMayDiffCustomerNamePhonePage(
                parameterMap, pg);
        } else if (diffType.equals("namecompany")) {
            mayDiffCustomerNameList = mayDiffCustomerService.getMayDiffCustomerNameCompanyPage(
                parameterMap, pg);
        }

        recordCount = this.getPage().getTotalRowsAmount();

    }

    private void beforeQueryEntityList() {
        String inChargeOfUserIds = "";
        String inChargeOfDeptIds = "";
        if (diffType != null) {
            parameterMap.put("diffType", diffType);
        }
        if(crmCustomer!=null){
			if(crmCustomer.getIsTrash()!=null){
				parameterMap.put("isTrash", crmCustomer.getIsTrash());
			}else{
				parameterMap.put("isTrash", 0);
			}
        	if(crmCustomer.getCustomerTypeId()!=null){
        		parameterMap.put("customerTypeId", crmCustomer.getCustomerTypeId());
        	}
        	if(!StringUtil.isEmpty(crmCustomer.getCustomerName())){
        		parameterMap.put("customerName", StringUtil.ReplaceSQLChar(crmCustomer.getCustomerName()));
        	}
        	//客户归属
        	String belongToType = request.getParameter("BelongToType");
        	if(!StringUtil.isEmpty(belongToType)){
        		parameterMap.put("BelongTo", belongToType);
        		if(isInChargeof){
    				inChargeOfDeptIds = getCurrentUserInChargeOfDeptIds();
    				if(StringUtil.isEmpty(inChargeOfDeptIds)){
    					inChargeOfDeptIds = "-1";
					}
    				inChargeOfUserIds = getCurrentInChargeUserIds();
    			}else{
    				inChargeOfDeptIds = "-1";
    				inChargeOfUserIds = this.getLoginInfo().getUserId().toString();
    			}
        		
        		if(belongToType.equals("brAll")){										//所有
        			parameterMap.put("ContainsShare", "ContainsShare");
            	}else if(belongToType.equals("brMine")){								//我的
            		inChargeOfUserIds = this.getLoginInfo().getUserId().toString();
            		parameterMap.put("ContainsShare", "ContainsShare");
            		//处理共享给别人的客户
            		parameterMap.put("QueryUserIds", inChargeOfUserIds);
            	}else if(belongToType.equals("brUser")){								//下属
            		inChargeOfUserIds = request.getParameter("userIds");
            		if(StringUtil.isEmpty(inChargeOfUserIds)){							//没有选择人员的时候
            			parameterMap.put("BelongTo", "brAll");
            			inChargeOfDeptIds = getCurrentUserInChargeOfDeptIds();
        				if(StringUtil.isEmpty(inChargeOfDeptIds)){
        					inChargeOfDeptIds = "-1";
    					}
        				inChargeOfUserIds = this.getLoginInfo().getUserId().toString();
            		}
            		parameterMap.put("ContainsShare", "ContainsShare");
            		//处理共享给别人的客户
            		parameterMap.put("QueryUserIds", inChargeOfUserIds);
            	}else if(belongToType.equals("brDept")){								//机构
            		inChargeOfDeptIds = request.getParameter("deptIds");
            		if(StringUtil.isEmpty(inChargeOfDeptIds)){							//没有选择机构的时候
            			parameterMap.put("BelongTo", "brAll");
            			inChargeOfDeptIds = getCurrentUserInChargeOfDeptIds();
            			if(StringUtil.isEmpty(inChargeOfDeptIds)){
            				inChargeOfDeptIds = "-1";
    					}
            			inChargeOfUserIds = this.getLoginInfo().getUserId().toString();
            		}
            		String containSub = request.getParameter("containSub");
            		if(!StringUtil.isEmpty(containSub)&&containSub.equals("1")){
            			inChargeOfDeptIds = getContainSubDeptIds(inChargeOfDeptIds);
            		}
            		parameterMap.put("ContainsShare", "ContainsShare");
            		//处理共享给别人的客户
            		parameterMap.put("QueryDeptIds", inChargeOfDeptIds);
            	}else if(belongToType.equals("brUnAllocate")){							//待分配
            		inChargeOfDeptIds = getCurrentUserInChargeOfDeptIds();
            		inChargeOfUserIds = "0";
            		//处理共享给别人的客户
            		parameterMap.put("QueryUserIds", "0");
            		parameterMap.put("QueryDeptIds", inChargeOfDeptIds);
            	}
        		parameterMap.put("InChargeOfUserIds", inChargeOfUserIds);
        		parameterMap.put("InChargeOfDeptIds", inChargeOfDeptIds);
        		parameterMap.put("UserId", this.getLoginInfo().getUserId());
        	}
        	
        	String searchFlag=request.getParameter("searchFlag");
            if(searchFlag==null){
                String roleIds=StringUtil.getIdsString(getLoginInfo().getRoles());
                boolean flag=specialDataAuthService.getSysDataAuthCondition(roleIds,"customerInfo");
                if(flag){
                    inChargeOfUserIds = this.getLoginInfo().getUserId().toString();
                    parameterMap.put("brMine", belongToType);
                    parameterMap.put("ContainsShare", "ContainsShare");
                    //处理共享给别人的客户
                    parameterMap.put("QueryUserIds", inChargeOfUserIds);
                    parameterMap.put("InChargeOfDeptIds","-1");
                    parameterMap.put("InChargeOfUserIds",getLoginInfo().getUserId().toString());
                }
            }
        }
    }
    
    //包含本身选中的
    private String getContainSubDeptIds(String deptids) {
        List<SysDept> deptList = deptFacadeService.getContainDeptListByDeptIds(deptids);
        String newDeptIds = "";
        for (SysDept dept : deptList) {
            if (newDeptIds.equals("")) {
                newDeptIds = dept.getDeptId().toString();
            } else {
                newDeptIds = newDeptIds + "," + dept.getDeptId().toString();
            }
        }
        return newDeptIds;
    }
    
    //当前用户有权限的      用户ids
  	private String getCurrentInChargeUserIds(){
  		String userIds = "";
  		Integer[] arr = deptFacadeService.getInChargeOfDeptUserIds();
  		if(arr!=null){
  			for(int i=0; i<arr.length; i++){
  				if(userIds.equals(""))
  					userIds = arr[i].toString();
  				else
  					userIds = userIds + "," + arr[i];
  			}
  		}
  		if(userIds.equals("")){
  			userIds = this.getLoginInfo().getUserId().toString();
  		}else{
  			userIds = userIds + "," +this.getLoginInfo().getUserId(); 
  		}
  		return userIds;
  	}
  	
    //当前用户有权限的机构ids
    private String getCurrentUserInChargeOfDeptIds() {
        String deptIds = "";
        Integer[] arr = deptFacadeService.getInChargeOfDeptIds();
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                if (deptIds.equals(""))
                    deptIds = arr[i].toString();
                else
                    deptIds = deptIds + "," + arr[i];
            }
        }
        return deptIds;
    }

    public int getTabNum1() {
        return tabNum1;
    }

    public void setTabNum1(int tabNum1) {
        this.tabNum1 = tabNum1;
    }

    public int getTabNum2() {
        return tabNum2;
    }

    public void setTabNum2(int tabNum2) {
        this.tabNum2 = tabNum2;
    }

    public int getTabNum3() {
        return tabNum3;
    }

    public void setTabNum3(int tabNum3) {
        this.tabNum3 = tabNum3;
    }

    public int getTabNum4() {
        return tabNum4;
    }

    public void setTabNum4(int tabNum4) {
        this.tabNum4 = tabNum4;
    }

    public int getTabNum5() {
        return tabNum5;
    }

    public void setTabNum5(int tabNum5) {
        this.tabNum5 = tabNum5;
    }

    public String getDiffTypeName() {
        return diffTypeName;
    }

    public void setDiffTypeName(String diffTypeName) {
        this.diffTypeName = diffTypeName;
    }

    public String getDiffType() {
        return diffType;
    }

    public void setDiffType(String diffType) {
        this.diffType = diffType;
    }

    public List<CrmCustomer> getMayDiffCustomerList() {
        return mayDiffCustomerList;
    }

    public void setMayDiffCustomerList(List<CrmCustomer> mayDiffCustomerList) {
        this.mayDiffCustomerList = mayDiffCustomerList;
    }

    public PageUtil<MayDiffCustomerName> getMayDiffCustomerNameList() {
        return mayDiffCustomerNameList;
    }

    public void setMayDiffCustomerNameList(PageUtil<MayDiffCustomerName> mayDiffCustomerNameList) {
        this.mayDiffCustomerNameList = mayDiffCustomerNameList;
    }

    public void setMayDiffCustomerService(MayDiffCustomerService mayDiffCustomerService) {
        this.mayDiffCustomerService = mayDiffCustomerService;
    }

    public Boolean getIsInChargeof() {
        return isInChargeof;
    }

    public void setIsInChargeof(Boolean isInChargeof) {
        this.isInChargeof = isInChargeof;
    }

    public Integer getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
    }

    public CrmCustomerTypeService getCrmCustomerTypeService() {
        return crmCustomerTypeService;
    }

    public List<CrmCustomerType> getCusTypelist() {
        return cusTypelist;
    }

    public void setCusTypelist(List<CrmCustomerType> cusTypelist) {
        this.cusTypelist = cusTypelist;
    }

    public void setCrmCustomerTypeService(CrmCustomerTypeService crmCustomerTypeService) {
        this.crmCustomerTypeService = crmCustomerTypeService;
    }

    public DeptFacadeService getDeptFacadeService() {
        return deptFacadeService;
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }

    public CrmCustomer getCrmCustomer() {
        return crmCustomer;
    }

    public Map<String, Object> getParameterMap() {
        return parameterMap;
    }

    public void setParameterMap(Map<String, Object> parameterMap) {
        this.parameterMap = parameterMap;
    }

    public void setCrmCustomer(CrmCustomer crmCustomer) {
        this.crmCustomer = crmCustomer;
    }

    public OpeventLoginLogService getOpeventLoginLogService() {
        return opeventLoginLogService;
    }

    public void setOpeventLoginLogService(OpeventLoginLogService opeventLoginLogService) {
        this.opeventLoginLogService = opeventLoginLogService;
    }

    public void setSpecialDataAuthService(SpecialDataAuthService specialDataAuthService) {
        this.specialDataAuthService = specialDataAuthService;
    }
}
