package com.zking.rabbitmqprovider.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitTopicConfig {

//public static  final  String Queue1="QUEUE";

    public static  final  String TOPICEXCHANGE="TOPICEXCHANGE";


   @Bean
    public Queue queue1(){
    return  new Queue("Queue1",true);
}


    @Bean
    public Queue queue2(){
        return  new Queue("Queue2",true);
    }

    @Bean
    public Queue queue3(){
        return  new Queue("Queue3",true);
    }

    /**
     * 交换机
     * @return
     */
    @Bean
    public TopicExchange topicExchange(){
     return  new TopicExchange(TOPICEXCHANGE);
}


@Bean
    public Binding binding1(){
        return BindingBuilder.bind(queue1()).to(topicExchange()).with("people.*");
}

    @Bean
    public Binding binding2(){
        return BindingBuilder.bind(queue2()).to(topicExchange()).with("*.people.boy");
    }

    @Bean
    public Binding binding3(){
        return BindingBuilder.bind(queue3()).to(topicExchange()).with("#.people");

    }

}
