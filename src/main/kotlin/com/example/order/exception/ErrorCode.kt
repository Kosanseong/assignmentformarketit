package com.example.order.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val errorCode: String,
    val httpStatus: HttpStatus,
    val message: String
) {
    UserNotFoundException("UNF-0001", HttpStatus.NOT_FOUND, "user is not found"),
    ProductNotFoundException("PNF-0001", HttpStatus.NOT_FOUND, "product is not found"),
    OrderNotFoundException("ONF-0001", HttpStatus.NOT_FOUND, "order is not found")
}
