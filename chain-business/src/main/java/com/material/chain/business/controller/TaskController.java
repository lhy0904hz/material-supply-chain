package com.material.chain.business.controller;

import com.material.chain.business.domain.dto.SupplierDTO;
import com.material.chain.business.domain.dto.TaskDTO;
import com.material.chain.business.service.TaskService;
import com.material.chain.common.utils.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "任务管理")
@RestController
@RequestMapping("v1/api/chain/business/task/")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @ApiOperation("生成任务")
    @PostMapping(value = "generateTask")
    public ApiResult<Boolean> generateTask(@RequestBody TaskDTO dto) {
        return ApiResult.success(taskService.generateTask(dto));
    }
}
