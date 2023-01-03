package com.itheima.service;

public interface RcsCallBackService {
    /**
     * 接收任务
     * @param taskId
     */
    void receiveRcsTask(String taskId);

    /**
     * 定时去扫描任务
     */
    void dispatchTaskRcs() throws Exception;
}
