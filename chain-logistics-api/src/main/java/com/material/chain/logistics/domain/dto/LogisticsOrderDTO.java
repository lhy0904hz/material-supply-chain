package com.material.chain.logistics.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogisticsOrderDTO {

    @ApiModelProperty(value = "业务系统订单号")
    private String businessNo;

    @ApiModelProperty(value = "物流商id")
    private Long providerId;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "地址信息")
    private LogisticsOrderAddressDTO orderAddress;

    @ApiModelProperty(value = "包裹信息")
    private LogisticsOrderItemDTO orderItem;
}
