package com.material.chain.business.job;

import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestTask {

    @XxlJob(value = "demoJob")
    public void test() {
        log.info("测试定时任务");
    }
}
