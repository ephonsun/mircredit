package com.banger.mobile.domain.collection;

/**
 * 数据行对像
 */
public class DataRow implements Comparable<DataRow> {
	protected int index;
	protected DataTable table;

    protected DataRow() {
	}

	/**
     * 得到行索引
     * @return
     */
    public int getIndex()
    {
    	return this.index;
    }

    /**
     * 得到数据表对像
     * @return
     */
    public DataTable getTable()
    {
        return this.table;
    }

    /**
     * 设置数据表对像
     * @param table
     */
    public void setTable(DataTable table)
    {
        this.table = table;
    }

    /**
     * 通过列索引得到该列的值
     * @param columnIndex
     * @return
     */
    public 	Object get(int columnIndex)
    {
        DataColumn col = this.getTable().getColumn(columnIndex);
        if (col != null)
        {
            return col.get(this.getIndex());
        }
        return null;
    }

    /**
     * 通过列名得到该列的值
     * @param columnName
     * @return
     */
    public Object get(String columnName)
    {
    	DataColumn col = this.getTable().getColumn(columnName);
        if (col != null)
        {
            return col.get(this.getIndex());
        }
        return null;
    }
    
    public boolean hasValue(String columnName)
    {
    	DataColumn col = this.getTable().getColumn(columnName);
        if (col != null)
        {
            return col.get(this.getIndex())!=null;
        }
        return false;
    }
    
    /**
     * 通过列名设置该列的值
     * @param columnName
     * @param value
     */
    public void set(String columnName,Object value)
    {
    	if(!this.getTable().contains(columnName))this.getTable().addColumn(columnName);
        DataColumn col = this.getTable().getColumn(columnName);
        if (col != null)
        {
            col.set(this.getIndex(),value);
        }
    }
    
    /**
     * 通过列索引设置该列的值
     * @param columnIndex
     * @param value
     */
    public void set(int columnIndex,Object value)
    {
    	DataColumn col = this.getTable().getColumn(columnIndex);
        if (col != null)
        {
            col.set(this.getIndex(),value);
        }
    }

    public DataRow(int rowIndex)
    {
        this.index = rowIndex;
    }

    /**
     * 通过行索引比较行的先后顺序
     */
	public int compareTo(DataRow row) {
		if(this.getIndex()>row.getIndex())
		{
			return -1;
		}
		else if(this.getIndex()==row.getIndex())
		{
			return 0;
		}
		else return 1;
	}
}
