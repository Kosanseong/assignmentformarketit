package com.example.order.service.dto

import com.example.order.controller.response.OrderResponse
import com.example.order.entity.order.Order
import com.example.order.entity.order.OrderId
import com.example.order.entity.product.ProductId
import com.example.order.entity.user.UserId
import java.time.OffsetDateTime

class OrderDto(
    val orderId: OrderId,
    val userId: UserId,
    val productQuantity: Int,
    val productName: String,
    val productId: ProductId,
    val createdAt: OffsetDateTime,
    val lastModifiedAt: OffsetDateTime?,
    val orderState: Order.OrderState,
    val totalPrice: Long,
) {
    fun toResponse() = OrderResponse(
        orderId = orderId,
        userId = userId,
        productQuantity = productQuantity,
        productName = productName,
        productId = productId,
        createdAt = createdAt,
        lastModifiedAt = lastModifiedAt,
        orderState = orderState,
        totalPrice = totalPrice,
    )
}

fun Order.toDto() = OrderDto(
    orderId = orderId,
    userId = user.userId,
    productQuantity = productQuantity,
    productName = product.productName,
    productId = product.productId,
    createdAt = createdAt,
    lastModifiedAt = lastModifiedAt,
    orderState = orderState,
    totalPrice = totalPrice
)
