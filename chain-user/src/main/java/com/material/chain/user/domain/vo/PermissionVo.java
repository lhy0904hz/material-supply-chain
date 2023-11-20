package com.material.chain.user.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionVo {

    private Long id;

    /**
     * 父id
     */
    private Long parentId;

    /**
     * 菜单名称
     */
    private String permissionName;

    /**
     * 描述
     */
    private String description;

    /**
     * 菜单子节点
     */
    private List<PermissionVo> permissionChildrenList;

}
