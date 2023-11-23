package com.material.chain.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.material.chain.business.domain.po.MaterialInventoryPo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MaterialInventoryPoMapper extends BaseMapper<MaterialInventoryPo> {
    int updateBatch(List<MaterialInventoryPo> list);

    int updateBatchSelective(List<MaterialInventoryPo> list);

    int batchInsert(@Param("list") List<MaterialInventoryPo> list);
}