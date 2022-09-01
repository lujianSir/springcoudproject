package com.itheima.service;

public interface CtuCallBackService {

    /**
     * 接收任务
     * @param taskId
     */
    void receiveCtuTask(String taskId);


    /**
     * 定时去扫描任务
     */
    void dispatchTaskCtu() throws Exception;
}
