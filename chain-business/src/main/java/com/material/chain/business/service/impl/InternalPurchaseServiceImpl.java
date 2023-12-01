package com.material.chain.business.service.impl;

import com.material.chain.business.domain.dto.PurchaseLogisticsDTO;
import com.material.chain.business.domain.dto.PurchaseOrderDTO;
import com.material.chain.business.domain.dto.PurchasePageDTO;
import com.material.chain.business.domain.vo.PurchaseOrderVo;
import com.material.chain.business.service.PurchaseService;
import com.material.chain.common.doamin.vo.PageVo;
import com.material.chain.logistics.domain.vo.LogisticsProviderVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class InternalPurchaseServiceImpl implements PurchaseService {
    @Override
    public Boolean createPurchaseOrder(PurchaseOrderDTO dto) {
        return null;
    }

    @Override
    public void updatePurchaseOrderStatus(Long id, Integer orderStatus) {

    }

    @Override
    public PageVo<PurchaseOrderVo> pageList(PurchasePageDTO dto) {
        return null;
    }

    @Override
    public PurchaseOrderVo detail(Long purchaseOrderId) {
        return null;
    }

    @Override
    public Boolean chooseLogistics(PurchaseLogisticsDTO dto) {
        return null;
    }

    @Override
    public List<LogisticsProviderVo> getLogisticsProviderList() {
        return null;
    }

    @Override
    public void updateStatus(Long id, Integer orderStatus, Integer logisticsStatus) {

    }
}
