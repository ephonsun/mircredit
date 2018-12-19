package com.banger.mobile.webapp.page;

import java.io.Serializable;

public class Page implements Serializable {
    private static final long serialVersionUID = 7346076082033400525L;

    public static final int   PAGESIZE         = 20;
    /**
     * 总记录数
     */
    private int               totalRowsAmount;
    /**
     * 记录数量是否已设置
     */
    private boolean           rowsAmountSet;
    /**
     * 每页多少条
     */
    private int               pageSize         = PAGESIZE;
    /**
     * 当前第几页
     */
    private int               currentPage      = 1;
    /**
     * 下一页的页码
     */
    private int               nextPage;
    /**
     * 上一页的页码
     */
    private int               previousPage;
    /**
     * 总页码数
     */
    private int               totalPages;
    /**
     * 是否有下一页
     */
    private boolean           hasNext;
    /**
     * 是否有上一页
     */
    private boolean           hasPrevious;

    /**
     * 本页开始的记录位置
     */
    private int               pageStartRow;
    /**
     * 本页结束的记录位置
     */
    private int               pageEndRow;

    /**
     * 构造函数,通过总记录数量构造Page对象
     * @param totalRows 总记录集的数据数量
     */
    public Page(int totalRows) {
        setTotalRowsAmount(totalRows);
    }

    public Page() {
    }

    /**
     * 设置记录集总数量,同时会计算一下Page里的其它属性值,如,根据每页记录数量计算总页码
     * @param totalRowsAmount   记录集总记录数量
     */
    public void setTotalRowsAmount(int i) {
        if (pageSize == 0)// 使用默认的每页记录数量
            pageSize = PAGESIZE;

        if (!this.rowsAmountSet) {// (TODO 是否有必要???) 如果总记录数量没有设置过,才可以设置记录集的数量
            totalRowsAmount = i;// 设置总记录数量
            totalPages = (totalRowsAmount % pageSize == 0) ? totalRowsAmount / pageSize
                : totalRowsAmount / pageSize + 1;// 设置总页码
            setCurrentPage(this.currentPage);// 设置当前所在页码,以及根据新的总记录数量重新计算页码相关的
            this.rowsAmountSet = true;// 标记已经设置过了记录集数量
        }

    }

    /**
     * 设置当前页码,需要判断是否超过总页码等.
     * @param currentPage   The currentPage to set.
     */
    public void setCurrentPage(int i) {
    	currentPage = (totalPages>0 && i>totalPages)?totalPages:i;
        nextPage = currentPage + 1;// 下一页码
        previousPage = currentPage - 1;// 上一页码
        if (currentPage * pageSize < totalRowsAmount) {
            pageEndRow = currentPage * pageSize;// 计算当前页码的结束位置
            pageStartRow = pageEndRow - pageSize + 1;// 计算当前页码起始的位置
        } else {
            pageEndRow = totalRowsAmount;// 计算当前页码的结束位置
            pageStartRow = pageSize * (totalPages - 1) + 1;// 计算当前页码起始的位置
            if (pageStartRow < 0) {// 修改了当totalRowsAmount =0,currentPage=1 的错误
                pageStartRow = 0;// 计算当前页码起始的位置
            }
        }
        if (nextPage > totalPages) {// 设置是否有下一页
            hasNext = false;
        } else {
            hasNext = true;
        }
        if (previousPage == 0) { // 设置是否有上一页
            hasPrevious = false;
        } else {
            hasPrevious = true;
        }
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    public int getNextPage() {
        return nextPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPreviousPage() {
        return previousPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalRowsAmount() {
        return totalRowsAmount;
    }

    public void setHasNext(boolean b) {
        hasNext = b;
    }

    public void setHasPrevious(boolean b) {
        hasPrevious = b;
    }

    public void setNextPage(int i) {
        nextPage = i;
    }

    public void setPageSize(int i) {
        pageSize = i;
    }

    public void setPreviousPage(int i) {
        previousPage = i;
    }

    public void setTotalPages(int i) {
        totalPages = i;
    }

    public int getPageEndRow() {
        return pageEndRow;
    }

    public int getPageStartRow() {
        return pageStartRow;
    }

    public void setPageStartRow(int pageStartRow) {
        this.pageStartRow = pageStartRow;
    }
        

    public void setPageEndRow(int pageEndRow) {
        this.pageEndRow = pageEndRow;
    }

    public String getDescription() {
        String description = "Total:" + this.getTotalRowsAmount() + " items "
                             + this.getTotalPages() + " pages,Current page:" + this.currentPage
                             + " Previous " + this.hasPrevious + " Next:" + this.hasNext
                             + " start row:" + this.pageStartRow + " end row:" + this.pageEndRow;
        return description;
    }

    public static void main(String args[]) {
        Page pc = new Page();

        pc.setPageSize(4);
        pc.setTotalRowsAmount(8);
        pc.setCurrentPage(1);
    }
}
