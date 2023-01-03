package com.itheima.service;

public interface GcsCallBackService {

    /**
     * 接收任务
     * @param taskId
     */
    void receiveGcsTask(String taskId);

    /**
     * 定时去扫描任务
     */
    void dispatchTaskGcs() throws Exception;
}
