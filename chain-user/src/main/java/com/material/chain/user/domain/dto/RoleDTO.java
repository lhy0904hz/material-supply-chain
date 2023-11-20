package com.material.chain.user.domain.dto;

import com.material.chain.user.domain.po.BasePo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO extends BasePo {

    private Long id;

    private String roleName;

    private String description;
}
