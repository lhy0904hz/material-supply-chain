package com.material.chain.logistics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.material.chain.logistics.domain.po.LogisticsTrajectoryPo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogisticsTrajectoryPoMapper extends BaseMapper<LogisticsTrajectoryPo> {
    int updateBatch(List<LogisticsTrajectoryPo> list);

    int updateBatchSelective(List<LogisticsTrajectoryPo> list);

    int batchInsert(@Param("list") List<LogisticsTrajectoryPo> list);

    List<LogisticsTrajectoryPo> findAllByOrderId(@Param("orderId") Long orderId);


}