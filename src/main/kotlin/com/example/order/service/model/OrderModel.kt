package com.example.order.service.model

import com.example.order.entity.order.Order
import com.example.order.entity.product.Product
import com.example.order.entity.product.ProductId
import com.example.order.entity.user.User

class OrderModel(
    val productQuantity: Int,
    val productId: ProductId,
) {
    fun toEntity(user: User, product: Product) = Order(
        user = user,
        productQuantity = productQuantity,
        product = product
    )
}
