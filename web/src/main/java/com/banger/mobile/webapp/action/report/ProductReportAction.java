/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :产品销售统计报表Action
 * Author     :liyb
 * Create Date:Sep 6, 2012
 */
package com.banger.mobile.webapp.action.report;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.report.ProductReportBean;
import com.banger.mobile.domain.model.report.ProductTypeTotalBean;
import com.banger.mobile.domain.model.report.ProductTypeTreeBean;
import com.banger.mobile.facade.crmCounterUser.CrmCounterUserService;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.report.PdtProductReportService;
import com.banger.mobile.util.ExcelUtil;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author liyb
 * @version $Id: ProductReportAction.java,v 0.1 Sep 6, 2012 4:54:53 PM liyb Exp $
 */
public class ProductReportAction extends BaseAction {

    private static final long serialVersionUID = -7457641724222942305L;
    private DeptFacadeService deptFacadeService;                       //机构
    private PdtProductReportService pdtProductReportService;
    private CrmCounterUserService crmCounterUserService;
    private List<ProductReportBean> productList;
    private List<ProductTypeTotalBean> typeList;
    private List<ProductTypeTreeBean> userList;
    private List<ProductTypeTotalBean> subTypeList;
    private ProductReportBean product;
    private String            userName;
    private Integer           userId;
    private String            startDate;
    private String            endDate;
    private JSONArray         jsonArray;
    private String            proId;
    
    private String userIds;
    private String deptIds;
    private String belongType;
    private Integer containSub;//是否查询子机构 1:是 0:否
    
