package com.material.chain.business.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 国内采购单物料表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_internal_purchase_item")
public class InternalPurchaseItemPo {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 采购单id
     */
    @TableField(value = "purchase_id")
    private String purchaseId;

    /**
     * 物料id
     */
    @TableField(value = "material_id")
    private Long materialId;

    /**
     * 供应商id
     */
    @TableField(value = "supplier_id")
    private Long supplierId;

    /**
     * 物料名称
     */
    @TableField(value = "material_name")
    private String materialName;

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
     * 总重量（T）
     */
    @TableField(value = "weight")
    private BigDecimal weight;

    /**
     * 单价
     */
    @TableField(value = "unit_price")
    private BigDecimal unitPrice;

    /**
     * 数量
     */
    @TableField(value = "quantity")
    private Integer quantity;

    /**
     * 总金额
     */
    @TableField(value = "price_total")
    private BigDecimal priceTotal;

    /**
     * 创建人id
     */
    @TableField(value = "create_id")
    private Long createId;

    /**
     * 修改人id
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