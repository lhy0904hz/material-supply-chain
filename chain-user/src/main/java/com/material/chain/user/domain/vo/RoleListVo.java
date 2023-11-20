package com.material.chain.user.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RoleListVo {

    private Long id;

    private String roleName;

    private String description;

    private Long createTime;
}
