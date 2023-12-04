package com.material.chain.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.material.chain.business.domain.dto.TaskDTO;
import com.material.chain.business.domain.po.TaskPo;

public interface TaskService extends IService<TaskPo> {

    /**
     * 生成任务
     */
    Boolean generateTask(TaskDTO dto);
}
