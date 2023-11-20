package com.material.chain.user.controller;

import com.material.chain.user.domain.dto.LoginDTO;
import com.material.chain.user.domain.vo.LoginResponse;
import com.material.chain.user.service.UserService;
import com.material.chain.user.utils.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理
 */
@RestController
@RequestMapping("v1/api/chain/user/")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param dto 入参
     * @return LoginResponse
     */
    @PostMapping(value = "login")
    public ApiResult<LoginResponse> login(@RequestBody LoginDTO dto) {
        return ApiResult.success(userService.login(dto));
    }
}