    /**
     * 产品类型树
     */
    public void initProductTypeTree(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            Map<String,Object> map=new HashMap<String,Object>();
            if(product!=null){
                if(!StringUtil.isBlank(product.getProductCode())){
                    map.put("code", product.getProductCode().trim());
                }
                if(!StringUtil.isBlank(product.getProductName())){
                    map.put("name", product.getProductName().trim());
                }
            }
            jsonArray=pdtProductReportService.getProductTypeTreeJson(map);
            out.print(jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 产品销售明细
     */
    public String detailBuyProductCountReport() {
        try {
            if (deptFacadeService.isInChargeOfDepartment()) {
                request.setAttribute("isManage", 1);
            }
            userName = this.getLoginInfo().getUserName();
            userId = this.getLoginInfo().getUserId();
            return SUCCESS;
        } catch (Exception e) {
            log.error("ProductAction detailBuyProductCount error:" + e.getMessage());
            return ERROR;
        }
    }

    /**
     * 产品销售统计查询结果
     * @return
     */
    public String showBuyProductCount() {
        try {
            Map<String,Object> map=new HashMap<String, Object>();
            if(StringUtil.isBlank(deptIds)){
            	if(!StringUtil.isBlank(userIds)){
                    map.put("userIds", userIds);
                }else{
                    map.put("userIds", getUserIdsString(false));
                }
            }
            if(!StringUtil.isBlank(proId)){
                map.put("proId", proId);
            }
            if(!StringUtil.isBlank(deptIds)){
            	String dids=crmCounterUserService.getAllCrmCounterUser(deptIds);
            	if(!StringUtil.isBlank(dids)){
            		map.put("userIds", getUserIdsString(true)+","+dids);
            		map.put("dids", dids);
            	}else{
            		map.put("userIds", getUserIdsString(true));
            	}
                if(containSub==1){
                    map.put("deptIds", getContainSubDeptIds(deptIds));
                }else
                    map.put("deptIds", deptIds);
            }
            if(!StringUtil.isBlank(startDate)&&!StringUtil.isBlank(endDate)){
                map.put("startEndDate", " AND PU.PRODUCT_ID IN(SELECT DISTINCT PRODUCT_ID FROM PDT_PRODUCT_CUSTOMER WHERE IS_DEL=0 AND BUY_DATE>='"+startDate+"' AND BUY_DATE<='"+endDate+"') ");
                map.put("userSearchDate", " AND PU.ID IN(SELECT DISTINCT USER_ID FROM PDT_PRODUCT_CUSTOMER WHERE IS_DEL=0 AND USER_ID IS NOT NULL AND BUY_DATE>='"+startDate+"' AND BUY_DATE<='"+endDate+"') ");
                map.put("startDate", startDate);
                map.put("endDate", endDate);
            }else if(!StringUtil.isBlank(startDate)&&StringUtil.isBlank(endDate)){
                map.put("startEndDate", " AND PU.PRODUCT_ID IN(SELECT DISTINCT PRODUCT_ID FROM PDT_PRODUCT_CUSTOMER WHERE IS_DEL=0 AND BUY_DATE>='"+startDate+"') ");
                map.put("userSearchDate", " AND PU.ID IN(SELECT DISTINCT USER_ID FROM PDT_PRODUCT_CUSTOMER WHERE IS_DEL=0 AND USER_ID IS NOT NULL AND BUY_DATE>='"+startDate+"') ");
                map.put("startDate", startDate);
            }else if(StringUtil.isBlank(startDate)&&!StringUtil.isBlank(endDate)){
                map.put("startEndDate", " AND PU.PRODUCT_ID IN(SELECT DISTINCT PRODUCT_ID FROM PDT_PRODUCT_CUSTOMER WHERE IS_DEL=0 AND BUY_DATE<='"+endDate+"') ");
                map.put("userSearchDate", " AND PU.ID IN(SELECT DISTINCT USER_ID FROM PDT_PRODUCT_CUSTOMER WHERE IS_DEL=0 AND USER_ID IS NOT NULL AND BUY_DATE<='"+endDate+"') ");
                map.put("endDate", endDate);
            }
            map.put("belongType", belongType);
            //大类型销售额
            typeList=pdtProductReportService.getProductTypeTotalList(map);
            //子类型销售额
            subTypeList=pdtProductReportService.getProductSubTypeTotalList(map);
            userList=pdtProductReportService.getProductUserList(map,typeList,subTypeList);
            productList=pdtProductReportService.getProductReportList(map);
            request.setAttribute("userName", this.getLoginInfo().getUserName());
            request.setAttribute("reportTime", new Date());
            
            /**
             * 把报表信息存入session,导出报表使用
             */
            HttpSession session = getRequest().getSession(); 
            session.setAttribute("typeList", typeList);
            session.setAttribute("subTypeList", subTypeList);
            session.setAttribute("userList", userList);
            session.setAttribute("productList", productList);
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ProductAction showBuyProductCountReport error:" + e.getMessage());
            return ERROR;
        }
    }

    /**
     * 导出产品销售统计报表
     */
    public void exportProductCountReport() {
        OutputStream outputStream = null;
        HSSFWorkbook workbook = null;
        try {
            String title = "";
            if (!StringUtil.isBlank(startDate)&&!StringUtil.isBlank(endDate)) {
                title += startDate + "至" + endDate;
            } else if (!StringUtil.isBlank(startDate)&&StringUtil.isBlank(endDate)) {
                title += startDate + "之后";
            } else if (StringUtil.isBlank(startDate)&&!StringUtil.isBlank(endDate)) {
                title += "截止至" + endDate;
            }
            String reportName = title + "产品销售明细统计表 ";
            getResponse().setHeader("Content-disposition","attachment; filename=" + new String(reportName.getBytes("gbk"), "iso8859-1")+ ".xls");//设定输出文件头
            getResponse().setContentType("application/msexcel");//定义输出类型
            getResponse().setCharacterEncoding("UTF-8");
            outputStream = getResponse().getOutputStream();
            HttpSession session = getRequest().getSession(); 
            List<ProductTypeTotalBean> tList=(List<ProductTypeTotalBean>) session.getAttribute("typeList");
            List<ProductTypeTotalBean> sList=(List<ProductTypeTotalBean>) session.getAttribute("subTypeList");
            List<ProductTypeTreeBean> uList=(List<ProductTypeTreeBean>) session.getAttribute("userList");
            List<ProductReportBean> pList=(List<ProductReportBean>) session.getAttribute("productList");
            workbook=pdtProductReportService.exportProductReportExcel(tList, sList, uList, pList, this.getLoginInfo().getUserName());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("exportProductReport action error:" + e.getMessage());
            log.error(e.getMessage());
            workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet();
            ExcelUtil.writeExcelDetailRow(sheet, new String[] { "导出报表发生系统异常" }, 0);
        } finally {
            ExcelUtil.writeToResponse(workbook, outputStream);
        }
    }

    /**
     * 根据部门id集合查询它们及下属的部门id集合 （工具类）
     * @param deptids
     * @return
     */
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
    
    /**
     * 获得登录用户所管理的部门用户的id集合
     * flag 为true是包含 为false不包含
     * @return
     */
    public String getUserIdsString(boolean flag) {
        String userIds = "";
        if (deptFacadeService.isInChargeOfDepartment()) {//存在业务主管
            Integer[] uids = deptFacadeService.getInChargeOfDeptUserIds(flag);
            if (uids != null) {
                for (Integer ids : uids) {
                    userIds = ids + "," + userIds;
                }
                userIds = userIds+getLoginInfo().getUserId().toString();
            }else userIds = getLoginInfo().getUserId().toString();
        } else {
            userIds = getLoginInfo().getUserId().toString();
        }
        return userIds;
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setPdtProductReportService(PdtProductReportService pdtProductReportService) {
        this.pdtProductReportService = pdtProductReportService;
    }

    public JSONArray getJsonArray() {
        return jsonArray;
    }

    public void setJsonArray(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    public List<ProductReportBean> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductReportBean> productList) {
        this.productList = productList;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public String getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(String deptIds) {
        this.deptIds = deptIds;
    }

    public List<ProductTypeTreeBean> getUserList() {
        return userList;
    }

    public void setUserList(List<ProductTypeTreeBean> userList) {
        this.userList = userList;
    }

    public List<ProductTypeTotalBean> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<ProductTypeTotalBean> typeList) {
        this.typeList = typeList;
    }

    public List<ProductTypeTotalBean> getSubTypeList() {
        return subTypeList;
    }

    public void setSubTypeList(List<ProductTypeTotalBean> subTypeList) {
        this.subTypeList = subTypeList;
    }

    public ProductReportBean getProduct() {
        return product;
    }

    public void setProduct(ProductReportBean product) {
        this.product = product;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getBelongType() {
        return belongType;
    }

    public void setBelongType(String belongType) {
        this.belongType = belongType;
    }

    public Integer getContainSub() {
        return containSub;
    }

    public void setContainSub(Integer containSub) {
        this.containSub = containSub;
    }

	public void setCrmCounterUserService(CrmCounterUserService crmCounterUserService) {
		this.crmCounterUserService = crmCounterUserService;
	}
}
