package com.lujian.microservicepluservice.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TestRetry {

    //重发次数
    private static final int tryTimes=20;

    //重试时间间隔
    private static final int intervalTime = 1;


    public static void main(String[] args) throws Exception{
        boolean flag = retryBuss();
        System.out.println("最终执行结果:" + (flag ? "成功" : "失败"));
    }

    private static boolean retryBuss()throws Exception{
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Integer retryNum = 1;
        boolean flag = false;
        while (retryNum <= tryTimes) {
            try {
                flag = execute(retryNum);
                if (flag) {
                    System.out.println(String.format("第%s次执行成功!!!，当前获取的值为%s，耗时%s",retryNum,6,df.format(new Date())));
                    break;
                }
            } catch (Exception e) {
                System.err.println(String.format("第%s次执行失败!!!，当前获取的值为%s，耗时%s",retryNum,e.getMessage(),df.format(new Date())));
                retryNum++;
                TimeUnit.SECONDS.sleep(intervalTime);
                //Thread.sleep(intervalTime);
                continue;
            }
        }

        return flag;
    }

    /**
     * 具体业务
     * @param retryNum
     * @return
     */
    private static boolean execute(int retryNum) throws Exception{
        Random random = new Random();
        int a = random.nextInt(10);
        boolean flag = true;
        if (a != 6) {
            flag = false;
            throw new Exception(String.valueOf(a));
        }
        return flag;
    }

}
