package com.material.chain.logistics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.material.chain.logistics.domain.po.LogisticsProviderPo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogisticsProviderPoMapper extends BaseMapper<LogisticsProviderPo> {

    List<LogisticsProviderPo> findAllByStatus(@Param("status") Integer status);


}