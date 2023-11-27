package com.material.chain.logistics.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 物流订单表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_logistics_order")
public class LogisticsOrderPo {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 物流单号
     */
    @TableField(value = "order_no")
    private String orderNo;

    /**
     * 业务系统单号
     */
    @TableField(value = "business_no")
    private String businessNo;

    /**
     * 物流状态
     */
    @TableField(value = "`status`")
    private Integer status;

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