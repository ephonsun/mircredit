package com.banger.mobile.facade.system;

import java.util.List;

import banger.collection.DataTable;

import com.banger.mobile.domain.model.system.TaskGrade;

public interface TaskGradeService {
    /**
     * 获取任务等级列表
     * @return
     */
    public List<TaskGrade> getTaskGradeList();
    
    /**
     * 根据Id删除
     * @param id
     */
    public void deleteTaskGrade(int taskGradeId);

    /**
     * 添加
     * @param 
     */
    public void addTaskGrade(TaskGrade taskGrade);

    /**
     * id查询
     * @return
     */
    public TaskGrade getTaskGradeById(int taskGradeId);

    /**
     * 修改
     * @param 
     */
    public void updateTaskGrade(TaskGrade taskGrade);

    /**
     * 获取相同名称的对象
     * @return
     */
    public List<TaskGrade> getTaskGradeBySameName(TaskGrade taskGrade);
    /**
     * 上移
     * @param taskGrade
     */
    public void upTaskGrade(TaskGrade taskGrade);
    /**
     * 下移
     * @param taskGrade
     */
    public void downTaskGrade(TaskGrade taskGrade);
    
    /**
     * 获取所有未删除且启用的任务等级
     * @return
     */
    public List<TaskGrade> getUnDelActiveTaskGrade();
    
}
