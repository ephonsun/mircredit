/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :业务模版action
 * Author     :yujh
 * Create Date:May 25, 2012
 */
package com.banger.mobile.webapp.action.pdtTemplate;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.banger.mobile.domain.model.template.CrmTemplate;
import com.banger.mobile.domain.model.templateField.CrmTemplateField;
import com.banger.mobile.facade.fieldCodeData.CrmFieldCodeDataService;
import com.banger.mobile.facade.log.OpeventLogService;
import com.banger.mobile.facade.template.CrmTemplateService;
import com.banger.mobile.facade.templateField.CrmTemplateFieldService;
import com.banger.mobile.webapp.action.BaseAction;
import com.banger.mobile.PageUtil;

/**
 * @author qianj
 * @version $Id: PdtCrmTemplateAction.java,v 0.1 May 25, 2012 11:48:45 AM qianj Exp $
 */
public class PdtCrmTemplateAction extends BaseAction {
    private static final long       serialVersionUID = -8527913127916645246L;

    private OpeventLogService       opeventLogService;

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
            parameterMap.put("templateTypeId", 2);//查询产品模版
            this.crmTemplatList = crmTemplateService.getCrmTemplatePage(parameterMap, this
                .getPage());
            int count = crmTemplatList.getItems().size();
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
        try {
            String result = "";
            int id = Integer.parseInt(request.getParameter("tempid"));
            if (crmTemplateFieldService.getCrmTemplateFieldListByTemplateId(id).size() > 0) {
                result = "产品资料中已经使用过此业务模板，不能够删除！";
            } else {
                crmTemplateService.deleteCrmTemplate(id);
                result = "SUCCESS";
            }
            PrintWriter out = this.getResponse().getWriter();
            out.write(result);
            opeventLogService.addOpeventLog("业务模板", "删除模板", 1, "删除模板");
        } catch (Exception e) {
            opeventLogService.addOpeventLog("业务模板", "删除模板", 0, "删除模板");
        }
    }

    /**
     * 根据Id获取模板信息
     * @return
     */
    public String crmTemplatDetail() {
        int id = Integer.parseInt(request.getParameter("tempid"));
        setTemplate(crmTemplateService.getCrmTemplateById(id));
        return SUCCESS;
    }

    /**
     * 编辑的时候判断是否存在相同模版名称
     * @return
     */
    public void validateUpdate() {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            if (!request.getParameter("oldName").equals(template.getTemplateName())) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("tempName", template.getTemplateName());
                map.put("templateTypeId", 2);//查询产品模版
                int count = crmTemplateService.CheckCrmTemplateByName(map);
                if (count > 0) {
                    out.print("已经存在相同的模板名称：" + template.getTemplateName());
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * 新增的时候判断是否存在相同模版名称
     * @return
     */
    public void validateAdd() {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("tempName", template.getTemplateName());
            map.put("templateTypeId", 2);//查询产品模版
            int count = crmTemplateService.CheckCrmTemplateByName(map);
            if (count > 0) {
                out.print("已经存在相同的模板名称：" + template.getTemplateName());
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
            opeventLogService.addOpeventLog("业务模板", "修改模板", 1, "修改模板");
            return SUCCESS;
        } catch (Exception e) {
            opeventLogService.addOpeventLog("业务模板", "修改模板", 0, "修改模板");
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
            opeventLogService.addOpeventLog("业务模板", "新增模板", 1, "新增模板");
            return SUCCESS;
        } catch (Exception e) {
            opeventLogService.addOpeventLog("业务模板", "新增模板", 0, "新增模板");
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

    public void setOpeventLogService(OpeventLogService opeventLogService) {
        this.opeventLogService = opeventLogService;
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
