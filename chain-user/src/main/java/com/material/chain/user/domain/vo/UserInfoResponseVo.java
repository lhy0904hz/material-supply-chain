package com.material.chain.user.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class UserInfoResponseVo {

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "token")
    private String token;

    @ApiModelProperty(value = "角色ID集合")
    private List<Long> roleIds;
}
