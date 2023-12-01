package com.material.chain.business.controller;

import com.material.chain.business.domain.dto.PurchaseLogisticsDTO;
import com.material.chain.business.domain.dto.PurchaseOrderDTO;
import com.material.chain.business.domain.dto.PurchasePageDTO;
import com.material.chain.business.domain.vo.PurchaseOrderVo;
import com.material.chain.business.service.PurchaseService;
import com.material.chain.common.doamin.vo.PageVo;
import com.material.chain.common.utils.ApiResult;
import com.material.chain.logistics.domain.vo.LogisticsProviderVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @ApiOperation("采购单列表")
    @PostMapping(value = "pageList")
    public ApiResult<PageVo<PurchaseOrderVo>> pageList(@RequestBody PurchasePageDTO dto) {
        PurchaseService service = PurchaseService.getBean(dto.getPurchaseType());
        return ApiResult.success(service.pageList(dto));
    }

    @ApiOperation("采购单详情")
    @GetMapping(value = "detail")
    public ApiResult<PurchaseOrderVo> detail(Long purchaseOrderId, String purchaseType) {
        PurchaseService service = PurchaseService.getBean(purchaseType);
        return ApiResult.success(service.detail(purchaseOrderId));
    }

    @ApiOperation("选择物流发货")
    @PostMapping(value = "chooseLogistics")
    public ApiResult<Boolean> chooseLogistics(@RequestBody PurchaseLogisticsDTO dto) {
        PurchaseService service = PurchaseService.getBean(dto.getPurchaseType());
        return ApiResult.success(service.chooseLogistics(dto));
    }

    @ApiOperation("获取物流商列表")
    @GetMapping(value = "getLogisticsProviderList")
    public ApiResult<List<LogisticsProviderVo>> getLogisticsProviderList(String purchaseType) {
        PurchaseService service = PurchaseService.getBean(purchaseType);
        return ApiResult.success(service.getLogisticsProviderList());
    }

    @ApiOperation("确认收货")
    @PostMapping(value = "takeDelivery")
    public ApiResult<Boolean> takeDelivery(@RequestBody PurchaseOrderDTO dto) {
        PurchaseService service = PurchaseService.getBean(dto.getPurchaseType());
        return ApiResult.success(service.takeDelivery(dto.getPurchaseId()));
    }
}
