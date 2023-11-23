package com.material.chain.business.domain.dto;

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
public class MaterialDTO {

    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 物料名称
     */
    @ApiModelProperty(value = "物料名称")
    private String materialName;

    /**
     * 分段
     */
    @ApiModelProperty(value = "分段")
    private Integer subsection;

    /**
     * 物料编码
     */
    @ApiModelProperty(value = "物料编码")
    private String materialCode;

    /**
     * 厚度（mm）
     */
    @ApiModelProperty(value = "厚度（mm）")
    private Integer thickness;

    /**
     * 宽度（mm）
     */
    @ApiModelProperty(value = "宽度（mm）")
    private Integer width;

    /**
     * 长度（mm）
     */
    @ApiModelProperty(value = "长度（mm）")
    private Integer length;

    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    private String materialType;

    /**
     * 加工
     */
    @ApiModelProperty(value = "加工")
    private String machining;

    /**
     * 零件数量
     */
    @ApiModelProperty(value = "零件数量")
    private Integer partNum;

    /**
     * 总重量（T）
     */
    @ApiModelProperty(value = "总重量（T）")
    private BigDecimal totalWeight;

    /**
     * 价格
     */
    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "库存数量")
    private Integer inventoryNumber;
}
