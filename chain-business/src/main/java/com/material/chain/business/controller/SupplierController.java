package com.material.chain.business.controller;

import com.material.chain.business.domain.dto.MaterialDTO;
import com.material.chain.business.domain.dto.SupplierDTO;
import com.material.chain.business.domain.dto.SupplierMaterialDTO;
import com.material.chain.business.domain.vo.MaterialGenerateVo;
import com.material.chain.business.domain.vo.SupplierVo;
import com.material.chain.business.service.MaterialService;
import com.material.chain.business.service.SupplierService;
import com.material.chain.common.doamin.vo.PageVo;
import com.material.chain.common.utils.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "供应商管理")
@RestController
@RequestMapping("v1/api/chain/business/supplier/")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private MaterialService materialService;

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

    @ApiOperation("添加物料")
    @PostMapping(value = "addMaterial")
    public ApiResult<Boolean> addMaterial(@RequestBody SupplierMaterialDTO dto) {
        return ApiResult.success(materialService.addMaterial(dto));
    }

    @ApiOperation("生成物料编码")
    @GetMapping(value = "generateMaterialCode")
    public ApiResult<MaterialGenerateVo> generateMaterialCode(Long supplierId) {
        return ApiResult.success(materialService.generateMaterialCode(supplierId));
    }
}
