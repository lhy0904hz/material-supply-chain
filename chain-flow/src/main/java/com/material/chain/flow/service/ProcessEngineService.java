package com.material.chain.flow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.material.chain.flow.model.dto.CreateProcessDTO;
import com.material.chain.flow.model.po.ProcessPo;

public interface ProcessEngineService extends IService<ProcessPo> {

    /**
     * 创建流程
     * @param dto 入参 　　　
     * @return Boolean
     */
    Long createProcess(CreateProcessDTO dto);
}
