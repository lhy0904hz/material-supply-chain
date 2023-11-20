package com.material.chain.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.material.chain.user.domain.po.UserPo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserPoMapper extends BaseMapper<UserPo> {
    int batchInsert(@Param("list") List<UserPo> list);
}