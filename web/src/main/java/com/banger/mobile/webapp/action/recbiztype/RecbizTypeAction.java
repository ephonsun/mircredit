/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :业务类型Action
 * Author     :liyb
 * Create Date:2012-5-17
 */
package com.banger.mobile.webapp.action.recbiztype;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.axis.utils.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.Enum.recbistype.EnumRecbizType;
import com.banger.mobile.domain.model.recbistype.RecbizType;
import com.banger.mobile.facade.log.OpeventLogService;
import com.banger.mobile.facade.recbiztype.RecbizTypeService;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author liyb
 * @version $Id: RecbizTypeAction.java,v 0.1 2012-5-17 下午05:26:19 liyb Exp $
 */
public class RecbizTypeAction extends BaseAction {

    private static final long    serialVersionUID = 4939677150539589077L;

    private RecbizTypeService    recbizTypeService;                       //业务类型service
    private List<RecbizType>     recbiztypeList;                          //业务类型List
    private RecbizType           recbizType;                              //业务类型实体
    private Map<Integer,String> activedMap=new LinkedHashMap<Integer,String>();//按照插入的顺序排列
    private String typeCode;//业务编号
    private String moveFlag;//上移、下移标识
    private OpeventLogService opeventLogService;//操作日志service
    
    /**
     * 初始化业务类型状态
     */
    public void initActived(){
        activedMap.put(1, EnumRecbizType.ACTIVED_ENABLED.getValue());
        activedMap.put(0, EnumRecbizType.ACTIVED_DISABLED.getValue());
    }
    
    /**
     * 业务类型列表
     * @return
     */
    public String showRecbizTypePage() {
        try {
            recbiztypeList = recbizTypeService.getRecbizTypeList();
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("showRecbizTypePage action error:" + e.getMessage());
            return ERROR;
        }
    }
    
    /**
     * 启用/停用
     * @return
     */
    public String checkActived(){
        try {
            recbizTypeService.updateActived(recbizType);
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("checkActived action error:" + e.getMessage());
            return ERROR;
        }
    }
    
