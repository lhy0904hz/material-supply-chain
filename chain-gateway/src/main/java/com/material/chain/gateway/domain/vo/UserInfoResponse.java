package com.material.chain.gateway.domain.vo;

import lombok.Data;

import java.util.List;

@Data
public class UserInfoResponse {

    private String account;

    private String userName;

    private String token;

    private List<Long> roleIds;
}
