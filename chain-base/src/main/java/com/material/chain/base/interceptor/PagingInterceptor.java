package com.material.chain.base.interceptor;

import cn.hutool.core.util.StrUtil;
import com.material.chain.base.page.ThreadPagingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 分页处理拦截器
 */
public class PagingInterceptor extends HandlerInterceptorAdapter {

    private static Logger log = LoggerFactory.getLogger(PagingInterceptor.class);

    public static final String PAGE_NO = "pageNo";

    public static final String PAGE_SIZE = "pageSize";

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        ThreadPagingUtil.buildPageParam(request.getParameter(PAGE_NO),request.getParameter(PAGE_SIZE));
        return true;
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ThreadPagingUtil.clear();
    }
}
