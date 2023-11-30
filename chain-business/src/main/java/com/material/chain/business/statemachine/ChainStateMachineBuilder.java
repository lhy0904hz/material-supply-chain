package com.material.chain.business.statemachine;

import com.material.chain.base.exception.ApiException;
import com.material.chain.business.statemachine.action.*;
import com.material.chain.common.enums.OrderActionEnum;
import com.material.chain.common.enums.OrderEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.stereotype.Component;

import java.util.EnumSet;
import java.util.Set;

/**
 * 构建状态机
 */
@Slf4j
@Component
public class ChainStateMachineBuilder {

    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private CreateAction createAction;

    @Autowired
    private PayAction payAction;

    @Autowired
    private DeliveryAction deliveryAction;

    @Autowired
    private TransportAction transportAction;

    @Autowired
    private FinishAction finishAction;

    private static final String CHAIN_MACHINE_ID = "chain_machine_id";

    private static Set<OrderEnum> orderEnumSet = EnumSet.allOf(OrderEnum.class);

    /**
     * 构建状态机流程
     */
    public StateMachine<OrderEnum, OrderActionEnum> build() {
        StateMachineBuilder.Builder<OrderEnum, OrderActionEnum> builder = StateMachineBuilder.builder();
        try {
            builder.configureConfiguration()
                    .withConfiguration()
                    .beanFactory(beanFactory)
                    .autoStartup(true)
                    .machineId(CHAIN_MACHINE_ID);

            //初始状态
            builder.configureStates()
                    .withStates()
                    .initial(OrderEnum.INIT)
                    .states(orderEnumSet);

            //业务流程状态流转
            builder.configureTransitions()
                    .withExternal()
                    .source(OrderEnum.INIT)
                    .target(OrderEnum.ORDER_CREATE)
                    .event(OrderActionEnum.CREATE)
                    .action(createAction)
                    .and()
                    .withExternal()
                    .source(OrderEnum.ORDER_CREATE)
                    .target(OrderEnum.BE_SHIPPED)
                    .event(OrderActionEnum.PAY)
                    .action(payAction)
                    .and()
                    .withExternal()
                    .source(OrderEnum.BE_SHIPPED)
                    .target(OrderEnum.SHIPPED)
                    .event(OrderActionEnum.DELIVERY)
                    .action(deliveryAction)
                    .and()
                    .withExternal()
                    .source(OrderEnum.SHIPPED)
                    .target(OrderEnum.IN_TRANSIT)
                    .event(OrderActionEnum.TRANSPORT)
                    .action(transportAction)
                    .and()
                    .withExternal()
                    .source(OrderEnum.IN_TRANSIT)
                    .target(OrderEnum.FINISH)
                    .event(OrderActionEnum.FINISH)
                    .action(finishAction);
        }catch (Exception e) {
            log.error("状态机初始化失败", e);
            throw new ApiException("状态机初始化失败");
        }
        //配置状态机基本参数
        return builder.build();
    }
}
