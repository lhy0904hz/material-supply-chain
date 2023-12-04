package com.material.chain.business.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 任务表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_task")
public class TaskPo {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 部门id
     */
    @TableField(value = "department_id")
    private Long departmentId;

    /**
     * 任务编号
     */
    @TableField(value = "task_code")
    private String taskCode;

    /**
     * 物料总数
     */
    @TableField(value = "material_total")
    private Integer materialTotal;

    /**
     * 分配状态 0：未分配 1：已分配
     */
    @TableField(value = "allocation_status")
    private Byte allocationStatus;

    /**
     * 完成状态 0:未完成 1:已完成
     */
    @TableField(value = "finish_status")
    private Byte finishStatus;

    /**
     * 完成时间
     */
    @TableField(value = "finish_time")
    private Long finishTime;

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