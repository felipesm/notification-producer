package com.ifdeveloper.notificationproducer.configuration

import org.springframework.amqp.core.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class ProducerRabbitConfiguration {
    @Value("\${spring.rabbitmq.routing-key.producer}")
    lateinit var queue: String

    @Value("\${spring.rabbitmq.exchange.producer}")
    lateinit var exchange: String

    @Value("\${spring.rabbitmq.dead-letter.producer}")
    lateinit var deadLetterQueue: String

    @Bean
    fun directExchange(): DirectExchange {
        return ExchangeBuilder.directExchange(exchange).build()
    }

    @Bean
    fun deadLetterQueue(): Queue {
        return QueueBuilder.durable(deadLetterQueue)
            .deadLetterExchange(exchange)
            .deadLetterRoutingKey(queue)
            .build()
    }

    @Bean
    fun queue(): Queue? {
        return QueueBuilder.durable(queue)
            .deadLetterExchange(exchange)
            .deadLetterRoutingKey(deadLetterQueue)
            .build()
    }

    @Bean
    fun bindingQueue(): Binding {
        return BindingBuilder
            .bind(queue())
            .to(directExchange())
            .with(queue)
    }

    @Bean
    fun bindingDeadLetter(): Binding {
        return BindingBuilder
            .bind(deadLetterQueue())
            .to(directExchange())
            .with(deadLetterQueue)
    }
}