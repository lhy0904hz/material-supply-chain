package com.material.chain.base.configuration;

import com.material.chain.base.interceptor.PageInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

public class InterceptorAutoConfiguration {

    @Order(10)
    @Bean
    PageInterceptor pageInterceptor(){
        return new PageInterceptor();
    }
}
