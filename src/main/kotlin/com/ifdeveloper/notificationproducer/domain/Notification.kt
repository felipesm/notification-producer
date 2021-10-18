package com.ifdeveloper.notificationproducer.domain

import java.time.LocalDateTime

data class Notification (
    val subject: String,
    val message: String,
    val from: String,
    val to: String,
    val createdAt: LocalDateTime
)