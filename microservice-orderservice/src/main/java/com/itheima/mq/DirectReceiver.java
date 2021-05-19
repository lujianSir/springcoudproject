package com.itheima.mq;

import com.itheima.model.FileLogHelper;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
//@RabbitListener(queues = "myDirectQueue")
public class DirectReceiver{

    @RabbitHandler
    @RabbitListener(queues = "TestDirectQueue")
    public void process(String msg) {
        FileLogHelper.WriteLog("process", "接收消息:"+msg);
    }
}
