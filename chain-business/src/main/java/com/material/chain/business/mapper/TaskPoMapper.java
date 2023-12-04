package com.material.chain.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.material.chain.business.domain.po.TaskPo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskPoMapper extends BaseMapper<TaskPo> {
    int updateBatch(List<TaskPo> list);

    int updateBatchSelective(List<TaskPo> list);

    int batchInsert(@Param("list") List<TaskPo> list);
}