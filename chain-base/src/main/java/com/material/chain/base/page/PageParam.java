package com.material.chain.base.page;

import java.io.Serializable;

public class PageParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private Boolean openPage = false;
    /**
     * 每页数据
     */
    private Integer pageSize;

    /**
     * 目标页码
     */
    private Integer pageNo;
    private final Integer DEFAULT_PAGE_NO = 1;
    private final Integer DEFAULT_PAGE_SIZE = 10;

    public PageParam() {
        this.pageNo = DEFAULT_PAGE_NO;
        this.pageSize = DEFAULT_PAGE_SIZE;
    }

    /**
     * 添加构造方法，加入页码和数量，以及是否开启开关
     */
    public PageParam(Integer pageNo, Integer pageSize, Boolean openPage) {
        this(pageNo, pageSize);
        this.openPage = openPage;
    }

    public PageParam(Integer current, Integer size) {
        setPageNo(current);
        setPageSize(size);
    }

    public Boolean getOpenPage() {
        return openPage;
    }

    public void setOpenPage(Boolean openPage) {
        this.openPage = openPage;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageSize(Integer size) {
        this.pageSize = size < 1 ? DEFAULT_PAGE_SIZE : size;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo <= 0 ? DEFAULT_PAGE_NO : pageNo;
    }
}