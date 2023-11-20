package com.material.chain.user.controller;

import com.material.chain.user.domain.dto.LoginDTO;
import com.material.chain.user.domain.dto.RoleDTO;
import com.material.chain.user.domain.dto.RolePageDTO;
import com.material.chain.user.domain.vo.PageVo;
import com.material.chain.user.domain.vo.RoleListVo;
import com.material.chain.user.service.RoleService;
import com.material.chain.user.utils.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色管理
 */
@RestController
@RequestMapping("v1/api/chain/role/")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 新增角色
     * @param dto 入参
     * @return Boolean
     */
    @PostMapping(value = "add")
    public ApiResult<Boolean> add(@RequestBody RoleDTO dto) {
        return ApiResult.success(roleService.addRole(dto));
    }

    /**
     * 修改角色
     * @param dto 入参
     * @return Boolean
     */
    @PostMapping(value = "update")
    public ApiResult<Boolean> update(@RequestBody RoleDTO dto) {
        return ApiResult.success(roleService.updateRole(dto));
    }

    /**
     * 角色列表
     * @param dto 入参
     * @return PageVo<RoleListVo>
     */
    @PostMapping(value = "pageList")
    public ApiResult<PageVo<RoleListVo>> pageList(@RequestBody RolePageDTO dto) {
        return ApiResult.success(roleService.pageList(dto));
    }
}
