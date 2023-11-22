package com.material.chain.business.controller;

import com.material.chain.business.domain.dto.SupplierDTO;
import com.material.chain.business.domain.vo.SupplierVo;
import com.material.chain.business.service.SupplierService;
import com.material.chain.common.doamin.vo.PageVo;
import com.material.chain.common.utils.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api/chain/business/supplier/")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping(value = "pageList")
    public ApiResult<PageVo<SupplierVo>> pageList(@RequestBody SupplierDTO dto) {
        return ApiResult.success(supplierService.pageList(dto));
    }
}
