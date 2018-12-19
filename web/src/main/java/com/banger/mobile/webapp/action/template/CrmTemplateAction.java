/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :任务管理Action
 * Author     :qianj
 * Create Date:2012-5-25
 */
package com.banger.mobile.webapp.action.template;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.banger.mobile.domain.model.template.CrmTemplate;
import com.banger.mobile.domain.model.templateField.CrmTemplateField;
import com.banger.mobile.facade.fieldCodeData.CrmFieldCodeDataService;
import com.banger.mobile.facade.log.OpeventLoginLogService;
import com.banger.mobile.facade.template.CrmTemplateService;
import com.banger.mobile.facade.templateField.CrmTemplateFieldService;
import com.banger.mobile.webapp.action.BaseAction;
import com.banger.mobile.PageUtil;

/**
 * @author qianj
 * @version $Id: CrmTaskAction.java,v 0.1 2012-5-25 上午11:25:00 qianj Exp $
 */
public class CrmTemplateAction extends BaseAction {

    private static final long       serialVersionUID = -8527913127916645246L;

    private OpeventLoginLogService  opeventLoginLogService;
    private CrmTemplateService      crmTemplateService;
    private CrmTemplateFieldService crmTemplateFieldService;
    private CrmFieldCodeDataService crmFieldCodeDataService;
    private PageUtil<CrmTemplate>   crmTemplatList;
    private CrmTemplate             template;

    public void setCrmTemplateService(CrmTemplateService crmTemplateService) {
        this.crmTemplateService = crmTemplateService;
    }

    /**
    * 列表查询分页
    * @return
    */
    public String showCrmTemplatListPage() {
        try {
            //String calltypes=request.getParameter("calltypes");
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            //parameterMap.put("callType", calltypes);
            parameterMap.put("templateTypeId", 1);//查询客户业务模板
            this.crmTemplatList = crmTemplateService.getCrmTemplatePage(parameterMap, this
                .getPage());
            int count = this.getPage().getTotalRowsAmount();
            request.setAttribute("count", String.valueOf(count));
            return SUCCESS;
        } catch (Exception e) {
            log.error("showRecordInfoListPage action error:" + e.getMessage());
            return ERROR;
        }
    }

    /**
     * 删除模板
     * @return
     */
    public void delCrmTemplat() {
        PrintWriter out = null;
        try {
            out = this.getResponse().getWriter();
            String result = "";
            int id = Integer.parseInt(request.getParameter("tempid"));
            List<CrmTemplateField> fieldList = crmTemplateFieldService.getCrmTemplateFieldListByTemplateId(id);
            Map<String, Object> map = new HashMap<String, Object>();
            if(fieldList.size()>0){
	            for(int i = 0;i<fieldList.size();i++){
	            	map.put("queryColumn",fieldList.get(i).getExtFieldName());
	            	if (crmTemplateFieldService.getCrmCustomerExtFieldIsNotNull(map).size() > 0) {
	                    result = "客户资料中已经使用过此业务模板，不能够删除！";
	                }else {
	                    crmTemplateService.deleteCrmTemplate(id);
	                    result = "SUCCESS";
	                }
	            	map.clear();
	            }
            }else{
            	crmTemplateService.deleteCrmTemplate(id);
                result = "SUCCESS";
            }
            out.write(result);
            opeventLoginLogService.addLog(7,"删除模板", 1, 1);
        } catch (Exception e) {
            opeventLoginLogService.addLog(7,"删除模板", 1, 0);
            out.write("未知错误，删除失败！");
        }
    }

    /**
     * 根据Id获取模板信息
     * @return
     */
    public String crmTemplatDetail() {
        if (StringUtils.isNotEmpty(request.getParameter("tempid"))) {
            int id = Integer.parseInt(request.getParameter("tempid"));
            setTemplate(crmTemplateService.getCrmTemplateById(id));
        } else {
            int id = Integer.parseInt(request.getParameter("template.templateId"));
            setTemplate(crmTemplateService.getCrmTemplateById(id));
        }

        return SUCCESS;
    }

