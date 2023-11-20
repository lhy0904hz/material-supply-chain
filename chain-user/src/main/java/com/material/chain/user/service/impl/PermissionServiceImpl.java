package com.material.chain.user.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.material.chain.common.enums.StatusEnum;
import com.material.chain.user.domain.po.PermissionPo;
import com.material.chain.user.domain.vo.PermissionVo;
import com.material.chain.user.mapper.PermissionPoMapper;
import com.material.chain.user.service.PermissionService;
import com.material.chain.user.utils.AppContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionPoMapper, PermissionPo> implements PermissionService {

    @Override
    public List<PermissionVo> getCurrentPermissionList() {
        LambdaQueryWrapper<PermissionPo> wrapper = Wrappers.lambdaQuery();
        Long currentUserId = AppContextUtil.getCurrentUserId();
        wrapper.eq(PermissionPo::getUserId, currentUserId);
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
