package com.example.demo.rabbitconfiguration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration {

    public static final String EXCHANGE = "Exchange";
    public static final String ROUTING_KEY1 ="Routing_keybook";
    public static final String QUEUE1 ="Queuebook";
    public static final String ROUTING_KEY2 ="Routing_keyuser";
    public static final String QUEUE2 ="Queueuser";

    //making queue
    @Bean
    public Queue queue1()
    {
        return new Queue(QUEUE1);
    }
    @Bean
    public Queue queue2()
    {
        return new Queue(QUEUE2);
    }

    //making the exchange
    @Bean
    public TopicExchange exchange()
    {
        return new TopicExchange(EXCHANGE);
    }

    //binding the queue to the exchange using the routing key
    @Bean
    public Binding binding1(Queue queue1, TopicExchange exchange)
    {
    return BindingBuilder
            .bind(queue1)
            .to(exchange)
            .with(ROUTING_KEY1);
    }
    @Bean
    public Binding binding2(Queue queue2, TopicExchange exchange)
    {
        return BindingBuilder
                .bind(queue2)
                .to(exchange)
                .with(ROUTING_KEY2);
    }
    //converting the object type in java to json format for the queue
    @Bean
    public MessageConverter messageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }

    //used to make a rabbitTemplate and converts message to json format
    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory)
    {
        RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

}
