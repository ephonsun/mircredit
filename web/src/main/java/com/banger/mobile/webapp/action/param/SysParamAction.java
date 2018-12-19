/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :全局参数action
 * Author     :yujh
 * Create Date:May 25, 2012
 */
package com.banger.mobile.webapp.action.param;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.banger.mobile.domain.Enum.recbistype.EnumRecbizType;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.param.SysParam;
import com.banger.mobile.domain.model.param.SysParamFlow;
import com.banger.mobile.domain.model.sysWebFlowControl.SysWebFlowControl;
import com.banger.mobile.domain.model.sysWebFlowControl.SysWebFlowControlInfo;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.log.OpeventLogService;
import com.banger.mobile.facade.param.SysParamService;
import com.banger.mobile.facade.sysWebFlowControl.SysWebFlowControlService;
import com.banger.mobile.util.FileUtil;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.util.SystemUtil;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author yujh
 * @version $Id: SysParamAction.java,v 0.1 May 25, 2012 11:48:45 AM Administrator Exp $
 */
public class SysParamAction extends BaseAction {

    private static final long serialVersionUID = 5625800988639925565L;
    private SysParamService             sysParamService;            //pad端流量控制service
    private Map<String,Object>          map;
    private SysParamFlow                sysParamFlow;               //pad流量控制实体
    private SysWebFlowControlService    sysWebFlowControlService;   //web端流量控制service
    private List<SysWebFlowControlInfo>       webFlowControlList;      //web端流量控制实体
    
    private String recordPath;
    private String recordCueSize;
    private String recordArchivePath;
    private String recordArchiveDays;
    private String recordPromptTone;
    private String uploadPromptTone;    //提示音上传
    private OpeventLogService opeventLogService;//操作日志service
    
    public void setOpeventLogService(OpeventLogService opeventLogService) {
        this.opeventLogService = opeventLogService;
    }

    /**
     * 查询流量参数
     * @return
     */
    public String showFlowControlPage() {
        //pad端
        map = this.sysParamService.getFlowControlParam();
        SysParamFlow  flow = new SysParamFlow();
        int isActive=Integer.parseInt(map.get("FLOW_CONTROL_ISACTIVE").toString());//pad端是否启用
        int maxBps=  Integer.parseInt(map.get("FLOW_CONTROL_MAXBPS").toString());//pad端流量大小
        int webIsActive=Integer.parseInt(map.get("WEB_FLOW_CONTROL_ISACTIVE").toString());//web端是否启用
        request.setAttribute("isActive", isActive);
        request.setAttribute("maxBps", maxBps);
        request.setAttribute("webIsActive", webIsActive);
        //web端
        webFlowControlList=this.sysWebFlowControlService.getAllFlowControl();
        
        return SUCCESS;
    }
    
    /**
     * 设置流量参数
     * @return
     */
    public String updateFlowParam() {
        //pad端
        int isActive = Integer.parseInt(request.getParameter("isActive"));
        int maxBPS = Integer.parseInt(request.getParameter("maxBps"));
        this.sysParamService.updateIsActive(isActive);
        this.sysParamService.updateMaxFlow(maxBPS);
        //web端
        int webIsActive=Integer.parseInt(request.getParameter("webIsActive"));
        this.sysParamService.updateIsActiveForWeb(webIsActive);//web端是否启用限流
        String deptIds[]=request.getParameterValues("deptId");
        String maxBPSs[]=request.getParameterValues("maxBPS");
        String isActiveds[]=request.getParameterValues("isActived");
        for(int i=0;i<deptIds.length;i++){
            SysWebFlowControl swfc= new SysWebFlowControl();
            swfc.setDeptId(Integer.parseInt(deptIds[i]));
            swfc.setMaxBps(Integer.parseInt(maxBPSs[i]));
            if(isActiveds==null){
                swfc.setIsActive(0);
            }
            swfc.setIsActive(Integer.parseInt(isActiveds[i]));
            this.sysWebFlowControlService.updateFlowControl(swfc);
        }

        return SUCCESS;
    }

    /**
     * 查询系统全局参数
     * @return
     */
    public String queryRecordSysParam(){
        try {
            map = this.sysParamService.querySysParam();
            recordPath = map.get("RECORD_PATH").toString();
            recordCueSize = map.get("RECORD_CUE_SIZE").toString();
            return SUCCESS;
        } catch (Exception e) {
            log.error("queryRecordSysParam action error:" + e.getMessage());
            return ERROR;
        }
    }
    
