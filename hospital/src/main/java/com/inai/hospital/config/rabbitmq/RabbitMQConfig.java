package com.inai.hospital.config.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.disease.queue.name}")
    private String diseaseQueue;

    @Value("${rabbitmq.disease.registered.queue.name}")
    private String diseaseRegisteredQueue;

    @Value("${rabbitmq.user.queue.name}")
    private String userQueue;

    @Value("${rabbitmq.disease.routing.key}")
    private String diseaseRoutingKey;

    @Value("${rabbitmq.disease.registered.routing.key}")
    private String diseaseRegisteredRoutingKey;

    @Value("${rabbitmq.user.routing.key}")
    private String userRoutingKey;


    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }

    @Bean
    public Queue diseaseQueue(){
        return new Queue(diseaseQueue);
    }

    @Bean
    public Queue diseaseRegisteredQueue(){
        return new Queue(diseaseRegisteredQueue);
    }

    @Bean
    public Queue userQueue(){
        return new Queue(userQueue);
    }

    @Bean
    public Binding diseaseBinding(){
        return BindingBuilder
                .bind(diseaseQueue())
                .to(exchange())
                .with(diseaseRoutingKey);
    }

    @Bean
    public Binding diseaseRegisteredBinding(){
        return BindingBuilder
                .bind(diseaseRegisteredQueue())
                .to(exchange())
                .with(diseaseRegisteredRoutingKey);
    }

    @Bean
    public Binding userBinding(){
        return BindingBuilder
                .bind(userQueue())
                .to(exchange())
                .with(userRoutingKey);
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
