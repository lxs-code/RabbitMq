package com.zking.rabbitmqprovider.conllter;




import com.zking.vo.model.BookVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/msg")
@Slf4j
public class Senderconllter {



        @Autowired
        private AmqpTemplate rabbitTemplate;

@RequestMapping("/send")
   public Map<String,Object> send(String msg){
    log.info("msgsendcontroller.send.......");
    log.info(msg);
    Map<String,Object> map=new HashMap<String,Object>();
    map.put("msg",msg);
    map.put("code",0);
//    rabbitTemplate.convertAndSend("first", map);
    rabbitTemplate.convertAndSend("DirectExchange", "testdirectExchange", map);
       return map;
   }


    @RequestMapping("/send2")
    public Map<String,Object> send2(String msg,String key){
        log.info("msgsendcontroller.send.......");
        log.info(msg);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("msg",msg);
        map.put("code",0);
//    rabbitTemplate.convertAndSend("first", map);
        rabbitTemplate.convertAndSend("TOPICEXCHANGE", key, msg);
        return map;
    }
    @RequestMapping("/send3")
    public Map<String,Object> send3(String msg){
        log.info("msgsendcontroller.send.......");
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("msg",msg);
        map.put("code",0);
//    rabbitTemplate.convertAndSend("first", map);
        rabbitTemplate.convertAndSend("FANOUTEXCHANGE", null, map);
        return map;
    }

    @RequestMapping("/send4")
    public Map<String,Object> send4(String msg){
        log.info("msgsendcontroller.send.......");
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("msg",msg+"死信交换机");
        map.put("dataa",System.currentTimeMillis());
        BookVo bk=new BookVo();
        bk.setId(1);
        bk.setName("你妈的");

        map.put("code",0);
//    rabbitTemplate.convertAndSend("first", map);aa
        rabbitTemplate.convertAndSend("normal_queue", "direct_routing", bk);
        return map;
    }






}
