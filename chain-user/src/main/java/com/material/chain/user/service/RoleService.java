package com.material.chain.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.material.chain.user.domain.dto.RoleDTO;
import com.material.chain.user.domain.dto.RolePageDTO;
import com.material.chain.user.domain.po.RolePo;
import com.material.chain.user.domain.vo.PageVo;
import com.material.chain.user.domain.vo.RoleListVo;

public interface RoleService extends IService<RolePo> {

    /**
     * 添加角色
     * @param dto
     */
    Boolean addRole(RoleDTO dto);

    /**
     * 修改角色
     * @param dto
     * @return
     */
    Boolean updateRole(RoleDTO dto);

    /**
     * 角色列表
     * @param dto
     * @return
     */
    PageVo<RoleListVo> pageList(RolePageDTO dto);
}
