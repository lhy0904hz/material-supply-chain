package com.material.chain.flow.model.dto;

import com.material.chain.flow.model.BasePo;
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
public class ProcessNodeDTO extends BasePo {

    @ApiModelProperty(value = "流程节点名称")
    private String nodeName;

    @ApiModelProperty(value = "是否指定审批人 1：不指定(默认取角色 ) 2：指定")
    private Integer isAssign;

    @ApiModelProperty(value = "节点类型 1:指定人串行 2:指定人并行 3:角色串行 4:角色并行")
    private Integer nodeType;

    @ApiModelProperty(value = "角色id集合")
    private List<Long> roleIds;

    @ApiModelProperty(value = "角色id集合")
    private List<Long> userIds;
}
