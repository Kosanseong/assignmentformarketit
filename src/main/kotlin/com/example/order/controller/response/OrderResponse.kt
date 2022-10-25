package com.example.order.controller.response

import com.example.order.entity.order.Order
import com.example.order.entity.order.OrderId
import com.example.order.entity.product.ProductId
import com.example.order.entity.user.UserId
import java.time.OffsetDateTime

class OrderResponse(
    val orderId: OrderId,
    val userId: UserId,
    val productQuantity:Int,
    val productName: String,
    val productId: ProductId,
    val createdAt: OffsetDateTime,
    val lastModifiedAt: OffsetDateTime?,
    val orderState: Order.OrderState,
    val totalPrice: Long
)
