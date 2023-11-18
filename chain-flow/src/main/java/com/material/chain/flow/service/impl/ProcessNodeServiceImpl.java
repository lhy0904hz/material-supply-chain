package com.material.chain.flow.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.material.chain.flow.convert.ProcessNodeConvert;
import com.material.chain.flow.mapper.ProcessNodePoMapper;
import com.material.chain.flow.model.dto.CreateProcessNodeDTO;
import com.material.chain.flow.model.po.ProcessNodePo;
import com.material.chain.flow.service.ProcessNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessNodeServiceImpl extends ServiceImpl<ProcessNodePoMapper, ProcessNodePo> implements ProcessNodeService {

    @Autowired
    private ProcessNodePoMapper processNodeMapper;

    @Override
    public boolean createProcessNode(CreateProcessNodeDTO dto) {
        List<ProcessNodePo> processNodeList = ProcessNodeConvert.convertProcessNodeList(dto.getNodeList(), dto.getProcessId());
        processNodeMapper.batchInsert(processNodeList);
        return true;
    }
}
