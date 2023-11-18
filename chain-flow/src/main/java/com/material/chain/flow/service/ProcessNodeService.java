package com.material.chain.flow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.material.chain.flow.model.dto.CreateProcessNodeDTO;
import com.material.chain.flow.model.po.ProcessNodePo;

public interface ProcessNodeService extends IService<ProcessNodePo> {

    boolean createProcessNode(CreateProcessNodeDTO dto);
}
