package com.material.chain.logistics.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogisticsProviderVo {

    @ApiModelProperty(value = "服务商名称")
    private String providerName;
}
