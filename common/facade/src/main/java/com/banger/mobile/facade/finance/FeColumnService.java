package com.banger.mobile.facade.finance;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.domain.model.finance.FeColumn;

public interface FeColumnService {
//    新建栏目
    public void addColumn(FeColumn fe);
//    获取所有栏目
    public List<FeColumn> getAllColumnList(Map<String,Object> map);
    
    public List<FeColumn> getAllColumnList();
//    删除栏目
    public void delColumn(Integer columnId);
//    编辑栏目
    public void editColumn(FeColumn feColumn);
//    上移栏目
    public void upColumn(Integer columnId);
//    下移栏目
    public void downColumn(Integer columnId);
//    启用栏目
    public void startColumn(Integer columnId);
//    停用栏目
    public void stopColumn(Integer columnId);
//    获取具体栏目
    public FeColumn getColumn(FeColumn fe);
}
