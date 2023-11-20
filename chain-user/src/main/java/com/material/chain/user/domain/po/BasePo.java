package com.material.chain.user.domain.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasePo {

    private Long createTime;

    private Long updateTime;

    private Long createId;

    private Long updateId;
}
