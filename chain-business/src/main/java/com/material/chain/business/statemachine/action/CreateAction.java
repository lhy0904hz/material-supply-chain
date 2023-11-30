package com.material.chain.business.statemachine.action;

import com.material.chain.business.domain.po.GlobalPurchaseOrderPo;
import com.material.chain.business.statemachine.constant.HeaderMessageConstant;
import com.material.chain.common.enums.LogisticsStatusEnum;
import com.material.chain.common.enums.OrderActionEnum;
import com.material.chain.common.enums.OrderEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CreateAction implements Action<OrderEnum, OrderActionEnum> {



    @Override
    public void execute(StateContext<OrderEnum, OrderActionEnum> stateContext) {
    }
}
