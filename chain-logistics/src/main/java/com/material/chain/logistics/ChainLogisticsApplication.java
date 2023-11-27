package com.material.chain.logistics;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author hxl
 * @version 1.0.0
 * @description TODO
 * @date 2022/07/26 11:34
 */
@SpringBootApplication(exclude = {MultipartAutoConfiguration.class})
@EnableDiscoveryClient(autoRegister = true)
@MapperScan("com.material.chain.logistics.mapper")
public class ChainLogisticsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChainLogisticsApplication.class, args);
    }
}
