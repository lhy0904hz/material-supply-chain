package com.material.chain.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.material.chain.business.domain.dto.TaskDTO;
import com.material.chain.business.domain.po.TaskPo;
import com.material.chain.business.mapper.TaskPoMapper;
import com.material.chain.business.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TaskServiceImpl extends ServiceImpl<TaskPoMapper, TaskPo> implements TaskService {

    /**
     * 生成任务
     */
    @Override
    public Boolean generateTask(TaskDTO dto) {
        return null;
    }
}
