package com.zking.rabbitmqprovider.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitfanConfig {

//public static  final  String Queue1="QUEUE";

    public static  final  String FANEXCHANGE="FANOUTEXCHANGE";


   @Bean
    public Queue fanqueue1(){
    return  new Queue("fanQueue1",true);
}


    @Bean
    public Queue fanqueue2(){
        return  new Queue("fanQueue2",true);
    }

    @Bean
    public Queue fanqueue3(){
        return  new Queue("fanQueue3",true);
    }

    /**
     * 交换机
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange(){
     return  new FanoutExchange(FANEXCHANGE);
}


@Bean
    public Binding fanbinding1(){
        return BindingBuilder.bind(fanqueue1()).to(fanoutExchange());
}

    @Bean
    public Binding fanbinding2(){
        return BindingBuilder.bind(fanqueue2()).to(fanoutExchange());
    }

    @Bean
    public Binding fanbinding3(){
        return BindingBuilder.bind(fanqueue3()).to(fanoutExchange());

    }

}
