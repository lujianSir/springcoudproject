package com.itheima.service.impl;

import com.itheima.redis.RedisUtil;
import com.itheima.service.LdRcsCallBackService;
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

@Service
public class LdRcsCallBackServiceImpl implements LdRcsCallBackService {

    @Autowired
    private RedisUtil redisUtil;

    private static String taskLdRcsMq="ldRcsDelayQueue";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void receiveLdRcsTask(String taskId) {
        //延迟2秒
        redisUtil.zAdd(taskLdRcsMq,this.setLdRcsMqValue(taskId,"AP"), Instant.now().plusSeconds(2).getEpochSecond());
        //延迟5秒
        redisUtil.zAdd(taskLdRcsMq,this.setLdRcsMqValue(taskId,"AD"), Instant.now().plusSeconds(5).getEpochSecond());
    }

    @Override
    public void dispatchTaskLdRcs() throws Exception {
        // 当前时间
        Set<ZSetOperations.TypedTuple<String>> typedTuples = redisUtil.zRangeWithScores(taskLdRcsMq, 0, 0);
        if(typedTuples.iterator().hasNext()){//判断有任务
            //获取的就是value的值
            ZSetOperations.TypedTuple<String> next = typedTuples.iterator().next();
            Double aLong = next.getScore();
            if(aLong<Instant.now().getEpochSecond()){//判断最早的消息时间大于当前时间
                String s = this.sendLdRcsMsg(next.getValue());
                if(JsonHelper.getBoolean("ret",s)){
                    // 删除已经执行的任务
                    redisUtil.zRemove(taskLdRcsMq, next.getValue());
                    System.out.println(String.format("【Ld_Rcs】成功发送信息:【%s】",next.getValue()));
                }else{
                    //打印报错信息
                    System.out.println(String.format("【Ld_Rcs】报错回告信息提示:【%s】",JsonHelper.getString("msg",s)));
                }
            }
        }
    }

    /**
     * 给MQ设值
     * @param taskId
     * @param taskType
     */
    private String setLdRcsMqValue(String taskId,String taskType){
        try {
            Map<String,String> map=new HashMap<>();
            map.put("taskId", taskId);
            map.put("taskType", taskType);
            map.put("taskStatuCode", "");
            map.put("taskStatuName", "");
            map.put("agvId", "");
            map.put("agvModel", "");
            return JsonHelper.toJson(map);
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    /**
     * Ctu的回告
     * @param json
     * @return
     */
    private String sendLdRcsMsg(String json) throws Exception {
        String url = "http://192.168.41.172:10018/api/v1/ldAgv/task/agvCallback";
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(url, json, String.class);
        return stringResponseEntity.getBody();
    }
}
