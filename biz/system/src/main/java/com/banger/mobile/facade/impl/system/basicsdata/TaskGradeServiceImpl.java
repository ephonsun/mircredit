package com.banger.mobile.facade.impl.system.basicsdata;

import java.util.List;

import banger.collection.DataTable;

import com.banger.mobile.dao.system.TaskGradeDao;
import com.banger.mobile.domain.model.system.TaskGrade;
import com.banger.mobile.facade.system.TaskGradeService;

public class TaskGradeServiceImpl implements TaskGradeService{
    private TaskGradeDao taskGradeDao;
  
    public TaskGradeDao getTaskGradeDao() {
        return taskGradeDao;
    }

    public void setTaskGradeDao(TaskGradeDao taskGradeDao) {
        this.taskGradeDao = taskGradeDao;
    }

    
    /**
     * 新增任务等级
     * @param taskGrade
     * @see com.banger.mobile.facade.system.TaskGradeService#addTaskGrade(com.banger.mobile.domain.model.system.TaskGrade)
     */
    public void addTaskGrade(TaskGrade taskGrade) {
        taskGradeDao.AddTaskGradee(taskGrade);
    }
    /**
     * 删除任务等级
     * @param taskGradeId
     * @see com.banger.mobile.facade.system.TaskGradeService#deleteTaskGrade(int)
     */
    public void deleteTaskGrade(int taskGradeId) {
        taskGradeDao.deleteTaskGrade(taskGradeId);
    }
    /**
     * 根据id查询任务等级
     * @param taskGradeId
     * @return
     * @see com.banger.mobile.facade.system.TaskGradeService#getTaskGradeById(int)
     */
    public TaskGrade getTaskGradeById(int taskGradeId) {
        return taskGradeDao.getTaskGradeById(taskGradeId);
    }
    /**
     * 获取同名任务等级数量
     * @param taskGrade
     * @return
     * @see com.banger.mobile.facade.system.TaskGradeService#getTaskGradeBySameName(com.banger.mobile.domain.model.system.TaskGrade)
     */
    public List<TaskGrade> getTaskGradeBySameName(TaskGrade taskGrade) {
        return taskGradeDao.getSameTaskGradeByName(taskGrade);
    }
    /**
     * 任务等级列表
     * @return
     * @see com.banger.mobile.facade.system.TaskGradeService#getTaskGradeList()
     */
    public List<TaskGrade> getTaskGradeList() {
        return taskGradeDao.getAllTaskGrade();
    }

    /**
     * 编辑任务等级
     * @param taskGrade
     * @see com.banger.mobile.facade.system.TaskGradeService#updateTaskGrade(com.banger.mobile.domain.model.system.TaskGrade)
     */
    public void updateTaskGrade(TaskGrade taskGrade) {
        taskGradeDao.updateTaskGrade(taskGrade);
    }

   /**
    * 下移
    * @param taskGrade
    * @see com.banger.mobile.facade.system.TaskGradeService#downTaskGrade(com.banger.mobile.domain.model.system.TaskGrade)
    */
    public void downTaskGrade(TaskGrade taskGrade) {
        taskGradeDao.downTaskGrade(taskGrade);
    }

  /**
   * 上移
   * @param taskGrade
   * @see com.banger.mobile.facade.system.TaskGradeService#upTaskGrade(com.banger.mobile.domain.model.system.TaskGrade)
   */
    public void upTaskGrade(TaskGrade taskGrade) {
        taskGradeDao.upTaskGrade(taskGrade);
    }

    /**
     * 获取所有未删除且启用的任务等级
     * @return
     */
	public List<TaskGrade> getUnDelActiveTaskGrade() {
		return taskGradeDao.getUnDelActiveTaskGrade();
	}

}
