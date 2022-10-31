package com.example.order.controller.config.exception

import java.time.OffsetDateTime

class ErrorResponse(
    val timestamp: OffsetDateTime = OffsetDateTime.now(),
    val message: String?,
    val path: String?,
    val errorCode: String
)

