package com.material.chain.business.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 物料库存表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_material_inventory")
public class MaterialInventoryPo {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

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
     * 库存数量
     */
    @TableField(value = "inventory_number")
    private Integer inventoryNumber;

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