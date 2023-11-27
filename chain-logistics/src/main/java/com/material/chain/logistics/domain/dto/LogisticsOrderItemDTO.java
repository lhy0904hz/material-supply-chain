package com.material.chain.logistics.domain.dto;

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
public class LogisticsOrderItemDTO {

    @ApiModelProperty(value = "包裹名称")
    private String itemName;

    @ApiModelProperty(value = "总数量")
    private Integer quantityTotal;

    @ApiModelProperty(value = "总价格")
    private BigDecimal priceTotal;

    @ApiModelProperty(value = "总重量")
    private BigDecimal weightTotal;
}
