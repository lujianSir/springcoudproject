package com.itheima.service.impl;

import com.itheima.redis.RedisUtil;
import com.itheima.service.CtuCallBackService;
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
public class CtuCallBackServiceImpl implements CtuCallBackService {

    @Autowired
    private RedisUtil redisUtil;

    private static String taskCtuMq="ctuDelayQueue";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void receiveCtuTask(String taskId) {
        //延迟2秒
        redisUtil.zAdd(taskCtuMq,this.setCtuMqValue(taskId,"outbin"), Instant.now().plusSeconds(2).getEpochSecond());
        //延迟5秒
        redisUtil.zAdd(taskCtuMq,this.setCtuMqValue(taskId,"end"), Instant.now().plusSeconds(5).getEpochSecond());
    }

    /**
     * 给MQ设值
     * @param taskId
     * @param style
     */
    private String setCtuMqValue(String taskId,String style){
        try {
            Map<String,String> map=new HashMap<>();
            map.put("reqCode", UUID.randomUUID().toString());
            map.put("method", style);
            map.put("taskCode", taskId);
            map.put("robotCode", "8007");
            return JsonHelper.toJson(map);
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public void dispatchTaskCtu() throws Exception {
        // 当前时间
        Set<ZSetOperations.TypedTuple<String>> typedTuples = redisUtil.zRangeWithScores(taskCtuMq, 0, 0);
        if(typedTuples.iterator().hasNext()){//判断有任务
            //获取的就是value的值
            ZSetOperations.TypedTuple<String> next = typedTuples.iterator().next();
            Double aLong = next.getScore();
            if(aLong<Instant.now().getEpochSecond()){//判断最早的消息时间大于当前时间
                String s = this.sendCtuMsg(next.getValue());
                if(JsonHelper.getString("code",s).equals("0")){
                    // 删除已经执行的任务
                    redisUtil.zRemove(taskCtuMq, next.getValue());
                    System.out.println(String.format("【CTU】成功发送信息:【%s】",next.getValue()));
                }else{
                    //打印报错信息
                    System.out.println(String.format("【CTU】报错回告信息提示:【%s】",JsonHelper.getString("message",s)));
                }
            }
        }
    }

    /**
     * Ctu的回告
     * @param json
     * @return
     */
    private String sendCtuMsg(String json) throws Exception {
        String url = "http://192.168.41.39:10018/ctu/ctuCallbackService/ctuCallback";
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(url, json, String.class);
        return stringResponseEntity.getBody();
    }
}
