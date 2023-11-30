package com.material.chain.business.statemachine.service.impl;

import com.material.chain.business.statemachine.service.ChainStateMachineService;
import com.material.chain.common.enums.OrderActionEnum;
import com.material.chain.common.enums.OrderEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;

@Slf4j
public class ChainStateMachineServiceImpl implements ChainStateMachineService {

    private final StateMachine<OrderEnum, OrderActionEnum> stateMachine;

    public ChainStateMachineServiceImpl(StateMachine<OrderEnum, OrderActionEnum> stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public void start() {
        stateMachine.start();;
    }

    @Override
    public boolean sendEvent(OrderActionEnum orderActionEnum, String messageKey, Object messageValue) {
        Message<OrderActionEnum> build = MessageBuilder
                .withPayload(orderActionEnum)
                .setHeader(messageKey, messageValue)
                .build();
        return stateMachine.sendEvent(build);
    }

    @Override
    public void stop() {
        stateMachine.stop();
    }
}
