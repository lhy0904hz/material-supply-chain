package com.material.chain.logistics.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogisticsOrderVo {
    @ApiModelProperty(value = "物流单id")
    private Long logisticsOrderId;

    @ApiModelProperty(value = "物流单号")
    private String orderNo;

    @ApiModelProperty(value = "业务系统单号")
    private String businessNo;

    @ApiModelProperty(value = "物流状态")
    private Integer status;
}
