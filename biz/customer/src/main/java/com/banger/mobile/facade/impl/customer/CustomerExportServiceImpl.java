/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户导出
 * Author     :cheny
 * Create Date:2012-8-24
 */
package com.banger.mobile.facade.impl.customer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.banger.mobile.constants.FileUploadPathConstants;
import com.banger.mobile.dao.user.SysUserDao;
import com.banger.mobile.domain.model.customer.CrmExportBean;
import com.banger.mobile.domain.model.customer.CustomerExportBar;
import com.banger.mobile.domain.model.customer.CustomerExportContext;
import com.banger.mobile.domain.model.templateField.CrmTemplateField;
import com.banger.mobile.domain.model.user.IUserInfo;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.customer.CustomerExportService;
import com.banger.mobile.facade.system.CdSystemService;
import com.banger.mobile.facade.template.CrmTemplateService;
import com.banger.mobile.util.DateUtil;
import com.banger.mobile.util.ReflectorUtil;
import com.banger.mobile.util.TypeUtil;

/**
 * @author cheny
 * @version $Id: CustomerExportServiceImpl.java,v 0.1 2012-8-24 下午7:44:50 cheny Exp $
 */
public class CustomerExportServiceImpl implements CustomerExportService{
    
    private  SysUserDao sysUserDao;                   //用户
    private CrmTemplateService crmTemplateService;    //模板
    private CdSystemService codetableService ;        //省份 城市 
    
    
    public void setCrmTemplateService(CrmTemplateService crmTemplateService) {
        this.crmTemplateService = crmTemplateService;
    }

    public void setSysUserDao(SysUserDao sysUserDao) {
        this.sysUserDao = sysUserDao;
    }

    public void setCodetableService(CdSystemService codetableService) {
        this.codetableService = codetableService;
    }

    private static Map<Integer,CustomerExportBar> customerExportBar;
    
