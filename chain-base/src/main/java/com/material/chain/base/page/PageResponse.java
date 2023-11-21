package com.material.chain.base.page;

import java.util.List;

public class PageResponse<T> {
    private Integer total;
    private Boolean hasPre;

    private Boolean hasNext;

    private Integer pageNo;

    private Integer pageSize;

    private Integer size;
    private static final long serialVersionUID = 1L;
    protected List<T> records;

    public PageResponse() {
    }

    public PageResponse(PageList page) {
        this.total = page.getTotal();
        this.pageNo = page.getPageNo();
        this.size = page.getSize();
        this.pageSize = page.getPageSize();
        this.hasPre = page.getHasPre();
        this.hasNext = page.getHasNext();
        this.records = page;
    }


    public Boolean getHasPre() {
        return hasPre;
    }

    public void setHasPre(Boolean hasPre) {
        this.hasPre = hasPre;
    }

    public Boolean getHasNext() {
        return hasNext;
    }

    public void setHasNext(Boolean hasNext) {
        this.hasNext = hasNext;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
