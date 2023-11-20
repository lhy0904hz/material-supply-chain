package com.material.chain.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@MapperScan("com.material.chain.user.mapper")
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class ChainUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChainUserApplication.class, args);
    }

}