    /**
     * 编辑的时候判断是否存在相同模板名称
     * @return
     */
    public void validateUpdate() {
        PrintWriter out = null;
        try {
            out = this.getResponse().getWriter();
            if (!request.getParameter("oldName").equals(template.getTemplateName())) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("tempName", template.getTemplateName());
                map.put("templateTypeId", 1);//查询客户业务模板
                int count = crmTemplateService.CheckCrmTemplateByName(map);
                if (count > 0) {
                    out.print("已经存在相同的模板名称");
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * 新增的时候判断是否存在相同模板名称
     * @return
     */
    public void validateAdd() {
        PrintWriter out = null;
        try {
            out = this.getResponse().getWriter();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("tempName", template.getTemplateName());
            map.put("templateTypeId", 1);//查询客户业务模板
            int count = crmTemplateService.CheckCrmTemplateByName(map);
            if (count > 0) {
                out.print("已经存在相同的模板名称");
            }

        } catch (Exception e) {
        }
    }

    /**
     * 编辑模板信息
     * @return
     */
    public String editCrmTemplat() {
        try {
            int id = template.getTemplateId();
            CrmTemplate temp = crmTemplateService.getCrmTemplateById(id);
            template.setUpdateUser(this.getLoginInfo().getUserId());
            template.setSortno(temp.getSortno());
            template.setIsBasic(temp.getIsBasic());
            crmTemplateService.updateCrmTemplate(template);
            opeventLoginLogService.addLog(7,"修改模板", 1, 1);
            return SUCCESS;
        } catch (Exception e) {
            opeventLoginLogService.addLog(7,"修改模板", 1, 0);
            return ERROR;
        }

    }

    /**
     * 移动模板信息
     * @return
     */
    public String moveCrmTemplat() {
        try {
            int id = Integer.parseInt(this.request.getParameter("id"));
            String moveType = this.request.getParameter("moveType");
            String str = crmTemplateService.moveCrmTemplate(id, moveType);
            PrintWriter out = this.getResponse().getWriter();
            out.write(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 新增模板信息
     * @return
     */
    public String addCrmTemplat() {
        try {
            template.setUpdateUser(this.getLoginInfo().getUserId());
            template.setCreateUser(this.getLoginInfo().getUserId());
            crmTemplateService.addCrmTemplate(template);
            opeventLoginLogService.addLog(7,"新增模板", 1, 1);
            return SUCCESS;
        } catch (Exception e) {
            opeventLoginLogService.addLog(7,"新增模板", 1, 0);
            //opeventLogService.addOpeventLog("业务模板", "新增模板", 0, "新增模板");
            return ERROR;
        }

    }

    /**
     * 新增保存并继续
     * @return
     */
    public String addCrmTemplateAndContinue() {
        return addCrmTemplat();
    }

    /**
     * 编辑保存并继续
     * @return
     */
    public String editCrmTemplateAndContinue() {
        return editCrmTemplat();
    }

    /**
     * 跳转新增模板信息
     * @return
     */
    public String jumpCrmTemplat() {
        return SUCCESS;
    }

    public PageUtil<CrmTemplate> getCrmTemplatList() {
        return crmTemplatList;
    }

    public void setCrmTemplatList(PageUtil<CrmTemplate> pageUtil) {
        this.crmTemplatList = pageUtil;
    }

    public CrmTemplate getTemplate() {
        return template;
    }

    public void setTemplate(CrmTemplate template) {
        this.template = template;
    }

    public void setOpeventLoginLogService(OpeventLoginLogService opeventLoginLogService) {
        this.opeventLoginLogService = opeventLoginLogService;
    }

    public CrmTemplateFieldService getCrmTemplateFieldService() {
        return crmTemplateFieldService;
    }

    public void setCrmTemplateFieldService(CrmTemplateFieldService crmTemplateFieldService) {
        this.crmTemplateFieldService = crmTemplateFieldService;
    }

    public CrmFieldCodeDataService getCrmFieldCodeDataService() {
        return crmFieldCodeDataService;
    }

    public void setCrmFieldCodeDataService(CrmFieldCodeDataService crmFieldCodeDataService) {
        this.crmFieldCodeDataService = crmFieldCodeDataService;
    }

}
