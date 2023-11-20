package com.material.chain.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.material.chain.user.domain.po.PermissionPo;
import com.material.chain.user.domain.vo.PermissionVo;

import java.util.List;

public interface PermissionService extends IService<PermissionPo> {

    List<PermissionVo> getCurrentPermissionList();
}
