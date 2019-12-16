package com.zking.rabbitmqprovider.rabbitmq;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class RabbitmqDirectConfig {
    /**
     *直连交换机
     * @return
     */

//    public static  final String QUEUE_NAME="direct_queue";
//    public static  final String EXCHANGE_NAME="direct_exchange";
//    public static  final String ROUTING_NAME="direct_routing";


//延时队列
public static  final String NORMAL_QUEUE_NAME="normal_queue";//正常队列名
    public static  final String NORMAL_EXCHANGE_NAME="normal_queue";//正常交换机名
    public static  final String NORMAL_ROUTING_KEY="direct_routing";//路由键

    public static  final String DELAY_NAME="DELAY_queue";//死信队列名
    public static  final String DELAY_EXCHANGE="DELAY_exchange";//死信交换机名
    public static  final String DELAY_ROUTING_KEY="DELAY_routing";//路由键

    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter jackson2JsonMessageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter);//指定json转换器123
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2MessageConverter() {
        Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();
        return jackson2JsonMessageConverter;
    }


    @Bean
    public Queue normalQueue() {
        // 创建一个队列，名称为：directQueue
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("x-message-ttl", 10000);//message在该队列queue的存活时间最大为10秒
        map.put("x-dead-letter-exchange", DELAY_EXCHANGE); //x-dead-letter-exchange参数是设置该队列的死信交换器（DLX）
        map.put("x-dead-letter-routing-key", DELAY_ROUTING_KEY);//x-dead-letter-routing-key参数是给这个DLX指定路由键
        return new Queue(NORMAL_QUEUE_NAME,true,false,false,map);
    }
    @Bean
    public DirectExchange normalExchange() {

        return new DirectExchange(NORMAL_EXCHANGE_NAME);
    }

    @Bean
    public  Binding bindingExchange(){
        return BindingBuilder.bind(normalQueue()).to(normalExchange()).with(NORMAL_ROUTING_KEY);
    }


//死信交换机


    @Bean
    public Queue delayQueue() {

        return new Queue(DELAY_NAME,true);
    }
    @Bean
    public DirectExchange delayExchange() {

        return new DirectExchange(DELAY_EXCHANGE);
    }

    @Bean
    public  Binding delaybindingExchange(){
        return BindingBuilder.bind(delayQueue()).to(delayExchange()).with(DELAY_ROUTING_KEY);
    }
















}
