package com.ifdeveloper.notificationproducer.amqp.producer

interface ProducerAmqp<T> {
    fun producer(t: T)
}