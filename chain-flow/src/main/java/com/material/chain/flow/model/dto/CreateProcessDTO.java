package com.material.chain.flow.model.dto;

import com.material.chain.flow.model.BasePo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel
public class CreateProcessDTO extends BasePo {

    @ApiModelProperty(value = "节点名称")
    private String processName;

    @ApiModelProperty(value = "用户id")
    private Long userId;

}
