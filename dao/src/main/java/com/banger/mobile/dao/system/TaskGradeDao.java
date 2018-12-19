package com.banger.mobile.dao.system;

import java.util.List;

import banger.collection.DataTable;

import com.banger.mobile.domain.model.system.TaskGrade;

public interface TaskGradeDao {
    /**
     * 添加一种任务等级
     * @param crmCustomerType
     */
    public void AddTaskGradee(TaskGrade taskGrade);
    
    /**
     * 修改任务等级
     * @param crmCustomerType
     */
    public void updateTaskGrade(TaskGrade taskGrade);
    
    /**
     * 删除一种任务等级
     * @param id
     */
    public void deleteTaskGrade(int taskGradeId);
    
    /**
     * 根据Id获取任务等级
     * @return
     */
    public TaskGrade getTaskGradeById(int taskGradeId);
    
    /**
     * 获取所有未删除的任务等级
     * @return
     */
    public List<TaskGrade> getAllTaskGrade();
    
    /**
     * 获取所有未删除且启用的任务等级
     * @return
     */
    public List<TaskGrade> getUnDelActiveTaskGrade();
    
    /**
     * 根据任务等级名称获取拥有相同任务等级名称的数据
     * @param crmCustomerType
     * @return
     */
    public List<TaskGrade> getSameTaskGradeByName(TaskGrade taskGrade);
    /**
     * 上移
     * @param map
     */
    public void upTaskGrade(TaskGrade taskGrade);
    /**
     * 下移
     */
    public void downTaskGrade(TaskGrade taskGrade);

}
