package com.material.chain.user.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.material.chain.base.exception.ApiException;
import com.material.chain.user.constant.UserConstant;
import com.material.chain.user.domain.dto.RoleDTO;
import com.material.chain.user.domain.dto.RolePageDTO;
import com.material.chain.user.domain.po.RolePo;
import com.material.chain.user.domain.vo.PageVo;
import com.material.chain.user.domain.vo.RoleListVo;
import com.material.chain.user.mapper.RoleMapper;
import com.material.chain.user.service.RoleService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 角色
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RolePo> implements RoleService {

    @Override
    public Boolean addRole(RoleDTO dto) {
        RolePo po = new RolePo();
        Long nowTime = System.currentTimeMillis();
        po.setName(dto.getRoleName());
        po.setDescription(dto.getDescription());
        po.setStatus(UserConstant.ROLE_NORMAL_STATUS);
        po.setCreateId(1L);
        po.setUpdateId(1L);
        po.setCreateTime(nowTime);
        po.setUpdateTime(nowTime);
        return this.save(po);
    }

    @Override
    public Boolean updateRole(RoleDTO dto) {
        RolePo po = new RolePo();
        Long nowTime = System.currentTimeMillis();
        po.setId(dto.getId());
        po.setName(dto.getRoleName());
        po.setDescription(dto.getDescription());
        po.setUpdateId(1L);
        po.setUpdateTime(nowTime);
        return this.saveOrUpdate(po);
    }

    /**
     * 角色列表
     * @param dto
     * @return
     */
    @Override
    public PageVo<RoleListVo> pageList(RolePageDTO dto) {
        LambdaQueryWrapper<RolePo> wrapper = Wrappers.lambdaQuery();
        wrapper.like(StringUtils.isNotBlank(dto.getRoleName()), RolePo::getName, dto.getRoleName());
        IPage<RolePo> page = this.page(new Page<>(dto.getPageNo(), dto.getPageSize()), wrapper);
        List<RolePo> records = Optional.ofNullable(page).map(IPage::getRecords).orElse(new ArrayList<>());
        if (CollectionUtil.isEmpty(records)) {
            return new PageVo<>();
        }

        List<RoleListVo> roleList = records.stream().map(record -> RoleListVo
                        .builder()
                        .id(record.getId())
                        .roleName(record.getName())
                        .description(record.getDescription())
                        .createTime(record.getCreateTime())
                        .build())
                .collect(Collectors.toList());

        PageVo<RoleListVo> vo = new PageVo<>();
        vo.setPageNo(page.getCurrent());
        vo.setPageSize(page.getSize());
        vo.setRecords(roleList);
        vo.setTotal(page.getTotal());

        return vo;
    }

    /**
     * 根据用户ID获取角色
     * @param userId 用户ID
     * @return UserRoleDTO
     */
    @Override
    public List<Long> getRoleIdsListByUserId(Long userId) {
        LambdaQueryWrapper<RolePo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(RolePo::getCreateId, userId);
        List<RolePo> rolePoList = this.list(wrapper);
        if (CollectionUtils.isEmpty(rolePoList)) {
            throw new ApiException("无角色权限");
        }
        return rolePoList.stream().map(RolePo::getId).collect(Collectors.toList());
    }
}
