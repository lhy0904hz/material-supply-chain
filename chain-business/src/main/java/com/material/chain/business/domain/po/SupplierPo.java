package com.material.chain.business.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 供应商表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_supplier")
public class SupplierPo {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 供应商名称
     */
    @TableField(value = "supplier_name")
    private String supplierName;

    /**
     * 主体名称
     */
    @TableField(value = "subject_name")
    private String subjectName;

    /**
     * 供应商等级 S A B C D
     */
    @TableField(value = "`level`")
    private String level;

    /**
     * 供应商类型 1:私企 2:国企
     */
    @TableField(value = "supplier_type")
    private Byte supplierType;

    /**
     * 创建人ID
     */
    @TableField(value = "create_id")
    private Long createId;

    /**
     * 修改人ID
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