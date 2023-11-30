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
public class LogisticsOrderPageDTO {

    @ApiModelProperty(value = "页数")
    private Integer pageNo;

    @ApiModelProperty(value = "每页总数")
    private Integer pageSize;

    @ApiModelProperty(value = "物流状态")
    private Integer status;

    @ApiModelProperty(value = "物流单号")
    private String logisticsOrderNo;

    @ApiModelProperty(value = "业务系统订单号")
    private String businessNo;

}
