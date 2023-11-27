package com.material.chain.logistics.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 物流订单地址表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_logistics_order_address")
public class LogisticsOrderAddressPo {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 物流商id
     */
    @TableField(value = "provider_id")
    private Long providerId;

    /**
     * 订单id
     */
    @TableField(value = "order_id")
    private Long orderId;

    /**
     * 收件人省
     */
    @TableField(value = "recipient_province")
    private String recipientProvince;

    /**
     * 收件人市
     */
    @TableField(value = "recipient_city")
    private String recipientCity;

    /**
     * 收件人区
     */
    @TableField(value = "recipient_area")
    private String recipientArea;

    /**
     * 收件人姓名
     */
    @TableField(value = "recipient_name")
    private String recipientName;

    /**
     * 收件人电话
     */
    @TableField(value = "recipient_phone")
    private String recipientPhone;

    /**
     * 收件人详细地址
     */
    @TableField(value = "recipient_address")
    private String recipientAddress;

    /**
     * 寄件人省
     */
    @TableField(value = "sender_province")
    private String senderProvince;

    /**
     * 寄件人市
     */
    @TableField(value = "sender_city")
    private String senderCity;

    /**
     * 寄件人区
     */
    @TableField(value = "sender_area")
    private String senderArea;

    /**
     * 寄件人姓名
     */
    @TableField(value = "sender_name")
    private String senderName;

    /**
     * 寄件人电话
     */
    @TableField(value = "sender_phone")
    private String senderPhone;

    /**
     * 寄件人详细地址
     */
    @TableField(value = "sender_address")
    private String senderAddress;

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