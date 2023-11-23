package com.material.chain.base.configuration;

import com.material.chain.base.holder.SpringContextHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContextAutoConfiguration {

    @Bean
    SpringContextHolder springContextHolder(){
        return new SpringContextHolder();
    }
}
