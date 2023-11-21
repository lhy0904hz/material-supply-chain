package com.material.chain.base.configuration;

import com.material.chain.base.interceptor.PageInterceptor;
import com.material.chain.base.interceptor.PagingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

public class InterceptorAutoConfiguration {

    @Order(10)
    @Bean
    PageInterceptor pageInterceptor(){
        return new PageInterceptor();
    }

    @Order(20)
    @Bean
    public PagingInterceptor pagingInterceptor() {
        return new PagingInterceptor();
    }
}
