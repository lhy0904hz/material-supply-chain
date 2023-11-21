package com.material.chain.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.material.chain.user.domain.po.RoleUserPo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleUserPoMapper extends BaseMapper<RoleUserPo> {
    int updateBatch(List<RoleUserPo> list);

    int updateBatchSelective(List<RoleUserPo> list);

    int batchInsert(@Param("list") List<RoleUserPo> list);
}