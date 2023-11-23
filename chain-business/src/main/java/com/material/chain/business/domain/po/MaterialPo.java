package com.material.chain.business.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_material")
public class MaterialPo {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 供应商ID
     */
    @TableField(value = "supplier_id")
    private Long supplierId;

    /**
     * 物料名称
     */
    @TableField(value = "material_name")
    private String materialName;

    /**
     * 分段
     */
    @TableField(value = "subsection")
    private Integer subsection;

    /**
     * 物料编码
     */
    @TableField(value = "material_code")
    private String materialCode;

    /**
     * 厚度（mm）
     */
    @TableField(value = "thickness")
    private Integer thickness;

    /**
     * 宽度（mm）
     */
    @TableField(value = "width")
    private Integer width;

    /**
     * 长度（mm）
     */
    @TableField(value = "`length`")
    private Integer length;

    /**
     * 类型
     */
    @TableField(value = "material_type")
    private String materialType;

    /**
     * 加工
     */
    @TableField(value = "machining")
    private String machining;

    /**
     * 零件数量
     */
    @TableField(value = "part_num")
    private Integer partNum;

    /**
     * 总重量（T）
     */
    @TableField(value = "total_weight")
    private BigDecimal totalWeight;

    /**
     * 价格
     */
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 创建人
     */
    @TableField(value = "create_id")
    private Long createId;

    /**
     * 修改人
     */
    @TableField(value = "update_id")
    private Long updateId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Long createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private Long updateTime;
}