    /**
     * 判断业务类型是否在使用
     */
    public void isByTypeUse(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            int count=recbizTypeService.isTypeUse(recbizType.getBizTypeId());
            out.print(count);
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
    
    /**
     * 排序
     */
    public void sort(){
      //在从新排列顺序
        List<RecbizType> list=recbizTypeService.getRecbizTypeList();
        if(list.size()>0){
            for (int i = 0; i < list.size(); i++) {
                RecbizType rec=list.get(i);
                rec.setSortno(i+1);
                recbizTypeService.updateRecbiztype(rec);
            }
        }
    }
    
    /**
     * 单个删除业务类型(伪删除)
     * @return
     */
    public String delRecbizTypeById(){
        try {
            recbizTypeService.deleteRecbizTypeById(recbizType.getBizTypeId());
            sort();
            return SUCCESS;
        } catch (Exception e) {
            log.error("delRecbizTypeById action error:" + e.getMessage());
            return ERROR;
        }
    }

    /**
     * 跳转新建业务类型页面
     * @return
     */
    public String toAddPage(){
        initActived();
        return SUCCESS;
    }
    
    /**
     * 业务名称是否存在
     */
    public void valationType(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            RecbizType type=new RecbizType();
            type.setBizTypeName(recbizType.getBizTypeName());
            int nameCount=recbizTypeService.validation("validateTypeCount", type);
            if(nameCount>0){
                out.print(EnumRecbizType.VALIDATIONMSG_NAME.getValue());
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
    
    /**
     * 添加业务类型
     * @return
     */
    public String addRecbizType(){
        try {
            recbizType.setCreateUser(this.getLoginInfo().getUserId());
            recbizType.setBizTypeName(recbizType.getBizTypeName().trim());
            recbizTypeService.insertRecbiztype(recbizType);
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("addRecbizType action error:" + e.getMessage());
            return ERROR;
        }
    }
    
    /**
     * 跳转编辑页面
     * @return
     */
    public String toUpdatePage(){
        recbizType=recbizTypeService.getRecbizTypeById(recbizType);
        return SUCCESS;
    }
    
    /**
     * 编辑验证业务名称是否存在
     */
    public void updateValidate(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            String oldName=request.getParameter("OldBizTypeName");
            if (StringUtils.isEmpty(recbizType.getBizTypeName())) {
                out.print(EnumRecbizType.VALIDATIONMSG_EMPTY.getValue());
            } else if(!oldName.equals(recbizType.getBizTypeName())){
                int nameCount=recbizTypeService.validation("validateTypeCount", recbizType);
                if(nameCount>0){
                    out.print(EnumRecbizType.VALIDATIONMSG_NAME.getValue());
                }
            }
        }catch (Exception e) {}
    }
    
    /**
     * 编辑业务类型
     * @return
     */
    public String updateRecbizType(){
        try {
            recbizType.setUpdateUser(this.getLoginInfo().getUserId());
            recbizType.setBizTypeName(recbizType.getBizTypeName().trim());
            recbizTypeService.updateRecbiztype(recbizType);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("updateRecbizType action error:"+e.getMessage());
            return ERROR;
        }
    }
    
    /**
     * 上移、下移操作
     * @return
     */
    public void upOrDown(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            //得到总数
            int count=recbizTypeService.validation("validateTypeCount", null);
            //根据编号返回业务类型
            recbizType=recbizTypeService.getRecbizTypeById(recbizType);
            if (moveFlag.equals("up")) {//上移
              if(recbizType.getSortno()==1){
                  out.print(EnumRecbizType.SORT_FIRST.getValue());
              }else{
                  Integer currentNo=recbizType.getSortno()-1;//上一个序号(当前的序号减1)
                  RecbizType rType=new RecbizType();
                  rType.setSortno(currentNo);
                  //查询上一个序号的类型
                  rType=recbizTypeService.getRecbizTypeById(rType);
                  //先把上一个序号加1
                  rType.setSortno(rType.getSortno()+1);
                  recbizTypeService.updateRecbiztype(rType);
                  //修改当前的序号上移-1
                  recbizType.setSortno(currentNo);
                  recbizTypeService.updateRecbiztype(recbizType);
                  out.print(EnumRecbizType.SORT_TRUE.getValue());
              }
            }else if(moveFlag.equals("down")){//下移
                if(recbizType.getSortno()==count){
                    out.print(EnumRecbizType.SORT_LAST.getValue());
                }else{
                    Integer currentNo=recbizType.getSortno()+1;//下一个序号(当前的序号加1)
                    RecbizType rType=new RecbizType();
                    rType.setSortno(currentNo);
                    //查询下一个序号的类型
                    rType=recbizTypeService.getRecbizTypeById(rType);
                    //先把下一个序号减1
                    rType.setSortno(rType.getSortno()-1);
                    recbizTypeService.updateRecbiztype(rType);
                    //修改当前的序号下移+1
                    recbizType.setSortno(currentNo);
                    recbizTypeService.updateRecbiztype(recbizType);
                    out.print(EnumRecbizType.SORT_TRUE.getValue());
                }
            }
        } catch (Exception e) {}
    }

    public RecbizTypeService getRecbizTypeService() {
        return recbizTypeService;
    }

    public void setRecbizTypeService(RecbizTypeService recbizTypeService) {
        this.recbizTypeService = recbizTypeService;
    }
    
    public List<RecbizType> getRecbiztypeList() {
        return recbiztypeList;
    }

    public void setRecbiztypeList(List<RecbizType> recbiztypeList) {
        this.recbiztypeList = recbiztypeList;
    }

    public RecbizType getRecbizType() {
        return recbizType;
    }

    public void setRecbizType(RecbizType recbizType) {
        this.recbizType = recbizType;
    }

    public Map<Integer, String> getActivedMap() {
        return activedMap;
    }

    public void setActivedMap(Map<Integer, String> activedMap) {
        this.activedMap = activedMap;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getMoveFlag() {
        return moveFlag;
    }

    public void setMoveFlag(String moveFlag) {
        this.moveFlag = moveFlag;
    }

    public OpeventLogService getOpeventLogService() {
        return opeventLogService;
    }

    public void setOpeventLogService(OpeventLogService opeventLogService) {
        this.opeventLogService = opeventLogService;
    }
}
