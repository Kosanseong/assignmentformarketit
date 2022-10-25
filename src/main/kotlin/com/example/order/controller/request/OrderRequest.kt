package com.example.order.controller.request

import com.example.order.entity.product.ProductId
import com.example.order.entity.user.UserId
import com.example.order.service.model.OrderModel

class OrderRequest(
    val productInOrder: List<ProductInOrderForRequest>,
) {
    class ProductInOrderForRequest(
        val productQuantity: Int,
        val productId: ProductId,
    )

    fun toModel(userId: UserId) = productInOrder.map {
        OrderModel(
            productId = it.productId,
            productQuantity = it.productQuantity,
        )
    }
}
