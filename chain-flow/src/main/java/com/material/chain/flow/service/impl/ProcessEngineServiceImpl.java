package com.material.chain.flow.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.material.chain.flow.convert.ProcessConvert;
import com.material.chain.flow.mapper.ProcessMapper;
import com.material.chain.flow.model.dto.CreateProcessDTO;
import com.material.chain.flow.model.po.ProcessPo;
import com.material.chain.flow.service.ProcessEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessEngineServiceImpl extends ServiceImpl<ProcessMapper, ProcessPo> implements ProcessEngineService {

    /**
     * 创建流程
     * @param dto 入参
     * @return Long
     */
    @Override
    public Long createProcess(CreateProcessDTO dto) {
        ProcessPo processPo = ProcessConvert.convertCreateProcessDTO(dto);
        this.save(processPo);
        return processPo.getId();
    }
}
