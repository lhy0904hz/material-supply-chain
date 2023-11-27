package com.material.chain.logistics.domain.po;

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
@TableName(value = "t_logistics_order_item")
public class LogisticsOrderItemPo {
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
     * 订单ID
     */
    @TableField(value = "order_id")
    private Long orderId;

    /**
     * 包裹名称
     */
    @TableField(value = "item_name")
    private String itemName;

    /**
     * 总数量
     */
    @TableField(value = "quantity_total")
    private Integer quantityTotal;

    /**
     * 总价格
     */
    @TableField(value = "price_total")
    private BigDecimal priceTotal;

    /**
     * 总重量
     */
    @TableField(value = "weight_total")
    private BigDecimal weightTotal;

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