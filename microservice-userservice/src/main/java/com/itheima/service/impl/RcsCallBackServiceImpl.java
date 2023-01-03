package com.itheima.service.impl;

import com.itheima.redis.RedisUtil;
import com.itheima.service.RcsCallBackService;
import com.itheima.util.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Service
public class RcsCallBackServiceImpl implements RcsCallBackService {

    @Autowired
    private RedisUtil redisUtil;

    private static String taskRcsMq = "rcsDelayQueue";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void receiveRcsTask(String taskId) {
        //延迟2秒
        redisUtil.zAdd(taskRcsMq, this.setRcsMqValue(taskId, "outbin"), Instant.now().plusSeconds(2).getEpochSecond());
        //延迟5秒
        redisUtil.zAdd(taskRcsMq, this.setRcsMqValue(taskId, "end"), Instant.now().plusSeconds(5).getEpochSecond());
    }

    @Override
    public void dispatchTaskRcs() throws Exception {
        // 当前时间
        Set<ZSetOperations.TypedTuple<String>> typedTuples = redisUtil.zRangeWithScores(taskRcsMq, 0, 0);
        if (typedTuples.iterator().hasNext()) {//判断有任务
            //获取的就是value的值
            ZSetOperations.TypedTuple<String> next = typedTuples.iterator().next();
            Double aLong = next.getScore();
            if (aLong < Instant.now().getEpochSecond()) {//判断最早的消息时间大于当前时间
                String s = this.sendRcsMsg(next.getValue());
                if (JsonHelper.getString("code", s).equals("0")) {
                    // 删除已经执行的任务
                    redisUtil.zRemove(taskRcsMq, next.getValue());
                    System.out.println(String.format("【RCS】成功发送信息:【%s】", next.getValue()));
                } else {
                    //打印报错信息
                    System.out.println(String.format("【RCS】报错回告信息提示:【%s】", JsonHelper.getString("message", s)));
                }
            }
        }
    }

    /**
     * Ctu的回告
     *
     * @param json
     * @return
     */
    private String sendRcsMsg(String json) throws Exception {
        String url = "http://192.168.41.184:10018/agv/agvCallbackService/agvCallback";
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(url, json, String.class);
        return stringResponseEntity.getBody();
    }

    /**
     * 给MQ设值
     *
     * @param taskId
     * @param style
     */
    private String setRcsMqValue(String taskId, String style) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("reqCode", UUID.randomUUID().toString());
            map.put("method", style);
            map.put("taskCode", taskId);
            map.put("robotCode", "8007");
            map.put("podCode", UUID.randomUUID().toString());
            return JsonHelper.toJson(map);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
