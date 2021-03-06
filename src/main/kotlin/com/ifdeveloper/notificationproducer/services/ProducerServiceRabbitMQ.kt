package com.ifdeveloper.notificationproducer.services

import com.ifdeveloper.notificationproducer.amqp.producer.ProducerAmqp
import com.ifdeveloper.notificationproducer.amqp.producer.ProducerRabbitMQ
import com.ifdeveloper.notificationproducer.domain.Notification
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProducerServiceRabbitMQ {

    @Autowired
    private val producerRabbitMQ: ProducerAmqp<Notification> = ProducerRabbitMQ()

    fun produceNotification(notification: Notification): Boolean {
        return try {
            producerRabbitMQ.producer(notification)
            true
        } catch (e: Exception) {
            false
        }
    }
}