package com.ifdeveloper.notificationproducer.amqp.producer

import com.ifdeveloper.notificationproducer.domain.Notification
import org.springframework.amqp.AmqpRejectAndDontRequeueException
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class ProducerRabbitMQ: ProducerAmqp<Notification> {

    @Value("\${spring.rabbitmq.routing-key.producer}")
    private lateinit var queue: String

    @Value("\${spring.rabbitmq.exchange.producer}")
    private lateinit var exchange: String

    @Autowired
    private val rabbitTemplate = RabbitTemplate()

    override fun producer(notification: Notification) {
        try {
            rabbitTemplate.convertAndSend(exchange, queue, notification)
        } catch (e: Exception) {
            throw AmqpRejectAndDontRequeueException(e)
        }
    }
}