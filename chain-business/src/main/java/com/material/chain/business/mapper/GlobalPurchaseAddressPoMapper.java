package com.material.chain.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.material.chain.business.domain.po.GlobalPurchaseAddressPo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GlobalPurchaseAddressPoMapper extends BaseMapper<GlobalPurchaseAddressPo> {
    int updateBatch(List<GlobalPurchaseAddressPo> list);

    int updateBatchSelective(List<GlobalPurchaseAddressPo> list);

    int batchInsert(@Param("list") List<GlobalPurchaseAddressPo> list);
}