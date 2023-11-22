package com.material.chain.user.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserInfoResponse {

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "token")
    private String token;
}
