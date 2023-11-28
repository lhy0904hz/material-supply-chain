package com.material.chain.base.page;

import java.util.List;
import java.util.function.Supplier;

public class PageUtil {
    public static <T> PageResponse<T> getPage(Supplier<List<T>> supplier) {
        ThreadPagingUtil.turnOn();
        return new PageResponse<>((PageList) supplier.get());
    }

    @Deprecated
    public static <T> PageResponse<T> getPage(PageParam pageParam, Supplier<List<T>> supplier) {
        ThreadPagingUtil.turnOn(pageParam);
        return new PageResponse<>((PageList) supplier.get());
    }
}
