package com.itheima.scheduled;

import com.itheima.service.*;
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

    @Autowired
    private McsCallBackService mcsCallBackService;

    @Autowired
    private LdRcsCallBackService ldRcsCallBackService;

    @Autowired
    private RcsCallBackService rcsCallBackService;

    @Autowired
    private GcsCallBackService gcsCallBackService;

    /**
     * RCS回告
     * @throws Exception
     */
    @Scheduled(initialDelay = 1000, fixedDelay = 1000)
    public void dispatchTaskRcs() throws Exception {
        rcsCallBackService.dispatchTaskRcs();
    }

    /**
     * CTU回告
     * @throws Exception
     */
    @Scheduled(initialDelay = 1000, fixedDelay = 1000)
    public void dispatchTaskCtu() throws Exception {
        ctuCallBackService.dispatchTaskCtu();
    }

    /**
     *MCS回告
     * @throws Exception
     */
    @Scheduled(initialDelay = 1000, fixedDelay = 1000)
    public void dispatchTaskMcs() throws Exception {
        mcsCallBackService.dispatchTaskMcs();
    }

    /**
     * Ld_Rcs回告
     * @throws Exception
     */
    @Scheduled(initialDelay = 1000, fixedDelay = 1000)
    public void dispatchTaskLdRcs() throws Exception {
        ldRcsCallBackService.dispatchTaskLdRcs();
    }

    /**
     * Gcs回告
     * @throws Exception
     */
    @Scheduled(initialDelay = 1000, fixedDelay = 1000)
    public void dispatchTaskGcs() throws Exception {
        gcsCallBackService.dispatchTaskGcs();
    }

}
