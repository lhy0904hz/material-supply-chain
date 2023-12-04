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
public class TaskDTO {

    @ApiModelProperty(value = "页数")
    private Integer pageNo;

    @ApiModelProperty(value = "每页总数")
    private Integer pageSize;

    @ApiModelProperty(value = "采购单id集合")
    private List<Long> purchaseIds;
}
