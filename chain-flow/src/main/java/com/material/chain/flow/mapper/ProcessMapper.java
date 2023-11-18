package com.material.chain.flow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.material.chain.flow.model.po.ProcessPo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProcessMapper extends BaseMapper<ProcessPo> {
    int updateBatch(List<ProcessPo> list);

    int updateBatchSelective(List<ProcessPo> list);

    int batchInsert(@Param("list") List<ProcessPo> list);
}