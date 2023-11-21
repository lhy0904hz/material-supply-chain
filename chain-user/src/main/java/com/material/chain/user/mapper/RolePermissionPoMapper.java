package com.material.chain.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.material.chain.user.domain.po.RolePermissionPo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RolePermissionPoMapper extends BaseMapper<RolePermissionPo> {
    int updateBatch(List<RolePermissionPo> list);

    int updateBatchSelective(List<RolePermissionPo> list);

    int batchInsert(@Param("list") List<RolePermissionPo> list);
}