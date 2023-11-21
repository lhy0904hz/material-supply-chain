package com.material.chain.user.controller;

import com.material.chain.common.utils.ApiResult;
import com.material.chain.user.domain.dto.LoginDTO;
import com.material.chain.user.domain.dto.PermissionDTO;
import com.material.chain.user.domain.dto.UserRoleDTO;
import com.material.chain.user.domain.vo.LoginResponse;
import com.material.chain.user.domain.vo.PermissionVo;
import com.material.chain.user.service.PermissionService;
import com.material.chain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户管理
 */
@RestController
@RequestMapping("v1/api/chain/admin/user/")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;

    /**
     * 登录
     * @param dto 入参
     * @return LoginResponse
     */
    @PostMapping(value = "login")
    public ApiResult<LoginResponse> login(@RequestBody LoginDTO dto) {
        return ApiResult.success(userService.login(dto));
    }

    /**
     * 获取菜单树
     * @return List<PermissionVo>
     */
    @GetMapping(value = "getCurrentPermissionList")
    public ApiResult<List<PermissionVo>> getCurrentPermissionList() {
        return ApiResult.success(permissionService.getPermissionTree());
    }

    /**
     * 保存菜单权限
     * @param dto 入参
     * @return Boolean
     */
    @PostMapping(value = "savePermission")
    public ApiResult<Boolean> savePermission(@RequestBody PermissionDTO dto) {
        return ApiResult.success(permissionService.savePermission(dto));
    }

    /**
     * 保存用户角色权限
     */
    @PostMapping(value = "saveUserRole")
    public ApiResult<Boolean> saveUserRole(@RequestBody UserRoleDTO dto) {
        return ApiResult.success(permissionService.saveUserRole(dto));
    }

    /**
     * 退出登录
     * @return Boolean
     */
    @GetMapping(value = "logout")
    public ApiResult<Boolean> logout() {
        return ApiResult.success(permissionService.logout());
    }
}
