package com.material.chain.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.material.chain.user.domain.po.PermissionPo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PermissionPoMapper extends BaseMapper<PermissionPo> {
    int updateBatch(List<PermissionPo> list);

    int updateBatchSelective(List<PermissionPo> list);

    int batchInsert(@Param("list") List<PermissionPo> list);
}