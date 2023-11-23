package com.material.chain.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.material.chain.business.domain.po.MaterialPo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MaterialPoMapper extends BaseMapper<MaterialPo> {
    int updateBatch(List<MaterialPo> list);

    int updateBatchSelective(List<MaterialPo> list);

    int batchInsert(@Param("list") List<MaterialPo> list);

    Integer findSubsectionBySupplierId(@Param("supplierId")Long supplierId);

}