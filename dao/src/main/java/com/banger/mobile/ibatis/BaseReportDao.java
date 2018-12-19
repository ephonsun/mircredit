package com.banger.mobile.ibatis;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.banger.mobile.Page;
import com.banger.mobile.domain.collection.DataRow;
import com.banger.mobile.domain.collection.DataSet;
import com.banger.mobile.domain.collection.DataTable;

public class BaseReportDao extends SqlMapClientDaoSupport {
    protected final Log log = LogFactory.getLog(getClass());
    
	/**
	 * 返回一个
	 * @param sqlId
	 * @param parameter
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataTable queryData(String sqlId,Object parameter)
	{
		DataTable table = new DataTable();
		Map<String,Object> condition = this.formatMap(parameter);
		List list = getSqlMapClientTemplate().queryForList(sqlId,condition);
		if(list.size()>0)
		{
			Object obj = list.get(0);
			if(obj instanceof Map)
			{
				Map<String,Object> map = (Map<String,Object>)obj;
				for(String name : map.keySet())
				{
					table.addColumn(name);
				}
			}
		}
		for(Object obj : list)
		{
			DataRow row = table.newRow();
			if(obj instanceof Map)
			{
				Map<String,Object> map = (Map<String,Object>)obj;
				for(String name : map.keySet())
				{
					row.set(name,map.get(name));
				}
			}
		}
		return table;
	}
	
	/**
	 * 查询报表数据
	 * @param sqlId
	 * @param parameter
	 * @return 返回结果为两个table
	 * 第一个 为报表条件数据 表名 Codition
	 * 第二个 为报表数据 表名 Data
	 */
	@SuppressWarnings("unchecked")
	public DataSet queryReport(String sqlId,Object parameter)
	{
		DataSet ds = new DataSet("Report");
		Map<String,Object> map = this.formatMap(parameter);
		DataTable codition = new DataTable("Codition");
		for(String name : map.keySet())
		{
			codition.addColumn(name);
		}
		DataRow row = codition.newRow();
		for(String name : map.keySet())
		{
			row.set(name,map.get(name));
		}
		ds.addTable(codition);
		
		DataTable data = this.queryData(sqlId, parameter);
		data.setName("Data");
		
		ds.addTable(data);
		
		return ds;
	}
	
	@SuppressWarnings("rawtypes")
	private Map formatMap(Object parameter)
	{
		//将参数统一转化成Map
        Map m = new HashMap();
        //判断parameter是否是Map类型,如果不是,将它转化成Map
        if (!(parameter instanceof Map)) {
            try {
                m = BeanUtils.describe(parameter);
            } catch (IllegalAccessException e) {
                log.error("", e);
            } catch (InvocationTargetException e) {
                log.error("", e);
            } catch (NoSuchMethodException e) {
                log.error("", e);
            }
        } else {
            m = (Map) parameter;
        }
        return m;
	}
	
	/**
     * 分页查询
     * @param statementList 查询SQL名
     * @param statementCount 查询数量SQL名称,这个可以为null
     * @param parameter 条件,尽量使用Map,而不要使用Object类型
     * @param page 分页信息
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public List findQueryPage(String statementList, String statementCount, Object parameter,
                                 Page page) {
        //查询记录数量,如果需要的话
        if (StringUtils.isNotBlank(statementCount)) {
            Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject(statementCount,
                parameter);
            if (null == page) {
                page = new Page();
            }
            int currentPage = page.getCurrentPage();
            page.setTotalRowsAmount(count);
            page.setCurrentPage(currentPage);
        }
        //将参数统一转化成Map
        Map m = new HashMap();
        //判断parameter是否是Map类型,如果不是,将它转化成Map
        if (!(parameter instanceof Map)) {
            try {
                m = BeanUtils.describe(parameter);
            } catch (IllegalAccessException e) {
                log.error("", e);
            } catch (InvocationTargetException e) {
                log.error("", e);
            } catch (NoSuchMethodException e) {
                log.error("", e);
            }
        } else {
            m = (Map) parameter;
        }
        //  加入分页信息
        if (null != page) {
            //  将分页信息放在参数Map中传到SqlMap里,进行物理分页.
            m.put("startRow", page.getPageStartRow());
            m.put("endRow", page.getPageEndRow());
        }

        //  分页查询
        List list = this.getSqlMapClientTemplate().queryForList(statementList, m);

        return list;
    }
}
