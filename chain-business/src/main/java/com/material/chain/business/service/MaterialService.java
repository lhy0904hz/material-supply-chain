package com.material.chain.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.material.chain.business.domain.dto.SupplierMaterialDTO;
import com.material.chain.business.domain.po.MaterialPo;
import com.material.chain.business.domain.vo.MaterialGenerateVo;

public interface MaterialService extends IService<MaterialPo> {

    /**
     * 添加物料
     */
    Boolean addMaterial(SupplierMaterialDTO dto);

    /**
     * 生成物料信息
     */
    MaterialGenerateVo generateMaterialCode(Long supplierId);
}
