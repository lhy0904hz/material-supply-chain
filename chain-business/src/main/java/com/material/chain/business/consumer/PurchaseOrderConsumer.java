package com.material.chain.business.consumer;

import com.alibaba.fastjson.JSON;
import com.material.chain.business.domain.vo.PurchaseOrderVo;
import com.material.chain.common.enums.OrderEnum;
import com.material.chain.business.service.PurchaseService;
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
        log.info("消费者：{}", s);
        PurchaseOrderVo purchaseOrderVo = JSON.parseObject(s, PurchaseOrderVo.class);
        //模拟支付成功
        PurchaseService service = PurchaseService.getBean(purchaseOrderVo.getPurchaseType());
        service.updatePurchaseOrderStatus(purchaseOrderVo.getPurchaseId(), OrderEnum.BE_SHIPPED.getCode());
        log.info("消费成功");
    }
}
