package com.example.order.controller.config.exception

import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes
import org.springframework.stereotype.Component
import org.springframework.web.context.request.WebRequest

@Component
class CustomErrorAttributes(
) : DefaultErrorAttributes() {
    override fun getErrorAttributes(webRequest: WebRequest?, options: ErrorAttributeOptions?): ErrorAttributes {

        val errorAttributes =
            super.getErrorAttributes(webRequest, ErrorAttributeOptions.of(ErrorAttributeOptions.Include.MESSAGE))

        errorAttributes.apply {
            set("errorCode", "UnexpectedException")
            remove("status")
            remove("error")
        }
        return errorAttributes
    }
}

typealias ErrorAttributes = MutableMap<String, Any>
