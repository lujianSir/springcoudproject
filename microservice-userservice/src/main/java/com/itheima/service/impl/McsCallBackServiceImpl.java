package com.itheima.service.impl;

import com.itheima.redis.RedisUtil;
import com.itheima.service.McsCallBackService;
import com.itheima.util.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
public class McsCallBackServiceImpl implements McsCallBackService {

    @Autowired
    private RedisUtil redisUtil;

    private static String taskMcsMq="mcsDelayQueue";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void receiveMcsTask(String taskId) {
        //延迟2秒
        redisUtil.zAdd(taskMcsMq,this.setMcsMqValue(taskId,"1"), Instant.now().plusSeconds(2).getEpochSecond());
        //延迟5秒
        redisUtil.zAdd(taskMcsMq,this.setMcsMqValue(taskId,"2"), Instant.now().plusSeconds(5).getEpochSecond());
    }

    @Override
    public void dispatchTaskMcs() throws Exception {
        // 当前时间
        Set<ZSetOperations.TypedTuple<String>> typedTuples = redisUtil.zRangeWithScores(taskMcsMq, 0, 0);
        if(typedTuples.iterator().hasNext()){//判断有任务
            //获取的就是value的值
            ZSetOperations.TypedTuple<String> next = typedTuples.iterator().next();
            Double aLong = next.getScore();
            if(aLong<Instant.now().getEpochSecond()){//判断最早的消息时间大于当前时间
                String s = this.sendMcsMsg(next.getValue());
                if(JsonHelper.getBoolean("ret",s)){
                    // 删除已经执行的任务
                    redisUtil.zRemove(taskMcsMq, next.getValue());
                    System.out.println(String.format("【MCS】成功发送信息:【%s】",next.getValue()));
                }else{
                    //打印报错信息
                    System.out.println(String.format("【MCS】报错回告信息提示:【%s】",JsonHelper.getString("msg",s)));
                }
            }
        }
    }

    /**
     * 给MQ设值
     * @param taskId
     * @param style
     */
    private String setMcsMqValue(String taskId,String style){
        try {
            Map<String,String> map=new HashMap<>();
            map.put("taskId", taskId);
            map.put("status", style);
            List<String> list=new ArrayList<>();
            list.add(JsonHelper.toJson(map));
            Map<String,String> newMap=new HashMap<>();
            newMap.put("carryList",JsonHelper.toJson(list));
            return JsonHelper.toJson(newMap);
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    /**
     * Mcs的回告
     * @param json
     * @return
     */
    private String sendMcsMsg(String json) throws Exception {
        String url = "http://192.168.41.39:10018/api/v1/mcs/task/taskReturn";
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(url, json, String.class);
        return stringResponseEntity.getBody();
    }

    public static void main(String[] args) {
        ThreadPoolTaskExecutor threadPoolTaskExecutor=new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(100);
        threadPoolTaskExecutor.setMaxPoolSize(200);
        threadPoolTaskExecutor.initialize();
        List<Object> list=new ArrayList<>();
        for (int i = 0; i <100 ; i++) {
            list.add(i);
        }
        for (Object o:list) {
            CompletableFuture.runAsync(()->{
                System.out.println(Thread.currentThread().getName());
            },threadPoolTaskExecutor);
        }

    }
}
