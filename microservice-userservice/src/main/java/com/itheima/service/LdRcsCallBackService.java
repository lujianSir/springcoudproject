package com.itheima.service;

public interface LdRcsCallBackService {

    /**
     * 接收任务
     * @param taskId
     */
    void receiveLdRcsTask(String taskId);

    /**
     * 定时去扫描任务
     */
    void dispatchTaskLdRcs() throws Exception;
}
