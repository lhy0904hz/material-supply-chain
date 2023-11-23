package com.material.chain.business.domain.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class MaterialInventoryVo {

    /**
     * 物料id
     */
    private Long materialId;

    /**
     * 物料库存
     */
    private Integer inventoryNum;
}
