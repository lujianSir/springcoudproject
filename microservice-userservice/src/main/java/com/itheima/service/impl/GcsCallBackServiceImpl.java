package com.itheima.service.impl;

import com.itheima.model.ZxGcsSendCarryResponseDto;
import com.itheima.redis.RedisUtil;
import com.itheima.service.GcsCallBackService;
import com.itheima.util.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.*;

@Service
public class GcsCallBackServiceImpl implements GcsCallBackService {

    @Autowired
    private RedisUtil redisUtil;

    private static String taskGcsMq="gcsDelayQueue";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void receiveGcsTask(String taskId) {
        //延迟2秒
        redisUtil.zAdd(taskGcsMq,this.setGcsMqValue(taskId,"1"), Instant.now().plusSeconds(2).getEpochSecond());
        //延迟5秒
        redisUtil.zAdd(taskGcsMq,this.setGcsMqValue(taskId,"2"), Instant.now().plusSeconds(5).getEpochSecond());
    }

    @Override
    public void dispatchTaskGcs() throws Exception {
        // 当前时间
        Set<ZSetOperations.TypedTuple<String>> typedTuples = redisUtil.zRangeWithScores(taskGcsMq, 0, 0);
        if(typedTuples.iterator().hasNext()){//判断有任务
            //获取的就是value的值
            ZSetOperations.TypedTuple<String> next = typedTuples.iterator().next();
            Double aLong = next.getScore();
            if(aLong<Instant.now().getEpochSecond()){//判断最早的消息时间大于当前时间
                String s = this.sendGcsMsg(next.getValue());
                if(JsonHelper.getBoolean("ret",s)){
                    // 删除已经执行的任务
                    redisUtil.zRemove(taskGcsMq, next.getValue());
                    System.out.println(String.format("【GCS】成功发送信息:【%s】",next.getValue()));
                }else{
                    //打印报错信息
                    System.out.println(String.format("【GCS】报错回告信息提示:【%s】",JsonHelper.getString("msg",s)));
                }
            }
        }
    }

    /**
     * 给MQ设值
     * @param taskId
     * @param style
     */
    private String setGcsMqValue(String taskId,String style){
        try {
            List<ZxGcsSendCarryResponseDto> zxGcsSendCarryResponseDtoList=new ArrayList<>();
            ZxGcsSendCarryResponseDto zxGcsSendCarryResponseDto=new ZxGcsSendCarryResponseDto();
            zxGcsSendCarryResponseDto.setBillCode(taskId);
            zxGcsSendCarryResponseDto.setWorkStatus(Integer.parseInt(style));
            zxGcsSendCarryResponseDtoList.add(zxGcsSendCarryResponseDto);
            return JsonHelper.toJson(zxGcsSendCarryResponseDtoList);
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
    private String sendGcsMsg(String json) throws Exception {
        String url = "http://192.168.41.184:10018/api/v1/gcs/task/GcsOrderReport";
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(url, json, String.class);
        return stringResponseEntity.getBody();
    }
}
