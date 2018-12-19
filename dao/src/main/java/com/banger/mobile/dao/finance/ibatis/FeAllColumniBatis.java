package com.banger.mobile.dao.finance.ibatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.finance.FeAllColumnDao;
import com.banger.mobile.domain.model.finance.FeColumn;
import com.banger.mobile.ibatis.GenericDaoiBatis;

public class FeAllColumniBatis extends GenericDaoiBatis implements FeAllColumnDao{
    
    public FeAllColumniBatis() {
        super(FeColumn.class);
    }

    public FeAllColumniBatis(Class persistentClass) {
		super(FeColumn.class);
		// TODO Auto-generated constructor stub
	}

	//  获取所有栏目
    public List<FeColumn> getAllColumn(Map map) {
        List<FeColumn> list = new ArrayList<FeColumn>();
        list = this.getSqlMapClientTemplate().queryForList("getAllColumn",map);
        
        return list;
    }

//   新建栏目
    public void addColumn(FeColumn fe) {
        this.getSqlMapClientTemplate().insert("insertColumn",fe);
    }
//  删除栏目
    public void delColumn(Integer columnId) {
        this.getSqlMapClientTemplate().update("delColumn", columnId);
    }

//  编辑栏目
    public void editColumn(FeColumn fe) {
        this.getSqlMapClientTemplate().update("editColumn", fe);
    }

//  启用栏目
    public void startColumn(Integer columnId) {
        this.getSqlMapClientTemplate().update("startColumn", columnId);
    }

//  停用栏目
    public void stopColumn(Integer columnId) {
        this.getSqlMapClientTemplate().update("stopColumn", columnId);
    }

//  上移栏目
    public void upColumn(Integer columnId) {
        FeColumn fe1 = new FeColumn();
        FeColumn fe2 = new FeColumn();
        
        fe1 = (FeColumn)this.getSqlMapClientTemplate().queryForObject("nowFeColumn", columnId);
        int nowOrder = fe1.getColumnOrder();
        
        
        fe2 = (FeColumn)this.getSqlMapClientTemplate().queryForObject("upFeColumn", nowOrder);
        int upOrder = fe2.getColumnOrder();
        
        fe1.setColumnOrder(upOrder);
        fe2.setColumnOrder(nowOrder);
        
        this.getSqlMapClientTemplate().update("upColumn1", fe1);
        this.getSqlMapClientTemplate().update("upColumn2", fe2);
    }
    
//  下移栏目
    public void downColumn(Integer columnId) {
       FeColumn fe1 = new FeColumn();
       FeColumn fe2 = new FeColumn();
       
       fe1 = (FeColumn)this.getSqlMapClientTemplate().queryForObject("nowFeColumnDown", columnId);
       int nowOrderDown = fe1.getColumnOrder();
       
       fe2 = (FeColumn)this.getSqlMapClientTemplate().queryForObject("nextFeColumnDown", nowOrderDown);
       int nextOrderDown = fe2.getColumnOrder();
       
       fe1.setColumnOrder(nextOrderDown);
       fe2.setColumnOrder(nowOrderDown);
       
       this.getSqlMapClientTemplate().update("downColumn1", fe1);
       this.getSqlMapClientTemplate().update("downColumn2", fe2);
       
    }

//    获取具体栏目
    public FeColumn getColumn(FeColumn fe) {
        return (FeColumn)this.getSqlMapClientTemplate().queryForObject("getColumn", fe);
    }

//  测试ibatis标签
    public FeColumn getTest(Map<String, Object> map) {
        return (FeColumn)this.getSqlMapClientTemplate().queryForObject("getTest", map);
    }


}
