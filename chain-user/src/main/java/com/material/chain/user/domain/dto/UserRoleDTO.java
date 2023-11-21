package com.material.chain.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleDTO {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 角色id集合
     */
    private List<Long> roleIds;
}
