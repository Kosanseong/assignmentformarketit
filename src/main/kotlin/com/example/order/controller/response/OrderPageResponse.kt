package com.example.order.controller.response

import org.springframework.data.domain.Page

class OrderPageResponse(
    val response: Page<OrderResponse>
)
