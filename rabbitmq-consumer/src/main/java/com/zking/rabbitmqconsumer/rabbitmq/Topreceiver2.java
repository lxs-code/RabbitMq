package com.zking.rabbitmqconsumer.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "Queue2")
@Slf4j
public class Topreceiver2 {
    @RabbitHandler
    public void process(   Map<String,Object> map) {

        System.out.println("receive msg  Topreceiver2: " + map.get("msg"));
    }
}
