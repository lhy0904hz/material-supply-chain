package com.material.chain.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.material.chain.user.domain.po.RolePo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper extends BaseMapper<RolePo> {
    int updateBatch(List<RolePo> list);

    int updateBatchSelective(List<RolePo> list);

    int batchInsert(@Param("list") List<RolePo> list);
}