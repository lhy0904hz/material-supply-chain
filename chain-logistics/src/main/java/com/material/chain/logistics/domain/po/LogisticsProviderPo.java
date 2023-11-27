package com.material.chain.logistics.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 物流商表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_logistics_provider")
public class LogisticsProviderPo {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 物流商名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 物流平台
     */
    @TableField(value = "`platform`")
    private String platform;

    /**
     * 状态 0:开启 1:关闭
     */
    @TableField(value = "`status`")
    private Byte status;

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