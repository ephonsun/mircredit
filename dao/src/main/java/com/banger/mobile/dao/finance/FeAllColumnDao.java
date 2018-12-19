package com.banger.mobile.dao.finance;

import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.finance.FeColumn;

public interface FeAllColumnDao {
//    获取所有栏目
    public List<FeColumn> getAllColumn(Map map);
//    新建一条栏目
    public void addColumn(FeColumn fe);
//    删除一条栏目
    public void delColumn(Integer columnId);
//    停用栏目
    public void stopColumn(Integer columnId);
//    启用栏目
    public void startColumn(Integer columnId);
//    上移栏目
    public void upColumn(Integer columnId);
//    下移栏目
    public void downColumn(Integer columnId);
//    编辑栏目
    public void editColumn(FeColumn fe);
//    获取具体栏目
    public FeColumn getColumn(FeColumn fe);
//     测试ibatis标签
    public FeColumn getTest(Map<String,Object> map);
}
