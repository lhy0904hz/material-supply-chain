package com.material.chain.base.configuration;

import com.material.chain.base.interceptor.PagingInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
public class CustomWebMvcConfigurer implements WebMvcConfigurer, ApplicationContextAware {

    public static List<String> SWAGGER_URL= new ArrayList<String>(){{
        add("/swagger-resources/**");
        add("/webjars/**");
        add("/v2/**");
        add("/swagger-ui.html/**");
        add("/csrf/**");
        add("/error");
        add("/doc.html");
        add("/favicon.ico");
    }};

    @Bean
    public PagingInterceptor pagingInterceptor() {
        return new PagingInterceptor();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(pagingInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(SWAGGER_URL);
    }
}
