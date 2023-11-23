package com.material.chain.business.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 国内采购单地址表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_internal_purchase_address")
public class InternalPurchaseAddressPo {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 采购单id
     */
    @TableField(value = "purchase_id")
    private Long purchaseId;

    /**
     * 收件人姓名
     */
    @TableField(value = "recipient_name")
    private String recipientName;

    /**
     * 收件人手机号
     */
    @TableField(value = "recipient_phone")
    private String recipientPhone;

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