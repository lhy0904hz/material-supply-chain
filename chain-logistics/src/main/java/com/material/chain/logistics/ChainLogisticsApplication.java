package com.material.chain.logistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.material.chain.logistics.client"})
public class ChainLogisticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChainLogisticsApplication.class, args);
	}

}
