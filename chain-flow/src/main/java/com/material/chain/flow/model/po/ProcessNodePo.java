package com.material.chain.flow.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 流程节点表
    */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_process_node")
public class ProcessNodePo {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 节点名称
     */
    @TableField(value = "node_name")
    private String nodeName;

    /**
     * 流程id
     */
    @TableField(value = "process_id")
    private Long processId;

    /**
     * 是否指定审批人 1：不指定(默认取角色 ) 2：指定
     */
    @TableField(value = "`is_ assign`")
    private Integer isAssign;

    /**
     * 状态 1:启用 2:禁用
     */
    @TableField(value = "`status`")
    private Integer status;

    /**
     * 创建人
     */
    @TableField(value = "create_id")
    private Long createId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Long createTime;

    /**
     * 修改人
     */
    @TableField(value = "update_id")
    private Long updateId;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private Long updateTime;

    /**
     * 节点类型 1:指定人串行 2:指定人并行 3:角色串行 4:角色并行
     */
    @TableField(value = "node_type")
    private Integer nodeType;

    /**
     * 审批角色集合
     */
    @TableField(value = "role_ids")
    private String roleIds;

    /**
     * 指定审批用户集合
     */
    @TableField(value = "user_Ids")
    private String userIds;
}