    /**
     * 修改录音设置参数
     */
    public String updateRecordSysParam(){
        try {
            map=new HashMap<String, Object>();
            recordPath = recordPath.replace("\\", "/");//路径符号转换
            map.put("paramValue", recordPath.trim());
            map.put("paramKey", "RECORD_PATH");
            this.sysParamService.updateSysParam(map);
            map.put("paramValue", recordCueSize);
            map.put("paramKey", "RECORD_CUE_SIZE");
            this.sysParamService.updateSysParam(map);
//            opeventLogService.addOpeventLog("录音设置","修改录音设置",1,"");
            return SUCCESS;
        } catch (Exception e) {
//            opeventLogService.addOpeventLog("录音设置","修改录音设置",0,"");
            log.error(e);
            return ERROR;
        }
    }
    
    /**
     * 验证路径是否存在
     * @return
     */
    public void isValidPath(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            String recordPath = request.getParameter("recordPath");
            PrintWriter out = response.getWriter();
            
            File f = new File(recordPath);
            if(SystemUtil.isWindows()){//是否windows系统
            	
            	if(StringUtil.isNotEmpty(recordPath)){
                    recordPath = recordPath.replace("/", "\\");
                }
            	
                if(recordPath.matches("[a-zA-Z]:(((\\\\(?! )[^\\\\/:*?<>|]+)+\\\\?)|(\\\\))\\s*$")){
                    if(f.mkdir()){
                        if(f.isDirectory()){
                            if(!f.exists()) out.print("系统盘符不存在,请重新输入!");
                        }else{
                            out.print("系统盘符不存在,请重新输入!");
                        }
                    }else{
                        if(f.isDirectory()){
                            if(!f.exists()) out.print("系统路径无效,请重新输入!");
                        }else{
                            out.print("系统盘符不存在,请重新输入!");
                        }
                    }
                }else{
                    out.print("系统路径无效,请重新输入!");
                }
            }else{//是否linux系统
                if(recordPath.matches("([\\\\/][\\w-]+)*$")){
                    if(f.mkdir()){
                        if(f.isDirectory()){
                            if(!f.exists()) out.print("系统盘符不存在,请重新输入!");
                        }else{
                            out.print("系统盘符不存在,请重新输入!");
                        }
                    }else{
                        if(f.isDirectory()){
                            if(!f.exists()) out.print("系统路径无效,请重新输入!");
                        }else{
                            out.print("系统盘符不存在,请重新输入!");
                        }
                    }
                }else{
                    out.print("系统路径无效,请重新输入!");
                }
            }
            out.close();
        } catch (Exception e) {
            log.error("SysParamAction isValidPath error:" + e.getMessage());
        }
    }
    
    
    public String getRecordPath() {
        return recordPath;
    }

    public void setRecordPath(String recordPath) {
        this.recordPath = recordPath;
    }

    public String getRecordCueSize() {
        return recordCueSize;
    }

    public void setRecordCueSize(String recordCueSize) {
        this.recordCueSize = recordCueSize;
    }

    public String getRecordArchivePath() {
        return recordArchivePath;
    }

    public void setRecordArchivePath(String recordArchivePath) {
        this.recordArchivePath = recordArchivePath;
    }

    public String getRecordArchiveDays() {
        return recordArchiveDays;
    }

    public void setRecordArchiveDays(String recordArchiveDays) {
        this.recordArchiveDays = recordArchiveDays;
    }

    public String getRecordPromptTone() {
        return recordPromptTone;
    }

    public void setRecordPromptTone(String recordPromptTone) {
        this.recordPromptTone = recordPromptTone;
    }

    public SysParamFlow getSysParamFlow() {
        return sysParamFlow;
    }

    public void setSysParamFlow(SysParamFlow sysParamFlow) {
        this.sysParamFlow = sysParamFlow;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public void setSysParamService(SysParamService sysParamService) {
        this.sysParamService = sysParamService;
    }

    public String getUploadPromptTone() {
        return uploadPromptTone;
    }

    public void setUploadPromptTone(String uploadPromptTone) {
        this.uploadPromptTone = uploadPromptTone;
    }

    public void setSysWebFlowControlService(SysWebFlowControlService sysWebFlowControlService) {
        this.sysWebFlowControlService = sysWebFlowControlService;
    }

    public List<SysWebFlowControlInfo> getWebFlowControlList() {
        return webFlowControlList;
    }

    public void setWebFlowControlList(List<SysWebFlowControlInfo> webFlowControlList) {
        this.webFlowControlList = webFlowControlList;
    }
    
    
}
