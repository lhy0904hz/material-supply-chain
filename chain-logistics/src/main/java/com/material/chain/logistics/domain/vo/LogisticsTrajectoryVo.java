package com.material.chain.logistics.domain.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogisticsTrajectoryVo {

    /**
     * 轨迹描述
     */
    private String desc;

    /**
     * 时间
     */
    private Long dateTime;
}
