package com.material.chain.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePageDTO {

    /**
     * 页数
     */
    private Integer pageNo;

    /**
     * 每页总数
     */
    private Integer pageSize;

    /**
     * 角色名称
     */
    private String roleName;
}
