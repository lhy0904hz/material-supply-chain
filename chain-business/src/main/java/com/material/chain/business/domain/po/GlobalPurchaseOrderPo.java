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
    * 国内采购单
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_global_purchase_order")
public class GlobalPurchaseOrderPo {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单号
     */
    @TableField(value = "oreder_no")
    private String orederNo;

    /**
     * 供应商id
     */
    @TableField(value = "supplier_id")
    private Long supplierId;

    /**
     * 订单总金额
     */
    @TableField(value = "price_total")
    private BigDecimal priceTotal;

    /**
     * 物料总数量
     */
    @TableField(value = "quantity_total")
    private Integer quantityTotal;

    /**
     * 物流单号
     */
    @TableField(value = "logistics_no")
    private String logisticsNo;

    /**
     * 币种
     */
    @TableField(value = "currency")
    private String currency;

    /**
     * 国家编码
     */
    @TableField(value = "country_code")
    private String countryCode;

    /**
     * 订单状态
     */
    @TableField(value = "order_status")
    private Integer orderStatus;

    /**
     * 物流状态
     */
    @TableField(value = "logistics_status")
    private Integer logisticsStatus;

    /**
     * 支付方式
     */
    @TableField(value = "pay_type")
    private Integer payType;

    /**
     * 支付时间
     */
    @TableField(value = "pay_time")
    private Long payTime;

    /**
     * 取消时间
     */
    @TableField(value = "cancel_time")
    private Long cancelTime;

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
}