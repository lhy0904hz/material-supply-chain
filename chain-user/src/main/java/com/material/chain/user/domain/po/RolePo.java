package com.material.chain.user.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_role")
public class RolePo {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 描述
     */
    @TableField(value = "description")
    private String description;

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
     * 修改人id
     */
    @TableField(value = "update_id")
    private Long updateId;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private Long updateTime;

    /**
     * 状态 1:正常 2:删除
     */
    @TableField(value = "`status`")
    private Integer status;
}