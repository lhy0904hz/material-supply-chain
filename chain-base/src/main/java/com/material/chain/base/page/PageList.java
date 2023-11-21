package com.material.chain.base.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class PageList<T> extends ArrayList<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer total;

    private Boolean hasPre;

    private Boolean hasNext;

    private Integer pageNo;

    private Integer pageSize;

    private Integer size;

    public PageList() {
        super();
    }

    public PageList(int size) {
        super(size);
    }

    public PageList(Collection<? extends T> c) {
        super(c);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

}
