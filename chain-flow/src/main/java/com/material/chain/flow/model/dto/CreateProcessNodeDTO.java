package com.material.chain.flow.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel
public class CreateProcessNodeDTO {

    @ApiModelProperty(value = "流程id")
    private Long processId;

    @ApiModelProperty(value = "流程节点集合")
    private List<ProcessNodeDTO> nodeList;

}
