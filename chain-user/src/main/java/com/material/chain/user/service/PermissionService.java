package com.material.chain.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.material.chain.user.domain.dto.PermissionDTO;
import com.material.chain.user.domain.dto.UserRoleDTO;
import com.material.chain.user.domain.po.PermissionPo;
import com.material.chain.user.domain.vo.PermissionVo;

import java.util.List;

public interface PermissionService extends IService<PermissionPo> {

    /**
     * 获取菜单树
     * @return List<PermissionVo>
     */
    List<PermissionVo> getPermissionTree();

    /**
     * 保存菜单权限
     * @return boolean
     */
    Boolean savePermission(PermissionDTO dto);

    /**
     * 保存用户角色权限
     * @return boolean
     */
    Boolean saveUserRole(UserRoleDTO dto);

    /**
     * 退出登录
     * @return Boolean
     */
    Boolean logout();
}
