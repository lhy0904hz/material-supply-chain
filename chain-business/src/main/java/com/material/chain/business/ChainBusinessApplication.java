package com.material.chain.business;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.material.chain.business.mapper")
@SpringBootApplication(scanBasePackages = {"com.material.chain.business", "com.material.chain.base.handler"})
public class ChainBusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChainBusinessApplication.class, args);
    }

}
