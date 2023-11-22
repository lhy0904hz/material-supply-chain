package com.material.chain.business.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 供应商地址表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_supplier_address")
public class SupplierAddressPo {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 供应商id
     */
    @TableField(value = "supplier_id")
    private Long supplierId;

    /**
     * 省
     */
    @TableField(value = "province")
    private String province;

    /**
     * 市
     */
    @TableField(value = "city")
    private String city;

    /**
     * 区
     */
    @TableField(value = "area")
    private String area;

    /**
     * 详细地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 邮编
     */
    @TableField(value = "zip_code")
    private String zipCode;

    /**
     * 寄件人名称
     */
    @TableField(value = "sender_name")
    private String senderName;

    /**
     * 寄件人手机号
     */
    @TableField(value = "sender_phone")
    private String senderPhone;

    /**
     * 是否为默认地址 0:默认 1:非默认
     */
    @TableField(value = "is_default")
    private Integer isDefault;

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