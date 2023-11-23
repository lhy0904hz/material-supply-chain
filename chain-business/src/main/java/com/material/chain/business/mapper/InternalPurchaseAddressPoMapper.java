package com.material.chain.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.material.chain.business.domain.po.InternalPurchaseAddressPo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InternalPurchaseAddressPoMapper extends BaseMapper<InternalPurchaseAddressPo> {
    int updateBatch(List<InternalPurchaseAddressPo> list);

    int updateBatchSelective(List<InternalPurchaseAddressPo> list);

    int batchInsert(@Param("list") List<InternalPurchaseAddressPo> list);
}