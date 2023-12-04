package com.material.chain.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class ChainGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChainGatewayApplication.class, args);
    }

}
