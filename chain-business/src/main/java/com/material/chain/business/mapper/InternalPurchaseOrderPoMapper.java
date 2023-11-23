package com.material.chain.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.material.chain.business.domain.po.InternalPurchaseOrderPo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InternalPurchaseOrderPoMapper extends BaseMapper<InternalPurchaseOrderPo> {
    int updateBatch(List<InternalPurchaseOrderPo> list);

    int updateBatchSelective(List<InternalPurchaseOrderPo> list);

    int batchInsert(@Param("list") List<InternalPurchaseOrderPo> list);
}