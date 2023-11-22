package com.material.chain.business;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.material.chain.business.mapper")
@SpringBootApplication
public class ChainBusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChainBusinessApplication.class, args);
    }

}
