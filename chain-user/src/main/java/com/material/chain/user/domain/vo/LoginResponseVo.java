package com.material.chain.user.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class LoginResponseVo {

    @ApiModelProperty(value = "token")
    private String token;
}
