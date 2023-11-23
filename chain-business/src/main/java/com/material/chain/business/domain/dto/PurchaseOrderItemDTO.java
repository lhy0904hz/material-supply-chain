package com.material.chain.business.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderItemDTO {

    @ApiModelProperty(value = "采购单id")
    private String purchaseId;

    @ApiModelProperty(value = "物料id")
    private Long materialId;

    @ApiModelProperty(value = "供应商id")
    private Long supplierId;

    @ApiModelProperty(value = "物料名称")
    private String materialName;

    @ApiModelProperty(value = "厚度（mm）")
    private Integer thickness;

    @ApiModelProperty(value = "宽度（mm）")
    private Integer width;

    @ApiModelProperty(value = "长度（mm）")
    private Integer length;

    @ApiModelProperty(value = "重量（T）")
    private BigDecimal weight;

    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;

    @ApiModelProperty(value = "数量")
    private Integer quantity;
}
