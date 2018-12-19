/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :QianJie
 * Create Date:Mar 2, 2013
 */
package com.banger.mobile.webapp.action.microTask;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.microTask.TskMicroTaskExecute;
import com.banger.mobile.facade.microTask.TskMicroTaskExecuteService;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author QianJie
 * @version $Id: TskMicroTaskExecuteAction.java,v 0.1 Mar 2, 2013 12:00:58 PM QianJie Exp $
 */
public class TskMicroTaskExecuteAction extends BaseAction {

    private static final long serialVersionUID = 4573232349208986044L;

    /**
     * Service
     */
    private TskMicroTaskExecuteService tskMicroTaskExecuteService;
    
    /**
     * 参数
     */
    private TskMicroTaskExecute tskMicroTaskExecute;
    
    public TskMicroTaskExecuteService getTskMicroTaskExecuteService() {
        return tskMicroTaskExecuteService;
    }

    public void setTskMicroTaskExecuteService(TskMicroTaskExecuteService tskMicroTaskExecuteService) {
        this.tskMicroTaskExecuteService = tskMicroTaskExecuteService;
    }
    
    public TskMicroTaskExecute getTskMicroTaskExecute() {
        return tskMicroTaskExecute;
    }

    public void setTskMicroTaskExecute(TskMicroTaskExecute tskMicroTaskExecute) {
        this.tskMicroTaskExecute = tskMicroTaskExecute;
    }

    /**
     * 保存任务执行者分配数据
     */
    public void saveTskMicroTaskExecute(){
        PrintWriter out = null;
        try {
            out = this.getResponse().getWriter();
            String msg = "SUCCESS";
            Map<String, Object> conds = new HashMap<String, Object>();
            conds.put("taskId", tskMicroTaskExecute.getTaskId());
            conds.put("deptId", tskMicroTaskExecute.getDeptId());
            conds.put("userId", tskMicroTaskExecute.getUserId());
            
            List<TskMicroTaskExecute> list = tskMicroTaskExecuteService.getAllTskMicroTaskExecuteByConds(conds);
            if(list.size() > 0){
                list.get(0).setTargetDept(tskMicroTaskExecute.getTargetDept());
                list.get(0).setTargetDeptUnassign(tskMicroTaskExecute.getTargetDeptUnassign());
                list.get(0).setTargetUser(tskMicroTaskExecute.getTargetUser());
                tskMicroTaskExecuteService.editTskMicroTaskExecute(list.get(0));
            }else{
                tskMicroTaskExecuteService.addTskMicroTaskExecute(tskMicroTaskExecute);
            }
            //删除分配数据为零的数据
            conds.clear();
            conds.put("taskId", tskMicroTaskExecute.getTaskId());
            conds.put("unAssignData", "unAssignData");
            tskMicroTaskExecuteService.delTskMicroTaskExecuteByConds(conds);
            out.write(msg);
          } catch (Exception ex) {
              out.write("分配失败");
              log.error(ex);
          }
    }

}
