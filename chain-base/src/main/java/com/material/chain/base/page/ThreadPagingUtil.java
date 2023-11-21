package com.material.chain.base.page;

import com.alibaba.fastjson.JSON;

public class ThreadPagingUtil {

    private static ThreadLocal<PageParam> local = new InheritableThreadLocal<PageParam>();

    /**
     * 获取当前分页对象
     *
     * @return
     */
    public static PageParam get() {
        return local.get();
    }

    /**
     * 开启分页，线程内有效,后端没有继承PageParam,前端直接在?current=1&size=10形式传参
     */
    public static void turnOn() {
        PageParam pageParam = local.get();
        if (pageParam == null) {
            pageParam = new PageParam();
            local.set(pageParam);
        }
        pageParam.setOpenPage(true);
    }

    /**
     * 开启分页，线程内有效 请求参数继承PageParam的
     */
    public static void turnOn(PageParam pageParam) {
        local.set(pageParam);
        pageParam.setOpenPage(true);
    }

    /**
     * 设置分页参数
     *
     * @param pageParam
     */
    public static void set(PageParam pageParam) {
        local.set(pageParam);
    }

    /**
     * 设置分页参数
     */
    public static void set(String pageJson) {
        local.set(JSON.parseObject(pageJson, PageParam.class));
    }

    /**
     * 清除
     */
    public static void clear() {
        local.remove();
    }

    public static void buildPageParam(String pageNo, String pageSize) {
        if (!"".equals(pageNo) && null != pageNo) {
            PageParam pageParam = new PageParam();
            pageParam.setPageNo(Integer.valueOf(pageNo));
            if (!"".equals(pageSize) && null != pageSize) {
                pageParam.setPageSize(Integer.valueOf(pageSize));
            }
            set(pageParam);
        }
    }
}
