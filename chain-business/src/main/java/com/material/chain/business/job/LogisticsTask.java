package com.material.chain.business.job;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LogisticsTask {

    @XxlJob(value = "demoJobHandler", init = "init", destroy = "destroy")
    public void demoJobHandler2() throws Exception {
        System.out.println("欢迎来到定时任务");
        XxlJobHelper.log("XXL-JOB, Hello World.");
        for (int i = 0; i < 5; i++) {
            XxlJobHelper.log("beat at:" + i);
        }
        // default success
    }

    // 初始化执行
    public void init() {
        log.info("init");
    }

    // 销毁执行
    public void destroy() {
        log.info("destory");
    }
}
