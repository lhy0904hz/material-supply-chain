package com.material.chain.logistics;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.material.chain.logistics")
@EnableFeignClients(basePackages = {"com.material.chain.logistics.client"})
public class LogisticsConfiguration {
}
