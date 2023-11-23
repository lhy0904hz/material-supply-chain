package com.material.chain.business.domain.vo;

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
public class MaterialGenerateVo {

    @ApiModelProperty(value = "物料名称")
    private String materialName;

    @ApiModelProperty(value = "分段")
    private Integer subsection;

    @ApiModelProperty(value = "物料编码")
    private List<String> materialCodeList;
}
