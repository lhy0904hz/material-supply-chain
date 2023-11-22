package com.material.chain.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.material.chain.business.domain.po.SupplierPo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SupplierPoMapper extends BaseMapper<SupplierPo> {
    int updateBatch(List<SupplierPo> list);

    int updateBatchSelective(List<SupplierPo> list);

    int batchInsert(@Param("list") List<SupplierPo> list);
}