package com.material.chain.business.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RocketMQMessageListener(topic = "PURCHASE_ORDER_TOPIC", consumerGroup = "chain-purchase-order-consumer-group")
public class PurchaseOrderConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String s) {
        log.info("消费者的消息：{}", s);
    }
}
