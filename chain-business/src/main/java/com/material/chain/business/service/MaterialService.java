package com.material.chain.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.material.chain.business.domain.dto.SupplierMaterialDTO;
import com.material.chain.business.domain.po.MaterialInventoryPo;
import com.material.chain.business.domain.po.MaterialPo;
import com.material.chain.business.domain.vo.MaterialGenerateVo;

import java.util.List;

public interface MaterialService extends IService<MaterialPo> {

    /**
     * 添加物料
     */
    Boolean addMaterial(SupplierMaterialDTO dto);

    /**
     * 生成物料信息
     */
    MaterialGenerateVo generateMaterialCode(Long supplierId);

    /**
     * 根据id集合查询物料库存
     */
    List<MaterialInventoryPo> getMaterialInventoryByIds(List<Long> materialIds);

    /**
     * 根据id查询物料
     */
    MaterialPo getMaterialCodeById(Long id);
}
