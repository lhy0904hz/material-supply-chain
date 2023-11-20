package com.material.chain.user.domain.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.math.BigDecimal;

@ApiModel
@Data
public class MaterialExcelDTO {

    /**
     * 船名
     */
    @ExcelProperty(value = "船名")
    private String shipName;

    /**
     * 批量
     */
    @ExcelProperty(value = "批量")
    private Integer batch;

    /**
     * 分段
     */
    @ExcelProperty(value = "分段")
    private Integer subsection;

    /**
     * 编码件号
     */
    @ExcelProperty(value = "编码")
    private String materielCode;

    /**
     * 材质
     */
    @ExcelProperty(value = "材质")
    private String material;

    /**
     * 厚度（mm）
     */
    @ExcelProperty(value = "厚度")
    private BigDecimal thickness;

    /**
     * 宽度（mm）
     */
    @ExcelProperty(value = "宽度")
    private BigDecimal width;

    /**
     * 长度（mm）
     */
    @ExcelProperty(value = "长度")
    private BigDecimal length;

    /**
     * 物料类型
     */
    @ExcelProperty(value = "类型")
    private String materielType;

    /**
     * 加工
     */
    @ExcelProperty(value = "加工")
    private String machining;

    /**
     * 零件数量
     */
    @ExcelProperty(value = "零件数量")
    private Integer partNumber;

    /**
     * 重量（t）
     */
    @ExcelProperty(value = "重量")
    private BigDecimal totalWeight;

    /**
     * 1：大船物料 2：中远物料 3：天津物料
     */
    @TableField(value = "material_classify")
    private Byte materialClassify;
}
