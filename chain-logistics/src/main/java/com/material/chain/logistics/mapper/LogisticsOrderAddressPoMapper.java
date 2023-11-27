package com.material.chain.logistics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.material.chain.logistics.domain.po.LogisticsOrderAddressPo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogisticsOrderAddressPoMapper extends BaseMapper<LogisticsOrderAddressPo> {
    int updateBatch(List<LogisticsOrderAddressPo> list);

    int updateBatchSelective(List<LogisticsOrderAddressPo> list);

    int batchInsert(@Param("list") List<LogisticsOrderAddressPo> list);
}