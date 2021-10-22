package com.ifdeveloper.notificationproducer.resources

import com.ifdeveloper.notificationproducer.domain.Notification
import com.ifdeveloper.notificationproducer.services.ProducerServiceRabbitMQ
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/producer")
class ProducerResource {

    @Autowired
    private val serviceRabbitMQ = ProducerServiceRabbitMQ()

    private val logger = LoggerFactory.getLogger(javaClass)

    @PostMapping("/notification")
    fun produceNotification(@RequestBody notification: Notification) : HttpEntity<Any> {
        logger.info("produce notification - $notification")
        val success = serviceRabbitMQ.produceNotification(notification)
        return if (success) {
            logger.info("produced notification - $notification")
            ResponseEntity.accepted().build()
        } else {
            logger.error("error produce notification - $notification")
            ResponseEntity.badRequest().build()
        }
    }
}