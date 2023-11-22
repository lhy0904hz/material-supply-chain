package com.material.chain.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.material.chain.business.domain.dto.SupplierDTO;
import com.material.chain.business.domain.po.SupplierPo;
import com.material.chain.business.domain.vo.SupplierVo;
import com.material.chain.common.doamin.vo.PageVo;

public interface SupplierService extends IService<SupplierPo> {

    /**
     * 供应商分页列表
     */
    PageVo<SupplierVo> pageList(SupplierDTO dto);

    /**
     * 新增供应商
     */
    Boolean addSupplier(SupplierDTO dto);

    /**
     * 编辑供应商
     */
    Boolean editSupplier(SupplierDTO dto);
}
