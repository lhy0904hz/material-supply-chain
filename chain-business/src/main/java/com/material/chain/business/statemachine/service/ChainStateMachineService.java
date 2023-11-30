package com.material.chain.business.statemachine.service;

import com.material.chain.common.enums.OrderActionEnum;

public interface ChainStateMachineService {

    /**
     * 开启状态机
     */
    void start();

    /**
     * 发送事件
     */
    boolean sendEvent(OrderActionEnum orderActionEnum, String messageKey, Object messageValue);

    /**
     * 结束事件
     */
    void stop();
}
