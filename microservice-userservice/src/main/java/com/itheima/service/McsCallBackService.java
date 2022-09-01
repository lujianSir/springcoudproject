package com.itheima.service;

public interface McsCallBackService {

    /**
     * 接收任务
     * @param taskId
     */
    void receiveMcsTask(String taskId);

    /**
     * 定时去扫描任务
     */
    void dispatchTaskMcs() throws Exception;
}
