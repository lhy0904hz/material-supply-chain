package com.material.chain.business.statemachine;

import com.material.chain.business.domain.po.GlobalPurchaseOrderPo;
import com.material.chain.business.statemachine.service.ChainStateMachineService;
import com.material.chain.business.statemachine.service.impl.ChainStateMachineServiceImpl;
import com.material.chain.common.enums.OrderActionEnum;
import com.material.chain.common.enums.OrderEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;

/**
 * 状态机工厂
 */
@Slf4j
public class ChainStateMachineFactory {

    @Autowired
    private ChainStateMachineBuilder stateMachineBuilder;

    @Autowired
    private StateMachinePersister stateMachinePersister;

    /**
     * 状态机实例
     */
    public ChainStateMachineService getInstance() {
        StateMachine<OrderEnum, OrderActionEnum> stateMachine = stateMachineBuilder.build();
        return new ChainStateMachineServiceImpl(stateMachine);
    }

    /**
     * 状态机实例，指定状态
     */
    public ChainStateMachineService getInstance(OrderEnum orderEnum) {
        StateMachine<OrderEnum, OrderActionEnum> stateMachine = stateMachineBuilder.build();
        GlobalPurchaseOrderPo po = new GlobalPurchaseOrderPo();
        po.setOrderStatus(orderEnum.getCode());
        try {
            stateMachinePersister.restore(stateMachine, po);
        }catch (Exception e) {
            log.error("设置状态机状态失败",e);
        }
        return new ChainStateMachineServiceImpl(stateMachine);
    }
}
