package com.example.order.entity.product

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Product(
    @Id
    @GeneratedValue
    val productId: ProductId = 0,
    val productName: String,
    val price: Long,
)

typealias ProductId = Long
