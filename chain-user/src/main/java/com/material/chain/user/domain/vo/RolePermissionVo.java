package com.material.chain.user.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePermissionVo {

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 该角色下的菜单树
     */
    private List<PermissionVo> permissionList;
}
