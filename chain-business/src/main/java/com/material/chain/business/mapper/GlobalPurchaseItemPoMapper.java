package com.material.chain.business.mapper;
import java.util.Collection;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.material.chain.business.domain.po.GlobalPurchaseItemPo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GlobalPurchaseItemPoMapper extends BaseMapper<GlobalPurchaseItemPo> {
    int updateBatch(List<GlobalPurchaseItemPo> list);

    int updateBatchSelective(List<GlobalPurchaseItemPo> list);

    int batchInsert(@Param("list") List<GlobalPurchaseItemPo> list);

    List<GlobalPurchaseItemPo> findAllInPurchaseIds(@Param("purchaseIds")Collection<Long> purchaseIds);

}