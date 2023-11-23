package com.material.chain.business.controller;

import com.material.chain.business.domain.dto.PurchaseOrderDTO;
import com.material.chain.business.service.PurchaseService;
import com.material.chain.common.utils.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "采购单")
@RestController
@RequestMapping("v1/api/chain/business/purchase/")
public class PurchaseOrderController {

    @ApiOperation("创建采购单")
    @PostMapping(value = "createPurchaseOrder")
    public ApiResult<Boolean> createPurchaseOrder(@RequestBody PurchaseOrderDTO dto) {
        PurchaseService service = PurchaseService.getBean(dto.getPurchaseType());
        return ApiResult.success(service.createPurchaseOrder(dto));
    }
}
