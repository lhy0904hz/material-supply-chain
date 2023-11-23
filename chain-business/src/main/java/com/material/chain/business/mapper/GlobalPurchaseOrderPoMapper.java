package com.material.chain.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.material.chain.business.domain.po.GlobalPurchaseOrderPo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GlobalPurchaseOrderPoMapper extends BaseMapper<GlobalPurchaseOrderPo> {
    int updateBatch(List<GlobalPurchaseOrderPo> list);

    int updateBatchSelective(List<GlobalPurchaseOrderPo> list);

    int batchInsert(@Param("list") List<GlobalPurchaseOrderPo> list);
}