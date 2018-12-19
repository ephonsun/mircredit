package com.banger.mobile.dao.system.ibatis;

import java.util.List;
import java.util.Map;

import banger.collection.DataTable;

import com.banger.mobile.dao.system.CdCityDao;
import com.banger.mobile.dao.system.TaskGradeDao;
import com.banger.mobile.domain.model.system.CdCity;
import com.banger.mobile.domain.model.system.TaskGrade;
import com.banger.mobile.ibatis.GenericDaoiBatis;

public class TaskGradeDaoiBatis extends GenericDaoiBatis implements TaskGradeDao{

    public TaskGradeDaoiBatis() {
        super(TaskGrade.class);
    }
    /**
     * 新增任务等级
     * @param taskGrade
     * @see com.banger.mobile.dao.system.TaskGradeDao#AddTaskGradee(com.banger.mobile.domain.model.system.TaskGrade)
     */
    public void AddTaskGradee(TaskGrade taskGrade) {
        this.getSqlMapClientTemplate().insert("addTaskGrade", taskGrade);
    }
    /**
     * 删除任务等级
     * @param taskGradeId
     * @see com.banger.mobile.dao.system.TaskGradeDao#deleteTaskGrade(int)
     */
    public void deleteTaskGrade(int taskGradeId) {
        this.getSqlMapClientTemplate().update("deleteTaskGradeById", taskGradeId);
    }
    /**
     * 获取所有任务等级
     * @return
     * @see com.banger.mobile.dao.system.TaskGradeDao#getAllTaskGrade()
     */
    public List<TaskGrade> getAllTaskGrade() {
        return this.getSqlMapClientTemplate().queryForList("getAllTaskGrade");
    }

    /**
     * 获取同名任务等级数量
     * @param taskGrade
     * @return
     * @see com.banger.mobile.dao.system.TaskGradeDao#getSameTaskGradeByName(com.banger.mobile.domain.model.system.TaskGrade)
     */
    public List<TaskGrade> getSameTaskGradeByName(TaskGrade taskGrade) {
        return this.getSqlMapClientTemplate().queryForList("getSameTaskGradeByName", taskGrade);
    }
    /**
     * 获取具体任务等级信息
     * @param taskGradeId
     * @return
     * @see com.banger.mobile.dao.system.TaskGradeDao#getTaskGradeById(int)
     */
    public TaskGrade getTaskGradeById(int taskGradeId) {
        return (TaskGrade)this.getSqlMapClientTemplate().queryForObject("getTaskGradeById",taskGradeId);
    }
    /**
     * 编辑任务等级
     * @param taskGrade
     * @see com.banger.mobile.dao.system.TaskGradeDao#updateTaskGrade(com.banger.mobile.domain.model.system.TaskGrade)
     */
    public void updateTaskGrade(TaskGrade taskGrade) {
        this.getSqlMapClientTemplate().update("updateTaskGrade", taskGrade);
    }
    /**
     * 下移
     * @param taskGrade
     * @see com.banger.mobile.dao.system.TaskGradeDao#downTaskGrade(com.banger.mobile.domain.model.system.TaskGrade)
     */
    public void downTaskGrade(TaskGrade taskGrade) {
        TaskGrade downTaskGrade = (TaskGrade)this.getSqlMapClientTemplate().queryForObject("downTaskGrade", taskGrade);
        int temp = taskGrade.getSortNo();
        taskGrade.setSortNo(downTaskGrade.getSortNo());
        downTaskGrade.setSortNo(temp);
        
        this.getSqlMapClientTemplate().update("updateTaskGrade", taskGrade);
        this.getSqlMapClientTemplate().update("updateTaskGrade", downTaskGrade);
    }
    /**
     * 上移
     * @param taskGrade
     * @see com.banger.mobile.dao.system.TaskGradeDao#upTaskGrade(com.banger.mobile.domain.model.system.TaskGrade)
     */
    public void upTaskGrade(TaskGrade taskGrade) {
        TaskGrade upTaskGrade = (TaskGrade)this.getSqlMapClientTemplate().queryForObject("upTaskGrade", taskGrade);
        int temp = taskGrade.getSortNo();
        taskGrade.setSortNo(upTaskGrade.getSortNo());
        upTaskGrade.setSortNo(temp);
        
        this.getSqlMapClientTemplate().update("updateTaskGrade", taskGrade);
        this.getSqlMapClientTemplate().update("updateTaskGrade", upTaskGrade);
    }
	
    /**
     * 获取所有未删除且启用的任务等级
     * @return
     */
	public List<TaskGrade> getUnDelActiveTaskGrade() {
		return this.getSqlMapClientTemplate().queryForList("GetUnDelActiveTaskGrade");
	}
  
}
