package com.material.chain.flow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.material.chain.flow.model.po.ProcessNodePo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProcessNodePoMapper extends BaseMapper<ProcessNodePo> {
    int updateBatch(List<ProcessNodePo> list);

    int updateBatchSelective(List<ProcessNodePo> list);

    int batchInsert(@Param("list") List<ProcessNodePo> list);
}