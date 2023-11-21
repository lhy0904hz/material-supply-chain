package com.material.chain.user.domain.dto;

import com.material.chain.user.domain.po.BasePo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionDTO extends BasePo {

    /**
     * 菜单ID集合
     */
    private List<Long> permissionIds;

    /**
     * 角色id
     */
    private Long roleId;

}
