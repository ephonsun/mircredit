package com.banger.mobile.facade.impl.finance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.finance.FeAllColumnDao;
import com.banger.mobile.domain.model.finance.FeColumn;
import com.banger.mobile.facade.finance.FeColumnService;

public class FeColumnServiceImpl implements FeColumnService{
    private FeAllColumnDao feAllColumnDao;
    
    public FeAllColumnDao getFeAllColumnDao() {
        return feAllColumnDao;
    }
    public void setFeAllColumnDao(FeAllColumnDao feAllColumnDao) {
        this.feAllColumnDao = feAllColumnDao;
    }
    
//  添加栏目
    public void addColumn(FeColumn feColumn) {
        
        feAllColumnDao.addColumn(feColumn);
    }
//  获取所有栏目（搜索）
    public List<FeColumn> getAllColumnList(Map<String,Object> map) {
       List<FeColumn> list = new ArrayList<FeColumn>();
       list = feAllColumnDao.getAllColumn(map);
       return list;
    }
    
//   获取所有栏目(不带参数的)
    public List<FeColumn> getAllColumnList() {
        List<FeColumn> list = new ArrayList<FeColumn>();
        Map map = new HashMap();
        map.put("isStart", 1);
        
        list = feAllColumnDao.getAllColumn(map);
        return list;
    }
//  删除栏目
    public void delColumn(Integer columnId) {
        feAllColumnDao.delColumn(columnId);
    }
//   下移栏目
    public void downColumn(Integer columnId) {
        feAllColumnDao.downColumn(columnId);
    }
//    编辑栏目
    public void editColumn(FeColumn feColumn) {
        feAllColumnDao.editColumn(feColumn);
    }
//    启用栏目
    public void startColumn(Integer columnId) {
        feAllColumnDao.startColumn(columnId);
    }
//    停用栏目
    public void stopColumn(Integer columnId) {
        feAllColumnDao.stopColumn(columnId);
    }
//    上移栏目
    public void upColumn(Integer columnId) {
        feAllColumnDao.upColumn(columnId);
    }
//    获取具体栏目
    public FeColumn getColumn(FeColumn fe) {
        return feAllColumnDao.getColumn(fe);
    }

}
