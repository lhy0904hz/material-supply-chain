package com.material.chain.business.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchasePageDTO {

    private Integer pageNo;

    private Integer pageSize;

    @ApiModelProperty(value = "采购类型 国内：internal 国际：global")
    private String purchaseType;

    @ApiModelProperty(value = "采购单编号")
    private String orderNo;

    @ApiModelProperty(value = "订单状态")
    private Integer status;

    @ApiModelProperty(value = "不包含的订单状态")
    private List<Integer> notInStatusList;
}
