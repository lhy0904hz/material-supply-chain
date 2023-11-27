package com.material.chain.business.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseLogisticsDTO {

    @ApiModelProperty(value = "服务商id")
    private Long providerId;

    @ApiModelProperty(value = "采购单id")
    private Long purchaseOrderId;

    @ApiModelProperty(value = "采购类型 国内：internal 国际：global")
    private String purchaseType;
}
