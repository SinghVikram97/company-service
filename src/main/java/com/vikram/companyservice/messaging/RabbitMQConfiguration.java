package com.vikram.companyservice.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
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
                new DirectExchange("x.delete-reviews-failure"),
                new Queue("q.delete-jobs"),
                QueueBuilder.durable("q.delete-reviews")
                        .withArgument("x-dead-letter-exchange","x.delete-reviews-failure")
                        .withArgument("x-dead-letter-routing-key","delete-reviews-fallback") // send to dead letter exchange with this routing key
                        .build(),
                new Queue("q.delete-reviews-dlq"),
                new Binding("q.delete-jobs", Binding.DestinationType.QUEUE, "x.delete-company","delete-jobs",null),
                new Binding("q.delete-reviews", Binding.DestinationType.QUEUE, "x.delete-company","delete-reviews",null),
                // accept if this routing key comes from exchange
                new Binding("q.delete-reviews-dlq", Binding.DestinationType.QUEUE, "x.delete-reviews-failure","delete-reviews-fallback",null)
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
