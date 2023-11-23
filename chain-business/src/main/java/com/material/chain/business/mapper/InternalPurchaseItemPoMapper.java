package com.material.chain.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.material.chain.business.domain.po.InternalPurchaseItemPo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InternalPurchaseItemPoMapper extends BaseMapper<InternalPurchaseItemPo> {
    int updateBatch(List<InternalPurchaseItemPo> list);

    int updateBatchSelective(List<InternalPurchaseItemPo> list);

    int batchInsert(@Param("list") List<InternalPurchaseItemPo> list);
}