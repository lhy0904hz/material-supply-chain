package com.material.chain.logistics.mapper;
import java.util.Collection;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.material.chain.logistics.domain.po.LogisticsOrderPo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogisticsOrderPoMapper extends BaseMapper<LogisticsOrderPo> {

    int updateStatusByIds(@Param("status")Integer status,@Param("ids")List<Long> ids);

    LogisticsOrderPo findByBusinessNo(@Param("businessNo") String businessNo);



}