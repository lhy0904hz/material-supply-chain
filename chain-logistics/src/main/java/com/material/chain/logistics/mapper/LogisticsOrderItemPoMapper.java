package com.material.chain.logistics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.material.chain.logistics.domain.po.LogisticsOrderItemPo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogisticsOrderItemPoMapper extends BaseMapper<LogisticsOrderItemPo> {
    int updateBatch(List<LogisticsOrderItemPo> list);

    int updateBatchSelective(List<LogisticsOrderItemPo> list);

    int batchInsert(@Param("list") List<LogisticsOrderItemPo> list);
}