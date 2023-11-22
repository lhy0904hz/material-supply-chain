package com.material.chain.user.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.material.chain.base.redis.RedisTemplateService;
import com.material.chain.base.utils.AppContextUtil;
import com.material.chain.common.enums.StatusEnum;
import com.material.chain.user.domain.dto.PermissionDTO;
import com.material.chain.user.domain.dto.UserRoleDTO;
import com.material.chain.user.domain.po.PermissionPo;
import com.material.chain.user.domain.po.RolePermissionPo;
import com.material.chain.user.domain.po.RoleUserPo;
import com.material.chain.user.domain.vo.PermissionVo;
import com.material.chain.user.mapper.PermissionPoMapper;
import com.material.chain.user.mapper.RolePermissionPoMapper;
import com.material.chain.user.mapper.RoleUserPoMapper;
import com.material.chain.user.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionPoMapper, PermissionPo> implements PermissionService {

    @Autowired
    private RolePermissionPoMapper rolePermissionMapper;
    @Autowired
    private RoleUserPoMapper roleUserPoMapper;

    /**
     * 获取菜单树
     * @return List<PermissionVo>
     */
    @Override
    public List<PermissionVo> getPermissionTree() {
        LambdaQueryWrapper<PermissionPo> wrapper = Wrappers.lambdaQuery();
        Long currentUserId = AppContextUtil.getCurrentUserId();
        wrapper.eq(PermissionPo::getStatus, StatusEnum.NORMAL.getCode());
        List<PermissionPo> permissionList = this.list(wrapper);
        if (CollectionUtil.isEmpty(permissionList)) {
            return Collections.emptyList();
        }

        List<PermissionVo> permissionVoList = new ArrayList<>();
        for (PermissionPo po : permissionList) {
            if (po.getParentId() == 0) {
                PermissionVo vo = new PermissionVo();
                vo.setId(po.getId());
                vo.setPermissionName(po.getName());
                vo.setParentId(po.getParentId());
                vo.setDescription(po.getDescription());
                vo.setPermissionChildrenList(buildPermissionChildrenList(po.getId(), permissionList));
                permissionVoList.add(vo);
            }
        }
        log.info("当前用户id：{}，权限菜单：{}", currentUserId, JSON.toJSONString(permissionVoList));
        return permissionVoList;
    }

    /**
     * 保存菜单权限
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean savePermission(PermissionDTO dto) {
        LambdaQueryWrapper<RolePermissionPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(RolePermissionPo::getRoleId, dto.getRoleId());
        rolePermissionMapper.delete(wrapper);
        Long currentUserId = AppContextUtil.getCurrentUserId();
        long timeMillis = System.currentTimeMillis();
        List<RolePermissionPo> rolePermissionList = dto.getPermissionIds().stream().map(d -> {
            RolePermissionPo po = new RolePermissionPo();
            po.setPermissionId(d);
            po.setRoleId(dto.getRoleId());
            po.setCreateId(currentUserId);
            po.setUpdateId(currentUserId);
            po.setCreateTime(timeMillis);
            po.setUpdateTime(timeMillis);
            return po;
        }).collect(Collectors.toList());
        return rolePermissionMapper.batchInsert(rolePermissionList) > 0;
    }

    /**
     * 保存用户角色权限
     */
    @Override
    public Boolean saveUserRole(UserRoleDTO dto) {
        LambdaQueryWrapper<RoleUserPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(RoleUserPo::getUserId, dto.getUserId());
        roleUserPoMapper.delete(wrapper);
        Long currentUserId = AppContextUtil.getCurrentUserId();
        long timeMillis = System.currentTimeMillis();
        List<RoleUserPo> roleUserList = dto.getRoleIds().stream().map(d -> {
            RoleUserPo po = new RoleUserPo();
            po.setUserId(dto.getUserId());
            po.setRoleId(d);
            po.setCreateId(currentUserId);
            po.setUpdateId(currentUserId);
            po.setCreateTime(timeMillis);
            po.setUpdateTime(timeMillis);
            return po;
        }).collect(Collectors.toList());
        return roleUserPoMapper.batchInsert(roleUserList) > 0;
    }

    /**
     * 根据当前用户获取菜单树
     * @return List<PermissionVo>
     */
    @Override
    public List<PermissionVo> getPermissionByCurrentUserId() {
        Long currentUserId = AppContextUtil.getCurrentUserId();

        return null;
    }

    private List<PermissionVo> buildPermissionChildrenList(Long parentId, List<PermissionPo> permissionList) {
        List<PermissionVo> childrenList = new ArrayList<>();
        for (PermissionPo po : permissionList) {
            if (Objects.equals(po.getParentId(), parentId)) {
                PermissionVo vo = new PermissionVo();
                vo.setId(po.getId());
                vo.setPermissionName(po.getName());
                vo.setParentId(po.getParentId());
                vo.setDescription(po.getDescription());
                List<PermissionVo> permissionChildrenList = buildPermissionChildrenList(po.getId(), permissionList);
                vo.setPermissionChildrenList(CollectionUtil.isNotEmpty(permissionChildrenList) ? permissionChildrenList : Collections.emptyList());
                childrenList.add(vo);
            }
        }
        return childrenList;
    }
}
