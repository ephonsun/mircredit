package com.banger.mobile.webapp.action.system;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import com.banger.mobile.domain.Enum.system.EnumSystem;
import com.banger.mobile.domain.model.system.CrmCustomerType;
import com.banger.mobile.domain.model.system.TaskGrade;
import com.banger.mobile.facade.system.TaskGradeService;
import com.banger.mobile.webapp.action.BaseAction;

public class TskTaskGradeAction extends BaseAction{

    private static final long serialVersionUID = -8621682579703028373L;
    
    private TaskGradeService taskGradeService;
    private List<TaskGrade> taskGradeList;
    private TaskGrade taskGrade;
    private String moveType;
    
    /**
     * 任务等级列表
     * @return
     */
    public String getTaskGradeForList(){
        try{
            taskGradeList = taskGradeService.getTaskGradeList();
            return SUCCESS;
        }catch(Exception e){
            log.error("getTaskGradeForList action error",e);
            return ERROR;
        }
    }
    
    /**
     * 删除任务等级(伪删除)
     * @return
     */
    public void deleteTaskGrade(){
        try {
            PrintWriter out = this.getResponse().getWriter();
            if(taskGrade!=null){
                taskGradeService.deleteTaskGrade(taskGrade.getMarketingGradeId());
                out.print("true");
            }
            //修改任务表中的关联数据，设置为默认的
            
        } catch (Exception e) {
            log.error("deleteCrmCustomerType action error:" + e.getMessage());
        }
    }

    /**
     * 添加任务等级
     */
    public void addTaskGrade(){
        
        try {
            PrintWriter out = this.getResponse().getWriter();
            if(taskGrade!=null){
            	taskGrade.setMarketingGradeName(taskGrade.getMarketingGradeName().trim());
                taskGradeList = taskGradeService.getTaskGradeBySameName(taskGrade);
                if(taskGradeList.size()>0){
                    out.print("false");
                }else if(taskGradeList.size()==0){
                    taskGrade.setIsDel(0);
                    taskGrade.setRemark("");
                    taskGrade.setIsActived(1);
                    taskGrade.setCreateUser(this.getLoginInfo().getUserId());
                    taskGrade.setUpdateUser(this.getLoginInfo().getUserId());
                    
                    taskGradeService.addTaskGrade(taskGrade);
                    out.print("true");
                }
            }
           
        } catch (Exception e) {
            log.error("addTaskGrade action error:" + e.getMessage());
        }
    }

    /**
     * 修改任务等级(包括启用/停用)
     */
    public void updateTaskGrade(){
        try {
            PrintWriter out = this.getResponse().getWriter();
            if(taskGrade!=null){
            	if(taskGrade.getMarketingGradeName()!=null){
            		taskGrade.setMarketingGradeName(taskGrade.getMarketingGradeName().trim());
            	}
                String handelActive = request.getParameter("handelActive");
                if(handelActive!=null){
                    taskGrade.setUpdateUser(this.getLoginInfo().getUserId());
                    taskGradeService.updateTaskGrade(taskGrade);
                    out.print("true");
                }else{
                    taskGradeList = taskGradeService.getTaskGradeBySameName(taskGrade);
                    if(taskGradeList.size()==0){
                        taskGrade.setUpdateUser(this.getLoginInfo().getUserId());
                        taskGradeService.updateTaskGrade(taskGrade);
                        out.print("true");
                    }
                }
            }
            
        } catch (Exception e) {
            log.error("updateTaskGrade action error:" + e.getMessage());
        }
    }
    
    /**
     * 跳转到修改任务等级页面
     * @return
     */
    public String gotoTaskGradePage(){
        try {
           String taskGradeId = request.getParameter("taskGradeId");
           if(taskGradeId!=null){
               taskGrade = taskGradeService.getTaskGradeById(Integer.parseInt(taskGradeId));
           }
           return SUCCESS;
        } catch (Exception e) {
            // TODO: handle exception
            log.error("updateTaskGradePage action error:"
                    + e.getMessage());
            return ERROR;
        }
    }
   
    /**
     * 上移和下移任务等级
     * 
     * @return
     */
    public void moveTaskGradeItems(){
        try {
            PrintWriter out = this.getResponse().getWriter();
            if(taskGrade!=null&&moveType!=null){
                taskGrade.setUpdateUser(this.getLoginInfo().getUserId());
                if(moveType.equals("up")){
                    taskGradeService.upTaskGrade(taskGrade);
                }else if(moveType.equals("down")){
                    taskGradeService.downTaskGrade(taskGrade);
                }
            }
            out.write("true");
        } catch (Exception e) {
            // TODO: handle exception
            log.error("moveTaskGradeItems action error:" + e.getMessage());
        }
    }


    public TaskGrade getTaskGrade() {
        return taskGrade;
    }

    public void setTaskGrade(TaskGrade taskGrade) {
        this.taskGrade = taskGrade;
    }

    public TaskGradeService getTaskGradeService() {
        return taskGradeService;
    }

    public void setTaskGradeService(TaskGradeService taskGradeService) {
        this.taskGradeService = taskGradeService;
    }

    public String getMoveType() {
        return moveType;
    }

    public void setMoveType(String moveType) {
        this.moveType = moveType;
    }

    public List<TaskGrade> getTaskGradeList() {
        return taskGradeList;
    }

    public void setTaskGradeList(List<TaskGrade> taskGradeList) {
        this.taskGradeList = taskGradeList;
    }

    
}