    public static Map<Integer, CustomerExportBar> getCustomerExportBar() {
        if(customerExportBar == null) customerExportBar = new HashMap<Integer,CustomerExportBar>();
        return customerExportBar;
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
     * 创建excel表头
     * @param ctx
     * @param excelSheet
     */
    protected void createHeader(CustomerExportContext ctx,HSSFSheet excelSheet){
    	ctx.setSheetRows(0);
        List<String> head = getHeadExcel(ctx);//表头
        HSSFRow newRow = excelSheet.createRow(0);   
        //循环导出列            
        for(int j=0;j<head.size();j++){               
            HSSFCell newCell = newRow.createCell((short)j);                
            newCell.setCellValue(head.get(j));                
        }        
   }
    
    /**
     * 获得excel sheet索引
     * @param ctx
     * @param book
     * @return
     */
    private HSSFSheet getSheetIndex(CustomerExportContext ctx,HSSFWorkbook book){
    	int n = book.getNumberOfSheets();
    	if(n==0){
    		HSSFSheet sheet = book.createSheet("Sheet" + 1);
    		createHeader(ctx,sheet);
    		return sheet;
    	}
    	else
    	{
    		if(ctx.getSheetRows()<ctx.getSheetMaxRows())
        	{
    			HSSFSheet sheet = book.getSheetAt(n-1);
        		return sheet;
        	}
    		else
    		{
    			HSSFSheet sheet = book.createSheet("Sheet" + (n+1));
        		createHeader(ctx,sheet);
        		return sheet;
    		}
    	}
    }
    
    /**
     * 插入数据行
     */
    public void insertRow(CustomerExportContext ctx,HSSFWorkbook book,List<CrmExportBean> customers){               

    	//循环数据源导出数据集         
        HSSFSheet newsheet = null;
        for (CrmExportBean dr : customers) {
        	newsheet = getSheetIndex(ctx,book);
        	ctx.setRecordCount(ctx.getRecordCount()+1);
        	ctx.setSheetRows(ctx.getSheetRows()+1);
            HSSFRow newRow = newsheet.createRow(ctx.getSheetRows());
            Integer userId = getUserLoginInfo().getUserId();
          
            if(customerExportBar.containsKey(userId)){
            	CustomerExportBar bar = customerExportBar.get(userId);
	            if(bar.getIsRun().equals(1) && bar.getIsStop().equals(0)){
	                bar.setCuurRow(ctx.getRecordCount()+(ctx.getBatchCount()-1)*ctx.getPageSize());  //当前执行的行数
	            }else{
	                bar.setCuurRow(0);
	                break;
	            }
            }
            
            insertCell(ctx,dr,newRow);    
         } 
     
     }
    //插入单元格数据     
    protected void insertCell(CustomerExportContext ctx,CrmExportBean customer,HSSFRow newRow){
        List<String> cellContents =  getContentExcel(ctx,customer);
        for (int i=0;i<cellContents.size();i++) {
            HSSFCell cell = newRow.createCell((short)i);
            cell.setCellValue(cellContents.get(i));
        } 
    }
    
    //构造表头
    public List<String> getHeadExcel(CustomerExportContext ctx){
        Map<String,String> baseColumn = initParameter();//初始化基础字段
        Map<String,CrmTemplateField> feildMap = ctx.getFields(); //所有的模板字段
        Map<String,Object> headMap = new LinkedHashMap<String,Object>();
        List<String> head = new ArrayList<String>();
        List<String> titleHead = new ArrayList<String>();
        Map<String,String> map = new LinkedHashMap<String, String>();
        String[] codes = ctx.getData().split(":");
        for (String code : codes) {
            if(!code.contains(","))  {//基础字段
                if(code.equals("customerBelong")){
                    titleHead.add("用户名");
                    titleHead.add("用户姓名");
                    titleHead.add("归属机构编号");
                    titleHead.add("归属机构名称");
                }else titleHead.add(baseColumn.get(code));
            }
            else{
                String[] cd = code.split(",");
                int n = Integer.valueOf(cd[0]);
                if(n<1) {//自定义字段
                    map.put(cd[2]+","+cd[3],cd[1]);
                }else{//扩展字段
                    headReNew(titleHead,cd[2],cd[1],feildMap);
                    
                }
            }
        }
        //处理自定义字段重复
        List<String> extName=new ArrayList<String>();//字段名集合(去除重复)
        Map<String,String> hmap = new LinkedHashMap<String,String>();
        for(String wd : map.keySet()){
            String[] wds = wd.split(",");
            if(extName.contains(wds[0])){//有重名
                if(head.lastIndexOf(wds[0]) != -1){
                    String fk = (String)headMap.get(wds[0]);
                    headMap.remove(wds[0]);
                    headMap.put(map.get(wds[0]+","+hmap.get(wds[0]))+"_"+wds[0], fk);
                    head.set(head.lastIndexOf(wds[0]),map.get(wds[0]+","+hmap.get(wds[0]))+"_"+wds[0]);
                }
                head.add(map.get(wd)+"_"+wds[0]);
                headMap.put(map.get(wd)+"_"+wds[0], wds[1]);
            }else{//没重名
                hmap.put(wds[0], wds[1]);//记录字段名和属性
                extName.add(wds[0]);
                head.add(wds[0]);
                headMap.put(wds[0], wds[1]);
            }
        }
        //自定义字段表头加上单位
        for(String sk : head){
            for(String key : headMap.keySet()){
                if(key.equals(sk)){
                    String feildKey = (String)headMap.get(key);//字段字段属性
                    for(String fm : feildMap.keySet()){
                        if(fm.equalsIgnoreCase(feildKey)){
                            String per = feildMap.get(fm).getMeasurement();//单位
                            if(per!=null && !per.equals("")){
                                titleHead.add(key+"("+per+")");
                                continue;
                            }else{
                                titleHead.add(key); 
                                continue;
                            }
                        }
                    }
                    continue;
                }
            }
        }
        return titleHead;
    }

    /**
     * 构造表格数据
     * @param ctx
     * @param crmCustomer
     * @return
     */
    public List<String> getContentExcel(CustomerExportContext ctx,CrmExportBean crmCustomer){
        List<String> contents = new ArrayList<String>();
        Map<String,CrmTemplateField> feildMap = ctx.getFields(); //所有的模板字段
        Map<Integer,String> userNameMap = ctx.getUserNames(); //所有的用户名
        String[] codes = ctx.getData().split(":");
        String content="";
        for (String code : codes) {
            if(!code.contains(","))  {//基础字段
                if(code.equals("customerBelong")){
                    content = (String)ReflectorUtil.getPropertyValue(crmCustomer, "account");
                    if(content != null) contents.add(content);
                    else contents.add("");
                    content = (String)ReflectorUtil.getPropertyValue(crmCustomer, "userName");
                    if(content != null) contents.add(content);
                    else contents.add("");
                    content = (String)ReflectorUtil.getPropertyValue(crmCustomer, "deptCode");
                    if(content != null) contents.add(content);
                    else contents.add("");
                    content = (String)ReflectorUtil.getPropertyValue(crmCustomer, "deptName");
                    if(content != null) contents.add(content);
                    else contents.add("");
                }
                /*********导出年龄开始**********/
                else if(code.equals("age")){
                    String birthday = null;
                    String year = DateUtil.getDateToString(new Date()).substring(0,4);
                    Date date =(Date)ReflectorUtil.getPropertyValue(crmCustomer, "birthday");
                    if(date != null) birthday= DateUtil.getDateToString(date);
                    int age =0;
                    if(birthday != null){//有生日
                        String birthyear =birthday.substring(0,4);//出生年份
                        age = Integer.valueOf(year)-Integer.valueOf(birthyear);
                        contents.add(age+"");
                    }else{
                        contents.add("");
                    }
                /*********导出年龄结束**********/ 
                }else if(code.equals("province")){//省份
                    String pro = (String)ReflectorUtil.getPropertyValue(crmCustomer, code);
                    if(pro != null && !pro.equals("")){
                        contents.add( codetableService.getProvinceByCode(pro).getShortName());
                    }else contents.add("");
                }else if(code.equals("city")){//城市
                    String city = (String)ReflectorUtil.getPropertyValue(crmCustomer, code);
                    if(city != null && !city.equals("")){
                        contents.add( codetableService.getCityByCode(city).getShortName());
                    }else contents.add("");    
                }else if("birthday,lastContactDate,createDate,updateDate".contains(code)){//Date类型
                    Date date =(Date)ReflectorUtil.getPropertyValue(crmCustomer, code);
                    if(date != null){
                        contents.add(DateUtil.getDateToString(date));
                    }else contents.add("");
                }else if(code.equals("isReceiveSms")){//是否愿意接收短信
                    Integer i = (Integer)ReflectorUtil.getPropertyValue(crmCustomer, "isReceiveSms");
                    if(i != null){
                        if(i.equals(1))contents.add("是");
                        else contents.add("否");
                    }else contents.add("是");
                    
                }else if("createUser,updateUser".contains(code)){//新建者、修改者
                    Integer id = (Integer)ReflectorUtil.getPropertyValue(crmCustomer, code);
                    if(id != null) {
                        if(userNameMap.containsKey(id)) contents.add(userNameMap.get(id));
                        else contents.add("");
                    }
                    else contents.add("");
                }else{
                    content = (String)ReflectorUtil.getPropertyValue(crmCustomer, code);
                    if(content != null) contents.add(content);
                    else contents.add("");
                }
            
            }else{
                String[] cd = code.split(",");
                int n = Integer.valueOf(cd[0]);
                if(n<1) {//自定义字段
                    changeType(contents,cd[3],crmCustomer,feildMap);
                }else{//扩展字段
                    changeType(contents,cd[2],crmCustomer,feildMap);
//                    contents.add((String)ReflectorUtil.getPropertyValue(crmCustomer, cd[2]));
                }
            }
        }
        return contents;
    }
    
  
    /**
     * 获得所有的自定义字段
     * @return
     */
    public Map<String,CrmTemplateField> getAllFeildType(){
		Map<String,CrmTemplateField> map = new HashMap<String,CrmTemplateField>();
        List<CrmTemplateField> feilds = crmTemplateService.getAllTemplateFields();
        if(feilds !=null && feilds.size()>0){
            for (CrmTemplateField field : feilds) {
            	map.put(field.getFieldName(), field);
            }
        }
        return map;
    } 
    
    /**自定义字段类型转换
     * 
     * @param contents 单元格内容集合
     * @param wd 单元格属性值
     * @param crmCustomer 反射对象
     * @param feildMap 模板字段
     */
    public void changeType(List<String> contents,String wd,CrmExportBean crmCustomer,Map<String,CrmTemplateField> feildMap){
        for(String feildName : feildMap.keySet()){
            if(feildName.equalsIgnoreCase(wd)){
                CrmTemplateField feild = feildMap.get(feildName);
                String feildType = feild.getTemplateFieldType();
                if("MultipleSelect,Select,Text,TextArea".contains(feildType)){
                    String tt = (String)ReflectorUtil.getPropertyValue(crmCustomer, wd);
                    if(tt!=null && !tt.equals("")){
                        contents.add(tt);
                    }else contents.add("");
                }else if(feildType.equals("YesNo")){
                    String tt = (String)ReflectorUtil.getPropertyValue(crmCustomer, wd);
                    if(tt!=null && !tt.equals("")){
                        if(tt.equalsIgnoreCase("yes")) contents.add("是");
                        else contents.add("否");
                    }else contents.add("否");
                }else if(feildType.equals("Date")){
                    Date date = (Date)ReflectorUtil.getPropertyValue(crmCustomer, wd);
                    if(date != null){
                        contents.add(DateUtil.getDateToString(date));
                    }else contents.add("");
                }else if(feildType.equals("Number")){
                   Double d = (Double)ReflectorUtil.getPropertyValue(crmCustomer, wd);
                   if(d != null){
                       String ss = (String)TypeUtil.changeType(d,String.class);
                       if(ss.endsWith(".0")) contents.add(ss.substring(0,ss.length()-2));
                       else contents.add(ss);
                   }else contents.add("");
                }else contents.add("");
            }
        }
    }
    
    
    /**表头改造加单位
     * 
     * @param head 表头集合
     * @param feildName  字段属性
     * @param tempFileName 模板字段名
     *  @param feildMap  模板字段
     */
    public void headReNew(List<String> head,String feildName,String tempFileName,Map<String,CrmTemplateField> fmap){
        for(String key : fmap.keySet()){
            if(key.equalsIgnoreCase(feildName)){
                String per = fmap.get(key).getMeasurement();//单位
                if(per!=null && !per.equals("")){
                    head.add(tempFileName+"("+per+")");
                }else{
                    head.add(tempFileName); 
                }
            }
        }
    }
    /**
     * 所有用户名
     */
    public Map<Integer,String> getAllUserName(){
        Map<Integer,String> userMap = new HashMap<Integer,String>();
        List<SysUser> users = sysUserDao.getAllData(true);
        if(users != null && users.size()>0){
            for (SysUser sysUser : users) {
                userMap.put(sysUser.getUserId(), sysUser.getUserName());
            }
        }
        return userMap;
    }
    /**
     * 获得客户导出内容
     */
    public CustomerExportContext getContext(){
    	CustomerExportContext ctx = new CustomerExportContext();
    	ctx.setParams(this.initParameter());
    	ctx.setFields(this.getAllFeildType());
    	ctx.setUserNames(this.getAllUserName());
    	ctx.setRecordCount(0);
    	ctx.setSheetMaxRows(50000);
    	return ctx;
    }
    
    
    /**
     * 导出基础字段
     */
    public Map<String, String> initParameter(){
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("customerBelong", "客户归属");
        map.put("customerName", "客户姓名");
        map.put("sex", "性别");
        map.put("customerTitle", "称谓");
        map.put("customerNo", "客户编号");
        map.put("customerTypeName", "客户类型");
        map.put("customerIndustryName", "所处行业");
        map.put("idCard", "身份证");
        map.put("birthday", "生日");
        map.put("company", "单位");
        map.put("age", "年龄");
        map.put("remark", "客户简介");
        map.put("province", "省份");
        map.put("city", "城市");
        map.put("address", "联系地址");
        map.put("mobilePhone1", "手机1");
        map.put("mobilePhone1Remark", "手机1备注");
        map.put("mobilePhone2", "手机2");
        map.put("mobilePhone2Remark", "手机2备注");
        map.put("phone", "固话");
        map.put("phoneExt", "固话分机");
        map.put("fax", "传真");
        map.put("faxExt", "传真分机");
        map.put("email", "邮箱");
        map.put("lastContactDate", "最后联系时间");
//        map.put("isReceiveSms", "是否愿意接收短信");
        map.put("createUser", "新建者");
        map.put("createDate", "新建时间");
        map.put("updateUser", "最后修改者");
        map.put("updateDate", "修改时间");
        return map;
    }
    
    /**
     * 创建文件
     */
    public File createFile(HttpServletRequest request){
        try {
            String path = FileUploadPathConstants.CRM_EXPORT_PATH;
            // 服务器绝对路径
            path = request.getRealPath("/") + path;
            // 检查文件夹是否存在
            File obj = new File(path);
            if (!obj.exists()) {
                obj.mkdirs();
            }
            File file = new File(request.getRealPath("/")+FileUploadPathConstants.CRM_EXPORT_PATH+File.separator+"crm_export"+this.getUserLoginInfo().getUserId()+".xls");
            if(! file.exists()){
               file.createNewFile();
            }
            return file;
        } catch (IOException e) {
            return null;
        }
    }
}
