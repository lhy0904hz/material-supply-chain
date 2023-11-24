package com.material.chain.business.service.impl;

import com.material.chain.business.domain.dto.PurchaseOrderDTO;
import com.material.chain.business.domain.dto.PurchasePageDTO;
import com.material.chain.business.domain.vo.PurchaseOrderVo;
import com.material.chain.business.service.PurchaseService;
import com.material.chain.common.doamin.vo.PageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
