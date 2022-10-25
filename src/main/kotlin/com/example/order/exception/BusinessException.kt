package com.example.order.exception

import java.lang.RuntimeException
import org.springframework.http.HttpStatus

open class BusinessException(
    val code: String,
    val status: HttpStatus,
    override val message: String,
    cause: Throwable? = null
) : RuntimeException(message, cause) {

    constructor(errorCode: ErrorCode, messageMapper: ((String) -> String)? = null, cause: Throwable? = null) : this(
        code = errorCode.errorCode,
        status = errorCode.httpStatus,
        message = messageMapper?.let { messageMapper(errorCode.message) } ?: errorCode.message,
        cause = cause
    )
}
