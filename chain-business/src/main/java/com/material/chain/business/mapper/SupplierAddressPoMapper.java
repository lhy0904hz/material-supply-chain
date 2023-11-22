package com.material.chain.business.mapper;
import java.util.Collection;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.material.chain.business.domain.po.SupplierAddressPo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SupplierAddressPoMapper extends BaseMapper<SupplierAddressPo> {
    int updateBatch(List<SupplierAddressPo> list);

    int updateBatchSelective(List<SupplierAddressPo> list);

    int batchInsert(@Param("list") List<SupplierAddressPo> list);

    List<SupplierAddressPo> findAllBySupplierIds(@Param("supplierIds")Collection<Long> supplierIds);


}