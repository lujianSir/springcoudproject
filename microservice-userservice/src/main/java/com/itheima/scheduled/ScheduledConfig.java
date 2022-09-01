package com.itheima.scheduled;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executors;

/**
 * @author chenbo
 * @date 2021/1/18 20:25
 */
@Configuration
//所有定时任务统一管理，定时任务启动使用不同的线程
public class ScheduledConfig implements SchedulingConfigurer {

    @Override
    // 设定一个长度为50的线程池
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(Executors.newScheduledThreadPool(50));
    }
}
