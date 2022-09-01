package com.itheima.scheduled;

import com.itheima.service.CtuCallBackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
@Slf4j
public class TimeTaskNew {

    @Autowired
    private CtuCallBackService ctuCallBackService;

    @Scheduled(initialDelay = 1000, fixedDelay = 1000)
    public void doOperationSecondTask() throws Exception {
        ctuCallBackService.dispatchTaskCtu();
    }
}
