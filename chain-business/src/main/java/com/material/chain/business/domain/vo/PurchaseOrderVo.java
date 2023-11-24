package com.material.chain.business.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class PurchaseOrderVo {

    @ApiModelProperty(value = "采购单id")
    private Long purchaseId;

    @ApiModelProperty(value = "采购类型 国内：internal 国际：global")
    private String purchaseType;
}
