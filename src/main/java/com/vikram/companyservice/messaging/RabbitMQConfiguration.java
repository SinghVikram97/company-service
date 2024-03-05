package com.vikram.companyservice.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitMQConfiguration {
    private final ConnectionFactory connectionFactory;

    @Bean
    public Declarables createCompanyExchangeAndQueues(){
        // Fanout exchange i.e. send messages to all queues bound to this exchange irrespective of routing key
        return new Declarables(
                new FanoutExchange("x.delete-company"),
                new Queue("q.delete-jobs"),
                new Queue("q.delete-reviews"),
                new Binding("q.delete-jobs", Binding.DestinationType.QUEUE, "x.delete-company","delete-jobs",null),
                new Binding("q.delete-reviews", Binding.DestinationType.QUEUE, "x.delete-company","delete-reviews",null)
        );
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
