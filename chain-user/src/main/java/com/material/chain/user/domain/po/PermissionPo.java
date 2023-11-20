package com.material.chain.user.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 资源表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_permission")
public class PermissionPo {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 用户id
     */
    @TableField(value = "`user_id`")
    private String userId;

    /**
     * 菜单名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 菜单路径
     */
    @TableField(value = "`path`")
    private String path;

    /**
     * 父id
     */
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 状态 1:正常 2:删除
     */
    @TableField(value = "`status`")
    private Integer status;

    /**
     * 排序
     */
    @TableField(value = "sort")
    private Integer sort;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Long createTime;

    /**
     * 创建人id
     */
    @TableField(value = "create_id")
    private Long createId;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private Long updateTime;

    /**
     * 修改人id
     */
    @TableField(value = "update_id")
    private Long updateId;

    /**
     * 资源类型 1:菜单 2:按钮
     */
    @TableField(value = "`type`")
    private Integer type;
}