package com.example.order.controller.config.exception

import com.example.order.exception.BusinessException
import javax.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException::class)
    protected fun handleBusinessException(
        e: BusinessException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {

        return ResponseEntity.status(e.status).body(
            ErrorResponse(
                message = e.message,
                path = request.requestURI,
                errorCode = e.code
            )
        )
    }
}
