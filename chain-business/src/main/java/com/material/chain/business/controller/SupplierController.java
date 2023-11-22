package com.material.chain.business.controller;

import com.material.chain.business.domain.dto.SupplierDTO;
import com.material.chain.business.domain.vo.SupplierVo;
import com.material.chain.business.service.SupplierService;
import com.material.chain.common.doamin.vo.PageVo;
import com.material.chain.common.utils.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "供应商管理")
@RestController
@RequestMapping("v1/api/chain/business/supplier/")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @ApiOperation("供应商分页列表")
    @PostMapping(value = "pageList")
    public ApiResult<PageVo<SupplierVo>> pageList(@RequestBody SupplierDTO dto) {
        return ApiResult.success(supplierService.pageList(dto));
    }

    @ApiOperation("添加供应商")
    @PostMapping(value = "add")
    public ApiResult<Boolean> add(@RequestBody SupplierDTO dto) {
        return ApiResult.success(supplierService.addSupplier(dto));
    }

    @ApiOperation("编辑供应商")
    @PostMapping(value = "edit")
    public ApiResult<Boolean> editSupplier(@RequestBody SupplierDTO dto) {
        return ApiResult.success(supplierService.editSupplier(dto));
    }
}
