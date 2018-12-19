package com.banger.mobile.domain.model.pad;

import java.io.Serializable;
import java.util.List;

/**
 * Created by thinkpad on 2014/11/10.
 */
public class LibraryPage implements Serializable {
    private List<Library> dataList;
    private Integer pageCount;

    public List<Library> getDataList() {
        return dataList;
    }

    public void setDataList(List<Library> dataList) {
        this.dataList = dataList;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }
}
